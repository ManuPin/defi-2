/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Formation16
 */
public class magasin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private FacadeLocal FacadeBean;
    @EJB
    private PanierLocal PanierBean;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if ((request.getParameter("action") != null)){
            String Action = request.getParameter("action");
            if (Action.contentEquals("Cancel")){ PanierBean.videPanier();}
            if (Action.contentEquals("Commande")){ commande(request, response);}
        }


        
        if ((request.getParameter("panier") != null)){
                /* Si le parametre panier alors ajout du produit en parametre dans le panier*/
                long numprod = Long.parseLong(request.getParameter("panier"));
                          PanierBean.addproduit(FacadeBean.rechercheProduitId(numprod));    
            }
        affichageProduit(request, response);

    }
protected void commande (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    boolean commandeOk = true;
    
    for(ElementPanier element : PanierBean.getPanier()){
        int restant = element.getProduit().getQuantitestock() - element.getQuantiter();
        if( 0 > restant){ commandeOk = false;}
    }
    
    if (commandeOk == true){
        try {
            for(ElementPanier element : PanierBean.getPanier()){
        FacadeBean.CommandeQuantiteProduit(element.getProduit().getId(), element.getQuantiter());
            }
        
        }catch(Exception ex) {
            affichageProblemeStock(request, response);
//            this.getServletContext().getRequestDispatcher( "/WEB-INF/ProblemeStock.jsp").forward( request, response );
        }
    }
    else{
        affichageProblemeStock(request, response);
//        this.getServletContext().getRequestDispatcher( "/WEB-INF/ProblemeStock.jsp").forward( request, response );
    }
        

affichageCommande(request, response);

}
        
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
protected void affichageProduit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Magasin Apiculture</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Magasin Apiculture</h1>");
            out.println(" <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> ");
            out.println("<tr> <th>Indice/Reference </th> <th>Nom</th>    <th>Prix</th> <th>Quantite </th> <th>Commande </th>  </tr>");
            FacadeBean.ensembleProduit().forEach(produ->{    
            out.println("<tr> <th>" + produ.getId() + "</th><th>" +  produ.getNom() + "</th><th>" + produ.getPrix() + "</th><th> " + produ.getQuantitestock() + "</th><th> " + "<a href=\"magasin?panier=" + produ.getId()  + "\"> +1 </a> </tr>");
            });

                /* Si le parametre panier alors ajout du produit en parametre dans le panier*/
            out.println( "  <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\">" );
            out.println( " <p><tr> <th>Nom produit dans panier  </th> <th> Quantite </th>  <th> reste en stock apres commande</th>  <th> Prix unitaire</th>  <th> Prix </th>  </tr>");
            PanierBean.getPanier().forEach(element->{
            Integer deltastock = element.getProduit().getQuantitestock()- element.getQuantiter();
            out.println("<tr> <th>"+ element.getProduit().getNom() +"</th> <th>"+ element.getQuantiter() +"</th> <th>" + deltastock +"</th> <th>" + element.getProduit().getPrix() +"</th> </tr>");
            });

        if ((request.getParameter("action") != null)){
            out.println( "Action : " + request.getParameter("action"));
            String Action = request.getParameter("action");
//            Action.contentEquals("Cancel")
            if (Action.contentEquals("Cancel")){ out.println( "Reset re : " + request.getParameter("action"));}
            if (Action.contentEquals("Commande")){ out.println( "Commande re : " + request.getParameter("action")); }
        }
            out.println("</body>");
out.println("<form action=\"magasin\" target=\"_blank\" method=\"GET\">");
out.println("<input type=\"submit\" name=\"action\" value=\"Commande\"></p></h2>");
out.println("</form> ");
      
//out.println("<a href=\"magasin?action=reset\" > Reset Session <a> ");

out.println("<form action=\"magasin\") target=\"_blank\" method=\"GET\">");
out.println("<input type=\"submit\" name=\"action\" value=\"Cancel\"></p></h2>");
out.println("</form> ");
        
            out.println("</html>");
        }
}
protected void affichageCommande(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Magasin Apiculture</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Magasin Apiculture Commande</h1>");
 
                /* Si le parametre panier alors ajout du produit en parametre dans le panier*/
            out.println( "  <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\">" );
            out.println( " <p><tr> <th>Nom produit dans panier  </th> <th> Quantite </th>  <th> reste en stock apres commande</th>  <th> Prix unitaire</th>  <th> Prix </th>  </tr>");
            PanierBean.getPanier().forEach(element->{
            Integer deltastock = element.getProduit().getQuantitestock()- element.getQuantiter();
            out.println("<tr> <th>"+ element.getProduit().getNom() +"</th> <th>"+ element.getQuantiter() +"</th> <th>" + deltastock +"</th> <th>" + element.getProduit().getPrix() +"</th> </tr>");
            });

        if ((request.getParameter("action") != null)){
            out.println( "Action : " + request.getParameter("action"));
            String Action = request.getParameter("action");
//            Action.contentEquals("Cancel")
            if (Action.contentEquals("Cancel")){ out.println( "Reset re : " + request.getParameter("action"));}
            if (Action.contentEquals("Commande")){ out.println( "Commande re : " + request.getParameter("action")); }
        }
            out.println(" <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> ");
            out.println("<tr> <th>Indice/Reference </th> <th>Nom</th>    <th>Prix</th> <th>Quantite </th> <th>Commande </th>  </tr>");
            FacadeBean.ensembleProduit().forEach(produ->{    
            out.println("<tr> <th>" + produ.getId() + "</th><th>" +  produ.getNom() + "</th><th>" + produ.getPrix() + "</th><th> " + produ.getQuantitestock() + "</th><th> " + "<a href=\"magasin?panier=" + produ.getId()  + "\"> +1 </a> </tr>");
            });

            out.println("</body>");
out.println("<form action=\"magasin\" target=\"_blank\" method=\"GET\">");
out.println("<input type=\"submit\" name=\"action\" value=\"Commande\"></p></h2>");
out.println("</form> ");
      
//out.println("<a href=\"magasin?action=reset\" > Reset Session <a> ");

out.println("<form action=\"magasin\") target=\"_blank\" method=\"GET\">");
out.println("<input type=\"submit\" name=\"action\" value=\"Cancel\"></p></h2>");
out.println("</form> ");
        
            out.println("</html>");
        }
}
protected void affichageProblemeStock(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Magasin Apiculture</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Magasin Apiculture Probleme de stock</h1>");
 
                /* Si le parametre panier alors ajout du produit en parametre dans le panier*/
            out.println( "  <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\">" );
            out.println( " <p><tr> <th>Nom produit dans panier  </th> <th> Quantite </th>  <th> reste en stock apres commande</th>  <th> Prix unitaire</th>  <th> Prix </th>  </tr>");
            PanierBean.getPanier().forEach(element->{
            Integer deltastock = element.getProduit().getQuantitestock()- element.getQuantiter();
            out.println("<tr> <th>"+ element.getProduit().getNom() +"</th> <th>"+ element.getQuantiter() +"</th> <th>" + deltastock +"</th> <th>" + element.getProduit().getPrix() +"</th> </tr>");
            });

        if ((request.getParameter("action") != null)){
            out.println( "Action : " + request.getParameter("action"));
            String Action = request.getParameter("action");
//            Action.contentEquals("Cancel")
            if (Action.contentEquals("Cancel")){ out.println( "Reset re : " + request.getParameter("action"));}
            if (Action.contentEquals("Commande")){ out.println( "Commande re : " + request.getParameter("action")); }
        }
            out.println(" <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> ");
            out.println("<tr> <th>Indice/Reference </th> <th>Nom</th>    <th>Prix</th> <th>Quantite </th> <th>Commande </th>  </tr>");
            FacadeBean.ensembleProduit().forEach(produ->{    
            out.println("<tr> <th>" + produ.getId() + "</th><th>" +  produ.getNom() + "</th><th>" + produ.getPrix() + "</th><th> " + produ.getQuantitestock() + "</th><th> " + "<a href=\"magasin?panier=" + produ.getId()  + "\"> +1 </a> </tr>");
            });

            out.println("</body>");
out.println("<form action=\"magasin\" target=\"_blank\" method=\"GET\">");
out.println("<input type=\"submit\" name=\"action\" value=\"Commande\"></p></h2>");
out.println("</form> ");
      
//out.println("<a href=\"magasin?action=reset\" > Reset Session <a> ");

out.println("<form action=\"magasin\") target=\"_blank\" method=\"GET\">");
out.println("<input type=\"submit\" name=\"action\" value=\"Cancel\"></p></h2>");
out.println("</form> ");
        
            out.println("</html>");
        }
}
}

