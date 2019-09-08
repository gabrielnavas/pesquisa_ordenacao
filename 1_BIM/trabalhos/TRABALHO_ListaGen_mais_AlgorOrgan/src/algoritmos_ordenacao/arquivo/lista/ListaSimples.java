package algoritmos_ordenacao.arquivo.lista;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class ListaSimples 
{
    private NoS inicio;
    
    public ListaSimples()
    {
        this.inicio = null;
    }
    
    public void inserirFinal(int info)
    {
        NoS novo = new NoS(info, null);
        NoS aux;
        
        if(inicio == null)
            inicio = novo;
        else
        {
            aux = inicio;
            while(aux.getProx() != null)
                aux = aux.getProx();
            
            aux.setProx(novo);
            inicio = novo;
        }
    }
    
    public int getTl()
    {
        int cont=0;
        NoS no = inicio;
        
        while(no != null)
        {
            no = no.getProx();
            cont++;
        }
        
        return cont++;
    }
    
    public int getMaior()
    {
        int maior = inicio.getInfo();
        
        NoS aux = inicio.getProx();
        
        while(aux != null)
        {
            if(aux.getInfo() > maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }
        
        return maior;
    }
    
    // ----------------------------------------------------métodos de ordenação
    
    
    public void radix()
    {
        /* 
            valorNo/[1..10..100....] % 10 = numero n de um número  
        */
        int maior, exp, tl;
        NoS noI;
        
        maior = getMaior();
        exp = 1;
        tl = getTl();
        
        int[] vetAux;
        int[] vetSaida = new int[tl];
        
        while(maior/exp > 0)
        {
            noI = inicio;
            
            //declarando aqui dentro para nao precisar iniciar com 0 os elementos
            vetAux = new int[10];
            
            //gerar frequencia
            for(int i=0 ; i < tl ; i++)
                vetAux[ (noI.getInfo()/exp) % 10  ]++;
            
            //gerar acumulativa
            for(int i=1 ; i < 10 ; i++)
                vetAux[i] = vetAux[i-1];
            
            noI = inicio;
            //gerar sequencia logica
            for(int i=0 ; i < tl ; i++)
            {
                vetSaida[ --vetAux[ (noI.getInfo()/exp) % 10 ]] = noI.getInfo();
                noI = noI.getProx();
            }
            
            exp = exp * 10;
        }
    }
}
