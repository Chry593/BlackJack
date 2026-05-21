package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CreazioneProfiloController;

/**
 * classe CreazioneProfiloView
 */
public class CreazioneProfiloView  extends JFrame implements Observer
{

	/**
	 * controller della creazione profilo
	 */
	private CreazioneProfiloController controllerProfilo;
	
	/**
	 * pannello dell'avatar
	 */
	private JPanel avatarPanel;
	
	/**
	 * immagine dell'avatar
	 */
	private BufferedImage immagineAvatar;
	
	/**
	 * il text field dove inserire il nome
	 */
	private JTextField nomeField;
	
	/**
	 * il pannello del nome dove inserire il field 
	 */
	private JPanel nomePanel;
	
	/**
	 * view del menu
	 */
	private MenuView menuView;
	
	/**
	 * sfondo in background della view
	 */
	private Image sfondo;
	
	/**
	 * costruttore della CreazioneProfiloView. Possiamo trovare 
	 * 1) controllerProfilo.getProfilo().addObserver(this) che servira' a 
	 * notificare la view che il Profilo ha scelto l'avatar cosi' da disegnarlo
	 * 2) Il titolo della finestra setTitle("JBlackJack");
	 * 3) Il settaggio dell'icona in alto a sinistra e del background della view
	 * 4) La creazione dei vari panel, bottoni e textField
	 * 5) l'aggiunta dei bottoni ai listener del controller
	 * @param menuView view del menu
	 */
	public CreazioneProfiloView( MenuView menuView)
	{
		controllerProfilo = new CreazioneProfiloController(this,menuView);
		controllerProfilo.getProfilo().addObserver(this);
		this.menuView = menuView;
		setTitle("JBlackJack");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
		try {
			sfondo = ImageIO.read(new File("assets/carte/sfondoCreaProfilo.png"));
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
		backGroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.set(10, 10, 10, 10); // Margini tra i pulsanti
	
		// inserimento icona della in alto a sinistra della finestra		
		try {
			setIconImage(ImageIO.read(new File("assets/carte/icona.png")));
		} catch (IOException e) {}
		
		// sezione scelta avatar
		avatarPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				if (immagineAvatar != null)
				{
					int x = (getWidth() - immagineAvatar.getWidth()) / 2;
					int y = (getHeight() - immagineAvatar.getHeight()) / 2;
					g.drawImage(immagineAvatar, x, y,this);
				}else
				{
					g.setColor(Color.BLACK);
					g.fillOval((getWidth() - 100) / 2, (getHeight() - 100) / 2, 100, 100);
				}
			}
		};
		
		avatarPanel.add(new JLabel("Scegli un'avatar") {
			{
				setForeground(Color.BLACK);
			}
		});
		avatarPanel.setPreferredSize(new Dimension(200,200));
		avatarPanel.setOpaque(false);
		controllerProfilo.addAvatarPanelMouseListener(avatarPanel);
		
	     // aggiungere i pulsanti al frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        backGroundPanel.add(avatarPanel, gbc);
		
		// sezione scelta nome
		nomeField = new JTextField(20);
		nomePanel = new JPanel();
		nomePanel.add(new JLabel("Inserisci nickname") {
			{
				setForeground(Color.BLACK);
			}
		});
		nomePanel.add(nomeField);
		nomePanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        backGroundPanel.add(nomePanel, gbc);
		
		
		// sezione bottone salva
		JButton salvaButton = new JButton("Salva");
		controllerProfilo.addSalvaButtonListener(salvaButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backGroundPanel.add(salvaButton, gbc);	
        setContentPane(backGroundPanel);
		

	
	
	}

	/**
	 * setter dell'immagine dell'avatar
	 * @param immagineAvatar immagine dell'avatar
	 */
	public void setImmagineAvatar(BufferedImage immagineAvatar) {this.immagineAvatar = immagineAvatar;}
	
	/**
	 * getter del TextField del nome
	 * @return TextField del nome
	 */
	public JTextField getNomeField() { return nomeField;}
	
	/**
	 * getter della view del menu
	 * @return view del menu
	 */
	public MenuView getMenuView() { return menuView;}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		avatarPanel.repaint();
		System.out.println("chiamato");
	}
	
	
}
