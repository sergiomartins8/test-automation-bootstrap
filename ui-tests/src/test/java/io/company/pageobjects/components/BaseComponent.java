package io.company.pageobjects.components;

import com.codeborne.selenide.SelenideElement;
import io.company.pageobjects.PageObject;

import static com.codeborne.selenide.Selenide.$;

/**
 * Base abstraction for concrete types of components.
 *
 * @param <T> represents the type of the concrete component.
 *            Example: {@code public class ConcreteComponent extends BaseComponent<ConcreteComponent>}
 */
public abstract class BaseComponent<T extends BaseComponent<T>> extends PageObject<T> {

    /**
     * Unique element that describes this component.
     */
    private final SelenideElement element;

    public BaseComponent(String selector) {
        element = $(selector);
    }

    public SelenideElement self() {
        return element;
    }
}
