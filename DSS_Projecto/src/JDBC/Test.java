package JDBC;

import MediaCenter.Utilizador;

import java.sql.*;

public class Test {

    public Test() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            //Classe nao disponivel
        }

        /*public Utilizador get(Object key){
            try (Connection con = DriverManager.getConnection("jdbc:mysql ://localhost/ DBmodel?user = un&password = pwd ") {

            }
            catch (SQLException e) {
                // Erro ao establecer ligaçao
            }

        }*/
    }
}
