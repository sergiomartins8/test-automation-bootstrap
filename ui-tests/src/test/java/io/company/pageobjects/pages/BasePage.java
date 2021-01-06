package io.company.pageobjects.pages;

import io.company.pageobjects.PageObject;

/**
 * Base abstraction for concrete types of pages.
 *
 * @param <T> represents the type of the concrete page.
 *            Example: {@code public class ConcretePage extends BasePage<ConcretePage>}
 */
public class BasePage<T extends BasePage<T>> extends PageObject<T> {
}
