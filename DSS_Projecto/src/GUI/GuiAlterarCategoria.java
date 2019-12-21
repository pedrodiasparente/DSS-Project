package GUI;



import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

class GuiAlterarCategoria extends JFrame {
    private JButton bPlayMedia;
    private JList<String> conteudoCurrentUser;
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
        conteudoCurrentUser = new JList<>(ctrl.getCurrentUserMedia());
        bPlayMedia = new JButton("Play");
        categoria = new JTextField(50);

        //Adicionar funcionalidade aos botões
        bPlayMedia.addActionListener(e -> {
            ctrl.addUtilizadores();
            /*ArrayList<String> musicas = new ArrayList<>();
            for(int i = 0; i < toPlay.size(); i++){
                musicas.add(toPlay.get(i));
            }
            ctrl.reproduzirConteudo(musicas.toArray(new String[0]));*/
        });

        //Posicionar Botões
        bPlayMedia.setBounds(290,135,90,40);

        categoria.setBounds(130, 145, 140, 20);

        JScrollPane scrollCurrentUser = new JScrollPane();
        scrollCurrentUser.setViewportView(conteudoCurrentUser);
        scrollCurrentUser.setBounds(20,30,90,250);

        //Adicionar à janela
        this.add(scrollCurrentUser);
        this.add(bPlayMedia);
        this.add(categoria);
    }
}