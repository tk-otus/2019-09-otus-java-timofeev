package ru.nspk.osks.learn.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class IODemo {
    private static final String personFile = "person.bin";
    private static final String resourcesPath = "hw08-json-object-writer/src/main/resources/";
    private static final String textFile = "textFile.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Current dir: " + System.getProperty("user.dir"));
        writeTextFile();
        readTextFile();
        copyFile(resourcesPath + textFile, resourcesPath + "copiedFile.txt");
        zipFile(resourcesPath + textFile, resourcesPath + "zipFile.zip");
        writeObject();
        readObject();
    }

    private static void writeTextFile() throws IOException {
        try (
                var bufferedWriter = new BufferedWriter(new FileWriter(resourcesPath + textFile))
        ) {
            bufferedWriter.write("Hello, Java!");
            bufferedWriter.newLine();
            bufferedWriter.write("This is two line");
            bufferedWriter.newLine();
        }
    }

    private static void readTextFile() throws IOException {
        try (
                var bufferedReader = new BufferedReader(new FileReader(resourcesPath + textFile))
        ) {
            var line = bufferedReader.readLine();
            System.out.println("Text from readed file:");
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        }
    }

    private static void copyFile(String src, String dest) throws IOException {
        try (
                var bufferedInputStream = new BufferedInputStream(new FileInputStream(src));
                var bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest))
        ) {
            int size;
            byte[] buffer = new byte[2];
            while ((size = bufferedInputStream.read(buffer, 0, buffer.length)) > 0) {
                bufferedOutputStream.write(buffer, 0, size);
            }
            bufferedOutputStream.close();
        }
    }

    private static void zipFile(String src, String dest) throws IOException {
        try (
                var bufferedInputStream = new BufferedInputStream(new FileInputStream(src));
                var zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(dest)))
        ) {
            ZipEntry zipEntry = new ZipEntry(src);
            zipOut.putNextEntry(zipEntry);
            int size;
            byte[] buffer = new byte[2];
            while ((size = bufferedInputStream.read(buffer, 0, buffer.length)) > 0) {
                zipOut.write(buffer, 0, size);
            }
            zipOut.close();
        }
    }

    private static void writeObject() throws IOException {
        try (
                var fileOutputStream = new FileOutputStream(resourcesPath + personFile);
                var objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            var person = new Person(92, "SerialPerson", "hidden");
            System.out.println("Writed object: " + person);
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();
        }
    }

    private static void readObject() throws IOException, ClassNotFoundException {
        try (
                var fileInputStream = new FileInputStream(resourcesPath + personFile);
                var objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            var person = (Person) objectInputStream.readObject();
            System.out.println("Readed object: " + person);
        }
    }
}
