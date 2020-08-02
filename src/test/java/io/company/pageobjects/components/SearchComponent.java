package io.company.pageobjects.components;

import org.openqa.selenium.Keys;

public class SearchComponent extends BaseComponent<SearchComponent> {

    public SearchComponent(String selector) {
        super(selector);
    }

    public SearchComponent searchFor(String word) {
        self().sendKeys(word, Keys.ENTER);
        return this;
    }
}
