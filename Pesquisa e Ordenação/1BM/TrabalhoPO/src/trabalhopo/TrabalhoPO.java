package trabalhopo;

/*
    Andressa Hisae Tsukasaki    R.A. 261742078
    Laura Lopes Stipsky         R.A. 261741578
*/

public class TrabalhoPO {
    
    Arquivo arqOrd, arqRev, arqRand, auxRev, auxRand; 

    public void geraTabela()
    {
        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();

        long tini, tfim, ttotalO, ttotalRev, ttotalRand;
        int compO, movO, compRev, movRev, compRand, movRand;

        //... Insercao Direta ... 

        //Arquivo Ordenado     
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.insercao_direta();
        tfim=System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); //faz uma cópia do arquivo de arqRev 
                                               //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.insercao_direta();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); //faz uma cópia do arquivo de arqRand  
                                                 //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.insercao_direta();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();     
        //grava na tabela informacoes os dados extraídos das execucoes do método
        //Insercao Direta
        gravaLinhaTabela(compO, calculaCompInsDir(arqOrd.filesize()), movO, calculaMovInsDir(arqOrd.filesize()), ttotalO, compRev, calculaCompInsDir(arqRev.filesize()), movRev, calculaMovInsDir(arqRev.filesize()), ttotalRev, compRand, calculaCompInsDir(arqRand.filesize()), movRand, calculaMovInsDir(arqRand.filesize()), ttotalRand );//tempo execução no arquivo Ordenado já convertido para segundos  
        //... fim Insercao Direta 
        //e assim continua para os outros métodos de ordenacao!!! 
    }
    
    public int calculaCompInsDir(int tamanho)
    {
        return 0;
    }
    
    public int calculaMovInsDir(int tamanho)
    {
        return 0;
    }

    public void gravaLinhaTabela(int compO, int calc_compO, int movO, int calc_movO, long ttotalO, 
                                 int compRev, int calc_compRe, int movRev, int calc_movRe, long ttotalRev,
                                 int compRand, int calc_compRa, int movRand, int calc_movRa, long ttotalRand)
            
    {
        
    }

    public static void main(String args[])
    {
        /*TrabalhoPO p = new TrabalhoPO();
        p.geraTabela();*/
        
        //-----TESTE ARQUIVO----------------------------
        
        Arquivo a = new Arquivo("arquivo.dat"), b  = new Arquivo("novo.dat");
        
        a.leArq();
        a.exibirArq();
        
        b.copiaArquivo(a.getArquivo());
        b.exibirArq();
        
        //------TESTE LISTA---------------------------------
        
        /*ListaEncad lista = new ListaEncad();
        lista.inicializa();
        lista.inserirNoFinal(2);
        lista.inserirNoFinal(3);
        lista.inserirNoFinal(1);
        lista.exibir();
        
        lista.insercao_binaria();
        lista.exibir();*/
    } 
}
