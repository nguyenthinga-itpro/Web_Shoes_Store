/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.CartDAO;
import dao.DAO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author dell
 */
public class Cart {
//    private int id;
//    private String name;
//    private String image;
//    private int amount;
//    private double price;
//    private double totalPrice;
//
//    public Cart() {
//    }
//
//    public Cart(int id, String name, String image, int amount, double price, double totalPrice) {
//        this.id = id;
//        this.name = name;
//        this.image = image;
//        this.amount = amount;
//        this.price = price;
//        this.totalPrice = totalPrice;
//    }
//
//   
//
//    
//
//    
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//   
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//    
//
//    @Override
//    public String toString() {
//        return "Cart{" + "id=" + id + ", name=" + name + ", amount=" + amount + ", price=" + price + '}';
//    }

    private List<Product> items;

    public Cart() {
    }

    public Cart(List<Product> items) {
        this.items = items;
    }

    public void add(Product ci, int sizeId) {
        for (Product product : items) {
            if (ci.getId() == product.getId() && sizeId == product.getSizeInCart().getSizeID()) {
                CartDAO dao = new CartDAO();
                SizeDetail size = dao.getProductSizesByProductIDAndSizeID(product.getId(), sizeId);
                if ((product.getNumberInCart() + 1) <= size.getQuantity()) {
                    product.setNumberInCart(product.getNumberInCart() + 1);
                }
                return;
            }
        }
        items.add(ci);
    }

    public void minus(Product ci, int sizeId) {
        for (Product product : items) {
            if (ci.getId() == product.getId() && sizeId == product.getSizeInCart().getSizeID()) {
                if (product.getNumberInCart() > 1) {
                    product.setNumberInCart(product.getNumberInCart() - 1);
                } else {
                    items.remove(product);
                }
                return;
            }
        }
    }

    public void remove(int id, int sizeId) {
        for (Product product : items) {
            if (product.getId() == id && sizeId == product.getSizeInCart().getSizeID()) {
                items.remove(product);
                return;
            }
        }
    }

    public double getAmount() {
        double s = 0;
        for (Product product : items) {
            s += product.getPrice() * product.getNumberInCart();
        }
        return Math.round(s * 100.0) / 100.0;
    }

    public List<Product> getItems() {
        return items;
    }

}
