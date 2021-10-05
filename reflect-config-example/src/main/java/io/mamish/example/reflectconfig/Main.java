package io.mamish.example.reflectconfig;

import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public class Main {

    public static void main(String[] args) {
        System.out.println("Generating schema reflectively...");
        TableSchema<ExampleReflectedBean> schema = TableSchema.fromBean(ExampleReflectedBean.class);
        System.out.println("Schema: " + schema);
    }

}
