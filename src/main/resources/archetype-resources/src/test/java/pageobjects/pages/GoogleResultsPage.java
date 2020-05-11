package ${package}.pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import ${package}.pageobjects.components.SearchComponent;

import static com.codeborne.selenide.Selenide.$$;

public class GoogleResultsPage extends BasePage<GoogleResultsPage> {

    /**
     * Google search input text selector.
     */
    private final String searchSelector = "[name='q']";
    private final String searchResults = "#res .g";

    private final SearchComponent searchComponent;

    public GoogleResultsPage() {
        searchComponent = new SearchComponent(searchSelector);
    }

    public SearchComponent searchComponent() {
        return searchComponent;
    }

    public ElementsCollection getResults() {
        return $$(searchResults);
    }
}
