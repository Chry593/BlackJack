package view;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.SceltaBotController;
import controller.StatisticheController;

/**
 * classe SceltaBotView
 */
public class SceltaBotView extends JFrame
{

	/**
	 * view del menu
	 */
	private MenuView menuView;
	
	/**
	 * controller della scelta dei bot
	 */
	private SceltaBotController controller;
	
	/**
	 * background della view
	 */
	private Image sfondo;
	
	/**
	 * costruttore per la creazione della SceltaBotView. Possiamo trovare:
	 * 1) l'inserimento dell'icona della finestra in alto a sinistra e il background della view
	 * 2) la creazione dei vari bottoni e panel
	 * 3) l'aggiunta dei bottoni ai listener del controller
	 * @param menuView view del menu
	 */
	public SceltaBotView(MenuView menuView)
	{
		this.menuView = menuView;
		controller = new SceltaBotController(menuView.getProfilo(),this,menuView);
		setTitle("JBlackJack");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		try {
			sfondo = ImageIO.read(new File("assets/carte/sfondoSceltaBot.png"));
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		JPanel backGroundPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(sfondo, 0,0,getWidth(),getHeight(),this);
				
			}
		};
		
		backGroundPanel.setLayout(new GridBagLayout());
		setResizable(false);
		setVisible(true);
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.set(10, 10, 10, 10);
		
        
		// inserimento icona della in alto a sinistra della finestra		
		try {
			setIconImage(ImageIO.read(new File("assets/carte/icona.png")));
		} catch (IOException e) {}
		
	
		JButton buttonUnBot= new JButton("1 Bot"); 
		JButton buttonDueBot= new JButton("2 Bot"); 
		JButton buttonTreBot= new JButton("3 bot"); 
		JButton buttonTornaIndietro= new JButton("Torna indietro"); 
        
		
	

        gbc.gridx = 0;
        gbc.gridy = 0;
        backGroundPanel.add(buttonUnBot, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        backGroundPanel.add(buttonDueBot, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        backGroundPanel.add(buttonTreBot, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        backGroundPanel.add(buttonTornaIndietro, gbc);
         
        controller.addUnBotButtonListener(buttonUnBot);
        controller.addDueBotButtonListener(buttonDueBot);
        controller.addTreBotButtonListener(buttonTreBot);
        controller.addTornaIndietroButtonListener(buttonTornaIndietro);
        
        
        
        setContentPane(backGroundPanel);
		
	}
	
	
}
