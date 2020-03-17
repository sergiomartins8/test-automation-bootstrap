package pageobjects.components;

import org.openqa.selenium.Keys;

public class ExampleComponent extends BaseComponent<ExampleComponent> {

    public ExampleComponent(String selector) {
        super(selector);
    }

    public ExampleComponent exampleAction() {
        self().sendKeys("lol", Keys.ENTER);
        return this;
    }
}
