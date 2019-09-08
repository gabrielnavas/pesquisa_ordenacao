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
    
    public int getMaior()
    {
        int maior = inicio.getInfo();
        No aux = inicio.getProx();
        
        while(aux != null)
        {
            if(aux.getInfo() > maior)
                maior = aux.getInfo();
            
            aux = aux.getProx();
        }
        
        return maior;
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
        No noI;
        int tl, aux;
        
        tl = getTl();
        while(tl > 1)
        {
            noI = inicio;
            while(noI.getProx() != null)
            {
                if(noI.getInfo() > noI.getProx().getInfo())
                {
                    aux = noI.getInfo();
                    noI.setInfo(noI.getProx().getInfo());
                    noI.getProx().setInfo(aux);
                }
                
                noI = noI.getProx();
            }
            
            tl--;
        }
        
    }
    
    public void shake()
    {
        No noIni, noFim, noI;
        int aux;
        
        noIni = inicio;
        noFim = fim;
        
        while(noIni != noFim)
        {
            noI = inicio;
            while(noI != noFim)
            {
                if(noI.getInfo() > noI.getProx().getInfo())
                {
                    aux = noI.getInfo();
                    noI.setInfo(noI.getProx().getInfo());
                    noI.getProx().setInfo(aux);
                }
            }
            
            noFim = noFim.getAnt();
            
            noI = noFim;
            while(noI != noIni)
            {
                if(noI.getInfo() < noI.getAnt().getInfo())
                {
                    aux = noI.getInfo();
                    noI.setInfo(noI.getAnt().getInfo());
                    noI.getAnt().setInfo(aux);
                }
            }
            
            if(noIni != noFim)
                noIni = noIni.getProx();
        }
    }
    
    public void shell()
    {
        int dist = 4, 
            tl = getTl(), 
            aux;
        
        No no, noDist;
        
        while(dist > 0)
        {
            for(int i=0 ; i < dist ; i++)
                for(int j=i ; j+dist < tl ; j++)
                {
                    no = getNo(j);
                    noDist = getNo(j+dist);
                    
                    if(no.getInfo() > noDist.getInfo())
                    {
                        aux = no.getInfo();
                        no.setInfo(noDist.getInfo());
                        noDist.setInfo(aux);
                        
                        if(j-dist >= i)
                        {
                            no = getNo(j);
                            noDist = getNo(j-dist);
                            
                            for(int k=j ; k-dist >= i && 
                                    no.getInfo() < noDist.getInfo() ; k -= dist)
                            {
                                aux = no.getInfo();
                                no.setInfo(noDist.getInfo());
                                noDist.setInfo(aux);
                               
                                if(k-dist >= i)
                                {
                                    no = getNo(k);
                                    noDist = getNo(k-dist);
                                }
                            }
                        }
                    }
                }
            
            tl = tl/2;
        }
    }
    
    public void heap()
    {
        int pai, fe, fd, aux, maiorf, tl;
        No no1, no2;
        
        tl = getTl();
        while(tl > 1)
        {
            pai = tl/2-1;
            while(pai >= 0)
            {
                fe = pai+pai+1;
                fd = fe+1;
                maiorf = fe;
                
                if(fd < tl)
                {   
                    no1 = getNo(fe);
                    no2 = getNo(fd);
                    
                    if(no1.getInfo() > no2.getInfo())
                        maiorf = fd;
                }
                
                no1 = getNo(maiorf);
                no2 = getNo(pai);
                
                if(no1.getInfo() > no2.getInfo())
                {
                    aux = no1.getInfo();
                    no1.setInfo(no2.getInfo());
                    no2.setInfo(aux);
                }
                
                pai--;
            }
            
            tl--;
        }
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
    
    public void counting()
    {
        int maior, tl = getTl();
        No noI;
        
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
            vetAux[i] = vetAux[i-1];
        
        // gero saida em ordem
        noI = inicio;
        for(int i=0 ; i < tl ; i++)
            vetSaida[ --vetAux[ noI.getInfo() ]] = noI.getInfo();
        
        //coloco de volta na lista
        noI = inicio;
        for(int i=0 ; i < vetSaida.length ; i++)
            noI.setInfo( vetSaida[i] );
    }
    
    
}
