package duplamente;

public class NoCidade {
 
    private String nome;
    private NoCidade prox;
    private NoCidade ant;
    
    public NoCidade(){}
    
    public NoCidade(String nome, NoCidade ant, NoCidade prox)
    {
        this.nome=nome;
        this.ant=ant;
        this.prox=prox;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NoCidade getProx() {
        return prox;
    }

    public void setProx(NoCidade prox) {
        this.prox = prox;
    }

    public NoCidade getAnt() {
        return ant;
    }

    public void setAnt(NoCidade ant) {
        this.ant = ant;
    }
}
