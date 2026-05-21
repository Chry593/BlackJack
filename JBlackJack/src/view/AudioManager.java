package view;
import java.io.BufferedInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * classe AudioManager
 */
public class AudioManager 
{

	/**
	 * istanza dell'AudioManager
	 */
	private static AudioManager instance;
	private Clip clip;
	
	/**
	 * metodo per ottenere istanza dell'AudioManager
	 * @return istanza dell'AudioManager
	 */
	public static AudioManager getInstance()
	{
		if(instance == null)
			return new AudioManager();
		else
			return instance;
	}
	
	private AudioManager() {}
	
	/**
	 * metodo per far partire l'audio 
	 * @param filename path dell'audio
	 * @param d il volume da dare all'audio
	 * @param loop valore booleano che se impostato su true allora mette in loop l'audio, mentre su false l'audio non verra' riprodotto un'altra volta una volta finito
	 */
	public void play(String filename,double d,boolean loop)
	{
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filename));
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			setVolume(clip,d);
			clip.start();
			

			
			if(loop)
			{
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * metodo che stoppa l'audio in corso
	 */
	public void stop() 
	{
		if (clip.isRunning() && clip != null)
			clip.stop();
	}
	
	/**
	 * metodo che setta il volume dell'audio
	 * @param clip audio 
	 * @param d il valore del volume
	 */
    private void setVolume(Clip clip, double d) 
    {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (float) ((range * d) + gainControl.getMinimum());
        gainControl.setValue(gain);
    }
}
