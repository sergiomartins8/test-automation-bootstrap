package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ui.automation.bootstrap.utils.logging.Loggable;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseComponent<T extends BaseComponent> implements Loggable {

    private SelenideElement element;

    public BaseComponent(String selector) {
        element = $(selector);
    }

    public SelenideElement self() {
        return element;
    }

    public T shouldBe(Condition condition) {
        element.shouldBe(condition);
        return (T) this;
    }

    public T should(Condition condition) {
        element.should(condition);
        return (T) this;
    }
}
