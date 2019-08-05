package Model;

class NoAutor {
    
    private String autor;
    private NoAutor prox;

    public NoAutor(String autor, NoAutor prox) {
        this.autor = autor;
        this.prox = prox;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public NoAutor getProx() {
        return prox;
    }

    public void setProx(NoAutor prox) {
        this.prox = prox;
    }
    
}
