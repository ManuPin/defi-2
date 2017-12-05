/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * 
 */
@Stateless
public class Facade implements FacadeLocal {
@PersistenceContext
private EntityManager em;

/**
 * Methode permettant d'initialiser le stock de produit avec la quantite
 */
public void init() {
        Produit gele = new Produit("gele royale",55, 15);
        Produit miel =  new Produit("miel",15, 10);
        Produit ruche = new Produit("ruche 12 Cadres",1, 12) ;
        Produit ruchette = new Produit("ruchette 7 Cadres",65, 8) ;
        Produit cadre = new Produit("cadre", 2 , 180) ;
        Produit enfumoire = new Produit("enfumoire", 20 , 20) ;
        Produit cire = new Produit("cire", 20 , 200) ;
        
        em.persist(gele);
        em.persist(miel);
        em.persist(ruche);
        em.persist(ruchette);
        em.persist(cadre);
        em.persist(cire);
    }
/**
 * methote retourne l'objet produit lorsque l'on recherche le produit a partir de son id
 * @param id
 * @return 
 */
public Produit rechercheProduitId (Long id) {
    Produit monproduit = em.find(Produit.class, id);
    return monproduit;
}
/**
 * retourne la liste de produit correspondant a la recherche du nom produit si il en existe plusieurs.
 * @param nom
 * @return 
 */
public List<Produit> rechercheProduitNom(String nom){
   Query query = em.createQuery("SELECT c FROM Produit c ORDER BY c.id WHERE c.nom=:param");
   query.setParameter("param", nom);
   List<Produit> produit = query.getResultList();
   return produit;
}
/**
 * retourn la list d'objet produit
 * @return 
 */
public List<Produit> ensembleProduit(){
   Query query = em.createQuery("SELECT c FROM Produit c ORDER BY c.id");
   List<Produit> produit = query.getResultList();
   return produit;
}
/**
 * Methode pour mettre à jour la quantite en prenant l'id du produit pour la recherche du porduit
 * @param id : Id du produit à mettre à jour
 * @param quantite : qunatite de stock à mettre à jour.
 * @throws Exception 
 */
public void modifyStockQuantite (Long id, Integer quantite)throws Exception{
   //mise a jour quantite de stock et le faire persister.
  Produit monproduit = em.find(Produit.class, id);
  if (quantite<0 ) throw new Exception();
  else{
       monproduit.setQuantitestock(quantite);
  }
 
}
/**
 * Permet en fournissant Id du produit de commander donc retirer du stock la quantite fournit.
 * gere l'exeption si le stock n'est pas suffisant, la methode emet une exception.
 * @param id : Id du produit
 * @param quantite : Quantite du produit a retirer du stock
 * @throws Exception 
 */

public void CommandeQuantiteProduit (Long id, Integer quantite)throws Exception {
   //mise a jour quantite de stock et le faire persister.
   Produit monproduit = em.find(Produit.class, id);
 if ( 0> monproduit.getQuantitestock() - quantite )
     throw new Exception();
 else
 {
     monproduit.setQuantitestock(monproduit.getQuantitestock() - quantite);
     
 }
}
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
