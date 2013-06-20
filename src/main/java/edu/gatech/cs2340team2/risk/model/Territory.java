package edu.gatech.cs2340team2.risk.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Territory
{

    private MapLocation location;
    private int playerId;
    private int armies;
    Gson json;
    
    public Territory(int r, int c)
    {
        location = new MapLocation(r,c);
        json = new Gson();
    }

    public int getPlayerId()
    {
	return playerId;
    }

    public void setPlayerId(int playerId)
    {
	this.playerId = playerId;
    }

    public void addArmies(int a)
    {
	armies += a;
    }

    public int getArmies()
    {
	return armies;
    }

    public void setArmies(int armies)
    {
	this.armies = armies;
    }

    public String getJSonTerritory()
    {
	return json.toJson(this);
    }
}
