package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.AudioManager;
import view.MenuView;
import view.StatisticheView;

/**
 * classe StatisticheController
 */
public class StatisticheController {
	
	/**
	 * view del menu
	 */
	private MenuView menuView;
	
	/**
	 * view delle statistiche
	 */
	private StatisticheView statisticheView;
	
	/**
	 * costruttore per la creazione di StatisitcheController
	 * @param statisticheView view statistiche
	 * @param menuView view menu
	 */
	public StatisticheController(StatisticheView statisticheView,MenuView menuView)
	{
		this.menuView = menuView;
		this.statisticheView = statisticheView;
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
				statisticheView.dispose();
				menuView.setVisible(true);
			}
		});
}
	
	
}
