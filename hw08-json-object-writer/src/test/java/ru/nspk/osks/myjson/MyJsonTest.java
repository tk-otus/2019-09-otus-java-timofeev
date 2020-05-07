package ru.nspk.osks.myjson;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyJsonTest {
    private final Gson gson = new Gson();
    private final MyJson myJson = new MyJson();

    @Test
    public void testGetJsonFromArray() {
        String[] stringArr = {"arr1", "arr2"};
        int[] intArr = {1, 2, 3};
        boolean[] boolArr = {true, false, true};

        assertEquals(gson.toJson(stringArr), myJson.toJson(stringArr));
        assertEquals(gson.toJson(boolArr), myJson.toJson(boolArr));
        assertEquals(gson.toJson(intArr), myJson.toJson(intArr));
    }

    @Test
    public void testGetJsonForSimpleClass() {
        SimpleClass simpleClass = new SimpleClass();
        assertEquals(gson.toJson(simpleClass), myJson.toJson(simpleClass));
    }

    @Test
    public void testGetJsonForUserClass() {
        List<PhoneNumber> numbers = new ArrayList<>() {
            {
                add(new PhoneNumber("Home", "222-22-222"));
                add(new PhoneNumber("work", "555-55-555"));
            }
        };
        List<Address> addresses = new ArrayList<>() {
            {
                add(new Address("Home", "Home Street 1"));
                add(new Address("Work", "Work Street 2"));
            }
        };
        User user = new User("Bob", 28, numbers, addresses);

        assertEquals(gson.toJson(user), myJson.toJson(user));
    }

    @Test
    public void tetGetJsonForNullClass() {
        assertEquals(gson.toJson(null), myJson.toJson(null));
    }
}
