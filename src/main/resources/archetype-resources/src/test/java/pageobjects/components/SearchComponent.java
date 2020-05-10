package ${package}.pageobjects.components;

import org.openqa.selenium.Keys;

public class SearchComponent extends BaseComponent<SearchComponent> {

    public SearchComponent(String selector) {
        super(selector);
    }

    public SearchComponent search(String word) {
        self().sendKeys(word, Keys.ENTER);
        return this;
    }
}
