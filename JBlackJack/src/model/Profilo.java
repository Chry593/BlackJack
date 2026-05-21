package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;

/**
 * classe Profilo
 */
public class Profilo extends Observable
{
	/**
	 * istanza del profilo
	 */
	private static Profilo instance;
	
	/**
	 * nickname del profilo
	 */
	public String nickname;
	
	/**
	 * path per l'avatar giocatore
	 */
	public String avatar;
	
	/**
	 * partite perse,vinte, partiteGiocate e il livello del giocatore
	 */
	public int perse,vinte,partiteGiocate,livelloGiocatore,pareggio;
	
	/**
	 * costruttore per creare profilo utente
	 */
	private Profilo()
	{
		this.nickname = "";
		this.perse = 0;
		this.vinte = 0;
		this.partiteGiocate = 0;
		this.livelloGiocatore = 0;
		this.pareggio = 0;
	}
	
	/**
	 * metodo per ottenere l'istanza del profilo
	 * @return istanza del profilo
	 */
	public static Profilo getInstance() 
	{
		if (instance == null)
			return new Profilo();
		else
			return instance;
	}
	
	/**
	 * aggiorna le partite in pareggio del giocatore e quelle giocate
	 */
	public void aggiornaPareggio() 
	{ 
		pareggio ++;
		partiteGiocate ++;

	}
	
	
	/**
	 * aggiorna le partite vinte dal giocatore e quelle giocate
	 */
	public void aggiornaVinte() 
	{ 
		vinte ++;
		partiteGiocate ++;

	}
	
	/**
	 * aggiorna le partite perse dfal giocatore e quelle giocate
	 */
	public void aggiornaPerse()
	{ 
		perse ++;
		partiteGiocate ++;

	}
	
	/**
	 * aggiorna il livello del giocatore ogni 10 partite vinte
	 */
	public void aumentaLivello()
	{
		if (vinte % 10 == 0)
			livelloGiocatore++;

			
	}
	
	/**
	 * getter per il nome del profilo
	 * @return nickname del profilo
	 */
	public String getNickname () { return nickname;}
	
	/**
	 * getter per le partite perse del giocatore
	 * @return partite perse
	 */
	public int getPerse() { return perse;}
	
	
	/**
	 * getter per le partite in pareggio del giocatore
	 * @return partite in pareggio
	 */
	public int getPareggio() { return pareggio;}
	
	/**
	 * getter per le partite vinte del giocatore
	 * @return return delle partite vinte
	 */
	public int getVinte() { return vinte;}
	
	/**
	 * getter per le partite totali giocate dal giocatore
	 * @return parite totali giocate
	 */
	public int getPartiteGiocate() { return partiteGiocate;}
	
	/**
	 * getter per il livello del giocatore
	 * @return livello del giocatore
	 */
	public int getLivelloGiocatore() { return livelloGiocatore;}
	
	/**
	 * toString del profilo
	 */
	@Override
	public String toString() {return getNickname() + "\nPartite giocate: "+getPartiteGiocate()+"\nPartite vinte: "+getVinte()+"\nPartite perse: "+getPerse()+"\nPartite pareggio: "+getPareggio()+"\nLivello giocatore: "+getLivelloGiocatore(); }
	
	/**
	 * getter del path per l'avatar
	 * @return path dell'avatar
	 */
	public String getAvatar() { return avatar; }

	/**
	 * setter dell'avatar
	 * @param a path dell' avatar
	 */
	public void setAvatar(String a)
	{
		avatar = a;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * setter nel nickName del giocatore
	 * @param nickname nome del giocatre
	 */
	public void setNickname(String nickname) { this.nickname = nickname; }

	/**
	 * setter delle partite perse
	 * @param perse partite perse
	 */
	public void setPerse(int perse) { this.perse = perse; }

	/**
	 * setter delle partite vinte
	 * @param vinte partite vinte
	 */
	public void setVinte(int vinte) { this.vinte = vinte; }

	/**
	 * setter delle partite giocate 
	 * @param partiteGiocate partite giocate
	 */
	public void setPartiteGiocate(int partiteGiocate) { this.partiteGiocate = partiteGiocate; }

	/**
	 * setter del livello del giocatore
	 * @param livelloGiocatore livello del giocatore
	 */
	public void setLivelloGiocatore(int livelloGiocatore) { this.livelloGiocatore = livelloGiocatore; }

	/**
	 * setter delle partite in pareggio
	 * @param pareggio partite in pareggio
	 */
	public void setPareggio(int pareggio) { this.pareggio = pareggio; }

	/**
	 * metodo per salvare il profilo in un file .txt
	 */
	public void salvataggioProfilo()
	{
		try {
			FileWriter file = new FileWriter("assets/profilo/profilo.txt");
			BufferedWriter f = new BufferedWriter(file);
			
			// scriviamo le statistiche del personaggio giocate vinte perse
			f.write(getNickname());
			f.newLine();
			f.write(Integer.toString(getPartiteGiocate()));
			f.newLine();
			f.write(Integer.toString(getVinte()));			
			f.newLine();
			f.write(Integer.toString(getPerse()));
			f.newLine();
			f.write(Integer.toString(getPareggio()));
			f.newLine();
			f.write(Integer.toString(getLivelloGiocatore()));
			f.newLine();
			f.write(getAvatar());
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo che carica e crea un profilo da un file txt 
	 * @return profilo creato
	 */
	public Profilo caricaProfilo()
	{
		String nickname,partiteGiocate,partiteVinte,partitePerse,partitePareggio,livelloGiocatore,avatarPath;
		
		FileReader file;
		try {
			file = new FileReader("assets/profilo/profilo.txt");
			BufferedReader f = new BufferedReader(file);
			
			//lettura delle righe
			nickname = f.readLine();
			partiteGiocate = f.readLine();
			partiteVinte = f.readLine();
			partitePerse = f.readLine();
			partitePareggio = f.readLine();
			livelloGiocatore = f.readLine();
			avatarPath = f.readLine();
			// creo il profilo
			Profilo p = Profilo.getInstance();
			p.setNickname(nickname);
			p.setPartiteGiocate(Integer.parseInt(partiteGiocate));
			p.setVinte(Integer.parseInt(partiteVinte));
			p.setPerse(Integer.parseInt(partitePerse));
			p.setPareggio(Integer.parseInt(partitePareggio));
			p.setLivelloGiocatore(Integer.parseInt(livelloGiocatore));
			p.setAvatar(avatarPath);
			return p;
			
			//giocate vinte perse pareggio livello
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
