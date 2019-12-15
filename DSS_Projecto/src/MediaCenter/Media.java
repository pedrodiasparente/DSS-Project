package MediaCenter;

public class Media {

    private double duracao;
    private String categoria;
    private String artista;
    private String nome;

    public Double getDuracao(){
        return this.duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getArtista() {
        return artista;
    }

    public String getMediaNome(){
        return this.nome;
    }

    public void setMediaCategoria(String categoria){
        this.categoria =  categoria;
    }

    public boolean equals(Object media){
        if (this == media) return true;

        if((media == null) || this.getClass() != media.getClass()){
            return false;
        }

        Media m = (Media) media;

        return(this.duracao == m.getDuracao() && this.categoria.equals(m.getCategoria()) && this.artista.equals(m.getArtista()) && this.nome.equals(m.getMediaNome()));
    }
}