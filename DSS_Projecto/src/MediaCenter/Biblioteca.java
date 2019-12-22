package MediaCenter;

import JDBC.MediaDAO;
import JDBC.MediaUtilizadorDAO;

import java.util.HashMap;

public class Biblioteca {

    private MediaUtilizadorDAO media;
    private HashMap<String, Playlist> playlists;

    public Biblioteca (Utilizador u){
        media = MediaUtilizadorDAO.getInstance(u);

    }

    public void addMedia(Media m){
        media.put(m.getNome(),m);
    }

    public HashMap<String, Media> getMedia() {
        HashMap<String, Media> res = new HashMap<>();
        for (Media m : media.values()) {
            res.put(m.getNome(), m);
        }
        return res;
    }

}