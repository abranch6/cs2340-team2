package edu.gatech.cs2340team2.risk.controller;

import edu.gatech.cs2340team2.risk.model.Player;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={
        "/startup", // GET
        "/load_game", // POST 
        "/update_num_players", // PUT
        "/delete/*" // DELETE
    })
public class RiskServlet extends HttpServlet {

    List<Integer> POSSIBLE_NUM_PLAYERS = Arrays.asList(3, 4, 5, 6);
    LinkedList<Player> players = new LinkedList<Player>();
    Integer numPlayers = 3;
    RiskGame game = new RiskGame();

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
            int j = 0;
            for (int i = 1; i <= numPlayers; i++) {
                System.out.println(request.getParameter("player" + i + "Name"));
                
                players.add(i-1, new Player((String)request.getParameter("player" + i + "Name"), 0));
            }
            request.setAttribute("players", players);
            request.setAttribute("numPlayers", numPlayers);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/display.jsp");
            dispatcher.forward(request,response);
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
    }

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doDelete()");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/start.jsp");
        dispatcher.forward(request,response);
    }

    private int getId(HttpServletRequest request) {
        String uri = request.getPathInfo();
        // Strip off the leading slash, e.g. "/2" becomes "2"
        String idStr = uri.substring(1, uri.length()); 
        return Integer.parseInt(idStr);
    }

}
