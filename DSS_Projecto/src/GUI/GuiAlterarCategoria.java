package GUI;



import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

class GuiAlterarCategoria extends JFrame {
    private JButton bPlayMedia, bBack;
    private JList<String> conteudoCurrentUser;
    private DefaultListModel<String> currentUserMedia;
    private  JTextField categoria;
    private Controller ctrl;

    public GuiAlterarCategoria(Controller controller) {
        ctrl = controller;
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                ctrl.dispose();
            }
        });
        this.setLayout(null);
        this.setSize(400,400);
        this.setTitle("Alterar Categoria");

        //Labels
        currentUserMedia = new DefaultListModel<>();
        bPlayMedia = new JButton("Play");
        bBack = new JButton("Back");
        categoria = new JTextField(50);
        conteudoCurrentUser = new JList<>(currentUserMedia);

        //Adicionar funcionalidade aos botões
        bPlayMedia.addActionListener(e -> {
            ctrl.alteraCategoria(conteudoCurrentUser.getSelectedValue(), categoria.getText());
        });

        bBack.addActionListener(e -> ctrl.showMainMenu());

        //Posicionar Botões
        bPlayMedia.setBounds(290,135,90,40);
        bBack.setBounds(5,5,70,20);
        categoria.setBounds(130, 145, 140, 20);

        JScrollPane scrollCurrentUser = new JScrollPane();
        scrollCurrentUser.setViewportView(conteudoCurrentUser);
        scrollCurrentUser.setBounds(20,30,90,250);

        //Adicionar à janela
        this.add(scrollCurrentUser);
        this.add(bPlayMedia);
        this.add(categoria);
        this.add(bBack);
    }

    @Override
    public void setVisible(boolean bool){
        super.setVisible(bool);
        if (bool) {
            currentUserMedia.clear();
            for(String media : ctrl.getCurrentUserMedia()){
                currentUserMedia.add(currentUserMedia.size(), media);
            }
        }
    }
}