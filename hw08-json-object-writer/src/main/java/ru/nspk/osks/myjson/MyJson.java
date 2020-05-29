package ru.nspk.osks.myjson;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class MyJson {

    public String toJson(Object obj) {
        return getJsonValue(obj).toString();
    }

    private JsonValue getJsonValue(Object obj) {
        if (obj == null) return JsonValue.NULL;

        Class<?> clazz = obj.getClass();
        if (clazz.isArray()) {
            if (Array.getLength(obj) == 0) {
                return JsonValue.EMPTY_JSON_ARRAY;
            }
            return arrayToJson(obj);
        }
        if (obj instanceof String) {
            return Json.createValue((String) obj);
        } else if (obj instanceof Character) {
            return Json.createValue((Character) obj);
        } else if (obj instanceof Integer) {
            return Json.createValue((Integer) obj);
        } else if (obj instanceof Long) {
            return Json.createValue((Long) obj);
        } else if (obj instanceof Byte) {
            return Json.createValue((Byte) obj);
        } else if (obj instanceof Short) {
            return Json.createValue((Short) obj);
        } else if (obj instanceof Double) {
            return Json.createValue((Double) obj);
        } else if (obj instanceof Float) {
            return Json.createValue((Float) obj);
        } else if (obj instanceof BigDecimal) {
            return Json.createValue((BigDecimal) obj);
        } else if (obj instanceof BigInteger) {
            return Json.createValue((BigInteger) obj);
        } else if (obj instanceof Boolean) {
            return (Boolean) obj ? JsonValue.TRUE : JsonValue.FALSE;
        } else if (obj instanceof Collection) {
            return arrayToJson(((Collection) obj).toArray());
        } else if (obj instanceof Map) {
            return mapToJson(obj);
        } else {
            return objToJson(obj);
        }
    }

    private JsonValue arrayToJson(Object obj) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < Array.getLength(obj); i++) {
            arrayBuilder.add(getJsonValue(Array.get(obj, i)));
        }
        return arrayBuilder.build();
    }

    private JsonValue objToJson(Object obj) {
        if (obj == null) return JsonValue.NULL;
        Class<?> clazz = obj.getClass();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!Modifier.isStatic(field.getModifiers())) {
                try {
                    Object o = field.get(obj);
                    objectBuilder.add(field.getName(), getJsonValue(o));
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return objectBuilder.build();
    }

    private JsonValue mapToJson(Object obj) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        for (Object entry : ((Map) obj).entrySet()) {
            String key = (String) ((Map.Entry) entry).getKey();
            JsonValue value = getJsonValue(((Map.Entry) entry).getValue());
            objectBuilder.add(key, value);
        }
        return objectBuilder.build();
    }
}
