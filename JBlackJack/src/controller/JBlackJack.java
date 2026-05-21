package controller;

import java.io.File;

import model.Profilo;
import view.MenuView;

/**
 * classe BlackJack
 */
public class JBlackJack 
{
	/**
	 * main dove inizializzo il path del profilo, controlla se esiste, se e' vero allora carica il profilo e lo passa al MenuView, se falso crea comunque un profilo
	 * senza nome e lo passa alla view, cosi' nel primo caso abbiamo che il menu si apre con il bottone "Statistiche" invece che "Profilo" come nel secondo caso
	 * @param args args
	 */
	public static void main(String [] args)
	{

		String percorsoFile = "assets/profilo/profilo.txt";
		File file = new File(percorsoFile);
		
		System.out.println(file.exists());
		
		MenuView menu;
		if (file.exists())
		{
			Profilo p = Profilo.getInstance().caricaProfilo();
			System.out.println(p);
			menu = new MenuView(p);
			menu.setVisible(true);
			System.out.println("esiste");
		}
		else
		{
			menu = new MenuView(Profilo.getInstance());
			menu.setVisible(true);
			System.out.println("non esiste");
		}
		
		// sistemare schermata del profilo e menu
		// debugging e correzioni
		// modificare GameView  e Partita in base ai giocatori, farlo con 3 bot
	}
	
	
}
