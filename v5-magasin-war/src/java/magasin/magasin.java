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
        try (PrintWriter out = response.getWriter()) {
             HttpSession session = request.getSession(true);
             List<Produit> produit = FacadeBean.ensembleProduit();
            session.setAttribute("listeProduit", produit);
            /* TODO output your page here. You may use following sample code. */
//            HttpSession session = request.getSession(true);
//            session.getAttribute("panier");
                       if ((request.getParameter("panier") != null)){
                           String NumProduit = request.getParameter("panier");
//                           //int numprod = Integer.parseInt (NumProduit);
                           long numprod = Long.parseLong(NumProduit);
                           out.println(numprod);
                    PanierBean.addproduit(FacadeBean.rechercheProduitId(numprod));    
                    
                          out.println(FacadeBean.rechercheProduitId(numprod).getNom());
                           List<ElementPanier> sespanier = PanierBean.getPanier();
                           out.println("tetetete");
                           session.setAttribute("sespanier",sespanier);
//                           
               this.getServletContext().getRequestDispatcher( "/WEB-INF/Affichage.jsp").forward( request, response );    
//this.getServletContext().getRequestDispatcher( "/Affichage").forward( request, response );    
            }
           else{
//                           if ((request.getParameter("reset") != null)){
//                               PanierBean.clear();
//                               request.;
//                           }
// this.getServletContext().getRequestDispatcher( "/Affichage").forward( request, response );                          
              this.getServletContext().getRequestDispatcher( "/WEB-INF/Affichage.jsp").forward( request, response );
           }
                       
            long number = 751;
            Integer quantite = 115;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet magasin</title>");            
            out.println("</head>");
            out.println("<body>");
//            FacadeBean.rechercheProduitId(number);
//            out.println("<h1>Servlet magasin at " + FacadeBean.rechercheProduitId(number).getNom() + "</h1>");
//              out.println("<h1>Servlet magasin at " + FacadeBean.rechercheProduitId(number).getQuantitestock() + "</h1>");
//    this.getServletContext().getRequestDispatcher( "/Affichage").forward( request, response );
//    this.getServletContext().getRequestDispatcher( "/WEB-INF/Affichage.jsp").forward( request, response );
//              List<Produit> produit = FacadeBean.ensembleProduit();
            
        out.println(" <style>table,th,td{border : 1px solid black;border-collapse:collapse;}</style> <table style=\"width:100%\"> ");
        out.println("<tr> <th>Indice/Reference </th> <th>Nom</th>    <th>Prix</th> <th>Quantite </th>   </tr>");
 
  FacadeBean.modifyStockQuantite(number, quantite);
//  out.println("<h1>Servlet magasin at " + FacadeBean.rechercheProduitId(number).getQuantitestock() + "</h1>");
  produit.forEach(produ-> {
  out.println("<tr> <th>" + produ.getId() + "</th><th>" +  produ.getNom() + "</th><th>" + produ.getPrix() + "</th><th> " + produ.getQuantitestock() + "</th></tr>");
  });
List<ElementPanier> sepanier = PanierBean.getPanier();



sepanier.forEach(pan-> {
  out.println("<tr> <th>" + pan.getProduit().getNom() + "</th><th>" +  pan.getQuantiter() + "</th> </tr>");
  });

          out.println("</body>");
            out.println("</html>");
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
