package duplamente;

public class NoEstado {
    private String nome;
    private NoEstado prox;
    private NoEstado ant;
    private ListaCidades listaCidades;
    
    public NoEstado(){}
    
    public NoEstado(String nome, ListaCidades lc, NoEstado ant, NoEstado Prox)
    {
        this.nome=nome;
        this.prox=prox;
        this.ant=ant;
        this.listaCidades=lc;
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

    public NoEstado getAnt() {
        return ant;
    }

    public void setAnt(NoEstado ant) {
        this.ant = ant;
    }

    public ListaCidades getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(ListaCidades listaCidades) {
        this.listaCidades = listaCidades;
    }
    
    
}
