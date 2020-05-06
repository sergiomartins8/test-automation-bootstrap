package ${package}.pageobjects.pages;

import ${package}.pageobjects.components.ExampleComponent;
import ${package}.pageobjects.selectors.ExampleSelector;

public class ExamplePage extends BasePage<ExamplePage> {

    private final ExampleComponent exampleComponent;

    public ExamplePage() {
        exampleComponent = new ExampleComponent(ExampleSelector.EXAMPLE_SELECTOR);
    }

    public ExampleComponent exampleComponent() {
        return exampleComponent;
    }
}
