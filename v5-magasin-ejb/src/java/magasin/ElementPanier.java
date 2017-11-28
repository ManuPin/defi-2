/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

/**
 *
 * @author Formation16
 */
public class ElementPanier {
    private Produit produit;
    private Integer quantiter;
    
    public Integer getSubTotal(){
        return produit.getPrix()*quantiter;
    }

    public ElementPanier(Produit produit, Integer quantiter) {
        this.produit = produit;
        this.quantiter = quantiter;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getQuantiter() {
        return quantiter;
    }

    public void setQuantiter(Integer quantiter) {
        this.quantiter = quantiter;
    }
    
}
