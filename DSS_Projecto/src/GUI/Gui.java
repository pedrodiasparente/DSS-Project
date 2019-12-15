package GUI;

import GUI.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
class Gui extends Frame {
    TextField name,pass;
    Button b1,b2;
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
        name=new TextField(20);
        pass=new TextField(20);
        pass.setEchoChar('#');
        b1=new Button("submit");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(b1.getLabel().equals("submit"))
                    ctrl.showLogin();
                else
                    b1.setLabel("submit");
            }
        });
        b2=new Button("cancel");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(b2.getLabel().equals("cancel"))
                    b2.setLabel("click me again");
                else
                    b2.setLabel("cancel");
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
        controller.showAlterarCategoria();;
    }
}