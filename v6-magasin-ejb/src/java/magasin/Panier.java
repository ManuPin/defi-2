/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;

/**
 *
 * @author Formation16
 */
@Stateful
public class Panier implements PanierLocal {
    private List<ElementPanier> elementPanier;
   

    @PostConstruct
    public void initialize(){
        elementPanier = new ArrayList<ElementPanier>();
        
    }
    
    
    @PreDestroy
    public void clear(){
        elementPanier = null;
    }
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
    public List<ElementPanier> getPanier(){
        return elementPanier;
    }
    public void videPanier(){
        elementPanier.clear();
    }
    public void PanierZero(){
       
        for (ElementPanier chaqueproduit : elementPanier ){
            chaqueproduit.setQuantiter(0);
        }
    }
}
