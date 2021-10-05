package io.mamish.example.classinit;

import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public class Main {

    public static void main(String[] args) {
        System.out.println("Fetching pre-initialised schema instance...");
        TableSchema<ExampleClassInitBean> schema = ExampleClassInitBeanSchema.INSTANCE;
        System.out.println("Schema: " + schema);
    }

}
