package in_vetor;

import Dados.Dados;

public class Vetor {
    
    private int[] vet;
    private int tl;
    
    public Vetor()
    {
        vet = Dados.getDadosInt();
        tl=vet.length;
    }
    
    // -- METODOS ORDENACAO -- //
    
    public void insercaoDireta()
    {
        int aux, pos;
        
        for(int i=1 ; i < tl ; i++)
        {
            aux=vet[i];
            pos=i;
            while(pos > 0 && vet[pos-1] > aux)
            {
                vet[pos] = vet[pos-1];
                pos--;
            }
            vet[pos] = aux;
        }
    }
    
    
    public int buscaBinariaSort(int chave, int tl)
    {
        int ini, fim, meio;
        
        ini=0;
        fim=tl-1;
        meio=fim/2;
        
        while(ini < fim && chave != vet[meio])
        {
            if(chave > vet[meio])
                ini = meio+1;
            else
                fim = meio-1;
            
            meio = (ini+fim)/2;
        }
        
        if(chave > vet[meio])
            return meio+1;
        return meio;
    }
    
    public void insercaoBinaria()
    {
        int aux, pos;
        
        for(int i=1; i < tl ; i++)
        {
            aux = vet[i];
            pos = buscaBinariaSort(aux, i);
            
            for(int j=i ; j > pos ; j--)
                vet[j] = vet[j-1];
            
            vet[pos] = aux;
        }
    }
    
    public void selecaoDireta()
    {
        int menor, posMenor;
        
        for(int i=0 ; i < tl-1 ; i++)
        {
            menor = vet[i];
            posMenor = i;
            for(int j=i+1 ; j < tl ; j++)
                if(vet[j] < menor)
                {
                    menor = vet[j];
                    posMenor = j;
                }
            vet[posMenor] = vet[i];
            vet[i] = menor;
        }
    }
    
    public void bolha()
    {
        int tl2, aux;
        
        tl2=tl;
        while(tl2 > 1)
        {
            for(int i=0 ; i < tl2-1 ; i++)
                if(vet[i] > vet[i+1])
                {
                    aux = vet[i];
                    vet[i] = vet[i+1];
                    vet[i+1] = aux;
                }
            
            tl--;
        }
    }
    
    public void shake()
    {
        int ini2, fim2, aux;
        
        ini2 = 0;
        fim2 = tl-1;
        while(ini2 < fim2)
        {
            for(int i=ini2 ; i < fim2 ; i++)
                if(vet[i] > vet[i+1])
                {
                    aux = vet[i];
                    vet[i] = vet[i+1];
                    vet[i+1] = aux;
                }
            
            fim2--;
            
            for(int j=fim2 ; j > ini2 ; j--)
                if(vet[j] > vet[j-1])
                {
                    aux = vet[j];
                    vet[j] = vet[j-1];
                    vet[j-1] = aux;
                }
            
            ini2++;
        }
    }
    
    public void shell()
    {
        int dist, aux;
        
        dist=4;
        while(dist > 0)
        {
            for(int i=0 ; i < dist ; i++)
                for(int j=i ; j+dist < tl ; j+=dist)
                    if(vet[j] > vet[j+dist])
                    {
                        aux = vet[j];
                        vet[j] = vet[j+dist];
                        vet[j+dist] = aux;
                        
                        for(int k=j ; k-dist >= 0 && vet[k] < vet[k-dist]; k--)
                        {
                            aux = vet[k];
                            vet[k] = vet[k-dist];
                            vet[k-dist] = aux;
                        }
                    }
            
            dist = dist/2;
        }
    }
    
    public void heap()
    {
        int pai, fe, fd, aux, tl2, maiorf;
        
        tl2=tl;
        while(tl2 > 1)
        {
            pai = tl2/2-1;
            while(pai >= 0)
            {
                fe = pai+pai+1;
                fd = fe+1;
                if(fd < tl2)
                {
                    if(vet[fe] > vet[fd])
                        maiorf = fd;
                    else
                        maiorf = fe;
                }
                else
                    maiorf = fe;
                
                if(vet[maiorf] > vet[pai])
                {
                    aux = vet[maiorf];
                    vet[maiorf] = vet[pai];
                    vet[pai] = aux;
                }
                pai--;
            }
            
            aux = vet[0];
            vet[0] = vet[tl2-1];
            vet[tl2-1] = aux;

            tl2--;
        }
    }
    
    public void quickSP()
    {
        
    }
    
    public void quickCP()
    {
        
    }
    
    public void quickSort()
    {
        
    }
    
    public void merge()
    {
        
    }
    
    public void comb()
    {
        int dist, i, aux;
        
        dist = (int) (tl/1.3);
        
        while(dist > 0)
        {
            for(i=0 ; i+dist < tl ; i++)
                if(vet[i] > vet[i+dist])
                {
                    aux=vet[i];
                    vet[i] = vet[i+dist];
                    vet[i+dist] = aux;
                }
            
            dist--;
        }
    }
}
