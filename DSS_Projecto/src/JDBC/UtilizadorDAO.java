package JDBC;

import MediaCenter.Utilizador;

import java.sql.*;

/**
 * Exemplo de um DAO (para o acesso aos dados de Aluno).
 * Como forma de minimizar o impacto de alteração dos Diag de Sequência, o DAO assume
 * a API da estrutura de dados que substitui - neste caso vai substituir um Map de Aluno.
 * O DAO utiliza o padrão Singleton.
 *
 * DISCLAIMER: Este código foi criado para discussão e edição durante as aulas práticas
 * de DSS, representando uma solução em construção. Como tal, não deverá ser visto como
 * uma solução canónica, ou mesmo acabada. É disponibilizado para auxiliar o processo de
 * estudo. Os alunos são encorajados a testar adequadamente o código fornecido e a procurar
 * soluções alternativas, à medida que forem adquirindo mais conhecimentos. Por exemplo,
 * protegendo o DAO de ataques por SQL injection.
 *
 * @author jfc
 * @version 20191202
 */

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.sql.*;

public class UtilizadorDAO implements Map<String,Utilizador> {

    private static UtilizadorDAO inst = null;

      private UtilizadorDAO () {
        try {
           Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilizadorDAO getInstance() {
        if (inst == null) {
            inst = new UtilizadorDAO();
        }
        return inst;
    }

    public void clear () {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM TAlunos");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Statement stm = conn.createStatement();
            String sql = "SELECT nome FROM TAlunos WHERE numero='"+(String)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("public boolean containsValue(Object value) not implemented!");
    }

    public Set<Map.Entry<String,Utilizador>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Aluno>> entrySet() not implemented!");
    }

    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }

    public Utilizador get(Object key) {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Utilizador al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM TAlunos WHERE numero='"+(String)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                al = new Utilizador(rs.getString(2),rs.getString(1),rs.getString(3));
            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public int hashCode() {
        return this.inst.hashCode();
    }

    public boolean isEmpty() {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM TAlunos");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<String> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    /* Exercício: Alterar para utilizar transacções! */
    public Utilizador put(String key, Utilizador value) {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Utilizador al = null;
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM TAlunos WHERE numero='"+key+"'");
            String sql = "INSERT INTO TAlunos VALUES ('"+ "jhonny" +"','"+key+"',";
            sql += 20 +","+ 10 +")";
            int i  = stm.executeUpdate(sql);
            return new Utilizador(value.getNome(),value.getEmail(),value.getPassword());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public void putAll(Map<? extends String,? extends Utilizador> t) {
        throw new NullPointerException("Not implemented!");
    }

    public Utilizador remove(Object key) {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Utilizador al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE '"+key+"' FROM TAlunos";
            int i  = stm.executeUpdate(sql);
            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public int size() {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM TAlunos");
            for (;rs.next();i++);
            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Collection<Utilizador> values() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/MediaCenter?user=root&password=password")) {
            Collection<Utilizador> col = new HashSet<Utilizador>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Utilizador");
            for (;rs.next();) {
                col.add(new Utilizador(rs.getString(1),rs.getString(2),rs.getString(3)));
            }

            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

}