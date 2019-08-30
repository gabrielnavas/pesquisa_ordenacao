package trabalhopo;

import java.io.IOException;

/*
    Andressa Hisae Tsukasaki    R.A. 261742078
    Laura Lopes Stipsky         R.A. 261741578
*/

public class TrabalhoPO {
    
    Arquivo arqOrd = new Arquivo("arqOrdenado.dat");
    Arquivo arqRev = new Arquivo("arqReverso.dat");
    Arquivo arqRand = new Arquivo("arqRandomico.dat");
    Arquivo tabela = new Arquivo("tabela.dat");
    Arquivo auxRev = new Arquivo("revCopia.dat");
    Arquivo auxRand = new Arquivo("randCopia.dat"); 

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
        
        arqOrd.exibirArq();

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
        auxRev.exibirArq();

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
        auxRand.exibirArq();
        //grava na tabela informacoes os dados extraídos das execucoes do método
        //Insercao Direta
        gravaLinhaTabela(compO, calculaCompInsDir(arqOrd.filesize(),1), movO, calculaMovInsDir(arqOrd.filesize(), 1), ttotalO, compRev, calculaCompInsDir(arqRev.filesize(),2), movRev, calculaMovInsDir(arqRev.filesize(),2), ttotalRev, compRand, calculaCompInsDir(arqRand.filesize(),3), movRand, calculaMovInsDir(arqRand.filesize(),3), ttotalRand, "Insercao Direta");//tempo execução no arquivo Ordenado já convertido para segundos  
        //... fim Insercao Direta 
        //e assim continua para os outros métodos de ordenacao!!! 
    }
    
    public double calculaCompInsDir(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return tamanho - 1;
        }
        else
            if(tipo == 2)
            {
                return (Math.pow(tamanho, 2) +(tamanho - 2)) / 4;
            }
            else
            {
                return (Math.pow(tamanho, 2) +(tamanho - 4)) / 4;
            }
    }
    
    public double calculaMovInsDir(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return 3 * (tamanho - 1);
        }
        else
            if(tipo == 2)
            {
                return (Math.pow(tamanho, 2) +(tamanho * 9) -  10) / 4;
            }
            else
            {
                return (Math.pow(tamanho, 2) +(tamanho * 3) - 4) / 2;
            }
    }

    public double calculaCompInsBin(int tamanho)
    {
        return  tamanho * (Math.log(tamanho) - Math.log(2.71828) +- 0.5);
    }
    
    public double calculaMovInsBin(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return 3 * (tamanho - 1);
        }
        else
            if(tipo == 2)
            {
                return (Math.pow(tamanho, 2) +(tamanho * 9) -  10) / 4;
            }
            else
            {
                return (Math.pow(tamanho, 2) +(tamanho * 3) - 4) / 2;
            }
    }
    
    public double calculaCompSelDir(int tamanho)
    {
        return (Math.pow(tamanho, 2)  -  tamanho) / 2;
    }
    
    public double calculaMovSelDir(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return 3 * (tamanho - 1);
        }
        else
            if(tipo == 2)
            {
                return Math.pow(tamanho, 2) / 4 + 3 * (tamanho - 1);
            }
            else
            {
                //return (Math.pow(tamanho, 2) +(tamanho * 3) - 4) / 2;
            }
    }
    
    public void gravaLinhaTabela(int compO, double calc_compO, int movO, double calc_movO, long ttotalO, 
                                 int compRev, double calc_compRe, int movRev, double calc_movRe, long ttotalRev,
                                 int compRand, double calc_compRa, int movRand, double calc_movRa, long ttotalRand,
                                 String ordenacao)
            
    {
        
        try
        {
            if(tabela.filesize() == 0)
            {
                tabela.getArquivo().writeBytes("Metodos de ordenacao"+"\t\t\t"+"Arquivo Ordenado"+
                        "\t\t\t"+"Arquivo em Ordem Reversa"+"\t\t\t"+"Arquivo Randomico"+"\n");
                tabela.getArquivo().writeBytes("\t\t\t"+"Comp.Prog. Comp.Equa. Mov.Prog. Mov.Equa. "
                        +"Tempo|Comp.Prog. Comp.Equa. Mov.Prog. Mov.Equa. Tempo|Comp.Prog. Comp."
                        + "Equa. Mov.Prog. Mov.Equa. Tempo "+"\n");
            }
            tabela.getArquivo().writeBytes(ordenacao+"\t\t"+compO+"\t\t"+calc_compO+"\t"+movO+"\t"+calc_movO
                        +"\t"+ttotalO+"|\t"+compRev+"\t\t"+calc_compRe+"\t"+movRev+"\t"+calc_movRe+"\t"+ttotalRev
                        +"|\t"+compRand+"\t\t"+calc_compRa+"\t"+movRand+"\t"+calc_movRa+"\t"+ttotalRand+"\n");
        }
        catch(Exception e)
        {
            
        } 
    }

    public static void main(String args[])
    {
        TrabalhoPO p = new TrabalhoPO();
        p.geraTabela();
        
        //-----TESTE ARQUIVO----------------------------
        
        /*Arquivo a = new Arquivo("arquivo.dat");
        
        a.leArq();
        a.exibirArq();
        
        a.gnome();
        a.exibirArq();*/
        
        //------TESTE LISTA---------------------------------
        
        /*ListaEncad lista = new ListaEncad();
        lista.inicializa();
        lista.inserirNoFinal(2);
        lista.inserirNoFinal(3);
        lista.inserirNoFinal(1);
        lista.exibir();
        
        lista.shell();
        lista.exibir();*/
    } 
}
