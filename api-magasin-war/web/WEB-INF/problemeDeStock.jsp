<%@page import="magasin.Produit"%>
<%@page import="magasin.Panier"%>
<%@page import="java.util.List"%>
<%@page import="magasin.ElementPanier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <h1> </h1> <h1> </h1>
           <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> 
        <p><tr> <th>Nom produit dans panier  </th> <th> Quantite </th>  <th> reste en stock apres commande</th>  <th> Prix unitaire</th>  <th> Prix </th>  </tr>
<%
    if ( session.getAttribute("sespanier") == null ){
    }
    else{
       Panier sespanier =  (Panier) session.getAttribute("sespanier");
       List<ElementPanier> panier = sespanier.getPanier();
     if (sespanier.quantiteSuperieurStock()){out.println(" !!!!!!  ATTENTION Quantite non presente en stock commande non passé   !!! ");}
       for (ElementPanier lepanier : panier){
         
       out.println("<tr> <th>" +  lepanier.getProduit().getNom()  +  "</th><th>" +   lepanier.getQuantiter() +  "</th><th>" + (lepanier.getProduit().getQuantitestock()-lepanier.getQuantiter()) +  "</th><th>" + lepanier.getProduit().getPrix() +  "</th><th>" + (lepanier.getProduit().getPrix()*lepanier.getQuantiter()) +"</th> </tr>" );
    }
                            
       out.println("<tr> <th>" +  "Somme : "  +  "</th><th>" + "" + "</th><th>"+   ""+  "</th><th>"  +  "</th><th>" + sespanier.prixTotalPanier() + "</th> </tr>" );
 
        }
   
%>   
         </p>
       <h2>  <p> 

<form action="magasin") target="_blank" method="POST">
<input type="submit" name="action" value="Cancel"></p></h2>
</form> 
    </body>
</html>
