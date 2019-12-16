package GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GuiMainMenu extends JFrame {
    private JButton bAlterarCategoria, bUpload, bPlayMedia, bLogout;
    private Controller ctrl;

    public GuiMainMenu(Controller controller) {
        ctrl = controller;
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                ctrl.dispose();
            }
        });
        setLayout(new FlowLayout());
        this.setLayout(null);
        this.setSize(400,400);
        this.setTitle("Main Menu");

        //Labels
        bAlterarCategoria = new JButton("Alterar Categoria");
        bUpload = new JButton("Carregar Conteudo");
        bPlayMedia = new JButton("Reproduzir Conteudo");
        bLogout = new JButton("Terminar Sessão");

        //Adicionar funcionalidade aos botões
        bAlterarCategoria.addActionListener(e -> ctrl.showAlterarCategoria());
        bUpload.addActionListener(e -> ctrl.showUploadConteudo());
        bPlayMedia.addActionListener(e -> ctrl.showReproduzirConteudo());
        bLogout.addActionListener(e -> ctrl.logout());

        //Posicionar Botões
        bAlterarCategoria.setBounds(50,30,300,40);
        bUpload.setBounds(50,90,300,40);
        bPlayMedia.setBounds(50,150,300,40);
        bLogout.setBounds(50,210,300,40);

        //Adicionar Botões
        this.add(bAlterarCategoria);
        this.add(bUpload);
        this.add(bPlayMedia);
        this.add(bLogout);
    }
}