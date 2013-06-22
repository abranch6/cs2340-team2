package edu.gatech.cs2340team2.risk.model;

public class Player
{
	private String name;
	private int armies;
	private int id;
	

	public Player(String name, int startingArmies, int id)
	{
		this.name = name;
		this.armies = startingArmies;
		this.id = id;
	}

	public String getName()
	{
		return name;
	}
        
        public void setName(String name) {
            this.name = name;
        }

	public void setArmies(int armies)
	{
		this.armies = armies;
	}

	public void addArmies(int armies)
	{
	    this.armies += armies;
	}
	public int getArmies()
	{
		return armies;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
}
