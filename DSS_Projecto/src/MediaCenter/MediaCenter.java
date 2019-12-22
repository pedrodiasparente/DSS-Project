package MediaCenter;


import JDBC.UtilizadorDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MediaCenter {

    private UtilizadorDAO utilizadores;
    private BibliotecaGeral bibliotecaGeral;
    private Utilizador admin;
    private Utilizador currentUser;
    private ArrayList<String> toPlay;

    public MediaCenter(){
        utilizadores = UtilizadorDAO.getInstance();
        bibliotecaGeral = new BibliotecaGeral();
        admin = new Utilizador("admin", "admin", "admin");
        currentUser = null;
    }

    public boolean login(String email, String password) {
        if (validaUtilizador(email) != null) {
            Utilizador user = validaUtilizador(email);
            if (user.getPassword().equals(password)){
                currentUser = user;
            return true;
            }
            System.out.println("Wrong password");
            return false;
        }
        System.out.println("No existo");
        return false;
    }

    public Utilizador validaUtilizador(String email) {
        for (Utilizador u : this.utilizadores.values()) {
            String m = u.getEmail();

            if (m.equals(email)) {
                return u;
            }
        }
        return null;
    }

    public void terminaSessao() {
        this.currentUser = null;
    }

    public boolean validaDados(String email) {
        boolean v = false;
        for (Utilizador u : this.utilizadores.values()) {
            String m = u.getEmail();
            if (m == email) {
                v = true;
                break;
            }
        }
        return v;
    }

    public void criaConta(String nome, String email, String password) {
        if (!(validaDados(email))) {
            Utilizador user = new Utilizador(nome, email, password);
            this.utilizadores.put(nome, user);
        }
    }

    public int alteraCategoria(String nome, String categoria) {
        int res = 0;
		/*
		for (MediaCenter.Media m : this.bibliotecaGeral.getMedia().values()) {
			if (nome == m.getMediaNome()) {
				m.setMediaCategoria(categoria);
				res = 1;
			}
		}

		 */
        for (Media m : currentUser.getBiblioteca().getMedia().values()) {
            if (m.getNome() == nome) {
                m.setMediaCategoria(categoria);
                res = 1;
            }
        }
        return res;
    }


    public boolean validaConteudoGeral(Media m) {
        for (Media med : this.bibliotecaGeral.getMedia().values()) {
            if (med.equals(m)) {
                return true;
            }
        }
        return false;
    }

    public boolean validaConteudoPessoal(Media m) {
        for (Media med : this.currentUser.getBiblioteca().getMedia().values()) {
            if (med.equals(m)) {
                return true;
            }
        }
        return false;
    }

    public String[] getCurrentUserMedia(){
        if(currentUser == null)
            return new String[0];
        ArrayList<String> musicaReal = new ArrayList<>();
        for(Media m : this.currentUser.getBiblioteca().getMedia().values()){
            musicaReal.add(m.getNome());
        }
        return musicaReal.toArray(new String[0]);
    }

    public String[] getMediaGlobal(){
        ArrayList<String> musicaReal = new ArrayList<>();
        for(Media m : this.bibliotecaGeral.getMedia().values()){
            musicaReal.add(m.getNome());
        }
        return musicaReal.toArray(new String[0]);
    }

    public void addConteudo (Media m){
        this.currentUser.getBiblioteca().addMedia(m);
    }

    public int carregarConteudo(HashMap<String, Media> conteudo) {
        int res = 0;
        for (Media m : conteudo.values()) {
            if (!(validaConteudoGeral(m)) && !(validaConteudoPessoal(m))) {
                addConteudo(m);
                res = 1;
            }
            if(validaConteudoGeral(m) && !(validaConteudoPessoal(m))){
                addConteudo(m);
                res = 2;
            }
            if(validaConteudoPessoal(m) && validaConteudoGeral(m)) {
                res = 0;
            }
        }
        return res;
    }

    //play media operations
    public void setPlayer(String[] musicas){
        toPlay = new ArrayList<String>();
        Collections.addAll(toPlay, musicas);
    }

    public String nextMedia(){
        String nextMedia;
        if(toPlay.size() > 0){
            nextMedia = toPlay.get(0);
            toPlay.remove(0);
            return nextMedia;
        }
        return null;
    }

    public void addMedia(String name, String artista, String categoria, int duracao) {
        Media m = new Media(duracao,categoria,artista,name);
        this.addConteudo(m);
    }
}