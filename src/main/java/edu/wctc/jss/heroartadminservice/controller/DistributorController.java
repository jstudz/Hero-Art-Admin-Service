package edu.wctc.jss.heroartadminservice.controller;

import edu.wctc.jss.heroartadminservice.entity.Distributor;
import edu.wctc.jss.heroartadminservice.service.DistributorService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DistributorController extends HttpServlet {

    private static final String LIST_DISTRIBUTORS_ACTION = "listDistributors";
    private static final String ADD_DISTRIBUTOR_ACTION = "addDistributor";
    private static final String ADD_EDIT_DISTRIBUTOR_ACTION = "addEditDistributor";
    private static final String HOME_ACTION = "goHome";
    private static final String LOG_OFF_ACTION = "logOff";
    private static final String SAVE_ACTION = "save";
    private static final String ACTION_PARAM = "action";
    private static final String HOME_PAGE = "/index.jsp";
    private static final String LIST_DISTRIBUTORS_PAGE = "/listDistributorsRest.jsp";
    private static final String ADD_EDIT_DISTRIBUTOR_PAGE = "/addEditDistributor.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = HOME_PAGE;
        String action = request.getParameter(ACTION_PARAM);

        ServletContext sctx = getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
        DistributorService distService = (DistributorService) ctx.getBean("distributorService");

        try {
            switch (action) {
                case ADD_EDIT_DISTRIBUTOR_ACTION:
                    String addEdit = request.getParameter("addEdit");
                    /*
                     Checks to see if the user hit the Add/Edit Button or the Delete button
                     */
                    if (addEdit != null) {
                        if (request.getParameter("id") == null) {
                            Distributor distributor = new Distributor();
                            request.setAttribute("distributor", distributor);
                            destination = ADD_EDIT_DISTRIBUTOR_PAGE;
                        } else {
                            String id = request.getParameter("id");
                            Distributor distributor = distService.findByIdAndFetchProductsEagerly(id);
                            request.setAttribute("distributor", distributor);
                            destination = ADD_EDIT_DISTRIBUTOR_PAGE;
                        }
                    } else {
//                        String id = request.getParameter("id");
//                        Distributor distributor = distService.findByIdAndFetchProductsEagerly(id);
//                        distService.remove(distributor);
//                        List<Distributor> distributors = distService.findAll();
//                        request.setAttribute("distributors", distributors);
                        destination = LIST_DISTRIBUTORS_PAGE;
                    }
                    break;
                case LIST_DISTRIBUTORS_ACTION:
                    List<Distributor> distributors = distService.findAll();
                    request.setAttribute("distributors", distributors);
                    destination = LIST_DISTRIBUTORS_PAGE;
                    break;
                case SAVE_ACTION:
                    String id = request.getParameter("id");
                    /*
                     Checks to see if the id is set to blank or null (new distributor)
                     */
                    if (id.equals("") || id == null) {
                        /*
                         Creates a new distributor and adds it to the database
                         */
                        Distributor distributor = new Distributor();
                        distributor.setDistributorName(request.getParameter("name"));
                        distributor.setCity(request.getParameter("city"));
                        distributor.setState(request.getParameter("state"));
                        distService.edit(distributor);
                    } else {
                        Distributor distributor = new Distributor();
                        distributor.setDistributorId(Integer.parseInt(request.getParameter("id")));
                        distributor.setDistributorName(request.getParameter("name"));
                        distributor.setCity(request.getParameter("city"));
                        distributor.setState(request.getParameter("state"));
                        distService.edit(distributor);
                    }
                    
                    List<Distributor> dists = distService.findAll();
                    request.setAttribute("distributors", dists);
                    destination = LIST_DISTRIBUTORS_PAGE;
                    break;
                case HOME_ACTION:
                    /*
                     Sends the user back to the home page
                     */
                    destination = HOME_PAGE;
                    break;
                case LOG_OFF_ACTION:
                    /*
                     Ends the HttpSession when the user hits log off
                     */
                    break;
                 case ADD_DISTRIBUTOR_ACTION:
                    /*
                     Sends the user to the Add/Edit page without a Distributor
                     */
                    destination = ADD_EDIT_DISTRIBUTOR_PAGE;
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
