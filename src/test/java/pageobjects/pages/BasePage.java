package pageobjects.pages;

import pageobjects.PageObject;

/**
 * Base abstraction for concrete types of pages.
 *
 * @param <T> represents the type of the concrete page.
 *            e.g. {@code public class ConcretePage extends BasePage<ConcretePage>}
 */
public class BasePage<T extends BasePage<T>> extends PageObject<T> {
}
