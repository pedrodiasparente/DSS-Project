package GUI;

import JDBC.Teste;
import MediaCenter.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private MediaCenter mediaCenter;
    private ArrayList<JFrame> frames;
    private static final int LOGIN = 0;
    private static final int MAIN_MENU = 1;
    private static final int REPRODUZIR_CONTEUDO = 2;
    private static final int UPLOAD_CONTEUDO = 3;
    private static final int ALTERAR_CATEGORIA = 4;
    private static final int PLAYER = 5;

    public Controller(){
        mediaCenter = new MediaCenter();
        frames = new ArrayList<>(6);
        for(int i = 0; i < 6; i++){
            frames.add(null);
        }
        frames.set(LOGIN, new GuiLogin(this));
        frames.set(MAIN_MENU, new GuiMainMenu(this));
        frames.set(REPRODUZIR_CONTEUDO, new GuiPlayMedia(this));
        frames.set(UPLOAD_CONTEUDO, new GuiUpload(this));
        frames.set(ALTERAR_CATEGORIA, new GuiAlterarCategoria(this));
        frames.set(PLAYER, new GuiFX(this));
    }

    public void showLogin(){
        for(JFrame f : frames){
            f.setVisible(false);
        }
        frames.get(LOGIN).setVisible(true);
    }

    public void showMainMenu(){
        for(JFrame f : frames){
            f.setVisible(false);
        }
        frames.get(MAIN_MENU).setVisible(true);
    }

    public void showReproduzirConteudo(){
        for(JFrame f : frames){
            f.setVisible(false);
        }
        frames.get(REPRODUZIR_CONTEUDO).setVisible(true);
    }

    public void showUploadConteudo(){
        for(JFrame f : frames){
            f.setVisible(false);
        }
        frames.get(UPLOAD_CONTEUDO).setVisible(true);
    }

    public void showAlterarCategoria(){
        for(JFrame f : frames){
            f.setVisible(false);
        }
        frames.get(ALTERAR_CATEGORIA).setVisible(true);
    }

    public void showPlayer(){
        for(JFrame f : frames){
            f.setVisible(false);
        }
        frames.get(PLAYER).setVisible(true);
    }

    public boolean login(String email, String password){
        return mediaCenter.login(email,password);
    }

    public void logout(){
        mediaCenter.terminaSessao();
        this.showLogin();
    }

    public String[] getCurrentUserMedia(){
        return mediaCenter.getCurrentUserMedia();
    }

    public String[] getMediaGlobal(){
        return mediaCenter.getMediaGlobal();
    }


    public void dispose(){
        GuiFX fx = (GuiFX) frames.get(PLAYER);
        fx.exit();
        for(JFrame f : frames){
            f.dispose();
        }
    }

    public void setPlayer(String[] musicas) {
        mediaCenter.setPlayer(musicas);
    }

    public boolean reproduzirConteudo() {
        String nextMedia = mediaCenter.nextMedia();
        if(nextMedia != null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GuiFX fx = (GuiFX) frames.get(PLAYER);
                    fx.setMusica(nextMedia);
                    fx.createScene();
                }
            });
            return true;
        }
        return false;
    }

    public void addUtilizadores(){
        Teste teste = new Teste();
        teste.addUtilizadores();
    }

    public void addMedia(String name, String artista, String categoria, int duracao) {
        mediaCenter.addMedia(name,artista,categoria,duracao);
    }

    public void alteraCategoria(String media, String newCategoria) {
        mediaCenter.alteraCategoria(media, newCategoria);
    }

    public HashMap<String, Media> getMediaFull() {
        return mediaCenter.getBibliotecaGeral().getMedia();
    }
}
