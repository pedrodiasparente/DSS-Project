package GUI;



import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

class GuiPlayMedia extends JFrame {
    private JButton bAddMedia2, bAddMedia1, bPlayMedia, bCleanPlay, bBack;
    private JList<String> conteudoCurrentUser;
    private JList<String> conteudoGlobal;
    private JList<String> conteudoToPlay;
    DefaultListModel<String> toPlay;
    DefaultListModel<String> currentUserMedia;
    DefaultListModel<String> globalMedia;

    JScrollPane scrollToPlay;
    JScrollPane scrollGlobal;
    JScrollPane scrollCurrentUser;

    private Controller ctrl;

    public GuiPlayMedia(Controller controller) {
        ctrl = controller;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ctrl.dispose();
            }
        });
        this.setLayout(null);
        this.setSize(400, 400);
        this.setTitle("Reproduzir Conteudo");
        toPlay = new DefaultListModel<>();
        globalMedia = new DefaultListModel<>();
        currentUserMedia = new DefaultListModel<>();

        for(String media : ctrl.getMediaGlobal()){
            globalMedia.add(globalMedia.size(), media);
        }

        for(String media : ctrl.getCurrentUserMedia()){
            currentUserMedia.add(currentUserMedia.size(), media);
        }

        //Labels
        conteudoToPlay = new JList<>(toPlay);
        conteudoCurrentUser = new JList<>(currentUserMedia);
        conteudoGlobal = new JList<>(globalMedia);
        bPlayMedia = new JButton("Play");
        bAddMedia1 = new JButton("Add");
        bAddMedia2 = new JButton("Add");
        bCleanPlay = new JButton("Clean");
        bBack = new JButton("Back");

        //Adicionar funcionalidade aos botões
        bAddMedia1.addActionListener(e -> toPlay.addElement(conteudoGlobal.getSelectedValue()));
        bAddMedia2.addActionListener(e -> toPlay.addElement(conteudoCurrentUser.getSelectedValue()));
        bPlayMedia.addActionListener(e -> {
            ArrayList<String> musicas = new ArrayList<>();
            for (int i = 0; i < toPlay.size(); i++) {
                musicas.add(toPlay.get(i));
            }
            ctrl.setPlayer(musicas.toArray(new String[0]));
            if(musicas.size() > 0) {
                ctrl.showPlayer();
            }
            ctrl.reproduzirConteudo();
        });
        bCleanPlay.addActionListener(e -> {
            ArrayList<String> musicas = new ArrayList<>();
            for (int i = toPlay.size()-1; i >= 0; i--) {
                toPlay.remove(i);
            }
        });
        bBack.addActionListener(e -> ctrl.showMainMenu());

        //Posicionar Botões
        bPlayMedia.setBounds(290, 240, 90, 40);
        bAddMedia1.setBounds(20, 300, 90, 40);
        bAddMedia2.setBounds(120, 300, 90, 40);
        bCleanPlay.setBounds(290, 300, 90, 40);
        bBack.setBounds(5,5,70,20);


        scrollToPlay = new JScrollPane();
        scrollToPlay.setViewportView(conteudoToPlay);
        scrollToPlay.setBounds(290, 30, 90, 190);

        scrollGlobal = new JScrollPane();
        scrollGlobal.setViewportView(conteudoGlobal);
        scrollGlobal.setBounds(20, 30, 90, 250);

        scrollCurrentUser = new JScrollPane();
        scrollCurrentUser.setViewportView(conteudoCurrentUser);
        scrollCurrentUser.setBounds(120, 30, 90, 250);

        //Adciionar ajanela
        this.add(scrollCurrentUser);
        this.add(scrollGlobal);
        this.add(scrollToPlay);
        this.add(bAddMedia1);
        this.add(bAddMedia2);
        this.add(bPlayMedia);
        this.add(bCleanPlay);
        this.add(bBack);
    }

    @Override
    public void setVisible(boolean bool) {
        super.setVisible(bool);
        if (bool) {
            globalMedia.clear();
            currentUserMedia.clear();
            for(String media : ctrl.getMediaGlobal()){
                globalMedia.add(globalMedia.size(), media);
            }

            for(String media : ctrl.getCurrentUserMedia()){
                currentUserMedia.add(currentUserMedia.size(), media);
            }
        }
    }
}