package lista_generica;


/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class NoPilha
{
    private NoGen noGen;
    private NoPilha prox;
    
    public NoPilha(NoGen no, NoPilha prox) {
        this.prox = prox;
        this.noGen = no;
    }

    public NoGen getNo() {
        return noGen;
    }

    public void setNo(NoGen no) {
        this.noGen = no;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }
}

