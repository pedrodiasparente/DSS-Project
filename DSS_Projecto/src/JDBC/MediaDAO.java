package JDBC;

import MediaCenter.Media;
import MediaCenter.Utilizador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Exemplo de um DAO (para o acesso aos dados de Aluno).
 * Como forma de minimizar o impacto de alteração dos Diag de Sequência, o DAO assume
 * a API da estrutura de dados que substitui - neste caso vai substituir um Map de Aluno.
 * O DAO utiliza o padrão Singleton.
 * <p>
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

public class MediaDAO implements Map<String,Media> {

    private static MediaDAO inst = null;

    private MediaDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static MediaDAO getInstance() {
        if (inst == null) {
            inst = new MediaDAO();
        }
        return inst;
    }

    public void clear() {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM TAlunos");
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Statement stm = conn.createStatement();
            String sql = "SELECT nome FROM TAlunos WHERE numero='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("public boolean containsValue(Object value) not implemented!");
    }

    public Set<Entry<String, Media>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Aluno>> entrySet() not implemented!");
    }

    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }

    public Media get(Object key) {
        try (Connection conn = DriverManager.getConnection("\"jdbc:mysql://localhost/MediaCenter?user=root&password=Broculos.23\"")) {
            Media media = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Media WHERE nome='" + (String) key + "';";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                media = new Media(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(1));
            return media;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public int hashCode() {
        return this.inst.hashCode();
    }

    public boolean isEmpty() {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM TAlunos");
            return !rs.next();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Set<String> keySet() {
        throw new NullPointerException("Not implemented!");
    }


    /* Exercício: Alterar para utilizar transacções! */
    //   PUT NO MEDIA JÁ ESTÁ NO MEDIA UTILIZADOR DAO
    public Media put(String key, Media value) {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            /*Statement stm = conn.createStatement();
            stm.executeUpdate("INSERT INTO Media (nome, duracao, categoriaDefault, artista) VALUES (" + value.getNome() + ", " + value.getDuracao() + ", " + value.getCategoria() +", " + value.getArtista() + ");");
            String sql = "INSERT INTO UtilizadorMedia (Utilizador_email, Media_nome, categoria) VALUES ("+ currentUser.getEmail() +","+ key +"," + value.getCategoria() + ");";
            stm.executeUpdate(sql);
            return new Media(value.getDuracao(),value.getCategoria(),value.getArtista(), value.getNome());*/
        }
        catch (Exception e) {/*throw new NullPointerException(e.getMessage());*/}
        return new Media(value.getDuracao(),value.getCategoria(),value.getArtista(), value.getNome());
    }

    public void putAll(Map<? extends String, ? extends Media> t) {
        throw new NullPointerException("Not implemented!");
    }

    public Media remove(Object key) {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            Media al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE '" + key + "' FROM TAlunos";
            int i = stm.executeUpdate(sql);
            return al;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public int size() {
        try (Connection conn = DriverManager.getConnection("jdbc:odbc:alunos")) {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM TAlunos");
            for (; rs.next(); i++) ;
            return i;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Collection<Media> values() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/MediaCenter?user=root&password=Broculos.23")) {
            Collection<Media> col = new HashSet<Media>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM MEDIA");
            for (; rs.next(); ) {
                col.add(new Media(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(1)));
            }

            return col;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }
}
