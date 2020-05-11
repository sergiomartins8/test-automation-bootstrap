package ${package}.pageobjects.pages;

import ${package}.pageobjects.components.SearchComponent;

public class GoogleSearchPage extends BasePage<GoogleSearchPage> {

    /**
     * Google search input text selector.
     */
    private final String searchSelector = "[name='q']";

    private final SearchComponent searchComponent;

    public GoogleSearchPage() {
        searchComponent = new SearchComponent(searchSelector);
    }

    public SearchComponent searchComponent() {
        return searchComponent;
    }
}
