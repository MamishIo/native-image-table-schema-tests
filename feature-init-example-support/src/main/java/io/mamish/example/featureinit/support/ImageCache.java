package io.mamish.example.featureinit.support;

import org.graalvm.nativeimage.ImageSingletons;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

public class ImageCache {

    private final Map<Class<?>, TableSchema<?>> tableSchemas;

    public ImageCache(Map<Class<?>, TableSchema<?>> tableSchemas) {
        this.tableSchemas = Collections.unmodifiableMap(tableSchemas);
    }

    public static <T> TableSchema<T> getTableSchema(Class<T> beanClass) {
        ImageCache cache = ImageSingletons.lookup(ImageCache.class);
        @SuppressWarnings("unchecked")
        TableSchema<T> schema = (TableSchema<T>) cache.tableSchemas.get(beanClass);
        if (schema == null) {
            throw new NoSuchElementException("No schema for class " + beanClass);
        }
        return schema;
    }
}
