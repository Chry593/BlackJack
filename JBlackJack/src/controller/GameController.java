package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Bot;
import model.Dealer;
import model.Giocatore;
import model.Mazzo;
import model.Partita;
import view.AudioManager;
import view.GameView;
import view.MenuView;

/**
 * classe GameController
 */
public class GameController 
{
	/**
	 * oggetto partita
	 */
	private Partita  partita;
	
	/**
	 * la view del game in corso
	 */
	private GameView gameView;
	
	/**
	 * la view del menu
	 */
	private MenuView menuView;
	
	/**
	 * numero di bot nella partita
	 */
	private int numeroBot;
	
	/**
	 * costruttore per la creazione del GameControler, dove dentro viene creato l'oggetto Partita passando anche il numero dei bot, e alla fine viene chiamato il metodo
	 * partita.iniziaPartita()
	 * @param gameView la view del game
	 * @param menuView la view del menu
	 * @param numeroBot il numero di bot totali nella partita
	 */
	public GameController(GameView gameView,MenuView menuView,int numeroBot)
	{
		this.numeroBot = numeroBot;
		this.gameView = gameView;
		this.menuView = menuView;
		this.partita = new Partita(numeroBot);
		partita.inizioPartita();
	}
	
	/**
	 * metodo che implementa il funzionamento del bottone pesca, ogni volta che viene cliccato viene chiamato il metodo logicaPescattaGiocatore() e parte l'effetto sonoro di una carta pescata
	 * @param pescaButton bottone pesca
	 */
	public void addPescaButtonListener(JButton pescaButton)
	{
		pescaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				partita.logicaPescataGiocatore();
				AudioManager.getInstance().play("assets/suoni/cartaSuono.wav",0.8,false);
			}
		});
		
	}
		
	
	/**
	 * metodo che implementa il funzionamento del bottone rigioca, quando viene cliccato viene chiamato il metodo inizioNuovaPartita() e parte l'effetto sonoro di un tasto schiacciato
	 * @param rigiocaButton bottone rigioca
	 */
	public void addRigiocaButtonListener(JButton rigiocaButton)
	{
		rigiocaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
				partita.inizioNuovaPartita();
			}
		});
		
	}
	
	/**
	 * metodo che implementa il funzionamento del bottone stai, quando viene cliccato viene chiamato il metodo logicaStaiGiocatore() e parte l'effetto sonoro di un tasto schiacciato
	 * @param staiButton bottone stai
	 */
	public void addStaiButtonListener(JButton staiButton)
	{
		staiButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
				partita.logicaStaiGiocatore();
			}
		});
	}

	/**
	 * metodo che implementa il funzionamento del bottone stai, se viene cliccato ti fa uscire dalla partita e tornare al menu principale , parte anche l'effetto sonoro di un tasto schiacciato
	 * @param esciButton bottone esci
	 */
	public void addEsciButtonListener(JButton esciButton)
	{
		esciButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				gameView.dispose();
				menuView.setVisible(true);
				AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
				menuView.getCanzoneMenu().play("assets/suoni/canzoneMenu.wav", 0.7, true);
				gameView.getAudioManagerMenu().stop();
			}
		});
	}
	
	/**
	 * getter oggetto Giocatore
	 * @return giocatore
	 */
	public Giocatore getGiocatore() { return partita.getGiocatore();}
	
	/**
	 * getter oggetto bot
	 * @return bot
	 */
	public Bot getBot() { return partita.getBot();}
	
	/**
	 * getter oggetto bot2
	 * @return bot2
	 */
	public Bot getBot2() { return partita.getBot2();}
	
	/**
	 * getter oggetto bot3
	 * @return bot3
	 */
	public Bot getBot3() { return partita.getBot3();}
	
	/**
	 * getter oggetto mazzo
	 * @return mazzo
	 */
	public Mazzo getMazzo() { return partita.getMazzo();}
	
	/**
	 * getter oggetto dealer
	 * @return dealer
	 */
	public Dealer getDealer() { return partita.getDealer();}
	
	/**
	 * getter oggetto partita
	 * @return partita
	 */
	public Partita getPartita() { return partita;}
}
