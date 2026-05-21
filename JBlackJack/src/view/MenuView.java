package view;

import java.awt.Color;
import java.awt.Dimension;
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

import controller.MenuController;
import model.Profilo;


/**
 * classe MenuView
 */
public class MenuView  extends JFrame
{
	/**
	 * menu controller
	 */
	private MenuController controllerMenu;
	
	/**
	 * profilo
	 */
	private Profilo profilo;
	
	/**
	 * background della view
	 */
	private Image sfondo;
	
	/**
	 * canzone del menu'
	 */
	private AudioManager canzoneMenu;
	
	/**
	 * costruttore delle MenuView. Possiamo trovare:
	 * 1) inserimento dell'icona in alto a sinistra e dello sfondo di background
	 * 2) l'impostazione del layout della  view con i vari bottoni di cui uno cambia nome 
	 * in base all'esistenza del profilo, abbiamo crea profilo se 
	 * questo non esiste e statistiche se il profilo esiste
	 * 3) l'aggiunta dei bottoni ai listener del controller
	 * @param profilo profilo
	 */
	public MenuView(Profilo profilo)
	{
		
		this.profilo = profilo;
		controllerMenu = new MenuController(MenuView.this);	
		// impostazioni del frame
		setTitle("JBlackJack");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // centra la finestra sullo schermo
		setResizable(false);
		setVisible(true);
		
		// inserimento icona della in alto a sinistra della finestra		
		try {
			setIconImage(ImageIO.read(new File("assets/carte/icona.png")));
		} catch (IOException e) {}
		
		
		try {
			sfondo = ImageIO.read(new File("assets/carte/sfondo.png"));
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
		
		// impostare il layout del frame
		canzoneMenu = AudioManager.getInstance();
		canzoneMenu.play("assets/suoni/canzoneMenu.wav", 0.7, true);
        backGroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.set(10, 10, 10, 10); // Margini tra i pulsanti
		
		// Creazione pulsanti
		JButton newGameButton = new JButton("Gioca");
		JButton newProfiloButton = new JButton(profilo.getNickname() == ""? "Crea profilo" : "Statistiche");
		JButton newExitButton = new JButton("Esci");
		controllerMenu.addNewGameButtonListener(newGameButton);
		controllerMenu.addnewProfiloButtonListener(newProfiloButton);
		controllerMenu.addnewExitButtonListener(newExitButton);
		

        // Imposta dimensioni preferite dei pulsanti
        Dimension buttonSize = new Dimension(200, 200);
        newGameButton.setMinimumSize(buttonSize);
        newProfiloButton.setMinimumSize(buttonSize);
        newExitButton.setMinimumSize(buttonSize);
       
		
     // aggiungere i pulsanti al frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        backGroundPanel.add(newGameButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        backGroundPanel.add(newProfiloButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        backGroundPanel.add(newExitButton, gbc);	
        setContentPane(backGroundPanel);
        

	}

	/**
	 * getter del profilo
	 * @return profilo
	 */
	public Profilo getProfilo() { return profilo;}
	
	/**
	 * getter della canzone del menu'
	 * @return audio del menu'
	 */
	public AudioManager getCanzoneMenu() { return canzoneMenu;}
	
	//public static void main(String[] args)
	//{
		//new MenuView().setVisible(true);;
	//}
}
	
	
