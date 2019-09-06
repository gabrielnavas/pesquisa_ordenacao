package lista_generica;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class Pilha
{
    private NoPilha topo;

    public Pilha() {
        topo = null;
    }
    
    public void push(NoGen no)
    {
        NoPilha novo = new NoPilha(no, topo);
        topo = novo;
    }
    
    public NoGen pop()
    {
        NoGen noGen = topo.getNo();
        topo = topo.getProx();
        return noGen;
    }
    
    public boolean isEmpty()
    {
        return topo == null;
    }
    
    public NoGen topo()
    {
        return topo.getNo();
    }
}

