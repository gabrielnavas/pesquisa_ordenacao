package algoritmos_ordenacao.arquivo;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.UtilApp.AlgoritmoNum;
import static utils.UtilApp.AlgoritmoNum.SELECAO_DIRETA;
import utils.UtilApp.ArquivoParams;
import static utils.UtilApp.ArquivoParams.gerarStringTamanho;
import utils.UtilApp.TipoOrdenado;


public class Aplicacao 
{    
    private Arquivo arqOrd;
    private Arquivo arqRev;
    private Arquivo arqRand;
    private Arquivo auxRev;
    private Arquivo auxRand;
    private Arquivo tabela;
    
            
    public Aplicacao()
    {
        
    }
    
    private void iniciarArquivos()
    {
        if(ArquivoParams.DELETA_ARQUIVOS_DADOS)
        {
            new File("arqOrd.bin").delete();
            new File("arqRev.bin").delete();
            new File("arqRand.bin").delete();
            new File("auxRev.bin").delete();
            new File("auxRand.bin").delete();
        }
        
        arqOrd = new Arquivo("arqOrd.bin");
        arqRev = new Arquivo("arqRev.bin");
        arqRand = new Arquivo("arqRand.bin");
        auxRev = new Arquivo("auxRev.bin");
        auxRand = new Arquivo("auxRand.bin");
        
        tabela = new Arquivo("tabela"/* + new Date().toString()+ ".dat"*/);
        
        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();
        
    }
    
    private void gerarCabecalhoTabela()
    {
        try 
        {
            tabela.getArquivo().writeBytes(
                "Metodos de ordenacao"+"\t\t\t"+
                "Arquivo Ordenado\t\t\t"+"Arquivo em Ordem Reversa"+
                "\t\t\t"+"Arquivo Randomico"+"\n"
            );
            
            tabela.getArquivo().writeBytes(
                "\t\t\t"+"Comp.Prog. Comp.Equa. Mov.Prog. Mov.Equa. "
                +"Tempo|Comp.Prog. Comp.Equa. Mov.Prog. Mov.Equa. Tempo|Comp.Prog. Comp."
                + "Equa. Mov.Prog. Mov.Equa. Tempo "+"\n"
            );
        } 
        catch (IOException ex)  { }
    }
    
    private void gravaLinhaTabela( String algoritmo,
            int compProgOrd, String compEquaOrd, int movProgOrd, String movEquaOrd, long tempoOrd,
            int compProgRev, String compEquaRev, int movProgRev, String movEquaRev, long tempoRev, 
            int compProgRand,String compEquaRand,int movProgRand,String movEquaRand,long tempoRand
    )
    {
        RandomAccessFile tabela = this.tabela.getArquivo();
        algoritmo = gerarStringTamanho(20, algoritmo);
        
        try
        {
            tabela.writeBytes(algoritmo 
                + "\t|" + compProgOrd  +"\t"+ compEquaOrd   +"\t"+ movProgOrd  +"\t"+ tempoOrd
                + "\t|" + compProgRev  +"\t"+ compEquaRev   +"\t"+ movProgRev  +"\t"+ tempoRev
                + "\t|" + compProgRand +"\t"+ compEquaRand  +"\t"+ movProgRand +"\t"+ tempoRand
                + "\n"
            );
        }
        catch(IOException ex)
        {
            System.out.println(
                    "Não foi possível gravar a linha do algoritmo " + algoritmo
            );
        }
    }
    
    public void exibirTabela()
    {
        tabela.exibirArqInText();
    }
    
    public void gerarLinha(String algName, int algTipo)
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotalO, ttotalRev, ttotalRand, tini, tfim;
        
        boolean calcula = (algTipo >= AlgoritmoNum.INSERCAO_DIRETA) 
                            && (algTipo <= AlgoritmoNum.SHAKE);
        
        System.out.println("GERANDO LINHA DO: "+algName);

//        arqOrd.exibirArq();
//        arqRev.exibirArq();
//        arqRand.exibirArq();
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        
        System.out.println("GERANDO LINHA DO: "+algName+"ORDENADO");
        
        switch(algTipo)
        {
            case AlgoritmoNum.INSERCAO_DIRETA: arqOrd.insercaoDireta(); break;
//            case AlgoritmoNum.INSERCAO_BINARIA:arqOrd.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA: arqOrd.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA: arqOrd.bolha(); break;
            case AlgoritmoNum.SHAKE:arqOrd.shake(); break;
            case AlgoritmoNum.HEAP: arqOrd.heap();break;
            case AlgoritmoNum.QUICKCP: arqOrd.quickCP();break;
            case AlgoritmoNum.QUICKSP: arqOrd.quickSP();break;
            case AlgoritmoNum.MERGE1: arqOrd.merge1();break;
            case AlgoritmoNum.MERGE2: arqOrd.merge2();break;
            case AlgoritmoNum.COUNTING: arqOrd.counting();break;
            case AlgoritmoNum.BUCKET: arqOrd.bucket();break;
            case AlgoritmoNum.RADIX: arqOrd.radix();break;
            case AlgoritmoNum.COMB: arqOrd.comb();break;
            case AlgoritmoNum.GNOME: arqOrd.gnome();break;
            case AlgoritmoNum.TIM: arqOrd.tim();break;
            
            default: System.out.println("Algoritmo não existe.");
        }
        
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        arqOrd.exibirArq();
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        
        
        System.out.println("GERANDO LINHA DO: "+algName+"REVERSO");
        
        switch(algTipo)
        {
            case AlgoritmoNum.INSERCAO_DIRETA: auxRev.insercaoDireta(); break;
//            case AlgoritmoNum.INSERCAO_BINARIA:auxRev.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA: auxRev.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA: auxRev.bolha(); break;
            case AlgoritmoNum.SHAKE:auxRev.shake(); break;
            case AlgoritmoNum.HEAP: auxRev.heap();break;
            case AlgoritmoNum.QUICKCP: auxRev.quickCP();break;
            case AlgoritmoNum.QUICKSP: auxRev.quickSP();break;
            case AlgoritmoNum.MERGE1: auxRev.merge1();break;
            case AlgoritmoNum.MERGE2: auxRev.merge2();break;
            case AlgoritmoNum.COUNTING: auxRev.counting();break;
            case AlgoritmoNum.BUCKET: auxRev.bucket();break;
            case AlgoritmoNum.RADIX: auxRev.radix();break;
            case AlgoritmoNum.COMB: auxRev.comb();break;
            case AlgoritmoNum.GNOME: auxRev.gnome();break;
            case AlgoritmoNum.TIM: auxRev.tim();break;
        }
        
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        auxRev.exibirArq();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        
        
        System.out.println("GERANDO LINHA DO: "+algName+"RANDOMICO");

        
        switch(algTipo)
        {
            case AlgoritmoNum.INSERCAO_DIRETA: auxRand.insercaoDireta(); break;
//            case AlgoritmoNum.INSERCAO_BINARIA:auxRev.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA: auxRand.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA: auxRand.bolha(); break;
            case AlgoritmoNum.SHAKE:auxRand.shake(); break;
            case AlgoritmoNum.HEAP: auxRand.heap();break;
            case AlgoritmoNum.QUICKCP: auxRand.quickCP();break;
            case AlgoritmoNum.QUICKSP: auxRand.quickSP();break;
            case AlgoritmoNum.MERGE1: auxRand.merge1();break;
            case AlgoritmoNum.MERGE2: auxRand.merge2();break;
            case AlgoritmoNum.COUNTING: auxRand.counting();break;
            case AlgoritmoNum.BUCKET: auxRand.bucket();break;
            case AlgoritmoNum.RADIX: auxRand.radix();break;
            case AlgoritmoNum.COMB: auxRand.comb();break;
            case AlgoritmoNum.GNOME: auxRand.gnome();break;
            case AlgoritmoNum.TIM: auxRand.tim();break;
        }
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        auxRand.exibirArq();
        
        
        gravaLinhaTabela(
            algName,  
                
            compO,
            (calcula) ? calcCompInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO) : "0.00",
            movO,
            (calcula) ? calcMovInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO) : "0.00",
            ttotalO,
            
            compRev,
            (calcula) ? calcCompInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO) : "0.00",
            movRev,
            (calcula) ? calcMovInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO) : "0.00",
            ttotalRev,
            
            compRand,
            (calcula) ? calcCompInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO) : "0.00",
            movRand,
            (calcula) ? calcMovInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO) : "0.00",
            ttotalRand
        ); 
        
    }

         
    public void gerarTabela()
    {
        String[] algoritmos = new String[]
        {
            "Inserção Direta", "Inserção Binária", "Seleção", "Bolha", "Shake",
            "Shell", "Heap", "Quick s/ pivô", "Quick c/ pivô", "Merge 1a Implement",
            "Merge 2a Implement", "Counting", "Bucket", "Radix", "Comb", "Gnome",
            "Tim"
        };
        
        for(int i=0 ; i < algoritmos.length ; i++)
            gerarLinha(algoritmos[i], i);
        
//        gerarLinha("Counting", AlgoritmoNum.COUNTING);
    }
    
    public static void main(String[] args)
    {
        Aplicacao p = new Aplicacao();
        p.iniciarArquivos();
        p.gerarTabela();
        p.exibirTabela();
        
        //FALTA ARRUMAR OS CALCULOS!!!!!!!!!!!!!!!!!!!!
    }
    
    private String calcCompInsDir(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (n - 1.00) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + n - 2) - 1.00);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + n - 4) - 1.00);

        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcMovInsDir(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (3 * (n - 1.00)) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + (9*n) - 10) / 4);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + (3*n) - 4) / 2);
        
        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcCompInsBin(int n, int tipoOrdenacao)
    {   
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (n - 1.00) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + n - 2) - 1.00);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + n - 4) - 1.00);

        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcMovInsBin(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (3 * (n - 1.00));

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + (9*n) - 10) / 4);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + (3*n) - 4) / 2);
        
        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcCompSeleDir(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (n - 1.00) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + n - 2) - 1.00);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + n - 4) - 1.00);

        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcMovSeleDir(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (3 * (n - 1.00)) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + (9*n) - 10) / 4);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + (3*n) - 4) / 2);
        
        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcCompBolha(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (n - 1.00) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + n - 2) - 1.00);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + n - 4) - 1.00);

        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcMovBolha(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (3 * (n - 1.00)) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + (9*n) - 10) / 4);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + (3*n) - 4) / 2);
        
        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcCompShake(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (n - 1.00) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + n - 2) - 1.00);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + n - 4) - 1.00);

        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    private String calcMovShake(int n, int tipoOrdenacao)
    {
        String calculoStr = "0.00";
        float calculo = 0.00f;
        
        if(tipoOrdenacao == TipoOrdenado.ORDENADO)
            calculo = (float) (3 * (n - 1.00)) ;

        else if(tipoOrdenacao == TipoOrdenado.RANDOMICO)
            calculo = (float) ((Math.pow(n, 2) + (9*n) - 10) / 4);

        else if(tipoOrdenacao == TipoOrdenado.REVERSO)
            calculo = (float) ((Math.pow(n, 2) + (3*n) - 4) / 2);
        
        calculoStr = String.format("%.2f", calculo);
        
        return calculoStr;
    }
    
    //    
//    private void gerarTabela()
//    {
//        int compO, movO,  compRev, movRev, compRand, movRand;
//        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
//        
//        arqOrd = new Arquivo("arqOrd.bin");
//        arqRev = new Arquivo("arqRev.bin");
//        arqRand = new Arquivo("arqRand.bin");
//        auxRev = new Arquivo("auxRev.bin");
//        auxRand = new Arquivo("auxRand.bin");
//        tabela = new Arquivo("tabela"/* + new Date().toString()+ ".dat"*/);
//        
//        arqOrd.geraArquivoOrdenado();
//        arqRev.geraArquivoReverso();
//        arqRand.geraArquivoRandomico();
//        
//        arqOrd.exibirArq();
//        System.out.println();
//        arqRev.exibirArq();
//        System.out.println();
//        arqRand.exibirArq();
//        System.out.println();
//        
//        //Arquivo Ordenado
//        arqOrd.initComp();
//        arqOrd.initMov();
//        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
//        arqOrd.insercaoDireta();
//        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
//        compO = arqOrd.getComp();
//        movO = arqOrd.getMov();
//        ttotalO = tfim - tini;
//        
//        arqOrd.exibirArq();
//        
//        //Arquivo Reverso
//        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
//        //para auxRev para preservar o original
//        auxRev.initComp();
//        auxRev.initMov();
//        tini = System.currentTimeMillis();
//        auxRev.insercaoDireta();
//        tfim = System.currentTimeMillis();
//        ttotalRev = tfim - tini;
//        compRev = auxRev.getComp();
//        movRev = auxRev.getMov();
//        
//        auxRev.exibirArq();
//        
//        //Arquivo Randomico
//        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
//        //para auxRand para preservar o original
//        auxRand.initComp();
//        auxRand.initMov();
//        tini = System.currentTimeMillis();
//        auxRand.insercaoDireta();
//        tfim = System.currentTimeMillis();
//        ttotalRand = tfim - tini;
//        compRand = auxRand.getComp();
//        movRand = auxRand.getMov();
//        
//        auxRand.exibirArq();
//        
//        gravaLinhaTabela(
//            "Insercao Direta",    
//                
//            compO,
//            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
//            movO,
//            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
//            ttotalO,
//            
//            compRev,
//            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
//            movRev,
//            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
//            ttotalRev,
//            
//            compRand,
//            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
//            movRand,
//            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
//            ttotalRand
//        );            
////
////        
////        
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.insercaoBinaria();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.insercaoBinaria();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.insercaoBinaria();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        gravaLinhaTabela(
////            "Insercao Binária",    
////                
////            compO,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
////            movO,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
////            ttotalO,
////            
////            compRev,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
////            movRev,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
////            ttotalRev,
////            
////            compRand,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
////            movRand,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
////            ttotalRand
////        );  
//    
//        
//        
//        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.selecaoDireta();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.selecaoDireta();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.selecaoDireta();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        gravaLinhaTabela(
////            "Seleção Direta",    
////                
////            compO,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
////            movO,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
////            ttotalO,
////            
////            compRev,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
////            movRev,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
////            ttotalRev,
////            
////            compRand,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
////            movRand,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
////            ttotalRand
////        );  
////   
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.bolha();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.bolha();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.bolha();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        gravaLinhaTabela(
////            "Bolha",    
////                
////            compO,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
////            movO,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
////            ttotalO,
////            
////            compRev,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
////            movRev,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
////            ttotalRev,
////            
////            compRand,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
////            movRand,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
////            ttotalRand
////        );  
////   
////        
////        
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.shake();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.shake();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.shake();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        gravaLinhaTabela(
////            "Shake",    
////                
////            compO,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
////            movO,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
////            ttotalO,
////            
////            compRev,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
////            movRev,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
////            ttotalRev,
////            
////            compRand,
////            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
////            movRand,
////            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
////            ttotalRand
////        );  
////    
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.shell();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqRand.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.shell();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.shell();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        gravaLinhaTabela(
////            "Shell",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        );  
////   
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.heap();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            auxRev.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.heap();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.heap();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        gravaLinhaTabela(
////            "Heap",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        );  
////   
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.quickCP();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.quickCP();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.quickCP();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        gravaLinhaTabela(
////            "Quick c/ pivô",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        );  
////    
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.quickSP();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.quickSP();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.quickSP();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Quick s/ pivô",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
////    
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.merge1();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.merge1();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.merge1();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Merge 1a Implement",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
////   
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.merge2();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.merge2();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.merge2();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Merge 2a Implement",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
////    
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
//////        arqOrd.counting();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
//////        auxRev.counting();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
//////        auxRand.counting();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Counting",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
////    
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.bucket();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.bucket();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.bucket();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Bucket",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
////    
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.radix();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.radix();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.radix();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Radix",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
////   
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.comb();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.comb();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.comb();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Comb",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
////
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.gnome();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.gnome();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.gnome();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Gnome",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
////    
////        
////        //Arquivo Ordenado
////        arqOrd.initComp();
////        arqOrd.initMov();
////        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        arqOrd.tim();
////        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
////        compO = arqOrd.getComp();
////        movO = arqOrd.getMov();
////        ttotalO = tfim - tini;
////        
////            arqOrd.exibirArq();
////        
////        
////        //Arquivo Reverso
////        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
////        //para auxRev para preservar o original
////        auxRev.initComp();
////        auxRev.initMov();
////        tini = System.currentTimeMillis();
////        auxRev.tim();
////        tfim = System.currentTimeMillis();
////        ttotalRev = tfim - tini;
////        compRev = auxRev.getComp();
////        movRev = auxRev.getMov();
////        
////            auxRev.exibirArq();
////        
////        
////        //Arquivo Randomico
////        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
////        //para auxRand para preservar o original
////        auxRand.initComp();
////        auxRand.initMov();
////        tini = System.currentTimeMillis();
////        auxRand.tim();
////        tfim = System.currentTimeMillis();
////        ttotalRand = tfim - tini;
////        compRand = auxRand.getComp();
////        movRand = auxRand.getMov();
////        
////            auxRand.exibirArq();
////        
////        
////        
////        gravaLinhaTabela(
////            "Tim",    
////                
////            compO,
////            "0.00",
////            movO,
////            "0.00",
////            ttotalO,
////            
////            compRev,
////            "0.00",
////            movRev,
////            "0.00",
////            ttotalRev,
////            
////            compRand,
////            "0.00",
////            movRand,
////            "0.00",
////            ttotalRand
////        ); 
//    }
}
