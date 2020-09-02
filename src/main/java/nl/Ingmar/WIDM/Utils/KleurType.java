package nl.Ingmar.WIDM.Utils;
public enum KleurType 
{
	LICHT_GROEN ("LICHT GROEN", "§a"),
	LICHT_BLAUW ("LICHT_BLAUW", "§b"),
	ROZE ("ROZE", "§d"),
	GEEL ("GEEL", "§e"),
	WIT ("WIT", "§f"),
	BLAUW ("BLAUW", "§1"),
	GROEN ("GROEN", "§2"),
	AQUA ("AQUA", "§3"),
	ROOD ("ROOD", "§4"),
	PAARS ("PAARS", "§5"),
	ORANJE ("ORANJE", "§6"),
	GRIJS ("GRIJS", "§8");
	
	String naam, kleur;
	
	KleurType(String naam, String kleur)
	{
		this.naam = naam;
		this.kleur = kleur;
	}
	
	public String getKleur()
	{
		return kleur;
	}

	public String getNaam()
	{
		 return naam;
	}
}
