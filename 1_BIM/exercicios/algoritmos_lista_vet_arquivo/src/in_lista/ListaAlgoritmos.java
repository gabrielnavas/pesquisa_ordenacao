package in_lista;


// ======================================================== NO
class No {
    
    private int info;
    private No prox;
    private No ant;
    
    public No(int info, No prox)
    {
        this.prox = prox;
        this.info = info;
    }

    public No(int info, No ant, No prox)
    {
        this.ant = ant;
        this.prox = prox;
        this.info = info;
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

// ======================================================== LISTA DUPLAMENTE ENCADEADA
class ListaDupla 
{    
    private No inicio;
    private No fim;
    int tl;
    
    public ListaDupla()
    {
        this.inicio = null;
        this.fim = null;
        this.tl=0;
    }
    
    public void inicia()
    {
        this.inicio = this.fim = null;
        this.tl=0;
    }
    
    public void inserirFinal(int num)
    {
        No novo = new No(num, null, null);
        
        if(fim == null)
            inicio=fim=novo;
        else
        {
            novo.setAnt(fim);
            fim.setProx(novo);
            fim=novo;
        }
        
        tl++;
    }
    
    
    public No getNo(No no, int pos)
    {
        No aux = no;
        
        if (pos < 0)
        {
            while(aux != null && pos < 0)
            {
                aux = aux.getAnt();
                pos++;
            }
        }
        else
        {
            while(aux != null && pos > 0)
            {
                aux = aux.getProx();
                pos--;
            }
        }
        
        return aux;
    }
    public No getNo(int pos)
    {
        No aux = inicio;
        
        while(pos > 0)
        {
            aux = aux.getProx();
            pos--;
        }

        return aux;
    }
    
    
    // ================= Algoritmos
    
    public void insercaoDireta()
    {
        No noI, noPos;
        int aux;
        
        noI = inicio.getProx();
        while(noI != null)
        {
            aux = noI.getInfo();
            noPos = noI;
            
            while(noPos != inicio && noPos.getAnt().getInfo() > aux)
            {
                noPos.setInfo(noPos.getAnt().getInfo());
                noPos = noPos.getAnt();
            }
            noPos.setInfo(aux);
            
            noI = noI.getProx();
        }
    }
    
    
    public int buscaBinariaIB(int chave, No fim)
    {
        //faço depois
        return 0;
    }
    
    public void insercaoBinaria()
    {
        //faço depois
    }
    
    
    public void bolha()
    {
        int tl, aux;
        No noI;
        
        tl=getTl();
        while(tl > 1)
        {
            noI = inicio;
            while(noI != fim)
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
            
            noI = fim;
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
    
    public void heap()
    {
        int pai, fd, fe, aux, tl;
        No noPai, noFd, noFe, noMaiorf, noTL;
        
        noTL = fim;
        tl = getTl();
        while(tl > 1)
        {
            pai = tl/2 - 1;
            while(pai >= 0)
            {
                fe = pai+pai+1;
                fd = fe+1;
                
                noPai = getNo(inicio, pai);
                noFe = getNo(inicio, fe);
                noFd = noFe.getProx();
                
                if(fd < tl)
                {
                    if(noFe.getInfo() > noFd.getInfo())
                        noMaiorf = noFe;
                    else
                        noMaiorf = noFd;
                }
                else
                    noMaiorf = noFe;
                
                if(noMaiorf.getInfo() > noPai.getInfo())
                {
                    aux = noMaiorf.getInfo();
                    noMaiorf.setInfo(noPai.getInfo());
                    noPai.setInfo(aux);
                }
                
                pai--;
            }
            
            aux = inicio.getInfo();
            inicio.setInfo(noTL.getInfo());
            noTL.setInfo(aux);
            
            noTL = noTL.getAnt();
            tl--;
        }
    }
    
    public void shell()
    {
        int aux,
            dist = 4,
            tl;
        
        No noI, noJ, noK, noDist;
        
        tl=getTl();
        
        while(dist > 0)
        {         
            noI=inicio;
            for(int i = 0 ; i < dist ; i++)
            {
                noJ = noI;
                for(int j=i ; j+dist < tl ; j+=dist)
                {
                    noJ = getNo(j);
                    noDist = getNo(j+dist);
                    
                    if(noJ.getInfo() > noDist.getInfo())
                    {
                        aux = noJ.getInfo();
                        noJ.setInfo(noDist.getInfo());
                        noDist.setInfo(aux);
                    

                        noDist = getNo(j-dist);
                        noK = noJ;
                        for(int k=j ; k-dist >= i && noK.getInfo() < noDist.getInfo() ; k-=dist)
                        {   
                            noDist = getNo(k-dist);
                            noK = getNo(k);
                            
                            aux = noK.getInfo();
                            noK.setInfo(noDist.getInfo());
                            noDist.setInfo(aux);
                        }
                    }
                }
                noI = noI.getProx();
            }
            
            dist = dist/2;
        }
    }

    
    public void quickSP()
    {
        quickSP(0, getTl()-1, inicio, fim);
    }
    
    public void quickSP(int ini, int fim, No noIni, No noFim)
    {
        int aux, i, j;
        No noI, noJ;
        
        i=ini;
        j=fim;
        noI = noIni;
        noJ = noFim;
        
        while(i < j)
        {
            while(i < j && noI.getInfo() <= noJ.getInfo())
            {
                i++;
                noI = noI.getProx();
            }
            
            aux = noI.getInfo();
            noI.setInfo(noJ.getInfo());
            noJ.setInfo(aux);
            
            while(i < j && noJ.getInfo() >= noI.getInfo())
            {
                j--;
                noJ = noJ.getAnt();
            }
            
            aux = noJ.getInfo();
            noI.setInfo(noJ.getInfo());
            noJ.setInfo(aux);
        }
        
        if(ini < i-1)
            quickSP(ini, i-1, noIni, noI.getAnt());
        if(j+1 < fim)
            quickSP(j+1, fim, noJ.getProx(), noFim);
    }
    
    public void quickSort()
    {
        quickSort(0, getTl()-1, inicio, fim);
    }
    
    public void quickSort(int ini, int fim, No noIni, No noFim)
    {
        boolean flag = true;
        int aux, i, j;
        No noI, noJ;
        
        i = ini;
        j = fim;
        noI = noIni;
        noJ = noFim;
        
        while(i < j)
        {
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
            quickSort(ini, i-1, noIni, noI.getAnt());
        if(j+1 < fim)
            quickSort(j+1, fim, noJ.getProx(), noFim);
    }
    
    public void quickCP()
    {
        quickCP(0, getTl()-1, inicio, fim);
    }
    
    public void quickCP(int ini, int fim, No noIni, No noFim)
    {        
        int aux, i, j, pivo;
        No noI, noJ, noPivo;
        i=ini;
        j=fim;
        pivo = (ini+fim)/2;
        noI=noIni;
        noJ=noFim;
        noPivo = getNo(pivo);
        
        while(i < j)
        {
            while(noI.getInfo() < noPivo.getInfo())
            {
                i++;
                noI = noI.getProx();
            }
            while(noJ.getInfo() > noPivo.getInfo())
            {
                j--;
                noJ = noJ.getAnt();
            }
            
            if(i <= j)
            {
                aux = noI.getInfo();
                noI.setInfo(noJ.getInfo());
                noJ.setInfo(aux);
                
                i++;
                noI = noI.getProx();
                
                j--;
                noJ = noJ.getAnt();
            }
        }
        
        if(ini < j)
            quickCP(ini, j, noIni, noJ);
        if(i < fim)
            quickCP(i, fim, noI, noFim);
    }
    
    public void merge1()
    {
        ListaDupla[] lts;
        int seq = 1;
        
        while(seq < getTl())
        {
            lts = particao();
            fusao(lts[0], lts[1], seq);
            
            seq = seq * 2;
        }
    }
    
    public ListaDupla[] particao()
    {
        ListaDupla[]lts = new ListaDupla[2];
        lts[0] = new ListaDupla();
        lts[1] = new ListaDupla();
        
        int i=0;
        No no = inicio;
        while(no != null && i < getTl()/2)
        {
            lts[0].inserirFinal(no.getInfo());
            no = no.getProx();
            i++;
        }
        
        while(no != null)
        {
            lts[1].inserirFinal(no.getInfo());
            no = no.getProx();
        }
        
        return lts;
    }
    
    public void fusao(ListaDupla l1, ListaDupla l2, int seq)
    {
        int i=0, j=0, k=0, aux_seq=seq;
        No  noI=l1.getInicio(), 
            noJ=l2.getInicio(), 
            noK=inicio;
        
        while(k < getTl()-1)
        {
            while(i < seq && j < seq)
            {
                if(noI.getInfo() < noJ.getInfo())
                {
                    noK.setInfo(noI.getInfo());
                    
                    i++;
                    k++;
                    noI = noI.getProx();
                    noK = noK.getProx();
                }
                else
                {
                    noK.setInfo(noJ.getInfo());
                    
                    j++;
                    k++;
                    noJ = noJ.getProx();
                    noK = noK.getProx();
                }
            }
            
            while(i < seq)
            {
                noK.setInfo(noI.getInfo());
                
                i++;
                k++;
                noI = noI.getProx();
                noK = noK.getProx();
            }
            
            while(j < seq)
            {
                noK.setInfo(noJ.getInfo());
                
                j++;
                k++;
                noJ = noJ.getProx();
                noK = noK.getProx();
            }
            
            seq = seq + aux_seq;
        }
    }
    
    public void merge2()
    {
        
    }
    
    public void comb()
    {
        
    }
    
    public void gnome()
    {
        
    }
    
    public void bucket()
    {
        
    }
    
    public void radix()
    {
        
    }
    
    public void tim()
    {
        
    }
    
    
    public void exibir()
    {
        No aux = inicio;
        while(aux != null)
        {
            System.out.print("["+aux.getInfo()+"] ");
            aux=aux.getProx();
        }
        System.out.println();
    }
    
    
    public int getTl() {
        return tl;
    }

    public No getInicio() {
        return inicio;
    }

    public No getFim() {
        return fim;
    }
}


// ======================================================== LISTA SIMPLESMENTE ENCADEADA
class ListaSimples
{
    private No inicio;
    int tl;    
    
    public ListaSimples()
    {
        this.inicio = null;
        this.tl=0;
    }
    
    public void inicia()
    {
        this.inicio=null;
        this.tl=0;
    }
    
    public void inserirFinal(int num)
    {
        No novo = new No(num, null);
        
        if(inicio == null)
            inicio=novo;
        else
        {
            No aux = inicio;
            while(aux.getProx() != null)
                aux = aux.getProx();
            
            aux.setProx(novo);
        }
        
        tl++;
    }
    
    public void exibir()
    {
        No aux = inicio;
        while(aux != null)
        {
            System.out.print("["+aux.getInfo()+"] ");
            aux=aux.getProx();
        }
        System.out.println();
    }
    
    
    public void selecaoDireta()
    {
        No pPosMenor, pI, pJ;
        int menor;
        
        pI = inicio;
        while(pI.getProx() != null)
        {
            pPosMenor = pI;
            menor = pI.getInfo();
            
            pJ = pI.getProx();
            while(pJ != null)
            {
                if(pJ.getInfo() < menor)
                {
                    pPosMenor = pJ;
                    menor = pJ.getInfo();
                }
                
                pJ = pJ.getProx();
            }
            
            pPosMenor.setInfo(pI.getInfo());
            pI.setInfo(menor);
            
            pI = pI.getProx();
        }
    }
}

public class ListaAlgoritmos {
    
    static ListaDupla listaInsercaoDireta = new ListaDupla();
    static ListaDupla listaInsercaoBinaria = new ListaDupla();
    static ListaSimples listaSelecaoDireta = new ListaSimples();
    static ListaDupla listaShell = new ListaDupla();
    static ListaDupla listaBolha = new ListaDupla();
    static ListaDupla listaShake = new ListaDupla();
    static ListaDupla listaHeap = new ListaDupla();
    static ListaDupla listaQuickSP = new ListaDupla();
    static ListaDupla listaQuickCP = new ListaDupla();
    static ListaDupla listaQuickSort = new ListaDupla();
    static ListaDupla listaMerge1 = new ListaDupla();
    static ListaDupla listaMerge2 = new ListaDupla();
    static ListaDupla listaComb = new ListaDupla();
    static ListaDupla listaGnome = new ListaDupla();
    static ListaDupla listaBucket = new ListaDupla();
    static ListaDupla listaRadix = new ListaDupla();
    static ListaDupla listaTim = new ListaDupla();
        
    public static void inserirDadosListas()
    {
        int[] dados = Dados.Dados.getDadosInt();
        
        for(int i=0 ; i < dados.length ; i++)
        {
            listaInsercaoDireta.inserirFinal(dados[i]);
//            listaInsercaoBinaria.inserirFinal(dados[i]); //faço depois
            listaSelecaoDireta.inserirFinal(dados[i]);
            listaShell.inserirFinal(dados[i]);
            listaHeap.inserirFinal(dados[i]);
            listaBolha.inserirFinal(dados[i]);
            listaShake.inserirFinal(dados[i]);
            listaQuickSP.inserirFinal(dados[i]);
            listaQuickCP.inserirFinal(dados[i]);
            listaQuickSort.inserirFinal(dados[i]);
            listaMerge1.inserirFinal(dados[i]);
//            listaMerge2.inserirFinal(dados[i]);
//            listaComb.inserirFinal(dados[i]);
//            listaGnome.inserirFinal(dados[i]);
//            listaBucket.inserirFinal(dados[i]);
//            listaRadix.inserirFinal(dados[i]);
//            listaTim.inserirFinal(dados[i]);
        }
    }
    
    public static void runOrdenarListas()
    {
        listaInsercaoDireta.insercaoDireta();
//        listaInsercaoBinaria.insercaoBinaria();
        listaSelecaoDireta.selecaoDireta();
        listaShell.shell();
        listaHeap.heap();
        listaBolha.bolha();
        listaShake.shake();
        listaQuickSP.quickSP();
        listaQuickCP.quickCP();
        listaQuickSort.quickSort();
        listaMerge1.merge1();
//        listaMerge2.merge2();
//        listaComb.comb();
//        listaGnome.gnome();
//        listaBucket.bucket();
//        listaRadix.radix();
//        listaTim.tim();
    }
    
    public static void exibirDadosListas()
    {
        System.out.print("Insercao Direta:   ");
        listaInsercaoDireta.exibir();

        System.out.print("Insercao Binaria:  ");
        listaInsercaoBinaria.exibir();

        System.out.print("Selecao Direta:    ");
        listaSelecaoDireta.exibir();
        
        System.out.print("Shell:             ");
        listaShell.exibir();

        System.out.print("Heap:              ");        
        listaHeap.exibir();

        System.out.print("Bolha:             ");        
        listaBolha.exibir();

        System.out.print("Shake:             ");        
        listaShake.exibir();
        
        System.out.print("QuickSP:           ");        
        listaQuickSP.exibir();

        System.out.print("QuickCP:           ");
        listaQuickCP.exibir();

        System.out.print("QuickSort:         ");
        listaQuickSort.exibir();

        System.out.print("Merge1:            ");
        listaMerge1.exibir();

        System.out.print("Merge2:            ");
        listaMerge2.exibir();

        System.out.print("Comb:              ");
        listaComb.exibir();

        System.out.print("Gnome:             ");
        listaGnome.exibir();

        System.out.print("Bucket:            ");
        listaBucket.exibir();

        System.out.print("Radix:             ");
        listaRadix.exibir();

        System.out.print("Tim:               ");
        listaTim.exibir();
    }
    
    public static void main(String[] args)
    {
        inserirDadosListas();
        runOrdenarListas();
        exibirDadosListas();
    }
}
