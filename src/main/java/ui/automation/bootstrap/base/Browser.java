package ui.automation.bootstrap.base;

import org.openqa.selenium.WebDriver;

public final class Browser {

    private WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void maximize() {
        driver.manage().window().maximize();
    }
}
