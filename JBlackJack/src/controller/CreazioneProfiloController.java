package controller;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.AudioManager;
import view.CreazioneProfiloView;
import view.MenuView;
import model.Profilo;

/**
 * classe CreazioneProfiloController
 */
public class CreazioneProfiloController 
{

	/**
	 * view della creazione del profilo
	 */
	private CreazioneProfiloView p;
	
	/**
	 * profilo
	 */
	private Profilo profilo;
	
	/**
	 * view del menu
	 */
	private MenuView menuView;
	
	/**
	 * costuttore per la creazione del controller
	 * @param p view del profilo
	 * @param menuView view del menu
	 */
	public CreazioneProfiloController(CreazioneProfiloView p,MenuView menuView)
	{
		this.p = p;
		this.menuView = menuView;
		profilo = Profilo.getInstance();
	}
	
	/**
	 * metodo che aggiunge la funzionalita' di scelta dell'avatar all'avatarPanel 
	 * @param avatarPanel pannello avatar
	 */
	public void addAvatarPanelMouseListener(JPanel avatarPanel)
	{
		avatarPanel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// implementare il click sull'avatar
				JFileChooser avatarScelta = new JFileChooser();
				avatarScelta.setDialogTitle("Scegli un'avatar");
				avatarScelta.setCurrentDirectory(new File("assets/avatar"));
				int risultato = avatarScelta.showOpenDialog(null);
				if (risultato == JFileChooser.APPROVE_OPTION)
				{
					File avatarSelezionato = avatarScelta.getSelectedFile();
					String path = avatarSelezionato.getAbsolutePath();
					try
					{
						BufferedImage immagineOriginale = ImageIO.read(avatarSelezionato);
						int width = 100;
						int heigth = 100;
						BufferedImage immagineModificata = new BufferedImage(width,heigth, BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = immagineModificata.createGraphics();
						g.drawImage(immagineOriginale,0,0,width,heigth,null);
						g.dispose();	
						p.setImmagineAvatar(immagineModificata);
						profilo.setAvatar(path);
					}catch (IOException exception)
					{
						exception.printStackTrace();
					}
					
				}
			}
		});
	}
	
	/**
	 * metodo che implementa il funzionamento del bottone salva, quindi una volta cliccato salvera' il profilo e ci rimandera' al menu solo se rispetta determinate caratteristiche come: aver scritto il nickname 
	 * nell'apposito field rispettando i caratteri ammessi e aver scelto l'avatar
	 * @param salvaButton bottone salva
	 */
	public void addSalvaButtonListener(JButton salvaButton)
	{
		salvaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String nome = p.getNomeField().getText();
				String pathAvatar = profilo.getAvatar();
				
				if(nome.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Inserisci il tuo nickname");
				} else if (nome.contains(" ")|| nome.contains("_") || nome.contains(",") || nome.contains(";") )
				{
					JOptionPane.showMessageDialog(null,"Il nickname contiene caratteri non ammessi");
				}else if(pathAvatar == null || pathAvatar.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Scegli un'avatar!");
				}
				else
				{
					profilo.setNickname(nome);
					profilo.salvataggioProfilo();
					p.dispose();
					
					AudioManager.getInstance().play("assets/suoni/rumoreBottoni.wav", 0.8, false);
					menuView.getCanzoneMenu().stop();
					new MenuView(profilo);
					System.out.println(profilo);
					
				}
			}
		});
	}
	
	/**
	 * getter del' oggetto profilo
	 * @return profilo
	 */
	public Profilo getProfilo() { return profilo;}
	
	
}
