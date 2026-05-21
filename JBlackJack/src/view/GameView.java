package view;
import javax.imageio.ImageIO;
import javax.swing.*;

import controller.GameController;
import model.Carta;
import model.Giocatore;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.util.Observable;
import java.util.Observer;

/**
 * classe GameView
 */
public class GameView extends JFrame implements Observer
{

	/**
	 * bottoni pesca,stai,rigioca e esci
	 */
	private JButton pescaButton,staiButton,rigiocaButton,esciButton;

	/**
	 * view del menu
	 */
	private MenuView menu;
	
	/**
	 * audio del menu
	 */
	private AudioManager audioManagerMenu;
	
	/**
	 * game controller
	 */
	private GameController controller;
	
	/**
	 * game panel
	 */
	private JPanel gamePanel;
	
	/**
	 * esito della partita
	 */
	private String esito;
	
	/**
	 * numero di bot presenti nella partita
	 */
	private int numeroBot;

	/**
	 * costruttore per la creazione della GameView. Possiamo trovare:
	 * 1) tutti gli addObserver() cosi  la view verra' informata per ogni cambiamento
	 * 2) il controllo per abilitare i pulsanti stai,pesca e rigioca in base allo stato del giocatore e della partita
	 * 3) il draw delle carte, delle mani e del mazzo 
	 * 4) in base al numero dei bot abbiamo una diversa disposizione delle mani del giocatore e dei bot
	 * 5) l'aggiunta dei bottoni ai listener del controller
	 * @param menu view del menu
	 * @param audioManagerMenu audio del menu
	 * @param numeroBot numero totali di bot presenti in partita
	 */
    public GameView(MenuView menu,AudioManager audioManagerMenu,int numeroBot) {
    	
    	this.numeroBot = numeroBot;
    	this.audioManagerMenu = audioManagerMenu;
    	controller = new GameController(GameView.this,menu,numeroBot);
    	Giocatore giocatore = controller.getGiocatore();
    	Giocatore bot1 = controller.getBot();
    	Giocatore bot2 = controller.getBot2();
    	Giocatore bot3 = controller.getBot3();
    	Giocatore dealer = controller.getDealer();
    	giocatore.addObserver(this);
    	bot1.addObserver(this);
    	bot2.addObserver(this);
    	bot3.addObserver(this);
    	dealer.addObserver(this);
    	
    	
    	int cardWidth = 110; 
    	int cardHeight= 154;
    	JFrame frame = new JFrame("BlackJack");
    	gamePanel = new JPanel() {
    		@Override
    		public void paintComponent(Graphics g)
    		{
    			super.paintComponent(g);
    			// se si sballa o si sta i tasti vengono disattivati
				if(giocatore.getBustato() || giocatore.getStai())
				{
					pescaButton.setEnabled(false);
					staiButton.setEnabled(false);
				}else
				{
					pescaButton.setEnabled(true);
					staiButton.setEnabled(true);
				}
				
				// controllo per vedere se la partita e' finita, se vero allora rigioca si puo' schiacciare
				if(controller.getDealer().getFinePartita())
				{
					rigiocaButton.setEnabled(true);
					
				}else
				{
					rigiocaButton.setEnabled(false);
				}
				
				if (numeroBot == 1)
				{
					//disegno delle prime due carte pescate dal dealer
					Carta carta1 = controller.getDealer().getListaCarteMano().get(0);
					Image immCarta1 = new ImageIcon(carta1.getAsset()).getImage();
					Carta carta2 = controller.getDealer().getListaCarteMano().get(1);
					Image immCarta2 = new ImageIcon(carta2.getAsset()).getImage();
					g.drawImage(immCarta1, 850 + (cardWidth + 5)*0, 20, cardWidth, cardHeight, null);
					g.drawImage(immCarta2, 850 + (cardWidth + 5)*1, 20, cardWidth, cardHeight, null);
	    			
	    			// posizione del mazzo
		
	    			Image mazzo = new ImageIcon("assets/carte/BACK.png").getImage();
	    			g.drawImage(mazzo, 1780, 20, cardWidth, cardHeight, null);
	    			
	    			g.setColor(Color.BLACK);
	    			g.setFont(new Font("Arial", Font.BOLD, 16));
	    			
	    			String text = "Mazzo";
	    			int textWidth = g.getFontMetrics().stringWidth(text);
	    			g.drawString(text,1780 +(cardWidth - textWidth) /2 ,20 + cardHeight + 20);
	    			   			
	    			
	    			// posizione della mano del bot 
	    			for (int i = 0 ; i < controller.getBot().getListaCarteMano().size(); i++)
	    			{ 
	
	    				Carta carta = controller.getBot().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 1200 + (cardWidth + 5)*i, 810, cardWidth, cardHeight, null);
	    				
	    			}
	    			String valoreManoBot = "Valore mano "+controller.getBot().getNome()+": "+String.valueOf(controller.getBot().valoreManoGiocatore());
	    			int textWidth1 = g.getFontMetrics().stringWidth(valoreManoBot);
	    			g.drawString(valoreManoBot,1240 +(cardWidth - textWidth1) /2 ,800 );
	    			
	    			
	    			// posizione della mano del player
	    			for (int i = 0 ; i < controller.getGiocatore().getListaCarteMano().size(); i++)
	    			{ 
	    				Carta carta = controller.getGiocatore().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 500 + (cardWidth + 5)*i, 810, cardWidth, cardHeight, null);
	    				
	    			}
	    			String valoreMano = "Valore mano "+menu.getProfilo().getNickname()+" : "+String.valueOf(giocatore.valoreManoGiocatore());
	    			int textWidth2 = g.getFontMetrics().stringWidth(valoreMano);
	    			g.drawString(valoreMano,530 +(cardWidth - textWidth2) /2 ,800 );
	    			
	    			
	    			//posizione e logica del dealer
	    			if (controller.getBot().getStai() || controller.getBot().getBustato())
	    			{
	    				controller.getDealer().iaDealer();
	    				for (int i = 2; i < controller.getDealer().getListaCarteMano().size() ; i++)
	    				{
	    					Carta carta = controller.getDealer().getListaCarteMano().get(i);
	    					Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    					g.drawImage(immCarta, 850 + (cardWidth + 5)*i, 20, cardWidth, cardHeight, null);
	    				}
	    			}
	    			// output del valore della mano del dealer gestito tramite operatore ternario
	    			String valoreManoDealer = "Valore mano Dealer: "+String.valueOf(controller.getDealer().getFinePartita() == true?controller.getDealer().valoreManoGiocatore() : controller.getDealer().getMano().valoreManoDealer() );
	    			int textWidth3 = g.getFontMetrics().stringWidth(valoreManoDealer);
	    			g.drawString(valoreManoDealer,890 +(cardWidth - textWidth3) /2 ,200);
	    			
	    			// controllo finale per vedere i risultati e il vincitore
	
	    			esito = controller.getPartita().logicaVittoria();
	    			//System.out.println(esito);
	    			g.setFont(new Font("Arial",Font.PLAIN,30));
	    			g.setColor(Color.WHITE);
	    			g.drawString(esito, 960, 540);		
	    			switch (esito)
	    			{
	    			case "Hai vinto!":
	    				System.out.print("partita vinta");
	    				menu.getProfilo().aggiornaVinte();
	    				menu.getProfilo().aumentaLivello();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			case "Pareggio!":
	    				System.out.print("partita pareggio");
	    				menu.getProfilo().aggiornaPareggio();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			case "Hai perso!":
	    				System.out.print("partita persa");
	    				menu.getProfilo().aggiornaPerse();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			}
				} else if (numeroBot == 2)
				{
					//disegno delle prime due carte pescate dal dealer
					Carta carta1 = controller.getDealer().getListaCarteMano().get(0);
					Image immCarta1 = new ImageIcon(carta1.getAsset()).getImage();
					Carta carta2 = controller.getDealer().getListaCarteMano().get(1);
					Image immCarta2 = new ImageIcon(carta2.getAsset()).getImage();
					g.drawImage(immCarta1, 850 + (cardWidth + 5)*0, 20, cardWidth, cardHeight, null);
					g.drawImage(immCarta2, 850 + (cardWidth + 5)*1, 20, cardWidth, cardHeight, null);
	    			
	    			// posizione del mazzo
	    			Image mazzo = new ImageIcon("assets/carte/BACK.png").getImage();
	    			g.drawImage(mazzo, 1780, 20, cardWidth, cardHeight, null);
	    			
	    			g.setColor(Color.BLACK);
	    			g.setFont(new Font("Arial", Font.BOLD, 16));
	    			
	    			String text = "Mazzo";
	    			int textWidth = g.getFontMetrics().stringWidth(text);
	    			g.drawString(text,1780 +(cardWidth - textWidth) /2 ,20 + cardHeight + 20);
	    			   			
	    			
	    			// posizione della mano del player
	    			for (int i = 0 ; i < controller.getGiocatore().getListaCarteMano().size(); i++)
	    			{ 
	    				Carta carta = controller.getGiocatore().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 740 + (cardWidth + 5)*i, 810, cardWidth, cardHeight, null);
	    				
	    			}
	    			String valoreMano = "Valore mano "+menu.getProfilo().getNickname()+" : "+String.valueOf(giocatore.valoreManoGiocatore());
	    			int textWidth2 = g.getFontMetrics().stringWidth(valoreMano);
	    			g.drawString(valoreMano,780 +(cardWidth - textWidth2) /2 ,800 );
	    			
	    			
	    			// posizione della mano del bot2
	    			for (int i = 0 ; i < controller.getBot2().getListaCarteMano().size(); i++)
	    			{ 
	
	    				
	    				Carta carta = controller.getBot2().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 1375 + (cardWidth + 5)*i, 810, cardWidth, cardHeight, null);
	    				
	    			}
	    			String valoreManoBot2 = "Valore mano "+controller.getBot2().getNome()+": "+String.valueOf(controller.getBot2().valoreManoGiocatore());
	    			int textWidthBot2 = g.getFontMetrics().stringWidth(valoreManoBot2);
	    			g.drawString(valoreManoBot2,1410 +(cardWidth - textWidthBot2) /2 ,800 );
	    			
	    			// posizione della mano del bot1
	    			for (int i = 0 ; i < controller.getBot().getListaCarteMano().size(); i++)
	    			{ 
	
	    				Carta carta = controller.getBot().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 10 + (cardWidth + 5)*i, 810, cardWidth, cardHeight, null);
	    				
	    			}
	    			String valoreManoBot = "Valore mano "+controller.getBot().getNome()+": "+String.valueOf(controller.getBot().valoreManoGiocatore());
	    			int textWidth1 = g.getFontMetrics().stringWidth(valoreManoBot);
	    			g.drawString(valoreManoBot,40 +(cardWidth - textWidth1) /2 ,800 );
	    			
	    			
	    			//posizione e logica del dealer
	    			if (controller.getBot().getStai() || controller.getBot().getBustato() && controller.getBot2().getStai() || controller.getBot2().getBustato())
	    			{
	    				controller.getDealer().iaDealer();
	    				for (int i = 2; i < controller.getDealer().getListaCarteMano().size() ; i++)
	    				{
	    					Carta carta = controller.getDealer().getListaCarteMano().get(i);
	    					Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    					g.drawImage(immCarta, 850 + (cardWidth + 5)*i, 20, cardWidth, cardHeight, null);
	    				}
	    			}
	    			// output del valore della mano del dealer gestito tramite operatore ternario
	    			String valoreManoDealer = "Valore mano Dealer: "+String.valueOf(controller.getDealer().getFinePartita() == true?controller.getDealer().valoreManoGiocatore() : controller.getDealer().getMano().valoreManoDealer() );
	    			int textWidth3 = g.getFontMetrics().stringWidth(valoreManoDealer);
	    			g.drawString(valoreManoDealer,890 +(cardWidth - textWidth3) /2 ,200);
	    			
	    			// controllo finale per vedere i risultati e il vincitore
	
	    			esito = controller.getPartita().logicaVittoria();
	    			//System.out.println(esito);
	    			g.setFont(new Font("Arial",Font.PLAIN,30));
	    			g.setColor(Color.WHITE);
	    			g.drawString(esito, 960, 540);
	    			
	    			System.out.println("Mano bot1 :" +controller.getBot().getListaCarteMano());
	    			System.out.println("Mano bot2 :" +controller.getBot2().getListaCarteMano());
	    			switch (esito)
	    			{
	    			case "Hai vinto!":
	    				menu.getProfilo().aggiornaVinte();
	    				menu.getProfilo().aumentaLivello();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			case "Pareggio!":
	    				menu.getProfilo().aggiornaPareggio();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			case "Hai perso!":
	    				menu.getProfilo().aggiornaPerse();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			}
	    			
	    			
				}else if(numeroBot == 3)
				{
			    	int cardWidth = 110; 
			    	int cardHeight= 154;
					//disegno delle prime due carte pescate dal dealer
					Carta carta1 = controller.getDealer().getListaCarteMano().get(0);
					Image immCarta1 = new ImageIcon(carta1.getAsset()).getImage();
					Carta carta2 = controller.getDealer().getListaCarteMano().get(1);
					Image immCarta2 = new ImageIcon(carta2.getAsset()).getImage();
					g.drawImage(immCarta1, 850 + (cardWidth + 5)*0, 20, cardWidth, cardHeight, null);
					g.drawImage(immCarta2, 850 + (cardWidth + 5)*1, 20, cardWidth, cardHeight, null);
	    			
	    			// posizione del mazzo
	    			Image mazzo = new ImageIcon("assets/carte/BACK.png").getImage();
	    			g.drawImage(mazzo, 1780, 20, cardWidth, cardHeight, null);
	    			
	    			g.setColor(Color.BLACK);
	    			g.setFont(new Font("Arial", Font.BOLD, 16));
	    			
	    			String text = "Mazzo";
	    			int textWidth = g.getFontMetrics().stringWidth(text);
	    			g.drawString(text,1780 +(cardWidth - textWidth) /2 ,20 + cardHeight + 20);
	    			   			
	    			int spazioPlayer = 0;
	    			// posizione della mano del player
	    			for (int i = 0 ; i < controller.getGiocatore().getListaCarteMano().size(); i++)
	    			{ 
	    				
	    				Carta carta = controller.getGiocatore().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 600 + spazioPlayer, 810, cardWidth, cardHeight, null);
	    				spazioPlayer += 55;
	    				
	    			}
	    			String valoreMano = "Valore mano "+menu.getProfilo().getNickname()+" : "+String.valueOf(giocatore.valoreManoGiocatore());
	    			int textWidth2 = g.getFontMetrics().stringWidth(valoreMano);
	    			g.drawString(valoreMano,655 +(cardWidth - textWidth2)  ,800 );
	    			
	    			
	    			int spazioBot2 = 0;
	    			// posizione della mano del bot2
	    			for (int i = 0 ; i < controller.getBot2().getListaCarteMano().size(); i++)
	    			{ 
	
	    				
	    				Carta carta = controller.getBot2().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 1000 + spazioBot2, 810, cardWidth, cardHeight, null);
	    				spazioBot2+= 55;
	    				
	    			}
	    			String valoreManoBot2 = "Valore mano "+controller.getBot2().getNome()+": "+String.valueOf(controller.getBot2().valoreManoGiocatore());
	    			int textWidthBot2 = g.getFontMetrics().stringWidth(valoreManoBot2);
	    			g.drawString(valoreManoBot2,1025+(cardWidth - textWidthBot2) /2 ,800 );
	    			
	    			int spazioBot3 = 0;
	    			// posizione della mano del bot3
	    			for (int i = 0 ; i < controller.getBot3().getListaCarteMano().size(); i++)
	    			{ 
	
	    				
	    				Carta carta = controller.getBot3().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 1475 +spazioBot3, 810, cardWidth, cardHeight, null);
	    				spazioBot3+=55;
	    				
	    			}
	    			String valoreManoBot3 = "Valore mano "+controller.getBot3().getNome()+": "+String.valueOf(controller.getBot3().valoreManoGiocatore());
	    			int textWidthBot3 = g.getFontMetrics().stringWidth(valoreManoBot2);
	    			g.drawString(valoreManoBot3,1500 +(cardWidth - textWidthBot3) /2 ,800 );
	    			
	    			int spazioBot1 = 0;
	    			// posizione della mano del bot1
	    			for (int i = 0 ; i < controller.getBot().getListaCarteMano().size(); i++)
	    			{ 
	
	    				Carta carta = controller.getBot().getListaCarteMano().get(i);
	    				Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    				g.drawImage(immCarta, 10 +spazioBot1, 810, cardWidth, cardHeight, null);
	    				spazioBot1 += 55;
	    				
	    			}
	    			String valoreManoBot = "Valore mano "+controller.getBot().getNome()+": "+String.valueOf(controller.getBot().valoreManoGiocatore());
	    			int textWidth1 = g.getFontMetrics().stringWidth(valoreManoBot);
	    			g.drawString(valoreManoBot,40 +(cardWidth - textWidth1) /2 ,800 );
	    			
	    			
	    			//posizione e logica del dealer
	    			if (controller.getBot().getStai() || controller.getBot().getBustato() && controller.getBot2().getStai() || controller.getBot2().getBustato() && controller.getBot3().getStai() || controller.getBot3().getBustato())
	    			{
	    				controller.getDealer().iaDealer();
	    				for (int i = 2; i < controller.getDealer().getListaCarteMano().size() ; i++)
	    				{
	    					Carta carta = controller.getDealer().getListaCarteMano().get(i);
	    					Image immCarta = new ImageIcon(carta.getAsset()).getImage();
	    					g.drawImage(immCarta, 850 + (cardWidth + 5)*i, 20, cardWidth, cardHeight, null);
	    				}
	    			}
	    			// output del valore della mano del dealer gestito tramite operatore ternario
	    			String valoreManoDealer = "Valore mano Dealer: "+String.valueOf(controller.getDealer().getFinePartita() == true?controller.getDealer().valoreManoGiocatore() : controller.getDealer().getMano().valoreManoDealer() );
	    			int textWidth3 = g.getFontMetrics().stringWidth(valoreManoDealer);
	    			g.drawString(valoreManoDealer,890 +(cardWidth - textWidth3) /2 ,200);
	    			
	    			// controllo finale per vedere i risultati e il vincitore
	    			esito = controller.getPartita().logicaVittoria();
	    			//System.out.println(esito);
	    			g.setFont(new Font("Arial",Font.PLAIN,30));
	    			g.setColor(Color.WHITE);
	    			g.drawString(esito, 960, 540);
	    			
	    			System.out.println("Mano bot1 :" +controller.getBot().getListaCarteMano());
	    			System.out.println("Mano bot2 :" +controller.getBot2().getListaCarteMano());
	    			System.out.println("Mano bot3 :" +controller.getBot3().getListaCarteMano());
	    			switch (esito)
	    			{
	    			case "Hai vinto!":
	    				menu.getProfilo().aggiornaVinte();
	    				menu.getProfilo().aumentaLivello();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			case "Pareggio!":
	    				menu.getProfilo().aggiornaPareggio();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			case "Hai perso!":
	    				menu.getProfilo().aggiornaPerse();
	    				menu.getProfilo().salvataggioProfilo();
	    				break;
	    			}
				}
    		}
    	};
    	
    	

    	
    	
    	
    	
    	JPanel buttonPanel = new JPanel();
    	pescaButton = new JButton("Pesca");
    	staiButton = new JButton("Stai");
    	rigiocaButton = new JButton("Rigioca");
    	esciButton = new JButton("Menu' principale");
    	

    	
    	setVisible(true);
    	setSize(1920,1080);
    	setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	setLocationRelativeTo(null);
    	setResizable(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	// inserimento icona della in alto a sinistra della finestra
    	try {
    		setIconImage(ImageIO.read(new File("C:\\Users\\UTENTE\\eclipse-workspace\\JBlackJack\\assets\\carte\\icona.png")));
    		} catch (IOException e) {}
    	
    	gamePanel.setLayout(new BorderLayout());
    	gamePanel.setBackground(new Color(53, 101, 77));
    	add(gamePanel);
    	
    	
    	
    	
    	
    	
    	pescaButton.setFocusable(false);
    	buttonPanel.add(pescaButton);
    	staiButton.setFocusable(false);
    	buttonPanel.add(staiButton);
    	rigiocaButton.setFocusable(false);
    	buttonPanel.add(rigiocaButton);
    	esciButton.setFocusable(false);
    	buttonPanel.add(esciButton);
    	add(buttonPanel, BorderLayout.SOUTH);
    	
        controller.addPescaButtonListener(pescaButton);
        controller.addStaiButtonListener(staiButton);
    	controller.addRigiocaButtonListener(rigiocaButton);
    	controller.addEsciButtonListener(esciButton);

    }
    
    /**
     * getter per il gamePanel
     * @return gamePanel
     */
    public JPanel getGamePanel() { return gamePanel;}
    
    /**
     * getter per il menu'
     * @return menu'
     */
    public MenuView getMenu() { return menu;}
    
    /**
     * getter per l'audio del menu'
     * @return audio menu'
     */
    public AudioManager getAudioManagerMenu() { return audioManagerMenu;}
    
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("sono stato chiamato da "+o);
		gamePanel.repaint();

	}
    

}
