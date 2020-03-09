package utils.mocks;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Single responsibility of parsing json files into objects.
 */
public final class MockParser {

    public static MockDefinition toObject(String path) {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = MockParser.class.getResourceAsStream(path);
        try {
            return mapper.readValue(inputStream, MockDefinition.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
