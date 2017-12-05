/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

import java.util.ArrayList;
import java.util.List;


/**
 * Panier constituer de liste d'elementPanier : Produit et quantite
 * 
 */
public class Panier {
    private List<ElementPanier> elementPanier;

    public Panier(Produit produit, Integer nombre) {
        elementPanier = new ArrayList<ElementPanier>();
        elementPanier.add(new ElementPanier(produit,nombre));
        
//        this.elementPanier = elementPanier;
    }
    
    public void clear(){
        elementPanier = null;
    }
    /**
     * Ajoute une quantite de 1 au produit dans le panier, si le produit n'est pas deja present dans le panier, la methode ajoute le produit à la liste
     * @param produit 
     */
  
    public void addproduit(Produit produit){
        boolean produitFound = false;
        for (ElementPanier chaqueproduit : elementPanier ){
            if (chaqueproduit.getProduit().equals(produit)){
                chaqueproduit.setQuantiter(chaqueproduit.getQuantiter()+1);
                produitFound = true;
            }
 
        }
          if( !produitFound ){
                elementPanier.add(new ElementPanier(produit,1));
            }  
    }
    /**
     * retourne la liste des elements du panier
     * @return la liste des element du panier
     */
    public List<ElementPanier> getPanier(){
        return elementPanier;
    }
    public void videPanier(){
        elementPanier.clear();
    }
    /**
     * retourne le prix total du panier : somme des (quantite d'element * son prix )
     * @return le prix total
     */
    public float prixTotalPanier(){
        float prixTotal = 0;
        for (ElementPanier lepanier : elementPanier){
         prixTotal = prixTotal + (lepanier.getProduit().getPrix() * lepanier.getQuantiter()); 
        }
        return prixTotal;
    }
    /**
     * Cette methode retourne true si la quantite des elements du panier est diponible en stock 
     * @return true si il y a assez de stock disponible pour les elements qui sont dans le panier
     * False si une quantite d'un au moin des elements depasse ce qui est disponible en stock
     */
    public boolean quantiteSuperieurStock(){
        boolean quantiteSup = false;
        for (ElementPanier lepanier : elementPanier){
         if ( 0> lepanier.getProduit().getQuantitestock() - lepanier.getQuantiter() ){
             quantiteSup = true;
         }
        }
        return quantiteSup;
    }
       /**
        * remet à zero la quantite de produit de chaque element du panier
        */     
    public void PanierZero(){
       
        for (ElementPanier chaqueproduit : elementPanier ){
            chaqueproduit.setQuantiter(0);
        }
    }
}
     