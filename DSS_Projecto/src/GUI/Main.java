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
class Main {
    public static void main(String args[])
    {
        Controller controller = new Controller();
        controller.showLogin();
    }
}