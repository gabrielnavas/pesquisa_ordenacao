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
    
    public boolean isOrdenado()
    {
        int tl = getTl();
        int i=0;
        NoS aux;
        
        aux = inicio;
        while(aux != null && aux.getProx() != null && i < tl 
                && aux.getInfo() < aux.getProx().getInfo())
        {
            i++;
            aux = aux.getProx();
        }
        
        return i == tl-1;
    }
    
    
    
//    public void exibir()
//    {
//        NoS aux = inicio;
//        while(aux != null)
//        {
//            System.out.print("["+aux.getInfo()+"]");
//            aux = aux.getProx();
//        }
//    }
    
    public void exibir()
    {
        NoS aux = inicio;
        
        System.out.print(isOrdenado() + " ");
        
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
    
    public void bucket()
    {
        int baldes, maior, hash;
        NoS noI;
        ListaSimples[] bucket;
        
        maior = getMaior();
        
        baldes = maior/5;
        
        bucket = new ListaSimples[baldes+1];
        
        for(int i=0 ; i < bucket.length ; i++)
            bucket[i] = new ListaSimples();
        
        //distruibuir os baldes
        noI = inicio;
        while(noI != null)
        {
            hash = noI.getInfo()/5;
            bucket[hash].inserirFinal(noI.getInfo());
            
            noI = noI.getProx();
        }
        
        //ordenar os baldes
        for(int i=0 ; i < bucket.length ; i++)
            bucket[i].selecaoDireta();
        
        //pega os itens dos baldes
        noI = inicio;
        for(int i=0 ; i < bucket.length ; i++)
            for(int j=0 ; j < bucket[i].getTl() ; j++)
            {
                noI.setInfo( bucket[i].getNo(j).getInfo() );
                noI = noI.getProx();
            }
    }
}
