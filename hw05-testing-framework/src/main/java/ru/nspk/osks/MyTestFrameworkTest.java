package ru.nspk.osks;

import ru.nspk.osks.testing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyTestFrameworkTest {
    File file;

    @Before public void createFile() throws IOException {
        file = new File("file.txt");
        file.createNewFile();
    }

    @Test public void testFileExist() throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("File not found!");
        }
        System.out.println("File exist!");
    }

    @Test public void testCanWriteToFile() throws IOException {
        if (!file.canWrite()) {
            throw new IOException("Can't write to file!");
        }
        System.out.println("Can write to file!");
    }

    @Test public void testCanReadFromFile() throws IOException {
        if (!file.canRead()) {
            throw new IOException("Can't read file!");
        }
        System.out.println("Can read from file!");
    }

    @Test public void testErrorOutput() {
        throw new RuntimeException("Runtime error!");
    }

    @Test public void testDivisionByZero() {
        float result = 1 / 0;
    }

    @After public void deleteFile() {
        file.delete();
    }
}
