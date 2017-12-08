/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

/**
 *Class des produits (Element) du panier et la quantite
 * 
 */
public class ElementPanier {
    private Produit produit;
    private Integer quantiter;

 /**
  * Construction d'Element Panier avec l'objet Produit et sa quantite
  * @param produit
  * @param quantiter 
  */
    public ElementPanier(Produit produit, Integer quantiter) {
        this.produit = produit;
        this.quantiter = quantiter;
    }
/**
 * Retourne l'objet Produit de l'element du panier
 * @return 
 */
    public Produit getProduit() {
        return produit;
    }
    /**
     * methode retourne pour l'element du panier, son prix * quantite
     * @return 
     */
   public Float getSubTotal(){      
        return produit.getPrix()*quantiter.floatValue() ;
    }
   /**
    * Methode permettant de modifier le type de Produit
    * @param produit 
    */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
/**
 * Methode permettant de retourner la quantite du porduit dans le panier
 * @return 
 */
    public Integer getQuantiter() {
        return quantiter;
    }
/**
 * Methode pour mettre à jour la quantite de produit dans le panier
 * @param quantiter 
 */
    public void setQuantiter(Integer quantiter) {
        this.quantiter = quantiter;
    }
    
}

