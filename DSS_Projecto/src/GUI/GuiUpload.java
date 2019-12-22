package GUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GuiUpload extends JFrame {
    JTextField name, artista, categoria, duracao;
    JButton b1,bBack;
    Controller ctrl;

    GuiUpload(Controller controller) {
        ctrl = controller;
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                ctrl.dispose();
            }
        });
        this.setLayout(null);

        JLabel n = new JLabel("Name:");
        JLabel a = new JLabel("Artista:");
        JLabel c = new JLabel("Categoria:");
        JLabel d = new JLabel("Duracao:");

        name = new JTextField(20);
        artista = new JTextField(20);
        categoria = new JTextField(20);
        duracao = new JTextField(20);
        b1=new JButton("Adicionar media");
        bBack = new JButton("Back");

        b1.addActionListener(e -> ctrl.addMedia(name.getText(), artista.getText(), categoria.getText(), Integer.parseInt(duracao.getText())));
        bBack.addActionListener(e -> ctrl.showMainMenu());


        n.setBounds(90,90,90,60);
        a.setBounds(90,110,90,60);
        c.setBounds(90,130,90,60);
        d.setBounds(90,150,90,60);
        name.setBounds(220,110,90,20);
        artista.setBounds(220,130,90,20);
        categoria.setBounds(220,150,90,20);
        duracao.setBounds(220,170,90,20);
        b1.setBounds(90,220,220,40);
        bBack.setBounds(5,5,70,20);

        this.setSize(400,400);
        this.add(n);
        this.add(a);
        this.add(c);
        this.add(d);
        this.add(name);
        this.add(artista);
        this.add(categoria);
        this.add(duracao);
        this.add(b1);
        this.add(bBack);

        this.setTitle("Carregar Conteudo");

    }

}