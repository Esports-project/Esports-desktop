package Esprit.Entities;

import Esprit.Services.ServiceProduit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            entries.clear();
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

    public int calculateNumber(){
        int total=0;
        for(CartEntry entry:entries.values()){
            total++;
        }
        return total;
    }

    public List <CartEntry> getEntries(){
        return new ArrayList<>(entries.values());
    }
}