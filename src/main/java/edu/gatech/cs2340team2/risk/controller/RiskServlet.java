package edu.gatech.cs2340team2.risk.controller;

import edu.gatech.cs2340team2.risk.model.Player;
import edu.gatech.cs2340team2.risk.model.RiskGame;
import edu.gatech.cs2340team2.risk.model.GameState;
import edu.gatech.cs2340team2.risk.model.MapLocation;

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
        "/start_game", // POST
        "/num_players", // POST
        "/get_js_map", //GET
        "/update_territory" //POST
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
                    if(request.getServletPath().equals("/num_players"))
                    {
                        numPlayers = Integer.parseInt(request.getParameter("numplayers"));
                        request.setAttribute("numPlayers", numPlayers);
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/enter_players.jsp");
                        dispatcher.forward(request,response);
                    }
                    else if(request.getServletPath().equals("/start_game"))
                    {
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
                    }
                    break;
            }
            if(request.getServletPath().equals("/update_territory"))
            {
                int r = Integer.parseInt(request.getParameter("row"));
                int c = Integer.parseInt(request.getParameter("col"));
                
                String json = game.getJSonTerritory(new MapLocation(r,c));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                
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
        
        switch(game.getGameState())
        {
            case INIT_PLAYERS:
                if(request.getServletPath().equals("/get_js_map"))
                {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(game.getMap().getJsonMap());
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
                
                break;
        }
    }

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {
        
    }
}
