package GUI;

import GUI.Controller;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GuiLogin extends JFrame {
    JTextField name;
    JPasswordField pass;
    JButton b1,b2;
    Controller ctrl;

    GuiLogin(Controller controller) {
        ctrl = controller;
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                ctrl.dispose();
            }
        });
        this.setLayout(null);
        JLabel n=new JLabel("Name:",JLabel.CENTER);
        JLabel p=new JLabel("password:",JLabel.CENTER);
        name=new JTextField(20);
        pass=new JPasswordField(20);
        pass.setEchoChar('#');
        b1=new JButton("submit");
        b1.addActionListener(e -> {
            if(ctrl.login(name.getText(), String.valueOf(pass.getPassword()))){
                ctrl.showMainMenu();
            }
        });

        n.setBounds(70,90,90,60);
        p.setBounds(70,130,90,60);
        name.setBounds(200,100,90,20);
        pass.setBounds(200,140,90,20);
        b1.setBounds(100,260,70,40);

        this.setSize(400,400);
        this.add(n);
        this.add(name);
        this.add(p);
        this.add(pass);
        this.add(b1);

        this.setTitle("Login");

    }
}