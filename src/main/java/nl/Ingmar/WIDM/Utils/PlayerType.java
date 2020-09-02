package nl.Ingmar.WIDM.Utils;

public enum PlayerType 
{
	PLAYER ("Speler"),
	MOL ("Mol"),
	EGOIST ("Egoïst"),
	ENGEL ("Engel"),
	EXECUTIONER ("Executioner");
	
	String naam;
	
	PlayerType(String naam)
	{
		this.naam = naam;
	}

	public String getNaam()
	{
		 return naam;
	}
}
