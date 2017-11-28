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
 * @author Formation16
 */
@Local
public interface PanierLocal {
    public void addproduit(Produit produit);
    public List<ElementPanier> getPanier();
}
