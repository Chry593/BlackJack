package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * classe Mazzo
 */
public class Mazzo
{

	/**
	 * lista delle carte nel mazzo
	 */
	private List<Carta> carte;
	
	/**
	 * variabile instance riferita a se stessa per attuare il singleton
	 */
	private static Mazzo instance;
	
	
	/**
	 * costruttore dell'oggetto Mazzo, con creazione del mazzo tramite il metodo creaMazzo()
	 */
	private Mazzo()
	{
	 carte = new ArrayList<>();
	 carte = creaMazzo();
	}
	
	/**
	 * metodo per ottenere l'istanza del mazzo
	 * @return instaza del mazzo
	 */
	public static Mazzo getInstance()
	{
		if (instance == null)
			return new Mazzo();
		else
			return instance;
	}
	
	/**
	 * metodo per avere il numero delle carte  nel mazzo
	 * @return il numero delle carte nel mazzo
	 */
	public int getGrandezzaMazzo() { return carte.size();}
	
	/**
	 * Meodo che crea un mazzo e dopo lo mischia col metodo shuffle
	 * @return le carte mischiate
	 */
	private List<Carta> creaMazzo() 
	{
		List<String> simboli = List.of("C","P","Q","F");
		
		for (int i = 0;i < 6;i ++) {
			for(String simbolo : simboli)
			{
				for(ValoreCarte valore: ValoreCarte.values()) 
				{
					carte.add(new Carta(simbolo,valore,false));
				}
			}
		}
		
		Collections.shuffle(carte);
		
		return carte;
	}
	
	/**
	 * metodo per pescare una carta in cima al mazzo
	 * @return la carta pescata
	 */
	public Carta pescaCarta() { return carte.remove(carte.size() -1);}
	
	/**
	 * toString del mazzo realizzato con gli stream
	 */
	@Override
	public String toString () 
	{
		StringBuilder sb = new StringBuilder();
		carte.stream()
		.forEach(x -> sb.append(x.toString() + "\n"));
		return sb.toString();
	}
	
	
}






