package ru.nspk.osks.learn.json.javax;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class JavaxJsonDemo {
    private static final String resourcesPath = "hw08-json-object-writer/src/main/resources/";

    public static void main(String[] args) throws FileNotFoundException {
        navigateTree(create());
        readFromFile();
    }

    private static JsonObject create() {
        var jsonObject = Json.createObjectBuilder()
                .add("firstName", "Duke")
                .add("age", 28)
                .add("streetAddress", "100 internet Dr")
                .add("phonesNumbers",
                        Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("type", "Home")
                                        .add("number", "222-222-2222")))
                .build();

        System.out.println("Json Object: " + jsonObject);
        return jsonObject;
    }

    private static void navigateTree(JsonValue tree) {
        switch (tree.getValueType()) {
            case OBJECT:
                System.out.println("OBJECT");
                var jsonObject = (JsonObject) tree;
                for (Map.Entry<String, JsonValue> entry : jsonObject.entrySet()) {
                    System.out.println();
                    System.out.println("OBJECT NAME: " + entry.getKey());
                    navigateTree(jsonObject.get(entry.getKey()));
                }
                break;
            case ARRAY:
                System.out.println("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue val : array) {
                    navigateTree(val);
                }
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                System.out.println("TYPE: STRING");
                System.out.println("VALUE: " + st);
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                System.out.println("TYPE: NUMBER");
                System.out.println("VALUE: " + num);
                break;
            case TRUE:
            case FALSE:
            case NULL:
                System.out.println(tree.getValueType().toString());
                break;
        }
    }

    private static void readFromFile() throws FileNotFoundException {
        try (
                var jsonReader = Json.createReader(new FileReader(resourcesPath + "example.json"))
        ) {
            JsonStructure jsonStructure = jsonReader.read();
            System.out.println("\nJson from file:");
            System.out.println(jsonStructure);
            System.out.println("Property 'firstName': " + jsonStructure.getValue("/firstName"));
            System.out.println("Property 'age': " + jsonStructure.getValue("/age"));
        }
    }
}
