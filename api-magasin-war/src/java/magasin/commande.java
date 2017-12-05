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


public class commande extends HttpServlet {
@EJB
    private FacadeLocal FacadeBean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          HttpSession session = request.getSession(true);
           if ( session.getAttribute("sespanier") == null ){  }
            else{
            Panier sespanier =  (Panier) session.getAttribute("sespanier");
            List<ElementPanier> panier = sespanier.getPanier();
            
    if (!sespanier.quantiteSuperieurStock()){
        try {
            for(ElementPanier element : panier){
                FacadeBean.CommandeQuantiteProduit(element.getProduit().getId(), element.getQuantiter());
            }
        
        }catch(Exception ex) {
           this.getServletContext().getRequestDispatcher( "/WEB-INF/problemeDeStock.jsp").forward( request, response );
       }
    }
    else{
        this.getServletContext().getRequestDispatcher( "/WEB-INF/problemeDeStock.jsp").forward( request, response );
    }

this.getServletContext().getRequestDispatcher( "/WEB-INF/commande.jsp").forward( request, response );
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
