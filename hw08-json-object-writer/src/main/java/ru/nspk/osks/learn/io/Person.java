package ru.nspk.osks.learn.io;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int age;
    private final String name;
    private transient final String hidden;

    public Person(int age, String name, String hidden) {
        System.out.println("New Person was created");
        this.age = age;
        this.name = name;
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", hidden='" + hidden + '\'' +
                '}';
    }
}
