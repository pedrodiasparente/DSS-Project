package MediaCenter;

public class Utilizador {

    private Biblioteca biblioteca;
    private String nome;
    private String email;
    private String password;

    public Utilizador(String nome, String email, String password){
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    public String getUtilizadorMail(){
        return this.email;
    }

    public Biblioteca getBiblioteca(){
        return this.biblioteca;
    }
}