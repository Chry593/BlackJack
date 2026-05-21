package model;

/**
 * classe Dealer
 */
public class Dealer extends Giocatore
{
	/**
	 * mazzo
	 */
	private Mazzo m;
	
	/**
	 * valore booleano che identifica lo stato di una partita
	 */
	private boolean finePartita;
	
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
	 * giocatore
	 */
	private Giocatore giocatore;
	
	/**
	 * valore booleano che identifica lo stato del turno del dealer
	 */
	private boolean turnoDealer;
	
	/**
	 * il numero di bot totali nella partita
	 */
	private int numeroBot;
	
	/**
	 * costruttore del dealer
	 * @param nome nome del dealer
	 * @param m il mazzo 
	 * @param bot oggetto bot
	 * @param bot2 oggetto bot2
	 * @param bot3 oggetto bot3
	 * @param giocatore giocatore 
	 * @param numeroBot numero totali dei bot nella partita,escluso il giocatore
	 */
	public Dealer(String nome,Mazzo m,Bot bot,Bot bot2,Bot bot3, Giocatore giocatore, int numeroBot)
	{
		super(nome);
		this.numeroBot = numeroBot;
		this.m = m;
		this.bot = bot;
		this.bot2 = bot2;
		this.bot3 = bot3;
		this.turnoDealer = true;
		this.giocatore = giocatore;
	}
	
	/**
	 * metodo che gestisce ia del bot, con tutte le possibilita' che si possono trovare durante il gioco
	 */
	public void iaDealer() {
		//boolean turnoDealer = true;
	    if (numeroBot == 1) {
	        while (turnoDealer) {
	            if (!giocatore.bustato() && !bot.bustato()) {
	                if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (!giocatore.bustato() && bot.bustato()) {
	                if (valoreManoGiocatore() < giocatore.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (giocatore.bustato() && !bot.bustato()) {
	                if (valoreManoGiocatore() < bot.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (giocatore.bustato() && bot.bustato()) {
	                stare();
	                turnoDealer = false;
	            }
	        }
	        finePartita = true;
	    } else if (numeroBot == 2) {
	        while (turnoDealer) {
	            if (!giocatore.bustato() && !bot.bustato() && !bot2.bustato()) {
	                if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                    if(bustato())
	                    	turnoDealer = false;
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (!giocatore.bustato() && bot.bustato() && bot2.bustato()) {
	                if (valoreManoGiocatore() < giocatore.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                    if(bustato())
	                    	turnoDealer = false;
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (giocatore.bustato() && !bot.bustato() && bot2.bustato()) {
	                if (valoreManoGiocatore() < bot.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                    if(bustato())
	                    	turnoDealer = false;
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (giocatore.bustato() && bot.bustato() && !bot2.bustato()) {
	                if (valoreManoGiocatore() < bot2.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                    if(bustato())
	                    	turnoDealer = false;
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (!giocatore.bustato() && !bot.bustato() && bot2.bustato()) {
	                if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                    if(bustato())
	                    	turnoDealer = false;
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (giocatore.bustato() && !bot.bustato() && !bot2.bustato()) {
	                if (valoreManoGiocatore() < bot.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                    if(bustato())
	                    	turnoDealer = false;
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (!giocatore.bustato() && bot.bustato() && !bot2.bustato()) {
	                if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore()) {
	                    aggiungiCarta(m.pescaCarta());
	                    if(bustato())
	                    	turnoDealer = false;
	                } else {
	                    stare();
	                    turnoDealer = false;
	                }
	            } else if (giocatore.bustato() && bot.bustato() && bot2.bustato()) {
	                stare();
	                turnoDealer = false;
	            }
	        }
	        finePartita = true;
	        System.out.println("finito game");
	    }else if (numeroBot == 3) {
	        while (turnoDealer) {
	            if (!giocatore.bustato()) {
	                if (!bot.bustato() && !bot2.bustato() && !bot3.bustato()) {
	                    if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore() || valoreManoGiocatore() < bot3.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (bot.bustato() && bot2.bustato() && bot3.bustato()) {
	                    if (valoreManoGiocatore() < giocatore.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (!bot.bustato() && bot2.bustato() && bot3.bustato()) {
	                    if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (bot.bustato() && !bot2.bustato() && bot3.bustato()) {
	                    if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (bot.bustato() && bot2.bustato() && !bot3.bustato()) {
	                    if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot3.valoreManoGiocatore()) 
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (!bot.bustato() && !bot2.bustato() && bot3.bustato()) {
	                    if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (!bot.bustato() && bot2.bustato() && !bot3.bustato()) {
	                    if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot.valoreManoGiocatore() || valoreManoGiocatore() < bot3.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());     
	                    else {
	                        stare();
	                        turnoDealer = false;
	                        System.out.println("settimo else");
	                    }
	                } else if (bot.bustato() && !bot2.bustato() && !bot3.bustato()) {
	                    if (valoreManoGiocatore() < giocatore.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore() || valoreManoGiocatore() < bot3.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                }
	            } else if (giocatore.bustato()) {
	                if (!bot.bustato() && !bot2.bustato() && !bot3.bustato()) {
	                    if (valoreManoGiocatore() < bot.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore() || valoreManoGiocatore() < bot3.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (!bot.bustato() && bot2.bustato() && bot3.bustato()) {
	                    if (valoreManoGiocatore() < bot.valoreManoGiocatore()) 
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (bot.bustato() && !bot2.bustato() && bot3.bustato()) {
	                    if (valoreManoGiocatore() < bot2.valoreManoGiocatore())
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (bot.bustato() && bot2.bustato() && !bot3.bustato()) {
	                    if (valoreManoGiocatore() < bot3.valoreManoGiocatore()) 
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (!bot.bustato() && !bot2.bustato() && bot3.bustato()) {
	                    if (valoreManoGiocatore() < bot.valoreManoGiocatore() || valoreManoGiocatore() < bot2.valoreManoGiocatore()) 
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                } else if (!bot.bustato() && bot2.bustato() && !bot3.bustato()) {
	                    if (valoreManoGiocatore() < bot.valoreManoGiocatore() || valoreManoGiocatore() < bot3.valoreManoGiocatore()) 
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                        
	                    }
	                } else if (bot.bustato() && !bot2.bustato() && !bot3.bustato()) {
	                    if (valoreManoGiocatore() < bot2.valoreManoGiocatore() || valoreManoGiocatore() < bot3.valoreManoGiocatore()) 
	                        aggiungiCarta(m.pescaCarta());
	                    else {
	                        stare();
	                        turnoDealer = false;
	                    }
	                }
	            }
	            if (giocatore.bustato() && bot.bustato() && bot2.bustato() && bot3.bustato()) {
	                stare();
	                turnoDealer = false;
	            }
	        }
	        finePartita = true;
	    }
	   }


	/**
	 * getter per il valore di finePartita
	 * @return valore booleano di finePartita
	 */
	public boolean getFinePartita() { return finePartita;}
	
	/**
	 * setter per il valore di finePartita
	 * @param v valore booleano, true o false
	 */
	public void setFinePartita(boolean v) { finePartita = v;}
	
	/**
	 * setter per il valore del turnoDealer
	 * @param v valore booleano, true o false
	 */
	public void setTurnoDealer(boolean v) { turnoDealer = v;}
	
}
