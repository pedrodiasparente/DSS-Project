package MediaCenter;


import JDBC.UtilizadorDAO;

import java.util.HashMap;

public class MediaCenter {

    private UtilizadorDAO utilizadores;
    private Biblioteca bibliotecaGeral;
    private Utilizador admin;
    private Utilizador currentUser;

    public MediaCenter(){
        utilizadores = UtilizadorDAO.getInstance();
        bibliotecaGeral = new Biblioteca();
        admin = new Utilizador("admin", "admin", "admin");
        currentUser = null;
    }

    public int login(String email) {
        if (validaUtilizador(email) != null) {
            this.currentUser = validaUtilizador(email);
            return 1;
        }
        System.out.println("No existo");
        return 0;
    }

    public Utilizador validaUtilizador(String email) {
        for (Utilizador u : this.utilizadores.values()) {
            String m = u.getEmail();
            if (m == email) {
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
        String [] musica = {"PotionSeller.mp4", "heydarren.mp4"};
        //currentUser.getBiblioteca().getUserMedia(currentUser);
        return musica;
    }

    public String[] getMediaGlobal(){
        String [] musica = {"PotionSeller.mp4", "heydarren.mp4","0-100 nigga real quick"};
        /*String [] musicaReal = {};
        for(Media m : this.bibliotecaGeral.getMedia().values()){
            musicaReal[musica.length] = m.getNome();
        }*/
        return musica;
    }

    public void addConteudoGeral (Media m){
        this.bibliotecaGeral.getMedia().put(m.getNome(), m);
    }

    public void addConteudoPessoal (Media m){
        this.currentUser.getBiblioteca().getMedia().put(m.getNome(), m);
    }

    public int carregarConteudo(HashMap<String, Media> conteudo) {
        int res = 0;
        for (Media m : conteudo.values()) {
            if (!(validaConteudoGeral(m)) && !(validaConteudoPessoal(m))) {
                addConteudoGeral(m);
                addConteudoPessoal(m);
                res = 1;
            }
            if(validaConteudoGeral(m) && !(validaConteudoPessoal(m))){
                addConteudoPessoal(m);
                res = 2;
            }
            if(validaConteudoPessoal(m) && validaConteudoGeral(m)) {
                res = 0;
            }
        }
        return res;
    }
}