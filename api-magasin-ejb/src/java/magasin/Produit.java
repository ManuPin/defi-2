/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Formation16
 */
@Entity
@Table(catalog = "", schema = "PRODUIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id")
    , @NamedQuery(name = "Produit.findByNom", query = "SELECT p FROM Produit p WHERE p.nom = :nom")
    , @NamedQuery(name = "Produit.findByPrix", query = "SELECT p FROM Produit p WHERE p.prix = :prix")
    , @NamedQuery(name = "Produit.findByQuantitestock", query = "SELECT p FROM Produit p WHERE p.quantitestock = :quantitestock")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Size(max = 255)
    @Column(length = 255)
    private String nom;
    private Float prix;
    private Integer quantitestock;
/**
 * Constructeur sans argument
 */
    public Produit() {
    }
/**
 * Constructeur avec comme argument String nom, Integer prix, Integer quantitestock
 * @param nom
 * @param prix
 * @param quantitestock 
 */
    public Produit(String nom, Float prix, Integer quantitestock) {
        this.nom = nom;
        this.prix = prix;
        this.quantitestock = quantitestock;
    }
    
/**
 * Constructeur avec unique parametre l'Id du porduit
 * @param id 
 */
    public Produit(Long id) {
        this.id = id;
    }
/**
 * retourne l'id de l'objet
 * @return 
 */
    public Long getId() {
        return id;
    }
/**
 * change l'id de l'objet
 * @param id 
 */
    public void setId(Long id) {
        this.id = id;
    }
/**
 * retourne le nom de l'objet
 * @return 
 */
    public String getNom() {
        return nom;
    }
/**
 * change le nom de l'objet produi
 * @param nom 
 */
    public void setNom(String nom) {
        this.nom = nom;
    }
/**
 * retourne le prix de l'objet
 * @return 
 */
    public Float getPrix() {
        return prix;
    }
/**
 * met à jour le prix du porduit 
 * @param prix 
 */
    public void setPrix(Float prix) {
        this.prix = prix;
    }
/**
 * retourne la quantite de produit en stock
 * @return 
 */
    public Integer getQuantitestock() {
        return quantitestock;
    }
/**
 * met à jour la quantite de produit en stock
 * @param quantitestock 
 */
    public void setQuantitestock(Integer quantitestock) {
        this.quantitestock = quantitestock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
/**
 * 
 * @return 
 */
    @Override
    public String toString() {
        return "magasin.Produit[ id=" + id + " ]";
    }
    
}
