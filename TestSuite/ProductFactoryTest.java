import org.junit.jupiter.api.Test;
import utility.StockCheck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductFactoryTest {

    private final ProductFactory productFactory = new ProductFactory();

    @Test
    void getProduct() {
        assertEquals("Knives", productFactory.getProduct("Knives").getName());
        assertEquals("Professional chef knife", productFactory.getProduct("Knives").getDescription());
        assertEquals(75, productFactory.getProduct("Knives").getPrice());

        assertEquals("Basketball", productFactory.getProduct("Basketball").getName());
        assertEquals("Professional ball used in the NBA league", productFactory.getProduct("Basketball").getDescription());
        assertEquals(25, productFactory.getProduct("Basketball").getPrice());

        assertEquals("Football", productFactory.getProduct("Football").getName());
        assertEquals("Professional ball used in the World Cup", productFactory.getProduct("Football").getDescription());
        assertEquals(15, productFactory.getProduct("Football").getPrice());

        assertEquals("Jeans", productFactory.getProduct("Jeans").getName());
        assertEquals("Blue slim fit jeans", productFactory.getProduct("Jeans").getDescription());
        assertEquals(100, productFactory.getProduct("Jeans").getPrice());

        assertEquals("Plates", productFactory.getProduct("Plates").getName());
        assertEquals("Ceramic Dinner Plates", productFactory.getProduct("Plates").getDescription());
        assertEquals(5, productFactory.getProduct("Plates").getPrice());

        assertEquals("Shirt", productFactory.getProduct("Shirt").getName());
        assertEquals("100% cotton shirts", productFactory.getProduct("Shirt").getDescription());
        assertEquals(25, productFactory.getProduct("Shirt").getPrice());

        assertEquals("Shoes", productFactory.getProduct("Shoes").getName());
        assertEquals("Oxford Shoes in Brown", productFactory.getProduct("Shoes").getDescription());
        assertEquals(110, productFactory.getProduct("Shoes").getPrice());
    }

    @Test
    void getProductsList() {
        productFactory.getProductsList();
        assertTrue(productFactory.getProductsList().contains("basketball"));
        assertTrue(productFactory.getProductsList().contains("football"));
        assertTrue(productFactory.getProductsList().contains("jeans"));
        assertTrue(productFactory.getProductsList().contains("knives"));
        assertTrue(productFactory.getProductsList().contains("plates"));
        assertTrue(productFactory.getProductsList().contains("shirt"));
        assertTrue(productFactory.getProductsList().contains("shoes"));
        // test for product not in catalogue
        assertFalse(productFactory.getProductsList().contains("flowers"));

    }

    @Test
    void checkStock() {
        assertEquals(StockCheck.LOW_STOCK.ordinal(),productFactory.checkStock(productFactory.getProduct("Knives"), 10000));
        assertEquals(StockCheck.IN_STOCK.ordinal(),productFactory.checkStock(productFactory.getProduct("Knives"), 10));
        productFactory.getProduct("Knives").setStock(0);
        assertEquals(StockCheck.OUT_OF_STOCK.ordinal(),productFactory.checkStock(productFactory.getProduct("Knives"), 10));
        productFactory.getProduct("Knives").setStock(100);
    }

    @Test
    void updateStock() {
        productFactory.getProduct("Knives").setStock(250);
        productFactory.updateStock(productFactory.getProduct("Knives"), 100);
        assertEquals(150, productFactory.getProduct("Knives").getStock());
        productFactory.getProduct("Knives").setStock(100);
    }

    @Test
    void calculateProductTotal() {
        assertEquals(375, productFactory.calculateProductTotal(productFactory.getProduct("knives"), 5));
    }
}