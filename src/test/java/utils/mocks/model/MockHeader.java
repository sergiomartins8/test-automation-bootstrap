package utils.mocks.model;

/**
 * Concrete definition of a mocked header.
 */
@SuppressWarnings("unused")
public final class MockHeader {

    /**
     * Header name.
     */
    private String name;

    /**
     * Header values.
     */
    private String values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
