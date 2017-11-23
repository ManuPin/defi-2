/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

import java.util.Collection;
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
public Collection rechercheParNom( String Name){
        Query q =em.createNamedQuery(Name);
       
    
}
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
