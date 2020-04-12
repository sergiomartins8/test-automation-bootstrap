package utils.mocks.model;

/**
 * Concrete definition of a mocked cookie.
 */
@SuppressWarnings("unused")
public class MockCookie {

    /**
     * Cookie name.
     */
    private String name;

    /**
     * Cookie value.
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
