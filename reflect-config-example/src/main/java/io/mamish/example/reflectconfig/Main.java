package io.mamish.example.reflectconfig;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {
        System.out.println("Parsing GSON reflectively...");
        Gson gson = new Gson();
        String json = "{" +
                "\"id\":\"foo\"," +
                "\"nested\":{" +
                "\"number\":7" +
                "}}";
        ExampleJavaObject exampleObject = gson.fromJson(json, ExampleJavaObject.class);
        System.out.println(exampleObject);
        System.out.println("Nested number = " + exampleObject.getNested().getNumber());
    }

}
