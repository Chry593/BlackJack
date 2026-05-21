package model;

import java.util.ArrayList;
import java.util.List;

/**
 * classe Mano
 */
public class Mano 
{

	/**
	 * Lista delle carte presenti nella mano
	 */
	private List<Carta> mano;
	
	/**
	 * Costruttore dell'oggetto mano, crea una mano vuota
	 */
	public Mano()
	{
		mano = new ArrayList<>();
	}
	
	/**
	 * metodo per svuotare la mano del giocatore
	 */
	public void svuotaMano() { mano.clear();}
	
	
	/**
	 * Metodo che aggiunge una carta alla mano
	 * @param c Carta da aggiungere
	 */
	public void aggiungiCartaMano(Carta c)
	{
		mano.add(c);

	}
	
	
	/**
	 * metodo che calcola il valore totatale della mano tramite stream
	 * @return valore mano
	 */
	public int valoreMano()
	{
		return mano.stream()
		.mapToInt(x -> x.getValore())
		.sum();
	}
	
	/**
	 * metodo che calcola il valore totale delle carte scoperte del dealer tramite stream
	 * @return valore mano delle carte scoperte del dealer
	 */
	public int valoreManoDealer()
	{
		return mano.stream()
				.filter(x -> x.getCoperta() == false)
				.mapToInt(x -> x.getValore())
				.sum();
	}
	
	
	/**
	 * Metodo getter per la mano
	 * @return mano
	 */
	public List<Carta> getMano() { return mano;}
	
	/**
	 * toString della mano realizzato tramite stream
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		mano.stream()
		.forEach(x -> sb.append(x.toString() + "\n"));
		return sb.toString();
	}
	
}
