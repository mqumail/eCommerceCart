import utility.StockCheck;

import java.util.*;

class ProductFactory {

    private static Basketball basketball;
    private static Football football;
    private static Jeans jeans;
    private static Knives knives;
    private static Plates plates;
    private static Shirt shirt;
    private static Shoes shoes;


    ProductFactory(){
        basketball = new Basketball();
        football = new Football();
        jeans = new Jeans();
        knives = new Knives();
        plates = new Plates();
        shirt = new Shirt();
        shoes = new Shoes();
    }

    Product getProduct(String name) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("BASKETBALL")){
            return basketball;
        }
        if (name.equalsIgnoreCase("FOOTBALL")){
            return football;
        }
        if (name.equalsIgnoreCase("JEANS")){
            return jeans;
        }
        if (name.equalsIgnoreCase("KNIVES")){
            return knives;
        }
        if (name.equalsIgnoreCase("PLATES")){
            return plates;
        }
        if (name.equalsIgnoreCase("SHIRT")){
            return shirt;
        }
        if (name.equalsIgnoreCase("SHOES")){
            return shoes;
        }

        return null;
    }

    static void printProductCatalogue(){
        System.out.println("******************************************Catalogue******************************************");
        System.out.printf("Name: %s, Description: %s, Price: %s, # In Stock: %s%n", basketball.getName(), basketball.getDescription(), String.valueOf(basketball.getPrice()), String.valueOf(basketball.getStock()));
        System.out.printf("Name: %s, Description: %s, Price: %s, # In Stock: %s%n", football.getName(), football.getDescription(), String.valueOf(football.getPrice()), String.valueOf(football.getStock()));
        System.out.printf("Name: %s, Description: %s, Price: %s, # In Stock: %s%n", jeans.getName(), jeans.getDescription(), String.valueOf(jeans.getPrice()), String.valueOf(jeans.getStock()));
        System.out.printf("Name: %s, Description: %s, Price: %s, # In Stock: %s%n", knives.getName(), knives.getDescription(), String.valueOf(knives.getPrice()), String.valueOf(knives.getStock()));
        System.out.printf("Name: %s, Description: %s, Price: %s, # In Stock: %s%n", plates.getName(), plates.getDescription(), String.valueOf(plates.getPrice()), String.valueOf(plates.getStock()));
        System.out.printf("Name: %s, Description: %s, Price: %s, # In Stock: %s%n", shirt.getName(), shirt.getDescription(), String.valueOf(shirt.getPrice()), String.valueOf(shirt.getStock()));
        System.out.printf("Name: %s, Description: %s, Price: %s, # In Stock: %s%n", shoes.getName(), shoes.getDescription(), String.valueOf(shoes.getPrice()), String.valueOf(shoes.getStock()));

    }

    List<String> getProductsList(){
        List<String> productsList = new ArrayList<String>();
        productsList.add(basketball.getName().toLowerCase());
        productsList.add(football.getName().toLowerCase());
        productsList.add(jeans.getName().toLowerCase());
        productsList.add(knives.getName().toLowerCase());
        productsList.add(plates.getName().toLowerCase());
        productsList.add(shirt.getName().toLowerCase());
        productsList.add(shoes.getName().toLowerCase());

        return productsList;
    }

    int checkStock(Product product, int requestedQuantity) {
        if (product.getStock() == StockCheck.OUT_OF_STOCK.ordinal()) {
            return StockCheck.OUT_OF_STOCK.ordinal();
        }
        else if (requestedQuantity > product.getStock()) {
            return StockCheck.LOW_STOCK.ordinal();
        }
        else {
            return StockCheck.IN_STOCK.ordinal();
        }
    }

    void updateStock(Product product, int soldQuantity) {
        product.setStock(product.getStock() - soldQuantity);
    }

    double calculateProductTotal(Product product, int quantity) {
        double total = quantity * product.getPrice();
        return total;
    }
}
