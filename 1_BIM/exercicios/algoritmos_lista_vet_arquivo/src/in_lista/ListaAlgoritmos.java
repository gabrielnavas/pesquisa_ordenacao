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
        No aux = null;
        
        if(pos == 0)
            aux = no;
        else if (pos < 0)
        {
            aux = no;
            while(aux != null && pos < 0)
            {
                aux = aux.getAnt();
                pos++;
            }
        }
        else
        {
            aux = no;
            while(aux != null && pos > 0)
            {
                aux = aux.getProx();
                pos--;
            }
        }
        
        return aux;
    }
    
    
    // ================= Algoritmos
    
    public void insercaoDireta()
    {
        No pI, pPos;
        int aux;
        
        pI = inicio.getProx();
        while(pI != null)
        {
            aux = pI.getInfo();
            pPos = pI;
            while(pPos != inicio && pPos.getAnt().getInfo() > aux)
            {
                pPos.setInfo(pPos.getAnt().getInfo());
                pPos = pPos.getAnt();
            }
            pPos.setInfo(aux);
            
            pI = pI.getProx();
        }
    }
    
    
    public int buscaBinariaIB(int chave, No fim)
    {
        return 0;
    }
    
    public void insercaoBinaria()
    {
        //faço depois
    }
    
    
    public void bolha()
    {
        int tl, aux;
        No pI;
        
        tl=getTl();
        while(tl > 1)
        {
            pI = inicio;
            while(pI != fim)
            {
                if(pI.getInfo() > pI.getProx().getInfo())
                {
                   aux = pI.getInfo();
                   pI.setInfo(pI.getProx().getInfo());
                   pI.getProx().setInfo(aux);
                }
                
                pI = pI.getProx();
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
                    noDist = getNo(noJ, dist);
                    if(noJ.getInfo() > noDist.getInfo())
                    {
                        aux = noJ.getInfo();
                        noJ.setInfo(noDist.getInfo());
                        noDist.setInfo(aux);
                    }
                    
                    noDist = getNo(noJ, -dist);
                    noK = noJ;
                    for(int k=j ; k-dist >= i && noK.getInfo() < noDist.getInfo() ; k-=dist)
                    {
                        aux = noK.getInfo();
                        noK.setInfo(noDist.getInfo());
                        noDist.setInfo(aux);
                        
                        noK = getNo(noK, -dist);
                    }
                    
                    noJ = getNo(noJ, dist);
                }
                
                noI = noI.getProx();
            }
            
            dist = dist/2;
        }
    }

    public void quickSP()
    {
        
    }
    
    public void quickSort()
    {
        
    }
    
    public void quickCP()
    {
        
    }
    
    public void merge1()
    {
        
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
    
    public int getTl() {
        return tl;
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
            listaInsercaoDireta.inserirFinal(i);
//            listaInsercaoBinaria.inserirFinal(i); //faço depois
            listaSelecaoDireta.inserirFinal(i);
            listaShell.inserirFinal(i);
            listaHeap.inserirFinal(i);
            listaBolha.inserirFinal(i);
            listaShake.inserirFinal(i);
//            listaQuickSP.inserirFinal(i);
//            listaQuickCP.inserirFinal(i);
//            listaQuickSort.inserirFinal(i);
//            listaMerge1.inserirFinal(i);
//            listaMerge2.inserirFinal(i);
//            listaComb.inserirFinal(i);
//            listaGnome.inserirFinal(i);
//            listaBucket.inserirFinal(i);
//            listaRadix.inserirFinal(i);
//            listaTim.inserirFinal(i);
        }
    }
    
    public static void runOrdenarListas()
    {
        listaInsercaoDireta.insercaoDireta();
        listaInsercaoBinaria.insercaoBinaria();
        listaSelecaoDireta.selecaoDireta();
        listaShell.shell();
        listaHeap.heap();
        listaBolha.bolha();
        listaShake.shake();
        listaQuickSP.quickSP();
        listaQuickCP.quickCP();
        listaQuickSort.quickSort();
        listaMerge1.merge1();
        listaMerge2.merge2();
        listaComb.comb();
        listaGnome.gnome();
        listaBucket.bucket();
        listaRadix.radix();
        listaTim.tim();
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
