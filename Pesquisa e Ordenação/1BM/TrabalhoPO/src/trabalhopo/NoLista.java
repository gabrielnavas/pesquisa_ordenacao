package trabalhopo;

public class NoLista {
    private int info;
    private NoLista prox;
    
    public NoLista() {
        this.info = 0;
        this.prox = null;
    }
    
    public NoLista(int info, NoLista prox) {
        this.info = info;
        this.prox = prox;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NoLista getProx() {
        return prox;
    }

    public void setProx(NoLista prox) {
        this.prox = prox;
    }

    Object getAnt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
