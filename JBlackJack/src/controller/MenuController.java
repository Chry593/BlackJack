package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import view.AudioManager;
import view.CreazioneProfiloView;
import view.MenuView;
import view.SceltaBotView;
import view.StatisticheView;

/**
 * classe MenuController
 */
public class MenuController 
{

	
	/**
	 * view del menu
	 */
	private MenuView menuView;

	/**
	 * costruttore per la creazione del MenuController
	 * @param menuView view del menu
	 */
	public MenuController(MenuView menuView)
	{
		this.menuView = menuView;
	}
	
	/**
	 * metodo che implementa il funzionamento del bottone Gioca.
	 * Se viene cliccato senza mai aver creato un profilo, uscira' a schermo un popup che ti avvisera' di dover creare un profilo e di conseguenza si aprira' la view 
	 * per la creazione di quest'ultimo, se invece il profilo e' stato creato si aprira' la view per la scelta dei bot nella partita
	 * @param newGameButton bottone gioca
	 */
	public void addNewGameButtonListener(JButton newGameButton)
	{
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(menuView.getProfilo().getNickname() != "")
				{
					menuView.dispose();
					new SceltaBotView(menuView);
					AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
					//AudioManager audioManagerMenu = AudioManager.getInstance();
					//audioManagerMenu.play("C:\\Users\\UTENTE\\eclipse-workspace\\JBlackJack\\src\\suoni\\musicaPartita.wav",0.7,true);
					//new GameView(menuView,audioManagerMenu);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Crea un profilo prima di giocare!");
					menuView.dispose();
					new CreazioneProfiloView(menuView);
				}
				
			}
		});
		
	}
		
	
	/**
	 * metodo che implementa il funzionamento del bottone crea profilo. Verifica se il profilo e' stato creato, se e' cosi' allora apre la view delle statistiche se no 
	 * apre la view della creazione del profilo
	 * @param newProfiloButton bottone profilo 
	 */
	public void addnewProfiloButtonListener(JButton newProfiloButton)
	{
		newProfiloButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//JOptionPane.showMessageDialog(null,"Sezione Profilo");
				// se il profilo e' null allora mi apre la pagina per la creazione di quest'ultimo
				if(menuView.getProfilo().getNickname() == "")
				{
					menuView.dispose();
					AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
					new CreazioneProfiloView(menuView);
				}
				else // altrimenti mi apre la pagina delle statistiche
				{
					menuView.dispose();
					AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
					new StatisticheView(menuView.getProfilo(), menuView);
				}
				
			}
		});
		
	}
	
	/**
	 *  metodo che implementa il funzionamento del bottone esci, quando viene cliccato viene chiamato il metodo System.exit(0) e il gioco si chiude
	 * @param newExitButton bottone esci
	 */
	public void addnewExitButtonListener(JButton newExitButton)
	{
		newExitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				System.exit(0);
			}
		});
	}
	
	
}
