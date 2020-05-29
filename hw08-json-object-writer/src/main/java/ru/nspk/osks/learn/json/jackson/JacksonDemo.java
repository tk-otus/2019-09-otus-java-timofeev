package ru.nspk.osks.learn.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JacksonDemo {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final User user = new User("Jackson", 28, "Important Data!");

    public static void main(String[] args) throws IOException {
        final String jsonFile = "hw08-json-object-writer/src/main/resources/jacksonUser.json";
        JacksonDemo jacksonDemo = new JacksonDemo();

        jacksonDemo.save(jsonFile);

        System.out.println("==========");
        jacksonDemo.loadUser(jsonFile);

        System.out.println("==========");
        var userAsString = jacksonDemo.writeAsString();
        System.out.println("User as string: " + userAsString);

        System.out.println("==========");
        var userFromString = jacksonDemo.readFromString(userAsString);
        System.out.println("User from string: " + userFromString);

        System.out.println("==========");
        var propertyValue = jacksonDemo.readPropValue(userAsString, "first_name");
        System.out.println("Prop: " + propertyValue);
    }

    private void save(String filePath) throws IOException {
        var file = new File(filePath);
        objectMapper.writeValue(file, user);
        System.out.println("User: " + user);
        System.out.println("User saved to the file: " + file.getAbsolutePath());
    }

    private void loadUser(String filePath) throws IOException {
        var file = new File(filePath);
        var userLoaded = objectMapper.readValue(file, User.class);
        System.out.println("User: " + userLoaded);
        System.out.println("User loaded from the file: " + file.getAbsolutePath());
    }

    private String writeAsString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(user);
    }

    private User readFromString(String userAsString) throws JsonProcessingException {
        return objectMapper.readValue(userAsString, User.class);
    }

    private String readPropValue(String userAsString, String nameForSerialization) throws JsonProcessingException {
        JsonNode node = objectMapper.readTree(userAsString);
        JsonNode jsonProp = node.get(nameForSerialization);
        return jsonProp.textValue();
    }
}
