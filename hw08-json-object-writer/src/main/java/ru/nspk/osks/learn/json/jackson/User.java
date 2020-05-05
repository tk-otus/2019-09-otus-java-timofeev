package ru.nspk.osks.learn.json.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"transientProperty"})
public class User {
    private int age;
    @JsonProperty("first_name")
    private String firstName;
    private String transientProperty;

    @JsonCreator
    public User(@JsonProperty("age") int age, @JsonProperty("first_name") String firstName) {
        System.out.println("User was created by JsonCreator constructor");
        this.age = age;
        this.firstName = firstName;
    }

    public User(String firstName, int age, String transientProperty) {
        System.out.println("User was created by default constructor");
        this.firstName = firstName;
        this.age = age;
        this.transientProperty = transientProperty;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTransientProperty() {
        return transientProperty;
    }

    public void setTransientProperty(String transientProperty) {
        this.transientProperty = transientProperty;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", transientProperty='" + transientProperty + '\'' +
                '}';
    }
}
