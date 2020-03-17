package pageobjects.pages;

import pageobjects.components.ExampleComponent;
import pageobjects.selectors.ExampleSelector;

public class ExamplePage extends BasePage<ExamplePage> {

    private ExampleComponent exampleComponent;

    public ExamplePage() {
        exampleComponent = new ExampleComponent(ExampleSelector.EXAMPLE_SELECTOR);
    }

    public ExampleComponent exampleComponent() {
        return exampleComponent;
    }
}
