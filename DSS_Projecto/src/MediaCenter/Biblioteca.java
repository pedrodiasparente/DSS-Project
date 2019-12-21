package MediaCenter;

import JDBC.MediaDAO;
import JDBC.MediaUtilizadorDAO;

import java.util.HashMap;

public class Biblioteca {

    private MediaUtilizadorDAO media;
    private MediaDAO mediaDAO;
    private HashMap<String, Playlist> playlists;

    public Biblioteca() {
        mediaDAO = MediaDAO.getInstance();
    }

    public Biblioteca (Utilizador u){
        media = MediaUtilizadorDAO.getInstance(u);

    }

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
        HashMap<String, Media> mediaUser = new HashMap<>();
        for(Media m : media.values()){
            mediaUser.put(m.getNome(),m);
        }
        return mediaUser;
    }
}