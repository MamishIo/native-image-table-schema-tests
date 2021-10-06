package io.mamish.example.featureinit;

import io.mamish.example.featureinit.support.ImageCache;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public class Main {

    public static void main(String[] args) {
        System.out.println("Fetching schema from preloaded image cache...");
        TableSchema<ExampleFeatureInitBean> schema = ImageCache.getTableSchema(ExampleFeatureInitBean.class);
        System.out.println("Schema: " + schema);
    }

}
