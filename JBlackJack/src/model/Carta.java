package model;

/**
 * clase Carta
 */
public class Carta 
{

	/**
	 * simbolo della carta
	 */
	private String simbolo;
	
	/**
	 * valore della carta
	 */
	private ValoreCarte valore;
	
	/**
	 * valore booleano per capire se la carta e' coperta o meno
	 */
	private boolean coperta;
	/**
	 * costruttore dell'oggetto Carta
	 * @param simbolo il simbolo della carta
	 * @param valore il valore della carta
	 * @param coperta  valore booleano per capire se la carta e' coperta o meno
	 */
	public Carta(String simbolo, ValoreCarte valore,boolean coperta)
	{
		this.coperta = coperta;
		this.simbolo = simbolo;
		this.valore = valore;
	}
	
	/**
	 * getter per il simbolo della carta
	 * @return simbolo della carta
	 */
	public String getSimbolo() { return simbolo;}
	
	/**
	 * getter per il valore della carta
	 * @return valore della carta
	 */
	public int  getValore() { return valore.getValore();}
	
	/**
	 * getter per il valore booleano di coperta
	 * @return valore booleano di coperta
	 */
	public boolean getCoperta() { return coperta;}
	
	/**
	 * setter del valore coperta
	 * @param c valore booleano nuovo di coperta
	 */
	public void setCoperta(boolean c) { coperta = c;}  
	/**
	 * toString della carta
	 */
	@Override
	public String toString()
	{
		return getValore() + "-"+ getSimbolo() + " path: "+getAsset();
	}
	
	/**
	 * metodo che serve per associare a ogni carta la sua corrispettiva immagine
	 * @return il path dell'immagine
	 */
	public String getAsset()
	{
		if (getCoperta())
			return "assets/carte/BACK.png";
		return "assets/carte/"+valore.getNome() +"-"+getSimbolo()+".png";
	}
}
