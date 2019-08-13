package main;

class NoEstado {
    private String nome;
    private NoEstado prox;
    private ListaCidade listaCidades;
    
    NoEstado(String nome, ListaCidade cidades, NoEstado prox)
    {
        this.nome = nome;
        this.listaCidades = cidades;
        this.prox = prox;
        this.listaCidades.inserirOrdenado(cidades);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NoEstado getProx() {
        return prox;
    }

    public void setProx(NoEstado prox) {
        this.prox = prox;
    }

    public ListaCidade getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(ListaCidade cidades) {
        this.listaCidades = cidades;
    }    
}
