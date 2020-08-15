package io.company.pageobjects.pages;

import io.company.pageobjects.components.SearchComponent;

public class GoogleSearchPage extends BasePage<GoogleSearchPage> {

    /**
     * Google search input text selector.
     */
    private static final String SEARCH_SELECTOR = "[name='q']";

    private final SearchComponent searchComponent;

    public GoogleSearchPage() {
        searchComponent = new SearchComponent(SEARCH_SELECTOR);
    }

    public SearchComponent searchComponent() {
        return searchComponent;
    }
}
