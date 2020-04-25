package ${package}.utils.mocks;

import com.google.gson.Gson;

import java.io.InputStreamReader;

/**
 * Single responsibility of parsing json files into {@link MockDefinition} objects.
 */
public class MockParser {

    public static MockDefinition toObject(String jsonPath) {
        return new Gson().fromJson(new InputStreamReader(MockParser.class.getResourceAsStream(jsonPath)), MockDefinition.class);
    }
}
