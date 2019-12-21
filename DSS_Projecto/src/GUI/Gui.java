package GUI;

import GUI.Controller;
import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
class Gui extends JFrame {
    JTextField name;
    JPasswordField pass;
    JButton b1,b2;
    Controller ctrl;

    Gui(Controller controller) {
        ctrl = controller;
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setLayout(new FlowLayout());
        this.setLayout(null);
        Label n=new Label("Name:",Label.CENTER);
        Label p=new Label("password:",Label.CENTER);
        name=new JTextField(20);
        pass=new JPasswordField(20);
        pass.setEchoChar('#');
        b1=new JButton("submit");
        b1.addActionListener(e -> {
            if(b1.getText().equals("submit"))
                ctrl.showLogin();
            else
                b1.setText("submit");
        });
        b2=new JButton("cancel");
        b2.addActionListener(e -> {
            DefaultListModel<String> toPlay = new DefaultListModel<>();
            String[] musicas = {};
            for(int i = 0; i < toPlay.size(); i++){
                musicas[i] = (toPlay.get(i));
            }
        });
        this.setSize(400,400);
        this.add(n);
        this.add(name);
        this.add(p);
        this.add(pass);
        this.add(b1);
        this.add(b2);
        n.setBounds(70,90,90,60);
        p.setBounds(70,130,90,60);
        name.setBounds(200,100,90,20);
        pass.setBounds(200,140,90,20);
        b1.setBounds(100,260,70,40);
        b2.setBounds(180,260,70,40);
        this.setTitle("A");

    }
    public static void main(String args[])
    {

        Controller controller = new Controller();
        controller.showMainMenu();
    }
}