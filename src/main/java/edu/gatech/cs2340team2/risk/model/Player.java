package edu.gatech.cs2340team2.risk.model;

public class Player
{
	private String name;
	private int armies;

	Player()
	{
	}

	Player(String name, int startingArmies)
	{
		this.name = name;
		armies = startingArmies;
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

	public int getArmies()
	{
		return armies;
	}
}