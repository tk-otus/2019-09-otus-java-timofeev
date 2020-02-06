package ru.nspk.osks;

import com.sun.management.GarbageCollectionNotificationInfo;
import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;


public class GC {
    private static int finalYoungDuration = 0;
    private static int finalYoungCount = 0;
    private static int finalOldDuration = 0;
    private static int finalOldCount = 0;

    public static void main(String... args) throws Exception {
        String pidName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Starting pid: " + pidName);
        switchOnMonitoring();
        long beginTime = System.currentTimeMillis();

        int size = 1000;
        int loopCounter = 10000;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("ru.otus:type=Benchmark");

        Benchmark mbean = new Benchmark(loopCounter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(size);

        try {
            mbean.run();
        } catch (OutOfMemoryError ex) {
        }

        System.out.println("Array size: " + mbean.getArraySize());
        System.out.println("time: " + (System.currentTimeMillis() - beginTime) / 1000);
        System.out.println("Young duration: " + finalYoungDuration);
        System.out.println("Young count: " + finalYoungCount);
        System.out.println("Old duration: " + finalOldDuration);
        System.out.println("Old count: " + finalOldCount);
    }

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name: " + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();

                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    if (gcName.equals("G1 Young Generation")) {
                        finalYoungDuration += duration;
                        finalYoungCount += 1;
                    } else {
                        finalOldDuration += duration;
                        finalOldCount += 1;
                    }

                    System.out.println("Name: " + gcName + ", action: " + gcAction + ", gcCause: " + gcCause + ", start: " + startTime + " (" + duration + " ms)");
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
