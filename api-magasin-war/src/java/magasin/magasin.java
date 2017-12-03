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
import magasin.Panier;

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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);

        
        List<Produit> produit = FacadeBean.ensembleProduit();
        session.setAttribute("listeProduit", produit);
            
            if ((request.getParameter("panier") != null)){
                long numprod = Long.parseLong(request.getParameter("panier"));
                if ( session.getAttribute("sespanier") == null ){ 
                Panier sespanier = new Panier(FacadeBean.rechercheProduitId(numprod),1);
               session.setAttribute("sespanier",sespanier);
                }
                else {
                  Panier sespanier =  (Panier) session.getAttribute("sespanier");
                   sespanier.addproduit(FacadeBean.rechercheProduitId(numprod));
//                  sespanier.getPanier().forEach(element->{
////                       out.println("<h1> sespannier est present " + element.getProduit().getNom() +  "  " + element.getQuantiter() + "</h1>");
//                   });
                }
                       
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
                               
                               
                               
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet magasin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet magasin at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        
         if ((request.getParameter("action") != null)){
            String Action = request.getParameter("action");
            if (Action.contentEquals("Cancel")){session.invalidate();}
            if (Action.contentEquals("Commande")){ commande(request, response);}
       }

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
    
protected void commande (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    
    HttpSession session = request.getSession(true);
           if ( session.getAttribute("sespanier") == null ){  }
            else{
            Panier sespanier =  (Panier) session.getAttribute("sespanier");
            List<ElementPanier> panier = sespanier.getPanier();
            
    if (sespanier.quantiteSuperieurStock()){
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
}
