/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package my.fazreil;

import java.io.IOException;

/**
 *
 * @author fazreil.jalil
 */
public class FFMPEG {
    
    String strCommand;
    
    public FFMPEG()
    {
        
    }
    
    public FFMPEG(String input, long b, long ab, String vcodec, String acodec, int sizeW, int sizeH, String outputExtension, String mode) throws Exception
    {
        if(mode.equalsIgnoreCase("audio"))
        {
            strCommand = "\"C:\\Program Files\\ffmpeg\\ffmpeg.exe\" -i \""+input+"\" -ab "+ab+"k -acodec "+acodec+" \""+input+".JohanRIP."+outputExtension+"\"";
        }
        else if(mode.equalsIgnoreCase("video"))
        {
            strCommand = "\"C:\\Program Files\\ffmpeg\\ffmpeg.exe\" -i \""+input+"\" -b "+b+"k -ab "+ab+"k -vcodec "+vcodec+" -s "+sizeW+"x"+sizeH+" -acodec "+acodec+" \""+input+".JohanRIP."+outputExtension+"\"";
        }
        exe();
    }
    
    public void exe() throws IOException
    {
        Runtime.getRuntime().exec(strCommand);
    }
    
    
}
