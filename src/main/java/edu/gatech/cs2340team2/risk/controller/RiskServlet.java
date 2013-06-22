package edu.gatech.cs2340team2.risk.controller;

import edu.gatech.cs2340team2.risk.model.Player;
import edu.gatech.cs2340team2.risk.model.RiskGame;
import edu.gatech.cs2340team2.risk.model.GameState;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(urlPatterns={
        "/startup", // GET
        "/load_game", // POST 
        "/update_num_players", // PUT
        "/get_js_map" //GET
    })
public class RiskServlet extends HttpServlet {

    final int[] POSSIBLE_NUM_PLAYERS = {3,4,5,6};
    int numPlayers = 3;
    RiskGame game = new RiskGame();
    String[] players;
    
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
            
            switch(game.getGameState())
            {
                case INIT_PLAYERS:     
                    int j = 0;
                    players = new String[numPlayers];
                    for (int i = 0; i < numPlayers; i++) {
                        players[i] = (String)request.getParameter("player" + i + "Name");
                    }
                    game.initPlayers(players);
                    request.setAttribute("players", game.getQueue());
                    request.setAttribute("numPlayers", numPlayers);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/display.jsp");
                    dispatcher.forward(request,response);
                    break;
            }
        }
    }

    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getServletPath.equals("/get_player_array")) {
        	response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
        	response.getWriter().write(game.getQueue().toString());
        }
        switch(game.getGameState())
        {
            case INIT_PLAYERS:
                if(request.getServletPath().equals("/get_js_map"))
                {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(game.getMap().getJsonMap());
                }
                else
                {
                    request.setAttribute("numPlayers", numPlayers);
               //     request.setAttribute("players", players);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/start.jsp");
                    dispatcher.forward(request,response);
                }
                break;
        }
    }

    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
   
        switch(game.getGameState())
        {
            case INIT_PLAYERS:
                numPlayers = Integer.parseInt(request.getParameter("numplayers"));
                request.setAttribute("numPlayers", numPlayers);
                request.setAttribute("players", players);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/start.jsp");
                dispatcher.forward(request,response);
                break;
        }
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
