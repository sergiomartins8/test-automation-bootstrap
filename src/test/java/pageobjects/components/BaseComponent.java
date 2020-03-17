package pageobjects.components;

import com.codeborne.selenide.SelenideElement;
import pageobjects.PageObject;
import utils.logging.Loggable;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseComponent<T extends BaseComponent<T>> extends PageObject<T> implements Loggable {

    private SelenideElement element;

    public BaseComponent(String selector) {
        element = $(selector);
    }

    public SelenideElement self() {
        return element;
    }
}
