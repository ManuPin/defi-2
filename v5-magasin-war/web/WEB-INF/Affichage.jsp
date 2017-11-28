<%-- 
    Document   : Affichage
    Created on : 27 nov. 2017, 15:54:17
    Author     : Formation16
--%>

<%@page import="javax.ejb.EJB"%>
<%@page import="java.io.IOException"%>
<%@page import="magasin.FacadeLocal"%>
<%@page import="magasin.PanierLocal"%>
<%@page import="magasin.Facade"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="magasin.magasin"%>
<%@page import="java.util.List"%>
<%@page import="magasin.Produit"%>
<%@page import="magasin.Panier"%>
<%@page import="magasin.ElementPanier"%>
<%! @javax.ejb.EJB FacadeLocal FacadeBean; %>
<%! @javax.ejb.EJB PanierLocal PanierBean; %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Magasin</title>
    </head>
    <body>
    <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> 
    <tr> <th>Indice/Reference </th> <th>Nom</th>    <th>Prix</th> <th>Quantite </th> <th>Commande </th>   </tr>
  
<% 
    List<Produit> produit = (List) session.getAttribute("listeProduit");
    //out.println(FacadeBean.ensembleProduit());   <jsp:useBean id="produit" class="List<Produit>" scope="request"/>
    //List<Produit> produit = FacadeBean.ensembleProduit();
    // Map<Integer, Produit> mapproduit = (Map) session.getAttribute("listeProduit");
  // List<Produit> produit = FacadeBean.ensembleProduit();
  
   for (Produit produ : produit){
     out.println("<tr> <th>" + produ.getId() + "</th><th>" +  produ.getNom() + "</th><th>" + produ.getPrix() + "</th><th> " + produ.getQuantitestock() + "</th><th>" +   "<a href=\"Affichage?panier="+ produ.getId() +"\"> +1 <a> </tr>");
    out.println("<tr> <th>" + produ.getId() + "</th><th>" +  produ.getNom() + "</th><th>" + produ.getPrix() + "</th><th> " + produ.getQuantitestock() + "</th><th>" +   "<a href=\"magasin?panier="+ produ.getId() +"\"> +1 <a> </tr>");
    }

%>

        <h1> </h1> <h1> </h1>
           <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> 
        <p><tr> <th>Nom produit dans panier  </th> <th> Quantite </th>  <th> reste en stock apres commande</th>  <th> Prix unitaire</th>  <th> Prix </th>  </tr>
<%
    if ( session.getAttribute("sespanier") == null ){
    }
    else{
        List<ElementPanier> panier = (List) session.getAttribute("sespanier");
        float somme = 0;
    for (ElementPanier lepanier : panier){
       out.println("<tr> <th>" +  lepanier.getProduit().getNom()  +  "</th><th>" +   lepanier.getQuantiter() +  "</th><th>" + "" +  "</th><th>" + "" +  "</th><th>" + "" +"</th> </tr>" );
    }
                            
           out.println("<tr> <th>" +  "Somme : "  +  "</th><th>" + "" + "</th><th>"+   ""+  "</th><th>"  +  "</th><th>" + somme + "</th> </tr>" );
 
        }
   
%>
    </body>
</html>
