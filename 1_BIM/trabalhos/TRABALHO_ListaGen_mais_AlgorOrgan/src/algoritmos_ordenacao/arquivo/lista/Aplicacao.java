package algoritmos_ordenacao.arquivo.lista;



public class Aplicacao
{
    
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
    static ListaSimples listaComb = new ListaSimples();
    static ListaSimples listaGnome = new ListaSimples();
    static ListaDupla listaBucket = new ListaDupla();
    static ListaSimples listaCounting = new ListaSimples();
    static ListaSimples listaRadix = new ListaSimples();
    static ListaDupla listaTim = new ListaDupla();
        
    public static void inserirDadosListas()
    {        
        for(int i=50 ; i > 0 ; i--)
        {
            listaInsercaoDireta.inserirFinal(i);
            listaInsercaoBinaria.inserirFinal(i);
            listaSelecaoDireta.inserirFinal(i);
            listaShell.inserirFinal(i);
            listaHeap.inserirFinal(i);
            listaBolha.inserirFinal(i);
            listaShake.inserirFinal(i);
            listaQuickSP.inserirFinal(i);
            listaQuickCP.inserirFinal(i);
            listaQuickSort.inserirFinal(i);
            listaMerge1.inserirFinal(i);
            listaMerge2.inserirFinal(i);
            listaComb.inserirFinal(i);
            listaGnome.inserirFinal(i);
            listaBucket.inserirFinal(i);
            listaCounting.inserirFinal(i);
            listaRadix.inserirFinal(i);
            listaTim.inserirFinal(i);
        }
    }
    
    public static void ordenarListasExibir()
    {
        listaInsercaoDireta.insercaoDireta(); //certo
        System.out.print("\nInsercao Direta:   ");
        listaInsercaoDireta.exibir();

        listaInsercaoBinaria.insercaoBinaria(); //certo
        System.out.print("\nInsercao Binaria:  ");
        listaInsercaoBinaria.exibir();
        
        listaSelecaoDireta.selecaoDireta(); //certo
        System.out.print("\nSelecao Direta:    ");
        listaSelecaoDireta.exibir();
        
        listaShell.shell(); //certo
        System.out.print("\nShell:             ");
        listaShell.exibir();

        listaHeap.heap(); //certo
        System.out.print("\nHeap:              ");        
        listaHeap.exibir();

        listaBolha.bolha(); //certo
        System.out.print("\nBolha:             ");        
        listaBolha.exibir();

        listaShake.shake(); //certo
        System.out.print("\nShake:             ");        
        listaShake.exibir();

        listaQuickSP.quickSemPivo(); //certo
        System.out.print("\nQuickSP:           ");        
        listaQuickSP.exibir();

        listaQuickCP.quickComPivo(); //certo
        System.out.print("\nQuickCP:           ");
        listaQuickCP.exibir();

        listaQuickSort.quickSort(); //certo
        System.out.print("\nQuickSort:         ");
        listaQuickSort.exibir();

        listaMerge1.merge1();
        System.out.print("\nMerge1:            ");
        listaMerge1.exibir();

        listaMerge2.merge2();
        System.out.print("\nMerge2:            ");
        listaMerge2.exibir();

        listaComb.comb();  //certo
        System.out.print("\nComb:              ");
        listaComb.exibir();

        listaGnome.gnome(); //certo
        System.out.print("\nGnome:             ");
        listaGnome.exibir();

//        listaBucket.bucket();
        System.out.print("\nBucket:            ");
        listaBucket.exibir();

        listaCounting.counting();  //certo
        System.out.print("\nCounting:          ");
        listaCounting.exibir();

        listaRadix.radix();
        System.out.print("\nRadix:             ");
        listaRadix.exibir();

        listaTim.tim();
        System.out.print("\nTim:               ");
        listaTim.exibir();
    }
    
    public static void main(String[] args)
    {
        inserirDadosListas();
        ordenarListasExibir();
    }
}

