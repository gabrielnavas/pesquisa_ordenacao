package algoritmos_ordenacao.arquivo.lista;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class No 
{
    private int info;
    private No prox;
    private No ant;
    
    public No() { }

    public No(int info, No ant, No prox) {
        this.info = info;
        this.ant = ant;
        this.prox = prox;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }
}
