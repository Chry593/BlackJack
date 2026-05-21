package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Profilo;
import view.AudioManager;
import view.GameView;
import view.MenuView;
import view.SceltaBotView;

/**
 * classe SceltaBotController
 */
public class SceltaBotController
{
	/**
	 * view della scelta dei bot
	 */
	private SceltaBotView sceltaBotView;
	
	/**
	 * view del menu
	 */
	private MenuView menuView;
	
	/**
	 * profilo
	 */
	private Profilo p;
	
	/**
	 * costruttore per la creazione del SceltaBotController
	 * @param p profilo
	 * @param sceltaBotView view della scelta dei bot
	 * @param menuView view del menu
	 */
	public SceltaBotController(Profilo p,SceltaBotView sceltaBotView,MenuView menuView)
	{
		this.sceltaBotView = sceltaBotView;
		this.menuView = menuView;
		this.p = p;
	}
	
	/**
	 * metodo che implementa il funzionamento del bottone unBot, quando viene cliccato creera' una GameView con un bot
	 * @param unBotButton bottone unBot
	 */
	public void addUnBotButtonListener(JButton unBotButton)
	{
		unBotButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
				sceltaBotView.dispose();
				AudioManager audioManagerMenu = AudioManager.getInstance();
				audioManagerMenu.play("assets/suoni/musicaPartita.wav",0.7,true);
				menuView.getCanzoneMenu().stop();
				new GameView(menuView,audioManagerMenu,1);
			}
		});
		
	}
	
	/**
	 * metodo che implementa il funzionamento del bottone dueBot, quando viene cliccato creera' una GameView con due bot
	 * @param dueBotButton bottone dueBot
	 */
	public void addDueBotButtonListener(JButton dueBotButton)
	{
		dueBotButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
				sceltaBotView.dispose();
				AudioManager audioManagerMenu = AudioManager.getInstance();
				audioManagerMenu.play("assets/suoni/musicaPartita.wav",0.7,true);
				menuView.getCanzoneMenu().stop();
				new GameView(menuView,audioManagerMenu,2);
			}
		});
		
	}
	
	/**
	 * metodo che implementa il funzionamento del bottone treBot, quando viene cliccato creera' una GameView con tre bot
	 * @param treBotButton bottone treBot
	 */
	public void addTreBotButtonListener(JButton treBotButton)
	{
		treBotButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
				sceltaBotView.dispose();
				AudioManager audioManagerMenu = AudioManager.getInstance();
				audioManagerMenu.play("assets/suoni/musicaPartita.wav",0.7,true);
				menuView.getCanzoneMenu().stop();
				new GameView(menuView,audioManagerMenu,3);
			}
		});
		
	}
	
	/**
	 * metodo che implementa il funzionamento del bottone tornaIndietro, quando viene cliccato fara' tornare il giocatore al menu' principale
	 * @param tornaIndietroButton bottone torna indietro
	 */
	public void addTornaIndietroButtonListener(JButton tornaIndietroButton)
	{
		tornaIndietroButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
				sceltaBotView.dispose();
				menuView.setVisible(true);
			}
		});
		
	}
	
	
}
