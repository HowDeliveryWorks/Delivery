

/**
 * Created by Max on 02.05.2017.
 */

/**
 * TODO: Do total ingredients review and refactor.
 * TODO: Wire enums {@link BreadType} {@link MeatType} etc. to {@link IngredientCategory}
 */

/**
package Delivery.model;
public class ConstructorCategory<T> {

    private T category;

    public ConstructorCategory(T arg) {
        category = arg;
    }

    public T getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "{" + category + "}";
    }
}
*/

package Delivery.model;
public enum ConstructorCategory {
    BreadType,
    MeatType,
    Roasting,
    Sauces
}

