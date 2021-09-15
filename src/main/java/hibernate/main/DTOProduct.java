package hibernate.main;

import hibernate.model.Product;

public class DTOProduct {

    public static void transferProduct(Product product) {
        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setName("Note 20");
        System.out.println(product1.getName());
    }

    public static Product saveNewProduct() {
        Product product = new Product();
        product.setName("Note 20");
        return product;
    }
}
