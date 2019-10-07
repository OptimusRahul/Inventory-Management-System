import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
public class MP3 {
    private String filename;
    private Player player;
    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
        this.filename = filename;
    }
    // play the MP3 file
    public void play() {
        try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
            player.play();
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }   
    }
    public static void main(String[] args) {
        MP3 mp3 = new MP3("C:/Users/pravendra/Downloads/Atif_Aslam_DJMaza.Com_-_Tu_Jaane_Na_DJMaza.Com_(mp3.pm).mp3");
        mp3.play();
   
    }

}