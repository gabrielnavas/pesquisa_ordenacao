package Model;

public class NoLivro {
    private String titulo;
    private int ano;
    private ListaAutores autores;
    private NoLivro prox;

    public NoLivro(String titulo, int ano, ListaAutores autores, NoLivro prox) {
        this.titulo = titulo;
        this.ano = ano;
        this.autores = autores;
        this.prox = prox;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public ListaAutores getAutores() {
        return autores;
    }

    public void setAutores(ListaAutores autores) {
        this.autores = autores;
    }

    public NoLivro getProx() {
        return prox;
    }

    public void setProx(NoLivro prox) {
        this.prox = prox;
    }
    
    
}
