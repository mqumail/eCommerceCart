import utility.ListToString;
import utility.ProductsInBasket;
import utility.StockCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // variables
    private static boolean repeat;
    private static ArrayList<ProductsInBasket> productsInBasket;
    private static double totalPrice;
    private static double totalPriceWithShipping;
    private static ProductFactory productFactory;
    private static List<String> listOfProducts;
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {

        // initialize variables
        initialize();

        welcomeMessage();

        createOrder();

        totalPrice = calculateBill();
        System.out.println("Bill amount for " + ListToString.productsListToString(productsInBasket) + "is: €" + totalPrice);

        updateOrder();

        pickingShippingMethod();

        pickingPaymentMethod();

        System.out.println("Thank you for shopping with us. Your order will arrive in specified time.");
    }

    private static void initialize() {
        repeat = true;
        productsInBasket = new ArrayList<>();
        totalPrice = 0;
        totalPriceWithShipping = 0;
        productFactory = new ProductFactory();
        listOfProducts = productFactory.getProductsList();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void welcomeMessage() {
        System.out.println("Welcome to Intershop eCommerce checkout page");
        System.out.println("--------------------------------------------");
        productFactory.printProductCatalogue();
    }

    //TODO: catch exceptions
    private static void createOrder() throws IOException {
        ProductsInBasket productInBasket;
        Product p;
        while (repeat){
            productInBasket = new ProductsInBasket();
            System.out.println("Enter the Product name: ");
            String product = reader.readLine();

            // Check if the product is sold by us
            while (!listOfProducts.contains(product.toLowerCase())){
                System.out.println("Unfortunately, we do not carry this Product at the moment. Please look at the catalogue for which products we sell and make an selection.");
                System.out.println("Enter the Product name: ");
                product = reader.readLine();
            }

            addingProductToBasket(productInBasket, product);

            // show current cart with items, quantity
            System.out.println(ListToString.productsListToString(productsInBasket));

            // Continue shopping?
            System.out.println("Press any key to continue shopping, enter 'n' to proceed to the next step.");
            String continueShopping = reader.readLine();
            if (continueShopping.equalsIgnoreCase("n") || continueShopping.equalsIgnoreCase("no")){
                repeat = false;
            }
        }
    }

    private static void updateOrder() throws IOException {
        boolean updateCart = true;
        while (updateCart){
            System.out.println("Proceed to checkout? Enter any key to proceed or enter 'n' to update your cart.");
            String updateOrProceed = reader.readLine();
            if (updateOrProceed.equalsIgnoreCase("n") || updateOrProceed.equalsIgnoreCase("no")){
                //allow user to update the contents of the cart:
                System.out.println(ListToString.productsListToString(productsInBasket));
                System.out.println("Enter the product for which you want to update the quantities or completely remove it: ");
                String updateProductName = reader.readLine();

                // Check if the product is sold by us
                while (!listOfProducts.contains(updateProductName.toLowerCase())){
                    System.out.println("This product is not in your cart. Please select an item from your cart to update it.");
                    System.out.println("Enter the Product name: ");
                    updateProductName = reader.readLine();
                }

                System.out.println("Enter the updated quantity, enter 0 to completely remove the item from your basket: ");
                int updateProductQuantity = Integer.parseInt(reader.readLine());
                // do the magic and update the basket
                ProductsInBasket updateProduct = new ProductsInBasket();
                updateProduct.setProduct(updateProductName);
                updateProduct.setQuantity(updateProductQuantity);
                if (productsInBasket.contains(updateProduct)) {
                    productsInBasket.set(productsInBasket.indexOf(updateProduct), updateProduct);
                    System.out.println("Product updated. Bellow is your current cart: ");
                    System.out.println(ListToString.productsListToString(productsInBasket));
                } else {
                    System.out.println("This product is not in your basket. Your current basket contains of the following items: ");
                    System.out.println(ListToString.productsListToString(productsInBasket));
                }
            } else {
                updateCart = false;
            }
        }
    }

    private static void pickingShippingMethod() throws IOException {
        boolean pickedShippingMethod = false;
        while (!pickedShippingMethod){
            // enter shipping details and payment methods and goodbye message
            System.out.println("Please select the shipping method. We offer the following shipping method: ");
            System.out.println("Enter 1 for 'Next day shipping - €10.99'");
            System.out.println("Enter 2 for '2 day shipping - €7.99'");
            System.out.println("Enter 3 for 'Regular shipping: 5 business days - €2.99'");
            // TODO: if user enters a character, it breaks. catch and handle the exception
            int preferredShippingMethod = Integer.parseInt(reader.readLine());
            if (preferredShippingMethod == 1){
                totalPriceWithShipping = calculateBill() + 10.99;
                pickedShippingMethod = true;
                System.out.println("Total: " + totalPriceWithShipping);
            } else if (preferredShippingMethod == 2) {
                totalPriceWithShipping = calculateBill() + 7.99;
                System.out.println("Total: " + totalPriceWithShipping);
                pickedShippingMethod = true;
            } else if (preferredShippingMethod == 3) {
                totalPriceWithShipping = calculateBill() + 2.99;
                System.out.println("Total: " + totalPriceWithShipping);
                pickedShippingMethod = true;
            } else{
                System.out.println("Invalid shipping preference entered");
            }
        }

        System.out.println("We will ship to the default shipping adress that you have stored with us.\n");
    }

    private static void pickingPaymentMethod() throws IOException {
        boolean pickedPaymentMethod = false;
        while(!pickedPaymentMethod){
            // payment methods
            System.out.println("Please select the payment method. We offer the following payment method: ");
            System.out.println("Enter 1 for 'Credit Card' - All major cards accepted");
            System.out.println("Enter 2 for Bank Card");
            System.out.println("Enter 3 for PayPal");
            int preferredPaymentMethod = Integer.parseInt(reader.readLine());
            if (preferredPaymentMethod == 1){
                pickedPaymentMethod = true;
                System.out.println("We are using the credit card that you have stored with us and charging it: €" + totalPriceWithShipping);
            } else if (preferredPaymentMethod == 2) {
                System.out.println("We are using the bank card that you have stored with us and charging it: €" + totalPriceWithShipping);
                pickedPaymentMethod = true;
            } else if (preferredPaymentMethod == 3) {
                System.out.println("We are using the PayPal account that you have stored with us and charging it: €" + totalPriceWithShipping);
                pickedPaymentMethod = true;
            } else{
                System.out.println("Invalid shipping preference entered");
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

    private static void addingProductToBasket(ProductsInBasket productInBasket, String productName) throws IOException {
        Product product;
        System.out.println("Enter the quantity: ");
        int quantity = Integer.parseInt(reader.readLine());

        productInBasket.setProduct(productName);
        productInBasket.setQuantity(quantity);
        product = productFactory.getProduct(productInBasket.getProduct());
        if (productFactory.checkStock(product, productInBasket.getQuantity()) == StockCheck.OUT_OF_STOCK.ordinal()){
            System.out.println("We currently are out of stock for this item. Please select another item");
        }
        else if (productFactory.checkStock(product, productInBasket.getQuantity()) == StockCheck.LOW_STOCK.ordinal()) {
            do {
                System.out.println("We currently do not have that much stock on hand. We only have " + product.getStock() + " piece(s) in stock.\nPlease enter a lower quantity: ");
                quantity = Integer.parseInt(reader.readLine());
            } while (productFactory.checkStock(product, quantity) == StockCheck.LOW_STOCK.ordinal());

            if (productsInBasket.contains(productInBasket)){
                int totalQuantity = productsInBasket.get(productsInBasket.indexOf(productInBasket)).getQuantity() + quantity;
                productsInBasket.get(productsInBasket.indexOf(productInBasket)).setQuantity(totalQuantity);
                productFactory.updateStock(product, quantity);
            }else {
                productInBasket.setQuantity(quantity);
                productsInBasket.add(productInBasket);
                productFactory.updateStock(product, quantity);
            }

        } else {
            ////Add new products with quantity in cart or update, if it already exists, the quantity only
            if (productsInBasket.contains(productInBasket)){
                int totalQuantity = productsInBasket.get(productsInBasket.indexOf(productInBasket)).getQuantity() + quantity;
                productsInBasket.get(productsInBasket.indexOf(productInBasket)).setQuantity(totalQuantity);
                productFactory.updateStock(product, quantity);
            } else {
                productsInBasket.add(productInBasket);
                productFactory.updateStock(product, quantity);
            }
        }
    }
}
