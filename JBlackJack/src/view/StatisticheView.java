package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.StatisticheController;
import model.Profilo;

/**
 * classe StatisticheView
 */
public class StatisticheView extends JFrame 
{
	/**
	 * profilo
	 */
	private Profilo p;
	
	/**
	 * menu view
	 */
	private MenuView menuView;
	
	/**
	 * Text area dove andranno scritte le statistiche
	 */
	private JTextArea statistiche;
	
	/**
	 * il panello dove verra' mostrato l'avatar
	 */
	private JPanel avatarPanel;
	
	/**
	 * path dell'avatar
	 */
	private String pathAvatar;
	
	/**
	 * sfondo di background della view
	 */
	private Image sfondo;
	
	/**
	 * costruttore per la creazione della StatisticheView. Possiamo trovare:
	 * 1) l'inserimento dell'icona della finestra in alto a sinistra e il background della view
	 * 2) la creazione dei vari bottoni e panel
	 * 3) l'aggiunta dei bottoni ai listener del controller
	 * @param p profilo
	 * @param menuView menu view
	 */
	public StatisticheView(Profilo p, MenuView menuView)
	{
		
		this.menuView = menuView;
		this.p = p;
		this.pathAvatar = p.getAvatar();
		StatisticheController controller = new StatisticheController(this,menuView);
		setTitle("JBlackJack");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// inserimento icona della in alto a sinistra della finestra		
		try {
			setIconImage(ImageIO.read(new File("assets/carte/icona.png")));
		} catch (IOException e) {}
		

        
        
		try {
			sfondo = ImageIO.read(new File("assets/carte/sfondoStats.png"));
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
        
		JButton buttonTornaIndietro = new JButton("Torna indietro"); 
        statistiche = new JTextArea(p.toString());
        statistiche.setEditable(false); 
        statistiche.setOpaque(false); 
        statistiche.setFocusable(false); 
        statistiche.setForeground(Color.BLACK);

		avatarPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g)
			{
				if(pathAvatar != null)
				{
					File avatarSelezionato = new File(pathAvatar);
					if(avatarSelezionato.exists())
					{
						try
						{
							BufferedImage immagineOriginale = ImageIO.read(avatarSelezionato);
							int width = 100;
							int heigth = 100;
							BufferedImage immagineModificata = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
							Graphics2D g2d = immagineModificata.createGraphics();
							g2d.drawImage(immagineOriginale, 0, 0, width, heigth, null);
							g2d.dispose();
                            int x = (getWidth() - immagineModificata.getWidth()) / 2;
                            int y = (getHeight() - immagineModificata.getHeight()) / 2;
                            g.drawImage(immagineModificata, x, y, this);
						} catch (IOException e) {
                         e.printStackTrace();
					}
				}
			}
		}
        
	};
	
	 avatarPanel.setPreferredSize(new Dimension(100, 100));
		
		
     gbc.gridx = 0;
     gbc.gridy = 0;
     backGroundPanel.add(avatarPanel, gbc);
   
     gbc.gridx = 0;
     gbc.gridy = 1;
     backGroundPanel.add(statistiche, gbc);
        
     gbc.gridx = 0;
     gbc.gridy = 2;
     backGroundPanel.add(buttonTornaIndietro, gbc);
        
     controller.addTornaIndietroButtonListener(buttonTornaIndietro);
     setContentPane(backGroundPanel);
	}
}
		
