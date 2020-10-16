package io.company.utils.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * Allows whoever implements {@link AsJson} interface to be printed as a json string.
 * <br>
 * Pretty useful for logging model/POJO classes.
 * <br>
 * Example:
 * <p>
 * public class UserModel implements AsJson {}
 * </p>
 * <p>
 * public void printObjectAsJson() {
 *      UserModel userModel = new UserModel();
 *      System.out.println(userModel.toJson());
 * }
 * </p>
 */
public interface AsJson {
    @SneakyThrows
    default String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
