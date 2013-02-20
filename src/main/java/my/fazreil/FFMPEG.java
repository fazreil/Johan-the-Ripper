/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package my.fazreil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author fazreil.jalil
 */
public class FFMPEG {
    
    String strCommand;
    File ffmpegBin;
    
    public FFMPEG(String ffmpegBinPath)
    {
    	try{
	    	ffmpegBin = new File(ffmpegBinPath);
	    	if(!ffmpegBin.canExecute())
	    	{
	    		System.out.println("ffmpeg is at "+ffmpegBin.getCanonicalPath());
	    	}
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    		ex.printStackTrace();
    	}
    }
    
    public boolean convertMP4toMP3(String mp4VideoPath, String resultingMP3Path)
    {
    	return convertMP4toMP3((long)128, mp4VideoPath, resultingMP3Path);
    }
    
    public boolean convertMP4toMP3(long audioBitrate, String mp4VideoPath, String resultingMP3Path)
    {
    	boolean returningBoolean = true;
    	
    	try {
    		
    		File mp4VideoFile = new File(mp4VideoPath);
    		File resultingMP3File = new File(resultingMP3Path);
	    	String command = ffmpegBin.getCanonicalPath()+
	    			" -i \""+mp4VideoFile.getCanonicalPath()+"\""+
	    			" -ab "+audioBitrate+""+
	    			" \""+resultingMP3File.getCanonicalPath()+"\"";
	    	System.out.println("Command: "+command);
	    	Runtime runtime = Runtime.getRuntime();
	    	Process process = runtime.exec(command);
			System.out.println("Process "+process.toString()+" exit status: "+process.waitFor());
			returningBoolean = resultingMP3File.canRead();
		} catch (IOException e) {
			e.printStackTrace();
			returningBoolean = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			returningBoolean = false;
		} catch (Exception ex) {
			ex.printStackTrace();
			returningBoolean = false;
		}
    	System.out.println(returningBoolean);
    	return returningBoolean;
    }
    
    public static void main(String args[])
    {
    	FFMPEG ffmpeg = new FFMPEG("C:\\Program Files\\ffmpeg\\bin\\ffmpeg.exe");
    	String dir = "D:\\v\\";
    	String[] files = {
    			"Apo Kono Eh Jang - Dato AC Mizal, Fida Ibrahim & Stellar Band (Official Video   LIRIK)",
    			"Don Omar - Danza Kuduro ft. Lucenzo",
    			"DRAMA BAND - Cerita Dia Official Music Video",
    			"GORILLA - GANGNAM STYLE Ft. Ricky Martin",
    			"Najwa Latif feat Sleeq & Syamkamarul Sahabat - Official Music Video",
    			"Official Music Video - Tasha Manshahar & Syed Shamim - Be Mine #CloraStudio",    			
    	};
    	for(String i:files)
    	{
    		ffmpeg.convertMP4toMP3(dir+i+".mp4", dir+i+".mp3");
    	}
    }
    
}
