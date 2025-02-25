package algoritmos_ordenacao.arquivo.lista;

import algoritmos_ordenacao.arquivo.Arquivo;
import java.io.File;

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
        No no = inicio;
        
        while(no != null && i > 0)
        {
            no = no.getProx();
            i--;
        }
        
        return no;
    }
    
    public int getTl()
    {
        No aux = inicio;
        int cont = 0;
        
        while(aux != null)
        {
            aux = aux.getProx();
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
    
    public boolean isOrdenado()
    {
        int tl = getTl();
        int i=0;
        No aux;
        
        aux = inicio;
        while(aux != null && aux.getProx() != null && i < tl 
                && aux.getInfo() < aux.getProx().getInfo())
        {
            i++;
            aux = aux.getProx();
        }
        
        return i == tl-1;
    }
    
    public void exibir()
    {
        No aux = inicio;
        
        System.out.print(isOrdenado() + " ");
        
        while(aux != null)
        {
            System.out.print("["+aux.getInfo()+"]");
            aux = aux.getProx();
        }
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
    
    public int buscaBinaria(int chave, int tl)
    {
        int ini, fim, meio;
        No noMeio;
        
        ini = 0;
        fim = tl-1;
        meio = fim/2;
        
        noMeio = getNo(meio);
        
        while(ini < fim && noMeio.getInfo() != chave)
        {
            if(chave < noMeio.getInfo())
                fim = meio-1;
            else
                ini = meio+1;
            
            meio = (ini+fim)/2;
            noMeio = getNo(meio);
        }
        
        if(chave > noMeio.getInfo())
            return meio+1;
        return meio;
    }
    
    public void insercaoBinaria()
    {
        No noI, noJ;
        int aux, pos, tl;
        
        tl = getTl();
        
        noI = inicio.getProx();
        for(int i=1 ; noI != null ; i++)
        {
            aux = noI.getInfo();
            pos = buscaBinaria(aux, i);
            
            noJ = noI;
            for(int j=i ; j > pos ; j--)
            {
                noJ.setInfo(noJ.getAnt().getInfo());
                noJ = noJ.getAnt();
            }
            
            noJ = getNo(pos);
            noJ.setInfo(aux);
            
            noI = noI.getProx();
        }
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
            noI = noIni;
            while(noI != noFim)
            {
                if(noI.getInfo() > noI.getProx().getInfo())
                {
                    aux = noI.getInfo();
                    noI.setInfo(noI.getProx().getInfo());
                    noI.getProx().setInfo(aux);
                }
                
                noI = noI.getProx();
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

                noI = noI.getAnt();
            }

            if(noIni != noFim)
                noIni = noIni.getProx();

        }
        
    }
    
    public void shell()
    {
        int dist = 4, 
            tl = getTl(), 
            aux, k;
        
        No no1, no2;
        
        while(dist > 0)
        {
            for(int i=0 ; i < dist ; i++)
                for(int j=i ; j+dist < tl ; j++)
                {
                    no1 = getNo(j);
                    no2 = getNo(j+dist);
                    
                    if(no1.getInfo() > no2.getInfo())
                    {
                        aux = no1.getInfo();
                        no1.setInfo(no2.getInfo());
                        no2.setInfo(aux);
                        
                        if(j-dist >= i)
                        {
                            no1 = getNo(j);
                            no2 = getNo(j-dist);
                            
                            k=j;
                            while(k-dist >= i && no1.getInfo() < no2.getInfo())
                            {
                                aux = no1.getInfo();
                                no1.setInfo(no2.getInfo());
                                no2.setInfo(aux);
                               
                                k -= dist;
                                
                                if(k-dist >= i)
                                {
                                    no1 = getNo(k);
                                    no2 = getNo(k-dist);
                                }
                                
                            }
                        }
                    }
                }
            
            dist = dist/2;
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
                    
                    if(no2.getInfo() > no1.getInfo())
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
            
            no2 = getNo(tl-1);
            
            aux = inicio.getInfo();
            inicio.setInfo(no2.getInfo());
            no2.setInfo(aux);
            
            tl--;
        }
    }
    
    public void quickComPivo()
    {
        quickComPivo(0, getTl()-1);
    }
    
    private void quickComPivo(int ini, int fim)
    {
        int pivo, i, j, aux;
        No noI, noJ;
        
        pivo = getNo( (ini+fim)/2 ).getInfo();
        
        i=ini;
        j=fim;
        
        while(i < j)
        {
            noI = getNo(i);
            while(noI.getInfo() < pivo)
            {
                noI = noI.getProx();
                i++;
            }
            
            noJ = getNo(j);
            while(noJ.getInfo() > pivo)
            {
                noJ = noJ.getAnt();
                j--;
            }
            
            if(i <= j)
            {
                aux = noI.getInfo();
                noI.setInfo(noJ.getInfo());
                noJ.setInfo(aux);
                
                i++;
                j--;
            }
        }
        
        if(ini < j)
            quickComPivo(ini, j);
        if(i < fim)
            quickComPivo(i, fim);
    }
    
    public void quickSemPivo()
    {
        quickSemPivo(0, getTl()-1);
    }
    
    private void quickSemPivo(int ini, int fim)
    {
        int i, j, aux;
        No noI, noJ;
        
        
        i=ini;
        j=fim;
        while(i < j)
        {
            noI = getNo(i);
            noJ = getNo(j);
            while(i < j && noI.getInfo() <= noJ.getInfo())
            {
                i++;
                noI = noI.getProx();
            }
            
            aux = noI.getInfo();
            noI.setInfo(noJ.getInfo());
            noJ.setInfo(aux);
            
            noI = getNo(i);
            noJ = getNo(j);
            while(i < j && noJ.getInfo() >= noI.getInfo())
            {
                j--;
                noJ = noJ.getAnt();
            }
            
            aux = noI.getInfo();
            noI.setInfo(noJ.getInfo());
            noJ.setInfo(aux);
        }
        
        if(ini < i-1)
            quickSemPivo(ini, i-1);
        if(j+1 < fim)
            quickSemPivo(j+1, fim);
    }
    
    public void quickSort()
    {
        quickSort(0, getTl()-1);
    }
    
    private void quickSort(int ini, int fim)
    {
        int i, j, aux;
        boolean flag = true;
        No noI, noJ;
        
        i = ini;
        j = fim;
        while(i < j)
        {
            noI = getNo(i);
            noJ = getNo(j);
            
            if(flag)
                while(i < j && noI.getInfo() <= noJ.getInfo())
                {
                    i++;
                    noI = noI.getProx();
                }
            else
                while(i < j && noJ.getInfo() >= noI.getInfo())
                {
                    j--;
                    noJ = noJ.getAnt();
                }
            
            aux = noI.getInfo();
            noI.setInfo(noJ.getInfo());
            noJ.setInfo(aux);
            flag = !flag;
        }
        
        if(ini < i-1)
            quickSort(ini, i-1);
        
        if(j+1 < fim)
            quickSort(j+1, fim);
    }
    
    public void merge1()
    {
        int seq = 1;
        int tl = getTl();
        
        ListaDupla l1 = new ListaDupla();
        ListaDupla l2 = new ListaDupla();
        
        while(seq < tl)
        {
            particao(l1, l2);
            fusao1(l1, l2, seq);
            seq = seq * 2;
            
            l1.iniciar();
            l2.iniciar();
        }
        
    }
    
    public void particao(ListaDupla l1, ListaDupla l2)
    {
        int meio = getTl()/2;
        
        No noI = getNo(0);
        No noJ = getNo(meio);
        
//        for(int i=0 ; i < meio ; i++)
        while(noJ != null)
        {
            l1.inserirFinal(noI.getInfo());
            l2.inserirFinal(noJ.getInfo());
            
            noI = noI.getProx();
            noJ = noJ.getProx();
        }
    }
    
    public void fusao1(ListaDupla l1, ListaDupla l2, int seq)
    {
        int i, j, k, tl, aux_seq;
        No noI, noJ, noK;
        
        i=0;
        j=0;
        k=0;
        tl = getTl();
        aux_seq = seq;
        
        noI = l1.getNo(i);
        noJ = l2.getNo(j);
        noK = getNo(0);
        
        while(k < tl)
        {
            while(i < seq && j < seq)
            {
                if(noI.getInfo() < noJ.getInfo())
                {
                    noK.setInfo(noI.getInfo());
                    
                    noK = noK.getProx();
                    noI = noI.getProx();
                    i++;
                    k++;
                }
                else
                {
                    noK.setInfo(noJ.getInfo());
                    
                    noK = noK.getProx();
                    noJ = noJ.getProx();
                    j++;
                    k++;
                }
            }
            
//            noI = getNo(i);
            while(i < seq)
            {
                noK.setInfo(noI.getInfo());
                
                noK = noK.getProx();
                noI = noI.getProx();
                
                i++;
                k++;
            }
            
//            noJ = getNo(j);
            while(j < seq)
            {
                noK.setInfo(noJ.getInfo());
                
                noK = noK.getProx();
                noJ = noJ.getProx();

                j++;
                k++;
            }
            
            seq = seq + aux_seq;
        }
    }
    
    public void merge2()
    {
        ListaDupla auxLista = new ListaDupla();
        merge2(auxLista, 0, getTl()-1);
    }
    
    public void merge2(ListaDupla auxLista, int esq, int dir)
    {
        int meio;
        
        if(esq < dir)
        {
            meio = (esq+dir)/2;
            
            merge2(auxLista, esq, meio);
            merge2(auxLista, meio+1, dir);
            fusao2(auxLista, esq, meio, meio+1, dir);
            
            auxLista.iniciar();
        }
    }
    
    public void fusao2(ListaDupla auxLista, int ini1, int fim1, int ini2, int fim2)
    {
        int auxIni1 = ini1;
        No noI, noJ;
        
        noI = getNo(ini1);
        noJ = getNo(ini2);
        
        while(ini1 <= fim1 && ini2 <= fim2)
        {
            if(noI.getInfo() < noJ.getInfo())
            {
                auxLista.inserirFinal(noI.getInfo());
                ini1++;
                noI = noI.getProx();
            }
            else
            {
                auxLista.inserirFinal(noJ.getInfo());
                ini2++;
                noJ = noJ.getProx();
            }
        }
        
        noI = getNo(ini1);
        while(ini1 <= fim1)
        {
            auxLista.inserirFinal(noI.getInfo());
            noI = noI.getProx();
            ini1++;
        }
        
        noJ = getNo(ini2);
        while(ini2 <= fim2)
        {
            auxLista.inserirFinal(noI.getInfo());
            noJ = noJ.getProx();
            ini2++;
        }
        
        noI = getNo(auxIni1);
        noJ = auxLista.getNo(0);
        while(noJ != null)
        {
            noI.setInfo(noJ.getInfo());
            
            noI = noI.getProx();
            noJ = noJ.getProx();
        }
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
            vetAux[i] += vetAux[i-1];
        
        // gero saida em ordem
        noI = fim;
        for(int i=tl-1 ; i >= 0 ; i--)
        {
            vetSaida[ --vetAux[ noI.getInfo() ]] = noI.getInfo();
            noI = noI.getAnt();
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
        No noI;
        
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
            noI = fim;
            for(int i = tl-1 ; i >= 0  ; i--)
            {
                vetSaida[ --vetAux[ (noI.getInfo()/exp) % 10 ]] = noI.getInfo();
                noI = noI.getAnt();
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
    
    public void insercaoDireta(int ini, int fim)
    {
        No noAux, noPos;
        int pos, aux;
        
        int i=ini;
        while(i < fim)
        {
            aux = getNo(i+1).getInfo();
            pos = i+1;
            
            noPos = getNo(pos);
            while(pos > ini && noPos.getAnt().getInfo() > aux)
            {
                noPos.setInfo(noPos.getAnt().getInfo());
                noPos = noPos.getAnt();
                pos--;
            }
            
            noAux = getNo(pos);
            noAux.setInfo(aux);
            
            i++;
        }
    }
    
    public void tim()
    {
        int ini1, ini2, fim1, fim2, tl, run;
        ListaDupla auxLista = new ListaDupla();
        run = 32;
        tl = getTl();
        
        if(tl < run)
            insercaoDireta();
        else
        {                
            for(int i=0 ; i < tl ; i += run)
            {
                ini1 = i;
                fim1 = Math.min(i+run-1, tl-1);
                insercaoDireta(ini1, fim1);
            }
        
            for(int run2 = run ; run2 < tl ; run2 = 2* run)
                for(ini1 = 0 ; ini1 < tl ; ini1 += 2*run)
                {
                    auxLista.iniciar();
                    
                    fim1 = ini1 + run2-1;

                    ini2 = fim1+1;
                    fim2 = Math.min( ini1 + (2*run2-1), tl-1);

                    //o seekArq vai andando dentro do fusao, quando retira os dados do aux e passa para o arquivo.
                    fusao2(auxLista, ini1, fim1, ini2, fim2);
                }
        
        }
    }
}
