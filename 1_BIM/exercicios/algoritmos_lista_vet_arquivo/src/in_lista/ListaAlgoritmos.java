package in_lista;


// ======================================================== NO
class No {
    
    private int info;
    private No prox;
    private No ant;
    
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
class ListaDupla {
    
    private No inicio;
    private No fim;
    int tl;
    
    public ListaDupla()
    {
        this.inicio = inicio;
        this.fim = fim;
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
    
    public void heap()
    {
        
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
                
                noI = getNo(noI, 1);
            }
            
            dist = dist/4;
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
    
}

public class ListaAlgoritmos {
    
    static ListaDupla listaInsercaoDireta = new ListaDupla();
    static ListaDupla listaInsercaoBinaria = new ListaDupla();
    static ListaDupla listaSelecaoDireta = new ListaDupla();
    static ListaDupla listaShell = new ListaDupla();
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
//            listaInsercaoDireta.inserirFinal(i);
//            listaInsercaoBinaria.inserirFinal(i);
//            listaSelecaoDireta.inserirFinal(i);
//            listaShell.inserirFinal(i);
//            listaHeap.inserirFinal(i);
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
