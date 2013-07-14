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
        "/update_territory", //POST
        "/get_js_map",
        "/place_armies",
        "/advance_turn", 
        "/update_num_players", // PUT
        "/get_player_json",
        "/get_player_turn_json",
        "/get_game_state",
        "/get_turn_phase",
        "/attack" //POST
    })
public class RiskServlet extends HttpServlet {

    int numPlayers = 3;
    RiskGame game = new RiskGame();
    String[] players;
    Gson json = new Gson();
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        String operation = (String) request.getParameter("operation");
        
        if (operation != null && operation.equalsIgnoreCase("PUT")) {
            doPut(request, response);
        } else if (operation != null && operation.equalsIgnoreCase("DELETE")) {
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
            else if(request.getServletPath().equals("/place_armies"))
            {
                int row = Integer.parseInt(request.getParameter("row"));
                int col = Integer.parseInt(request.getParameter("col"));
                int playerId = Integer.parseInt(request.getParameter("player"));
                
                int armies = 0;
                boolean placed = false;

                if(!request.getParameter("armies").equals(""))
                {   
                   armies = Integer.parseInt(request.getParameter("armies"));
                }
    
                if(armies != 0)
                {
                     placed = game.placeArmies(row, col, playerId, armies);
                }

                
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json.toJson(Boolean.toString(placed)));
            }
            else if (request.getServletPath().equals("/attack"))
            {
            	int attackDieNum = Integer.parseInt(request.getParameter("attackDieNum"));
            	int defendDieNum = Integer.parseInt(request.getParameter("defendDieNum"));
            	
            	int attackRow = Integer.parseInt(request.getParameter("attackRow"));
            	int attackCol = Integer.parseInt(request.getParameter("attackCol"));
            	int defendRow = Integer.parseInt(request.getParameter("defendRow"));
            	int defendCol = Integer.parseInt(request.getParameter("defendCol"));
            	
            	boolean attackWasSuccess = false;
            	int[][] dice = game.attack(attackDieNum, defendDieNum, attackRow, attackCol, defendRow, defendCol);

            	
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json.toJson(dice));          	
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
        
        if (request.getServletPath().equals("/get_player_json")){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(game.getPlayerJSON());
        }
        else  if (request.getServletPath().equals("/get_player_turn_json")){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(game.getPlayerTurnJSON());
        }
        else if(request.getServletPath().equals("/get_game_state"))
        {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(game.getGameStateJSON());
        }
        else if(request.getServletPath().equals("/get_turn_phase"))
        {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(game.getTurnPhaseJSON());
        }

        switch(game.getGameState())
        {
            case PRE_GAME:
                if(request.getServletPath().equals("/get_js_map"))
                {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(game.getMap().getJsonMap());
                }
                break;
        }
        
        
        if(request.getServletPath().equals("/advance_turn"))
        {
                game.updateTurnPhase();    
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
