<%@page import="javax.ejb.EJB"%>
<%@page import="java.io.IOException"%>
<%@page import="magasin.FacadeLocal"%>
<%@page import="magasin.Facade"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="magasin.magasin"%>
<%@page import="java.util.List"%>
<%@page import="magasin.Produit"%>
<%@page import="magasin.Panier"%>
<%@page import="magasin.ElementPanier"%>
<%! @javax.ejb.EJB FacadeLocal FacadeBean; %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Magasin</title>
    </head>
    <body>
    <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> 
    <tr> <th>Indice/Reference </th> <th>Nom</th>    <th>Prix</th> <th>Quantite </th> <th>Mise à jour Stock </th>   </tr>
  
<% 
    List<Produit> produit = (List) session.getAttribute("listeProduit");
    for (Produit produ : produit){
     out.println("<tr> <th>" + produ.getId() + "</th><th>" +  produ.getNom() + "</th><th>" + produ.getPrix() + "</th><th> " + produ.getQuantitestock() + "</th><th> <form action=\"init\" target=\"_blank\" method=\"POST\" > <input type=\"text\" name=\"Produitstock\" value=\"" + produ.getId() + "\" > <input type=\"text\" name=\"valeur\" value=\""+ produ.getQuantitestock() + "\"> <input type=\"submit\"> </form>  </tr>");
  }

%>

        <h1> </h1> <h1> </h1>
           <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> 
        <p><tr> <th>Nom produit dans panier  </th> <th> Quantite </th>  <th> reste en stock apres commande</th>  <th> Prix unitaire</th>  <th> Prix </th>  </tr>
<%
    if ( session.getAttribute("sespanier") == null ){
    }
    else{
       Panier sespanier =  (Panier) session.getAttribute("sespanier");
       List<ElementPanier> panier = sespanier.getPanier();
     if (sespanier.quantiteSuperieurStock()){out.println("ATTENTION Quantite non presente en stock ");}
       for (ElementPanier lepanier : panier){
         
       out.println("<tr> <th>" +  lepanier.getProduit().getNom()  +  "</th><th>" +   lepanier.getQuantiter() +  "</th><th>" + (lepanier.getProduit().getQuantitestock()-lepanier.getQuantiter()) +  "</th><th>" + lepanier.getProduit().getPrix() +  "</th><th>" + (lepanier.getProduit().getPrix()*lepanier.getQuantiter()) +"</th> </tr>" );
    }
                            
       out.println("<tr> <th>" +  "Somme : "  +  "</th><th>" + "" + "</th><th>"+   ""+  "</th><th>"  +  "</th><th>" + sespanier.prixTotalPanier() + "</th> </tr>" );
 
        }
   
%>
        </p>
       <h2>  <p> 

<form action="magasin" target="_blank" method="POST">
<input type="submit" name="action" value="Magasin"></p></h2>
</form> 
<form action="init" target="_blank" method="POST">
<input type="submit" name="action" value="Init"></p></h2>
</form>
     </body>
</html>
           