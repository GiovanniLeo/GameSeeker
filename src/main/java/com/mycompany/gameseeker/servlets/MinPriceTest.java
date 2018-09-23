/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameseeker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.gameseeker.mediator.Mediator;
import com.mycompany.gameseeker.mongoDB.Result;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author raffa
 */
@WebServlet(name = "MinPriceTest", urlPatterns = {"/MinPriceTest"})
public class MinPriceTest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        ArrayList<Result> resultMatching = new ArrayList<>();
        ArrayList<Result> resultIg = new ArrayList<>();
        ArrayList<Result> resultG2a = new ArrayList<>();
        ArrayList<Result> resultYoutube = new ArrayList<>();
        ArrayList<Result> resultEveryEye = new ArrayList<>();
        Result resultAmazon;
        Result resultNull = null;
        
        String titolo = request.getParameter("titolo");
        Mediator md = new Mediator();
        System.out.println(titolo);
        md.selectElements(titolo);

        resultMatching = md.getResultsMatch();
        resultIg = md.getIgResults();
        resultG2a = md.getG2aResults();
        resultYoutube = md.getYouTubeResults();
        resultEveryEye = md.getEveryeyeResults();
        resultAmazon = md.getAmazonResult();
        System.out.println("matching size"+resultMatching.size());
        System.out.println("g2a prima"+resultG2a.size());
        System.out.println("ig prima"+resultIg.size());
        ServletContext context = getServletContext();

        if(resultG2a.isEmpty())
            {
               System.out.println("g2a è null");
               resultG2a = new ArrayList();
               resultG2a.add(resultNull);
            }
        if( resultIg.isEmpty())
            {   
                resultIg = new ArrayList();
                resultIg.add(resultNull);
            }
        
        if (resultMatching.isEmpty()) {
            Result minG2a = null;
            Result minIg = null;
         
            if (!resultG2a.isEmpty() && resultG2a.get(0)!=null) {
                minG2a = resultG2a.get(0);
                for (int i = 0; i < resultG2a.size(); i++) {
                    if (resultG2a.get(i).getPrice() < minG2a.getPrice()) {
                        minG2a = resultG2a.get(i);
                    }
                }
            }
             if (!resultIg.isEmpty() && resultIg.get(0)!=null) {
                minIg = resultIg.get(0);
                for (int i = 0; i < resultIg.size(); i++) {
                    if (resultIg.get(i).getPrice() < minIg.getPrice()) {
                        minIg = resultIg.get(i);
                    }
                }
            }
            if (minIg != null && minG2a != null) {
                if (minG2a.getPrice() < minIg.getPrice()) {
                    resultMatching.add(minG2a);
                } else {
                    resultMatching.add(minIg);
                }
            }
            else if(minIg != null && minG2a == null)
            {
                resultMatching.add(minIg);
            }
            else if(minIg == null && minG2a != null)
            {
                resultMatching.add(minG2a);
            }
            else
            {
                System.out.println("Entrambi null");
                resultMatching.add(resultNull);
            }
        }
        
        System.out.println("g2a dopo"+resultG2a.size());
        System.out.println("ig dopo"+resultIg.size());
        
        request.setAttribute("resultMatching", resultMatching);
        request.setAttribute("resultIg", resultIg);
        request.setAttribute("resultG2a", resultG2a);
        request.setAttribute("resultYoutube", resultYoutube);
        request.setAttribute("resultEveryEye", resultEveryEye);
        request.setAttribute("resultAmazon", resultAmazon);
        RequestDispatcher dispatcher = context.getRequestDispatcher("/ResultsQuery.jsp");

        dispatcher.forward(request, response);

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
