package ru.nspk.osks.learn.json.gson;

import java.util.Objects;

public class User {
    private String firstName;
    private int age;
    private String streetAddress;

    public User(String firstName, int age, String streetAddress) {
        System.out.println("New User was created");
        this.firstName = firstName;
        this.age = age;
        this.streetAddress = streetAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getSomeInfo() {
        return "Some REST API info";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(streetAddress, user.streetAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, age, streetAddress);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", streetAddress='" + streetAddress + '\'' +
                '}';
    }
}
