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
 * Class pour initialiser ou mettre a jour la quantite de stock
 */
public class init extends HttpServlet {
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

        
        List<Produit> produit = FacadeBean.ensembleProduit();
        session.setAttribute("listeProduit", produit);
        
        
        if ((request.getParameter("action") != null)){
            String Action = request.getParameter("action");
            if (Action.contentEquals("Cancel")){session.invalidate();}
            if (Action.contentEquals("Init")){ FacadeBean.init();}
       }
                 if ((request.getParameter("Produitstock") != null)){
                    long IdProduit =  Long.parseLong(request.getParameter("Produitstock"));
                    Integer Quantite = Integer.valueOf(request.getParameter("valeur"));
                    try {FacadeBean.modifyStockQuantite(IdProduit, Quantite);
                    Thread.sleep(4000);
                    }catch(Exception ex){
                        this.getServletContext().getRequestDispatcher( "/WEB-INF/miseajour.jsp").forward( request, response );
                    }
                    
                 }
                
       this.getServletContext().getRequestDispatcher( "/WEB-INF/miseajour.jsp").forward( request, response );   
      
       
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
        HttpSession session = request.getSession(true);

        
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
