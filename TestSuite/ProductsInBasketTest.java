import org.junit.jupiter.api.Test;
import utility.ProductsInBasket;

import static org.junit.jupiter.api.Assertions.*;

class ProductsInBasketTest {

    private final ProductsInBasket productsInBasket = new ProductsInBasket();

    @Test
    void equals() {
        productsInBasket.setProduct("knives");
        ProductsInBasket testProduct = new ProductsInBasket();
        assertNotEquals(productsInBasket, testProduct);
        testProduct.setProduct("knives");
        assertEquals(productsInBasket, testProduct);
        testProduct.setProduct("football");
        assertNotEquals(productsInBasket, testProduct);

    }
}