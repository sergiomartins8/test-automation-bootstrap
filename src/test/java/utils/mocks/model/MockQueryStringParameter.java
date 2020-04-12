package utils.mocks.model;

import java.util.List;

/**
 * Concrete definition of a mocked query parameter.
 */
@SuppressWarnings("unused")
public final class MockQueryStringParameter {

    /**
     * Query parameter name.
     */
    private String name;

    /**
     * Query parameter values.
     */
    private List<String> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
