package algoritmos_ordenacao.arquivo;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import utils.UtilApp.AlgoritmoNum;
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
    
            
    public Aplicacao() { }
    
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
        
        if(ArquivoParams.DELETA_ARQUIVO_TABELA)
            new File("tabela").delete();
        
        
        //abre os arquivos
        arqOrd = new Arquivo("arqOrd.bin");
        arqRev = new Arquivo("arqRev.bin");
        arqRand = new Arquivo("arqRand.bin");
        auxRev = new Arquivo("auxRev.bin");
        auxRand = new Arquivo("auxRand.bin");
        
        tabela = new Arquivo("tabela"/* + new Date().toString()+ ".dat"*/);
//        gerarCabecalhoTabela();
        
        //gera os dados nos arquivos
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
        
        System.out.println("----------- GERANDO ARQUIVOS DO: "+algName+" ------------");

        //apagar depois
//        arqOrd.exibirArq();
//        arqRev.exibirArq();
//        arqRand.exibirArq();
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        
        System.out.println("----------- GERANDO LINHA DO: "+algName+" ORDENADO ------ ");
        
        switch(algTipo)
        {
            case AlgoritmoNum.INSERCAO_DIRETA:   arqOrd.insercaoDireta(); break;
            case AlgoritmoNum.INSERCAO_BINARIA:  arqOrd.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA:    arqOrd.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA:             arqOrd.bolha(); break;
            case AlgoritmoNum.SHAKE:             arqOrd.shake(); break;
            case AlgoritmoNum.HEAP:              arqOrd.heap();break;
            case AlgoritmoNum.QUICKCP:           arqOrd.quickCP();break;
            case AlgoritmoNum.QUICKSP:           arqOrd.quickSP();break;
            case AlgoritmoNum.MERGE1:            arqOrd.merge1();break;
            case AlgoritmoNum.MERGE2:            arqOrd.merge2();break;
            case AlgoritmoNum.COUNTING:          arqOrd.counting();break;
            case AlgoritmoNum.BUCKET:            arqOrd.bucket();break;
            case AlgoritmoNum.RADIX:             arqOrd.radix();break;
            case AlgoritmoNum.COMB:              arqOrd.comb();break;
            case AlgoritmoNum.GNOME:             arqOrd.gnome();break;
            case AlgoritmoNum.TIM:               arqOrd.tim();break;
            
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
        
        
        System.out.println("----------- GERANDO LINHA DO: "+algName+" REVERSO ------ ");
        
        switch(algTipo)
        {
            case AlgoritmoNum.INSERCAO_DIRETA:    auxRev.insercaoDireta(); break;
            case AlgoritmoNum.INSERCAO_BINARIA:   auxRev.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA:     auxRev.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA:              auxRev.bolha(); break;
            case AlgoritmoNum.SHAKE:              auxRev.shake(); break;
            case AlgoritmoNum.HEAP:               auxRev.heap();break;
            case AlgoritmoNum.QUICKCP:            auxRev.quickCP();break;
            case AlgoritmoNum.QUICKSP:            auxRev.quickSP();break;
            case AlgoritmoNum.MERGE1:             auxRev.merge1();break;
            case AlgoritmoNum.MERGE2:             auxRev.merge2();break;
            case AlgoritmoNum.COUNTING:           auxRev.counting();break;
            case AlgoritmoNum.BUCKET:             auxRev.bucket();break;
            case AlgoritmoNum.RADIX:              auxRev.radix();break;
            case AlgoritmoNum.COMB:               auxRev.comb();break;
            case AlgoritmoNum.GNOME:              auxRev.gnome();break;
            case AlgoritmoNum.TIM:                auxRev.tim();break;
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
        
        
        System.out.println("----------- GERANDO LINHA DO: "+algName+" RANDOMICO ------ ");

        
        switch(algTipo)
        {
            case AlgoritmoNum.INSERCAO_DIRETA:    auxRand.insercaoDireta(); break;
            case AlgoritmoNum.INSERCAO_BINARIA:   auxRand.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA:     auxRand.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA:              auxRand.bolha(); break;
            case AlgoritmoNum.SHAKE:              auxRand.shake(); break;
            case AlgoritmoNum.HEAP:               auxRand.heap();break;
            case AlgoritmoNum.QUICKCP:            auxRand.quickCP();break;
            case AlgoritmoNum.QUICKSP:            auxRand.quickSP();break;
            case AlgoritmoNum.MERGE1:             auxRand.merge1();break;
            case AlgoritmoNum.MERGE2:             auxRand.merge2();break;
            case AlgoritmoNum.COUNTING:           auxRand.counting();break;
            case AlgoritmoNum.BUCKET:             auxRand.bucket();break;
            case AlgoritmoNum.RADIX:              auxRand.radix();break;
            case AlgoritmoNum.COMB:               auxRand.comb();break;
            case AlgoritmoNum.GNOME:              auxRand.gnome();break;
            case AlgoritmoNum.TIM:                auxRand.tim();break;
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
        
        System.out.println("-------------- FIM DA LINHA DO: "+algName+"-------------");
    }

         
    public void gerarTabela()
    {
//        String[] algoritmos = new String[]
//        {
//            "Inserção Direta", "Inserção Binária", "Seleção", "Bolha", "Shake",
//            "Shell", "Heap", "Quick s/ pivô", "Quick c/ pivô", "Merge 1a Implement",
//            "Merge 2a Implement", "Counting", "Bucket", "Radix", "Comb", "Gnome",
//            "Tim"
//        };
//        
//        for(int i=0 ; i < algoritmos.length ; i++)
//            gerarLinha(algoritmos[i], i);
        
        gerarLinha("Tim", AlgoritmoNum.TIM);
    }
    
    public static void main(String[] args)
    {
        Aplicacao p = new Aplicacao();
        p.iniciarArquivos();
        p.gerarTabela();
        p.exibirTabela();
        
        
        //ESTA DELETANDO A TABELA FINAL (CUIDADO)
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
}
