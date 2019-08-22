package simplesmeste;

public class NoEstado 
{
    private String nome;
    private NoEstado prox;
    private ListaCidade listaCidades;
    
    public NoEstado() {}
    
    public NoEstado(String estado, String cidade, NoEstado prox)
    {
        this.nome = estado;
        this.prox = prox;
        
        this.listaCidades = new ListaCidade();
        this.listaCidades.inserirOrdenado(cidade);
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
