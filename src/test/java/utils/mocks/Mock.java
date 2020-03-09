package utils.mocks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to inject mocks during runtime.
 * <p>
 * Accepts a String array which contains the paths to the mocks.
 * e.g. {@code @Mock({"path1", "path2", ...})}
 * Mocks are required to be json files.
 * </p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {
    String[] path();
}
