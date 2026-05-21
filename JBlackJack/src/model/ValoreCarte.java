package model;

/**
 * enum ValoreCarte
 */
public enum ValoreCarte
{
	/**
	 * nome e valore delle carte
	 */
    DUE("2", 2), TRE("3", 3), QUATTRO("4", 4), CINQUE("5", 5), SEI("6", 6),
    SETTE("7", 7), OTTO("8", 8), NOVE("9", 9), DIECI("10", 10),
    JACK("J", 10), QUEEN("Q", 10), KING("K", 10), ASSO("A", 11);
	
	/**
	 * nome della carta
	 */
	private String nome;
	
	/**
	 * valore della carta
	 */
	private int valore;
	
	/**
	 * costruttore del valore della carta
	 * @param nome nome della carta
	 * @param valore valore della carta
	 */
	ValoreCarte(String nome,int valore)
	{
		this.nome = nome;
		this.valore = valore;
	}
	
	/**
	 * getter per nome della carta
	 * @return nome della carta
	 */
	public String getNome() { return nome;}
	
	/**
	 * getter per il valore della carta
	 * @return valore della carta
	 */
	public int getValore() { return valore;}
	
}
