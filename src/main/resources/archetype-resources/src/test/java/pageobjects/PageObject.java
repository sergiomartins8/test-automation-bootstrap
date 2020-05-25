package ${package}.pageobjects;

import ${package}.utils.logging.Loggable;

/**
 * Abstraction that represents the definition of a page object.
 * <br>
 * Contains methods that are reusable by any page object
 *
 * @param <T> represents the type of the concrete page object
 */
public abstract class PageObject<T extends PageObject<T>> implements Loggable {
}
