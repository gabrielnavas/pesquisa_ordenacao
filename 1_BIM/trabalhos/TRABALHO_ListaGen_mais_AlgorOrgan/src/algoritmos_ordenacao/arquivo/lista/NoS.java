package algoritmos_ordenacao.arquivo.lista;

public class NoS {
    
    private int info;
    private NoS prox;

    public NoS() {
    }
    
    public NoS(int info, NoS prox) {
        this.info = info;
        this.prox = prox;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NoS getProx() {
        return prox;
    }

    public void setProx(NoS prox) {
        this.prox = prox;
    }
}
