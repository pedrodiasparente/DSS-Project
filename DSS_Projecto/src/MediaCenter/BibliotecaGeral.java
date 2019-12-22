package MediaCenter;

import JDBC.MediaDAO;
import JDBC.MediaUtilizadorDAO;

import java.util.HashMap;

public class BibliotecaGeral {

    private MediaDAO media;
    private HashMap<String, Playlist> playlists;

    public BibliotecaGeral() {
        media = MediaDAO.getInstance();
    }

    public HashMap<String, Media> getMedia() {
        HashMap<String, Media> res = new HashMap<>();
        for (Media m : media.values()) {
            res.put(m.getNome(), m);
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