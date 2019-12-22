package JDBC;
import MediaCenter.*;

import MediaCenter.Utilizador;

public class Teste {
    UtilizadorDAO usrDAO = UtilizadorDAO.getInstance();

    public void addUtilizadores() {
        Utilizador userAdmin = new Utilizador("admin","admin","admin");
        Utilizador userPedro = new Utilizador("pedro", "kazziofan1@uminho.pt", "berto");
        Utilizador userNuno = new Utilizador("klezio","djklezio@uminho.pt","agentosecreto");
        Utilizador userAnt = new Utilizador("Joaquim SILVA SILVA", "joaquim54@uminho.pt","numroncajavali");
        usrDAO.put(userAdmin.getEmail(),userAdmin);
        usrDAO.put(userPedro.getEmail(), userPedro);
        usrDAO.put(userNuno.getEmail(), userNuno);
        usrDAO.put(userAnt.getEmail(), userAnt);
        //Media media = new Media(6, "Jazzzz", "Darren", "heydarren.mp4");
        //userAdmin.getBiblioteca().getMedia().put(media.getNome(), media);
    }
}
