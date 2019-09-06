package algoritmos_ordenacao.arquivo.lista;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class ListaDupla 
{
    private No inicio;
        private No fim;

    public ListaDupla()
    {
        this.inicio = null;
        this.fim = null;
    }

    public void iniciar()
    {
        this.inicio = null;
        this.fim = null;
    }

    public void inserirFinal(int info)
    {
        No novo = new No(info, fim, null);

        if(fim == null)
            inicio = fim = novo;
        else
        {
            fim.setProx(novo);
            fim = novo;
        }
    }

    public void inserirInicio(int info)
    {
        No novo = new No(info, null, inicio);

        if(inicio == null)
            inicio = fim = novo;
        else
        {
            inicio.setAnt(novo);
            inicio = novo;
        }
    }
    
    public No getNo(int i)
    {
        No no = null;
        
        while(no != null && i > 0)
        {
            no = no.getProx();
            i--;
        }
        
        return no;
    }
    
    public int getTl()
    {
        No no = inicio;
        int cont = 0;
        
        while(no != null)
        {
            no = no.getProx();
            cont++;
        }
        
        return cont;
    }
    
    // -------------------------------------- Início dos métodos de ordenação
    public void insercaoDireta()
    {
        int tl, pos, aux;
        No noI;
        
        tl = getTl();
        
        for(int i=1 ; i < tl ; i++)
        {
            pos = i;
            aux = getNo(pos).getInfo();
            
            noI = getNo(pos);
            while(pos > 0 && noI.getAnt().getInfo() > aux)
            {
                noI.setInfo(noI.getAnt().getInfo());
                noI = noI.getAnt();
                pos--;
            }
            
            noI.setInfo(aux);
        }
    }
    
    public void insercaoBinaria()
    {
        
    }
    
    public void selecaoDireta()
    {
        
    }
    
    public void bolha()
    {
        
    }
    
    public void shake()
    {
        
    }
    
    public void shell()
    {
        
    }
    
    public void heap()
    {
        
    }
    
    public void quickCP()
    {
        
    }
    
    public void quickSP()
    {
        
    }
    
    public void merge1()
    {
        
    }
    
    public void merge2()
    {
        
    }
}
