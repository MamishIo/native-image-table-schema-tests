package io.mamish.example.featureinit.support;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;

/**
 * Warning: Don't instantiate this in any processor constructor since processingEnv may be null.
 * This should be instantiated during the processing round, when actually needed to write files.
 */
public class ResourceWriter {

    private final ProcessingEnvironment processingEnv;

    public ResourceWriter(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    public void writeResourceContent(String filePath, String fileContent) {
        // 'Origin' is a vague term for the originally annotated object that triggered this processing,
        // which is used by IDEs and such to determine when to run the processor again.
        // We use it as an origin and as a way to differentiate config files under META-INF
        try (Writer resourceFileWriter = processingEnv.getFiler().createResource(
                StandardLocation.CLASS_OUTPUT,
                "",
                filePath)
                .openWriter()
        ){
            resourceFileWriter.write(fileContent);
        } catch (IOException ioe) {
            throw new RuntimeException("Resource file write failed: " + ioe.getMessage());
        }
    }
}
