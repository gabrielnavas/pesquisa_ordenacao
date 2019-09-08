package algoritmos_ordenacao.arquivo;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import static utils.UtilApp.AlgoritmoNum;
import utils.UtilApp.ArquivoParams;
import utils.UtilApp.TipoOrdenado;
import utils.UtilApp.ExibirDados;


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
        tabela = new Arquivo("tabela" + new Date().toString()+ ".dat");
    }
    
    private void gerarDadosArquivos(String nameAlg)
    {
        //nao muda o nome, to dando split("|") pra exibir :P
        arqOrd = new Arquivo(nameAlg  + "| arqOrd.bin");
        arqRev = new Arquivo(nameAlg  + "| arqRev.bin");
        arqRand = new Arquivo(nameAlg + "| arqRand.bin");
        auxRev = new Arquivo(nameAlg  + "| auxRev.bin");
        auxRand = new Arquivo(nameAlg + "| auxRand.bin");
        
        arqOrd.geraArquivoOrdenado();
        if(ExibirDados.EXIBIR_ARQUIVO) 
            arqOrd.exibirArq();
        
        arqRev.geraArquivoReverso();
        if(ExibirDados.EXIBIR_ARQUIVO) 
            arqRev.exibirArq();
        
        arqRand.geraArquivoRandomico();
        if(ExibirDados.EXIBIR_ARQUIVO) 
            arqRand.exibirArq();
    }
    
    private void deletarArquivos(String nomeAlg)
    {
        if(ArquivoParams.DELETA_ARQUIVOS_DADOS)
        {
            arqOrd.close();
            arqRev.close();
            arqRand.close();
            auxRev.close();
            auxRand.close();
            
            //nao muda o nome, to dando split("|") pra exibir :P
            new File(nomeAlg + "| arqOrd.bin").delete();
            new File(nomeAlg + "| arqRev.bin").delete();
            new File(nomeAlg + "| arqRand.bin").delete();
            new File(nomeAlg + "| arqRev.bin").delete();
            new File(nomeAlg + "| arqRand.bin").delete();
        }
    }
    
    private void gravaLinhaTabela( String algoritmo,
            int compProgOrd, String compEquaOrd, int movProgOrd, String movEquaOrd, long tempoOrd,
            int compProgRev, String compEquaRev, int movProgRev, String movEquaRev, long tempoRev, 
            int compProgRand,String compEquaRand,int movProgRand,String movEquaRand,long tempoRand
    )
    {
        RandomAccessFile tabela = this.tabela.getArquivo();
        algoritmo = ArquivoParams.gerarStringTamanho(20, algoritmo);
        
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
    
    private String calculaCompInsDir(int n, int tipoOrdenacao)
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
    
    private String calculaMovInsDir(int n, int tipoOrdenacao)
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
    
    private String calculaCompInsBin(int n, int tipoOrdenacao)
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
    
    private String calculaMovInsBin(int n, int tipoOrdenacao)
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
    
    private String calculaCompSeleDir(int n, int tipoOrdenacao)
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
    
    private String calculaMovSeleDir(int n, int tipoOrdenacao)
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
    
    private String calculaCompBolha(int n, int tipoOrdenacao)
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
    
    private String calculaMovBolha(int n, int tipoOrdenacao)
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
    
    private String calculaCompShake(int n, int tipoOrdenacao)
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
    
    private String calculaMovShake(int n, int tipoOrdenacao)
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
    
    
    
    private void linha(String nomeOrd, int algNum)
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //gera arquivos;
        gerarDadosArquivos(nomeOrd);
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        
        switch(algNum)
        {
            case AlgoritmoNum.INSERCAO_DIRETA:  arqOrd.insercaoDireta();break;
            case AlgoritmoNum.INSERCAO_BINARIA: arqOrd.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA:   arqOrd.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA:            arqOrd.bolha(); break;
            case AlgoritmoNum.SHAKE:            arqOrd.shake(); break;
            case AlgoritmoNum.SHELL:            arqOrd.shell(); break;
            case AlgoritmoNum.HEAP:             arqOrd.heap(); break;
            case AlgoritmoNum.QUICKCP:          arqOrd.quickCP(); break;
            case AlgoritmoNum.QUICKSP:          arqOrd.quickSP(); break;
            case AlgoritmoNum.MERGE1:           arqOrd.merge1(); break;
            case AlgoritmoNum.MERGE2:           arqOrd.merge2(); break;
            case AlgoritmoNum.COUNTING:         arqOrd.counting(); break;
            case AlgoritmoNum.BUCKET:           arqOrd.bucket(); break;
            case AlgoritmoNum.RADIX:            arqOrd.radix(); break;
            case AlgoritmoNum.COMB:             arqOrd.comb(); break;
            case AlgoritmoNum.GNOME:            arqOrd.gnome(); break;
            case AlgoritmoNum.TIM:              arqOrd.tim(); break;
            
            default: System.out.println("Algoritmo não existe.");
        }
        
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        if(ExibirDados.EXIBIR_ARQUIVO) 
            auxRev.exibirArq();
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        
        switch(algNum)
        {
            case AlgoritmoNum.INSERCAO_DIRETA: auxRev.insercaoDireta();break;
            case AlgoritmoNum.INSERCAO_BINARIA: auxRev.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA: auxRev.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA: auxRev.bolha(); break;
            case AlgoritmoNum.SHAKE: auxRev.shake(); break;
            case AlgoritmoNum.SHELL: auxRev.shell(); break;
            case AlgoritmoNum.HEAP: auxRev.heap(); break;
            case AlgoritmoNum.QUICKCP: auxRev.quickCP(); break;
            case AlgoritmoNum.QUICKSP: auxRev.quickSP(); break;
            case AlgoritmoNum.MERGE1: auxRev.merge1(); break;
            case AlgoritmoNum.MERGE2: auxRev.merge2(); break;
            case AlgoritmoNum.COUNTING: auxRev.counting(); break;
            case AlgoritmoNum.BUCKET: auxRev.bucket(); break;
            case AlgoritmoNum.RADIX: auxRev.radix(); break;
            case AlgoritmoNum.COMB: auxRev.comb(); break;
            case AlgoritmoNum.GNOME: auxRev.gnome(); break;
            case AlgoritmoNum.TIM: auxRev.tim(); break;
            
            default: System.out.println("Algoritmo não existe.");
        }
        
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        if(ExibirDados.EXIBIR_ARQUIVO) 
            auxRev.exibirArq();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        
        switch(algNum)
        {
            case AlgoritmoNum.INSERCAO_DIRETA: auxRand.insercaoDireta();break;
            case AlgoritmoNum.INSERCAO_BINARIA: auxRand.insercaoBinaria(); break;
            case AlgoritmoNum.SELECAO_DIRETA: auxRand.selecaoDireta(); break;
            case AlgoritmoNum.BOLHA: auxRand.bolha(); break;
            case AlgoritmoNum.SHAKE: auxRand.shake(); break;
            case AlgoritmoNum.SHELL: auxRand.shell(); break;
            case AlgoritmoNum.HEAP: auxRand.heap(); break;
            case AlgoritmoNum.QUICKCP: auxRand.quickCP(); break;
            case AlgoritmoNum.QUICKSP: auxRand.quickSP(); break;
            case AlgoritmoNum.MERGE1: auxRand.merge1(); break;
            case AlgoritmoNum.MERGE2: auxRand.merge2(); break;
            case AlgoritmoNum.COUNTING: auxRand.counting(); break;
            case AlgoritmoNum.BUCKET: auxRand.bucket(); break;
            case AlgoritmoNum.RADIX: auxRand.radix(); break;
            case AlgoritmoNum.COMB: auxRand.comb(); break;
            case AlgoritmoNum.GNOME: auxRand.gnome(); break;
            case AlgoritmoNum.TIM: auxRand.tim(); break;
            
            default: System.out.println("Algoritmo não existe.");
        }
        
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        if(ExibirDados.EXIBIR_ARQUIVO) 
            auxRev.exibirArq();
        
        
        gravaLinhaTabela(
            "Seleção Direta",    
                
            compO,
            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
            movO,
            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.ORDENADO),
            ttotalO,
            
            compRev,
            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
            movRev,
            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.REVERSO),
            ttotalRev,
            
            compRand,
            calculaCompInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
            movRand,
            calculaMovInsDir(arqOrd.fileSize(), TipoOrdenado.RANDOMICO),
            ttotalRand
        );  
        
        deletarArquivos(nomeOrd);
    }
    
    public void geraTabela()
    {
        String[] algoritmosNames = new String[]{
            "Inserção Direta", 
            "Inserção Binária", 
            "Seleção Direta",
            "Bolha", 
            "Shake", 
            "Shell", 
            "Heap", 
            "Quick s/ Pivô", 
            "Quick c/ Pivô",
            "Merge 1 com fusão 1",
            "Merge 2 com fusão 2",
            "Counting", 
            "Bucket", 
            "Radix", 
            "Comb", 
            "Gnome", 
            "Tim"
        };
        
        for(int i=0 ; i < algoritmosNames.length ; i++)
            this.linha(algoritmosNames[i], i);
    }
                
    public static void main(String[] args)
    {
        Aplicacao p = new Aplicacao();
        p.geraTabela();
        p.exibirTabela();
    }
}
