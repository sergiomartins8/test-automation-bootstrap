package io.company.pageobjects.components;

import io.company.pageobjects.pages.GoogleResultsPage;
import org.openqa.selenium.Keys;

public class SearchComponent extends BaseComponent<SearchComponent> {
    public SearchComponent(String selector) {
        super(selector);
    }

    public GoogleResultsPage searchFor(String word) {
        self().sendKeys(word, Keys.ENTER);
        return new GoogleResultsPage();
    }
}
