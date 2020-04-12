package utils.mocks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to inject mocks during runtime.
 * <p>
 * Mocks are required to be json files.
 * <p>
 * e.g. {@code @Mock({"path1", "path2", ...})}
 * </p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {
    String[] path();
}
