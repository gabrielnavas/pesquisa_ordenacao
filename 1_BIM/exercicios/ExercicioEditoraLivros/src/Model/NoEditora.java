package Model;

public class NoEditora {
    
    private String editora;
    private ListaLivros livros;
    private NoEditora prox;
    
    public NoEditora(String editora, ListaLivros livros, NoEditora prox) 
    {
        this.editora = editora;
        this.livros = livros;
        this.prox = prox;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public ListaLivros getLivros() {
        return livros;
    }

    public void setLivros(ListaLivros livros) {
        this.livros = livros;
    }

    public NoEditora getProx() {
        return prox;
    }

    public void setProx(NoEditora prox) {
        this.prox = prox;
    }
    
    
}
