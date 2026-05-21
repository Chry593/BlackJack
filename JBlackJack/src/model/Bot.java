package model;

import java.util.Random;

/**
 * classe Bot
 */
public class Bot extends Giocatore
{

	/**
	 * mazzo
	 */
	private Mazzo m;
	
	/**
	 * turnoBot, false il suo turno e' finito, true il suo turno e' in corso
	 */
	boolean turnoBot;
	
	/**
	 * Costruttore del Bot
	 * @param name nome del bot
	 * @param m mazzo
	 */
	public Bot(String nome, Mazzo m)
	{
		super(nome);
		this.m = m;
		this.turnoBot = false;
	}
	
	/**
	 * metodo che gestisce ia del bot, se valore della mano e' minore di 10 allora pesca direttamente, mentre se va da 11 a 15 ha il 50% di possibilita' di pesca se no sta,mentre se va da 16 a 21 si ferma direttamente
	 */
	public void ia()  
	{
		Random r = new Random();
		
		while (turnoBot) 
		{
			
			if(valoreManoGiocatore() <= 10)
			{
				
				Carta c = m.pescaCarta();
				aggiungiCarta(c);
			}
			else if (valoreManoGiocatore() >= 11 && valoreManoGiocatore() <= 15)
			{
				int scelta = r.nextInt(2);
				if(scelta == 0) 
				{
					Carta c = m.pescaCarta();
					aggiungiCarta(c);
					if(bustato())
					{
						turnoBot = false;
						System.out.println("Bot ha sballato");;
					}
				}else
				{
					stare();
					turnoBot = false;
				}
				
			}else if (valoreManoGiocatore() >= 16 && valoreManoGiocatore() <= 21)
			{
				stare();
				turnoBot = false;
			}
		}

	}
	
	/**
	 * metodo getter per il turnoBot
	 * @return restituisce il valore booleano del turnoBot
	 */
	public boolean getTurnoBot() { return turnoBot;}
	
	/**
	 * setter del turnoBot
	 * @param scelta valore booleano, true o false
	 */
	public void setTurnoBot(boolean scelta) 
	{ 
		turnoBot = scelta;
		setChanged();
		notifyObservers();
	}
}
