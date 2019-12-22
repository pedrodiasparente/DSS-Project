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
        this.biblioteca = new Biblioteca(this);
    }
    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public Biblioteca getBiblioteca(){
        return this.biblioteca;
    }
}