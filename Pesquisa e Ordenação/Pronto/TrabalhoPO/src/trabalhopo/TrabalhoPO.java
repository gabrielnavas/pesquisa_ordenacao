package trabalhopo;

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
        inicia_tabela();
        
        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();

        long tini, tfim, ttotalO, ttotalRev, ttotalRand;
        int compO, movO, compRev, movRev, compRand, movRand;

        //... Insercao Direta ... 
  
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.insercao_direta();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.insercao_direta();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
        
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.insercao_direta();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(
                compO, 
                calculaCompInsDir(arqOrd.filesize(),1), 
                movO, 
                calculaMovInsDir(arqOrd.filesize(), 1), 
                ttotalO, 
                
                compRev, 
                calculaCompInsDir(arqRev.filesize(),2), 
                movRev, 
                calculaMovInsDir(arqRev.filesize(),2), 
                ttotalRev, 
                
                compRand, 
                calculaCompInsDir(arqRand.filesize(),3), 
                movRand, 
                calculaMovInsDir(arqRand.filesize(),3), 
                ttotalRand, "Insercao Direta"
        );
//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Insercao Binaria
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.insercao_binaria();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.insercao_binaria();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.insercao_binaria();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, calculaCompInsBin(arqOrd.filesize()), movO, calculaMovInsBin(arqOrd.filesize(), 1), ttotalO, compRev, calculaCompInsBin(arqRev.filesize()), movRev, calculaMovInsBin(arqRev.filesize(),2), ttotalRev, compRand, calculaCompInsBin(arqRand.filesize()), movRand, calculaMovInsBin(arqRand.filesize(),3), ttotalRand, "Insercao Binaria");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Selecao direta
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.selecao_direta();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.selecao_direta();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.selecao_direta();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, calculaCompSelDir(arqOrd.filesize()), movO, calculaMovSelDir(arqOrd.filesize(), 1), ttotalO, compRev, calculaCompSelDir(arqRev.filesize()), movRev, calculaMovSelDir(arqRev.filesize(),2), ttotalRev, compRand, calculaCompSelDir(arqRand.filesize()), movRand, calculaMovSelDir(arqRand.filesize(),3), ttotalRand, "Selecao Direta");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        // Bolha
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.bolha();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.bolha();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo()); 
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.bolha();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, calculaCompBubbleShake(arqOrd.filesize()), movO, calculaMovBubbleShake(arqOrd.filesize(), 1), ttotalO, compRev, calculaCompBubbleShake(arqRev.filesize()), movRev, calculaMovBubbleShake(arqRev.filesize(),2), ttotalRev, compRand, calculaCompBubbleShake(arqRand.filesize()), movRand, calculaMovBubbleShake(arqRand.filesize(),3), ttotalRand, "Bolha");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Shake
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.shake();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.shake();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.shake();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, calculaCompBubbleShake(arqOrd.filesize()), movO, calculaMovBubbleShake(arqOrd.filesize(), 1), ttotalO, compRev, calculaCompBubbleShake(arqRev.filesize()), movRev, calculaMovBubbleShake(arqRev.filesize(),2), ttotalRev, compRand, calculaCompBubbleShake(arqRand.filesize()), movRand, calculaMovBubbleShake(arqRand.filesize(),3), ttotalRand, "Bolha");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Shell
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.shell();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.shell();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.shell();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(
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
                ttotalRand, 
                "Shell");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Heap
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.heap();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.heap();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.heap();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Heap");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Quick sem pivo
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.quick_sem_pivo(0, arqOrd.filesize()-1);
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo()); 
                                              
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.quick_sem_pivo(0, auxRev.filesize()-1);
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.quick_sem_pivo(0, auxRand.filesize()-1);
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Quick s/ pivo");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Quick com pivo
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.quick_com_pivo(0, arqOrd.filesize()-1);
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                              
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.quick_com_pivo(0,auxRev.filesize()-1);
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo()); 
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.quick_com_pivo(0,auxRand.filesize()-1);
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Quick c/ pivo");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        // Merge 1
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis(); 
        arqOrd.fusao_direta_merge1();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo()); 
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.fusao_direta_merge1();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo()); 
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.fusao_direta_merge1();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Merge 1");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        // Merge 2
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis();
        arqOrd.fusao_direta_merge2();
        tfim=System.currentTimeMillis(); 
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                              
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.fusao_direta_merge2();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.fusao_direta_merge2();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Merge 2");//tempo execução no arquivo Ordenado já convertido para segundos  
     
        
        // Counting
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis(); 
        arqOrd.counting();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo()); 
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.counting();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo()); 
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.counting();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Counting");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Bucket
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis(); 
        arqOrd.bucket();
        tfim=System.currentTimeMillis(); 
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo()); 
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.bucket();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.bucket();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Bucket");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Radix
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis(); 
        arqOrd.radix();
        tfim=System.currentTimeMillis(); 
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo()); 
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.radix();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo()); 
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.radix();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, 
                "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", 
                movRand, "0.00", ttotalRand, "Radix");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Comb
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis(); 
        arqOrd.comb();
        tfim=System.currentTimeMillis(); 
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo());
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.comb();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());
                                                
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.comb();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Comb");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Gnome
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis(); 
        arqOrd.gnome();
        tfim=System.currentTimeMillis();
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo()); 
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.gnome();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo()); 
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.gnome();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Gnome");//tempo execução no arquivo Ordenado já convertido para segundos  
        
        
        //Tim
        arqOrd.initComp();
        arqOrd.initMov();
        tini=System.currentTimeMillis(); 
        arqOrd.tim();
        tfim=System.currentTimeMillis(); 
        compO=arqOrd.getComp();
        movO=arqOrd.getMov();
        ttotalO=tfim-tini; 

        auxRev.copiaArquivo(arqRev.getArquivo()); 
                                               
        auxRev.initComp();
        auxRev.initMov();
        tini=System.currentTimeMillis();
        auxRev.tim();
        tfim=System.currentTimeMillis();
        ttotalRev=tfim-tini;
        compRev=auxRev.getComp();
        movRev= auxRev.getMov(); 

        auxRand.copiaArquivo(arqRand.getArquivo());   
                                                 
        auxRand.initComp();
        auxRand.initMov();
        tini=System.currentTimeMillis(); 
        auxRand.tim();
        tfim=System.currentTimeMillis();
        ttotalRand=tfim-tini;
        compRand=auxRand.getComp();
        movRand=auxRand.getMov();    
        
        gravaLinhaTabela(compO, "0.00", movO, "0.00", ttotalO, compRev, "0.00", movRev, "0.00", ttotalRev, compRand, "0.00", movRand, "0.00", ttotalRand, "Tim");//tempo execução no arquivo Ordenado já convertido para segundos
    }
    
    public String calculaCompInsDir(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return String.format("%.2f", tamanho - 1.0);
        }
        else
            if(tipo == 2)
            {
                return String.format("%.2f",(Math.pow(tamanho, 2) +(tamanho - 2)) / 4);
            }
            else
            {
                return String.format("%.2f", (Math.pow(tamanho, 2) +(tamanho - 4)) / 4);
            }
    }
    
    public String calculaMovInsDir(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return String.format("%.2f", 3 * (tamanho - 1.0));
        }
        else
            if(tipo == 2)
            {
                return String.format("%.2f", (Math.pow(tamanho, 2) +(tamanho * 9) -  10) / 4);
            }
            else
            {
                return String.format("%.2f", (Math.pow(tamanho, 2) +(tamanho * 3) - 4) / 2);
            }
    }

    public String calculaCompInsBin(int tamanho)
    {
        return  String.format("%.2f", tamanho * (Math.log(tamanho) - Math.log(2.71828) + 0.5));
    }
    
    public String calculaMovInsBin(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return String.format("%.2f", 3 * (tamanho - 1.0));
        }
        else
            if(tipo == 2)
            {
                return String.format("%.2f", (Math.pow(tamanho, 2) +(tamanho * 9) -  10) / 4);
            }
            else
            {
                return String.format("%.2f", (Math.pow(tamanho, 2) +(tamanho * 3) - 4) / 2);
            }
    }
    
    public String calculaCompSelDir(int tamanho)
    {
        return String.format("%.2f", (Math.pow(tamanho, 2)  -  tamanho) / 2);
    }
    
    public String calculaMovSelDir(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return String.format("%.2f", 3 * (tamanho - 1.0));
        }
        else
            if(tipo == 2)
            {
                return String.format("%.2f", Math.pow(tamanho, 2) / 4 + 3 * (tamanho - 1));
            }
            else
            {
                return String.format("%.2f", tamanho * (tamanho * Math.log(tamanho) + 0.577216));
            }
    }
    
    public String calculaCompBubbleShake(int tamanho)
    {
        return String.format("%.2f", (Math.pow(tamanho, 2)  -  tamanho) / 2);
    }
    
    public String calculaMovBubbleShake(int tamanho, int tipo)
    {
        if(tipo == 1)
        {
            return String.format("%.2f", 0.0);
        }
        else
            if(tipo == 2)
            {
                return String.format("%.2f", 3 * (Math.pow(tamanho, 2) - tamanho) / 4);
            }
            else
            {
                return String.format("%.2f", 3 * (Math.pow(tamanho, 2) - tamanho) / 2);
            }
    }
    
    public void inicia_tabela()
    {
        try
        {
            tabela.getArquivo().writeBytes("Metodos de ordenacao"+"\t\t\t"+"Arquivo Ordenado"+
                        "\t\t\t"+"Arquivo em Ordem Reversa"+"\t\t\t"+"Arquivo Randomico"+"\n");
                tabela.getArquivo().writeBytes("\t\t\t"+"Comp.Prog. Comp.Equa. Mov.Prog. Mov.Equa. "
                        +"Tempo|Comp.Prog. Comp.Equa. Mov.Prog. Mov.Equa. Tempo|Comp.Prog. Comp."
                        + "Equa. Mov.Prog. Mov.Equa. Tempo "+"\n");
        }
        catch(Exception e)
        {
            
        } 
    }
    
    public void gravaLinhaTabela(int compO, String calc_compO, int movO, String calc_movO, long ttotalO, 
                                 int compRev, String calc_compRe, int movRev, String calc_movRe, long ttotalRev,
                                 int compRand, String calc_compRa, int movRand, String calc_movRa, long ttotalRand,
                                 String ordenacao)
            
    {
        
        try
        {
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
        System.out.println("FEITO!");
    } 
}
