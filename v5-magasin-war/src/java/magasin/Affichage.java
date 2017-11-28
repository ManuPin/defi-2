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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Formation16
 */
public class Affichage extends HttpServlet {

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
    private PanierLocal PanierBean;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(true);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Affichage</title>");            
            out.println("</head>");
            out.println("<body>");
           
  List<Produit> produit = FacadeBean.ensembleProduit();
 session.setAttribute("listeProduit", produit);
 
//  List<ElementPanier> elementPanier = PanierBean.getPanier();
  out.println(" <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> ");
  out.println("<tr> <th>Indice/Reference </th> <th>Nom</th>    <th>Prix</th> <th>Quantite </th> <th>Commande </th>  </tr>");
  produit.forEach(produ-> {
  
  out.println("<tr> <th>" + produ.getId() + "</th><th>" +  produ.getNom() + "</th><th>" + produ.getPrix() + "</th><th> " + produ.getQuantitestock() + "</th><th> " + "<a href=\"/magasin?panier=" + produ.getId()  + "\"> +1 </a> </tr>");
  });
            out.println("</body>");                                                                                                                                                     
            out.println("</html>");
             this.getServletContext().getRequestDispatcher( "/WEB-INF/Affichage.jsp").forward( request, response );
        }
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

}
