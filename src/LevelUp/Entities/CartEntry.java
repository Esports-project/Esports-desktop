package Esprit.Entities;


public class CartEntry {
    private Produit produit;
    private int quantity;

    public CartEntry(Produit produit,int quantity){
        this.produit=produit;
        this.quantity=quantity;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(){
        if(this.quantity>0 && this.produit.getQuantity()>this.quantity){
            this.quantity++;
        }
    }

    public void decreaseQuantity(){
        if(this.quantity>0 && this.produit.getQuantity()>this.quantity){
            this.quantity--;
        }
    }


}