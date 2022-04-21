package Esprit.entities;

import Esprit.services.ServiceProduit;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private static ShoppingCart INSTANCE;

    public static ShoppingCart getInstance(){
        if(INSTANCE==null){
            INSTANCE=new ShoppingCart();
        }
        return INSTANCE;
    }
    private Map<String, CartEntry> entries;

    public ShoppingCart() {
        this.entries = new HashMap<>();
    }

    public void addProduct(String referance) {
        ServiceProduit serviceProduit = new ServiceProduit();
        CartEntry productEntry = entries.get(referance);
        if (productEntry != null) {
            productEntry.increaseQuantity();
        } else {
            Produit produit = serviceProduit.getProduitByRef(referance);
            CartEntry entry = new CartEntry(produit, 1);
            entries.put(referance, entry);
        }
    }

    public void removeProduct(String referance) {
        CartEntry productEntry = entries.get(referance);
        if (productEntry != null) {
            productEntry.decreaseQuantity();
        }
    }

    public int getQuantity(String referance){
        CartEntry entry = entries.get(referance);
        if(entry!=null){
            return entry.getQuantity();
        }
        else return 0;
    }

    public float calculateTotal(){
        float total=0;
        for(CartEntry entry:entries.values()){
            float entryCost=entry.getProduit().getPrice()*entry.getQuantity();
            total=total+entryCost;
        }
        return total;
    }
}
