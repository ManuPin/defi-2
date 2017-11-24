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
 * @author Formation16
 */
@Stateless
public class Facade implements FacadeLocal {
@PersistenceContext
private EntityManager em;

public void init() {
        Produit gele = new Produit("gele royale",55, 15);
        Produit miel =  new Produit("miel",15, 10);
        Produit ruche = new Produit("ruche 12 Cadres",1, 12) ;
        Produit ruchette = new Produit("ruchette 7 Cadres",65, 8) ;
        Produit cadre = new Produit("cadre", 2 , 180) ;
        Produit enfumoire = new Produit("enfumoire", 20 , 20) ;
        Produit cire = new Produit("cire", 20 , 200) ;
        
        em.persist(cire);
        em.persist(miel);
        em.persist(ruche);
        em.persist(ruchette);
        em.persist(cadre);
        em.persist(cire);
    }
public Produit rechercheProduitId (Long id) {
    Produit monproduit = em.find(Produit.class, id);
    return monproduit;
}
public List<Produit> ensembleProduit(){
   Query query = em.createQuery("SELECT c FROM Produit c");
   List<Produit> produit = query.getResultList();
   return produit;
}

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
