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
    
    public NoS getNo(int i)
    {
        NoS aux = inicio;
        while(i > 0)
        {
            aux = aux.getProx();
            i--;
        }
        return aux;
    }
    
    public void exibir()
    {
        NoS aux = inicio;
        while(aux != null)
        {
            System.out.print("["+aux.getInfo()+"]");
            aux = aux.getProx();
        }
    }
    
    // ----------------------------------------------------métodos de ordenação
    
    public void selecaoDireta()
    {
        NoS noI = new NoS();
        NoS noJ = new NoS();
        
        int tl = getTl();
        
        int menor, posMenor, aux;
        
        noI = inicio;
        for(int i=0 ; i < tl-1 ; i++)
        {
            menor = noI.getInfo();
            posMenor = i;
            
            noJ = noI.getProx();
            for(int j=i+1 ; j < tl ; j++)
            {
                if(noJ.getInfo() < menor)
                {
                    posMenor = j;
                    menor = noJ.getInfo();
                }
                
                noJ = noJ.getProx();
            }
            
            noJ = getNo(posMenor);
            
            noJ.setInfo(noI.getInfo());
            noI.setInfo(menor);
            
            noI = noI.getProx();
        }
    }
    
    
    public void counting()
    {
        int maior, tl = getTl();
        NoS noI;
        
        maior = getMaior();
        
        int[] vetAux = new int[maior+1];
        int[] vetSaida = new int[tl];
        
        //gera frequencia
        noI = inicio;
        while(noI != null)
        {
            vetAux[ noI.getInfo() ]++;
            noI = noI.getProx();
        }
        
        //gera acumulativa
        for(int i=1 ; i < vetAux.length ; i++)
            vetAux[i] += vetAux[i-1];
        
        // gero saida em ordem
        noI = inicio;
        for(int i=0 ; i < tl ; i++)
        {
            vetSaida[ --vetAux[ noI.getInfo() ]] = noI.getInfo();
            noI = noI.getProx();
        }
        //coloco de volta na lista
        noI = inicio;
        for(int i=0 ; i < vetSaida.length ; i++)
        {
            noI.setInfo( vetSaida[i] );
            noI = noI.getProx();
        }
    }
    
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
            //declarando aqui dentro para nao precisar iniciar com 0 os elementos
            vetAux = new int[10];
            
            //gerar frequencia
            noI = inicio;
            for(int i=0 ; i < tl ; i++)
            {
                vetAux[ (noI.getInfo()/exp) % 10  ]++;
                noI = noI.getProx();
            }
            
            //gerar acumulativa
            for(int i=1 ; i < 10 ; i++)
                vetAux[i] += vetAux[i-1];
            
            //gerar sequencia 
            noI = inicio;
            for(int i=0 ; i < tl ; i++)
            {
                vetSaida[ --vetAux[ (noI.getInfo()/exp) % 10 ]] = noI.getInfo();
                noI = noI.getProx();
            }
            
            noI = inicio;
            for(int i=0 ; i < tl ; i++)
            {
                noI.setInfo(vetSaida[i]);
                noI = noI.getProx();
            }
            
            exp = exp * 10;
        }
    }
    
    public void gnome()
    {
        NoS noI, noJ;
        int aux, tl = getTl();
        
        
        while(tl > 1)
        {
            noI = inicio;
            for(int i=0 ; i < tl-1 ; i++)
            {
                if(noI.getInfo() > noI.getProx().getInfo())
                {
                    aux = noI.getInfo();
                    noI.setInfo(noI.getProx().getInfo());
                    noI.getProx().setInfo(aux);
                    
                    if(i > 0)
                    {
                        i = i-2;
                        noI = getNo(i);
                    }
                    else
                        noI = noI.getProx();
                }
                else
                    noI = noI.getProx();
            }
            
            tl--;
        }
    }
    
    public void comb()
    {
        int dist, aux, tl;
        NoS no1, no2;
        
        tl = getTl();
        
        dist = (int) (tl / 1.3);
        while(dist > 0)
        {
            
            for(int i=0 ; i + dist < tl ; i++)
            {
                no1 = getNo(i);
                no2 = getNo(i+dist);
                
                if(no1.getInfo() > no2.getInfo())
                {
                    aux = no1.getInfo();
                    no1.setInfo(no2.getInfo());
                    no2.setInfo(aux);
                }
            }
            
            dist = (int) (dist / 1.3);
        }
    }
}
