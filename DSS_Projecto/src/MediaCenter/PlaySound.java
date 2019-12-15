package MediaCenter;

public class PlaySound {

    public static void play(String path) {
        try{
            Runtime.getRuntime().exec("vlc " + path);
        } catch (Exception e)
        {
            System.out.println("something failed");
        }
    }
}
