# Использование различных сборщиков мусора

## Описание задачи

Написанное приложение в цикле добавляет 100000 объектов типа ```new String(new char[0])``` в массив ```ArrayList<String>```, а затем удаляет из него 
половину объктов.

Было проведено несколько тестов с использованием различных сборщиков мусора с целью выявить наиболее оптимальный из 
них для решения этой задачи. Оптимальность будем определять по соотношения общего времени работы приложения и 
времени затраченного на сборку.

## Результаты
### Serial GC
<details>
  <summary>Конфигурация</summary>
  
```
-Xms32m
-Xmx32m
-XX:+UseSerialGC
-Xlog:gc=debug:file=./hw03-gc/logs/gc-%p.log:tags,uptime,time,level
```

</details>
<details>
  <summary>Лог выполнения</summary>

```
[2020-02-06T10:05:07.503-0300][0.061s][info][gc] Using Serial
[2020-02-06T10:05:21.002-0300][13.560s][info][gc] GC(0) Pause Young (Allocation Failure) 8M->3M(30M) 9.431ms
[2020-02-06T10:05:51.999-0300][44.556s][info][gc] GC(1) Pause Young (Allocation Failure) 11M->7M(30M) 15.274ms
[2020-02-06T10:06:21.485-0300][74.041s][info][gc] GC(2) Pause Young (Allocation Failure) 16M->13M(30M) 12.548ms
[2020-02-06T10:06:56.975-0300][109.530s][info][gc] GC(3) Pause Young (Allocation Failure) 20M->19M(30M) 19.682ms
[2020-02-06T10:07:32.020-0300][144.574s][info][gc] GC(4) Pause Young (Allocation Failure) 27M->27M(30M) 0.058ms
[2020-02-06T10:07:32.057-0300][144.611s][info][gc] GC(5) Pause Full (Allocation Failure) 27M->18M(30M) 36.884ms
[2020-02-06T10:08:02.976-0300][175.529s][info][gc] GC(6) Pause Young (Allocation Failure) 26M->26M(30M) 0.073ms
[2020-02-06T10:08:03.014-0300][175.567s][info][gc] GC(7) Pause Full (Allocation Failure) 26M->21M(30M) 38.307ms
[2020-02-06T10:08:33.708-0300][206.260s][info][gc] GC(8) Pause Full (Allocation Failure) 30M->24M(30M) 46.528ms
[2020-02-06T10:08:58.418-0300][230.969s][info][gc] GC(9) Pause Full (Allocation Failure) 30M->24M(30M) 67.406ms
[2020-02-06T10:09:23.464-0300][256.014s][info][gc] GC(10) Pause Full (Allocation Failure) 30M->26M(30M) 81.123ms
[2020-02-06T10:09:42.266-0300][274.816s][info][gc] GC(11) Pause Full (Allocation Failure) 30M->28M(30M) 51.451ms
[2020-02-06T10:09:51.717-0300][284.266s][info][gc] GC(12) Pause Full (Allocation Failure) 30M->28M(30M) 67.723ms
[2020-02-06T10:09:58.835-0300][291.384s][info][gc] GC(13) Pause Full (Allocation Failure) 29M->28M(30M) 72.782ms
[2020-02-06T10:09:58.893-0300][291.442s][info][gc] GC(14) Pause Full (Allocation Failure) 28M->28M(30M) 56.324ms
```

</details>

| Тип сборки | Количество | Суммарное время сборки |
| ---------- | ---------- | ---------------------- |
| Young      | 6          | 57 ms (0,057 s)        |
| Full       | 9          | 515 ms (0,515 s)       |

Объектов в массиве: 984 500 шт.

Общее время: 286 сек.

### Parallel GC
<details>
  <summary>Конфигурация</summary>
  
```
-Xms32m
-Xmx32m
-XX:+UseParallelGC
-Xlog:gc=debug:file=./hw03-gc/logs/gc-%p.log:tags,uptime,time,level
```

</details>
<details>
  <summary>Лог выполнения</summary>

```
[2020-02-06T10:16:19.542-0300][0.027s][info][gc] Using Parallel
[2020-02-06T10:16:33.242-0300][13.727s][info][gc] GC(0) Pause Young (Allocation Failure) 8M->3M(31M) 7.556ms
[2020-02-06T10:17:03.547-0300][44.030s][info][gc] GC(1) Pause Young (Allocation Failure) 11M->8M(31M) 14.727ms
[2020-02-06T10:17:33.837-0300][74.320s][info][gc] GC(2) Pause Young (Allocation Failure) 17M->15M(31M) 25.527ms
[2020-02-06T10:17:33.896-0300][74.378s][info][gc] GC(3) Pause Full (Ergonomics) 15M->10M(31M) 44.266ms
[2020-02-06T10:18:11.266-0300][111.748s][info][gc] GC(4) Pause Young (Allocation Failure) 16M->16M(31M) 20.414ms
[2020-02-06T10:18:11.301-0300][111.783s][info][gc] GC(5) Pause Full (Ergonomics) 16M->13M(31M) 35.225ms
[2020-02-06T10:18:21.994-0300][122.476s][info][gc] GC(6) Pause Full (Ergonomics) 21M->15M(31M) 43.737ms
[2020-02-06T10:18:53.098-0300][153.578s][info][gc] GC(7) Pause Full (Ergonomics) 24M->18M(31M) 49.668ms
[2020-02-06T10:19:17.926-0300][178.405s][info][gc] GC(8) Pause Full (Ergonomics) 24M->19M(31M) 61.713ms
[2020-02-06T10:19:36.382-0300][196.861s][info][gc] GC(9) Pause Full (Ergonomics) 28M->22M(31M) 56.369ms
[2020-02-06T10:20:07.204-0300][227.682s][info][gc] GC(10) Pause Full (Ergonomics) 29M->24M(31M) 63.910ms
[2020-02-06T10:20:30.150-0300][250.627s][info][gc] GC(11) Pause Full (Ergonomics) 29M->25M(31M) 70.845ms
[2020-02-06T10:20:47.452-0300][267.928s][info][gc] GC(12) Pause Full (Ergonomics) 29M->26M(31M) 68.218ms
[2020-02-06T10:21:00.367-0300][280.843s][info][gc] GC(13) Pause Full (Ergonomics) 29M->27M(31M) 68.409ms
[2020-02-06T10:21:08.521-0300][288.997s][info][gc] GC(14) Pause Full (Ergonomics) 29M->28M(31M) 87.511ms
[2020-02-06T10:21:14.537-0300][295.013s][info][gc] GC(15) Pause Full (Ergonomics) 29M->28M(31M) 73.849ms
[2020-02-06T10:21:16.392-0300][296.868s][info][gc] GC(16) Pause Full (Ergonomics) 28M->28M(31M) 69.006ms
[2020-02-06T10:21:16.459-0300][296.935s][info][gc] GC(17) Pause Full (Allocation Failure) 28M->28M(31M) 66.899ms
```

</details>

| Тип сборки | Количество | Суммарное время сборки |
| ---------- | ---------- | ---------------------- |
| Young      | 4          | 68 ms (0,068 s)        |
| Full       | 14         | 859 ms (0,859 s)       |

Объектов в массиве: 984 500 шт.

Общее время: 291 сек.


### ConcurrentMarkSweep GC
<details>
  <summary>Конфигурация</summary>
  
```
-Xms32m
-Xmx32m
-XX:+UseConcMarkSweepGC
-Xlog:gc=debug:file=./hw03-gc/logs/gc-%p.log:tags,uptime,time,level
```

</details>
<details>
  <summary>Лог выполнения</summary>

```
[2020-02-06T10:34:59.392-0300][0.049s][debug][gc] ConcGCThreads: 0
[2020-02-06T10:34:59.392-0300][0.049s][debug][gc] ParallelGCThreads: 4
[2020-02-06T10:34:59.392-0300][0.049s][info ][gc] Using Concurrent Mark Sweep
[2020-02-06T10:35:12.919-0300][13.575s][info ][gc] GC(0) Pause Young (Allocation Failure) 8M->3M(30M) 21.535ms
[2020-02-06T10:35:30.981-0300][31.636s][info ][gc] GC(1) Pause Young (Allocation Failure) 11M->9M(30M) 31.905ms
[2020-02-06T10:35:51.320-0300][51.975s][info ][gc] GC(2) Pause Young (Allocation Failure) 17M->14M(30M) 18.812ms
[2020-02-06T10:35:51.343-0300][51.998s][info ][gc] GC(3) Pause Initial Mark 14M->14M(30M) 7.257ms
[2020-02-06T10:35:51.343-0300][51.998s][info ][gc] GC(3) Concurrent Mark
[2020-02-06T10:35:51.359-0300][52.014s][debug][gc] GC(3) Concurrent active time: 15.457ms
[2020-02-06T10:35:51.359-0300][52.014s][info ][gc] GC(3) Concurrent Mark 15.631ms
[2020-02-06T10:35:51.359-0300][52.014s][info ][gc] GC(3) Concurrent Preclean
[2020-02-06T10:35:51.360-0300][52.015s][debug][gc] GC(3) Concurrent active time: 0.995ms
[2020-02-06T10:35:51.360-0300][52.015s][info ][gc] GC(3) Concurrent Preclean 1.020ms
[2020-02-06T10:35:51.360-0300][52.015s][debug][gc] GC(3) YG occupancy: 1346 K (9792 K)
[2020-02-06T10:35:51.364-0300][52.019s][info ][gc] GC(3) Pause Remark 14M->14M(30M) 4.334ms
[2020-02-06T10:35:51.365-0300][52.020s][info ][gc] GC(3) Concurrent Sweep
[2020-02-06T10:35:51.372-0300][52.027s][debug][gc] GC(3) Concurrent active time: 7.180ms
[2020-02-06T10:35:51.372-0300][52.027s][info ][gc] GC(3) Concurrent Sweep 7.240ms
[2020-02-06T10:35:51.372-0300][52.027s][info ][gc] GC(3) Concurrent Reset
[2020-02-06T10:35:51.372-0300][52.027s][debug][gc] GC(3) Concurrent active time: 0.021ms
[2020-02-06T10:35:51.372-0300][52.027s][info ][gc] GC(3) Concurrent Reset 0.041ms
[2020-02-06T10:36:14.231-0300][74.886s][info ][gc] GC(4) Pause Young (Allocation Failure) 17M->14M(30M) 18.303ms
[2020-02-06T10:36:14.234-0300][74.888s][info ][gc] GC(5) Pause Initial Mark 14M->14M(30M) 0.732ms
[2020-02-06T10:36:14.234-0300][74.888s][info ][gc] GC(5) Concurrent Mark
[2020-02-06T10:36:14.245-0300][74.899s][debug][gc] GC(5) Concurrent active time: 10.915ms
[2020-02-06T10:36:14.245-0300][74.899s][info ][gc] GC(5) Concurrent Mark 11.003ms
[2020-02-06T10:36:14.245-0300][74.899s][info ][gc] GC(5) Concurrent Preclean
[2020-02-06T10:36:14.247-0300][74.902s][debug][gc] GC(5) Concurrent active time: 2.228ms
[2020-02-06T10:36:14.247-0300][74.902s][info ][gc] GC(5) Concurrent Preclean 2.338ms
[2020-02-06T10:36:14.248-0300][74.902s][debug][gc] GC(5) YG occupancy: 1273 K (9792 K)
[2020-02-06T10:36:14.255-0300][74.909s][info ][gc] GC(5) Pause Remark 14M->14M(30M) 7.024ms
[2020-02-06T10:36:14.255-0300][74.910s][info ][gc] GC(5) Concurrent Sweep
[2020-02-06T10:36:14.264-0300][74.919s][debug][gc] GC(5) Concurrent active time: 8.811ms
[2020-02-06T10:36:14.264-0300][74.919s][info ][gc] GC(5) Concurrent Sweep 8.963ms
[2020-02-06T10:36:14.264-0300][74.919s][info ][gc] GC(5) Concurrent Reset
[2020-02-06T10:36:14.264-0300][74.919s][debug][gc] GC(5) Concurrent active time: 0.020ms
[2020-02-06T10:36:14.264-0300][74.919s][info ][gc] GC(5) Concurrent Reset 0.056ms
[2020-02-06T10:36:16.270-0300][76.924s][info ][gc] GC(6) Pause Initial Mark 13M->13M(30M) 1.763ms
[2020-02-06T10:36:16.270-0300][76.925s][info ][gc] GC(6) Concurrent Mark
[2020-02-06T10:36:16.280-0300][76.935s][debug][gc] GC(6) Concurrent active time: 10.036ms
[2020-02-06T10:36:16.280-0300][76.935s][info ][gc] GC(6) Concurrent Mark 10.109ms
[2020-02-06T10:36:16.280-0300][76.935s][info ][gc] GC(6) Concurrent Preclean
[2020-02-06T10:36:16.282-0300][76.936s][debug][gc] GC(6) Concurrent active time: 1.598ms
[2020-02-06T10:36:16.282-0300][76.936s][info ][gc] GC(6) Concurrent Preclean 1.631ms
[2020-02-06T10:36:16.282-0300][76.936s][debug][gc] GC(6) YG occupancy: 2328 K (9792 K)
[2020-02-06T10:36:16.285-0300][76.939s][info ][gc] GC(6) Pause Remark 13M->13M(30M) 2.610ms
[2020-02-06T10:36:16.285-0300][76.939s][info ][gc] GC(6) Concurrent Sweep
[2020-02-06T10:36:16.290-0300][76.944s][debug][gc] GC(6) Concurrent active time: 4.771ms
[2020-02-06T10:36:16.290-0300][76.944s][info ][gc] GC(6) Concurrent Sweep 4.840ms
[2020-02-06T10:36:16.290-0300][76.944s][info ][gc] GC(6) Concurrent Reset
[2020-02-06T10:36:16.290-0300][76.944s][debug][gc] GC(6) Concurrent active time: 0.010ms
[2020-02-06T10:36:16.290-0300][76.944s][info ][gc] GC(6) Concurrent Reset 0.024ms
[2020-02-06T10:36:18.296-0300][78.950s][info ][gc] GC(7) Pause Initial Mark 13M->13M(30M) 2.026ms
[2020-02-06T10:36:18.296-0300][78.950s][info ][gc] GC(7) Concurrent Mark
[2020-02-06T10:36:18.305-0300][78.959s][debug][gc] GC(7) Concurrent active time: 9.145ms
[2020-02-06T10:36:18.305-0300][78.959s][info ][gc] GC(7) Concurrent Mark 9.218ms
[2020-02-06T10:36:18.305-0300][78.959s][info ][gc] GC(7) Concurrent Preclean
[2020-02-06T10:36:18.307-0300][78.961s][debug][gc] GC(7) Concurrent active time: 1.646ms
[2020-02-06T10:36:18.307-0300][78.961s][info ][gc] GC(7) Concurrent Preclean 1.677ms
[2020-02-06T10:36:18.307-0300][78.961s][debug][gc] GC(7) YG occupancy: 2951 K (9792 K)
[2020-02-06T10:36:18.310-0300][78.964s][info ][gc] GC(7) Pause Remark 13M->13M(30M) 2.854ms
[2020-02-06T10:36:18.310-0300][78.964s][info ][gc] GC(7) Concurrent Sweep
[2020-02-06T10:36:18.315-0300][78.969s][debug][gc] GC(7) Concurrent active time: 5.293ms
[2020-02-06T10:36:18.315-0300][78.969s][info ][gc] GC(7) Concurrent Sweep 5.333ms
[2020-02-06T10:36:18.315-0300][78.969s][info ][gc] GC(7) Concurrent Reset
[2020-02-06T10:36:18.315-0300][78.969s][debug][gc] GC(7) Concurrent active time: 0.010ms
[2020-02-06T10:36:18.315-0300][78.969s][info ][gc] GC(7) Concurrent Reset 0.025ms
[2020-02-06T10:36:20.321-0300][80.975s][info ][gc] GC(8) Pause Initial Mark 14M->14M(30M) 3.327ms
[2020-02-06T10:36:20.321-0300][80.975s][info ][gc] GC(8) Concurrent Mark
[2020-02-06T10:36:20.332-0300][80.986s][debug][gc] GC(8) Concurrent active time: 10.829ms
[2020-02-06T10:36:20.332-0300][80.987s][info ][gc] GC(8) Concurrent Mark 11.037ms
[2020-02-06T10:36:20.332-0300][80.987s][info ][gc] GC(8) Concurrent Preclean
[2020-02-06T10:36:20.334-0300][80.989s][debug][gc] GC(8) Concurrent active time: 1.899ms
[2020-02-06T10:36:20.334-0300][80.989s][info ][gc] GC(8) Concurrent Preclean 2.079ms
[2020-02-06T10:36:20.335-0300][80.989s][info ][gc] GC(8) Concurrent Abortable Preclean
[2020-02-06T10:36:25.338-0300][85.992s][debug][gc] GC(8)  CMS: abort preclean due to time 
[2020-02-06T10:36:25.338-0300][85.992s][debug][gc] GC(8) Concurrent active time: 1160.655ms
[2020-02-06T10:36:25.338-0300][85.992s][info ][gc] GC(8) Concurrent Abortable Preclean 5003.242ms
[2020-02-06T10:36:25.338-0300][85.992s][debug][gc] GC(8) YG occupancy: 5049 K (9792 K)
[2020-02-06T10:36:25.341-0300][85.995s][info ][gc] GC(8) Pause Remark 15M->15M(30M) 3.268ms
[2020-02-06T10:36:25.341-0300][85.995s][info ][gc] GC(8) Concurrent Sweep
[2020-02-06T10:36:25.345-0300][85.999s][debug][gc] GC(8) Concurrent active time: 3.623ms
[2020-02-06T10:36:25.345-0300][85.999s][info ][gc] GC(8) Concurrent Sweep 3.665ms
[2020-02-06T10:36:25.345-0300][85.999s][info ][gc] GC(8) Concurrent Reset
[2020-02-06T10:36:25.345-0300][85.999s][debug][gc] GC(8) Concurrent active time: 0.010ms
[2020-02-06T10:36:25.345-0300][85.999s][info ][gc] GC(8) Concurrent Reset 0.025ms
[2020-02-06T10:36:27.349-0300][88.003s][info ][gc] GC(9) Pause Initial Mark 16M->16M(30M) 3.916ms
[2020-02-06T10:36:27.349-0300][88.003s][info ][gc] GC(9) Concurrent Mark
[2020-02-06T10:36:27.358-0300][88.012s][debug][gc] GC(9) Concurrent active time: 8.474ms
[2020-02-06T10:36:27.358-0300][88.012s][info ][gc] GC(9) Concurrent Mark 8.561ms
[2020-02-06T10:36:27.358-0300][88.012s][info ][gc] GC(9) Concurrent Preclean
[2020-02-06T10:36:27.360-0300][88.014s][debug][gc] GC(9) Concurrent active time: 1.736ms
[2020-02-06T10:36:27.360-0300][88.014s][info ][gc] GC(9) Concurrent Preclean 1.758ms
[2020-02-06T10:36:27.360-0300][88.014s][info ][gc] GC(9) Concurrent Abortable Preclean
[2020-02-06T10:36:32.429-0300][93.083s][debug][gc] GC(9)  CMS: abort preclean due to time 
[2020-02-06T10:36:32.429-0300][93.083s][debug][gc] GC(9) Concurrent active time: 1154.656ms
[2020-02-06T10:36:32.429-0300][93.083s][info ][gc] GC(9) Concurrent Abortable Preclean 5069.158ms
[2020-02-06T10:36:32.429-0300][93.083s][debug][gc] GC(9) YG occupancy: 7203 K (9792 K)
[2020-02-06T10:36:32.434-0300][93.087s][info ][gc] GC(9) Pause Remark 17M->17M(30M) 4.692ms
[2020-02-06T10:36:32.434-0300][93.088s][info ][gc] GC(9) Concurrent Sweep
[2020-02-06T10:36:32.437-0300][93.091s][debug][gc] GC(9) Concurrent active time: 3.385ms
[2020-02-06T10:36:32.437-0300][93.091s][info ][gc] GC(9) Concurrent Sweep 3.417ms
[2020-02-06T10:36:32.437-0300][93.091s][info ][gc] GC(9) Concurrent Reset
[2020-02-06T10:36:32.437-0300][93.091s][debug][gc] GC(9) Concurrent active time: 0.010ms
[2020-02-06T10:36:32.437-0300][93.091s][info ][gc] GC(9) Concurrent Reset 0.024ms
[2020-02-06T10:36:34.445-0300][95.098s][info ][gc] GC(10) Pause Initial Mark 17M->17M(30M) 4.678ms
[2020-02-06T10:36:34.445-0300][95.099s][info ][gc] GC(10) Concurrent Mark
[2020-02-06T10:36:34.456-0300][95.110s][debug][gc] GC(10) Concurrent active time: 11.133ms
[2020-02-06T10:36:34.456-0300][95.110s][info ][gc] GC(10) Concurrent Mark 11.315ms
[2020-02-06T10:36:34.456-0300][95.110s][info ][gc] GC(10) Concurrent Preclean
[2020-02-06T10:36:34.458-0300][95.112s][debug][gc] GC(10) Concurrent active time: 1.741ms
[2020-02-06T10:36:34.458-0300][95.112s][info ][gc] GC(10) Concurrent Preclean 1.832ms
[2020-02-06T10:36:34.458-0300][95.112s][info ][gc] GC(10) Concurrent Abortable Preclean
[2020-02-06T10:36:39.543-0300][100.197s][debug][gc] GC(10)  CMS: abort preclean due to time 
[2020-02-06T10:36:39.545-0300][100.198s][debug][gc] GC(10) Concurrent active time: 1277.047ms
[2020-02-06T10:36:39.545-0300][100.198s][info ][gc] GC(10) Concurrent Abortable Preclean 5086.310ms
[2020-02-06T10:36:39.545-0300][100.198s][debug][gc] GC(10) YG occupancy: 9119 K (9792 K)
[2020-02-06T10:36:39.550-0300][100.204s][info ][gc] GC(10) Pause Remark 18M->18M(30M) 5.587ms
[2020-02-06T10:36:39.550-0300][100.204s][info ][gc] GC(10) Concurrent Sweep
[2020-02-06T10:36:39.553-0300][100.207s][debug][gc] GC(10) Concurrent active time: 3.068ms
[2020-02-06T10:36:39.553-0300][100.207s][info ][gc] GC(10) Concurrent Sweep 3.095ms
[2020-02-06T10:36:39.553-0300][100.207s][info ][gc] GC(10) Concurrent Reset
[2020-02-06T10:36:39.553-0300][100.207s][debug][gc] GC(10) Concurrent active time: 0.010ms
[2020-02-06T10:36:39.553-0300][100.207s][info ][gc] GC(10) Concurrent Reset 0.028ms
[2020-02-06T10:36:41.562-0300][102.216s][info ][gc] GC(11) Pause Initial Mark 18M->18M(30M) 5.298ms
[2020-02-06T10:36:41.564-0300][102.218s][info ][gc] GC(11) Concurrent Mark
[2020-02-06T10:36:41.576-0300][102.229s][debug][gc] GC(11) Concurrent active time: 11.404ms
[2020-02-06T10:36:41.576-0300][102.229s][info ][gc] GC(11) Concurrent Mark 11.628ms
[2020-02-06T10:36:41.576-0300][102.230s][info ][gc] GC(11) Concurrent Preclean
[2020-02-06T10:36:41.578-0300][102.231s][debug][gc] GC(11) Concurrent active time: 1.631ms
[2020-02-06T10:36:41.578-0300][102.231s][info ][gc] GC(11) Concurrent Preclean 1.665ms
[2020-02-06T10:36:41.578-0300][102.231s][info ][gc] GC(11) Concurrent Abortable Preclean
[2020-02-06T10:36:42.005-0300][102.658s][debug][gc] GC(11) Concurrent active time: 116.660ms
[2020-02-06T10:36:42.005-0300][102.658s][info ][gc] GC(11) Concurrent Abortable Preclean 427.270ms
[2020-02-06T10:36:42.008-0300][102.662s][info ][gc] GC(12) Pause Young (Allocation Failure) 18M->15M(30M) 22.866ms
[2020-02-06T10:36:42.025-0300][102.678s][debug][gc] GC(11) YG occupancy: 1302 K (9792 K)
[2020-02-06T10:36:42.033-0300][102.687s][info ][gc] GC(11) Pause Remark 15M->15M(30M) 8.401ms
[2020-02-06T10:36:42.033-0300][102.687s][info ][gc] GC(11) Concurrent Sweep
[2020-02-06T10:36:42.041-0300][102.694s][debug][gc] GC(11) Concurrent active time: 7.657ms
[2020-02-06T10:36:42.041-0300][102.694s][info ][gc] GC(11) Concurrent Sweep 7.761ms
[2020-02-06T10:36:42.041-0300][102.694s][info ][gc] GC(11) Concurrent Reset
[2020-02-06T10:36:42.041-0300][102.694s][debug][gc] GC(11) Concurrent active time: 0.035ms
[2020-02-06T10:36:42.041-0300][102.695s][info ][gc] GC(11) Concurrent Reset 0.071ms
[2020-02-06T10:36:44.046-0300][104.699s][info ][gc] GC(13) Pause Initial Mark 15M->15M(30M) 1.387ms
[2020-02-06T10:36:44.046-0300][104.700s][info ][gc] GC(13) Concurrent Mark
[2020-02-06T10:36:44.056-0300][104.710s][debug][gc] GC(13) Concurrent active time: 10.028ms
[2020-02-06T10:36:44.056-0300][104.710s][info ][gc] GC(13) Concurrent Mark 10.124ms
[2020-02-06T10:36:44.056-0300][104.710s][info ][gc] GC(13) Concurrent Preclean
[2020-02-06T10:36:44.058-0300][104.712s][debug][gc] GC(13) Concurrent active time: 2.006ms
[2020-02-06T10:36:44.058-0300][104.712s][info ][gc] GC(13) Concurrent Preclean 2.025ms
[2020-02-06T10:36:44.058-0300][104.712s][debug][gc] GC(13) YG occupancy: 1961 K (9792 K)
[2020-02-06T10:36:44.063-0300][104.716s][info ][gc] GC(13) Pause Remark 15M->15M(30M) 4.609ms
[2020-02-06T10:36:44.063-0300][104.716s][info ][gc] GC(13) Concurrent Sweep
[2020-02-06T10:36:44.068-0300][104.721s][debug][gc] GC(13) Concurrent active time: 4.875ms
[2020-02-06T10:36:44.068-0300][104.721s][info ][gc] GC(13) Concurrent Sweep 4.933ms
[2020-02-06T10:36:44.068-0300][104.721s][info ][gc] GC(13) Concurrent Reset
[2020-02-06T10:36:44.068-0300][104.721s][debug][gc] GC(13) Concurrent active time: 0.012ms
[2020-02-06T10:36:44.068-0300][104.721s][info ][gc] GC(13) Concurrent Reset 0.032ms
[2020-02-06T10:36:46.075-0300][106.729s][info ][gc] GC(14) Pause Initial Mark 16M->16M(30M) 2.200ms
[2020-02-06T10:36:46.075-0300][106.729s][info ][gc] GC(14) Concurrent Mark
[2020-02-06T10:36:46.090-0300][106.743s][debug][gc] GC(14) Concurrent active time: 14.069ms
[2020-02-06T10:36:46.090-0300][106.743s][info ][gc] GC(14) Concurrent Mark 14.269ms
[2020-02-06T10:36:46.090-0300][106.743s][info ][gc] GC(14) Concurrent Preclean
[2020-02-06T10:36:46.092-0300][106.745s][debug][gc] GC(14) Concurrent active time: 2.171ms
[2020-02-06T10:36:46.092-0300][106.745s][info ][gc] GC(14) Concurrent Preclean 2.283ms
[2020-02-06T10:36:46.092-0300][106.746s][debug][gc] GC(14) YG occupancy: 2432 K (9792 K)
[2020-02-06T10:36:46.097-0300][106.751s][info ][gc] GC(14) Pause Remark 16M->16M(30M) 5.151ms
[2020-02-06T10:36:46.098-0300][106.751s][info ][gc] GC(14) Concurrent Sweep
[2020-02-06T10:36:46.104-0300][106.757s][debug][gc] GC(14) Concurrent active time: 6.159ms
[2020-02-06T10:36:46.104-0300][106.757s][info ][gc] GC(14) Concurrent Sweep 6.294ms
[2020-02-06T10:36:46.104-0300][106.757s][info ][gc] GC(14) Concurrent Reset
[2020-02-06T10:36:46.104-0300][106.757s][debug][gc] GC(14) Concurrent active time: 0.012ms
[2020-02-06T10:36:46.104-0300][106.757s][info ][gc] GC(14) Concurrent Reset 0.033ms
[2020-02-06T10:36:48.108-0300][108.761s][info ][gc] GC(15) Pause Initial Mark 16M->16M(30M) 2.173ms
[2020-02-06T10:36:48.108-0300][108.761s][info ][gc] GC(15) Concurrent Mark
[2020-02-06T10:36:48.122-0300][108.775s][debug][gc] GC(15) Concurrent active time: 14.381ms
[2020-02-06T10:36:48.122-0300][108.776s][info ][gc] GC(15) Concurrent Mark 14.643ms
[2020-02-06T10:36:48.122-0300][108.776s][info ][gc] GC(15) Concurrent Preclean
[2020-02-06T10:36:48.126-0300][108.779s][debug][gc] GC(15) Concurrent active time: 3.308ms
[2020-02-06T10:36:48.126-0300][108.779s][info ][gc] GC(15) Concurrent Preclean 3.655ms
[2020-02-06T10:36:48.127-0300][108.780s][debug][gc] GC(15) YG occupancy: 3028 K (9792 K)
[2020-02-06T10:36:48.134-0300][108.788s][info ][gc] GC(15) Pause Remark 16M->16M(30M) 7.624ms
[2020-02-06T10:36:48.135-0300][108.788s][info ][gc] GC(15) Concurrent Sweep
[2020-02-06T10:36:48.143-0300][108.796s][debug][gc] GC(15) Concurrent active time: 8.221ms
[2020-02-06T10:36:48.143-0300][108.796s][info ][gc] GC(15) Concurrent Sweep 8.434ms
[2020-02-06T10:36:48.143-0300][108.796s][info ][gc] GC(15) Concurrent Reset
[2020-02-06T10:36:48.143-0300][108.797s][debug][gc] GC(15) Concurrent active time: 0.127ms
[2020-02-06T10:36:48.143-0300][108.797s][info ][gc] GC(15) Concurrent Reset 0.188ms
[2020-02-06T10:36:50.148-0300][110.801s][info ][gc] GC(16) Pause Initial Mark 16M->16M(30M) 2.304ms
[2020-02-06T10:36:50.148-0300][110.801s][info ][gc] GC(16) Concurrent Mark
[2020-02-06T10:36:50.159-0300][110.812s][debug][gc] GC(16) Concurrent active time: 10.771ms
[2020-02-06T10:36:50.159-0300][110.812s][info ][gc] GC(16) Concurrent Mark 10.844ms
[2020-02-06T10:36:50.159-0300][110.812s][info ][gc] GC(16) Concurrent Preclean
[2020-02-06T10:36:50.161-0300][110.815s][debug][gc] GC(16) Concurrent active time: 2.274ms
[2020-02-06T10:36:50.161-0300][110.815s][info ][gc] GC(16) Concurrent Preclean 2.307ms
[2020-02-06T10:36:50.161-0300][110.815s][info ][gc] GC(16) Concurrent Abortable Preclean
[2020-02-06T10:36:55.233-0300][115.886s][debug][gc] GC(16)  CMS: abort preclean due to time 
[2020-02-06T10:36:55.233-0300][115.886s][debug][gc] GC(16) Concurrent active time: 1179.593ms
[2020-02-06T10:36:55.233-0300][115.887s][info ][gc] GC(16) Concurrent Abortable Preclean 5071.927ms
[2020-02-06T10:36:55.233-0300][115.887s][debug][gc] GC(16) YG occupancy: 7566 K (9792 K)
[2020-02-06T10:36:55.240-0300][115.893s][info ][gc] GC(16) Pause Remark 20M->20M(30M) 6.346ms
[2020-02-06T10:36:55.240-0300][115.893s][info ][gc] GC(16) Concurrent Sweep
[2020-02-06T10:36:55.244-0300][115.897s][debug][gc] GC(16) Concurrent active time: 4.275ms
[2020-02-06T10:36:55.244-0300][115.897s][info ][gc] GC(16) Concurrent Sweep 4.310ms
[2020-02-06T10:36:55.244-0300][115.897s][info ][gc] GC(16) Concurrent Reset
[2020-02-06T10:36:55.244-0300][115.897s][debug][gc] GC(16) Concurrent active time: 0.012ms
[2020-02-06T10:36:55.244-0300][115.897s][info ][gc] GC(16) Concurrent Reset 0.033ms
[2020-02-06T10:36:57.255-0300][117.908s][info ][gc] GC(17) Pause Initial Mark 21M->21M(30M) 9.078ms
[2020-02-06T10:36:57.255-0300][117.908s][info ][gc] GC(17) Concurrent Mark
[2020-02-06T10:36:57.266-0300][117.919s][debug][gc] GC(17) Concurrent active time: 10.782ms
[2020-02-06T10:36:57.266-0300][117.919s][info ][gc] GC(17) Concurrent Mark 10.830ms
[2020-02-06T10:36:57.266-0300][117.919s][info ][gc] GC(17) Concurrent Preclean
[2020-02-06T10:36:57.266-0300][117.919s][debug][gc] GC(17) Concurrent active time: 0.058ms
[2020-02-06T10:36:57.266-0300][117.919s][info ][gc] GC(17) Concurrent Preclean 0.075ms
[2020-02-06T10:36:57.266-0300][117.919s][info ][gc] GC(17) Concurrent Abortable Preclean
[2020-02-06T10:37:02.367-0300][123.020s][debug][gc] GC(17)  CMS: abort preclean due to time 
[2020-02-06T10:37:02.369-0300][123.022s][debug][gc] GC(17) Concurrent active time: 549.237ms
[2020-02-06T10:37:02.369-0300][123.022s][info ][gc] GC(17) Concurrent Abortable Preclean 5103.279ms
[2020-02-06T10:37:02.370-0300][123.023s][debug][gc] GC(17) YG occupancy: 9607 K (9792 K)
[2020-02-06T10:37:02.377-0300][123.030s][info ][gc] GC(17) Pause Remark 22M->22M(30M) 6.849ms
[2020-02-06T10:37:02.377-0300][123.030s][info ][gc] GC(17) Concurrent Sweep
[2020-02-06T10:37:02.382-0300][123.035s][debug][gc] GC(17) Concurrent active time: 5.134ms
[2020-02-06T10:37:02.382-0300][123.035s][info ][gc] GC(17) Concurrent Sweep 5.198ms
[2020-02-06T10:37:02.382-0300][123.035s][info ][gc] GC(17) Concurrent Reset
[2020-02-06T10:37:02.382-0300][123.035s][debug][gc] GC(17) Concurrent active time: 0.013ms
[2020-02-06T10:37:02.382-0300][123.035s][info ][gc] GC(17) Concurrent Reset 0.042ms
[2020-02-06T10:37:03.420-0300][124.073s][info ][gc] GC(18) Pause Young (Allocation Failure) 20M->17M(30M) 14.647ms
[2020-02-06T10:37:03.421-0300][124.073s][info ][gc] GC(19) Pause Initial Mark 18M->18M(30M) 0.570ms
[2020-02-06T10:37:03.421-0300][124.073s][info ][gc] GC(19) Concurrent Mark
[2020-02-06T10:37:03.437-0300][124.090s][debug][gc] GC(19) Concurrent active time: 16.301ms
[2020-02-06T10:37:03.437-0300][124.090s][info ][gc] GC(19) Concurrent Mark 16.552ms
[2020-02-06T10:37:03.437-0300][124.090s][info ][gc] GC(19) Concurrent Preclean
[2020-02-06T10:37:03.441-0300][124.093s][debug][gc] GC(19) Concurrent active time: 3.267ms
[2020-02-06T10:37:03.441-0300][124.093s][info ][gc] GC(19) Concurrent Preclean 3.354ms
[2020-02-06T10:37:03.441-0300][124.094s][debug][gc] GC(19) YG occupancy: 1249 K (9792 K)
[2020-02-06T10:37:03.447-0300][124.100s][info ][gc] GC(19) Pause Remark 18M->18M(30M) 6.278ms
[2020-02-06T10:37:03.447-0300][124.100s][info ][gc] GC(19) Concurrent Sweep
[2020-02-06T10:37:03.461-0300][124.113s][debug][gc] GC(19) Concurrent active time: 12.887ms
[2020-02-06T10:37:03.461-0300][124.114s][info ][gc] GC(19) Concurrent Sweep 13.381ms
[2020-02-06T10:37:03.461-0300][124.114s][info ][gc] GC(19) Concurrent Reset
[2020-02-06T10:37:03.462-0300][124.114s][debug][gc] GC(19) Concurrent active time: 0.222ms
[2020-02-06T10:37:03.462-0300][124.115s][info ][gc] GC(19) Concurrent Reset 0.477ms
[2020-02-06T10:37:05.467-0300][126.120s][info ][gc] GC(20) Pause Initial Mark 18M->18M(30M) 1.499ms
[2020-02-06T10:37:05.467-0300][126.120s][info ][gc] GC(20) Concurrent Mark
[2020-02-06T10:37:05.482-0300][126.135s][debug][gc] GC(20) Concurrent active time: 14.896ms
[2020-02-06T10:37:05.482-0300][126.135s][info ][gc] GC(20) Concurrent Mark 14.987ms
[2020-02-06T10:37:05.482-0300][126.135s][info ][gc] GC(20) Concurrent Preclean
[2020-02-06T10:37:05.485-0300][126.137s][debug][gc] GC(20) Concurrent active time: 2.588ms
[2020-02-06T10:37:05.485-0300][126.137s][info ][gc] GC(20) Concurrent Preclean 2.640ms
[2020-02-06T10:37:05.485-0300][126.138s][debug][gc] GC(20) YG occupancy: 1739 K (9792 K)
[2020-02-06T10:37:05.493-0300][126.146s][info ][gc] GC(20) Pause Remark 18M->18M(30M) 8.271ms
[2020-02-06T10:37:05.494-0300][126.147s][info ][gc] GC(20) Concurrent Sweep
[2020-02-06T10:37:05.509-0300][126.161s][debug][gc] GC(20) Concurrent active time: 14.747ms
[2020-02-06T10:37:05.509-0300][126.162s][info ][gc] GC(20) Concurrent Sweep 15.072ms
[2020-02-06T10:37:05.509-0300][126.162s][info ][gc] GC(20) Concurrent Reset
[2020-02-06T10:37:05.509-0300][126.162s][debug][gc] GC(20) Concurrent active time: 0.039ms
[2020-02-06T10:37:05.509-0300][126.162s][info ][gc] GC(20) Concurrent Reset 0.141ms
[2020-02-06T10:37:07.515-0300][128.168s][info ][gc] GC(21) Pause Initial Mark 18M->18M(30M) 1.488ms
[2020-02-06T10:37:07.515-0300][128.168s][info ][gc] GC(21) Concurrent Mark
[2020-02-06T10:37:07.529-0300][128.182s][debug][gc] GC(21) Concurrent active time: 14.061ms
[2020-02-06T10:37:07.529-0300][128.182s][info ][gc] GC(21) Concurrent Mark 14.134ms
[2020-02-06T10:37:07.529-0300][128.182s][info ][gc] GC(21) Concurrent Preclean
[2020-02-06T10:37:07.532-0300][128.184s][debug][gc] GC(21) Concurrent active time: 2.426ms
[2020-02-06T10:37:07.532-0300][128.184s][info ][gc] GC(21) Concurrent Preclean 2.469ms
[2020-02-06T10:37:07.532-0300][128.185s][debug][gc] GC(21) YG occupancy: 2364 K (9792 K)
[2020-02-06T10:37:07.535-0300][128.188s][info ][gc] GC(21) Pause Remark 18M->18M(30M) 3.463ms
[2020-02-06T10:37:07.535-0300][128.188s][info ][gc] GC(21) Concurrent Sweep
[2020-02-06T10:37:07.542-0300][128.194s][debug][gc] GC(21) Concurrent active time: 6.090ms
[2020-02-06T10:37:07.542-0300][128.194s][info ][gc] GC(21) Concurrent Sweep 6.164ms
[2020-02-06T10:37:07.542-0300][128.194s][info ][gc] GC(21) Concurrent Reset
[2020-02-06T10:37:07.542-0300][128.194s][debug][gc] GC(21) Concurrent active time: 0.013ms
[2020-02-06T10:37:07.542-0300][128.194s][info ][gc] GC(21) Concurrent Reset 0.033ms
[2020-02-06T10:37:09.548-0300][130.200s][info ][gc] GC(22) Pause Initial Mark 18M->18M(30M) 1.477ms
[2020-02-06T10:37:09.548-0300][130.200s][info ][gc] GC(22) Concurrent Mark
[2020-02-06T10:37:09.562-0300][130.214s][debug][gc] GC(22) Concurrent active time: 13.788ms
[2020-02-06T10:37:09.562-0300][130.214s][info ][gc] GC(22) Concurrent Mark 13.838ms
[2020-02-06T10:37:09.562-0300][130.214s][info ][gc] GC(22) Concurrent Preclean
[2020-02-06T10:37:09.564-0300][130.216s][debug][gc] GC(22) Concurrent active time: 2.189ms
[2020-02-06T10:37:09.564-0300][130.216s][info ][gc] GC(22) Concurrent Preclean 2.218ms
[2020-02-06T10:37:09.564-0300][130.216s][debug][gc] GC(22) YG occupancy: 2856 K (9792 K)
[2020-02-06T10:37:09.567-0300][130.219s][info ][gc] GC(22) Pause Remark 18M->18M(30M) 2.797ms
[2020-02-06T10:37:09.567-0300][130.219s][info ][gc] GC(22) Concurrent Sweep
[2020-02-06T10:37:09.572-0300][130.225s][debug][gc] GC(22) Concurrent active time: 5.593ms
[2020-02-06T10:37:09.572-0300][130.225s][info ][gc] GC(22) Concurrent Sweep 5.636ms
[2020-02-06T10:37:09.572-0300][130.225s][info ][gc] GC(22) Concurrent Reset
[2020-02-06T10:37:09.572-0300][130.225s][debug][gc] GC(22) Concurrent active time: 0.010ms
[2020-02-06T10:37:09.572-0300][130.225s][info ][gc] GC(22) Concurrent Reset 0.024ms
[2020-02-06T10:37:11.578-0300][132.231s][info ][gc] GC(23) Pause Initial Mark 19M->19M(30M) 2.553ms
[2020-02-06T10:37:11.579-0300][132.231s][info ][gc] GC(23) Concurrent Mark
[2020-02-06T10:37:11.597-0300][132.250s][debug][gc] GC(23) Concurrent active time: 18.453ms
[2020-02-06T10:37:11.597-0300][132.250s][info ][gc] GC(23) Concurrent Mark 18.729ms
[2020-02-06T10:37:11.597-0300][132.250s][info ][gc] GC(23) Concurrent Preclean
[2020-02-06T10:37:11.600-0300][132.253s][debug][gc] GC(23) Concurrent active time: 2.525ms
[2020-02-06T10:37:11.600-0300][132.253s][info ][gc] GC(23) Concurrent Preclean 2.611ms
[2020-02-06T10:37:11.600-0300][132.253s][info ][gc] GC(23) Concurrent Abortable Preclean
[2020-02-06T10:37:16.703-0300][137.355s][debug][gc] GC(23)  CMS: abort preclean due to time 
[2020-02-06T10:37:16.703-0300][137.355s][debug][gc] GC(23) Concurrent active time: 1435.326ms
[2020-02-06T10:37:16.703-0300][137.355s][info ][gc] GC(23) Concurrent Abortable Preclean 5102.453ms
[2020-02-06T10:37:16.703-0300][137.355s][debug][gc] GC(23) YG occupancy: 4821 K (9792 K)
[2020-02-06T10:37:16.707-0300][137.359s][info ][gc] GC(23) Pause Remark 20M->20M(30M) 4.099ms
[2020-02-06T10:37:16.707-0300][137.359s][info ][gc] GC(23) Concurrent Sweep
[2020-02-06T10:37:16.712-0300][137.364s][debug][gc] GC(23) Concurrent active time: 4.987ms
[2020-02-06T10:37:16.712-0300][137.364s][info ][gc] GC(23) Concurrent Sweep 5.025ms
[2020-02-06T10:37:16.712-0300][137.364s][info ][gc] GC(23) Concurrent Reset
[2020-02-06T10:37:16.712-0300][137.364s][debug][gc] GC(23) Concurrent active time: 0.012ms
[2020-02-06T10:37:16.712-0300][137.364s][info ][gc] GC(23) Concurrent Reset 0.040ms
[2020-02-06T10:37:18.720-0300][139.372s][info ][gc] GC(24) Pause Initial Mark 21M->21M(30M) 3.196ms
[2020-02-06T10:37:18.722-0300][139.374s][info ][gc] GC(24) Concurrent Mark
[2020-02-06T10:37:18.734-0300][139.387s][debug][gc] GC(24) Concurrent active time: 12.395ms
[2020-02-06T10:37:18.734-0300][139.387s][info ][gc] GC(24) Concurrent Mark 12.545ms
[2020-02-06T10:37:18.734-0300][139.387s][info ][gc] GC(24) Concurrent Preclean
[2020-02-06T10:37:18.737-0300][139.389s][debug][gc] GC(24) Concurrent active time: 2.305ms
[2020-02-06T10:37:18.737-0300][139.389s][info ][gc] GC(24) Concurrent Preclean 2.330ms
[2020-02-06T10:37:18.737-0300][139.389s][info ][gc] GC(24) Concurrent Abortable Preclean
[2020-02-06T10:37:20.167-0300][140.819s][debug][gc] GC(24) Concurrent active time: 435.496ms
[2020-02-06T10:37:20.167-0300][140.819s][info ][gc] GC(24) Concurrent Abortable Preclean 1430.432ms
[2020-02-06T10:37:20.167-0300][140.820s][debug][gc] GC(24) YG occupancy: 5761 K (9792 K)
[2020-02-06T10:37:20.175-0300][140.827s][info ][gc] GC(24) Pause Remark 21M->21M(30M) 7.271ms
[2020-02-06T10:37:20.175-0300][140.827s][info ][gc] GC(24) Concurrent Sweep
[2020-02-06T10:37:20.181-0300][140.834s][debug][gc] GC(24) Concurrent active time: 6.753ms
[2020-02-06T10:37:20.181-0300][140.834s][info ][gc] GC(24) Concurrent Sweep 6.793ms
[2020-02-06T10:37:20.181-0300][140.834s][info ][gc] GC(24) Concurrent Reset
[2020-02-06T10:37:20.181-0300][140.834s][debug][gc] GC(24) Concurrent active time: 0.011ms
[2020-02-06T10:37:20.181-0300][140.834s][info ][gc] GC(24) Concurrent Reset 0.032ms
[2020-02-06T10:37:22.191-0300][142.843s][info ][gc] GC(25) Pause Initial Mark 21M->21M(30M) 5.434ms
[2020-02-06T10:37:22.191-0300][142.843s][info ][gc] GC(25) Concurrent Mark
[2020-02-06T10:37:22.209-0300][142.861s][debug][gc] GC(25) Concurrent active time: 17.870ms
[2020-02-06T10:37:22.209-0300][142.861s][info ][gc] GC(25) Concurrent Mark 18.111ms
[2020-02-06T10:37:22.209-0300][142.861s][info ][gc] GC(25) Concurrent Preclean
[2020-02-06T10:37:22.212-0300][142.864s][debug][gc] GC(25) Concurrent active time: 2.591ms
[2020-02-06T10:37:22.212-0300][142.864s][info ][gc] GC(25) Concurrent Preclean 2.748ms
[2020-02-06T10:37:22.212-0300][142.864s][info ][gc] GC(25) Concurrent Abortable Preclean
[2020-02-06T10:37:22.212-0300][142.864s][debug][gc] GC(25) Concurrent active time: 0.014ms
[2020-02-06T10:37:22.212-0300][142.864s][info ][gc] GC(25) Concurrent Abortable Preclean 0.063ms
[2020-02-06T10:37:22.212-0300][142.865s][debug][gc] GC(25) YG occupancy: 6372 K (9792 K)
[2020-02-06T10:37:22.218-0300][142.871s][info ][gc] GC(25) Pause Remark 21M->21M(30M) 6.055ms
[2020-02-06T10:37:22.219-0300][142.871s][info ][gc] GC(25) Concurrent Sweep
[2020-02-06T10:37:22.226-0300][142.878s][debug][gc] GC(25) Concurrent active time: 7.194ms
[2020-02-06T10:37:22.226-0300][142.878s][info ][gc] GC(25) Concurrent Sweep 7.288ms
[2020-02-06T10:37:22.226-0300][142.878s][info ][gc] GC(25) Concurrent Reset
[2020-02-06T10:37:22.226-0300][142.878s][debug][gc] GC(25) Concurrent active time: 0.010ms
[2020-02-06T10:37:22.226-0300][142.878s][info ][gc] GC(25) Concurrent Reset 0.024ms
[2020-02-06T10:37:24.229-0300][144.881s][info ][gc] GC(26) Pause Initial Mark 21M->21M(30M) 2.439ms
[2020-02-06T10:37:24.229-0300][144.881s][info ][gc] GC(26) Concurrent Mark
[2020-02-06T10:37:24.239-0300][144.891s][debug][gc] GC(26) Concurrent active time: 10.052ms
[2020-02-06T10:37:24.239-0300][144.891s][info ][gc] GC(26) Concurrent Mark 10.124ms
[2020-02-06T10:37:24.239-0300][144.891s][info ][gc] GC(26) Concurrent Preclean
[2020-02-06T10:37:24.242-0300][144.894s][debug][gc] GC(26) Concurrent active time: 2.693ms
[2020-02-06T10:37:24.242-0300][144.894s][info ][gc] GC(26) Concurrent Preclean 2.843ms
[2020-02-06T10:37:24.242-0300][144.894s][info ][gc] GC(26) Concurrent Abortable Preclean
[2020-02-06T10:37:24.242-0300][144.894s][debug][gc] GC(26) Concurrent active time: 0.023ms
[2020-02-06T10:37:24.242-0300][144.894s][info ][gc] GC(26) Concurrent Abortable Preclean 0.088ms
[2020-02-06T10:37:24.242-0300][144.894s][debug][gc] GC(26) YG occupancy: 6949 K (9792 K)
[2020-02-06T10:37:24.253-0300][144.905s][info ][gc] GC(26) Pause Remark 21M->21M(30M) 10.874ms
[2020-02-06T10:37:24.253-0300][144.905s][info ][gc] GC(26) Concurrent Sweep
[2020-02-06T10:37:24.261-0300][144.913s][debug][gc] GC(26) Concurrent active time: 7.354ms
[2020-02-06T10:37:24.261-0300][144.913s][info ][gc] GC(26) Concurrent Sweep 7.540ms
[2020-02-06T10:37:24.261-0300][144.913s][info ][gc] GC(26) Concurrent Reset
[2020-02-06T10:37:24.261-0300][144.913s][debug][gc] GC(26) Concurrent active time: 0.056ms
[2020-02-06T10:37:24.261-0300][144.913s][info ][gc] GC(26) Concurrent Reset 0.097ms
[2020-02-06T10:37:26.270-0300][146.922s][info ][gc] GC(27) Pause Initial Mark 22M->22M(30M) 4.283ms
[2020-02-06T10:37:26.270-0300][146.923s][info ][gc] GC(27) Concurrent Mark
[2020-02-06T10:37:26.283-0300][146.935s][debug][gc] GC(27) Concurrent active time: 12.141ms
[2020-02-06T10:37:26.283-0300][146.935s][info ][gc] GC(27) Concurrent Mark 12.201ms
[2020-02-06T10:37:26.283-0300][146.935s][info ][gc] GC(27) Concurrent Preclean
[2020-02-06T10:37:26.285-0300][146.937s][debug][gc] GC(27) Concurrent active time: 2.338ms
[2020-02-06T10:37:26.285-0300][146.937s][info ][gc] GC(27) Concurrent Preclean 2.361ms
[2020-02-06T10:37:26.285-0300][146.937s][info ][gc] GC(27) Concurrent Abortable Preclean
[2020-02-06T10:37:26.285-0300][146.937s][debug][gc] GC(27) Concurrent active time: 0.004ms
[2020-02-06T10:37:26.285-0300][146.937s][info ][gc] GC(27) Concurrent Abortable Preclean 0.016ms
[2020-02-06T10:37:26.285-0300][146.937s][debug][gc] GC(27) YG occupancy: 7431 K (9792 K)
[2020-02-06T10:37:26.289-0300][146.941s][info ][gc] GC(27) Pause Remark 22M->22M(30M) 4.109ms
[2020-02-06T10:37:26.289-0300][146.941s][info ][gc] GC(27) Concurrent Sweep
[2020-02-06T10:37:26.294-0300][146.946s][debug][gc] GC(27) Concurrent active time: 4.390ms
[2020-02-06T10:37:26.294-0300][146.946s][info ][gc] GC(27) Concurrent Sweep 4.425ms
[2020-02-06T10:37:26.294-0300][146.946s][info ][gc] GC(27) Concurrent Reset
[2020-02-06T10:37:26.294-0300][146.946s][debug][gc] GC(27) Concurrent active time: 0.010ms
[2020-02-06T10:37:26.294-0300][146.946s][info ][gc] GC(27) Concurrent Reset 0.023ms
[2020-02-06T10:37:28.301-0300][148.953s][info ][gc] GC(28) Pause Initial Mark 22M->22M(30M) 3.332ms
[2020-02-06T10:37:28.301-0300][148.953s][info ][gc] GC(28) Concurrent Mark
[2020-02-06T10:37:28.316-0300][148.968s][debug][gc] GC(28) Concurrent active time: 14.095ms
[2020-02-06T10:37:28.316-0300][148.968s][info ][gc] GC(28) Concurrent Mark 14.232ms
[2020-02-06T10:37:28.316-0300][148.968s][info ][gc] GC(28) Concurrent Preclean
[2020-02-06T10:37:28.318-0300][148.970s][debug][gc] GC(28) Concurrent active time: 2.321ms
[2020-02-06T10:37:28.318-0300][148.970s][info ][gc] GC(28) Concurrent Preclean 2.343ms
[2020-02-06T10:37:28.318-0300][148.970s][info ][gc] GC(28) Concurrent Abortable Preclean
[2020-02-06T10:37:33.390-0300][154.042s][debug][gc] GC(28)  CMS: abort preclean due to time 
[2020-02-06T10:37:33.390-0300][154.042s][debug][gc] GC(28) Concurrent active time: 1580.114ms
[2020-02-06T10:37:33.390-0300][154.042s][info ][gc] GC(28) Concurrent Abortable Preclean 5072.065ms
[2020-02-06T10:37:33.390-0300][154.042s][debug][gc] GC(28) YG occupancy: 9487 K (9792 K)
[2020-02-06T10:37:33.396-0300][154.048s][info ][gc] GC(28) Pause Remark 23M->23M(30M) 5.759ms
[2020-02-06T10:37:33.396-0300][154.048s][info ][gc] GC(28) Concurrent Sweep
[2020-02-06T10:37:33.401-0300][154.053s][debug][gc] GC(28) Concurrent active time: 4.450ms
[2020-02-06T10:37:33.401-0300][154.053s][info ][gc] GC(28) Concurrent Sweep 4.553ms
[2020-02-06T10:37:33.401-0300][154.053s][info ][gc] GC(28) Concurrent Reset
[2020-02-06T10:37:33.401-0300][154.053s][debug][gc] GC(28) Concurrent active time: 0.013ms
[2020-02-06T10:37:33.401-0300][154.053s][info ][gc] GC(28) Concurrent Reset 0.034ms
[2020-02-06T10:37:35.117-0300][155.768s][info ][gc] GC(29) Pause Young (Allocation Failure) 24M->20M(30M) 14.929ms
[2020-02-06T10:37:35.119-0300][155.771s][info ][gc] GC(30) Pause Initial Mark 20M->20M(30M) 0.640ms
[2020-02-06T10:37:35.119-0300][155.771s][info ][gc] GC(30) Concurrent Mark
[2020-02-06T10:37:35.135-0300][155.787s][debug][gc] GC(30) Concurrent active time: 15.561ms
[2020-02-06T10:37:35.135-0300][155.787s][info ][gc] GC(30) Concurrent Mark 15.743ms
[2020-02-06T10:37:35.135-0300][155.787s][info ][gc] GC(30) Concurrent Preclean
[2020-02-06T10:37:35.138-0300][155.790s][debug][gc] GC(30) Concurrent active time: 2.697ms
[2020-02-06T10:37:35.138-0300][155.790s][info ][gc] GC(30) Concurrent Preclean 2.739ms
[2020-02-06T10:37:35.138-0300][155.790s][debug][gc] GC(30) YG occupancy: 1338 K (9792 K)
[2020-02-06T10:37:35.142-0300][155.794s][info ][gc] GC(30) Pause Remark 20M->20M(30M) 4.500ms
[2020-02-06T10:37:35.142-0300][155.794s][info ][gc] GC(30) Concurrent Sweep
[2020-02-06T10:37:35.149-0300][155.801s][debug][gc] GC(30) Concurrent active time: 6.791ms
[2020-02-06T10:37:35.149-0300][155.801s][info ][gc] GC(30) Concurrent Sweep 6.871ms
[2020-02-06T10:37:35.149-0300][155.801s][info ][gc] GC(30) Concurrent Reset
[2020-02-06T10:37:35.149-0300][155.801s][debug][gc] GC(30) Concurrent active time: 0.015ms
[2020-02-06T10:37:35.149-0300][155.801s][info ][gc] GC(30) Concurrent Reset 0.058ms
[2020-02-06T10:37:37.154-0300][157.806s][info ][gc] GC(31) Pause Initial Mark 20M->20M(30M) 1.497ms
[2020-02-06T10:37:37.155-0300][157.807s][info ][gc] GC(31) Concurrent Mark
[2020-02-06T10:37:37.172-0300][157.824s][debug][gc] GC(31) Concurrent active time: 16.896ms
[2020-02-06T10:37:37.172-0300][157.824s][info ][gc] GC(31) Concurrent Mark 17.028ms
[2020-02-06T10:37:37.172-0300][157.824s][info ][gc] GC(31) Concurrent Preclean
[2020-02-06T10:37:37.174-0300][157.826s][debug][gc] GC(31) Concurrent active time: 2.576ms
[2020-02-06T10:37:37.174-0300][157.826s][info ][gc] GC(31) Concurrent Preclean 2.601ms
[2020-02-06T10:37:37.175-0300][157.826s][debug][gc] GC(31) YG occupancy: 1835 K (9792 K)
[2020-02-06T10:37:37.177-0300][157.829s][info ][gc] GC(31) Pause Remark 20M->20M(30M) 2.365ms
[2020-02-06T10:37:37.177-0300][157.829s][info ][gc] GC(31) Concurrent Sweep
[2020-02-06T10:37:37.183-0300][157.835s][debug][gc] GC(31) Concurrent active time: 6.172ms
[2020-02-06T10:37:37.183-0300][157.835s][info ][gc] GC(31) Concurrent Sweep 6.199ms
[2020-02-06T10:37:37.183-0300][157.835s][info ][gc] GC(31) Concurrent Reset
[2020-02-06T10:37:37.183-0300][157.835s][debug][gc] GC(31) Concurrent active time: 0.010ms
[2020-02-06T10:37:37.183-0300][157.835s][info ][gc] GC(31) Concurrent Reset 0.024ms
[2020-02-06T10:37:39.188-0300][159.839s][info ][gc] GC(32) Pause Initial Mark 21M->21M(30M) 1.237ms
[2020-02-06T10:37:39.188-0300][159.840s][info ][gc] GC(32) Concurrent Mark
[2020-02-06T10:37:39.222-0300][159.874s][debug][gc] GC(32) Concurrent active time: 33.951ms
[2020-02-06T10:37:39.222-0300][159.874s][info ][gc] GC(32) Concurrent Mark 34.344ms
[2020-02-06T10:37:39.222-0300][159.874s][info ][gc] GC(32) Concurrent Preclean
[2020-02-06T10:37:39.225-0300][159.877s][debug][gc] GC(32) Concurrent active time: 2.938ms
[2020-02-06T10:37:39.225-0300][159.877s][info ][gc] GC(32) Concurrent Preclean 3.009ms
[2020-02-06T10:37:39.225-0300][159.877s][debug][gc] GC(32) YG occupancy: 2413 K (9792 K)
[2020-02-06T10:37:39.231-0300][159.883s][info ][gc] GC(32) Pause Remark 21M->21M(30M) 5.720ms
[2020-02-06T10:37:39.231-0300][159.883s][info ][gc] GC(32) Concurrent Sweep
[2020-02-06T10:37:39.241-0300][159.893s][debug][gc] GC(32) Concurrent active time: 9.563ms
[2020-02-06T10:37:39.241-0300][159.893s][info ][gc] GC(32) Concurrent Sweep 9.683ms
[2020-02-06T10:37:39.241-0300][159.893s][info ][gc] GC(32) Concurrent Reset
[2020-02-06T10:37:39.241-0300][159.893s][debug][gc] GC(32) Concurrent active time: 0.010ms
[2020-02-06T10:37:39.241-0300][159.893s][info ][gc] GC(32) Concurrent Reset 0.024ms
[2020-02-06T10:37:41.248-0300][161.900s][info ][gc] GC(33) Pause Initial Mark 21M->21M(30M) 2.563ms
[2020-02-06T10:37:41.248-0300][161.900s][info ][gc] GC(33) Concurrent Mark
[2020-02-06T10:37:41.278-0300][161.929s][debug][gc] GC(33) Concurrent active time: 29.195ms
[2020-02-06T10:37:41.278-0300][161.929s][info ][gc] GC(33) Concurrent Mark 29.296ms
[2020-02-06T10:37:41.278-0300][161.929s][info ][gc] GC(33) Concurrent Preclean
[2020-02-06T10:37:41.281-0300][161.933s][debug][gc] GC(33) Concurrent active time: 3.242ms
[2020-02-06T10:37:41.281-0300][161.933s][info ][gc] GC(33) Concurrent Preclean 3.341ms
[2020-02-06T10:37:41.281-0300][161.933s][debug][gc] GC(33) YG occupancy: 2991 K (9792 K)
[2020-02-06T10:37:41.287-0300][161.939s][info ][gc] GC(33) Pause Remark 21M->21M(30M) 5.630ms
[2020-02-06T10:37:41.287-0300][161.939s][info ][gc] GC(33) Concurrent Sweep
[2020-02-06T10:37:41.296-0300][161.948s][debug][gc] GC(33) Concurrent active time: 8.743ms
[2020-02-06T10:37:41.296-0300][161.948s][info ][gc] GC(33) Concurrent Sweep 8.835ms
[2020-02-06T10:37:41.296-0300][161.948s][info ][gc] GC(33) Concurrent Reset
[2020-02-06T10:37:41.296-0300][161.948s][debug][gc] GC(33) Concurrent active time: 0.010ms
[2020-02-06T10:37:41.296-0300][161.948s][info ][gc] GC(33) Concurrent Reset 0.025ms
[2020-02-06T10:37:43.302-0300][163.953s][info ][gc] GC(34) Pause Initial Mark 21M->21M(30M) 1.329ms
[2020-02-06T10:37:43.302-0300][163.953s][info ][gc] GC(34) Concurrent Mark
[2020-02-06T10:37:43.317-0300][163.969s][debug][gc] GC(34) Concurrent active time: 15.162ms
[2020-02-06T10:37:43.317-0300][163.969s][info ][gc] GC(34) Concurrent Mark 15.370ms
[2020-02-06T10:37:43.317-0300][163.969s][info ][gc] GC(34) Concurrent Preclean
[2020-02-06T10:37:43.321-0300][163.973s][debug][gc] GC(34) Concurrent active time: 3.885ms
[2020-02-06T10:37:43.321-0300][163.973s][info ][gc] GC(34) Concurrent Preclean 4.165ms
[2020-02-06T10:37:43.321-0300][163.973s][info ][gc] GC(34) Concurrent Abortable Preclean
[2020-02-06T10:37:43.321-0300][163.973s][debug][gc] GC(34) Concurrent active time: 0.021ms
[2020-02-06T10:37:43.321-0300][163.973s][info ][gc] GC(34) Concurrent Abortable Preclean 0.108ms
[2020-02-06T10:37:43.322-0300][163.973s][debug][gc] GC(34) YG occupancy: 3447 K (9792 K)
[2020-02-06T10:37:43.329-0300][163.981s][info ][gc] GC(34) Pause Remark 21M->21M(30M) 7.705ms
[2020-02-06T10:37:43.330-0300][163.981s][info ][gc] GC(34) Concurrent Sweep
[2020-02-06T10:37:43.338-0300][163.989s][debug][gc] GC(34) Concurrent active time: 7.983ms
[2020-02-06T10:37:43.338-0300][163.990s][info ][gc] GC(34) Concurrent Sweep 8.250ms
[2020-02-06T10:37:43.338-0300][163.990s][info ][gc] GC(34) Concurrent Reset
[2020-02-06T10:37:43.338-0300][163.990s][debug][gc] GC(34) Concurrent active time: 0.088ms
[2020-02-06T10:37:43.338-0300][163.990s][info ][gc] GC(34) Concurrent Reset 0.258ms
[2020-02-06T10:37:45.344-0300][165.995s][info ][gc] GC(35) Pause Initial Mark 22M->22M(30M) 2.166ms
[2020-02-06T10:37:45.344-0300][165.996s][info ][gc] GC(35) Concurrent Mark
[2020-02-06T10:37:45.360-0300][166.011s][debug][gc] GC(35) Concurrent active time: 15.790ms
[2020-02-06T10:37:45.360-0300][166.011s][info ][gc] GC(35) Concurrent Mark 15.857ms
[2020-02-06T10:37:45.360-0300][166.011s][info ][gc] GC(35) Concurrent Preclean
[2020-02-06T10:37:45.363-0300][166.014s][debug][gc] GC(35) Concurrent active time: 2.709ms
[2020-02-06T10:37:45.363-0300][166.014s][info ][gc] GC(35) Concurrent Preclean 2.739ms
[2020-02-06T10:37:45.363-0300][166.014s][info ][gc] GC(35) Concurrent Abortable Preclean
[2020-02-06T10:37:45.363-0300][166.014s][debug][gc] GC(35) Concurrent active time: 0.003ms
[2020-02-06T10:37:45.363-0300][166.014s][info ][gc] GC(35) Concurrent Abortable Preclean 0.017ms
[2020-02-06T10:37:45.363-0300][166.014s][debug][gc] GC(35) YG occupancy: 4010 K (9792 K)
[2020-02-06T10:37:45.366-0300][166.017s][info ][gc] GC(35) Pause Remark 22M->22M(30M) 3.094ms
[2020-02-06T10:37:45.366-0300][166.017s][info ][gc] GC(35) Concurrent Sweep
[2020-02-06T10:37:45.372-0300][166.023s][debug][gc] GC(35) Concurrent active time: 5.788ms
[2020-02-06T10:37:45.372-0300][166.023s][info ][gc] GC(35) Concurrent Sweep 5.820ms
[2020-02-06T10:37:45.372-0300][166.023s][info ][gc] GC(35) Concurrent Reset
[2020-02-06T10:37:45.372-0300][166.023s][debug][gc] GC(35) Concurrent active time: 0.010ms
[2020-02-06T10:37:45.372-0300][166.023s][info ][gc] GC(35) Concurrent Reset 0.023ms
[2020-02-06T10:37:47.378-0300][168.029s][info ][gc] GC(36) Pause Initial Mark 22M->22M(30M) 2.954ms
[2020-02-06T10:37:47.378-0300][168.030s][info ][gc] GC(36) Concurrent Mark
[2020-02-06T10:37:47.396-0300][168.048s][debug][gc] GC(36) Concurrent active time: 17.999ms
[2020-02-06T10:37:47.396-0300][168.048s][info ][gc] GC(36) Concurrent Mark 18.152ms
[2020-02-06T10:37:47.396-0300][168.048s][info ][gc] GC(36) Concurrent Preclean
[2020-02-06T10:37:47.399-0300][168.051s][debug][gc] GC(36) Concurrent active time: 2.705ms
[2020-02-06T10:37:47.399-0300][168.051s][info ][gc] GC(36) Concurrent Preclean 2.734ms
[2020-02-06T10:37:47.399-0300][168.051s][info ][gc] GC(36) Concurrent Abortable Preclean
[2020-02-06T10:37:47.399-0300][168.051s][debug][gc] GC(36) Concurrent active time: 0.004ms
[2020-02-06T10:37:47.399-0300][168.051s][info ][gc] GC(36) Concurrent Abortable Preclean 0.017ms
[2020-02-06T10:37:47.399-0300][168.051s][debug][gc] GC(36) YG occupancy: 4597 K (9792 K)
[2020-02-06T10:37:47.403-0300][168.054s][info ][gc] GC(36) Pause Remark 22M->22M(30M) 3.373ms
[2020-02-06T10:37:47.403-0300][168.054s][info ][gc] GC(36) Concurrent Sweep
[2020-02-06T10:37:47.408-0300][168.060s][debug][gc] GC(36) Concurrent active time: 5.678ms
[2020-02-06T10:37:47.408-0300][168.060s][info ][gc] GC(36) Concurrent Sweep 5.702ms
[2020-02-06T10:37:47.408-0300][168.060s][info ][gc] GC(36) Concurrent Reset
[2020-02-06T10:37:47.408-0300][168.060s][debug][gc] GC(36) Concurrent active time: 0.010ms
[2020-02-06T10:37:47.408-0300][168.060s][info ][gc] GC(36) Concurrent Reset 0.023ms
[2020-02-06T10:37:49.416-0300][170.067s][info ][gc] GC(37) Pause Initial Mark 22M->22M(30M) 3.564ms
[2020-02-06T10:37:49.416-0300][170.067s][info ][gc] GC(37) Concurrent Mark
[2020-02-06T10:37:49.442-0300][170.094s][debug][gc] GC(37) Concurrent active time: 26.465ms
[2020-02-06T10:37:49.442-0300][170.094s][info ][gc] GC(37) Concurrent Mark 26.593ms
[2020-02-06T10:37:49.442-0300][170.094s][info ][gc] GC(37) Concurrent Preclean
[2020-02-06T10:37:49.445-0300][170.097s][debug][gc] GC(37) Concurrent active time: 2.931ms
[2020-02-06T10:37:49.445-0300][170.097s][info ][gc] GC(37) Concurrent Preclean 3.007ms
[2020-02-06T10:37:49.445-0300][170.097s][info ][gc] GC(37) Concurrent Abortable Preclean
[2020-02-06T10:37:49.445-0300][170.097s][debug][gc] GC(37) Concurrent active time: 0.008ms
[2020-02-06T10:37:49.445-0300][170.097s][info ][gc] GC(37) Concurrent Abortable Preclean 0.053ms
[2020-02-06T10:37:49.446-0300][170.097s][debug][gc] GC(37) YG occupancy: 5084 K (9792 K)
[2020-02-06T10:37:49.453-0300][170.105s][info ][gc] GC(37) Pause Remark 22M->22M(30M) 7.763ms
[2020-02-06T10:37:49.454-0300][170.105s][info ][gc] GC(37) Concurrent Sweep
[2020-02-06T10:37:49.463-0300][170.114s][debug][gc] GC(37) Concurrent active time: 9.219ms
[2020-02-06T10:37:49.463-0300][170.115s][info ][gc] GC(37) Concurrent Sweep 9.502ms
[2020-02-06T10:37:49.463-0300][170.115s][info ][gc] GC(37) Concurrent Reset
[2020-02-06T10:37:49.463-0300][170.115s][debug][gc] GC(37) Concurrent active time: 0.012ms
[2020-02-06T10:37:49.463-0300][170.115s][info ][gc] GC(37) Concurrent Reset 0.076ms
[2020-02-06T10:37:51.471-0300][172.123s][info ][gc] GC(38) Pause Initial Mark 23M->23M(30M) 3.760ms
[2020-02-06T10:37:51.471-0300][172.123s][info ][gc] GC(38) Concurrent Mark
[2020-02-06T10:37:51.494-0300][172.146s][debug][gc] GC(38) Concurrent active time: 23.150ms
[2020-02-06T10:37:51.495-0300][172.146s][info ][gc] GC(38) Concurrent Mark 23.310ms
[2020-02-06T10:37:51.495-0300][172.146s][info ][gc] GC(38) Concurrent Preclean
[2020-02-06T10:37:51.498-0300][172.150s][debug][gc] GC(38) Concurrent active time: 3.545ms
[2020-02-06T10:37:51.498-0300][172.150s][info ][gc] GC(38) Concurrent Preclean 3.652ms
[2020-02-06T10:37:51.498-0300][172.150s][info ][gc] GC(38) Concurrent Abortable Preclean
[2020-02-06T10:37:51.498-0300][172.150s][debug][gc] GC(38) Concurrent active time: 0.016ms
[2020-02-06T10:37:51.498-0300][172.150s][info ][gc] GC(38) Concurrent Abortable Preclean 0.043ms
[2020-02-06T10:37:51.498-0300][172.150s][debug][gc] GC(38) YG occupancy: 5657 K (9792 K)
[2020-02-06T10:37:51.504-0300][172.155s][info ][gc] GC(38) Pause Remark 23M->23M(30M) 5.427ms
[2020-02-06T10:37:51.504-0300][172.156s][info ][gc] GC(38) Concurrent Sweep
[2020-02-06T10:37:51.516-0300][172.168s][debug][gc] GC(38) Concurrent active time: 11.802ms
[2020-02-06T10:37:51.516-0300][172.168s][info ][gc] GC(38) Concurrent Sweep 11.969ms
[2020-02-06T10:37:51.516-0300][172.168s][info ][gc] GC(38) Concurrent Reset
[2020-02-06T10:37:51.516-0300][172.168s][debug][gc] GC(38) Concurrent active time: 0.011ms
[2020-02-06T10:37:51.516-0300][172.168s][info ][gc] GC(38) Concurrent Reset 0.027ms
[2020-02-06T10:37:53.525-0300][174.176s][info ][gc] GC(39) Pause Initial Mark 23M->23M(30M) 4.101ms
[2020-02-06T10:37:53.527-0300][174.178s][info ][gc] GC(39) Concurrent Mark
[2020-02-06T10:37:53.552-0300][174.203s][debug][gc] GC(39) Concurrent active time: 25.460ms
[2020-02-06T10:37:53.552-0300][174.204s][info ][gc] GC(39) Concurrent Mark 25.718ms
[2020-02-06T10:37:53.552-0300][174.204s][info ][gc] GC(39) Concurrent Preclean
[2020-02-06T10:37:53.556-0300][174.207s][debug][gc] GC(39) Concurrent active time: 3.709ms
[2020-02-06T10:37:53.556-0300][174.208s][info ][gc] GC(39) Concurrent Preclean 4.008ms
[2020-02-06T10:37:53.556-0300][174.208s][info ][gc] GC(39) Concurrent Abortable Preclean
[2020-02-06T10:37:53.557-0300][174.208s][debug][gc] GC(39) Concurrent active time: 0.032ms
[2020-02-06T10:37:53.557-0300][174.208s][info ][gc] GC(39) Concurrent Abortable Preclean 0.161ms
[2020-02-06T10:37:53.558-0300][174.209s][debug][gc] GC(39) YG occupancy: 6255 K (9792 K)
[2020-02-06T10:37:53.569-0300][174.220s][info ][gc] GC(39) Pause Remark 23M->23M(30M) 11.371ms
[2020-02-06T10:37:53.569-0300][174.221s][info ][gc] GC(39) Concurrent Sweep
[2020-02-06T10:37:53.581-0300][174.232s][debug][gc] GC(39) Concurrent active time: 10.094ms
[2020-02-06T10:37:53.581-0300][174.232s][info ][gc] GC(39) Concurrent Sweep 11.708ms
[2020-02-06T10:37:53.581-0300][174.232s][info ][gc] GC(39) Concurrent Reset
[2020-02-06T10:37:53.581-0300][174.233s][debug][gc] GC(39) Concurrent active time: 0.020ms
[2020-02-06T10:37:53.581-0300][174.233s][info ][gc] GC(39) Concurrent Reset 0.054ms
[2020-02-06T10:37:55.598-0300][176.249s][info ][gc] GC(40) Pause Initial Mark 24M->24M(30M) 11.782ms
[2020-02-06T10:37:55.611-0300][176.262s][info ][gc] GC(40) Concurrent Mark
[2020-02-06T10:37:55.663-0300][176.314s][debug][gc] GC(40) Concurrent active time: 51.842ms
[2020-02-06T10:37:55.663-0300][176.314s][info ][gc] GC(40) Concurrent Mark 52.017ms
[2020-02-06T10:37:55.663-0300][176.314s][info ][gc] GC(40) Concurrent Preclean
[2020-02-06T10:37:55.668-0300][176.320s][debug][gc] GC(40) Concurrent active time: 5.049ms
[2020-02-06T10:37:55.668-0300][176.320s][info ][gc] GC(40) Concurrent Preclean 5.117ms
[2020-02-06T10:37:55.668-0300][176.320s][info ][gc] GC(40) Concurrent Abortable Preclean
[2020-02-06T10:37:55.668-0300][176.320s][debug][gc] GC(40) Concurrent active time: 0.004ms
[2020-02-06T10:37:55.668-0300][176.320s][info ][gc] GC(40) Concurrent Abortable Preclean 0.027ms
[2020-02-06T10:37:55.669-0300][176.320s][debug][gc] GC(40) YG occupancy: 6718 K (9792 K)
[2020-02-06T10:37:55.681-0300][176.332s][info ][gc] GC(40) Pause Remark 24M->24M(30M) 11.414ms
[2020-02-06T10:37:55.685-0300][176.336s][info ][gc] GC(40) Concurrent Sweep
[2020-02-06T10:37:55.696-0300][176.348s][debug][gc] GC(40) Concurrent active time: 11.168ms
[2020-02-06T10:37:55.696-0300][176.348s][info ][gc] GC(40) Concurrent Sweep 11.283ms
[2020-02-06T10:37:55.697-0300][176.348s][info ][gc] GC(40) Concurrent Reset
[2020-02-06T10:37:55.697-0300][176.348s][debug][gc] GC(40) Concurrent active time: 0.021ms
[2020-02-06T10:37:55.697-0300][176.348s][info ][gc] GC(40) Concurrent Reset 0.048ms
[2020-02-06T10:37:57.705-0300][178.356s][info ][gc] GC(41) Pause Initial Mark 24M->24M(30M) 5.763ms
[2020-02-06T10:37:57.706-0300][178.358s][info ][gc] GC(41) Concurrent Mark
[2020-02-06T10:37:57.727-0300][178.378s][debug][gc] GC(41) Concurrent active time: 20.545ms
[2020-02-06T10:37:57.727-0300][178.378s][info ][gc] GC(41) Concurrent Mark 20.649ms
[2020-02-06T10:37:57.727-0300][178.378s][info ][gc] GC(41) Concurrent Preclean
[2020-02-06T10:37:57.730-0300][178.381s][debug][gc] GC(41) Concurrent active time: 2.962ms
[2020-02-06T10:37:57.730-0300][178.381s][info ][gc] GC(41) Concurrent Preclean 3.014ms
[2020-02-06T10:37:57.730-0300][178.381s][info ][gc] GC(41) Concurrent Abortable Preclean
[2020-02-06T10:37:57.730-0300][178.381s][debug][gc] GC(41) Concurrent active time: 0.005ms
[2020-02-06T10:37:57.730-0300][178.381s][info ][gc] GC(41) Concurrent Abortable Preclean 0.024ms
[2020-02-06T10:37:57.730-0300][178.382s][debug][gc] GC(41) YG occupancy: 7179 K (9792 K)
[2020-02-06T10:37:57.737-0300][178.389s][info ][gc] GC(41) Pause Remark 24M->24M(30M) 7.188ms
[2020-02-06T10:37:57.738-0300][178.389s][info ][gc] GC(41) Concurrent Sweep
[2020-02-06T10:37:57.745-0300][178.397s][debug][gc] GC(41) Concurrent active time: 7.862ms
[2020-02-06T10:37:57.745-0300][178.397s][info ][gc] GC(41) Concurrent Sweep 7.938ms
[2020-02-06T10:37:57.745-0300][178.397s][info ][gc] GC(41) Concurrent Reset
[2020-02-06T10:37:57.746-0300][178.397s][debug][gc] GC(41) Concurrent active time: 0.014ms
[2020-02-06T10:37:57.746-0300][178.397s][info ][gc] GC(41) Concurrent Reset 0.035ms
[2020-02-06T10:37:59.752-0300][180.404s][info ][gc] GC(42) Pause Initial Mark 24M->24M(30M) 2.682ms
[2020-02-06T10:37:59.754-0300][180.405s][info ][gc] GC(42) Concurrent Mark
[2020-02-06T10:37:59.769-0300][180.420s][debug][gc] GC(42) Concurrent active time: 14.759ms
[2020-02-06T10:37:59.769-0300][180.420s][info ][gc] GC(42) Concurrent Mark 14.877ms
[2020-02-06T10:37:59.769-0300][180.420s][info ][gc] GC(42) Concurrent Preclean
[2020-02-06T10:37:59.772-0300][180.423s][debug][gc] GC(42) Concurrent active time: 3.241ms
[2020-02-06T10:37:59.772-0300][180.423s][info ][gc] GC(42) Concurrent Preclean 3.293ms
[2020-02-06T10:37:59.772-0300][180.423s][info ][gc] GC(42) Concurrent Abortable Preclean
[2020-02-06T10:37:59.772-0300][180.423s][debug][gc] GC(42) Concurrent active time: 0.004ms
[2020-02-06T10:37:59.772-0300][180.423s][info ][gc] GC(42) Concurrent Abortable Preclean 0.023ms
[2020-02-06T10:37:59.772-0300][180.423s][debug][gc] GC(42) YG occupancy: 7756 K (9792 K)
[2020-02-06T10:37:59.778-0300][180.429s][info ][gc] GC(42) Pause Remark 24M->24M(30M) 6.054ms
[2020-02-06T10:37:59.778-0300][180.429s][info ][gc] GC(42) Concurrent Sweep
[2020-02-06T10:37:59.784-0300][180.435s][debug][gc] GC(42) Concurrent active time: 5.929ms
[2020-02-06T10:37:59.784-0300][180.435s][info ][gc] GC(42) Concurrent Sweep 5.972ms
[2020-02-06T10:37:59.784-0300][180.435s][info ][gc] GC(42) Concurrent Reset
[2020-02-06T10:37:59.784-0300][180.435s][debug][gc] GC(42) Concurrent active time: 0.011ms
[2020-02-06T10:37:59.784-0300][180.436s][info ][gc] GC(42) Concurrent Reset 0.026ms
[2020-02-06T10:38:01.792-0300][182.443s][info ][gc] GC(43) Pause Initial Mark 25M->25M(30M) 4.104ms
[2020-02-06T10:38:01.793-0300][182.444s][info ][gc] GC(43) Concurrent Mark
[2020-02-06T10:38:01.823-0300][182.474s][debug][gc] GC(43) Concurrent active time: 30.421ms
[2020-02-06T10:38:01.823-0300][182.474s][info ][gc] GC(43) Concurrent Mark 30.791ms
[2020-02-06T10:38:01.823-0300][182.475s][info ][gc] GC(43) Concurrent Preclean
[2020-02-06T10:38:01.827-0300][182.478s][debug][gc] GC(43) Concurrent active time: 3.175ms
[2020-02-06T10:38:01.827-0300][182.478s][info ][gc] GC(43) Concurrent Preclean 3.297ms
[2020-02-06T10:38:01.827-0300][182.478s][info ][gc] GC(43) Concurrent Abortable Preclean
[2020-02-06T10:38:01.827-0300][182.478s][debug][gc] GC(43) Concurrent active time: 0.014ms
[2020-02-06T10:38:01.827-0300][182.478s][info ][gc] GC(43) Concurrent Abortable Preclean 0.042ms
[2020-02-06T10:38:01.827-0300][182.478s][debug][gc] GC(43) YG occupancy: 8335 K (9792 K)
[2020-02-06T10:38:01.836-0300][182.487s][info ][gc] GC(43) Pause Remark 25M->25M(30M) 8.613ms
[2020-02-06T10:38:01.836-0300][182.487s][info ][gc] GC(43) Concurrent Sweep
[2020-02-06T10:38:01.845-0300][182.496s][debug][gc] GC(43) Concurrent active time: 8.975ms
[2020-02-06T10:38:01.845-0300][182.496s][info ][gc] GC(43) Concurrent Sweep 9.096ms
[2020-02-06T10:38:01.845-0300][182.496s][info ][gc] GC(43) Concurrent Reset
[2020-02-06T10:38:01.845-0300][182.496s][debug][gc] GC(43) Concurrent active time: 0.012ms
[2020-02-06T10:38:01.845-0300][182.496s][info ][gc] GC(43) Concurrent Reset 0.027ms
[2020-02-06T10:38:03.837-0300][184.488s][info ][gc] GC(44) Pause Young (Allocation Failure) 25M->25M(30M) 0.158ms
[2020-02-06T10:38:03.910-0300][184.561s][info ][gc] GC(45) Pause Full (Allocation Failure) 25M->20M(30M) 73.454ms
[2020-02-06T10:38:03.912-0300][184.563s][info ][gc] GC(46) Pause Initial Mark 24M->24M(30M) 2.101ms
[2020-02-06T10:38:03.913-0300][184.564s][info ][gc] GC(46) Concurrent Mark
[2020-02-06T10:38:03.933-0300][184.584s][debug][gc] GC(46) Concurrent active time: 20.213ms
[2020-02-06T10:38:03.933-0300][184.584s][info ][gc] GC(46) Concurrent Mark 20.277ms
[2020-02-06T10:38:03.933-0300][184.584s][info ][gc] GC(46) Concurrent Preclean
[2020-02-06T10:38:03.933-0300][184.584s][debug][gc] GC(46) Concurrent active time: 0.062ms
[2020-02-06T10:38:03.933-0300][184.584s][info ][gc] GC(46) Concurrent Preclean 0.079ms
[2020-02-06T10:38:03.933-0300][184.584s][info ][gc] GC(46) Concurrent Abortable Preclean
[2020-02-06T10:38:03.933-0300][184.584s][debug][gc] GC(46) Concurrent active time: 0.003ms
[2020-02-06T10:38:03.933-0300][184.584s][info ][gc] GC(46) Concurrent Abortable Preclean 0.017ms
[2020-02-06T10:38:03.933-0300][184.584s][debug][gc] GC(46) YG occupancy: 4015 K (9792 K)
[2020-02-06T10:38:03.938-0300][184.589s][info ][gc] GC(46) Pause Remark 24M->24M(30M) 5.164ms
[2020-02-06T10:38:03.938-0300][184.589s][info ][gc] GC(46) Concurrent Sweep
[2020-02-06T10:38:03.945-0300][184.596s][debug][gc] GC(46) Concurrent active time: 7.083ms
[2020-02-06T10:38:03.945-0300][184.597s][info ][gc] GC(46) Concurrent Sweep 7.140ms
[2020-02-06T10:38:03.946-0300][184.597s][info ][gc] GC(46) Concurrent Reset
[2020-02-06T10:38:03.946-0300][184.597s][debug][gc] GC(46) Concurrent active time: 0.014ms
[2020-02-06T10:38:03.946-0300][184.597s][info ][gc] GC(46) Concurrent Reset 0.035ms
[2020-02-06T10:38:05.958-0300][186.609s][info ][gc] GC(47) Pause Initial Mark 24M->24M(30M) 11.083ms
[2020-02-06T10:38:05.958-0300][186.609s][info ][gc] GC(47) Concurrent Mark
[2020-02-06T10:38:05.983-0300][186.634s][debug][gc] GC(47) Concurrent active time: 24.599ms
[2020-02-06T10:38:05.983-0300][186.634s][info ][gc] GC(47) Concurrent Mark 24.723ms
[2020-02-06T10:38:05.983-0300][186.634s][info ][gc] GC(47) Concurrent Preclean
[2020-02-06T10:38:05.983-0300][186.634s][debug][gc] GC(47) Concurrent active time: 0.073ms
[2020-02-06T10:38:05.983-0300][186.634s][info ][gc] GC(47) Concurrent Preclean 0.092ms
[2020-02-06T10:38:05.983-0300][186.634s][info ][gc] GC(47) Concurrent Abortable Preclean
[2020-02-06T10:38:05.983-0300][186.634s][debug][gc] GC(47) Concurrent active time: 0.005ms
[2020-02-06T10:38:05.983-0300][186.634s][info ][gc] GC(47) Concurrent Abortable Preclean 0.018ms
[2020-02-06T10:38:05.983-0300][186.634s][debug][gc] GC(47) YG occupancy: 4468 K (9792 K)
[2020-02-06T10:38:05.989-0300][186.640s][info ][gc] GC(47) Pause Remark 24M->24M(30M) 5.585ms
[2020-02-06T10:38:05.989-0300][186.640s][info ][gc] GC(47) Concurrent Sweep
[2020-02-06T10:38:05.996-0300][186.647s][debug][gc] GC(47) Concurrent active time: 7.169ms
[2020-02-06T10:38:05.996-0300][186.647s][info ][gc] GC(47) Concurrent Sweep 7.220ms
[2020-02-06T10:38:05.996-0300][186.647s][info ][gc] GC(47) Concurrent Reset
[2020-02-06T10:38:05.996-0300][186.647s][debug][gc] GC(47) Concurrent active time: 0.012ms
[2020-02-06T10:38:05.996-0300][186.647s][info ][gc] GC(47) Concurrent Reset 0.027ms
[2020-02-06T10:38:08.014-0300][188.665s][info ][gc] GC(48) Pause Initial Mark 22M->22M(30M) 13.595ms
[2020-02-06T10:38:08.015-0300][188.666s][info ][gc] GC(48) Concurrent Mark
[2020-02-06T10:38:08.058-0300][188.709s][debug][gc] GC(48) Concurrent active time: 42.307ms
[2020-02-06T10:38:08.058-0300][188.709s][info ][gc] GC(48) Concurrent Mark 42.393ms
[2020-02-06T10:38:08.058-0300][188.709s][info ][gc] GC(48) Concurrent Preclean
[2020-02-06T10:38:08.058-0300][188.709s][debug][gc] GC(48) Concurrent active time: 0.077ms
[2020-02-06T10:38:08.058-0300][188.709s][info ][gc] GC(48) Concurrent Preclean 0.098ms
[2020-02-06T10:38:08.058-0300][188.709s][info ][gc] GC(48) Concurrent Abortable Preclean
[2020-02-06T10:38:13.147-0300][193.797s][debug][gc] GC(48)  CMS: abort preclean due to time 
[2020-02-06T10:38:13.147-0300][193.798s][debug][gc] GC(48) Concurrent active time: 6.973ms
[2020-02-06T10:38:13.147-0300][193.798s][info ][gc] GC(48) Concurrent Abortable Preclean 5089.380ms
[2020-02-06T10:38:13.148-0300][193.799s][debug][gc] GC(48) YG occupancy: 6206 K (9792 K)
[2020-02-06T10:38:13.156-0300][193.807s][info ][gc] GC(48) Pause Remark 23M->23M(30M) 8.172ms
[2020-02-06T10:38:13.156-0300][193.807s][info ][gc] GC(48) Concurrent Sweep
[2020-02-06T10:38:13.164-0300][193.814s][debug][gc] GC(48) Concurrent active time: 7.484ms
[2020-02-06T10:38:13.164-0300][193.815s][info ][gc] GC(48) Concurrent Sweep 7.551ms
[2020-02-06T10:38:13.164-0300][193.815s][info ][gc] GC(48) Concurrent Reset
[2020-02-06T10:38:13.164-0300][193.815s][debug][gc] GC(48) Concurrent active time: 0.013ms
[2020-02-06T10:38:13.164-0300][193.815s][info ][gc] GC(48) Concurrent Reset 0.042ms
[2020-02-06T10:38:15.179-0300][195.830s][info ][gc] GC(49) Pause Initial Mark 24M->24M(30M) 12.081ms
[2020-02-06T10:38:15.180-0300][195.830s][info ][gc] GC(49) Concurrent Mark
[2020-02-06T10:38:15.209-0300][195.860s][debug][gc] GC(49) Concurrent active time: 29.157ms
[2020-02-06T10:38:15.209-0300][195.860s][info ][gc] GC(49) Concurrent Mark 29.351ms
[2020-02-06T10:38:15.209-0300][195.860s][info ][gc] GC(49) Concurrent Preclean
[2020-02-06T10:38:15.209-0300][195.860s][debug][gc] GC(49) Concurrent active time: 0.229ms
[2020-02-06T10:38:15.209-0300][195.860s][info ][gc] GC(49) Concurrent Preclean 0.317ms
[2020-02-06T10:38:15.209-0300][195.860s][info ][gc] GC(49) Concurrent Abortable Preclean
[2020-02-06T10:38:20.231-0300][200.882s][debug][gc] GC(49)  CMS: abort preclean due to time 
[2020-02-06T10:38:20.231-0300][200.882s][debug][gc] GC(49) Concurrent active time: 6.357ms
[2020-02-06T10:38:20.231-0300][200.882s][info ][gc] GC(49) Concurrent Abortable Preclean 5021.865ms
[2020-02-06T10:38:20.232-0300][200.882s][debug][gc] GC(49) YG occupancy: 8033 K (9792 K)
[2020-02-06T10:38:20.243-0300][200.894s][info ][gc] GC(49) Pause Remark 25M->25M(30M) 11.474ms
[2020-02-06T10:38:20.243-0300][200.894s][info ][gc] GC(49) Concurrent Sweep
[2020-02-06T10:38:20.256-0300][200.906s][debug][gc] GC(49) Concurrent active time: 12.026ms
[2020-02-06T10:38:20.256-0300][200.906s][info ][gc] GC(49) Concurrent Sweep 12.443ms
[2020-02-06T10:38:20.256-0300][200.907s][info ][gc] GC(49) Concurrent Reset
[2020-02-06T10:38:20.256-0300][200.907s][debug][gc] GC(49) Concurrent active time: 0.080ms
[2020-02-06T10:38:20.256-0300][200.907s][info ][gc] GC(49) Concurrent Reset 0.152ms
[2020-02-06T10:38:22.257-0300][202.907s][info ][gc] GC(50) Pause Young (Allocation Failure) 25M->26M(30M) 13.377ms
[2020-02-06T10:38:22.319-0300][202.969s][info ][gc] GC(51) Pause Full (Allocation Failure) 26M->22M(30M) 61.918ms
[2020-02-06T10:38:22.320-0300][202.971s][info ][gc] GC(52) Pause Initial Mark 22M->22M(30M) 1.079ms
[2020-02-06T10:38:22.320-0300][202.971s][info ][gc] GC(52) Concurrent Mark
[2020-02-06T10:38:22.337-0300][202.987s][debug][gc] GC(52) Concurrent active time: 16.563ms
[2020-02-06T10:38:22.337-0300][202.987s][info ][gc] GC(52) Concurrent Mark 16.631ms
[2020-02-06T10:38:22.337-0300][202.987s][info ][gc] GC(52) Concurrent Preclean
[2020-02-06T10:38:22.357-0300][203.008s][debug][gc] GC(52) Concurrent active time: 20.472ms
[2020-02-06T10:38:22.357-0300][203.008s][info ][gc] GC(52) Concurrent Preclean 20.522ms
[2020-02-06T10:38:22.358-0300][203.008s][debug][gc] GC(52) YG occupancy: 1552 K (9792 K)
[2020-02-06T10:38:22.362-0300][203.012s][info ][gc] GC(52) Pause Remark 22M->22M(30M) 4.198ms
[2020-02-06T10:38:22.362-0300][203.013s][info ][gc] GC(52) Concurrent Sweep
[2020-02-06T10:38:22.369-0300][203.020s][debug][gc] GC(52) Concurrent active time: 7.081ms
[2020-02-06T10:38:22.369-0300][203.020s][info ][gc] GC(52) Concurrent Sweep 7.147ms
[2020-02-06T10:38:22.369-0300][203.020s][info ][gc] GC(52) Concurrent Reset
[2020-02-06T10:38:22.369-0300][203.020s][debug][gc] GC(52) Concurrent active time: 0.010ms
[2020-02-06T10:38:22.369-0300][203.020s][info ][gc] GC(52) Concurrent Reset 0.024ms
[2020-02-06T10:38:24.378-0300][205.029s][info ][gc] GC(53) Pause Initial Mark 23M->23M(30M) 2.635ms
[2020-02-06T10:38:24.379-0300][205.029s][info ][gc] GC(53) Concurrent Mark
[2020-02-06T10:38:24.410-0300][205.061s][debug][gc] GC(53) Concurrent active time: 31.300ms
[2020-02-06T10:38:24.411-0300][205.061s][info ][gc] GC(53) Concurrent Mark 31.693ms
[2020-02-06T10:38:24.411-0300][205.061s][info ][gc] GC(53) Concurrent Preclean
[2020-02-06T10:38:24.415-0300][205.065s][debug][gc] GC(53) Concurrent active time: 3.893ms
[2020-02-06T10:38:24.415-0300][205.065s][info ][gc] GC(53) Concurrent Preclean 4.089ms
[2020-02-06T10:38:24.415-0300][205.065s][info ][gc] GC(53) Concurrent Abortable Preclean
[2020-02-06T10:38:24.415-0300][205.065s][debug][gc] GC(53) Concurrent active time: 0.027ms
[2020-02-06T10:38:24.415-0300][205.065s][info ][gc] GC(53) Concurrent Abortable Preclean 0.097ms
[2020-02-06T10:38:24.416-0300][205.066s][debug][gc] GC(53) YG occupancy: 2147 K (9792 K)
[2020-02-06T10:38:24.420-0300][205.070s][info ][gc] GC(53) Pause Remark 23M->23M(30M) 4.570ms
[2020-02-06T10:38:24.420-0300][205.071s][info ][gc] GC(53) Concurrent Sweep
[2020-02-06T10:38:24.427-0300][205.078s][debug][gc] GC(53) Concurrent active time: 7.057ms
[2020-02-06T10:38:24.427-0300][205.078s][info ][gc] GC(53) Concurrent Sweep 7.136ms
[2020-02-06T10:38:24.427-0300][205.078s][info ][gc] GC(53) Concurrent Reset
[2020-02-06T10:38:24.427-0300][205.078s][debug][gc] GC(53) Concurrent active time: 0.013ms
[2020-02-06T10:38:24.427-0300][205.078s][info ][gc] GC(53) Concurrent Reset 0.041ms
[2020-02-06T10:38:26.436-0300][207.086s][info ][gc] GC(54) Pause Initial Mark 23M->23M(30M) 3.101ms
[2020-02-06T10:38:26.438-0300][207.088s][info ][gc] GC(54) Concurrent Mark
[2020-02-06T10:38:26.466-0300][207.116s][debug][gc] GC(54) Concurrent active time: 28.183ms
[2020-02-06T10:38:26.466-0300][207.116s][info ][gc] GC(54) Concurrent Mark 28.414ms
[2020-02-06T10:38:26.466-0300][207.117s][info ][gc] GC(54) Concurrent Preclean
[2020-02-06T10:38:26.470-0300][207.121s][debug][gc] GC(54) Concurrent active time: 4.072ms
[2020-02-06T10:38:26.470-0300][207.121s][info ][gc] GC(54) Concurrent Preclean 4.267ms
[2020-02-06T10:38:26.471-0300][207.121s][info ][gc] GC(54) Concurrent Abortable Preclean
[2020-02-06T10:38:26.471-0300][207.121s][debug][gc] GC(54) Concurrent active time: 0.021ms
[2020-02-06T10:38:26.471-0300][207.121s][info ][gc] GC(54) Concurrent Abortable Preclean 0.066ms
[2020-02-06T10:38:26.471-0300][207.121s][debug][gc] GC(54) YG occupancy: 2610 K (9792 K)
[2020-02-06T10:38:26.479-0300][207.129s][info ][gc] GC(54) Pause Remark 23M->23M(30M) 7.992ms
[2020-02-06T10:38:26.479-0300][207.129s][info ][gc] GC(54) Concurrent Sweep
[2020-02-06T10:38:26.488-0300][207.138s][debug][gc] GC(54) Concurrent active time: 8.436ms
[2020-02-06T10:38:26.488-0300][207.138s][info ][gc] GC(54) Concurrent Sweep 8.563ms
[2020-02-06T10:38:26.488-0300][207.138s][info ][gc] GC(54) Concurrent Reset
[2020-02-06T10:38:26.488-0300][207.138s][debug][gc] GC(54) Concurrent active time: 0.012ms
[2020-02-06T10:38:26.488-0300][207.138s][info ][gc] GC(54) Concurrent Reset 0.027ms
[2020-02-06T10:38:28.491-0300][209.141s][info ][gc] GC(55) Pause Initial Mark 24M->24M(30M) 1.407ms
[2020-02-06T10:38:28.491-0300][209.142s][info ][gc] GC(55) Concurrent Mark
[2020-02-06T10:38:28.508-0300][209.159s][debug][gc] GC(55) Concurrent active time: 17.236ms
[2020-02-06T10:38:28.509-0300][209.159s][info ][gc] GC(55) Concurrent Mark 17.344ms
[2020-02-06T10:38:28.509-0300][209.159s][info ][gc] GC(55) Concurrent Preclean
[2020-02-06T10:38:28.513-0300][209.164s][debug][gc] GC(55) Concurrent active time: 4.560ms
[2020-02-06T10:38:28.513-0300][209.164s][info ][gc] GC(55) Concurrent Preclean 4.703ms
[2020-02-06T10:38:28.513-0300][209.164s][info ][gc] GC(55) Concurrent Abortable Preclean
[2020-02-06T10:38:28.513-0300][209.164s][debug][gc] GC(55) Concurrent active time: 0.032ms
[2020-02-06T10:38:28.514-0300][209.164s][info ][gc] GC(55) Concurrent Abortable Preclean 0.195ms
[2020-02-06T10:38:28.514-0300][209.164s][debug][gc] GC(55) YG occupancy: 3182 K (9792 K)
[2020-02-06T10:38:28.521-0300][209.172s][info ][gc] GC(55) Pause Remark 24M->24M(30M) 7.702ms
[2020-02-06T10:38:28.522-0300][209.172s][info ][gc] GC(55) Concurrent Sweep
[2020-02-06T10:38:28.533-0300][209.183s][debug][gc] GC(55) Concurrent active time: 11.007ms
[2020-02-06T10:38:28.533-0300][209.183s][info ][gc] GC(55) Concurrent Sweep 11.222ms
[2020-02-06T10:38:28.533-0300][209.183s][info ][gc] GC(55) Concurrent Reset
[2020-02-06T10:38:28.533-0300][209.183s][debug][gc] GC(55) Concurrent active time: 0.011ms
[2020-02-06T10:38:28.533-0300][209.183s][info ][gc] GC(55) Concurrent Reset 0.025ms
[2020-02-06T10:38:30.542-0300][211.192s][info ][gc] GC(56) Pause Initial Mark 24M->24M(30M) 2.970ms
[2020-02-06T10:38:30.542-0300][211.192s][info ][gc] GC(56) Concurrent Mark
[2020-02-06T10:38:30.565-0300][211.215s][debug][gc] GC(56) Concurrent active time: 23.039ms
[2020-02-06T10:38:30.565-0300][211.215s][info ][gc] GC(56) Concurrent Mark 23.123ms
[2020-02-06T10:38:30.565-0300][211.215s][info ][gc] GC(56) Concurrent Preclean
[2020-02-06T10:38:30.571-0300][211.221s][debug][gc] GC(56) Concurrent active time: 6.201ms
[2020-02-06T10:38:30.571-0300][211.222s][info ][gc] GC(56) Concurrent Preclean 6.325ms
[2020-02-06T10:38:30.571-0300][211.222s][info ][gc] GC(56) Concurrent Abortable Preclean
[2020-02-06T10:38:30.571-0300][211.222s][debug][gc] GC(56) Concurrent active time: 0.005ms
[2020-02-06T10:38:30.571-0300][211.222s][info ][gc] GC(56) Concurrent Abortable Preclean 0.021ms
[2020-02-06T10:38:30.572-0300][211.222s][debug][gc] GC(56) YG occupancy: 3731 K (9792 K)
[2020-02-06T10:38:30.580-0300][211.231s][info ][gc] GC(56) Pause Remark 24M->24M(30M) 8.110ms
[2020-02-06T10:38:30.580-0300][211.231s][info ][gc] GC(56) Concurrent Sweep
[2020-02-06T10:38:30.596-0300][211.246s][debug][gc] GC(56) Concurrent active time: 15.171ms
[2020-02-06T10:38:30.596-0300][211.246s][info ][gc] GC(56) Concurrent Sweep 15.356ms
[2020-02-06T10:38:30.596-0300][211.246s][info ][gc] GC(56) Concurrent Reset
[2020-02-06T10:38:30.596-0300][211.246s][debug][gc] GC(56) Concurrent active time: 0.078ms
[2020-02-06T10:38:30.596-0300][211.246s][info ][gc] GC(56) Concurrent Reset 0.167ms
[2020-02-06T10:38:32.598-0300][213.248s][info ][gc] GC(57) Pause Initial Mark 25M->25M(30M) 1.673ms
[2020-02-06T10:38:32.598-0300][213.248s][info ][gc] GC(57) Concurrent Mark
[2020-02-06T10:38:32.612-0300][213.262s][debug][gc] GC(57) Concurrent active time: 14.242ms
[2020-02-06T10:38:32.612-0300][213.262s][info ][gc] GC(57) Concurrent Mark 14.304ms
[2020-02-06T10:38:32.612-0300][213.262s][info ][gc] GC(57) Concurrent Preclean
[2020-02-06T10:38:32.615-0300][213.266s][debug][gc] GC(57) Concurrent active time: 3.210ms
[2020-02-06T10:38:32.615-0300][213.266s][info ][gc] GC(57) Concurrent Preclean 3.236ms
[2020-02-06T10:38:32.615-0300][213.266s][info ][gc] GC(57) Concurrent Abortable Preclean
[2020-02-06T10:38:32.615-0300][213.266s][debug][gc] GC(57) Concurrent active time: 0.003ms
[2020-02-06T10:38:32.615-0300][213.266s][info ][gc] GC(57) Concurrent Abortable Preclean 0.016ms
[2020-02-06T10:38:32.616-0300][213.266s][debug][gc] GC(57) YG occupancy: 4304 K (9792 K)
[2020-02-06T10:38:32.620-0300][213.270s][info ][gc] GC(57) Pause Remark 25M->25M(30M) 4.325ms
[2020-02-06T10:38:32.620-0300][213.270s][info ][gc] GC(57) Concurrent Sweep
[2020-02-06T10:38:32.627-0300][213.277s][debug][gc] GC(57) Concurrent active time: 6.711ms
[2020-02-06T10:38:32.627-0300][213.277s][info ][gc] GC(57) Concurrent Sweep 6.742ms
[2020-02-06T10:38:32.627-0300][213.277s][info ][gc] GC(57) Concurrent Reset
[2020-02-06T10:38:32.627-0300][213.277s][debug][gc] GC(57) Concurrent active time: 0.010ms
[2020-02-06T10:38:32.627-0300][213.277s][info ][gc] GC(57) Concurrent Reset 0.023ms
[2020-02-06T10:38:34.631-0300][215.281s][info ][gc] GC(58) Pause Initial Mark 25M->25M(30M) 3.171ms
[2020-02-06T10:38:34.631-0300][215.281s][info ][gc] GC(58) Concurrent Mark
[2020-02-06T10:38:34.648-0300][215.298s][debug][gc] GC(58) Concurrent active time: 16.649ms
[2020-02-06T10:38:34.648-0300][215.298s][info ][gc] GC(58) Concurrent Mark 16.708ms
[2020-02-06T10:38:34.648-0300][215.298s][info ][gc] GC(58) Concurrent Preclean
[2020-02-06T10:38:34.651-0300][215.302s][debug][gc] GC(58) Concurrent active time: 3.517ms
[2020-02-06T10:38:34.652-0300][215.302s][info ][gc] GC(58) Concurrent Preclean 3.563ms
[2020-02-06T10:38:34.652-0300][215.302s][info ][gc] GC(58) Concurrent Abortable Preclean
[2020-02-06T10:38:34.652-0300][215.302s][debug][gc] GC(58) Concurrent active time: 0.004ms
[2020-02-06T10:38:34.652-0300][215.302s][info ][gc] GC(58) Concurrent Abortable Preclean 0.017ms
[2020-02-06T10:38:34.652-0300][215.302s][debug][gc] GC(58) YG occupancy: 4789 K (9792 K)
[2020-02-06T10:38:34.656-0300][215.306s][info ][gc] GC(58) Pause Remark 25M->25M(30M) 4.623ms
[2020-02-06T10:38:34.656-0300][215.307s][info ][gc] GC(58) Concurrent Sweep
[2020-02-06T10:38:34.663-0300][215.313s][debug][gc] GC(58) Concurrent active time: 6.808ms
[2020-02-06T10:38:34.663-0300][215.313s][info ][gc] GC(58) Concurrent Sweep 6.863ms
[2020-02-06T10:38:34.663-0300][215.313s][info ][gc] GC(58) Concurrent Reset
[2020-02-06T10:38:34.663-0300][215.313s][debug][gc] GC(58) Concurrent active time: 0.011ms
[2020-02-06T10:38:34.663-0300][215.313s][info ][gc] GC(58) Concurrent Reset 0.026ms
[2020-02-06T10:38:36.672-0300][217.322s][info ][gc] GC(59) Pause Initial Mark 25M->25M(30M) 5.183ms
[2020-02-06T10:38:36.672-0300][217.322s][info ][gc] GC(59) Concurrent Mark
[2020-02-06T10:38:36.699-0300][217.349s][debug][gc] GC(59) Concurrent active time: 26.552ms
[2020-02-06T10:38:36.699-0300][217.349s][info ][gc] GC(59) Concurrent Mark 26.703ms
[2020-02-06T10:38:36.699-0300][217.349s][info ][gc] GC(59) Concurrent Preclean
[2020-02-06T10:38:36.702-0300][217.352s][debug][gc] GC(59) Concurrent active time: 3.191ms
[2020-02-06T10:38:36.702-0300][217.352s][info ][gc] GC(59) Concurrent Preclean 3.210ms
[2020-02-06T10:38:36.702-0300][217.352s][info ][gc] GC(59) Concurrent Abortable Preclean
[2020-02-06T10:38:36.702-0300][217.352s][debug][gc] GC(59) Concurrent active time: 0.004ms
[2020-02-06T10:38:36.702-0300][217.352s][info ][gc] GC(59) Concurrent Abortable Preclean 0.019ms
[2020-02-06T10:38:36.702-0300][217.352s][debug][gc] GC(59) YG occupancy: 5251 K (9792 K)
[2020-02-06T10:38:36.706-0300][217.356s][info ][gc] GC(59) Pause Remark 25M->25M(30M) 3.916ms
[2020-02-06T10:38:36.706-0300][217.356s][info ][gc] GC(59) Concurrent Sweep
[2020-02-06T10:38:36.713-0300][217.363s][debug][gc] GC(59) Concurrent active time: 6.614ms
[2020-02-06T10:38:36.713-0300][217.363s][info ][gc] GC(59) Concurrent Sweep 6.636ms
[2020-02-06T10:38:36.713-0300][217.363s][info ][gc] GC(59) Concurrent Reset
[2020-02-06T10:38:36.713-0300][217.363s][debug][gc] GC(59) Concurrent active time: 0.010ms
[2020-02-06T10:38:36.713-0300][217.363s][info ][gc] GC(59) Concurrent Reset 0.023ms
[2020-02-06T10:38:38.720-0300][219.370s][info ][gc] GC(60) Pause Initial Mark 26M->26M(30M) 5.296ms
[2020-02-06T10:38:38.721-0300][219.371s][info ][gc] GC(60) Concurrent Mark
[2020-02-06T10:38:38.746-0300][219.396s][debug][gc] GC(60) Concurrent active time: 24.882ms
[2020-02-06T10:38:38.746-0300][219.396s][info ][gc] GC(60) Concurrent Mark 25.152ms
[2020-02-06T10:38:38.746-0300][219.396s][info ][gc] GC(60) Concurrent Preclean
[2020-02-06T10:38:38.750-0300][219.400s][debug][gc] GC(60) Concurrent active time: 4.000ms
[2020-02-06T10:38:38.750-0300][219.400s][info ][gc] GC(60) Concurrent Preclean 4.147ms
[2020-02-06T10:38:38.750-0300][219.400s][info ][gc] GC(60) Concurrent Abortable Preclean
[2020-02-06T10:38:38.750-0300][219.400s][debug][gc] GC(60) Concurrent active time: 0.013ms
[2020-02-06T10:38:38.750-0300][219.400s][info ][gc] GC(60) Concurrent Abortable Preclean 0.036ms
[2020-02-06T10:38:38.750-0300][219.400s][debug][gc] GC(60) YG occupancy: 5825 K (9792 K)
[2020-02-06T10:38:38.757-0300][219.407s][info ][gc] GC(60) Pause Remark 26M->26M(30M) 7.185ms
[2020-02-06T10:38:38.758-0300][219.408s][info ][gc] GC(60) Concurrent Sweep
[2020-02-06T10:38:38.766-0300][219.416s][debug][gc] GC(60) Concurrent active time: 8.057ms
[2020-02-06T10:38:38.766-0300][219.416s][info ][gc] GC(60) Concurrent Sweep 8.139ms
[2020-02-06T10:38:38.766-0300][219.416s][info ][gc] GC(60) Concurrent Reset
[2020-02-06T10:38:38.766-0300][219.416s][debug][gc] GC(60) Concurrent active time: 0.013ms
[2020-02-06T10:38:38.766-0300][219.416s][info ][gc] GC(60) Concurrent Reset 0.035ms
[2020-02-06T10:38:40.772-0300][221.422s][info ][gc] GC(61) Pause Initial Mark 26M->26M(30M) 4.674ms
[2020-02-06T10:38:40.772-0300][221.422s][info ][gc] GC(61) Concurrent Mark
[2020-02-06T10:38:40.802-0300][221.452s][debug][gc] GC(61) Concurrent active time: 29.466ms
[2020-02-06T10:38:40.802-0300][221.452s][info ][gc] GC(61) Concurrent Mark 29.642ms
[2020-02-06T10:38:40.802-0300][221.452s][info ][gc] GC(61) Concurrent Preclean
[2020-02-06T10:38:40.807-0300][221.456s][debug][gc] GC(61) Concurrent active time: 4.393ms
[2020-02-06T10:38:40.807-0300][221.456s][info ][gc] GC(61) Concurrent Preclean 4.494ms
[2020-02-06T10:38:40.807-0300][221.457s][info ][gc] GC(61) Concurrent Abortable Preclean
[2020-02-06T10:38:40.807-0300][221.457s][debug][gc] GC(61) Concurrent active time: 0.015ms
[2020-02-06T10:38:40.807-0300][221.457s][info ][gc] GC(61) Concurrent Abortable Preclean 0.047ms
[2020-02-06T10:38:40.807-0300][221.457s][debug][gc] GC(61) YG occupancy: 6288 K (9792 K)
[2020-02-06T10:38:40.812-0300][221.461s][info ][gc] GC(61) Pause Remark 26M->26M(30M) 4.693ms
[2020-02-06T10:38:40.812-0300][221.462s][info ][gc] GC(61) Concurrent Sweep
[2020-02-06T10:38:40.824-0300][221.474s][debug][gc] GC(61) Concurrent active time: 12.640ms
[2020-02-06T10:38:40.824-0300][221.474s][info ][gc] GC(61) Concurrent Sweep 12.795ms
[2020-02-06T10:38:40.825-0300][221.474s][info ][gc] GC(61) Concurrent Reset
[2020-02-06T10:38:40.825-0300][221.475s][debug][gc] GC(61) Concurrent active time: 0.074ms
[2020-02-06T10:38:40.825-0300][221.475s][info ][gc] GC(61) Concurrent Reset 0.135ms
[2020-02-06T10:38:42.833-0300][223.483s][info ][gc] GC(62) Pause Initial Mark 26M->26M(30M) 6.482ms
[2020-02-06T10:38:42.833-0300][223.483s][info ][gc] GC(62) Concurrent Mark
[2020-02-06T10:38:42.862-0300][223.512s][debug][gc] GC(62) Concurrent active time: 28.098ms
[2020-02-06T10:38:42.862-0300][223.512s][info ][gc] GC(62) Concurrent Mark 28.402ms
[2020-02-06T10:38:42.862-0300][223.512s][info ][gc] GC(62) Concurrent Preclean
[2020-02-06T10:38:42.866-0300][223.515s][debug][gc] GC(62) Concurrent active time: 3.587ms
[2020-02-06T10:38:42.866-0300][223.515s][info ][gc] GC(62) Concurrent Preclean 3.655ms
[2020-02-06T10:38:42.866-0300][223.515s][info ][gc] GC(62) Concurrent Abortable Preclean
[2020-02-06T10:38:42.866-0300][223.515s][debug][gc] GC(62) Concurrent active time: 0.012ms
[2020-02-06T10:38:42.866-0300][223.515s][info ][gc] GC(62) Concurrent Abortable Preclean 0.044ms
[2020-02-06T10:38:42.866-0300][223.516s][debug][gc] GC(62) YG occupancy: 6785 K (9792 K)
[2020-02-06T10:38:42.873-0300][223.523s][info ][gc] GC(62) Pause Remark 26M->26M(30M) 6.979ms
[2020-02-06T10:38:42.873-0300][223.523s][info ][gc] GC(62) Concurrent Sweep
[2020-02-06T10:38:42.883-0300][223.533s][debug][gc] GC(62) Concurrent active time: 9.731ms
[2020-02-06T10:38:42.883-0300][223.533s][info ][gc] GC(62) Concurrent Sweep 9.830ms
[2020-02-06T10:38:42.883-0300][223.533s][info ][gc] GC(62) Concurrent Reset
[2020-02-06T10:38:42.883-0300][223.533s][debug][gc] GC(62) Concurrent active time: 0.010ms
[2020-02-06T10:38:42.883-0300][223.533s][info ][gc] GC(62) Concurrent Reset 0.028ms
[2020-02-06T10:38:44.893-0300][225.543s][info ][gc] GC(63) Pause Initial Mark 27M->27M(30M) 5.142ms
[2020-02-06T10:38:44.893-0300][225.543s][info ][gc] GC(63) Concurrent Mark
[2020-02-06T10:38:44.924-0300][225.574s][debug][gc] GC(63) Concurrent active time: 30.838ms
[2020-02-06T10:38:44.924-0300][225.574s][info ][gc] GC(63) Concurrent Mark 31.047ms
[2020-02-06T10:38:44.924-0300][225.574s][info ][gc] GC(63) Concurrent Preclean
[2020-02-06T10:38:44.929-0300][225.578s][debug][gc] GC(63) Concurrent active time: 4.479ms
[2020-02-06T10:38:44.929-0300][225.579s][info ][gc] GC(63) Concurrent Preclean 4.668ms
[2020-02-06T10:38:44.929-0300][225.579s][info ][gc] GC(63) Concurrent Abortable Preclean
[2020-02-06T10:38:44.929-0300][225.579s][debug][gc] GC(63) Concurrent active time: 0.022ms
[2020-02-06T10:38:44.929-0300][225.579s][info ][gc] GC(63) Concurrent Abortable Preclean 0.067ms
[2020-02-06T10:38:44.930-0300][225.580s][debug][gc] GC(63) YG occupancy: 7359 K (9792 K)
[2020-02-06T10:38:44.936-0300][225.586s][info ][gc] GC(63) Pause Remark 27M->27M(30M) 6.553ms
[2020-02-06T10:38:44.936-0300][225.586s][info ][gc] GC(63) Concurrent Sweep
[2020-02-06T10:38:44.943-0300][225.593s][debug][gc] GC(63) Concurrent active time: 6.860ms
[2020-02-06T10:38:44.943-0300][225.593s][info ][gc] GC(63) Concurrent Sweep 6.926ms
[2020-02-06T10:38:44.943-0300][225.593s][info ][gc] GC(63) Concurrent Reset
[2020-02-06T10:38:44.943-0300][225.593s][debug][gc] GC(63) Concurrent active time: 0.012ms
[2020-02-06T10:38:44.943-0300][225.593s][info ][gc] GC(63) Concurrent Reset 0.033ms
[2020-02-06T10:38:46.951-0300][227.601s][info ][gc] GC(64) Pause Initial Mark 27M->27M(30M) 4.626ms
[2020-02-06T10:38:46.951-0300][227.601s][info ][gc] GC(64) Concurrent Mark
[2020-02-06T10:38:46.988-0300][227.637s][debug][gc] GC(64) Concurrent active time: 36.242ms
[2020-02-06T10:38:46.988-0300][227.637s][info ][gc] GC(64) Concurrent Mark 36.371ms
[2020-02-06T10:38:46.988-0300][227.637s][info ][gc] GC(64) Concurrent Preclean
[2020-02-06T10:38:46.993-0300][227.643s][debug][gc] GC(64) Concurrent active time: 5.290ms
[2020-02-06T10:38:46.993-0300][227.643s][info ][gc] GC(64) Concurrent Preclean 5.605ms
[2020-02-06T10:38:46.993-0300][227.643s][info ][gc] GC(64) Concurrent Abortable Preclean
[2020-02-06T10:38:46.993-0300][227.643s][debug][gc] GC(64) Concurrent active time: 0.029ms
[2020-02-06T10:38:46.993-0300][227.643s][info ][gc] GC(64) Concurrent Abortable Preclean 0.076ms
[2020-02-06T10:38:46.996-0300][227.646s][debug][gc] GC(64) YG occupancy: 7820 K (9792 K)
[2020-02-06T10:38:47.005-0300][227.655s][info ][gc] GC(64) Pause Remark 27M->27M(30M) 9.106ms
[2020-02-06T10:38:47.005-0300][227.655s][info ][gc] GC(64) Concurrent Sweep
[2020-02-06T10:38:47.012-0300][227.662s][debug][gc] GC(64) Concurrent active time: 6.615ms
[2020-02-06T10:38:47.012-0300][227.662s][info ][gc] GC(64) Concurrent Sweep 6.705ms
[2020-02-06T10:38:47.012-0300][227.662s][info ][gc] GC(64) Concurrent Reset
[2020-02-06T10:38:47.012-0300][227.662s][debug][gc] GC(64) Concurrent active time: 0.014ms
[2020-02-06T10:38:47.013-0300][227.662s][info ][gc] GC(64) Concurrent Reset 0.075ms
[2020-02-06T10:38:49.022-0300][229.671s][info ][gc] GC(65) Pause Initial Mark 27M->27M(30M) 8.766ms
[2020-02-06T10:38:49.022-0300][229.672s][info ][gc] GC(65) Concurrent Mark
[2020-02-06T10:38:49.054-0300][229.703s][debug][gc] GC(65) Concurrent active time: 31.613ms
[2020-02-06T10:38:49.054-0300][229.703s][info ][gc] GC(65) Concurrent Mark 31.854ms
[2020-02-06T10:38:49.054-0300][229.703s][info ][gc] GC(65) Concurrent Preclean
[2020-02-06T10:38:49.058-0300][229.708s][debug][gc] GC(65) Concurrent active time: 4.141ms
[2020-02-06T10:38:49.058-0300][229.708s][info ][gc] GC(65) Concurrent Preclean 4.324ms
[2020-02-06T10:38:49.058-0300][229.708s][info ][gc] GC(65) Concurrent Abortable Preclean
[2020-02-06T10:38:49.058-0300][229.708s][debug][gc] GC(65) Concurrent active time: 0.018ms
[2020-02-06T10:38:49.058-0300][229.708s][info ][gc] GC(65) Concurrent Abortable Preclean 0.065ms
[2020-02-06T10:38:49.058-0300][229.708s][debug][gc] GC(65) YG occupancy: 8293 K (9792 K)
[2020-02-06T10:38:49.066-0300][229.716s][info ][gc] GC(65) Pause Remark 27M->27M(30M) 7.911ms
[2020-02-06T10:38:49.066-0300][229.716s][info ][gc] GC(65) Concurrent Sweep
[2020-02-06T10:38:49.073-0300][229.723s][debug][gc] GC(65) Concurrent active time: 6.501ms
[2020-02-06T10:38:49.073-0300][229.723s][info ][gc] GC(65) Concurrent Sweep 6.545ms
[2020-02-06T10:38:49.073-0300][229.723s][info ][gc] GC(65) Concurrent Reset
[2020-02-06T10:38:49.073-0300][229.723s][debug][gc] GC(65) Concurrent active time: 0.014ms
[2020-02-06T10:38:49.073-0300][229.723s][info ][gc] GC(65) Concurrent Reset 0.033ms
[2020-02-06T10:38:51.082-0300][231.732s][info ][gc] GC(66) Pause Initial Mark 28M->28M(30M) 5.186ms
[2020-02-06T10:38:51.082-0300][231.732s][info ][gc] GC(66) Concurrent Mark
[2020-02-06T10:38:51.105-0300][231.755s][debug][gc] GC(66) Concurrent active time: 22.723ms
[2020-02-06T10:38:51.105-0300][231.755s][info ][gc] GC(66) Concurrent Mark 22.833ms
[2020-02-06T10:38:51.105-0300][231.755s][info ][gc] GC(66) Concurrent Preclean
[2020-02-06T10:38:51.110-0300][231.759s][debug][gc] GC(66) Concurrent active time: 4.328ms
[2020-02-06T10:38:51.110-0300][231.759s][info ][gc] GC(66) Concurrent Preclean 4.533ms
[2020-02-06T10:38:51.110-0300][231.759s][info ][gc] GC(66) Concurrent Abortable Preclean
[2020-02-06T10:38:51.110-0300][231.759s][debug][gc] GC(66) Concurrent active time: 0.013ms
[2020-02-06T10:38:51.110-0300][231.759s][info ][gc] GC(66) Concurrent Abortable Preclean 0.057ms
[2020-02-06T10:38:51.110-0300][231.760s][debug][gc] GC(66) YG occupancy: 8778 K (9792 K)
[2020-02-06T10:38:51.119-0300][231.769s][info ][gc] GC(66) Pause Remark 28M->28M(30M) 8.708ms
[2020-02-06T10:38:51.119-0300][231.769s][info ][gc] GC(66) Concurrent Sweep
[2020-02-06T10:38:51.133-0300][231.782s][debug][gc] GC(66) Concurrent active time: 13.340ms
[2020-02-06T10:38:51.133-0300][231.783s][info ][gc] GC(66) Concurrent Sweep 13.618ms
[2020-02-06T10:38:51.133-0300][231.783s][info ][gc] GC(66) Concurrent Reset
[2020-02-06T10:38:51.133-0300][231.783s][debug][gc] GC(66) Concurrent active time: 0.013ms
[2020-02-06T10:38:51.133-0300][231.783s][info ][gc] GC(66) Concurrent Reset 0.034ms
[2020-02-06T10:38:53.142-0300][233.791s][info ][gc] GC(67) Pause Initial Mark 28M->28M(30M) 5.708ms
[2020-02-06T10:38:53.142-0300][233.791s][info ][gc] GC(67) Concurrent Mark
[2020-02-06T10:38:53.158-0300][233.807s][debug][gc] GC(67) Concurrent active time: 15.845ms
[2020-02-06T10:38:53.158-0300][233.807s][info ][gc] GC(67) Concurrent Mark 15.917ms
[2020-02-06T10:38:53.158-0300][233.807s][info ][gc] GC(67) Concurrent Preclean
[2020-02-06T10:38:53.161-0300][233.811s][debug][gc] GC(67) Concurrent active time: 3.696ms
[2020-02-06T10:38:53.162-0300][233.811s][info ][gc] GC(67) Concurrent Preclean 3.882ms
[2020-02-06T10:38:53.162-0300][233.811s][info ][gc] GC(67) Concurrent Abortable Preclean
[2020-02-06T10:38:53.162-0300][233.811s][debug][gc] GC(67) Concurrent active time: 0.017ms
[2020-02-06T10:38:53.162-0300][233.811s][info ][gc] GC(67) Concurrent Abortable Preclean 0.103ms
[2020-02-06T10:38:53.162-0300][233.812s][debug][gc] GC(67) YG occupancy: 9252 K (9792 K)
[2020-02-06T10:38:53.174-0300][233.823s][info ][gc] GC(67) Pause Remark 28M->28M(30M) 11.819ms
[2020-02-06T10:38:53.174-0300][233.824s][info ][gc] GC(67) Concurrent Sweep
[2020-02-06T10:38:53.184-0300][233.833s][debug][gc] GC(67) Concurrent active time: 9.133ms
[2020-02-06T10:38:53.184-0300][233.833s][info ][gc] GC(67) Concurrent Sweep 9.441ms
[2020-02-06T10:38:53.184-0300][233.833s][info ][gc] GC(67) Concurrent Reset
[2020-02-06T10:38:53.184-0300][233.834s][debug][gc] GC(67) Concurrent active time: 0.117ms
[2020-02-06T10:38:53.184-0300][233.834s][info ][gc] GC(67) Concurrent Reset 0.197ms
[2020-02-06T10:38:55.194-0300][235.843s][info ][gc] GC(68) Pause Initial Mark 28M->28M(30M) 5.869ms
[2020-02-06T10:38:55.195-0300][235.845s][info ][gc] GC(68) Concurrent Mark
[2020-02-06T10:38:55.212-0300][235.861s][debug][gc] GC(68) Concurrent active time: 16.246ms
[2020-02-06T10:38:55.212-0300][235.861s][info ][gc] GC(68) Concurrent Mark 16.335ms
[2020-02-06T10:38:55.212-0300][235.861s][info ][gc] GC(68) Concurrent Preclean
[2020-02-06T10:38:55.216-0300][235.865s][debug][gc] GC(68) Concurrent active time: 3.704ms
[2020-02-06T10:38:55.216-0300][235.865s][info ][gc] GC(68) Concurrent Preclean 3.729ms
[2020-02-06T10:38:55.216-0300][235.865s][info ][gc] GC(68) Concurrent Abortable Preclean
[2020-02-06T10:38:55.216-0300][235.865s][debug][gc] GC(68) Concurrent active time: 0.003ms
[2020-02-06T10:38:55.216-0300][235.865s][info ][gc] GC(68) Concurrent Abortable Preclean 0.020ms
[2020-02-06T10:38:55.216-0300][235.865s][debug][gc] GC(68) YG occupancy: 9749 K (9792 K)
[2020-02-06T10:38:55.221-0300][235.870s][info ][gc] GC(68) Pause Remark 28M->28M(30M) 5.054ms
[2020-02-06T10:38:55.221-0300][235.870s][info ][gc] GC(68) Concurrent Sweep
[2020-02-06T10:38:55.229-0300][235.878s][debug][gc] GC(68) Concurrent active time: 7.917ms
[2020-02-06T10:38:55.229-0300][235.878s][info ][gc] GC(68) Concurrent Sweep 8.118ms
[2020-02-06T10:38:55.229-0300][235.879s][info ][gc] GC(68) Concurrent Reset
[2020-02-06T10:38:55.229-0300][235.879s][debug][gc] GC(68) Concurrent active time: 0.068ms
[2020-02-06T10:38:55.229-0300][235.879s][info ][gc] GC(68) Concurrent Reset 0.165ms
[2020-02-06T10:38:55.231-0300][235.880s][info ][gc] GC(69) Pause Young (Allocation Failure) 28M->28M(30M) 0.040ms
[2020-02-06T10:38:55.298-0300][235.947s][info ][gc] GC(70) Pause Full (Allocation Failure) 28M->25M(30M) 66.645ms
[2020-02-06T10:38:55.301-0300][235.950s][info ][gc] GC(71) Pause Initial Mark 25M->25M(30M) 2.612ms
[2020-02-06T10:38:55.301-0300][235.950s][info ][gc] GC(71) Concurrent Mark
[2020-02-06T10:38:55.317-0300][235.967s][debug][gc] GC(71) Concurrent active time: 16.517ms
[2020-02-06T10:38:55.317-0300][235.967s][info ][gc] GC(71) Concurrent Mark 16.604ms
[2020-02-06T10:38:55.317-0300][235.967s][info ][gc] GC(71) Concurrent Preclean
[2020-02-06T10:38:55.345-0300][235.994s][debug][gc] GC(71) Concurrent active time: 26.806ms
[2020-02-06T10:38:55.345-0300][235.994s][info ][gc] GC(71) Concurrent Preclean 27.774ms
[2020-02-06T10:38:55.345-0300][235.994s][info ][gc] GC(71) Concurrent Abortable Preclean
[2020-02-06T10:38:55.345-0300][235.995s][debug][gc] GC(71) Concurrent active time: 0.002ms
[2020-02-06T10:38:55.345-0300][235.995s][info ][gc] GC(71) Concurrent Abortable Preclean 0.044ms
[2020-02-06T10:38:55.346-0300][235.996s][debug][gc] GC(71) YG occupancy: 3952 K (9792 K)
[2020-02-06T10:38:55.354-0300][236.004s][info ][gc] GC(71) Pause Remark 25M->25M(30M) 8.226ms
[2020-02-06T10:38:55.355-0300][236.004s][info ][gc] GC(71) Concurrent Sweep
[2020-02-06T10:38:55.364-0300][236.014s][debug][gc] GC(71) Concurrent active time: 9.328ms
[2020-02-06T10:38:55.364-0300][236.014s][info ][gc] GC(71) Concurrent Sweep 9.472ms
[2020-02-06T10:38:55.364-0300][236.014s][info ][gc] GC(71) Concurrent Reset
[2020-02-06T10:38:55.364-0300][236.014s][debug][gc] GC(71) Concurrent active time: 0.014ms
[2020-02-06T10:38:55.364-0300][236.014s][info ][gc] GC(71) Concurrent Reset 0.044ms
[2020-02-06T10:38:57.369-0300][238.019s][info ][gc] GC(72) Pause Initial Mark 25M->25M(30M) 3.151ms
[2020-02-06T10:38:57.369-0300][238.019s][info ][gc] GC(72) Concurrent Mark
[2020-02-06T10:38:57.386-0300][238.036s][debug][gc] GC(72) Concurrent active time: 16.894ms
[2020-02-06T10:38:57.386-0300][238.036s][info ][gc] GC(72) Concurrent Mark 17.002ms
[2020-02-06T10:38:57.386-0300][238.036s][info ][gc] GC(72) Concurrent Preclean
[2020-02-06T10:38:57.391-0300][238.040s][debug][gc] GC(72) Concurrent active time: 4.565ms
[2020-02-06T10:38:57.391-0300][238.041s][info ][gc] GC(72) Concurrent Preclean 4.792ms
[2020-02-06T10:38:57.391-0300][238.041s][info ][gc] GC(72) Concurrent Abortable Preclean
[2020-02-06T10:38:57.391-0300][238.041s][debug][gc] GC(72) Concurrent active time: 0.035ms
[2020-02-06T10:38:57.391-0300][238.041s][info ][gc] GC(72) Concurrent Abortable Preclean 0.140ms
[2020-02-06T10:38:57.392-0300][238.041s][debug][gc] GC(72) YG occupancy: 4511 K (9792 K)
[2020-02-06T10:38:57.403-0300][238.053s][info ][gc] GC(72) Pause Remark 25M->25M(30M) 11.566ms
[2020-02-06T10:38:57.404-0300][238.053s][info ][gc] GC(72) Concurrent Sweep
[2020-02-06T10:38:57.417-0300][238.066s][debug][gc] GC(72) Concurrent active time: 13.170ms
[2020-02-06T10:38:57.417-0300][238.066s][info ][gc] GC(72) Concurrent Sweep 13.428ms
[2020-02-06T10:38:57.417-0300][238.067s][info ][gc] GC(72) Concurrent Reset
[2020-02-06T10:38:57.417-0300][238.067s][debug][gc] GC(72) Concurrent active time: 0.079ms
[2020-02-06T10:38:57.417-0300][238.067s][info ][gc] GC(72) Concurrent Reset 0.126ms
[2020-02-06T10:38:59.427-0300][240.076s][info ][gc] GC(73) Pause Initial Mark 26M->26M(30M) 4.952ms
[2020-02-06T10:38:59.427-0300][240.076s][info ][gc] GC(73) Concurrent Mark
[2020-02-06T10:38:59.445-0300][240.094s][debug][gc] GC(73) Concurrent active time: 17.986ms
[2020-02-06T10:38:59.445-0300][240.094s][info ][gc] GC(73) Concurrent Mark 18.059ms
[2020-02-06T10:38:59.445-0300][240.094s][info ][gc] GC(73) Concurrent Preclean
[2020-02-06T10:38:59.449-0300][240.098s][debug][gc] GC(73) Concurrent active time: 4.082ms
[2020-02-06T10:38:59.449-0300][240.098s][info ][gc] GC(73) Concurrent Preclean 4.272ms
[2020-02-06T10:38:59.449-0300][240.098s][info ][gc] GC(73) Concurrent Abortable Preclean
[2020-02-06T10:38:59.449-0300][240.099s][debug][gc] GC(73) Concurrent active time: 0.038ms
[2020-02-06T10:38:59.449-0300][240.099s][info ][gc] GC(73) Concurrent Abortable Preclean 0.140ms
[2020-02-06T10:38:59.450-0300][240.100s][debug][gc] GC(73) YG occupancy: 5079 K (9792 K)
[2020-02-06T10:38:59.464-0300][240.114s][info ][gc] GC(73) Pause Remark 26M->26M(30M) 14.078ms
[2020-02-06T10:38:59.465-0300][240.115s][info ][gc] GC(73) Concurrent Sweep
[2020-02-06T10:38:59.483-0300][240.133s][debug][gc] GC(73) Concurrent active time: 17.830ms
[2020-02-06T10:38:59.483-0300][240.133s][info ][gc] GC(73) Concurrent Sweep 18.032ms
[2020-02-06T10:38:59.483-0300][240.133s][info ][gc] GC(73) Concurrent Reset
[2020-02-06T10:38:59.484-0300][240.133s][debug][gc] GC(73) Concurrent active time: 0.080ms
[2020-02-06T10:38:59.484-0300][240.133s][info ][gc] GC(73) Concurrent Reset 0.206ms
[2020-02-06T10:39:01.493-0300][242.143s][info ][gc] GC(74) Pause Initial Mark 26M->26M(30M) 5.029ms
[2020-02-06T10:39:01.495-0300][242.144s][info ][gc] GC(74) Concurrent Mark
[2020-02-06T10:39:01.515-0300][242.164s][debug][gc] GC(74) Concurrent active time: 19.629ms
[2020-02-06T10:39:01.515-0300][242.164s][info ][gc] GC(74) Concurrent Mark 19.742ms
[2020-02-06T10:39:01.515-0300][242.164s][info ][gc] GC(74) Concurrent Preclean
[2020-02-06T10:39:01.521-0300][242.170s][debug][gc] GC(74) Concurrent active time: 6.188ms
[2020-02-06T10:39:01.521-0300][242.171s][info ][gc] GC(74) Concurrent Preclean 6.603ms
[2020-02-06T10:39:01.521-0300][242.171s][info ][gc] GC(74) Concurrent Abortable Preclean
[2020-02-06T10:39:01.521-0300][242.171s][debug][gc] GC(74) Concurrent active time: 0.031ms
[2020-02-06T10:39:01.522-0300][242.171s][info ][gc] GC(74) Concurrent Abortable Preclean 0.152ms
[2020-02-06T10:39:01.524-0300][242.173s][debug][gc] GC(74) YG occupancy: 5545 K (9792 K)
[2020-02-06T10:39:01.539-0300][242.188s][info ][gc] GC(74) Pause Remark 26M->26M(30M) 14.360ms
[2020-02-06T10:39:01.539-0300][242.188s][info ][gc] GC(74) Concurrent Sweep
[2020-02-06T10:39:01.553-0300][242.203s][debug][gc] GC(74) Concurrent active time: 14.480ms
[2020-02-06T10:39:01.553-0300][242.203s][info ][gc] GC(74) Concurrent Sweep 14.626ms
[2020-02-06T10:39:01.553-0300][242.203s][info ][gc] GC(74) Concurrent Reset
[2020-02-06T10:39:01.553-0300][242.203s][debug][gc] GC(74) Concurrent active time: 0.013ms
[2020-02-06T10:39:01.553-0300][242.203s][info ][gc] GC(74) Concurrent Reset 0.033ms
[2020-02-06T10:39:03.562-0300][244.211s][info ][gc] GC(75) Pause Initial Mark 26M->26M(30M) 4.782ms
[2020-02-06T10:39:03.562-0300][244.212s][info ][gc] GC(75) Concurrent Mark
[2020-02-06T10:39:03.580-0300][244.229s][debug][gc] GC(75) Concurrent active time: 17.705ms
[2020-02-06T10:39:03.580-0300][244.229s][info ][gc] GC(75) Concurrent Mark 17.766ms
[2020-02-06T10:39:03.580-0300][244.229s][info ][gc] GC(75) Concurrent Preclean
[2020-02-06T10:39:03.585-0300][244.234s][debug][gc] GC(75) Concurrent active time: 4.471ms
[2020-02-06T10:39:03.585-0300][244.234s][info ][gc] GC(75) Concurrent Preclean 4.658ms
[2020-02-06T10:39:03.585-0300][244.234s][info ][gc] GC(75) Concurrent Abortable Preclean
[2020-02-06T10:39:03.585-0300][244.234s][debug][gc] GC(75) Concurrent active time: 0.018ms
[2020-02-06T10:39:03.585-0300][244.234s][info ][gc] GC(75) Concurrent Abortable Preclean 0.066ms
[2020-02-06T10:39:03.585-0300][244.234s][debug][gc] GC(75) YG occupancy: 5997 K (9792 K)
[2020-02-06T10:39:03.592-0300][244.242s][info ][gc] GC(75) Pause Remark 26M->26M(30M) 7.345ms
[2020-02-06T10:39:03.593-0300][244.242s][info ][gc] GC(75) Concurrent Sweep
[2020-02-06T10:39:03.605-0300][244.254s][debug][gc] GC(75) Concurrent active time: 12.055ms
[2020-02-06T10:39:03.605-0300][244.254s][info ][gc] GC(75) Concurrent Sweep 12.165ms
[2020-02-06T10:39:03.605-0300][244.254s][info ][gc] GC(75) Concurrent Reset
[2020-02-06T10:39:03.605-0300][244.254s][debug][gc] GC(75) Concurrent active time: 0.031ms
[2020-02-06T10:39:03.605-0300][244.254s][info ][gc] GC(75) Concurrent Reset 0.072ms
[2020-02-06T10:39:05.612-0300][246.261s][info ][gc] GC(76) Pause Initial Mark 27M->27M(30M) 2.841ms
[2020-02-06T10:39:05.612-0300][246.261s][info ][gc] GC(76) Concurrent Mark
[2020-02-06T10:39:05.627-0300][246.276s][debug][gc] GC(76) Concurrent active time: 15.393ms
[2020-02-06T10:39:05.627-0300][246.276s][info ][gc] GC(76) Concurrent Mark 15.458ms
[2020-02-06T10:39:05.627-0300][246.276s][info ][gc] GC(76) Concurrent Preclean
[2020-02-06T10:39:05.631-0300][246.280s][debug][gc] GC(76) Concurrent active time: 3.484ms
[2020-02-06T10:39:05.631-0300][246.280s][info ][gc] GC(76) Concurrent Preclean 3.521ms
[2020-02-06T10:39:05.631-0300][246.280s][info ][gc] GC(76) Concurrent Abortable Preclean
[2020-02-06T10:39:05.631-0300][246.280s][debug][gc] GC(76) Concurrent active time: 0.003ms
[2020-02-06T10:39:05.631-0300][246.280s][info ][gc] GC(76) Concurrent Abortable Preclean 0.021ms
[2020-02-06T10:39:05.631-0300][246.280s][debug][gc] GC(76) YG occupancy: 6537 K (9792 K)
[2020-02-06T10:39:05.635-0300][246.284s][info ][gc] GC(76) Pause Remark 27M->27M(30M) 4.284ms
[2020-02-06T10:39:05.635-0300][246.284s][info ][gc] GC(76) Concurrent Sweep
[2020-02-06T10:39:05.643-0300][246.293s][debug][gc] GC(76) Concurrent active time: 8.340ms
[2020-02-06T10:39:05.644-0300][246.293s][info ][gc] GC(76) Concurrent Sweep 8.475ms
[2020-02-06T10:39:05.644-0300][246.293s][info ][gc] GC(76) Concurrent Reset
[2020-02-06T10:39:05.644-0300][246.293s][debug][gc] GC(76) Concurrent active time: 0.071ms
[2020-02-06T10:39:05.644-0300][246.293s][info ][gc] GC(76) Concurrent Reset 0.202ms
[2020-02-06T10:39:07.653-0300][248.302s][info ][gc] GC(77) Pause Initial Mark 27M->27M(30M) 5.008ms
[2020-02-06T10:39:07.653-0300][248.302s][info ][gc] GC(77) Concurrent Mark
[2020-02-06T10:39:07.670-0300][248.319s][debug][gc] GC(77) Concurrent active time: 17.099ms
[2020-02-06T10:39:07.670-0300][248.319s][info ][gc] GC(77) Concurrent Mark 17.181ms
[2020-02-06T10:39:07.670-0300][248.319s][info ][gc] GC(77) Concurrent Preclean
[2020-02-06T10:39:07.674-0300][248.323s][debug][gc] GC(77) Concurrent active time: 3.486ms
[2020-02-06T10:39:07.674-0300][248.323s][info ][gc] GC(77) Concurrent Preclean 3.513ms
[2020-02-06T10:39:07.674-0300][248.323s][info ][gc] GC(77) Concurrent Abortable Preclean
[2020-02-06T10:39:07.674-0300][248.323s][debug][gc] GC(77) Concurrent active time: 0.003ms
[2020-02-06T10:39:07.674-0300][248.323s][info ][gc] GC(77) Concurrent Abortable Preclean 0.020ms
[2020-02-06T10:39:07.674-0300][248.323s][debug][gc] GC(77) YG occupancy: 7003 K (9792 K)
[2020-02-06T10:39:07.678-0300][248.327s][info ][gc] GC(77) Pause Remark 27M->27M(30M) 4.361ms
[2020-02-06T10:39:07.678-0300][248.327s][info ][gc] GC(77) Concurrent Sweep
[2020-02-06T10:39:07.685-0300][248.334s][debug][gc] GC(77) Concurrent active time: 6.806ms
[2020-02-06T10:39:07.685-0300][248.334s][info ][gc] GC(77) Concurrent Sweep 6.855ms
[2020-02-06T10:39:07.685-0300][248.334s][info ][gc] GC(77) Concurrent Reset
[2020-02-06T10:39:07.685-0300][248.334s][debug][gc] GC(77) Concurrent active time: 0.010ms
[2020-02-06T10:39:07.685-0300][248.334s][info ][gc] GC(77) Concurrent Reset 0.029ms
[2020-02-06T10:39:09.696-0300][250.346s][info ][gc] GC(78) Pause Initial Mark 28M->28M(30M) 7.947ms
[2020-02-06T10:39:09.697-0300][250.346s][info ][gc] GC(78) Concurrent Mark
[2020-02-06T10:39:09.720-0300][250.370s][debug][gc] GC(78) Concurrent active time: 23.729ms
[2020-02-06T10:39:09.721-0300][250.370s][info ][gc] GC(78) Concurrent Mark 23.967ms
[2020-02-06T10:39:09.721-0300][250.370s][info ][gc] GC(78) Concurrent Preclean
[2020-02-06T10:39:09.725-0300][250.374s][debug][gc] GC(78) Concurrent active time: 3.893ms
[2020-02-06T10:39:09.725-0300][250.374s][info ][gc] GC(78) Concurrent Preclean 3.982ms
[2020-02-06T10:39:09.725-0300][250.374s][info ][gc] GC(78) Concurrent Abortable Preclean
[2020-02-06T10:39:09.725-0300][250.374s][debug][gc] GC(78) Concurrent active time: 0.009ms
[2020-02-06T10:39:09.725-0300][250.374s][info ][gc] GC(78) Concurrent Abortable Preclean 0.043ms
[2020-02-06T10:39:09.725-0300][250.374s][debug][gc] GC(78) YG occupancy: 7554 K (9792 K)
[2020-02-06T10:39:09.730-0300][250.379s][info ][gc] GC(78) Pause Remark 28M->28M(30M) 4.601ms
[2020-02-06T10:39:09.730-0300][250.379s][info ][gc] GC(78) Concurrent Sweep
[2020-02-06T10:39:09.736-0300][250.386s][debug][gc] GC(78) Concurrent active time: 6.877ms
[2020-02-06T10:39:09.736-0300][250.386s][info ][gc] GC(78) Concurrent Sweep 6.918ms
[2020-02-06T10:39:09.737-0300][250.386s][info ][gc] GC(78) Concurrent Reset
[2020-02-06T10:39:09.737-0300][250.386s][debug][gc] GC(78) Concurrent active time: 0.012ms
[2020-02-06T10:39:09.737-0300][250.386s][info ][gc] GC(78) Concurrent Reset 0.038ms
[2020-02-06T10:39:11.744-0300][252.393s][info ][gc] GC(79) Pause Initial Mark 28M->28M(30M) 4.005ms
[2020-02-06T10:39:11.745-0300][252.394s][info ][gc] GC(79) Concurrent Mark
[2020-02-06T10:39:11.765-0300][252.414s][debug][gc] GC(79) Concurrent active time: 20.106ms
[2020-02-06T10:39:11.765-0300][252.414s][info ][gc] GC(79) Concurrent Mark 20.415ms
[2020-02-06T10:39:11.765-0300][252.414s][info ][gc] GC(79) Concurrent Preclean
[2020-02-06T10:39:11.769-0300][252.418s][debug][gc] GC(79) Concurrent active time: 3.685ms
[2020-02-06T10:39:11.769-0300][252.418s][info ][gc] GC(79) Concurrent Preclean 3.735ms
[2020-02-06T10:39:11.769-0300][252.418s][info ][gc] GC(79) Concurrent Abortable Preclean
[2020-02-06T10:39:11.769-0300][252.418s][debug][gc] GC(79) Concurrent active time: 0.004ms
[2020-02-06T10:39:11.769-0300][252.418s][info ][gc] GC(79) Concurrent Abortable Preclean 0.022ms
[2020-02-06T10:39:11.769-0300][252.418s][debug][gc] GC(79) YG occupancy: 8018 K (9792 K)
[2020-02-06T10:39:11.775-0300][252.424s][info ][gc] GC(79) Pause Remark 28M->28M(30M) 5.796ms
[2020-02-06T10:39:11.775-0300][252.424s][info ][gc] GC(79) Concurrent Sweep
[2020-02-06T10:39:11.783-0300][252.432s][debug][gc] GC(79) Concurrent active time: 7.714ms
[2020-02-06T10:39:11.783-0300][252.432s][info ][gc] GC(79) Concurrent Sweep 7.898ms
[2020-02-06T10:39:11.783-0300][252.432s][info ][gc] GC(79) Concurrent Reset
[2020-02-06T10:39:11.783-0300][252.432s][debug][gc] GC(79) Concurrent active time: 0.120ms
[2020-02-06T10:39:11.783-0300][252.432s][info ][gc] GC(79) Concurrent Reset 0.187ms
[2020-02-06T10:39:13.793-0300][254.442s][info ][gc] GC(80) Pause Initial Mark 28M->28M(30M) 5.876ms
[2020-02-06T10:39:13.793-0300][254.442s][info ][gc] GC(80) Concurrent Mark
[2020-02-06T10:39:13.819-0300][254.468s][debug][gc] GC(80) Concurrent active time: 25.831ms
[2020-02-06T10:39:13.819-0300][254.468s][info ][gc] GC(80) Concurrent Mark 26.051ms
[2020-02-06T10:39:13.819-0300][254.468s][info ][gc] GC(80) Concurrent Preclean
[2020-02-06T10:39:13.823-0300][254.472s][debug][gc] GC(80) Concurrent active time: 3.805ms
[2020-02-06T10:39:13.823-0300][254.472s][info ][gc] GC(80) Concurrent Preclean 3.998ms
[2020-02-06T10:39:13.823-0300][254.472s][info ][gc] GC(80) Concurrent Abortable Preclean
[2020-02-06T10:39:13.823-0300][254.472s][debug][gc] GC(80) Concurrent active time: 0.013ms
[2020-02-06T10:39:13.823-0300][254.472s][info ][gc] GC(80) Concurrent Abortable Preclean 0.067ms
[2020-02-06T10:39:13.824-0300][254.473s][debug][gc] GC(80) YG occupancy: 8561 K (9792 K)
[2020-02-06T10:39:13.830-0300][254.479s][info ][gc] GC(80) Pause Remark 28M->28M(30M) 6.638ms
[2020-02-06T10:39:13.830-0300][254.479s][info ][gc] GC(80) Concurrent Sweep
[2020-02-06T10:39:13.842-0300][254.491s][debug][gc] GC(80) Concurrent active time: 11.185ms
[2020-02-06T10:39:13.842-0300][254.491s][info ][gc] GC(80) Concurrent Sweep 11.389ms
[2020-02-06T10:39:13.842-0300][254.491s][info ][gc] GC(80) Concurrent Reset
[2020-02-06T10:39:13.842-0300][254.491s][debug][gc] GC(80) Concurrent active time: 0.060ms
[2020-02-06T10:39:13.842-0300][254.491s][info ][gc] GC(80) Concurrent Reset 0.119ms
[2020-02-06T10:39:15.852-0300][256.501s][info ][gc] GC(81) Pause Initial Mark 29M->29M(30M) 5.569ms
[2020-02-06T10:39:15.852-0300][256.501s][info ][gc] GC(81) Concurrent Mark
[2020-02-06T10:39:15.877-0300][256.526s][debug][gc] GC(81) Concurrent active time: 25.069ms
[2020-02-06T10:39:15.877-0300][256.526s][info ][gc] GC(81) Concurrent Mark 25.248ms
[2020-02-06T10:39:15.877-0300][256.526s][info ][gc] GC(81) Concurrent Preclean
[2020-02-06T10:39:15.882-0300][256.531s][debug][gc] GC(81) Concurrent active time: 4.503ms
[2020-02-06T10:39:15.882-0300][256.531s][info ][gc] GC(81) Concurrent Preclean 4.798ms
[2020-02-06T10:39:15.882-0300][256.531s][info ][gc] GC(81) Concurrent Abortable Preclean
[2020-02-06T10:39:15.882-0300][256.531s][debug][gc] GC(81) Concurrent active time: 0.029ms
[2020-02-06T10:39:15.883-0300][256.531s][info ][gc] GC(81) Concurrent Abortable Preclean 0.153ms
[2020-02-06T10:39:15.883-0300][256.532s][debug][gc] GC(81) YG occupancy: 8996 K (9792 K)
[2020-02-06T10:39:15.892-0300][256.540s][info ][gc] GC(81) Pause Remark 29M->29M(30M) 8.305ms
[2020-02-06T10:39:15.892-0300][256.541s][info ][gc] GC(81) Concurrent Sweep
[2020-02-06T10:39:15.902-0300][256.551s][debug][gc] GC(81) Concurrent active time: 9.867ms
[2020-02-06T10:39:15.902-0300][256.551s][info ][gc] GC(81) Concurrent Sweep 9.972ms
[2020-02-06T10:39:15.902-0300][256.551s][info ][gc] GC(81) Concurrent Reset
[2020-02-06T10:39:15.902-0300][256.551s][debug][gc] GC(81) Concurrent active time: 0.025ms
[2020-02-06T10:39:15.902-0300][256.551s][info ][gc] GC(81) Concurrent Reset 0.061ms
[2020-02-06T10:39:17.910-0300][258.559s][info ][gc] GC(82) Pause Initial Mark 29M->29M(30M) 5.785ms
[2020-02-06T10:39:17.910-0300][258.559s][info ][gc] GC(82) Concurrent Mark
[2020-02-06T10:39:17.941-0300][258.590s][debug][gc] GC(82) Concurrent active time: 31.073ms
[2020-02-06T10:39:17.941-0300][258.590s][info ][gc] GC(82) Concurrent Mark 31.328ms
[2020-02-06T10:39:17.941-0300][258.590s][info ][gc] GC(82) Concurrent Preclean
[2020-02-06T10:39:17.947-0300][258.596s][debug][gc] GC(82) Concurrent active time: 5.457ms
[2020-02-06T10:39:17.947-0300][258.596s][info ][gc] GC(82) Concurrent Preclean 5.619ms
[2020-02-06T10:39:17.947-0300][258.596s][info ][gc] GC(82) Concurrent Abortable Preclean
[2020-02-06T10:39:17.947-0300][258.596s][debug][gc] GC(82) Concurrent active time: 0.015ms
[2020-02-06T10:39:17.947-0300][258.596s][info ][gc] GC(82) Concurrent Abortable Preclean 0.066ms
[2020-02-06T10:39:17.948-0300][258.597s][debug][gc] GC(82) YG occupancy: 9492 K (9792 K)
[2020-02-06T10:39:17.959-0300][258.608s][info ][gc] GC(82) Pause Remark 29M->29M(30M) 10.782ms
[2020-02-06T10:39:17.959-0300][258.608s][info ][gc] GC(82) Concurrent Sweep
[2020-02-06T10:39:17.972-0300][258.621s][debug][gc] GC(82) Concurrent active time: 13.104ms
[2020-02-06T10:39:17.972-0300][258.621s][info ][gc] GC(82) Concurrent Sweep 13.296ms
[2020-02-06T10:39:17.972-0300][258.621s][info ][gc] GC(82) Concurrent Reset
[2020-02-06T10:39:17.972-0300][258.621s][debug][gc] GC(82) Concurrent active time: 0.014ms
[2020-02-06T10:39:17.972-0300][258.621s][info ][gc] GC(82) Concurrent Reset 0.041ms
[2020-02-06T10:39:19.400-0300][260.049s][info ][gc] GC(83) Pause Young (Allocation Failure) 29M->29M(30M) 0.080ms
[2020-02-06T10:39:19.462-0300][260.111s][info ][gc] GC(84) Pause Full (Allocation Failure) 29M->26M(30M) 61.796ms
[2020-02-06T10:39:19.466-0300][260.115s][info ][gc] GC(85) Pause Initial Mark 26M->26M(30M) 4.028ms
[2020-02-06T10:39:19.467-0300][260.115s][info ][gc] GC(85) Concurrent Mark
[2020-02-06T10:39:19.489-0300][260.138s][debug][gc] GC(85) Concurrent active time: 21.944ms
[2020-02-06T10:39:19.489-0300][260.138s][info ][gc] GC(85) Concurrent Mark 22.549ms
[2020-02-06T10:39:19.489-0300][260.138s][info ][gc] GC(85) Concurrent Preclean
[2020-02-06T10:39:19.512-0300][260.161s][debug][gc] GC(85) Concurrent active time: 23.180ms
[2020-02-06T10:39:19.512-0300][260.161s][info ][gc] GC(85) Concurrent Preclean 23.259ms
[2020-02-06T10:39:19.512-0300][260.161s][info ][gc] GC(85) Concurrent Abortable Preclean
[2020-02-06T10:39:19.512-0300][260.161s][debug][gc] GC(85) Concurrent active time: 0.001ms
[2020-02-06T10:39:19.512-0300][260.161s][info ][gc] GC(85) Concurrent Abortable Preclean 0.020ms
[2020-02-06T10:39:19.513-0300][260.161s][debug][gc] GC(85) YG occupancy: 5685 K (9792 K)
[2020-02-06T10:39:19.522-0300][260.171s][info ][gc] GC(85) Pause Remark 26M->26M(30M) 9.322ms
[2020-02-06T10:39:19.522-0300][260.171s][info ][gc] GC(85) Concurrent Sweep
[2020-02-06T10:39:19.530-0300][260.179s][debug][gc] GC(85) Concurrent active time: 7.691ms
[2020-02-06T10:39:19.530-0300][260.179s][info ][gc] GC(85) Concurrent Sweep 7.786ms
[2020-02-06T10:39:19.530-0300][260.179s][info ][gc] GC(85) Concurrent Reset
[2020-02-06T10:39:19.530-0300][260.179s][debug][gc] GC(85) Concurrent active time: 0.021ms
[2020-02-06T10:39:19.530-0300][260.179s][info ][gc] GC(85) Concurrent Reset 0.056ms
[2020-02-06T10:39:21.538-0300][262.187s][info ][gc] GC(86) Pause Initial Mark 27M->27M(30M) 4.186ms
[2020-02-06T10:39:21.538-0300][262.187s][info ][gc] GC(86) Concurrent Mark
[2020-02-06T10:39:21.574-0300][262.223s][debug][gc] GC(86) Concurrent active time: 35.696ms
[2020-02-06T10:39:21.574-0300][262.223s][info ][gc] GC(86) Concurrent Mark 35.961ms
[2020-02-06T10:39:21.574-0300][262.223s][info ][gc] GC(86) Concurrent Preclean
[2020-02-06T10:39:21.578-0300][262.227s][debug][gc] GC(86) Concurrent active time: 4.067ms
[2020-02-06T10:39:21.579-0300][262.227s][info ][gc] GC(86) Concurrent Preclean 4.239ms
[2020-02-06T10:39:21.579-0300][262.227s][info ][gc] GC(86) Concurrent Abortable Preclean
[2020-02-06T10:39:21.579-0300][262.227s][debug][gc] GC(86) Concurrent active time: 0.014ms
[2020-02-06T10:39:21.579-0300][262.227s][info ][gc] GC(86) Concurrent Abortable Preclean 0.054ms
[2020-02-06T10:39:21.579-0300][262.228s][debug][gc] GC(86) YG occupancy: 6205 K (9792 K)
[2020-02-06T10:39:21.588-0300][262.236s][info ][gc] GC(86) Pause Remark 27M->27M(30M) 8.514ms
[2020-02-06T10:39:21.588-0300][262.237s][info ][gc] GC(86) Concurrent Sweep
[2020-02-06T10:39:21.596-0300][262.245s][debug][gc] GC(86) Concurrent active time: 8.174ms
[2020-02-06T10:39:21.596-0300][262.245s][info ][gc] GC(86) Concurrent Sweep 8.287ms
[2020-02-06T10:39:21.596-0300][262.245s][info ][gc] GC(86) Concurrent Reset
[2020-02-06T10:39:21.596-0300][262.245s][debug][gc] GC(86) Concurrent active time: 0.012ms
[2020-02-06T10:39:21.596-0300][262.245s][info ][gc] GC(86) Concurrent Reset 0.031ms
[2020-02-06T10:39:23.605-0300][264.253s][info ][gc] GC(87) Pause Initial Mark 27M->27M(30M) 6.868ms
[2020-02-06T10:39:23.605-0300][264.253s][info ][gc] GC(87) Concurrent Mark
[2020-02-06T10:39:23.635-0300][264.283s][debug][gc] GC(87) Concurrent active time: 29.717ms
[2020-02-06T10:39:23.635-0300][264.283s][info ][gc] GC(87) Concurrent Mark 30.058ms
[2020-02-06T10:39:23.635-0300][264.284s][info ][gc] GC(87) Concurrent Preclean
[2020-02-06T10:39:23.639-0300][264.288s][debug][gc] GC(87) Concurrent active time: 4.263ms
[2020-02-06T10:39:23.639-0300][264.288s][info ][gc] GC(87) Concurrent Preclean 4.445ms
[2020-02-06T10:39:23.639-0300][264.288s][info ][gc] GC(87) Concurrent Abortable Preclean
[2020-02-06T10:39:23.639-0300][264.288s][debug][gc] GC(87) Concurrent active time: 0.014ms
[2020-02-06T10:39:23.640-0300][264.288s][info ][gc] GC(87) Concurrent Abortable Preclean 0.062ms
[2020-02-06T10:39:23.640-0300][264.288s][debug][gc] GC(87) YG occupancy: 6637 K (9792 K)
[2020-02-06T10:39:23.646-0300][264.295s][info ][gc] GC(87) Pause Remark 27M->27M(30M) 6.253ms
[2020-02-06T10:39:23.646-0300][264.295s][info ][gc] GC(87) Concurrent Sweep
[2020-02-06T10:39:23.653-0300][264.302s][debug][gc] GC(87) Concurrent active time: 6.925ms
[2020-02-06T10:39:23.653-0300][264.302s][info ][gc] GC(87) Concurrent Sweep 6.956ms
[2020-02-06T10:39:23.653-0300][264.302s][info ][gc] GC(87) Concurrent Reset
[2020-02-06T10:39:23.653-0300][264.302s][debug][gc] GC(87) Concurrent active time: 0.010ms
[2020-02-06T10:39:23.653-0300][264.302s][info ][gc] GC(87) Concurrent Reset 0.028ms
[2020-02-06T10:39:25.661-0300][266.310s][info ][gc] GC(88) Pause Initial Mark 28M->28M(30M) 6.290ms
[2020-02-06T10:39:25.662-0300][266.310s][info ][gc] GC(88) Concurrent Mark
[2020-02-06T10:39:25.695-0300][266.343s][debug][gc] GC(88) Concurrent active time: 32.831ms
[2020-02-06T10:39:25.696-0300][266.344s][info ][gc] GC(88) Concurrent Mark 33.635ms
[2020-02-06T10:39:25.696-0300][266.344s][info ][gc] GC(88) Concurrent Preclean
[2020-02-06T10:39:25.700-0300][266.348s][debug][gc] GC(88) Concurrent active time: 3.916ms
[2020-02-06T10:39:25.700-0300][266.348s][info ][gc] GC(88) Concurrent Preclean 4.059ms
[2020-02-06T10:39:25.700-0300][266.348s][info ][gc] GC(88) Concurrent Abortable Preclean
[2020-02-06T10:39:25.700-0300][266.348s][debug][gc] GC(88) Concurrent active time: 0.018ms
[2020-02-06T10:39:25.700-0300][266.348s][info ][gc] GC(88) Concurrent Abortable Preclean 0.080ms
[2020-02-06T10:39:25.700-0300][266.349s][debug][gc] GC(88) YG occupancy: 7128 K (9792 K)
[2020-02-06T10:39:25.709-0300][266.357s][info ][gc] GC(88) Pause Remark 28M->28M(30M) 8.655ms
[2020-02-06T10:39:25.709-0300][266.358s][info ][gc] GC(88) Concurrent Sweep
[2020-02-06T10:39:25.717-0300][266.366s][debug][gc] GC(88) Concurrent active time: 8.003ms
[2020-02-06T10:39:25.717-0300][266.366s][info ][gc] GC(88) Concurrent Sweep 8.076ms
[2020-02-06T10:39:25.717-0300][266.366s][info ][gc] GC(88) Concurrent Reset
[2020-02-06T10:39:25.718-0300][266.366s][debug][gc] GC(88) Concurrent active time: 0.011ms
[2020-02-06T10:39:25.718-0300][266.366s][info ][gc] GC(88) Concurrent Reset 0.050ms
[2020-02-06T10:39:27.730-0300][268.378s][info ][gc] GC(89) Pause Initial Mark 28M->28M(30M) 7.027ms
[2020-02-06T10:39:27.730-0300][268.378s][info ][gc] GC(89) Concurrent Mark
[2020-02-06T10:39:27.755-0300][268.404s][debug][gc] GC(89) Concurrent active time: 25.276ms
[2020-02-06T10:39:27.755-0300][268.404s][info ][gc] GC(89) Concurrent Mark 25.425ms
[2020-02-06T10:39:27.755-0300][268.404s][info ][gc] GC(89) Concurrent Preclean
[2020-02-06T10:39:27.759-0300][268.407s][debug][gc] GC(89) Concurrent active time: 3.740ms
[2020-02-06T10:39:27.759-0300][268.408s][info ][gc] GC(89) Concurrent Preclean 3.772ms
[2020-02-06T10:39:27.759-0300][268.408s][info ][gc] GC(89) Concurrent Abortable Preclean
[2020-02-06T10:39:27.759-0300][268.408s][debug][gc] GC(89) Concurrent active time: 0.003ms
[2020-02-06T10:39:27.759-0300][268.408s][info ][gc] GC(89) Concurrent Abortable Preclean 0.020ms
[2020-02-06T10:39:27.759-0300][268.408s][debug][gc] GC(89) YG occupancy: 7647 K (9792 K)
[2020-02-06T10:39:27.765-0300][268.413s][info ][gc] GC(89) Pause Remark 28M->28M(30M) 5.768ms
[2020-02-06T10:39:27.765-0300][268.413s][info ][gc] GC(89) Concurrent Sweep
[2020-02-06T10:39:27.772-0300][268.420s][debug][gc] GC(89) Concurrent active time: 6.783ms
[2020-02-06T10:39:27.772-0300][268.420s][info ][gc] GC(89) Concurrent Sweep 6.817ms
[2020-02-06T10:39:27.772-0300][268.420s][info ][gc] GC(89) Concurrent Reset
[2020-02-06T10:39:27.772-0300][268.420s][debug][gc] GC(89) Concurrent active time: 0.012ms
[2020-02-06T10:39:27.772-0300][268.420s][info ][gc] GC(89) Concurrent Reset 0.060ms
[2020-02-06T10:39:29.782-0300][270.430s][info ][gc] GC(90) Pause Initial Mark 28M->28M(30M) 9.275ms
[2020-02-06T10:39:29.782-0300][270.431s][info ][gc] GC(90) Concurrent Mark
[2020-02-06T10:39:29.810-0300][270.458s][debug][gc] GC(90) Concurrent active time: 27.232ms
[2020-02-06T10:39:29.810-0300][270.458s][info ][gc] GC(90) Concurrent Mark 27.554ms
[2020-02-06T10:39:29.810-0300][270.458s][info ][gc] GC(90) Concurrent Preclean
[2020-02-06T10:39:29.815-0300][270.464s][debug][gc] GC(90) Concurrent active time: 5.325ms
[2020-02-06T10:39:29.815-0300][270.464s][info ][gc] GC(90) Concurrent Preclean 5.587ms
[2020-02-06T10:39:29.815-0300][270.464s][info ][gc] GC(90) Concurrent Abortable Preclean
[2020-02-06T10:39:29.816-0300][270.464s][debug][gc] GC(90) Concurrent active time: 0.024ms
[2020-02-06T10:39:29.816-0300][270.464s][info ][gc] GC(90) Concurrent Abortable Preclean 0.156ms
[2020-02-06T10:39:29.816-0300][270.465s][debug][gc] GC(90) YG occupancy: 8167 K (9792 K)
[2020-02-06T10:39:29.827-0300][270.475s][info ][gc] GC(90) Pause Remark 28M->28M(30M) 10.622ms
[2020-02-06T10:39:29.827-0300][270.476s][info ][gc] GC(90) Concurrent Sweep
[2020-02-06T10:39:29.835-0300][270.484s][debug][gc] GC(90) Concurrent active time: 8.308ms
[2020-02-06T10:39:29.836-0300][270.484s][info ][gc] GC(90) Concurrent Sweep 8.459ms
[2020-02-06T10:39:29.836-0300][270.484s][info ][gc] GC(90) Concurrent Reset
[2020-02-06T10:39:29.836-0300][270.484s][debug][gc] GC(90) Concurrent active time: 0.014ms
[2020-02-06T10:39:29.836-0300][270.484s][info ][gc] GC(90) Concurrent Reset 0.042ms
[2020-02-06T10:39:31.846-0300][272.495s][info ][gc] GC(91) Pause Initial Mark 29M->29M(30M) 7.330ms
[2020-02-06T10:39:31.847-0300][272.495s][info ][gc] GC(91) Concurrent Mark
[2020-02-06T10:39:31.870-0300][272.519s][debug][gc] GC(91) Concurrent active time: 23.540ms
[2020-02-06T10:39:31.870-0300][272.519s][info ][gc] GC(91) Concurrent Mark 23.745ms
[2020-02-06T10:39:31.870-0300][272.519s][info ][gc] GC(91) Concurrent Preclean
[2020-02-06T10:39:31.874-0300][272.523s][debug][gc] GC(91) Concurrent active time: 3.904ms
[2020-02-06T10:39:31.874-0300][272.523s][info ][gc] GC(91) Concurrent Preclean 3.938ms
[2020-02-06T10:39:31.874-0300][272.523s][info ][gc] GC(91) Concurrent Abortable Preclean
[2020-02-06T10:39:31.874-0300][272.523s][debug][gc] GC(91) Concurrent active time: 0.004ms
[2020-02-06T10:39:31.874-0300][272.523s][info ][gc] GC(91) Concurrent Abortable Preclean 0.021ms
[2020-02-06T10:39:31.874-0300][272.523s][debug][gc] GC(91) YG occupancy: 8704 K (9792 K)
[2020-02-06T10:39:31.881-0300][272.529s][info ][gc] GC(91) Pause Remark 29M->29M(30M) 6.282ms
[2020-02-06T10:39:31.881-0300][272.529s][info ][gc] GC(91) Concurrent Sweep
[2020-02-06T10:39:31.888-0300][272.536s][debug][gc] GC(91) Concurrent active time: 6.737ms
[2020-02-06T10:39:31.888-0300][272.536s][info ][gc] GC(91) Concurrent Sweep 6.779ms
[2020-02-06T10:39:31.888-0300][272.536s][info ][gc] GC(91) Concurrent Reset
[2020-02-06T10:39:31.888-0300][272.536s][debug][gc] GC(91) Concurrent active time: 0.012ms
[2020-02-06T10:39:31.888-0300][272.536s][info ][gc] GC(91) Concurrent Reset 0.037ms
[2020-02-06T10:39:33.903-0300][274.551s][info ][gc] GC(92) Pause Initial Mark 29M->29M(30M) 7.352ms
[2020-02-06T10:39:33.903-0300][274.551s][info ][gc] GC(92) Concurrent Mark
[2020-02-06T10:39:33.927-0300][274.575s][debug][gc] GC(92) Concurrent active time: 24.204ms
[2020-02-06T10:39:33.927-0300][274.576s][info ][gc] GC(92) Concurrent Mark 24.329ms
[2020-02-06T10:39:33.927-0300][274.576s][info ][gc] GC(92) Concurrent Preclean
[2020-02-06T10:39:33.931-0300][274.579s][debug][gc] GC(92) Concurrent active time: 3.695ms
[2020-02-06T10:39:33.931-0300][274.579s][info ][gc] GC(92) Concurrent Preclean 3.741ms
[2020-02-06T10:39:33.931-0300][274.579s][info ][gc] GC(92) Concurrent Abortable Preclean
[2020-02-06T10:39:33.931-0300][274.579s][debug][gc] GC(92) Concurrent active time: 0.004ms
[2020-02-06T10:39:33.931-0300][274.579s][info ][gc] GC(92) Concurrent Abortable Preclean 0.021ms
[2020-02-06T10:39:33.931-0300][274.580s][debug][gc] GC(92) YG occupancy: 9152 K (9792 K)
[2020-02-06T10:39:33.938-0300][274.586s][info ][gc] GC(92) Pause Remark 29M->29M(30M) 6.391ms
[2020-02-06T10:39:33.938-0300][274.586s][info ][gc] GC(92) Concurrent Sweep
[2020-02-06T10:39:33.945-0300][274.593s][debug][gc] GC(92) Concurrent active time: 6.859ms
[2020-02-06T10:39:33.945-0300][274.593s][info ][gc] GC(92) Concurrent Sweep 6.921ms
[2020-02-06T10:39:33.945-0300][274.593s][info ][gc] GC(92) Concurrent Reset
[2020-02-06T10:39:33.945-0300][274.593s][debug][gc] GC(92) Concurrent active time: 0.013ms
[2020-02-06T10:39:33.945-0300][274.593s][info ][gc] GC(92) Concurrent Reset 0.041ms
[2020-02-06T10:39:35.955-0300][276.604s][info ][gc] GC(93) Pause Initial Mark 29M->29M(30M) 7.017ms
[2020-02-06T10:39:35.956-0300][276.604s][info ][gc] GC(93) Concurrent Mark
[2020-02-06T10:39:35.985-0300][276.633s][debug][gc] GC(93) Concurrent active time: 29.226ms
[2020-02-06T10:39:35.985-0300][276.633s][info ][gc] GC(93) Concurrent Mark 29.510ms
[2020-02-06T10:39:35.985-0300][276.633s][info ][gc] GC(93) Concurrent Preclean
[2020-02-06T10:39:35.989-0300][276.637s][debug][gc] GC(93) Concurrent active time: 4.007ms
[2020-02-06T10:39:35.989-0300][276.638s][info ][gc] GC(93) Concurrent Preclean 4.135ms
[2020-02-06T10:39:35.989-0300][276.638s][info ][gc] GC(93) Concurrent Abortable Preclean
[2020-02-06T10:39:35.989-0300][276.638s][debug][gc] GC(93) Concurrent active time: 0.015ms
[2020-02-06T10:39:35.989-0300][276.638s][info ][gc] GC(93) Concurrent Abortable Preclean 0.060ms
[2020-02-06T10:39:35.990-0300][276.638s][debug][gc] GC(93) YG occupancy: 9622 K (9792 K)
[2020-02-06T10:39:35.998-0300][276.646s][info ][gc] GC(93) Pause Remark 29M->29M(30M) 8.110ms
[2020-02-06T10:39:35.998-0300][276.646s][info ][gc] GC(93) Concurrent Sweep
[2020-02-06T10:39:36.005-0300][276.653s][debug][gc] GC(93) Concurrent active time: 6.708ms
[2020-02-06T10:39:36.005-0300][276.653s][info ][gc] GC(93) Concurrent Sweep 6.750ms
[2020-02-06T10:39:36.005-0300][276.653s][info ][gc] GC(93) Concurrent Reset
[2020-02-06T10:39:36.005-0300][276.653s][debug][gc] GC(93) Concurrent active time: 0.011ms
[2020-02-06T10:39:36.005-0300][276.653s][info ][gc] GC(93) Concurrent Reset 0.038ms
[2020-02-06T10:39:36.454-0300][277.103s][info ][gc] GC(94) Pause Young (Allocation Failure) 29M->29M(30M) 0.066ms
[2020-02-06T10:39:36.543-0300][277.191s][info ][gc] GC(95) Pause Full (Allocation Failure) 29M->27M(30M) 88.440ms
[2020-02-06T10:39:36.550-0300][277.198s][info ][gc] GC(96) Pause Initial Mark 27M->27M(30M) 4.514ms
[2020-02-06T10:39:36.550-0300][277.198s][info ][gc] GC(96) Concurrent Mark
[2020-02-06T10:39:36.567-0300][277.215s][debug][gc] GC(96) Concurrent active time: 16.963ms
[2020-02-06T10:39:36.567-0300][277.215s][info ][gc] GC(96) Concurrent Mark 17.046ms
[2020-02-06T10:39:36.567-0300][277.215s][info ][gc] GC(96) Concurrent Preclean
[2020-02-06T10:39:36.594-0300][277.242s][debug][gc] GC(96) Concurrent active time: 26.928ms
[2020-02-06T10:39:36.595-0300][277.243s][info ][gc] GC(96) Concurrent Preclean 27.548ms
[2020-02-06T10:39:36.595-0300][277.243s][info ][gc] GC(96) Concurrent Abortable Preclean
[2020-02-06T10:39:36.595-0300][277.243s][debug][gc] GC(96) Concurrent active time: 0.035ms
[2020-02-06T10:39:36.596-0300][277.244s][info ][gc] GC(96) Concurrent Abortable Preclean 0.679ms
[2020-02-06T10:39:36.597-0300][277.245s][debug][gc] GC(96) YG occupancy: 6708 K (9792 K)
[2020-02-06T10:39:36.604-0300][277.253s][info ][gc] GC(96) Pause Remark 27M->27M(30M) 7.544ms
[2020-02-06T10:39:36.604-0300][277.253s][info ][gc] GC(96) Concurrent Sweep
[2020-02-06T10:39:36.611-0300][277.260s][debug][gc] GC(96) Concurrent active time: 6.953ms
[2020-02-06T10:39:36.611-0300][277.260s][info ][gc] GC(96) Concurrent Sweep 7.008ms
[2020-02-06T10:39:36.611-0300][277.260s][info ][gc] GC(96) Concurrent Reset
[2020-02-06T10:39:36.612-0300][277.260s][debug][gc] GC(96) Concurrent active time: 0.011ms
[2020-02-06T10:39:36.612-0300][277.260s][info ][gc] GC(96) Concurrent Reset 0.033ms
[2020-02-06T10:39:38.618-0300][279.266s][info ][gc] GC(97) Pause Initial Mark 28M->28M(30M) 4.581ms
[2020-02-06T10:39:38.618-0300][279.266s][info ][gc] GC(97) Concurrent Mark
[2020-02-06T10:39:38.634-0300][279.283s][debug][gc] GC(97) Concurrent active time: 16.256ms
[2020-02-06T10:39:38.634-0300][279.283s][info ][gc] GC(97) Concurrent Mark 16.322ms
[2020-02-06T10:39:38.634-0300][279.283s][info ][gc] GC(97) Concurrent Preclean
[2020-02-06T10:39:38.638-0300][279.286s][debug][gc] GC(97) Concurrent active time: 3.787ms
[2020-02-06T10:39:38.638-0300][279.286s][info ][gc] GC(97) Concurrent Preclean 3.822ms
[2020-02-06T10:39:38.638-0300][279.286s][info ][gc] GC(97) Concurrent Abortable Preclean
[2020-02-06T10:39:38.638-0300][279.286s][debug][gc] GC(97) Concurrent active time: 0.003ms
[2020-02-06T10:39:38.638-0300][279.286s][info ][gc] GC(97) Concurrent Abortable Preclean 0.021ms
[2020-02-06T10:39:38.638-0300][279.286s][debug][gc] GC(97) YG occupancy: 7171 K (9792 K)
[2020-02-06T10:39:38.646-0300][279.295s][info ][gc] GC(97) Pause Remark 28M->28M(30M) 8.160ms
[2020-02-06T10:39:38.647-0300][279.295s][info ][gc] GC(97) Concurrent Sweep
[2020-02-06T10:39:38.655-0300][279.303s][debug][gc] GC(97) Concurrent active time: 8.652ms
[2020-02-06T10:39:38.655-0300][279.304s][info ][gc] GC(97) Concurrent Sweep 8.773ms
[2020-02-06T10:39:38.655-0300][279.304s][info ][gc] GC(97) Concurrent Reset
[2020-02-06T10:39:38.656-0300][279.304s][debug][gc] GC(97) Concurrent active time: 0.043ms
[2020-02-06T10:39:38.656-0300][279.304s][info ][gc] GC(97) Concurrent Reset 0.131ms
[2020-02-06T10:39:40.663-0300][281.311s][info ][gc] GC(98) Pause Initial Mark 28M->28M(30M) 6.481ms
[2020-02-06T10:39:40.663-0300][281.311s][info ][gc] GC(98) Concurrent Mark
[2020-02-06T10:39:40.681-0300][281.330s][debug][gc] GC(98) Concurrent active time: 18.067ms
[2020-02-06T10:39:40.681-0300][281.330s][info ][gc] GC(98) Concurrent Mark 18.158ms
[2020-02-06T10:39:40.681-0300][281.330s][info ][gc] GC(98) Concurrent Preclean
[2020-02-06T10:39:40.686-0300][281.334s][debug][gc] GC(98) Concurrent active time: 4.271ms
[2020-02-06T10:39:40.686-0300][281.334s][info ][gc] GC(98) Concurrent Preclean 4.320ms
[2020-02-06T10:39:40.686-0300][281.334s][info ][gc] GC(98) Concurrent Abortable Preclean
[2020-02-06T10:39:40.686-0300][281.334s][debug][gc] GC(98) Concurrent active time: 0.004ms
[2020-02-06T10:39:40.686-0300][281.334s][info ][gc] GC(98) Concurrent Abortable Preclean 0.021ms
[2020-02-06T10:39:40.686-0300][281.334s][debug][gc] GC(98) YG occupancy: 7642 K (9792 K)
[2020-02-06T10:39:40.693-0300][281.341s][info ][gc] GC(98) Pause Remark 28M->28M(30M) 6.861ms
[2020-02-06T10:39:40.693-0300][281.341s][info ][gc] GC(98) Concurrent Sweep
[2020-02-06T10:39:40.702-0300][281.350s][debug][gc] GC(98) Concurrent active time: 8.899ms
[2020-02-06T10:39:40.702-0300][281.350s][info ][gc] GC(98) Concurrent Sweep 9.035ms
[2020-02-06T10:39:40.702-0300][281.350s][info ][gc] GC(98) Concurrent Reset
[2020-02-06T10:39:40.702-0300][281.350s][debug][gc] GC(98) Concurrent active time: 0.094ms
[2020-02-06T10:39:40.702-0300][281.350s][info ][gc] GC(98) Concurrent Reset 0.248ms
[2020-02-06T10:39:42.717-0300][283.366s][info ][gc] GC(99) Pause Initial Mark 28M->28M(30M) 12.545ms
[2020-02-06T10:39:42.720-0300][283.368s][info ][gc] GC(99) Concurrent Mark
[2020-02-06T10:39:42.755-0300][283.403s][debug][gc] GC(99) Concurrent active time: 34.634ms
[2020-02-06T10:39:42.755-0300][283.403s][info ][gc] GC(99) Concurrent Mark 34.755ms
[2020-02-06T10:39:42.755-0300][283.403s][info ][gc] GC(99) Concurrent Preclean
[2020-02-06T10:39:42.762-0300][283.410s][debug][gc] GC(99) Concurrent active time: 7.106ms
[2020-02-06T10:39:42.762-0300][283.410s][info ][gc] GC(99) Concurrent Preclean 7.197ms
[2020-02-06T10:39:42.762-0300][283.410s][info ][gc] GC(99) Concurrent Abortable Preclean
[2020-02-06T10:39:42.762-0300][283.411s][debug][gc] GC(99) Concurrent active time: 0.006ms
[2020-02-06T10:39:42.762-0300][283.411s][info ][gc] GC(99) Concurrent Abortable Preclean 0.061ms
[2020-02-06T10:39:42.765-0300][283.413s][debug][gc] GC(99) YG occupancy: 8006 K (9792 K)
[2020-02-06T10:39:42.779-0300][283.427s][info ][gc] GC(99) Pause Remark 28M->28M(30M) 13.929ms
[2020-02-06T10:39:42.782-0300][283.430s][info ][gc] GC(99) Concurrent Sweep
[2020-02-06T10:39:42.871-0300][283.519s][debug][gc] GC(99) Concurrent active time: 88.802ms
[2020-02-06T10:39:42.871-0300][283.519s][info ][gc] GC(99) Concurrent Sweep 89.007ms
[2020-02-06T10:39:42.872-0300][283.520s][info ][gc] GC(99) Concurrent Reset
[2020-02-06T10:39:42.872-0300][283.520s][debug][gc] GC(99) Concurrent active time: 0.013ms
[2020-02-06T10:39:42.872-0300][283.520s][info ][gc] GC(99) Concurrent Reset 0.060ms
[2020-02-06T10:39:44.884-0300][285.532s][info ][gc] GC(100) Pause Initial Mark 29M->29M(30M) 8.871ms
[2020-02-06T10:39:44.884-0300][285.532s][info ][gc] GC(100) Concurrent Mark
[2020-02-06T10:39:44.919-0300][285.567s][debug][gc] GC(100) Concurrent active time: 34.326ms
[2020-02-06T10:39:44.919-0300][285.567s][info ][gc] GC(100) Concurrent Mark 34.488ms
[2020-02-06T10:39:44.919-0300][285.567s][info ][gc] GC(100) Concurrent Preclean
[2020-02-06T10:39:44.925-0300][285.573s][debug][gc] GC(100) Concurrent active time: 6.133ms
[2020-02-06T10:39:44.925-0300][285.573s][info ][gc] GC(100) Concurrent Preclean 6.243ms
[2020-02-06T10:39:44.925-0300][285.573s][info ][gc] GC(100) Concurrent Abortable Preclean
[2020-02-06T10:39:44.925-0300][285.573s][debug][gc] GC(100) Concurrent active time: 0.010ms
[2020-02-06T10:39:44.925-0300][285.573s][info ][gc] GC(100) Concurrent Abortable Preclean 0.052ms
[2020-02-06T10:39:44.926-0300][285.574s][debug][gc] GC(100) YG occupancy: 8498 K (9792 K)
[2020-02-06T10:39:44.938-0300][285.586s][info ][gc] GC(100) Pause Remark 29M->29M(30M) 12.632ms
[2020-02-06T10:39:44.938-0300][285.586s][info ][gc] GC(100) Concurrent Sweep
[2020-02-06T10:39:44.954-0300][285.602s][debug][gc] GC(100) Concurrent active time: 15.562ms
[2020-02-06T10:39:44.954-0300][285.602s][info ][gc] GC(100) Concurrent Sweep 15.666ms
[2020-02-06T10:39:44.954-0300][285.602s][info ][gc] GC(100) Concurrent Reset
[2020-02-06T10:39:44.954-0300][285.602s][debug][gc] GC(100) Concurrent active time: 0.013ms
[2020-02-06T10:39:44.954-0300][285.602s][info ][gc] GC(100) Concurrent Reset 0.042ms
[2020-02-06T10:39:46.967-0300][287.615s][info ][gc] GC(101) Pause Initial Mark 29M->29M(30M) 7.643ms
[2020-02-06T10:39:46.967-0300][287.615s][info ][gc] GC(101) Concurrent Mark
[2020-02-06T10:39:46.985-0300][287.633s][debug][gc] GC(101) Concurrent active time: 18.197ms
[2020-02-06T10:39:46.985-0300][287.633s][info ][gc] GC(101) Concurrent Mark 18.286ms
[2020-02-06T10:39:46.985-0300][287.633s][info ][gc] GC(101) Concurrent Preclean
[2020-02-06T10:39:46.989-0300][287.637s][debug][gc] GC(101) Concurrent active time: 3.842ms
[2020-02-06T10:39:46.989-0300][287.637s][info ][gc] GC(101) Concurrent Preclean 3.893ms
[2020-02-06T10:39:46.989-0300][287.637s][info ][gc] GC(101) Concurrent Abortable Preclean
[2020-02-06T10:39:46.989-0300][287.637s][debug][gc] GC(101) Concurrent active time: 0.004ms
[2020-02-06T10:39:46.989-0300][287.637s][info ][gc] GC(101) Concurrent Abortable Preclean 0.025ms
[2020-02-06T10:39:46.989-0300][287.637s][debug][gc] GC(101) YG occupancy: 8921 K (9792 K)
[2020-02-06T10:39:46.995-0300][287.643s][info ][gc] GC(101) Pause Remark 29M->29M(30M) 6.328ms
[2020-02-06T10:39:46.995-0300][287.643s][info ][gc] GC(101) Concurrent Sweep
[2020-02-06T10:39:47.002-0300][287.650s][debug][gc] GC(101) Concurrent active time: 6.914ms
[2020-02-06T10:39:47.002-0300][287.650s][info ][gc] GC(101) Concurrent Sweep 6.984ms
[2020-02-06T10:39:47.003-0300][287.650s][info ][gc] GC(101) Concurrent Reset
[2020-02-06T10:39:47.003-0300][287.650s][debug][gc] GC(101) Concurrent active time: 0.012ms
[2020-02-06T10:39:47.003-0300][287.650s][info ][gc] GC(101) Concurrent Reset 0.033ms
[2020-02-06T10:39:49.012-0300][289.660s][info ][gc] GC(102) Pause Initial Mark 30M->30M(30M) 6.483ms
[2020-02-06T10:39:49.012-0300][289.660s][info ][gc] GC(102) Concurrent Mark
[2020-02-06T10:39:49.034-0300][289.682s][debug][gc] GC(102) Concurrent active time: 21.611ms
[2020-02-06T10:39:49.034-0300][289.682s][info ][gc] GC(102) Concurrent Mark 21.684ms
[2020-02-06T10:39:49.034-0300][289.682s][info ][gc] GC(102) Concurrent Preclean
[2020-02-06T10:39:49.038-0300][289.686s][debug][gc] GC(102) Concurrent active time: 3.869ms
[2020-02-06T10:39:49.038-0300][289.686s][info ][gc] GC(102) Concurrent Preclean 3.920ms
[2020-02-06T10:39:49.038-0300][289.686s][info ][gc] GC(102) Concurrent Abortable Preclean
[2020-02-06T10:39:49.038-0300][289.686s][debug][gc] GC(102) Concurrent active time: 0.005ms
[2020-02-06T10:39:49.038-0300][289.686s][info ][gc] GC(102) Concurrent Abortable Preclean 0.023ms
[2020-02-06T10:39:49.038-0300][289.686s][debug][gc] GC(102) YG occupancy: 9442 K (9792 K)
[2020-02-06T10:39:49.045-0300][289.693s][info ][gc] GC(102) Pause Remark 30M->30M(30M) 6.670ms
[2020-02-06T10:39:49.045-0300][289.693s][info ][gc] GC(102) Concurrent Sweep
[2020-02-06T10:39:49.052-0300][289.700s][debug][gc] GC(102) Concurrent active time: 7.158ms
[2020-02-06T10:39:49.052-0300][289.700s][info ][gc] GC(102) Concurrent Sweep 7.273ms
[2020-02-06T10:39:49.052-0300][289.700s][info ][gc] GC(102) Concurrent Reset
[2020-02-06T10:39:49.052-0300][289.700s][debug][gc] GC(102) Concurrent active time: 0.014ms
[2020-02-06T10:39:49.052-0300][289.700s][info ][gc] GC(102) Concurrent Reset 0.042ms
[2020-02-06T10:39:50.729-0300][291.376s][info ][gc] GC(103) Pause Young (Allocation Failure) 30M->30M(30M) 0.077ms
[2020-02-06T10:39:50.793-0300][291.441s][info ][gc] GC(104) Pause Full (Allocation Failure) 30M->28M(30M) 64.765ms
[2020-02-06T10:39:50.800-0300][291.448s][info ][gc] GC(105) Pause Initial Mark 28M->28M(30M) 6.355ms
[2020-02-06T10:39:50.801-0300][291.448s][info ][gc] GC(105) Concurrent Mark
[2020-02-06T10:39:50.828-0300][291.476s][debug][gc] GC(105) Concurrent active time: 27.654ms
[2020-02-06T10:39:50.828-0300][291.476s][info ][gc] GC(105) Concurrent Mark 27.855ms
[2020-02-06T10:39:50.828-0300][291.476s][info ][gc] GC(105) Concurrent Preclean
[2020-02-06T10:39:50.849-0300][291.497s][debug][gc] GC(105) Concurrent active time: 20.641ms
[2020-02-06T10:39:50.849-0300][291.497s][info ][gc] GC(105) Concurrent Preclean 20.707ms
[2020-02-06T10:39:50.849-0300][291.497s][info ][gc] GC(105) Concurrent Abortable Preclean
[2020-02-06T10:39:50.849-0300][291.497s][debug][gc] GC(105) Concurrent active time: 0.001ms
[2020-02-06T10:39:50.849-0300][291.497s][info ][gc] GC(105) Concurrent Abortable Preclean 0.019ms
[2020-02-06T10:39:50.849-0300][291.497s][debug][gc] GC(105) YG occupancy: 7528 K (9792 K)
[2020-02-06T10:39:50.856-0300][291.504s][info ][gc] GC(105) Pause Remark 28M->28M(30M) 6.693ms
[2020-02-06T10:39:50.856-0300][291.504s][info ][gc] GC(105) Concurrent Sweep
[2020-02-06T10:39:50.863-0300][291.511s][debug][gc] GC(105) Concurrent active time: 7.098ms
[2020-02-06T10:39:50.863-0300][291.511s][info ][gc] GC(105) Concurrent Sweep 7.175ms
[2020-02-06T10:39:50.863-0300][291.511s][info ][gc] GC(105) Concurrent Reset
[2020-02-06T10:39:50.863-0300][291.511s][debug][gc] GC(105) Concurrent active time: 0.013ms
[2020-02-06T10:39:50.863-0300][291.511s][info ][gc] GC(105) Concurrent Reset 0.051ms
[2020-02-06T10:39:52.875-0300][293.523s][info ][gc] GC(106) Pause Initial Mark 29M->29M(30M) 8.248ms
[2020-02-06T10:39:52.875-0300][293.523s][info ][gc] GC(106) Concurrent Mark
[2020-02-06T10:39:52.896-0300][293.544s][debug][gc] GC(106) Concurrent active time: 20.390ms
[2020-02-06T10:39:52.896-0300][293.544s][info ][gc] GC(106) Concurrent Mark 20.495ms
[2020-02-06T10:39:52.896-0300][293.544s][info ][gc] GC(106) Concurrent Preclean
[2020-02-06T10:39:52.902-0300][293.550s][debug][gc] GC(106) Concurrent active time: 5.870ms
[2020-02-06T10:39:52.902-0300][293.550s][info ][gc] GC(106) Concurrent Preclean 6.074ms
[2020-02-06T10:39:52.902-0300][293.550s][info ][gc] GC(106) Concurrent Abortable Preclean
[2020-02-06T10:39:52.902-0300][293.550s][debug][gc] GC(106) Concurrent active time: 0.022ms
[2020-02-06T10:39:52.902-0300][293.550s][info ][gc] GC(106) Concurrent Abortable Preclean 0.135ms
[2020-02-06T10:39:52.903-0300][293.551s][debug][gc] GC(106) YG occupancy: 8028 K (9792 K)
[2020-02-06T10:39:52.918-0300][293.566s][info ][gc] GC(106) Pause Remark 29M->29M(30M) 15.379ms
[2020-02-06T10:39:52.919-0300][293.566s][info ][gc] GC(106) Concurrent Sweep
[2020-02-06T10:39:52.929-0300][293.577s][debug][gc] GC(106) Concurrent active time: 10.551ms
[2020-02-06T10:39:52.929-0300][293.577s][info ][gc] GC(106) Concurrent Sweep 10.775ms
[2020-02-06T10:39:52.929-0300][293.577s][info ][gc] GC(106) Concurrent Reset
[2020-02-06T10:39:52.930-0300][293.577s][debug][gc] GC(106) Concurrent active time: 0.100ms
[2020-02-06T10:39:52.930-0300][293.578s][info ][gc] GC(106) Concurrent Reset 0.249ms
[2020-02-06T10:39:54.936-0300][295.584s][info ][gc] GC(107) Pause Initial Mark 29M->29M(30M) 5.458ms
[2020-02-06T10:39:54.936-0300][295.584s][info ][gc] GC(107) Concurrent Mark
[2020-02-06T10:39:54.955-0300][295.603s][debug][gc] GC(107) Concurrent active time: 18.810ms
[2020-02-06T10:39:54.955-0300][295.603s][info ][gc] GC(107) Concurrent Mark 18.912ms
[2020-02-06T10:39:54.955-0300][295.603s][info ][gc] GC(107) Concurrent Preclean
[2020-02-06T10:39:54.963-0300][295.611s][debug][gc] GC(107) Concurrent active time: 7.964ms
[2020-02-06T10:39:54.963-0300][295.611s][info ][gc] GC(107) Concurrent Preclean 8.047ms
[2020-02-06T10:39:54.963-0300][295.611s][info ][gc] GC(107) Concurrent Abortable Preclean
[2020-02-06T10:39:54.963-0300][295.611s][debug][gc] GC(107) Concurrent active time: 0.005ms
[2020-02-06T10:39:54.964-0300][295.611s][info ][gc] GC(107) Concurrent Abortable Preclean 0.036ms
[2020-02-06T10:39:54.964-0300][295.611s][debug][gc] GC(107) YG occupancy: 8556 K (9792 K)
[2020-02-06T10:39:54.972-0300][295.620s][info ][gc] GC(107) Pause Remark 29M->29M(30M) 8.116ms
[2020-02-06T10:39:54.972-0300][295.620s][info ][gc] GC(107) Concurrent Sweep
[2020-02-06T10:39:54.979-0300][295.627s][debug][gc] GC(107) Concurrent active time: 7.124ms
[2020-02-06T10:39:54.979-0300][295.627s][info ][gc] GC(107) Concurrent Sweep 7.189ms
[2020-02-06T10:39:54.979-0300][295.627s][info ][gc] GC(107) Concurrent Reset
[2020-02-06T10:39:54.979-0300][295.627s][debug][gc] GC(107) Concurrent active time: 0.012ms
[2020-02-06T10:39:54.979-0300][295.627s][info ][gc] GC(107) Concurrent Reset 0.031ms
[2020-02-06T10:39:56.992-0300][297.639s][info ][gc] GC(108) Pause Initial Mark 29M->29M(30M) 8.452ms
[2020-02-06T10:39:56.992-0300][297.639s][info ][gc] GC(108) Concurrent Mark
[2020-02-06T10:39:57.025-0300][297.672s][debug][gc] GC(108) Concurrent active time: 32.833ms
[2020-02-06T10:39:57.025-0300][297.672s][info ][gc] GC(108) Concurrent Mark 32.979ms
[2020-02-06T10:39:57.025-0300][297.672s][info ][gc] GC(108) Concurrent Preclean
[2020-02-06T10:39:57.029-0300][297.677s][debug][gc] GC(108) Concurrent active time: 4.178ms
[2020-02-06T10:39:57.029-0300][297.677s][info ][gc] GC(108) Concurrent Preclean 4.223ms
[2020-02-06T10:39:57.029-0300][297.677s][info ][gc] GC(108) Concurrent Abortable Preclean
[2020-02-06T10:39:57.029-0300][297.677s][debug][gc] GC(108) Concurrent active time: 0.004ms
[2020-02-06T10:39:57.029-0300][297.677s][info ][gc] GC(108) Concurrent Abortable Preclean 0.025ms
[2020-02-06T10:39:57.029-0300][297.677s][debug][gc] GC(108) YG occupancy: 9021 K (9792 K)
[2020-02-06T10:39:57.036-0300][297.684s][info ][gc] GC(108) Pause Remark 29M->29M(30M) 6.859ms
[2020-02-06T10:39:57.036-0300][297.684s][info ][gc] GC(108) Concurrent Sweep
[2020-02-06T10:39:57.043-0300][297.691s][debug][gc] GC(108) Concurrent active time: 6.898ms
[2020-02-06T10:39:57.043-0300][297.691s][info ][gc] GC(108) Concurrent Sweep 6.959ms
[2020-02-06T10:39:57.043-0300][297.691s][info ][gc] GC(108) Concurrent Reset
[2020-02-06T10:39:57.043-0300][297.691s][debug][gc] GC(108) Concurrent active time: 0.012ms
[2020-02-06T10:39:57.043-0300][297.691s][info ][gc] GC(108) Concurrent Reset 0.032ms
[2020-02-06T10:39:59.055-0300][299.702s][info ][gc] GC(109) Pause Initial Mark 30M->30M(30M) 7.464ms
[2020-02-06T10:39:59.056-0300][299.704s][info ][gc] GC(109) Concurrent Mark
[2020-02-06T10:39:59.090-0300][299.737s][debug][gc] GC(109) Concurrent active time: 33.283ms
[2020-02-06T10:39:59.090-0300][299.737s][info ][gc] GC(109) Concurrent Mark 33.546ms
[2020-02-06T10:39:59.090-0300][299.737s][info ][gc] GC(109) Concurrent Preclean
[2020-02-06T10:39:59.096-0300][299.744s][debug][gc] GC(109) Concurrent active time: 6.206ms
[2020-02-06T10:39:59.096-0300][299.744s][info ][gc] GC(109) Concurrent Preclean 6.274ms
[2020-02-06T10:39:59.096-0300][299.744s][info ][gc] GC(109) Concurrent Abortable Preclean
[2020-02-06T10:39:59.096-0300][299.744s][debug][gc] GC(109) Concurrent active time: 0.006ms
[2020-02-06T10:39:59.096-0300][299.744s][info ][gc] GC(109) Concurrent Abortable Preclean 0.032ms
[2020-02-06T10:39:59.096-0300][299.744s][debug][gc] GC(109) YG occupancy: 9513 K (9792 K)
[2020-02-06T10:39:59.105-0300][299.753s][info ][gc] GC(109) Pause Remark 30M->30M(30M) 8.913ms
[2020-02-06T10:39:59.106-0300][299.753s][info ][gc] GC(109) Concurrent Sweep
[2020-02-06T10:39:59.118-0300][299.766s][debug][gc] GC(109) Concurrent active time: 12.329ms
[2020-02-06T10:39:59.118-0300][299.766s][info ][gc] GC(109) Concurrent Sweep 12.446ms
[2020-02-06T10:39:59.118-0300][299.766s][info ][gc] GC(109) Concurrent Reset
[2020-02-06T10:39:59.118-0300][299.766s][debug][gc] GC(109) Concurrent active time: 0.016ms
[2020-02-06T10:39:59.118-0300][299.766s][info ][gc] GC(109) Concurrent Reset 0.051ms
[2020-02-06T10:40:00.233-0300][300.881s][info ][gc] GC(110) Pause Young (Allocation Failure) 30M->30M(30M) 0.063ms
[2020-02-06T10:40:00.304-0300][300.952s][info ][gc] GC(111) Pause Full (Allocation Failure) 30M->29M(30M) 70.851ms
[2020-02-06T10:40:00.315-0300][300.962s][info ][gc] GC(112) Pause Initial Mark 29M->29M(30M) 7.371ms
[2020-02-06T10:40:00.315-0300][300.962s][info ][gc] GC(112) Concurrent Mark
[2020-02-06T10:40:00.344-0300][300.991s][debug][gc] GC(112) Concurrent active time: 28.501ms
[2020-02-06T10:40:00.344-0300][300.991s][info ][gc] GC(112) Concurrent Mark 28.991ms
[2020-02-06T10:40:00.344-0300][300.991s][info ][gc] GC(112) Concurrent Preclean
[2020-02-06T10:40:00.365-0300][301.012s][debug][gc] GC(112) Concurrent active time: 21.022ms
[2020-02-06T10:40:00.365-0300][301.012s][info ][gc] GC(112) Concurrent Preclean 21.080ms
[2020-02-06T10:40:00.365-0300][301.012s][info ][gc] GC(112) Concurrent Abortable Preclean
[2020-02-06T10:40:00.365-0300][301.012s][debug][gc] GC(112) Concurrent active time: 0.001ms
[2020-02-06T10:40:00.365-0300][301.012s][info ][gc] GC(112) Concurrent Abortable Preclean 0.019ms
[2020-02-06T10:40:00.365-0300][301.013s][debug][gc] GC(112) YG occupancy: 8208 K (9792 K)
[2020-02-06T10:40:00.372-0300][301.020s][info ][gc] GC(112) Pause Remark 29M->29M(30M) 7.038ms
[2020-02-06T10:40:00.372-0300][301.020s][info ][gc] GC(112) Concurrent Sweep
[2020-02-06T10:40:00.379-0300][301.027s][debug][gc] GC(112) Concurrent active time: 7.053ms
[2020-02-06T10:40:00.379-0300][301.027s][info ][gc] GC(112) Concurrent Sweep 7.112ms
[2020-02-06T10:40:00.379-0300][301.027s][info ][gc] GC(112) Concurrent Reset
[2020-02-06T10:40:00.379-0300][301.027s][debug][gc] GC(112) Concurrent active time: 0.012ms
[2020-02-06T10:40:00.379-0300][301.027s][info ][gc] GC(112) Concurrent Reset 0.036ms
[2020-02-06T10:40:02.390-0300][303.037s][info ][gc] GC(113) Pause Initial Mark 29M->29M(30M) 5.475ms
[2020-02-06T10:40:02.390-0300][303.038s][info ][gc] GC(113) Concurrent Mark
[2020-02-06T10:40:02.407-0300][303.055s][debug][gc] GC(113) Concurrent active time: 17.011ms
[2020-02-06T10:40:02.407-0300][303.055s][info ][gc] GC(113) Concurrent Mark 17.069ms
[2020-02-06T10:40:02.407-0300][303.055s][info ][gc] GC(113) Concurrent Preclean
[2020-02-06T10:40:02.411-0300][303.059s][debug][gc] GC(113) Concurrent active time: 4.080ms
[2020-02-06T10:40:02.411-0300][303.059s][info ][gc] GC(113) Concurrent Preclean 4.105ms
[2020-02-06T10:40:02.411-0300][303.059s][info ][gc] GC(113) Concurrent Abortable Preclean
[2020-02-06T10:40:02.411-0300][303.059s][debug][gc] GC(113) Concurrent active time: 0.002ms
[2020-02-06T10:40:02.411-0300][303.059s][info ][gc] GC(113) Concurrent Abortable Preclean 0.019ms
[2020-02-06T10:40:02.411-0300][303.059s][debug][gc] GC(113) YG occupancy: 8716 K (9792 K)
[2020-02-06T10:40:02.418-0300][303.066s][info ][gc] GC(113) Pause Remark 29M->29M(30M) 7.145ms
[2020-02-06T10:40:02.419-0300][303.066s][info ][gc] GC(113) Concurrent Sweep
[2020-02-06T10:40:02.425-0300][303.073s][debug][gc] GC(113) Concurrent active time: 6.847ms
[2020-02-06T10:40:02.425-0300][303.073s][info ][gc] GC(113) Concurrent Sweep 6.875ms
[2020-02-06T10:40:02.425-0300][303.073s][info ][gc] GC(113) Concurrent Reset
[2020-02-06T10:40:02.425-0300][303.073s][debug][gc] GC(113) Concurrent active time: 0.010ms
[2020-02-06T10:40:02.425-0300][303.073s][info ][gc] GC(113) Concurrent Reset 0.028ms
[2020-02-06T10:40:04.435-0300][305.082s][info ][gc] GC(114) Pause Initial Mark 30M->30M(30M) 9.380ms
[2020-02-06T10:40:04.435-0300][305.082s][info ][gc] GC(114) Concurrent Mark
[2020-02-06T10:40:04.473-0300][305.120s][debug][gc] GC(114) Concurrent active time: 37.727ms
[2020-02-06T10:40:04.473-0300][305.120s][info ][gc] GC(114) Concurrent Mark 37.918ms
[2020-02-06T10:40:04.473-0300][305.120s][info ][gc] GC(114) Concurrent Preclean
[2020-02-06T10:40:04.480-0300][305.127s][debug][gc] GC(114) Concurrent active time: 6.513ms
[2020-02-06T10:40:04.480-0300][305.127s][info ][gc] GC(114) Concurrent Preclean 6.588ms
[2020-02-06T10:40:04.480-0300][305.127s][info ][gc] GC(114) Concurrent Abortable Preclean
[2020-02-06T10:40:04.480-0300][305.127s][debug][gc] GC(114) Concurrent active time: 0.005ms
[2020-02-06T10:40:04.480-0300][305.127s][info ][gc] GC(114) Concurrent Abortable Preclean 0.036ms
[2020-02-06T10:40:04.480-0300][305.127s][debug][gc] GC(114) YG occupancy: 9178 K (9792 K)
[2020-02-06T10:40:04.489-0300][305.136s][info ][gc] GC(114) Pause Remark 30M->30M(30M) 9.140ms
[2020-02-06T10:40:04.489-0300][305.136s][info ][gc] GC(114) Concurrent Sweep
[2020-02-06T10:40:04.498-0300][305.145s][debug][gc] GC(114) Concurrent active time: 8.806ms
[2020-02-06T10:40:04.498-0300][305.145s][info ][gc] GC(114) Concurrent Sweep 8.885ms
[2020-02-06T10:40:04.498-0300][305.145s][info ][gc] GC(114) Concurrent Reset
[2020-02-06T10:40:04.498-0300][305.145s][debug][gc] GC(114) Concurrent active time: 0.016ms
[2020-02-06T10:40:04.498-0300][305.145s][info ][gc] GC(114) Concurrent Reset 0.044ms
[2020-02-06T10:40:06.511-0300][307.159s][info ][gc] GC(115) Pause Initial Mark 30M->30M(30M) 10.372ms
[2020-02-06T10:40:06.512-0300][307.159s][info ][gc] GC(115) Concurrent Mark
[2020-02-06T10:40:06.538-0300][307.185s][debug][gc] GC(115) Concurrent active time: 25.986ms
[2020-02-06T10:40:06.538-0300][307.185s][info ][gc] GC(115) Concurrent Mark 26.167ms
[2020-02-06T10:40:06.538-0300][307.185s][info ][gc] GC(115) Concurrent Preclean
[2020-02-06T10:40:06.542-0300][307.189s][debug][gc] GC(115) Concurrent active time: 4.171ms
[2020-02-06T10:40:06.542-0300][307.189s][info ][gc] GC(115) Concurrent Preclean 4.225ms
[2020-02-06T10:40:06.542-0300][307.189s][info ][gc] GC(115) Concurrent Abortable Preclean
[2020-02-06T10:40:06.542-0300][307.189s][debug][gc] GC(115) Concurrent active time: 0.004ms
[2020-02-06T10:40:06.542-0300][307.189s][info ][gc] GC(115) Concurrent Abortable Preclean 0.025ms
[2020-02-06T10:40:06.542-0300][307.190s][debug][gc] GC(115) YG occupancy: 9654 K (9792 K)
[2020-02-06T10:40:06.550-0300][307.197s][info ][gc] GC(115) Pause Remark 30M->30M(30M) 7.437ms
[2020-02-06T10:40:06.550-0300][307.197s][info ][gc] GC(115) Concurrent Sweep
[2020-02-06T10:40:06.557-0300][307.204s][debug][gc] GC(115) Concurrent active time: 7.146ms
[2020-02-06T10:40:06.557-0300][307.204s][info ][gc] GC(115) Concurrent Sweep 7.210ms
[2020-02-06T10:40:06.557-0300][307.204s][info ][gc] GC(115) Concurrent Reset
[2020-02-06T10:40:06.557-0300][307.204s][debug][gc] GC(115) Concurrent active time: 0.013ms
[2020-02-06T10:40:06.557-0300][307.204s][info ][gc] GC(115) Concurrent Reset 0.032ms
[2020-02-06T10:40:07.341-0300][307.989s][info ][gc] GC(116) Pause Young (Allocation Failure) 30M->30M(30M) 0.069ms
[2020-02-06T10:40:07.701-0300][308.348s][info ][gc] GC(117) Pause Full (Allocation Failure) 30M->29M(30M) 359.449ms
[2020-02-06T10:40:07.713-0300][308.360s][info ][gc] GC(118) Pause Initial Mark 29M->29M(30M) 8.356ms
[2020-02-06T10:40:07.713-0300][308.360s][info ][gc] GC(118) Concurrent Mark
[2020-02-06T10:40:07.745-0300][308.393s][debug][gc] GC(118) Concurrent active time: 32.263ms
[2020-02-06T10:40:07.745-0300][308.393s][info ][gc] GC(118) Concurrent Mark 32.377ms
[2020-02-06T10:40:07.745-0300][308.393s][info ][gc] GC(118) Concurrent Preclean
[2020-02-06T10:40:07.779-0300][308.426s][debug][gc] GC(118) Concurrent active time: 33.239ms
[2020-02-06T10:40:07.779-0300][308.426s][info ][gc] GC(118) Concurrent Preclean 33.338ms
[2020-02-06T10:40:07.779-0300][308.426s][info ][gc] GC(118) Concurrent Abortable Preclean
[2020-02-06T10:40:07.779-0300][308.426s][debug][gc] GC(118) Concurrent active time: 0.001ms
[2020-02-06T10:40:07.779-0300][308.426s][info ][gc] GC(118) Concurrent Abortable Preclean 0.038ms
[2020-02-06T10:40:07.779-0300][308.426s][debug][gc] GC(118) YG occupancy: 8848 K (9792 K)
[2020-02-06T10:40:07.794-0300][308.442s][info ][gc] GC(118) Pause Remark 30M->30M(30M) 15.299ms
[2020-02-06T10:40:07.795-0300][308.442s][info ][gc] GC(118) Concurrent Sweep
[2020-02-06T10:40:07.809-0300][308.456s][debug][gc] GC(118) Concurrent active time: 14.098ms
[2020-02-06T10:40:07.809-0300][308.456s][info ][gc] GC(118) Concurrent Sweep 14.202ms
[2020-02-06T10:40:07.809-0300][308.456s][info ][gc] GC(118) Concurrent Reset
[2020-02-06T10:40:07.809-0300][308.456s][debug][gc] GC(118) Concurrent active time: 0.014ms
[2020-02-06T10:40:07.809-0300][308.456s][info ][gc] GC(118) Concurrent Reset 0.059ms
[2020-02-06T10:40:09.822-0300][310.469s][info ][gc] GC(119) Pause Initial Mark 30M->30M(30M) 7.886ms
[2020-02-06T10:40:09.822-0300][310.469s][info ][gc] GC(119) Concurrent Mark
[2020-02-06T10:40:09.845-0300][310.493s][debug][gc] GC(119) Concurrent active time: 23.270ms
[2020-02-06T10:40:09.845-0300][310.493s][info ][gc] GC(119) Concurrent Mark 23.370ms
[2020-02-06T10:40:09.845-0300][310.493s][info ][gc] GC(119) Concurrent Preclean
[2020-02-06T10:40:09.850-0300][310.498s][debug][gc] GC(119) Concurrent active time: 4.936ms
[2020-02-06T10:40:09.850-0300][310.498s][info ][gc] GC(119) Concurrent Preclean 4.996ms
[2020-02-06T10:40:09.850-0300][310.498s][info ][gc] GC(119) Concurrent Abortable Preclean
[2020-02-06T10:40:09.850-0300][310.498s][debug][gc] GC(119) Concurrent active time: 0.005ms
[2020-02-06T10:40:09.850-0300][310.498s][info ][gc] GC(119) Concurrent Abortable Preclean 0.035ms
[2020-02-06T10:40:09.851-0300][310.498s][debug][gc] GC(119) YG occupancy: 9251 K (9792 K)
[2020-02-06T10:40:09.859-0300][310.506s][info ][gc] GC(119) Pause Remark 30M->30M(30M) 8.483ms
[2020-02-06T10:40:09.859-0300][310.507s][info ][gc] GC(119) Concurrent Sweep
[2020-02-06T10:40:09.866-0300][310.514s][debug][gc] GC(119) Concurrent active time: 7.047ms
[2020-02-06T10:40:09.866-0300][310.514s][info ][gc] GC(119) Concurrent Sweep 7.097ms
[2020-02-06T10:40:09.866-0300][310.514s][info ][gc] GC(119) Concurrent Reset
[2020-02-06T10:40:09.866-0300][310.514s][debug][gc] GC(119) Concurrent active time: 0.010ms
[2020-02-06T10:40:09.866-0300][310.514s][info ][gc] GC(119) Concurrent Reset 0.032ms
[2020-02-06T10:40:10.924-0300][311.571s][info ][gc] GC(120) Pause Young (Allocation Failure) 30M->30M(30M) 0.116ms
[2020-02-06T10:40:11.015-0300][311.662s][info ][gc] GC(121) Pause Full (Allocation Failure) 30M->29M(30M) 90.686ms
[2020-02-06T10:40:11.097-0300][311.744s][info ][gc] GC(122) Pause Full (Allocation Failure) 29M->29M(30M) 81.996ms
[2020-02-06T10:40:11.104-0300][311.751s][info ][gc] GC(123) Pause Initial Mark 29M->29M(30M) 6.670ms
[2020-02-06T10:40:11.105-0300][311.752s][info ][gc] GC(123) Concurrent Mark
[2020-02-06T10:40:11.134-0300][311.782s][debug][gc] GC(123) Concurrent active time: 29.624ms
[2020-02-06T10:40:11.134-0300][311.782s][info ][gc] GC(123) Concurrent Mark 29.848ms
[2020-02-06T10:40:11.134-0300][311.782s][info ][gc] GC(123) Concurrent Preclean
[2020-02-06T10:40:11.162-0300][311.809s][debug][gc] GC(123) Concurrent active time: 27.282ms
[2020-02-06T10:40:11.162-0300][311.809s][info ][gc] GC(123) Concurrent Preclean 27.518ms
[2020-02-06T10:40:11.162-0300][311.809s][info ][gc] GC(123) Concurrent Abortable Preclean
[2020-02-06T10:40:11.162-0300][311.809s][debug][gc] GC(123) Concurrent active time: 0.003ms
[2020-02-06T10:40:11.162-0300][311.809s][info ][gc] GC(123) Concurrent Abortable Preclean 0.039ms
[2020-02-06T10:40:11.162-0300][311.809s][debug][gc] GC(123) YG occupancy: 8911 K (9792 K)
[2020-02-06T10:40:11.175-0300][311.822s][info ][gc] GC(123) Pause Remark 30M->30M(30M) 12.835ms
[2020-02-06T10:40:11.175-0300][311.822s][info ][gc] GC(123) Concurrent Sweep
[2020-02-06T10:40:11.186-0300][311.833s][debug][gc] GC(123) Concurrent active time: 10.975ms
[2020-02-06T10:40:11.186-0300][311.834s][info ][gc] GC(123) Concurrent Sweep 11.185ms
[2020-02-06T10:40:11.186-0300][311.834s][info ][gc] GC(123) Concurrent Reset
[2020-02-06T10:40:11.187-0300][311.834s][debug][gc] GC(123) Concurrent active time: 0.072ms
[2020-02-06T10:40:11.187-0300][311.834s][info ][gc] GC(123) Concurrent Reset 0.133ms
```

</details>

| Тип сборки | Количество | Суммарное время сборки |
| ---------- | ---------- | ---------------------- |
| Young      | 16         | 155 ms (0,155 s)       |
| Full       | 107        | 53328 ms (53,328 s)    |

Объектов в массиве: 984 500 шт.

Общее время: 306 сек.


### G1 GC
<details>
  <summary>Конфигурация</summary>
  
```
-Xms32m
-Xmx32m
-XX:+UseG1GC
-Xlog:gc=debug:file=./hw03-gc/logs/gc-%p.log:tags,uptime,time,level
```

</details>
<details>
  <summary>Лог выполнения</summary>

```
[2020-02-06T10:42:59.174-0300][0.036s][debug][gc] ConcGCThreads: 1 offset 8
[2020-02-06T10:42:59.174-0300][0.036s][debug][gc] ParallelGCThreads: 4
[2020-02-06T10:42:59.174-0300][0.036s][debug][gc] Initialize mark stack with 4096 chunks, maximum 16384
[2020-02-06T10:42:59.175-0300][0.037s][info ][gc] Using G1
[2020-02-06T10:42:59.225-0300][0.088s][info ][gc] Periodic GC disabled
[2020-02-06T10:43:21.898-0300][22.759s][info ][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 14M->5M(32M) 17.396ms
[2020-02-06T10:43:42.709-0300][43.570s][info ][gc] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 14M->10M(32M) 21.458ms
[2020-02-06T10:44:03.242-0300][64.102s][info ][gc] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 18M->16M(32M) 12.338ms
[2020-02-06T10:44:13.118-0300][73.978s][info ][gc] GC(3) Pause Young (Concurrent Start) (G1 Humongous Allocation) 19M->18M(32M) 7.109ms
[2020-02-06T10:44:13.118-0300][73.978s][info ][gc] GC(4) Concurrent Cycle
[2020-02-06T10:44:13.142-0300][74.002s][debug][gc] GC(4) Reclaimed 1 empty regions
[2020-02-06T10:44:13.142-0300][74.002s][info ][gc] GC(4) Pause Remark 20M->19M(32M) 2.610ms
[2020-02-06T10:44:13.154-0300][74.015s][info ][gc] GC(4) Pause Cleanup 19M->19M(32M) 0.025ms
[2020-02-06T10:44:13.155-0300][74.015s][info ][gc] GC(4) Concurrent Cycle 36.521ms
[2020-02-06T10:44:23.632-0300][84.492s][info ][gc] GC(5) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 22M->21M(32M) 10.721ms
[2020-02-06T10:44:26.817-0300][87.676s][info ][gc] GC(6) Pause Young (Mixed) (G1 Evacuation Pause) 22M->18M(32M) 11.209ms
[2020-02-06T10:44:30.067-0300][90.927s][info ][gc] GC(7) Pause Young (Mixed) (G1 Evacuation Pause) 19M->18M(32M) 16.794ms
[2020-02-06T10:44:37.177-0300][98.037s][info ][gc] GC(8) Pause Young (Concurrent Start) (G1 Evacuation Pause) 20M->19M(32M) 5.746ms
[2020-02-06T10:44:37.178-0300][98.037s][info ][gc] GC(9) Concurrent Cycle
[2020-02-06T10:44:37.213-0300][98.073s][debug][gc] GC(9) Reclaimed 2 empty regions
[2020-02-06T10:44:37.213-0300][98.073s][info ][gc] GC(9) Pause Remark 19M->17M(32M) 1.982ms
[2020-02-06T10:44:37.227-0300][98.086s][info ][gc] GC(9) Pause Cleanup 17M->17M(32M) 0.030ms
[2020-02-06T10:44:37.227-0300][98.086s][info ][gc] GC(9) Concurrent Cycle 49.556ms
[2020-02-06T10:44:44.388-0300][105.247s][info ][gc] GC(10) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 19M->18M(32M) 4.301ms
[2020-02-06T10:44:47.830-0300][108.689s][info ][gc] GC(11) Pause Young (Mixed) (G1 Evacuation Pause) 19M->17M(32M) 12.790ms
[2020-02-06T10:44:53.193-0300][114.052s][info ][gc] GC(12) Pause Young (Concurrent Start) (G1 Humongous Allocation) 18M->18M(32M) 7.781ms
[2020-02-06T10:44:53.193-0300][114.052s][info ][gc] GC(13) Concurrent Cycle
[2020-02-06T10:44:53.235-0300][114.094s][info ][gc] GC(13) Pause Remark 21M->21M(32M) 1.300ms
[2020-02-06T10:44:53.252-0300][114.111s][info ][gc] GC(13) Pause Cleanup 21M->21M(32M) 0.029ms
[2020-02-06T10:44:53.253-0300][114.112s][info ][gc] GC(13) Concurrent Cycle 59.639ms
[2020-02-06T10:45:00.862-0300][121.720s][info ][gc] GC(14) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 23M->22M(32M) 7.545ms
[2020-02-06T10:45:04.207-0300][125.065s][info ][gc] GC(15) Pause Young (Mixed) (G1 Evacuation Pause) 23M->21M(32M) 8.703ms
[2020-02-06T10:45:07.528-0300][128.387s][info ][gc] GC(16) Pause Young (Concurrent Start) (G1 Evacuation Pause) 22M->22M(32M) 10.226ms
[2020-02-06T10:45:07.528-0300][128.387s][info ][gc] GC(17) Concurrent Cycle
[2020-02-06T10:45:07.577-0300][128.435s][debug][gc] GC(17) Reclaimed 2 empty regions
[2020-02-06T10:45:07.577-0300][128.435s][info ][gc] GC(17) Pause Remark 22M->20M(32M) 1.760ms
[2020-02-06T10:45:07.593-0300][128.452s][info ][gc] GC(17) Pause Cleanup 20M->20M(32M) 0.033ms
[2020-02-06T10:45:07.593-0300][128.452s][info ][gc] GC(17) Concurrent Cycle 65.230ms
[2020-02-06T10:45:11.200-0300][132.059s][info ][gc] GC(18) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 21M->21M(32M) 4.400ms
[2020-02-06T10:45:14.709-0300][135.568s][info ][gc] GC(19) Pause Young (Mixed) (G1 Evacuation Pause) 22M->19M(32M) 12.984ms
[2020-02-06T10:45:22.249-0300][143.107s][info ][gc] GC(20) Pause Young (Normal) (G1 Evacuation Pause) 21M->20M(32M) 7.932ms
[2020-02-06T10:45:29.390-0300][150.248s][info ][gc] GC(21) Pause Young (Normal) (G1 Evacuation Pause) 22M->22M(32M) 6.898ms
[2020-02-06T10:45:33.193-0300][154.051s][info ][gc] GC(22) Pause Young (Concurrent Start) (G1 Evacuation Pause) 23M->22M(32M) 7.055ms
[2020-02-06T10:45:33.193-0300][154.051s][info ][gc] GC(23) Concurrent Cycle
[2020-02-06T10:45:33.229-0300][154.086s][debug][gc] GC(23) Reclaimed 1 empty regions
[2020-02-06T10:45:33.229-0300][154.086s][info ][gc] GC(23) Pause Remark 22M->21M(32M) 2.084ms
[2020-02-06T10:45:33.246-0300][154.104s][info ][gc] GC(23) Pause Cleanup 21M->21M(32M) 0.028ms
[2020-02-06T10:45:33.246-0300][154.104s][info ][gc] GC(23) Concurrent Cycle 53.494ms
[2020-02-06T10:45:36.535-0300][157.393s][info ][gc] GC(24) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 22M->22M(32M) 6.335ms
[2020-02-06T10:45:39.982-0300][160.839s][info ][gc] GC(25) Pause Young (Mixed) (G1 Evacuation Pause) 23M->21M(32M) 10.252ms
[2020-02-06T10:45:43.774-0300][164.632s][info ][gc] GC(26) Pause Young (Normal) (G1 Evacuation Pause) 22M->22M(32M) 6.533ms
[2020-02-06T10:45:46.996-0300][167.854s][info ][gc] GC(27) Pause Young (Normal) (G1 Evacuation Pause) 23M->23M(32M) 5.642ms
[2020-02-06T10:45:50.536-0300][171.393s][info ][gc] GC(28) Pause Young (Concurrent Start) (G1 Evacuation Pause) 24M->23M(32M) 7.510ms
[2020-02-06T10:45:50.536-0300][171.393s][info ][gc] GC(29) Concurrent Cycle
[2020-02-06T10:45:50.585-0300][171.443s][info ][gc] GC(29) Pause Remark 23M->23M(32M) 1.353ms
[2020-02-06T10:45:50.606-0300][171.463s][info ][gc] GC(29) Pause Cleanup 23M->23M(32M) 0.028ms
[2020-02-06T10:45:50.606-0300][171.463s][info ][gc] GC(29) Concurrent Cycle 69.702ms
[2020-02-06T10:45:54.113-0300][174.970s][info ][gc] GC(30) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 24M->24M(32M) 6.070ms
[2020-02-06T10:45:57.847-0300][178.704s][info ][gc] GC(31) To-space exhausted
[2020-02-06T10:45:57.847-0300][178.704s][info ][gc] GC(31) Pause Young (Mixed) (G1 Evacuation Pause) 29M->29M(32M) 15.782ms
[2020-02-06T10:46:01.341-0300][182.198s][info ][gc] GC(32) To-space exhausted
[2020-02-06T10:46:01.341-0300][182.198s][info ][gc] GC(32) Pause Young (Concurrent Start) (G1 Evacuation Pause) 30M->30M(32M) 6.856ms
[2020-02-06T10:46:01.341-0300][182.198s][info ][gc] GC(33) Concurrent Cycle
[2020-02-06T10:46:01.344-0300][182.201s][debug][gc] GC(34) Clear Next Bitmap 0.053ms
[2020-02-06T10:46:01.377-0300][182.234s][info ][gc] GC(34) Pause Full (G1 Evacuation Pause) 30M->22M(32M) 32.889ms
[2020-02-06T10:46:01.377-0300][182.234s][info ][gc] GC(33) Concurrent Cycle 36.100ms
[2020-02-06T10:46:03.015-0300][183.872s][info ][gc] GC(35) Pause Young (Normal) (G1 Evacuation Pause) 23M->22M(32M) 4.799ms
[2020-02-06T10:46:06.483-0300][187.340s][info ][gc] GC(36) Pause Young (Concurrent Start) (G1 Evacuation Pause) 23M->23M(32M) 5.291ms
[2020-02-06T10:46:06.484-0300][187.340s][info ][gc] GC(37) Concurrent Cycle
[2020-02-06T10:46:06.546-0300][187.402s][info ][gc] GC(37) Pause Remark 23M->23M(32M) 1.342ms
[2020-02-06T10:46:06.568-0300][187.425s][info ][gc] GC(37) Pause Cleanup 23M->23M(32M) 0.032ms
[2020-02-06T10:46:06.568-0300][187.425s][info ][gc] GC(37) Concurrent Cycle 84.560ms
[2020-02-06T10:46:10.529-0300][191.386s][info ][gc] GC(38) Pause Young (Normal) (G1 Evacuation Pause) 24M->23M(32M) 9.275ms
[2020-02-06T10:46:13.966-0300][194.823s][info ][gc] GC(39) Pause Young (Concurrent Start) (G1 Evacuation Pause) 24M->24M(32M) 7.289ms
[2020-02-06T10:46:13.968-0300][194.825s][info ][gc] GC(40) Concurrent Cycle
[2020-02-06T10:46:14.029-0300][194.885s][info ][gc] GC(40) Pause Remark 24M->24M(32M) 3.253ms
[2020-02-06T10:46:14.054-0300][194.911s][info ][gc] GC(40) Pause Cleanup 24M->24M(32M) 0.036ms
[2020-02-06T10:46:14.054-0300][194.911s][info ][gc] GC(40) Concurrent Cycle 86.016ms
[2020-02-06T10:46:17.723-0300][198.579s][info ][gc] GC(41) Pause Young (Normal) (G1 Evacuation Pause) 25M->24M(32M) 6.182ms
[2020-02-06T10:46:21.056-0300][201.912s][info ][gc] GC(42) Pause Young (Concurrent Start) (G1 Evacuation Pause) 25M->25M(32M) 4.177ms
[2020-02-06T10:46:21.056-0300][201.912s][info ][gc] GC(43) Concurrent Cycle
[2020-02-06T10:46:21.098-0300][201.954s][info ][gc] GC(43) Pause Remark 25M->25M(32M) 1.058ms
[2020-02-06T10:46:21.120-0300][201.976s][info ][gc] GC(43) Pause Cleanup 25M->25M(32M) 0.026ms
[2020-02-06T10:46:21.120-0300][201.976s][info ][gc] GC(43) Concurrent Cycle 64.061ms
[2020-02-06T10:46:24.314-0300][205.171s][info ][gc] GC(44) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 26M->26M(32M) 4.331ms
[2020-02-06T10:46:28.372-0300][209.228s][info ][gc] GC(45) To-space exhausted
[2020-02-06T10:46:28.374-0300][209.230s][info ][gc] GC(45) Pause Young (Mixed) (G1 Evacuation Pause) 27M->28M(32M) 16.785ms
[2020-02-06T10:46:31.602-0300][212.458s][info ][gc] GC(46) To-space exhausted
[2020-02-06T10:46:31.603-0300][212.458s][info ][gc] GC(46) Pause Young (Concurrent Start) (G1 Evacuation Pause) 29M->29M(32M) 12.937ms
[2020-02-06T10:46:31.603-0300][212.459s][info ][gc] GC(48) Concurrent Cycle
[2020-02-06T10:46:31.603-0300][212.459s][debug][gc] GC(47) Clear Next Bitmap 0.076ms
[2020-02-06T10:46:31.638-0300][212.494s][info ][gc] GC(47) Pause Full (G1 Evacuation Pause) 29M->24M(32M) 35.074ms
[2020-02-06T10:46:31.638-0300][212.494s][info ][gc] GC(48) Concurrent Cycle 35.199ms
[2020-02-06T10:46:33.566-0300][214.422s][info ][gc] GC(49) Pause Young (Normal) (G1 Evacuation Pause) 25M->24M(32M) 5.621ms
[2020-02-06T10:46:36.934-0300][217.790s][info ][gc] GC(50) Pause Young (Concurrent Start) (G1 Evacuation Pause) 25M->25M(32M) 6.583ms
[2020-02-06T10:46:36.934-0300][217.790s][info ][gc] GC(51) Concurrent Cycle
[2020-02-06T10:46:37.001-0300][217.856s][info ][gc] GC(51) Pause Remark 25M->25M(32M) 1.315ms
[2020-02-06T10:46:37.026-0300][217.882s][info ][gc] GC(51) Pause Cleanup 25M->25M(32M) 0.046ms
[2020-02-06T10:46:37.026-0300][217.882s][info ][gc] GC(51) Concurrent Cycle 91.742ms
[2020-02-06T10:46:40.733-0300][221.589s][info ][gc] GC(52) Pause Young (Normal) (G1 Evacuation Pause) 26M->26M(32M) 8.548ms
[2020-02-06T10:46:44.162-0300][225.017s][info ][gc] GC(53) Pause Young (Concurrent Start) (G1 Evacuation Pause) 27M->26M(32M) 7.075ms
[2020-02-06T10:46:44.162-0300][225.017s][info ][gc] GC(54) Concurrent Cycle
[2020-02-06T10:46:44.226-0300][225.082s][info ][gc] GC(54) Pause Remark 26M->26M(32M) 1.579ms
[2020-02-06T10:46:44.252-0300][225.108s][info ][gc] GC(54) Pause Cleanup 26M->26M(32M) 0.032ms
[2020-02-06T10:46:44.252-0300][225.108s][info ][gc] GC(54) Concurrent Cycle 90.536ms
[2020-02-06T10:46:48.082-0300][228.937s][info ][gc] GC(55) Pause Young (Normal) (G1 Evacuation Pause) 27M->27M(32M) 5.134ms
[2020-02-06T10:46:51.685-0300][232.540s][info ][gc] GC(56) Pause Young (Concurrent Start) (G1 Evacuation Pause) 28M->27M(32M) 7.848ms
[2020-02-06T10:46:51.685-0300][232.540s][info ][gc] GC(57) Concurrent Cycle
[2020-02-06T10:46:51.750-0300][232.606s][info ][gc] GC(57) Pause Remark 27M->27M(32M) 1.278ms
[2020-02-06T10:46:51.777-0300][232.632s][info ][gc] GC(57) Pause Cleanup 27M->27M(32M) 0.040ms
[2020-02-06T10:46:51.777-0300][232.632s][info ][gc] GC(57) Concurrent Cycle 92.120ms
[2020-02-06T10:46:55.283-0300][236.139s][info ][gc] GC(58) Pause Young (Normal) (G1 Evacuation Pause) 28M->28M(32M) 4.212ms
[2020-02-06T10:46:59.496-0300][240.351s][info ][gc] GC(59) To-space exhausted
[2020-02-06T10:46:59.496-0300][240.351s][info ][gc] GC(59) Pause Young (Concurrent Start) (G1 Evacuation Pause) 29M->29M(32M) 9.675ms
[2020-02-06T10:46:59.496-0300][240.351s][info ][gc] GC(60) Concurrent Cycle
[2020-02-06T10:46:59.574-0300][240.429s][info ][gc] GC(60) Pause Remark 29M->29M(32M) 1.363ms
[2020-02-06T10:46:59.602-0300][240.457s][info ][gc] GC(60) Pause Cleanup 29M->29M(32M) 0.038ms
[2020-02-06T10:46:59.602-0300][240.457s][info ][gc] GC(60) Concurrent Cycle 106.048ms
[2020-02-06T10:47:03.565-0300][244.420s][info ][gc] GC(61) To-space exhausted
[2020-02-06T10:47:03.566-0300][244.421s][info ][gc] GC(61) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 30M->30M(32M) 10.293ms
[2020-02-06T10:47:03.642-0300][244.497s][info ][gc] GC(62) Pause Full (G1 Evacuation Pause) 30M->26M(32M) 76.572ms
[2020-02-06T10:47:05.333-0300][246.188s][info ][gc] GC(63) Pause Young (Concurrent Start) (G1 Evacuation Pause) 27M->27M(32M) 5.637ms
[2020-02-06T10:47:05.333-0300][246.188s][info ][gc] GC(64) Concurrent Cycle
[2020-02-06T10:47:05.400-0300][246.255s][info ][gc] GC(64) Pause Remark 27M->27M(32M) 1.299ms
[2020-02-06T10:47:05.428-0300][246.283s][info ][gc] GC(64) Pause Cleanup 27M->27M(32M) 0.033ms
[2020-02-06T10:47:05.428-0300][246.283s][info ][gc] GC(64) Concurrent Cycle 95.206ms
[2020-02-06T10:47:11.916-0300][252.770s][info ][gc] GC(65) To-space exhausted
[2020-02-06T10:47:11.916-0300][252.770s][info ][gc] GC(65) Pause Young (Normal) (G1 Evacuation Pause) 28M->29M(32M) 8.968ms
[2020-02-06T10:47:11.957-0300][252.812s][info ][gc] GC(66) Pause Full (G1 Evacuation Pause) 29M->27M(32M) 41.676ms
[2020-02-06T10:47:12.537-0300][253.392s][info ][gc] GC(67) Pause Young (Concurrent Start) (G1 Evacuation Pause) 28M->27M(32M) 8.878ms
[2020-02-06T10:47:12.537-0300][253.392s][info ][gc] GC(68) Concurrent Cycle
[2020-02-06T10:47:12.624-0300][253.479s][info ][gc] GC(68) Pause Remark 27M->27M(32M) 1.774ms
[2020-02-06T10:47:12.658-0300][253.513s][info ][gc] GC(68) Pause Cleanup 27M->27M(32M) 0.030ms
[2020-02-06T10:47:12.659-0300][253.514s][info ][gc] GC(68) Concurrent Cycle 121.430ms
[2020-02-06T10:47:16.618-0300][257.472s][info ][gc] GC(69) Pause Young (Normal) (G1 Evacuation Pause) 28M->27M(32M) 7.817ms
[2020-02-06T10:47:20.520-0300][261.374s][info ][gc] GC(70) To-space exhausted
[2020-02-06T10:47:20.520-0300][261.374s][info ][gc] GC(70) Pause Young (Concurrent Start) (G1 Evacuation Pause) 28M->28M(32M) 8.670ms
[2020-02-06T10:47:20.520-0300][261.374s][info ][gc] GC(71) Concurrent Cycle
[2020-02-06T10:47:20.591-0300][261.445s][info ][gc] GC(71) Pause Remark 29M->29M(32M) 1.357ms
[2020-02-06T10:47:20.622-0300][261.477s][info ][gc] GC(71) Pause Cleanup 29M->29M(32M) 0.038ms
[2020-02-06T10:47:20.622-0300][261.477s][info ][gc] GC(71) Concurrent Cycle 102.789ms
[2020-02-06T10:47:24.598-0300][265.452s][info ][gc] GC(72) To-space exhausted
[2020-02-06T10:47:24.598-0300][265.452s][info ][gc] GC(72) Pause Young (Normal) (G1 Evacuation Pause) 29M->29M(32M) 7.077ms
[2020-02-06T10:47:24.659-0300][265.514s][info ][gc] GC(73) Pause Full (G1 Evacuation Pause) 29M->28M(32M) 61.404ms
[2020-02-06T10:47:26.402-0300][267.256s][info ][gc] GC(74) Pause Young (Concurrent Start) (G1 Evacuation Pause) 29M->28M(32M) 5.833ms
[2020-02-06T10:47:26.402-0300][267.256s][info ][gc] GC(75) Concurrent Cycle
[2020-02-06T10:47:26.484-0300][267.338s][info ][gc] GC(75) Pause Remark 28M->28M(32M) 2.509ms
[2020-02-06T10:47:26.529-0300][267.384s][info ][gc] GC(75) Pause Cleanup 28M->28M(32M) 0.128ms
[2020-02-06T10:47:26.530-0300][267.384s][info ][gc] GC(75) Concurrent Cycle 127.893ms
[2020-02-06T10:47:30.272-0300][271.126s][info ][gc] GC(76) To-space exhausted
[2020-02-06T10:47:30.272-0300][271.126s][info ][gc] GC(76) Pause Young (Normal) (G1 Evacuation Pause) 29M->29M(32M) 6.709ms
[2020-02-06T10:47:30.313-0300][271.167s][info ][gc] GC(77) Pause Full (G1 Evacuation Pause) 29M->28M(32M) 40.887ms
[2020-02-06T10:47:32.248-0300][273.102s][info ][gc] GC(78) To-space exhausted
[2020-02-06T10:47:32.248-0300][273.102s][info ][gc] GC(78) Pause Young (Concurrent Start) (G1 Evacuation Pause) 29M->29M(32M) 7.709ms
[2020-02-06T10:47:32.248-0300][273.102s][info ][gc] GC(80) Concurrent Cycle
[2020-02-06T10:47:32.248-0300][273.102s][debug][gc] GC(79) Clear Next Bitmap 0.058ms
[2020-02-06T10:47:32.294-0300][273.148s][info ][gc] GC(79) Pause Full (G1 Evacuation Pause) 29M->28M(32M) 46.039ms
[2020-02-06T10:47:32.294-0300][273.148s][info ][gc] GC(80) Concurrent Cycle 46.180ms
[2020-02-06T10:47:36.511-0300][277.365s][info ][gc] GC(81) Pause Young (Normal) (G1 Evacuation Pause) 29M->29M(32M) 6.408ms
[2020-02-06T10:47:40.359-0300][281.213s][info ][gc] GC(82) To-space exhausted
[2020-02-06T10:47:40.359-0300][281.213s][info ][gc] GC(82) Pause Young (Concurrent Start) (G1 Evacuation Pause) 30M->30M(32M) 9.044ms
[2020-02-06T10:47:40.359-0300][281.213s][info ][gc] GC(84) Concurrent Cycle
[2020-02-06T10:47:40.360-0300][281.213s][debug][gc] GC(83) Clear Next Bitmap 0.072ms
[2020-02-06T10:47:40.418-0300][281.272s][info ][gc] GC(83) Pause Full (G1 Evacuation Pause) 30M->28M(32M) 58.293ms
[2020-02-06T10:47:40.418-0300][281.272s][info ][gc] GC(84) Concurrent Cycle 58.408ms
[2020-02-06T10:47:43.694-0300][284.548s][info ][gc] GC(85) To-space exhausted
[2020-02-06T10:47:43.695-0300][284.548s][info ][gc] GC(85) Pause Young (Normal) (G1 Evacuation Pause) 29M->29M(32M) 10.810ms
[2020-02-06T10:47:43.746-0300][284.600s][info ][gc] GC(86) Pause Full (G1 Evacuation Pause) 29M->29M(32M) 51.555ms
[2020-02-06T10:47:45.686-0300][286.540s][info ][gc] GC(87) To-space exhausted
[2020-02-06T10:47:45.686-0300][286.540s][info ][gc] GC(87) Pause Young (Concurrent Start) (G1 Evacuation Pause) 30M->30M(32M) 6.773ms
[2020-02-06T10:47:45.686-0300][286.540s][info ][gc] GC(89) Concurrent Cycle
[2020-02-06T10:47:45.686-0300][286.540s][debug][gc] GC(88) Clear Next Bitmap 0.067ms
[2020-02-06T10:47:45.750-0300][286.604s][info ][gc] GC(88) Pause Full (G1 Evacuation Pause) 30M->29M(32M) 63.580ms
[2020-02-06T10:47:45.770-0300][286.624s][info ][gc] GC(89) Concurrent Cycle 83.729ms
[2020-02-06T10:47:47.672-0300][288.526s][info ][gc] GC(90) To-space exhausted
[2020-02-06T10:47:47.672-0300][288.526s][info ][gc] GC(90) Pause Young (Normal) (G1 Evacuation Pause) 30M->30M(32M) 8.256ms
[2020-02-06T10:47:47.718-0300][288.571s][info ][gc] GC(91) Pause Full (G1 Evacuation Pause) 30M->29M(32M) 45.442ms
[2020-02-06T10:47:49.761-0300][290.614s][info ][gc] GC(92) To-space exhausted
[2020-02-06T10:47:49.761-0300][290.615s][info ][gc] GC(92) Pause Young (Concurrent Start) (G1 Evacuation Pause) 30M->30M(32M) 9.556ms
[2020-02-06T10:47:49.761-0300][290.615s][info ][gc] GC(94) Concurrent Cycle
[2020-02-06T10:47:49.761-0300][290.615s][debug][gc] GC(93) Clear Next Bitmap 0.109ms
[2020-02-06T10:47:49.809-0300][290.662s][info ][gc] GC(93) Pause Full (G1 Evacuation Pause) 30M->29M(32M) 47.713ms
[2020-02-06T10:47:49.809-0300][290.663s][info ][gc] GC(94) Concurrent Cycle 48.037ms
[2020-02-06T10:47:51.154-0300][292.007s][info ][gc] GC(95) To-space exhausted
[2020-02-06T10:47:51.154-0300][292.008s][info ][gc] GC(95) Pause Young (Normal) (G1 Evacuation Pause) 30M->30M(32M) 7.180ms
[2020-02-06T10:47:51.199-0300][292.053s][info ][gc] GC(96) Pause Full (G1 Evacuation Pause) 30M->29M(32M) 45.369ms
[2020-02-06T10:47:52.957-0300][293.811s][info ][gc] GC(97) To-space exhausted
[2020-02-06T10:47:52.957-0300][293.811s][info ][gc] GC(97) Pause Young (Concurrent Start) (G1 Evacuation Pause) 30M->30M(32M) 7.717ms
[2020-02-06T10:47:52.957-0300][293.811s][info ][gc] GC(99) Concurrent Cycle
[2020-02-06T10:47:52.957-0300][293.811s][debug][gc] GC(98) Clear Next Bitmap 0.052ms
[2020-02-06T10:47:53.008-0300][293.862s][info ][gc] GC(98) Pause Full (G1 Evacuation Pause) 30M->29M(32M) 50.834ms
[2020-02-06T10:47:53.054-0300][293.908s][info ][gc] GC(100) Pause Full (G1 Evacuation Pause) 29M->29M(32M) 46.209ms
[2020-02-06T10:47:53.055-0300][293.908s][info ][gc] GC(99) Concurrent Cycle 97.262ms
[2020-02-06T10:47:53.055-0300][293.908s][info ][gc] GC(101) Pause Young (Normal) (G1 Evacuation Pause) 29M->29M(32M) 0.248ms
[2020-02-06T10:47:53.096-0300][293.949s][info ][gc] GC(102) Pause Full (G1 Evacuation Pause) 29M->29M(32M) 40.947ms
[2020-02-06T10:47:53.136-0300][293.990s][info ][gc] GC(103) Pause Full (G1 Evacuation Pause) 29M->29M(32M) 40.250ms
[2020-02-06T10:47:53.137-0300][293.990s][info ][gc] GC(104) Pause Young (Concurrent Start) (G1 Evacuation Pause) 29M->29M(32M) 0.332ms
[2020-02-06T10:47:53.137-0300][293.990s][info ][gc] GC(106) Concurrent Cycle
[2020-02-06T10:47:53.137-0300][293.990s][debug][gc] GC(105) Clear Next Bitmap 0.053ms
[2020-02-06T10:47:53.177-0300][294.031s][info ][gc] GC(105) Pause Full (G1 Evacuation Pause) 29M->29M(32M) 40.381ms
[2020-02-06T10:47:53.218-0300][294.072s][info ][gc] GC(107) Pause Full (G1 Evacuation Pause) 29M->29M(32M) 40.913ms
[2020-02-06T10:47:53.218-0300][294.072s][info ][gc] GC(106) Concurrent Cycle 81.473ms
[2020-02-06T10:47:53.218-0300][294.072s][info ][gc] GC(108) Pause Young (Normal) (G1 Evacuation Pause) 29M->29M(32M) 0.205ms
[2020-02-06T10:47:53.226-0300][294.080s][info ][gc] GC(109) Pause Full (G1 Evacuation Pause) 29M->3M(32M) 7.475ms
```

</details>

| Тип сборки | Количество | Суммарное время сборки |
| ---------- | ---------- | ---------------------- |
| Young      | 65         | 526 ms (0,526 s)       |
| Full       | 20         | 918 ms (0,918 s)       |

Объектов в массиве: 979 000 шт.

Общее время: 288 сек.

### Сводная таблица
<table>
    <tr>
        <th>Сборщик</th>
        <th>Время работы</th>
        <th>Объектов в массиве</th>
        <th>Тип сборки</th>
        <th>Количество</th>
        <th>Суммарное время сборки</th>
    </tr>
    <tr>
        <td rowspan=2>Serial</td>
        <td rowspan=2>286 сек.</td>
        <td rowspan=2>984 500 шт.</td>
        <td>Young</td>
        <td>6</td>
        <td>57 ms (0,057 s)</td>
    </tr>
    <tr>
        <td>Old</td>
        <td>9</td>
        <td>515 ms (0,515 s)</td>
    </tr>
    <tr>
        <td rowspan=2>Parallel</td>
        <td rowspan=2>291 сек.</td>
        <td rowspan=2>984 500 шт.</td>
        <td>Young</td>
        <td>4</td>
        <td>68 ms (0,068 s)</td>
    </tr>
    <tr>
        <td>Old</td>
        <td>14</td>
        <td>859 ms (0,859 s)</td>
    </tr>
    <tr>
        <td rowspan=2>CMS</td>
        <td rowspan=2>306 сек.</td>
        <td rowspan=2>984 500 шт.</td>
        <td>Young</td>
        <td>16</td>
        <td>155 ms (0,155 s)</td>
    </tr>
    <tr>
        <td>Old</td>
        <td>107</td>
        <td>53328 ms (53,328 s)</td>
    </tr>
    <tr>
        <td rowspan=2>G1</td>
        <td rowspan=2>288 сек.</td>
        <td rowspan=2>979 000 шт.</td>
        <td>Young</td>
        <td>65</td>
        <td>526 ms (0,526 s)</td>
    </tr>
    <tr>
        <td>Old</td>
        <td>20</td>
        <td>918 ms (0,918 s)</td>
    </tr>
</table>​

## Выводы
Из полученных результатов видно, что для данной задачи наиболее подходящим сборщиком является Serial, так как он имеет
лучшие показатели по всем метрикам: Наименьшее время выполнения программы, наименьшее количество сборок и наименьшее
время затраченное на сборки.
