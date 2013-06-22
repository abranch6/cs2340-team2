package edu.gatech.cs2340team2.risk.model;

public class Player
{
	private String name;
	private int armies;
<<<<<<< HEAD
	

	public Player(String name, int startingArmies)
	{
		this.name = name;
		this.armies = startingArmies;
=======
	private int id;
	

	public Player(String name, int startingArmies, int id)
	{
		this.name = name;
		this.armies = startingArmies;
		this.id = id;
>>>>>>> upstream/master
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

<<<<<<< HEAD
=======
	public void addArmies(int armies)
	{
	    this.armies += armies;
	}
>>>>>>> upstream/master
	public int getArmies()
	{
		return armies;
	}
<<<<<<< HEAD
}
=======
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
}
>>>>>>> upstream/master
