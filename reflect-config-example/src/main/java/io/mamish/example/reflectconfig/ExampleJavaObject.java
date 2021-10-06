package io.mamish.example.reflectconfig;

import io.mamish.example.reflectconfig.support.EnableFieldReflection;

@EnableFieldReflection
public class ExampleJavaObject {

    private String id;
    private NestedData nested;

    public static class NestedData {

        private int number;

        public int getNumber() {
            return number;
        }
    }

    public String getId() {
        return id;
    }

    public NestedData getNested() {
        return nested;
    }
}
