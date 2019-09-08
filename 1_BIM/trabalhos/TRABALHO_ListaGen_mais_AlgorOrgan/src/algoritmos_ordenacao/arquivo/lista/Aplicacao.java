package algoritmos_ordenacao.arquivo.lista;

import algoritmos_ordenacao.arquivo.Arquivo;
import java.io.File;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class Aplicacao 
{    
    private Arquivo arqOrd;
    private Arquivo arqRev;
    private Arquivo arqRand;
    private Arquivo auxRev;
    private Arquivo auxRand;
    
    private int compO, movO,  compRev, movRev, compRand, movRand;
    private long ttotal0, ttotalRev, tini, tfim, ttotalO, ttotalRand;
        
    public Aplicacao()
    {
        this.deletarArquivos();
        
        arqOrd = new Arquivo("arqOrd.bin");
        arqRev = new Arquivo("arqRev.bin");
        arqRand = new Arquivo("arqRand.bin");
        auxRev = new Arquivo("arqRev.bin");
        auxRand = new Arquivo("arqRand.bin");
        
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
    
    private void linhaInsercaoDireta()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaSelecaoDireta()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaInsercaoBinaria()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaBolha()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaShake()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaShell()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaHeap()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaQuickCP()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaQuickSP()
    {
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.QuickSP();
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
        auxRev.QuickSP();
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
        auxRand.QuickSP();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaMerge1()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaMerge2()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaCounting()
    {
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.counting();
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
        auxRev.counting();
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
        auxRand.counting();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaBucket()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaRadix()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaComb()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaGnome()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
    }
    
    private void linhaTim()
    {
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
        
        
        gravaLinhaTabela(compO,
            calculaCompInsDir(filesize()),
            movO,
            calculaMovInsDir(filesize()),
            ttotalO, //tempo execução no arquivo Ordenado já convertido
        //para segundos

        );
    //... fim Insercao Direta
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
                
                
    //e assim continua para os outros métodos de ordenacao!!!
    
    public static void main(String[] args)
    {
        Aplicacao p = new Aplicacao();
        p.geraTabela();
    }
}
