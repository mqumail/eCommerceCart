import utility.Helpers;
import utility.ProductsInBasket;
import utility.StockCheck;
import utility.StringConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

class OrderManagement {

    private static boolean repeat;
    private static BufferedReader reader;
    private static ProductFactory productFactory ;
    private static List<String> listOfProducts;
    private static ArrayList<ProductsInBasket> productsInBasket;
    private static double totalPriceWithShipping;
    private static double totalPrice;

    static void run(){
        initialize();

        System.out.println(StringConstants.WELCOME_MESSAGE);
        ProductFactory.printProductCatalogue();

        createOrder();

        totalPrice = calculateBill();
        System.out.println(StringConstants.PROMPT_BASKET_TOTAL + totalPrice);
        System.out.println(Helpers.productsListToString(productsInBasket));

        updateOrder();

        pickingShippingMethod();

        pickingPaymentMethod();

        System.out.println(StringConstants.GOODBYE_MESSAGE);
    }

    private static void initialize() {
        repeat = true;
        reader = new BufferedReader(new InputStreamReader(System.in));
        productFactory = new ProductFactory();
        listOfProducts = productFactory.getProductsList();
        productsInBasket = new ArrayList<>();
        totalPriceWithShipping = 0;
        totalPrice = 0;
    }

    private static void createOrder(){
        ProductsInBasket productInBasket;
        while (repeat){
            productInBasket = new ProductsInBasket();
            System.out.println(StringConstants.PROMPT_PRODUCT_NAME);
            String productName;
            int productQuantity;
            try {
                productName = reader.readLine();

                // Check if the product is sold by us
                while (!listOfProducts.contains(productName.toLowerCase())){
                    System.out.println(StringConstants.PROMPT_PRODUCT_NOT_SOLD);
                    System.out.println(StringConstants.PROMPT_PRODUCT_NAME);
                    productName = reader.readLine();
                }

                System.out.println(StringConstants.PROMPT_PRODUCT_QUANTITY);
                productQuantity = Integer.parseInt(reader.readLine());

                // ask for quantity here, not in addingProductToBasket
                addingProductToBasket(productInBasket, productName, productQuantity);

                // show current cart with items, quantity
                System.out.println(Helpers.productsListToString(productsInBasket));

                // Continue shopping?
                System.out.println(StringConstants.PROMPT_CONTINUE_SHOPPING);
                String continueShopping = reader.readLine();
                if (continueShopping.equalsIgnoreCase("n") || continueShopping.equalsIgnoreCase("no")){
                    repeat = false;
                }
            } catch (IOException e) {
                System.out.println(StringConstants.ERROR_INVALID_INPUT);
            }
        }
    }

    private static void updateOrder(){
        boolean updateCart = true;
        while (updateCart){
            System.out.println(StringConstants.PROMPT_UPDATE_CART);
            String updateOrProceed;
            try {
                updateOrProceed = reader.readLine();
                if (updateOrProceed.equalsIgnoreCase("n") || updateOrProceed.equalsIgnoreCase("no")){
                    //allow user to update the contents of the cart:
                    System.out.println(Helpers.productsListToString(productsInBasket));
                    System.out.println(StringConstants.PROMPT_PRODUCT_UPDATE_NAME);
                    String updateProductName = reader.readLine();

                    // Check if the product is sold by us
                    while (!listOfProducts.contains(updateProductName.toLowerCase())){
                        System.out.println(StringConstants.ERROR_PRODUCT_NOT_IN_CART);
                        System.out.println(StringConstants.PROMPT_PRODUCT_NAME);
                        updateProductName = reader.readLine();
                    }

                    System.out.println(StringConstants.PROMPT_PRODUCT_UPDATE_QUANTITY);
                    int updateProductQuantity = Integer.parseInt(reader.readLine());
                    // do the magic and update the basket
                    ProductsInBasket updateProduct = new ProductsInBasket();
                    updateProduct.setProduct(updateProductName.toLowerCase());
                    updateProduct.setQuantity(updateProductQuantity);
                    if (productsInBasket.contains(updateProduct)) {
                        productsInBasket.set(productsInBasket.indexOf(updateProduct), updateProduct);
                        System.out.println(StringConstants.PROMPT_PRODUCT_UPDATED);
                        System.out.println(Helpers.productsListToString(productsInBasket));
                    } else {
                        System.out.println(StringConstants.PROMPT_PRODUCT_NOT_IN_BASKET);
                        System.out.println(Helpers.productsListToString(productsInBasket));
                    }
                } else {
                    updateCart = false;
                }
            } catch (IOException e) {
                System.out.println(StringConstants.ERROR_INVALID_INPUT);
            }

        }
    }

    private static void pickingShippingMethod() {
        boolean pickedShippingMethod = false;
        while (!pickedShippingMethod){
            System.out.println(StringConstants.PROMPT_SELECT_SHIPPING);
            System.out.println(StringConstants.NEXT_DAY_SHIPPING);
            System.out.println(StringConstants.TWO_DAY_SHIPPING);
            System.out.println(StringConstants.REGULAR_SHIPPING);

            int preferredShippingMethod;
            try {
                preferredShippingMethod = Integer.parseInt(reader.readLine());
                if (preferredShippingMethod == 1){
                    totalPriceWithShipping = calculateBill() + 10.99;
                    pickedShippingMethod = true;
                    System.out.println(StringConstants.TOTAL + totalPriceWithShipping);
                } else if (preferredShippingMethod == 2) {
                    totalPriceWithShipping = calculateBill() + 7.99;
                    System.out.println(StringConstants.TOTAL + totalPriceWithShipping);
                    pickedShippingMethod = true;
                } else if (preferredShippingMethod == 3) {
                    totalPriceWithShipping = calculateBill() + 2.99;
                    System.out.println(StringConstants.TOTAL + totalPriceWithShipping);
                    pickedShippingMethod = true;
                } else{
                    System.out.println(StringConstants.SHIPPING_ERROR);
                }

                System.out.println(StringConstants.SHIPPING_CONFIRMATION);
            } catch (NumberFormatException | IOException e) {
                System.out.println(StringConstants.ERROR_INVALID_INPUT);
            }
        }
    }

    private static void pickingPaymentMethod() {
        boolean pickedPaymentMethod = false;
        while(!pickedPaymentMethod){
            // payment methods
            System.out.println(StringConstants.PROMPT_SELECT_PAYMENT);
            System.out.println(StringConstants.CREDIT_CARD_PAYMENT);
            System.out.println(StringConstants.BANK_CARD_PAYMENT);
            System.out.println(StringConstants.PAYPAL_PAYMENT);
            int preferredPaymentMethod;
            try {
                preferredPaymentMethod = Integer.parseInt(reader.readLine());
                if (preferredPaymentMethod == 1){
                    pickedPaymentMethod = true;
                    System.out.println(StringConstants.CREDIT_CARD_CONFIRMATION + totalPriceWithShipping);
                } else if (preferredPaymentMethod == 2) {
                    System.out.println(StringConstants.BANK_CARD_CONFIRMATION + totalPriceWithShipping);
                    pickedPaymentMethod = true;
                } else if (preferredPaymentMethod == 3) {
                    System.out.println(StringConstants.PAYPAL_CONFIRMATION + totalPriceWithShipping);
                    pickedPaymentMethod = true;
                } else{
                    System.out.println(StringConstants.PAYMENT_ERROR);
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println(StringConstants.ERROR_INVALID_INPUT);
            }
        }
    }

    private static double calculateBill() {
        double updateTotalPrice = 0;
        for (ProductsInBasket product: productsInBasket){
            updateTotalPrice  = updateTotalPrice + productFactory.calculateProductTotal(productFactory.getProduct(product.getProduct()), product.getQuantity());
        }
        return updateTotalPrice;
    }

    private static void addingProductToBasket(ProductsInBasket productInBasket, String productName, int productQuantity){
        Product product;
        try {

            productInBasket.setProduct(productName.toLowerCase());
            productInBasket.setQuantity(productQuantity);
            product = productFactory.getProduct(productInBasket.getProduct());
            if (productFactory.checkStock(product, productInBasket.getQuantity()) == StockCheck.OUT_OF_STOCK.ordinal()){
                System.out.println(StringConstants.OUT_OF_STOCK);
            }
            else if (productFactory.checkStock(product, productInBasket.getQuantity()) == StockCheck.LOW_STOCK.ordinal()) {
                do {
                    System.out.println(StringConstants.LOW_STOCK + " We only have " + product.getStock() + " piece(s) in stock. \n" + StringConstants.PROMPT_PRODUCT_QUANTITY);
                    productQuantity = Integer.parseInt(reader.readLine());
                } while (productFactory.checkStock(product, productQuantity) == StockCheck.LOW_STOCK.ordinal());

                if (productsInBasket.contains(productInBasket)){
                    int totalQuantity = productsInBasket.get(productsInBasket.indexOf(productInBasket)).getQuantity() + productQuantity;
                    productsInBasket.get(productsInBasket.indexOf(productInBasket)).setQuantity(totalQuantity);
                    productFactory.updateStock(product, productQuantity);
                }else {
                    productInBasket.setQuantity(productQuantity);
                    productsInBasket.add(productInBasket);
                    productFactory.updateStock(product, productQuantity);
                }

            } else {
                // Add new products with quantity in cart or update the quantity only if it already exists in the cart
                if (productsInBasket.contains(productInBasket)){
                    int totalQuantity = productsInBasket.get(productsInBasket.indexOf(productInBasket)).getQuantity() + productQuantity;
                    productsInBasket.get(productsInBasket.indexOf(productInBasket)).setQuantity(totalQuantity);
                    productFactory.updateStock(product, productQuantity);
                } else {
                    productsInBasket.add(productInBasket);
                    productFactory.updateStock(product, productQuantity);
                }
            }
        } catch (IOException e) {
            System.out.println(StringConstants.ERROR_INVALID_INPUT);
        }
    }

}
