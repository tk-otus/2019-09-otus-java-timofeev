package ru.nspk.osks.myjson.classes;

public class Address {
    private String description;
    private String address;

    public Address(String description, String address) {
        this.description = description;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
