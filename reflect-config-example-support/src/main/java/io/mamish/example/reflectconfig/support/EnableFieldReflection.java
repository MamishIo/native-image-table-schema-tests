package io.mamish.example.reflectconfig.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
public @interface EnableFieldReflection {
    // Marker interface to enable reflection on all classes and fields within this type
}
