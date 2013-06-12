package edu.gatech.cs2340team2.risk.controller;

import edu.gatech.cs2340team2.risk.model.Player;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={
        "/startup", // GET
        "/create", // POST 
        "/update", // PUT
        "/delete/*" // DELETE
    })
public class RiskServlet extends HttpServlet {

    TreeMap<Integer, Player> players = new TreeMap<>();
    Integer numPlayers = 3;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doPost()");
        // Handle the hidden HTML form field that simulates
        // HTTP PUT and DELETE methods.
        String operation = (String) request.getParameter("operation");
        // If form didn't contain an operation field and
        // we're in doPost(), the operation is POST
        if (null == operation) operation = "POST";
        System.out.println("operation is " + operation);
        if (operation.equalsIgnoreCase("PUT")) {
            System.out.println("Delegating to doPut().");
            doPut(request, response);
        } else if (operation.equalsIgnoreCase("DELETE")) {
            System.out.println("Delegating to doDelete().");
            doDelete(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/start.jsp");
            dispatcher.forward(request,response);
            /*
            int numplayers = Integer.parseInt(request.getParameter("numplayers"));
            request.setAttribute("numPlayers", numPlayers);
            request.setAttribute("players", players);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/start.jsp");
            dispatcher.forward(request,response);
            */
            
            /*
            String title = request.getParameter("title");
            String task = request.getParameter("task");
            todos.put(todos.size(), new Todo(title, task));
            request.setAttribute("todos", todos);
            RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher("/list.jsp");
            dispatcher.forward(request,response);
            */
        }
    }

    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doGet()");
        request.setAttribute("numPlayers", numPlayers);
        request.setAttribute("players", players);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/start.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doPut()");
        System.out.println("numplayers passed on = :" + request.getParameter("numplayers"));
        numPlayers = Integer.parseInt(request.getParameter("numplayers"));
        request.setAttribute("numPlayers", numPlayers);
        request.setAttribute("players", players);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/start.jsp");
        dispatcher.forward(request,response);
        
        /*
        String title = (String) request.getParameter("title");
        String task = (String)  request.getParameter("task");
        int id = getId(request);
        todos.put(id, new Todo(title, task));
        request.setAttribute("todos", todos);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
        */
    }

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doDelete()");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/start.jsp");
        dispatcher.forward(request,response);
        
        /*
        int id = getId(request);
        todos.remove(id);
        request.setAttribute("todos", todos);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
        */
    }

    private int getId(HttpServletRequest request) {
        String uri = request.getPathInfo();
        // Strip off the leading slash, e.g. "/2" becomes "2"
        String idStr = uri.substring(1, uri.length()); 
        return Integer.parseInt(idStr);
    }

}
