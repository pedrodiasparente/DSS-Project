package GUI;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GuiMainMenu extends Frame {
    private Button bAlterarCategoria, bUpload, bPlayMedia, bLogout;
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
        bAlterarCategoria = new Button("Alterar Categoria");
        bUpload = new Button("Carregar Conteudo");
        bPlayMedia = new Button("Reproduzir Conteudo");
        bLogout = new Button("Terminar Sessão");

        //Adicionar funcionalidade aos botões
        bAlterarCategoria.addActionListener(e -> ctrl.showAlterarCategoria());
        bUpload.addActionListener(e -> ctrl.showUploadConteudo());
        bPlayMedia.addActionListener(e -> ctrl.showReproduzirConteudo());
        bLogout.addActionListener(e -> ctrl.logout());

        //Posicionar Botões
        bAlterarCategoria.setBounds(20,30,120,40);
        bUpload.setBounds(20,90,120,40);
        bPlayMedia.setBounds(20,150,120,40);
        bLogout.setBounds(20,210,120,40);

        //Adicionar Botões
        this.add(bAlterarCategoria);
        this.add(bUpload);
        this.add(bPlayMedia);
        this.add(bLogout);
    }
    public static void main(String[] args)
    {

    }
}