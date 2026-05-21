package model;

import java.util.List;
import java.util.Observable;

/**
 * classe Giocatore
 */
public class Giocatore extends Observable
{

	/**
	 * status per vedere se il giocatore non vuole piu' pescare
	 */
	private boolean stai;
	/**
	 * nome del giocatore
	 */
	private String nome;
	
	/**
	 * mano del giocatre
	 */
	private Mano mano;
	
	/**
	 * status per vedere se il giocatore ha sballato
	 */
	private boolean sballato;
	
	/**
	 * costruttore per l'oggetto Giocatore
	 * @param nome nome giocatore
	 */
	public Giocatore(String nome)
	{
		this.nome = nome;
		this.mano = new Mano();
		this.sballato = false;
		this.stai = false;
	}
	
	/**
	 * getter per il nome del giocatore
	 * @return nome del giocatore
	 */
	public String getNome() { return nome;}
	
	/**
	 * getter per la lista carte nella mano del giocatore
	 * @return lista delle carte nella mano del giocatore
	 */
	public List<Carta> getListaCarteMano() { return mano.getMano();}
	
	/**
	 * getter di mano
	 * @return mano
	 */
	public Mano getMano() { return mano;}
	
	/**
	 * getter per la variabile stai
	 * @return stato del giocatore
	 */
	public boolean getStai() { return stai;}
	
	
	/**
	 * metodo per aggiungere carta alla mano del giocatore
	 * @param c carta da aggiungere
	 */
	public void aggiungiCarta(Carta c)
	{
		mano.aggiungiCartaMano(c);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * metodo per pulire la mano del giocatore
	 */
	public void pulisciMano()
	{
		mano.svuotaMano();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * metodo che calcola il valore della mano del giocatore
	 * @return valore della mano del giocatore
	 */
	public int valoreManoGiocatore()
	{
		return mano.valoreMano();
	}
	
	/**
	 * metodo che serve per stare, cioe' per non pescare piu' carte
	 * @return il valore di stai
	 */
	public boolean stare()
	{
		stai = true;
		setChanged();
		notifyObservers();
		return stai;
	}
	
	/**
	 * metodo che controlla se il giocatore ha superato 21
	 * @return il valore di sballato in base al valore della mano
	 */
	public boolean bustato()
	{
		if(valoreManoGiocatore() > 21)
			sballato = true;
		setChanged();
		notifyObservers();
		return sballato;
	}
	
	/**
	 * getter per il valore di sballato
	 * @return valore booleano di sballato
	 */
	public boolean getBustato() { return sballato;}
	
	/**
	 * setter per valore di sballato
	 * @param v valore booleano, true o false
	 */
	public void setBustato(boolean v) { sballato = v;} 
	
	/**
	 * setter per valore di stai
	 * @param v valore booleano, true o false
	 */
	public void setStai(boolean v) { stai = v;} 
	
	}
	

