package ru.nspk.osks.myjson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String firstName;
    private int age;
    private List<PhoneNumber> numbers = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();

    public User(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    public User(String firstName, int age, List<PhoneNumber> numbers, List<Address> addresses) {
        this.firstName = firstName;
        this.age = age;
        this.numbers = numbers;
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        numbers.add(phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", numbers=" + numbers +
                ", addresses=" + addresses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(numbers, user.numbers) &&
                Objects.equals(addresses, user.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, age, numbers, addresses);
    }
}
