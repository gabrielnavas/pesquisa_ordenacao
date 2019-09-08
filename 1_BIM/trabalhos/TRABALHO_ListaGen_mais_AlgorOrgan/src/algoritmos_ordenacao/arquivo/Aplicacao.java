package algoritmos_ordenacao.arquivo;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class TipoOrdenado
{
    public static final int ORDENADO=0;
    public static final int REVERSO=1;
    public static final int RANDOMICO=2;       
}

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
        arqOrd = new Arquivo("arqOrd.bin");
        arqRev = new Arquivo("arqRev.bin");
        arqRand = new Arquivo("arqRand.bin");
        auxRev = new Arquivo("arqRev.bin");
        auxRand = new Arquivo("arqRand.bin");
        tabela = new Arquivo("tabela.dat");
        
        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();
    }
    
    private void deletarArquivos()
    {
        new File("arqOrd.bin").delete();
        new File("arqRev.bin").delete();
        new File("arqRand.bin").delete();
        new File("arqRev.bin").delete();
        new File("arqRand.bin").delete();
    }
    
    private String tratarNomeAlgoritmo(String algoritmo)
    {
        //trata tamanho real da string para o arquivo
        
        char[] algoritmoName = new char[20];
        
        for(int i=0 ; i < algoritmoName.length ; i++)
            algoritmoName[i] = ' ';
        
        for(int i=0 ; i < algoritmo.length() ; i++)
            algoritmoName[i] = algoritmo.charAt(i);
        
        algoritmo = String.copyValueOf(algoritmoName);
        
        return algoritmo;
    }
    
    private void gravaLinhaTabela( String algoritmo,
            int compProgOrd, String compEquaOrd, int movProgOrd, String movEquaOrd, long tempoOrd,
            int compProgRev, String compEquaRev, int movProgRev, String movEquaRev, long tempoRev, 
            int compProgRand,String compEquaRand,int movProgRand,String movEquaRand,long tempoRand
    )
    {
        RandomAccessFile tabela = this.tabela.getArquivo();
        algoritmo = tratarNomeAlgoritmo(algoritmo);
        
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
    
    private void linhaInsercaoDireta()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.insercaoDireta();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.insercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.insercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Insercao Direta",    
                
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
    }
    
    private void linhaInsercaoBinaria()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.insercaoBinaria();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.insercaoBinaria();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.insercaoBinaria();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Insercao Binária",    
                
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
    }
    
    private void linhaSelecaoDireta()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.selecaoDireta();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.selecaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.selecaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
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
    }
    
    
    
    private void linhaBolha()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.bolha();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.bolha();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.bolha();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Bolha",    
                
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
    }
    
    private void linhaShake()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.shake();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.shake();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.shake();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Shake",    
                
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
    }
    
    private void linhaShell()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.shell();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.shell();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.shell();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Shell",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        );  
    }
    
    private void linhaHeap()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.heap();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.heap();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.heap();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Heap",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        );  
    }
    
    private void linhaQuickCP()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.quickCP();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.quickCP();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.quickCP();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Quick c/ pivô",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        );  
    }
    
    private void linhaQuickSP()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.quickSP();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.quickSP();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.quickSP();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Quick s/ pivô",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    private void linhaMerge1()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.merge1();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.merge1();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.merge1();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Merge 1a Implement",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    private void linhaMerge2()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.merge2();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.merge2();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.merge2();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Merge 2a Implement",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    private void linhaCounting()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
//        arqOrd.counting();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
//        auxRev.counting();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
//        auxRand.counting();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Counting",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    private void linhaBucket()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.bucket();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.bucket();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.bucket();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Bucket",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    private void linhaRadix()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.radix();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.radix();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.radix();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Radix",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    private void linhaComb()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.comb();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.comb();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.comb();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Comb",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    private void linhaGnome()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.gnome();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.gnome();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.gnome();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Gnome",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    private void linhaTim()
    {
        int compO, movO,  compRev, movRev, compRand, movRand;
        long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.tim();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getArquivo()); // faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.tim();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getArquivo()); // faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.tim();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(
            "Tim",    
                
            compO,
            "0.00",
            movO,
            "0.00",
            ttotalO,
            
            compRev,
            "0.00",
            movRev,
            "0.00",
            ttotalRev,
            
            compRand,
            "0.00",
            movRand,
            "0.00",
            ttotalRand
        ); 
    }
    
    public void geraTabela()
    {
        this.linhaInsercaoDireta();
        this.linhaInsercaoBinaria();
        this.linhaSelecaoDireta();
        this.linhaBolha();
        this.linhaShake();
        this.linhaShell();
        this.linhaHeap();
        this.linhaQuickCP();
        this.linhaQuickSP();
        this.linhaMerge1();;
        this.linhaMerge2();
        this.linhaCounting();
        this.linhaBucket();
        this.linhaRadix();
        this.linhaComb();
        this.linhaGnome();
        this.linhaTim();
    }
                
    public static void main(String[] args)
    {
        Aplicacao p = new Aplicacao();
//        p.deletarArquivos();
        p.geraTabela();
    }
}