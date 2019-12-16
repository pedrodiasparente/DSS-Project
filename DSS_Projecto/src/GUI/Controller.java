package GUI;

import MediaCenter.MediaCenter;

import java.awt.*;
import java.util.ArrayList;

public class Controller {
    MediaCenter mediaCenter;
    private ArrayList<Frame> frames;
    private static final int LOGIN = 0;
    private static final int MAIN_MENU = 1;
    private static final int REPRODUZIR_CONTEUDO = 2;
    private static final int UPLOAD_CONTEUDO = 3;
    private static final int ALTERAR_CATEGORIA = 4;

    public Controller(){
        frames = new ArrayList<>(5);
        for(int i = 0; i < 5; i++){
            frames.add(null);
        }
        frames.set(LOGIN, new GuiLogin(this));
        frames.set(MAIN_MENU, new GuiMainMenu(this));
        frames.set(REPRODUZIR_CONTEUDO, new Gui(this));
        frames.set(UPLOAD_CONTEUDO, new Gui(this));
        frames.set(ALTERAR_CATEGORIA, new Gui(this));
        mediaCenter = new MediaCenter();
    }

    public void showLogin(){
        for(Frame f : frames){
            f.setVisible(false);
        }
        frames.get(LOGIN).setVisible(true);
    }

    public void showMainMenu(){
        for(Frame f : frames){
            f.setVisible(false);
        }
        System.out.println("showing menu");
        frames.get(MAIN_MENU).setVisible(true);
    }

    public void showReproduzirConteudo(){
        for(Frame f : frames){
            f.setVisible(false);
        }
        frames.get(REPRODUZIR_CONTEUDO).setVisible(true);
    }

    public void showUploadConteudo(){
        for(Frame f : frames){
            f.setVisible(false);
        }
        frames.get(UPLOAD_CONTEUDO).setVisible(true);
    }

    public void showAlterarCategoria(){
        for(Frame f : frames){
            f.setVisible(false);
        }
        frames.get(ALTERAR_CATEGORIA).setVisible(true);
    }

    public void login(String email, String password){
        mediaCenter.login(email);
    }

    public void logout(){
        mediaCenter.terminaSessao();
        this.showLogin();
    }

    public void dispose(){
        for(Frame f : frames){
            f.dispose();
        }
    }
}
