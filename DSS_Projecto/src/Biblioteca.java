import java.util.HashMap;

public class Biblioteca {

    private HashMap<String, Media> media;
    private HashMap<String, Playlist> playlists;

    public HashMap<String, Media> getMedia() {
        HashMap<String, Media> res = new HashMap<>();
        for (String s : media.keySet()) {
            for (Media m : media.values()) {
                res.put(s, m);
            }
        }
        return res;
    }
}