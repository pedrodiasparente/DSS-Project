package GUI;



import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

class GuiPlayMedia extends JFrame {
    private JButton bAddMedia2, bAddMedia1, bPlayMedia;
    private JList<String> conteudoCurrentUser;
    private JList<String> conteudoGlobal;
    private JList<String> conteudoToPlay;
    private Controller ctrl;

    public GuiPlayMedia(Controller controller) {
        ctrl = controller;
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                ctrl.dispose();
            }
        });
        this.setLayout(null);
        this.setSize(400,400);
        this.setTitle("Reproduzir Conteudo");
        DefaultListModel<String> toPlay = new DefaultListModel<>();

        //Labels
        conteudoCurrentUser = new JList<>(ctrl.getCurrentUserMedia());
        conteudoGlobal = new JList<>(ctrl.getMediaGlobal());
        conteudoToPlay = new JList<>(toPlay);
        bPlayMedia = new JButton("Play");
        bAddMedia1 = new JButton("Add");
        bAddMedia2 = new JButton("Add");

        //Adicionar funcionalidade aos botões
        bAddMedia1.addActionListener(e -> toPlay.addElement(conteudoGlobal.getSelectedValue()));
        bAddMedia2.addActionListener(e -> toPlay.addElement(conteudoCurrentUser.getSelectedValue()));
        bPlayMedia.addActionListener(e -> {
            ArrayList<String> musicas = new ArrayList<>();
            for(int i = 0; i < toPlay.size(); i++){
                musicas.add(toPlay.get(i));
            }
            ctrl.showPlayer(musicas.toArray(new String[0])[0]);
        });

        //Posicionar Botões
        bPlayMedia.setBounds(290,300,90,40);
        bAddMedia1.setBounds(20,300,90,40);
        bAddMedia2.setBounds(120,300,90,40);

        JScrollPane scrollGlobal = new JScrollPane();
        scrollGlobal.setViewportView(conteudoGlobal);
        scrollGlobal.setBounds(20,30,90,250);

        JScrollPane scrollCurrentUser = new JScrollPane();
        scrollCurrentUser.setViewportView(conteudoCurrentUser);
        scrollCurrentUser.setBounds(120,30,90,250);

        JScrollPane scrollToPlay = new JScrollPane();
        scrollToPlay.setViewportView(conteudoToPlay);
        scrollToPlay.setBounds(290,30,90,250);

        //Adicionar à janela
        this.add(scrollCurrentUser);
        this.add(scrollGlobal);
        this.add(scrollToPlay);
        this.add(bAddMedia1);
        this.add(bAddMedia2);
        this.add(bPlayMedia);
    }
}