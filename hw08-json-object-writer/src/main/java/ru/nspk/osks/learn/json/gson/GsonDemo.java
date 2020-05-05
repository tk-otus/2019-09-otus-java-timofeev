package ru.nspk.osks.learn.json.gson;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;

public class GsonDemo {
    private static final String jsonFile = "hw08-json-object-writer/src/main/resources/example.json";
    private static final String jsonFile2 = "hw08-json-object-writer/src/main/resources/example_2.json";

    public static void main(String[] args) throws IOException {
        tryGson();
        System.out.println("=======");
        gsonWithJavaClass();
        System.out.println("=======");
        gsonWithNonMatchingFieldNames();
    }

    private static void tryGson() {
        Gson gson = new Gson();
        Primitives primitives1 = new Primitives();
        System.out.println("Object #1: " + primitives1);

        String json = gson.toJson(primitives1);
        System.out.println("Json: " + json);

        Primitives primitives2 = gson.fromJson(json, Primitives.class);
        System.out.println("Object #2: " + primitives2);
        System.out.println("Is equals: " + primitives1.equals(primitives2));
    }

    private static void gsonWithJavaClass() throws IOException {
        User user1 = new User("Duke", 28, "100 internet Dr");
        System.out.println("User #1: " + user1);

        String json = readFile(jsonFile);
        User user2 = new Gson().fromJson(json.toString(), User.class);

        System.out.println("User #2: " + user2);
        System.out.println("Is equals: " + user1.equals(user2));
    }

    private static void gsonWithNonMatchingFieldNames() throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserDeserializerForGson());
        User user = gsonBuilder.create().fromJson(readFile(jsonFile2).toString(), User.class);
        System.out.println(user);
    }

    private static class UserDeserializerForGson implements JsonDeserializer<User> {
        @Override
        public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jObject = jsonElement.getAsJsonObject();
            String firstName = jObject.get("first_name").getAsString();
            int age = jObject.get("age").getAsInt();
            String streetAddress = jObject.get("street_address").getAsString();
            return new User(firstName, age, streetAddress);
        }
    }

    private static String readFile(String file) throws IOException {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ) {
            StringBuilder json = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                json.append(line);
                line = bufferedReader.readLine();
            }
            return json.toString();
        }
    }
}
