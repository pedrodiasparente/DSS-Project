package GUI;

import MediaCenter.MediaCenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        frames.set(UPLOAD_CONTEUDO, new Gui(this));
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

    public void showPlayer(String musica){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuiFX fx = (GuiFX) frames.get(PLAYER);
                fx.setMusica(musica);
                fx.createScene();
                fx.setVisible(true);
                //browser.loadURL("http://oracle.com");
            }
        });
    }

    public void login(String email, String password){
        mediaCenter.login(email);
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
        for(JFrame f : frames){
            f.dispose();
        }
    }

    public void reproduzirConteudo(String[] media) {

    }
}
