package com.products.demo;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsRepository {

    private List<Product> productList;


    public ProductsRepository(List<Product> productList) {
        this.productList = productList;
        productList.add(new Product("Chleb", 3.50, Category.GROCERIES));
        productList.add(new Product("Makaron", 5.50, Category.GROCERIES));
        productList.add(new Product("Mop", 20.50, Category.HOUSEHOLD_ITEMS));
        productList.add(new Product("Płyn do mycia naczyń", 10.50, Category.HOUSEHOLD_ITEMS));
        productList.add(new Product("Inny produkt", 15.50, Category.ANOTHER));

    }

    public List<Product> getAll() {
        return productList;
    }

    public boolean add(Product product) {
        if (productList.contains(product)) {
            return false;
        } else {
            productList.add(product);
            return true;
        }
    }

}
