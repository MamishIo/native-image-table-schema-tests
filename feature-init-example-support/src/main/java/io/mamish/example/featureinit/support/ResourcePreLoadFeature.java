package io.mamish.example.featureinit.support;

import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("unused")
public class ResourcePreLoadFeature implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        ClassLoader appClassLoader = access.getApplicationClassLoader();
        Map<Class<?>, TableSchema<?>> tableSchemas = preloadResources(appClassLoader,
                ResourcePaths.TABLE_SCHEMAS_LIST.path(), TableSchema::fromBean);

        ImageCache runtimeCache = new ImageCache(tableSchemas);

        ImageSingletons.add(ImageCache.class, runtimeCache);
    }

    private <V> Map<Class<?>,V> preloadResources(ClassLoader appClassLoader, String resourcePath,
                                                 Function<Class<?>, V> instanceGenerator) {
        final Map<Class<?>, V> tempMap = new HashMap<>();
        forAllLines(appClassLoader, resourcePath, classNameLine -> {
            Class<?> classForLine;
            try {
                classForLine = appClassLoader.loadClass(classNameLine);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Could not locate indicated class <" + classNameLine + ">", e);
            }
            V instance = instanceGenerator.apply(classForLine);
            tempMap.put(classForLine, instance);
        });
        return tempMap;
    }

    private void forAllLines(ClassLoader appClassLoader, String resourcePath, Consumer<String> lineConsumer) {
        try {
            Iterator<URL> iterator = appClassLoader.getResources(resourcePath).asIterator();
            while (iterator.hasNext()) {
                URL resourceUrl = iterator.next();
                var lineReader = new BufferedReader(new InputStreamReader(resourceUrl.openStream()));
                String line;
                while ((line = lineReader.readLine()) != null) {
                    if (StringUtils.isNotBlank(line)) {
                        lineConsumer.accept(line);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource lines at <" + resourcePath + ">", e);
        }
    }

}
