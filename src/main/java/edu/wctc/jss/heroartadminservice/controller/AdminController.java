/*
 The admin controller takes request from the view and passes it along to the model
 which returns some data that is forwarded back to the view and displayed for the user
 */
package edu.wctc.jss.heroartadminservice.controller;

import edu.wctc.jss.heroartadminservice.entity.Distributor;
import edu.wctc.jss.heroartadminservice.entity.Product;
import edu.wctc.jss.heroartadminservice.service.DistributorService;
import edu.wctc.jss.heroartadminservice.service.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AdminController extends HttpServlet {

    private static final String GET_NAME_ACTION = "getName";
    private static final String ADD_EDIT_ACTION = "addEdit";
    private static final String LIST_PRODUCT_ACTION = "listProduct";
    private static final String ADD_PRODUCT_ACTION = "addProduct";
    private static final String HOME_ACTION = "goHome";
    private static final String LOG_OFF_ACTION = "logOff";
    private static final String SAVE_ACTION = "save";
    private static final String ACTION_PARAM = "action";
    private static final String HOME_PAGE = "/index.jsp";
    private static final String LIST_PAGE = "/listProduct.jsp";
    private static final String ADD_EDIT_PAGE = "/addEditProduct.jsp";
    
    private String emailAddress;
    private String name;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        String destination = HOME_PAGE;
        String action = request.getParameter(ACTION_PARAM);
        
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        ProductService prodServ = (ProductService) ctx.getBean("productService");
        DistributorService distService = (DistributorService) ctx.getBean("distributorService");
        /*
         Use of HttpSession and ServletContext, ServletContext gets the email address
         from the web.xml file and passes it into the view which is utilized for contact
         information on the footer
         */
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        emailAddress = context.getInitParameter("email");
        context.setAttribute("email", emailAddress);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        session.setAttribute("userName", userName);
        /*
         Checks to see which action was called from the View and finds the appropriate response
         and passes information to the model and returns it to the view for the user
         */
        try {
            switch (action) {
                case ADD_EDIT_ACTION:
                    String addEdit = request.getParameter("addEdit");
                    /*
                     Checks to see if the user hit the Add/Edit Button or the Delete Button
                     */
                    if (addEdit != null) {
                        /*
                         Checks to see if the product ID is null, if it is they are redirected to
                         the Add/Edit Page, if it is not then the controller searches for the product
                         via the product ID
                         */
                        if (request.getParameter("id") == null) {
                            Product product = new Product();
                            request.setAttribute("product", product);
                            destination = ADD_EDIT_PAGE;
                        } else {
                            String id = request.getParameter("id");
                            Product product = prodServ.findById(id);
                            request.setAttribute("product", product);
                            destination = ADD_EDIT_PAGE;
                        }
                    } else {
                        /*
                         Finds the selected product and then deletes it from the database
                         */
                        String id = request.getParameter("id");
                        Product product = prodServ.findById(id);
                        prodServ.remove(product);
                        List<Product> products = prodServ.findAll();
                        request.setAttribute("products", products);
                        destination = LIST_PAGE;
                    }
                    List<Distributor> dists = distService.findAll();
                    request.setAttribute("distributors", dists);
                    break;
                case LIST_PRODUCT_ACTION: {
                    /*
                     Finds all products and returns the List to the correct jsp
                     */
                    List<Product> products = prodServ.findAll();
                    request.setAttribute("products", products);
                    destination = LIST_PAGE;
                    break;
                }
                case SAVE_ACTION: {
                    String id = request.getParameter("id");
                    String distributorId = request.getParameter("distributorId");
                    /*
                     Checks to see if the id is set to 0 or null (new product)
                     */
                    if (id.equals("") || id == null) {
                        /*
                         Creates a new product and adds it to the database
                         */
                        Product product = new Product();
                        product.setProductName(request.getParameter("name"));
                        product.setProductDescription(request.getParameter("description"));
                        product.setProductImg(request.getParameter("imgUrl"));
                        product.setProductPrice(Double.parseDouble(request.getParameter("price")));
                        
                        Distributor distributor = null;
                        if (distributorId != null) {
                            distributor = distService.findById(distributorId);
                            product.setDistributorId(distributor);
                        }
                        
                        prodServ.edit(product);
                    } else {
                        /*
                         Updates an existing product in the database
                         */
                        Product product = new Product();
                        product.setProductId(Integer.parseInt(request.getParameter("id")));
                        product.setProductName(request.getParameter("name"));
                        product.setProductDescription(request.getParameter("description"));
                        product.setProductImg(request.getParameter("imgUrl"));
                        product.setProductPrice(Double.parseDouble(request.getParameter("price")));
                        
                        Distributor distributor = null;
                        if (distributorId != null) {
                            distributor = distService.findById(distributorId);
                            product.setDistributorId(distributor);
                        }
                        
                        prodServ.edit(product);
                    }
                    List<Product> products = prodServ.findAll();
                    request.setAttribute("products", products);
                    destination = LIST_PAGE;
                    break;
                }
                case ADD_PRODUCT_ACTION:
                    /*
                     Sends the user directly to the add/edit page
                     */
                    List<Distributor> distributors = distService.findAll();
                    request.setAttribute("distributors", distributors);
                    destination = ADD_EDIT_PAGE;
                    break;
                case HOME_ACTION:
                    /*
                     Sends the user back to the home page
                     */
                    destination = HOME_PAGE;
                    break;
            }
        } catch (Exception err) {
            request.setAttribute("error", err.getCause().getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
