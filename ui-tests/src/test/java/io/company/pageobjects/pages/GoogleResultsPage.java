package io.company.pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import io.company.pageobjects.components.SearchComponent;

import static com.codeborne.selenide.Selenide.$$;

public class GoogleResultsPage extends BasePage<GoogleResultsPage> {

    /**
     * Google search input text selector.
     */
    private static final String SEARCH_SELECTOR = "[name='q']";
    private static final String SEARCH_RESULTS = "#res .g";

    private final SearchComponent searchComponent;

    public GoogleResultsPage() {
        searchComponent = new SearchComponent(SEARCH_SELECTOR);
    }

    public SearchComponent searchComponent() {
        return searchComponent;
    }

    public ElementsCollection getResults() {
        return $$(SEARCH_RESULTS);
    }
}
