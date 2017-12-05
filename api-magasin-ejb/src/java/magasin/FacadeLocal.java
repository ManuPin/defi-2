/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * FacadeLocal, permet d'affiche les methodes de Facade
 */
@Local
public interface FacadeLocal {
        public void init();
        public Produit rechercheProduitId (Long id);
        public List<Produit> ensembleProduit();
        public void modifyStockQuantite (Long id, Integer quantite)throws Exception;
        public List<Produit> rechercheProduitNom(String nom);
        public void CommandeQuantiteProduit (Long id, Integer quantite)throws Exception;
}
