package Esprit.services;

import Esprit.entities.Panier;
import Esprit.entities.Produit;
import java.util.Collection;
import java.util.HashMap;

public class ServicePanier {
    private HashMap<Produit, Panier> orders;

    public ServicePanier() {
        orders = new HashMap<Produit, Panier>();
    }

    public void addItem(Produit product, int quantity) {
        Panier newOrder = new Panier(product, quantity);
        orders.put(product, newOrder);
    }

    public boolean hasProduct(Produit product) {
        Panier order = orders.get(product);
        boolean hasProduct;
        if (order == null) {
            hasProduct = false;
        }
        else {
            hasProduct = true;
        }

        return hasProduct;
    }

    public void removeItem(Produit product) {
        orders.remove(product);
    }

    public boolean changeQuantity(Produit product, int quantity) {
        // Find the product in the cart
        Panier existingOrder = orders.get(product);
        if (existingOrder == null) {
            return false;
        }
        existingOrder.setQuantite(quantity);
        return true;
    }

    public int getOrderCount() {
        int totalOrderCount = 0;
        for (Panier order : orders.values()) {
            totalOrderCount += order.getQuantite();
        }
        return totalOrderCount;
    }

    public Collection<Panier> getOrders() {
        return orders.values();
    }

    public float getTotalOrderCost() {
        float totalCost = 0f;
        for (Panier order : orders.values()) {
            totalCost += order.getProduit().getPrice() * order.getQuantite();
        }
        return totalCost;
    }

    public int getProductQuantity(Produit product) {
        if (!this.hasProduct(product)) {
            return 0;
        }
        return orders.get(product).getQuantite();
    }

    public void updateProductQuantity(String product, int quantity) {
        orders.get(product).setQuantite(quantity);
    }

    public void empty() {
        orders.clear();
    }
}
