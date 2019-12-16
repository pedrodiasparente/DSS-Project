package MediaCenter;

import JDBC.MediaDAO;

import java.util.HashMap;

public class Biblioteca {

    private MediaDAO media;
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

    public HashMap<String, Media> getUserMedia(Utilizador currentUser){
        media.getUserMedia(currentUser);
        return null;
    }
}