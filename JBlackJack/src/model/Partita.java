package model;

/**
 * classe Partita
 */
public class Partita 
{

	/**
	 * Dealer
	 */
	private Dealer dealer;
	
	/**
	 * giocatore
	 */
	private Giocatore giocatore;
	
	/**
	 * valore booelano che identifica il turno del giocatore
	 */
	private boolean turnoGiocatore;
	
	/**
	 * bot1
	 */
	private Bot bot;
	
	/**
	 * bot2
	 */
	private Bot bot2;
	
	/**
	 * bot3
	 */
	private Bot bot3;
	
	/**
	 * mazzo
	 */
	private Mazzo mazzo;
	
	/**
	 * numero totali di bot presenti nella partita
	 */
	private int numeroBot;
	
	/**
	 * costruttore di partita, dove viene inizializzato tutto il necessario per la creazione di una partita
	 * @param numeroBot numero totali di bot presenti nella partita
	 */
	public Partita(int numeroBot)
	{
		this.numeroBot = numeroBot;
		this.mazzo = Mazzo.getInstance();;
		this.giocatore = new Giocatore("Christian");;
		this.bot = new Bot("Jhonny",mazzo);
		this.bot2 = new Bot("Paolo",mazzo);
		this.bot3 = new Bot("Fabio",mazzo);
		this.dealer = new Dealer("Dealer",mazzo,bot,bot2,bot3,giocatore,numeroBot);
		this.turnoGiocatore = true;

	}
	
	/**
	 * metodo che gestice la logica della partita, confrontando la mano del giocatore con quella del dealer
	 * @return lo stato finale della partita (vinto,perso,pareggio)
	 */
	public String logicaVittoria()
	{
		if(dealer.getFinePartita())
		{
			dealer.getListaCarteMano().get(0).setCoperta(false);
			int valoreManoDealerConfronto = dealer.valoreManoGiocatore();
			if (giocatore.getBustato())
				return "Hai perso!";
			else if(valoreManoDealerConfronto > giocatore.valoreManoGiocatore() && valoreManoDealerConfronto <= 21)
				return "Hai perso!";
			else if (valoreManoDealerConfronto == giocatore.valoreManoGiocatore())
				return "Pareggio!";
			else
				return "Hai vinto!";
		}
		return "";
		
	}
	
	
	/**
	 * metodo che inizializza una partita facendo pescare al giocatore,ai bot e al dealer due carte, setta la prima carta del dealer pescata coperta
	 */
	public void inizioPartita()
	{		
		if(numeroBot == 1)
		{
			
			giocatore.aggiungiCarta(mazzo.pescaCarta());
			bot.aggiungiCarta(mazzo.pescaCarta());
			giocatore.aggiungiCarta(mazzo.pescaCarta());
			bot.aggiungiCarta(mazzo.pescaCarta());
			
			Carta c1 =mazzo.pescaCarta();
			c1.setCoperta(true);
			dealer.aggiungiCarta(c1);
			Carta c2 =mazzo.pescaCarta();
			dealer.aggiungiCarta(c2);
			System.out.println(dealer.getMano());
			System.out.println(dealer.getMano().valoreManoDealer());
			System.out.println(dealer.getMano().valoreMano());
		}
		else if(numeroBot == 2)
		{
			giocatore.aggiungiCarta(mazzo.pescaCarta());
			bot.aggiungiCarta(mazzo.pescaCarta());
			bot2.aggiungiCarta(mazzo.pescaCarta());
			giocatore.aggiungiCarta(mazzo.pescaCarta());
			bot.aggiungiCarta(mazzo.pescaCarta());
			bot2.aggiungiCarta(mazzo.pescaCarta());
			
			Carta c1 =mazzo.pescaCarta();
			c1.setCoperta(true);
			dealer.aggiungiCarta(c1);
			Carta c2 =mazzo.pescaCarta();
			dealer.aggiungiCarta(c2);
			System.out.println(dealer.getMano());
			System.out.println(dealer.getMano().valoreManoDealer());
			System.out.println(dealer.getMano().valoreMano());
		}
		else if(numeroBot == 3)
		{
			giocatore.aggiungiCarta(mazzo.pescaCarta());
			bot.aggiungiCarta(mazzo.pescaCarta());
			bot2.aggiungiCarta(mazzo.pescaCarta());
			bot3.aggiungiCarta(mazzo.pescaCarta());
			giocatore.aggiungiCarta(mazzo.pescaCarta());
			bot.aggiungiCarta(mazzo.pescaCarta());
			bot2.aggiungiCarta(mazzo.pescaCarta());
			bot3.aggiungiCarta(mazzo.pescaCarta());
			
			Carta c1 =mazzo.pescaCarta();
			c1.setCoperta(true);
			dealer.aggiungiCarta(c1);
			Carta c2 =mazzo.pescaCarta();
			dealer.aggiungiCarta(c2);
			System.out.println(dealer.getMano());
			System.out.println(dealer.getMano().valoreManoDealer());
			System.out.println(dealer.getMano().valoreMano());
		}
	}
	
	/**
	 * metodo che gestisce la logica della pescata, dopo ogni carta pescata dal giocatore controlla se esso ha sballato, se e' vero allora setta il turno del giocatore su false e quello del/dei
	 *  bot su true e fa partire ia di quest'ultimi con il metodo .ia()
	 */
	public void logicaPescataGiocatore()
	{
		if(numeroBot == 1)
		{
			if(turnoGiocatore)
			{
				giocatore.aggiungiCarta(mazzo.pescaCarta());
				if(giocatore.bustato())
				{
					turnoGiocatore = false;
					bot.setTurnoBot(true);
					System.out.println("hai sballato");
					bot.ia();
	
				}
				System.out.println("mano: "+giocatore.getListaCarteMano());
				System.out.println(mazzo.getGrandezzaMazzo());
			}// aggiungere logica altri bot es ( se turno mio false e quello del bot anceh allora parte il secondo bot, se il mio quello del primo bot e quello del secondo sono false allora tocca al terzo bot
		}
		else if(numeroBot == 2)
		{
			if(turnoGiocatore)
			{
				giocatore.aggiungiCarta(mazzo.pescaCarta());
				if(giocatore.bustato())
				{
					turnoGiocatore = false;
					bot.setTurnoBot(true);
					bot2.setTurnoBot(true);
					
					System.out.println("hai sballato");
					bot.ia();
					bot2.ia();
					
	
				}
				System.out.println("mano: "+giocatore.getListaCarteMano());
				System.out.println(mazzo.getGrandezzaMazzo());
			}
		}
		else if(numeroBot ==3)
		{
			if(turnoGiocatore)
			{
				giocatore.aggiungiCarta(mazzo.pescaCarta());
				if(giocatore.bustato())
				{

					turnoGiocatore = false;
					bot.setTurnoBot(true);
					bot2.setTurnoBot(true);
					bot3.setTurnoBot(true);
					System.out.println("hai sballato");
					bot.ia();
					bot2.ia();
					bot3.ia();
	
				}
				System.out.println("mano: "+giocatore.getListaCarteMano());
				System.out.println(mazzo.getGrandezzaMazzo());
			}
		}
	}
	
	/**
	 * logica che gestisce lo "stai" del giocatore, se il giocatore "sta" allora setta il suo turno su false e quello del/dei bot su true e fa partire ia di quest'ultimi con il metodo .ia()
	 */
	public void logicaStaiGiocatore()
	{
		if (numeroBot == 1)
		{
			if(turnoGiocatore)
			{
				giocatore.stare();
				turnoGiocatore = false;
				bot.setTurnoBot(true);
				bot.ia();
	
			}
		}
		else if(numeroBot == 2)
		{
			if(turnoGiocatore)
			{
				giocatore.stare();
				turnoGiocatore = false;
				bot.setTurnoBot(true);
				bot2.setTurnoBot(true);
				bot.ia();
				bot2.ia();
			}
		}
		else if(numeroBot == 3)
		{
			giocatore.stare();
			turnoGiocatore = false;
			bot.setTurnoBot(true);
			bot2.setTurnoBot(true);
			bot3.setTurnoBot(true);
			bot.ia();
			bot2.ia();
			bot3.ia();
		}
	}
	
	/**
	 * metodo che gestisce la logica per iniziare una nuova partita, pulisce le mani di tutti i giocatori, resetta lo stato dei giocatori  e chiama il metodo iniziaPartita()
	 */
	public void inizioNuovaPartita() 
	{
		if(numeroBot == 1)
		{
			giocatore.pulisciMano();
			dealer.pulisciMano();
			bot.pulisciMano();
			
			
			bot.setTurnoBot(false);
			bot.setBustato(false);
			bot.setStai(false);
			
			giocatore.setBustato(false);
			giocatore.setStai(false);
			turnoGiocatore = true;
			
			dealer.setFinePartita(false);
			dealer.setBustato(false);
			dealer.setStai(false);
			dealer.setTurnoDealer(true);
			inizioPartita();	
		}
		else if(numeroBot == 2)
		{
			giocatore.pulisciMano();
			dealer.pulisciMano();
			bot.pulisciMano();
			bot2.pulisciMano();

			
			
			bot.setTurnoBot(false);
			bot.setBustato(false);
			bot.setStai(false);
			
			bot2.setTurnoBot(false);
			bot2.setBustato(false);
			bot2.setStai(false);

			
			giocatore.setBustato(false);
			giocatore.setStai(false);
			turnoGiocatore = true;
			
			dealer.setFinePartita(false);
			dealer.setBustato(false);
			dealer.setStai(false);
			dealer.setTurnoDealer(true);
			inizioPartita();
		}
		else if(numeroBot == 3)
		{
			giocatore.pulisciMano();
			dealer.pulisciMano();
			bot.pulisciMano();
			bot2.pulisciMano();
			bot3.pulisciMano();
			
			
			bot.setTurnoBot(false);
			bot.setBustato(false);
			bot.setStai(false);
			
			bot2.setTurnoBot(false);
			bot2.setBustato(false);
			bot2.setStai(false);
			
			bot3.setTurnoBot(false);
			bot3.setBustato(false);
			bot3.setStai(false);
			
			giocatore.setBustato(false);
			giocatore.setStai(false);
			turnoGiocatore = true;
			
			dealer.setFinePartita(false);
			dealer.setBustato(false);
			dealer.setStai(false);
			dealer.setTurnoDealer(true);
			inizioPartita();
		}
	}
	
	/**
	 * getter per l'oggetto giocatore
	 * @return giocatore
	 */
	public Giocatore getGiocatore() { return giocatore;}
	
	/**
	 * getter per l'oggetto dealer
	 * @return dealer
	 */
	public Dealer getDealer() { return dealer;}
	
	/**
	 * getter per l'oggetto bot
	 * @return bot
	 */
	public Bot getBot() { return bot;}
	
	/**
	 * getter per l'oggetto bot2
	 * @return bot2
	 */
	public Bot getBot2() { return bot2;}
	
	/**
	 * getter per l'oggetto bot3
	 * @return bot3
	 */
	public Bot getBot3() { return bot3;}
	
	/**
	 * getter per l'oggetto mazzo
	 * @return mazzo
	 */
	public Mazzo getMazzo() { return mazzo;}
	
	
}
