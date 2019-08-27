package in_arquivo;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;

class Entrada
{
    public static String leString(String msg)
    {
        String line = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try
        {
            System.out.println(msg);
            line = br.readLine();
        } catch (Exception e)
        {  }
        return line;
    }

    public static int leInteger(String msg)
    {
        String line = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try
        {
            System.out.println(msg);
            line = br.readLine();
            int retorno = Integer.valueOf(line);
            return retorno;
        } catch (Exception e)
        {  return -1; }
    }
}

class Registro
{
    public final int tf = 20;//variavel do tipo "final" � constante
    private int codigo, tl = tf, idade;
    private char nome[] = new char[tf];

    public Registro()
    {
    }

    public Registro(int codigo, String Snome, int idade)
    {
        this.codigo = codigo; //this � variavel de estancia
        this.idade = idade;
        this.tl = Snome.length();
        for (int i = 0; i < Snome.length(); i++)
        {
            nome[i] = Snome.charAt(i);
        }
    }

    public int getCodigo()
    {
        return (codigo);
    }

    public String getNome()
    {
        String Snome = new String(nome);
        return (Snome);
    }

    public int getIdade() {
        return idade;
    }
    
    
    
    public void gravaNoArq(RandomAccessFile arquivo)
    {
        try
        {
            arquivo.writeInt(codigo);
            arquivo.writeInt(idade);
            arquivo.writeInt(tl);
            for (int i = 0; i < tf; i++)
            {
                arquivo.writeChar(nome[i]);
            }
        } catch (IOException e)
        { }
    }

    public void leDoArq(RandomAccessFile arquivo)
    {
        try
        {
            this.codigo = arquivo.readInt();
            this.idade = arquivo.readInt();
            this.tl = arquivo.readInt();
            for (int i = 0; i < this.tf; i++)
                this.nome[i] = arquivo.readChar();
            for (int i = tl; i < tf; i++)
                this.nome[i] = ' ';
        } catch (IOException e)
        { }
    }

    public void exibirReg()
    {
        int i;
        System.out.print("[" + codigo + "] ");
//        System.out.print("codigo....." + this.codigo);
//        System.out.print(" nome.......");
//        String Snome = new String(nome);
//        System.out.print(Snome);
//        System.out.println(" idade......." + this.idade);
//        System.out.println("----------------------------------");
    }

    static int length()
    {
        return (52); //int codigo, tl=20, idade; ------------> 12 bytes
        //private char nome[] = new char[20]; --> 40 bytes
        //------------------------------------------------- +
        //                      Total : 40 + 12 = 52 bytes
    }
}


class Arquivo
{
    private String nomearquivo;
    private RandomAccessFile arquivo;

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        { }
    }

    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc)
        { }
    }

    public boolean eof()  
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;                               
        } catch (IOException e)
        { }
        return (retorno);
    }

    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(fileSize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void exibirArq()
    {
        int i;
        Registro reg = new Registro();
        seekArq(0);
        
        while (!this.eof())
        {
            reg.leDoArq(arquivo);
            reg.exibirReg();
        }
        
        System.out.println();
    }

    public void exibirUmRegistro(int pos)
    {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
        aux.exibirReg();
    }

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }

    public int fileSize() {
        int registros = 0,
            totalBytes = 0;
        
        try 
        {
            totalBytes = (int) arquivo.length();
            registros = totalBytes / Registro.length();
        } 
        catch (IOException ex) {}
        
        return (registros);
    }
    
    public void lerArq()
    {
        int codigo, idade;
        String nome;
        codigo = Entrada.leInteger("Digite o código");
        while (codigo != 0)
        {
            nome = Entrada.leString("Digite o nome");
            idade = Entrada.leInteger("Digite a idade");
            inserirRegNoFinal(new Registro(codigo, nome, idade));
            codigo = Entrada.leInteger("Digite o c�digo");
        }
    }

    public RandomAccessFile getArquivo() {
        return arquivo;
    }
    
    public void setSeekArq(int pos)
    {
        seekArq(pos);
    }
    
    //.............................................................................
    /*

    insira aqui os métodos de Ordenação;

    */
    
    public void insercaoDireta()
    {
        Registro regI = new Registro(), 
                 regAux = new Registro(),
                 regIAnt = new Registro();
        
        int i, pos, tl;
        
        tl = fileSize();
        
        i=1;
        while(i < tl)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            seekArq(i);
            regAux.leDoArq(arquivo);
            
            seekArq(i-1);
            regIAnt.leDoArq(arquivo);
            
            pos=i;
            while(pos > 0 && regIAnt.getCodigo() > regAux.getCodigo() )
            {
                seekArq(pos);
                regIAnt.gravaNoArq(arquivo);
                pos--;
                
                if(pos > 0)
                {
                    seekArq(pos-1);
                    regIAnt.leDoArq(arquivo);
                }
            }
            
            seekArq(pos);
            regAux.gravaNoArq(arquivo);
            
            i++;
        }
    }
    
    public void insercaoBinaria()
    {
        
    }
    
    public void selecaoDireta()
    {
        Registro regI = new Registro(),
                 regJ = new Registro(),
                 regMenor = new Registro();
        
        int menor, 
            posMenor,
            i, j, tl;
        
        tl=fileSize();
        
        i=0;
        while(i < tl-1)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            posMenor=i;
            menor=regI.getCodigo();
            
            j=i+1;
            while(j < tl)
            {
                seekArq(j);
                regJ.leDoArq(arquivo);
                
                if(regJ.getCodigo() < menor)
                {
                    posMenor = j;
                    menor = regJ.getCodigo();
                }
                j++;
            }
            
            seekArq(posMenor);
            regMenor.leDoArq(arquivo); //tem que ler o reg inteiro, pq tem mais de um atributo
            
            seekArq(posMenor);
            regI.gravaNoArq(arquivo);
            
            seekArq(i);
            regMenor.gravaNoArq(arquivo);
            
            i++;
        }
    }
    
    public void bolha()
    {
        int tl = fileSize();
        Registro regi = new Registro();
        Registro regj = new Registro();
        
        while(tl > 1)
        {
            for(int i=0 ; i < tl-1 ; i++)
            {
                seekArq(i);
                regi.leDoArq(arquivo);
                regj.leDoArq(arquivo);
                if(regi.getCodigo() > regj.getCodigo())
                {
                    seekArq(i);
                    regj.gravaNoArq(arquivo);
                    regi.gravaNoArq(arquivo);
                }
            }
            
            tl--;
        }
    }
    
    public void shake()
    {
        int inicio2, fim2, i;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        inicio2=0 ;
        fim2=fileSize();
        
        while(inicio2 < fim2)
        {
            for(i=inicio2 ; i < fim2 ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                regJ.leDoArq(arquivo);
                
                if(regI.getCodigo() > regJ.getCodigo())
                {
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    regI.gravaNoArq(arquivo);
                }
            }
            fim2--;
            
            for(i=fim2 ; i > inicio2 ; i--)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                
                seekArq(i-1);
                regJ.leDoArq(arquivo);
                
                if(regI.getCodigo() < regJ.getCodigo())
                {
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    
                    seekArq(i-1);
                    regI.gravaNoArq(arquivo);
                }
            }
            
            if(inicio2<fim2)
                inicio2++;
        }
    }
    
    public void shell()
    {
        int i, j, k, tl, dist=4;

        Registro regJ=new Registro(),
                 regK=new Registro(),
                 regDist=new Registro();

        tl=fileSize();

        while(dist > 0)
        {
            for(i=0 ; i < dist ; i++)
                for(j=i ; j+dist < tl ; j+= dist)
                {
                    seekArq(j);
                    regJ.leDoArq(arquivo);

                    seekArq(j+dist);
                    regDist.leDoArq(arquivo);

                    if(regJ.getCodigo() > regDist.getCodigo())
                    {
                        seekArq(j);
                        regDist.gravaNoArq(arquivo);
                        seekArq(j+dist);
                        regJ.gravaNoArq(arquivo);
                    }

                    
                    seekArq(j);
                    regK.leDoArq(arquivo);
                    seekArq(j-dist);
                    regDist.leDoArq(arquivo);
                    
                    for(k=j ; k-dist >= i && regK.getCodigo() < regDist.getCodigo() ; k-=dist)
                    {
                        seekArq(k);
                        regK.leDoArq(arquivo);

                        seekArq(k-dist);
                        regDist.leDoArq(arquivo);
                        
                        seekArq(k);
                        regDist.gravaNoArq(arquivo);
                        seekArq(k-dist);
                        regK.gravaNoArq(arquivo);
                    }
                }

            dist = dist /2;
        }
    }

    public void quickSP()
    {
        quickSP(0, fileSize()-1);
    }
    
    public void quickSP(int ini, int fim)
    {
        int i=ini, 
            j=fim;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            seekArq(j);
            regJ.leDoArq(arquivo);
            
            while(i < j && regI.getCodigo() <= regJ.getCodigo())
            {
                i++;
                seekArq(i);
                regI.leDoArq(arquivo);
            }
            
            seekArq(j);            
            regI.gravaNoArq(arquivo);
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            
            while(i < j && regJ.getCodigo() >= regI.getCodigo())
            {
                j--;
                seekArq(j);
                regJ.leDoArq(arquivo);
            }
            
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            seekArq(j);            
            regI.gravaNoArq(arquivo);
        }
        
        if(ini < i-1)
            quickSP(ini, i-1);
        if(j+1 < fim)
            quickSP(j+1, fim);
    }
            
    public void quickSort()
    {
        quickSort(0, fileSize()-1);
    }
    
    public void quickSort(int ini, int fim)
    {
        int i=ini, j=fim;
        boolean flag=true;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            
            if(flag)
                while(i < j && regI.getCodigo() <= regJ.getCodigo())
                {
                    i++;
                    seekArq(i);
                    regI.leDoArq(arquivo);
                }
            else
                while(i < j && regJ.getCodigo() >= regI.getCodigo())
                {
                    j--;
                    seekArq(j);
                    regJ.leDoArq(arquivo);
                }
            
            seekArq(j);
            regI.gravaNoArq(arquivo);
            seekArq(i);
            regJ.gravaNoArq(arquivo);
        }
        
        if(ini < i-1)
            quickSort(ini, i-1);
        if(j +1 < fim)
            quickSort(j+1, fim);
    }
    
    public void quickCP()
    {
        quickCP(0, fileSize()-1);
    }
    
    public void quickCP(int ini, int fim)
    {
        int i, j, pivo;
        Registro regI = new Registro(),
                 regJ = new Registro(),
                 regPivo = new Registro();
        
        pivo = (ini+fim)/2;
        seekArq(pivo);
        regPivo.leDoArq(arquivo);
        
        i=ini;
        j=fim;
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);    
            while(regI.getCodigo() < regPivo.getCodigo())
            {
                i++;
                seekArq(i);
                regI.leDoArq(arquivo);
            }
            
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(regJ.getCodigo() > regPivo.getCodigo())
            {
                j--;
                seekArq(j);
                regJ.leDoArq(arquivo);
            }
            
            if(i <= j)
            {
                seekArq(i);
                regJ.gravaNoArq(arquivo);
                seekArq(j);
                regI.gravaNoArq(arquivo);
                
                i++;
                j--;
            }
        }
        
        if(ini < j)
            quickCP(ini, j);
        if(i < fim)
            quickCP(i, fim);
    }
    
    public void heap()
    {
        int pai, fd, fe, maiorf, tl = fileSize();
        Registro regPai = new Registro(),
                 regFd = new Registro(), 
                 regFe = new Registro(), regMaiorf;
        
        while(tl > 1)
        {
            pai = tl/2-1;
            while(pai >= 0)
            {
                fe=pai+pai+1;
                fd=fe+1;
                
                seekArq(pai);
                regPai.leDoArq(arquivo);
                seekArq(fe);
                regFe.leDoArq(arquivo);
                seekArq(fd);
                regFd.leDoArq(arquivo);
                
                if(fd < tl)
                {
                    if(regFd.getCodigo() < regFe.getCodigo())
                    {
                        regMaiorf = regFe;
                        maiorf = fe;
                    }
                    else
                    {
                        regMaiorf = regFd;
                        maiorf = fd;
                    }
                }
                else
                {
                    regMaiorf = regFe;
                    maiorf = fe;
                }
                
                if(regMaiorf.getCodigo() > regPai.getCodigo())
                {
                    seekArq(maiorf);
                    regPai.gravaNoArq(arquivo);
                    seekArq(pai);
                    regMaiorf.gravaNoArq(arquivo);
                }
                
                pai--;
            }
            
            seekArq(0);
            regPai.leDoArq(arquivo);
            seekArq(tl-1);
            regFd.leDoArq(arquivo);
            
            seekArq(0);
            regFd.gravaNoArq(arquivo);
            seekArq(tl-1);
            regPai.gravaNoArq(arquivo);
            
            tl--;
        }
    }
    
    
    public void merge1()
    {
        Arquivo[] arqs;
        
        int seq=1, tl=fileSize();
        
        while(seq < tl)
        {
            arqs = particao();
            fusao(arqs[0], arqs[1], seq);
            
            new File("aux1").delete();
            new File("aux2").delete();
            
            seq = seq * 2;
        }
    }
    
    public Arquivo[] particao()
    {
        Arquivo arq1 = new Arquivo("aux1");
        Arquivo arq2 = new Arquivo("aux2");
        Arquivo[] arqs = new Arquivo[2];
        arqs[0] = arq1;
        arqs[1] = arq2;
        
        int meio = fileSize()/2;
        Registro regAux = new Registro();
        
        for(int i=0 ; i < meio ; i++)
        {
            seekArq(i);
            regAux.leDoArq(arquivo);
            arq1.inserirRegNoFinal(regAux);
            
            seekArq(i+meio);
            regAux.leDoArq(arquivo);
            arq2.inserirRegNoFinal(regAux);
        }
        
        return arqs;
    }
    
    public void fusao(Arquivo arq1, Arquivo arq2, int seq)
    {
        int i=0, 
            j=0, 
            k=0,
            tl=fileSize(),
            aux_seq=seq;
        
        Registro regI = new Registro(),
                 regJ = new Registro();
                 
        while(k < tl)
        {
            while(i < seq && j < seq)
            {
                arq1.setSeekArq(i);
                regI.leDoArq(arq1.getArquivo());
                
                arq2.setSeekArq(j);
                regJ.leDoArq(arq2.getArquivo());
                
                if(regI.getCodigo() < regJ.getCodigo())
                {
                    seekArq(k);
                    regI.gravaNoArq(arquivo);
                    
                    i++;
                    k++;
                }
                else
                {
                    seekArq(k);
                    regJ.gravaNoArq(arquivo);
                    
                    j++;
                    k++;
                }
            }
            
            while(i < seq)
            {
                arq1.setSeekArq(i);
                regI.leDoArq(arq1.getArquivo());
                
                seekArq(k);
                regI.gravaNoArq(arquivo);
                
                i++;
                k++;
            }
            
            while(j < seq)
            {
                arq2.setSeekArq(j);
                regJ.leDoArq(arq2.getArquivo());
                
                seekArq(k);
                regJ.gravaNoArq(arquivo);
                
                j++;
                k++;
            }
            
            seq = seq + aux_seq;
        }
        
    }
    
    public void merge2()
    {
        
    }
    
    public void comb()
    {
        int dist, tl;
        Registro regI = new Registro(),
                 regDist = new Registro();
        
        tl = fileSize();
        dist = (int) (tl/1.3);
        
        while(dist > 0)
        {
            for(int i=0 ; i+dist < tl ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);

                seekArq(i+dist);
                regDist.leDoArq(arquivo);

                if(regI.getCodigo() > regDist.getCodigo())
                {
                    seekArq(i);
                    regDist.gravaNoArq(arquivo);

                    seekArq(i+dist);
                    regDist.gravaNoArq(arquivo);
                }
            }
            
            dist--;
        }
    }
    
    public void gnome()
    {
        Registro regI = new Registro();
        Registro regI2 = new Registro();
        
        int tl = fileSize();
        
        for(int i=0 ; i < tl ; i++)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            seekArq(i+1);
            regI2.leDoArq(arquivo);
            
            if(regI.getCodigo() > regI2.getCodigo())
            {
                seekArq(i);
                regI2.gravaNoArq(arquivo);
                
                seekArq(i+1);
                regI.gravaNoArq(arquivo);
            
                if(i > 0)
                    i = i-2;
            }
        }
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
    
    
    private Registro getReg(int pos)
    {
        Registro reg = new Registro();
        int posAtual=0;
        try {
            posAtual = (int) ( arquivo.getFilePointer() * Registro.length() );
        } 
        catch (IOException ex) {}
        
        if(pos == 0)
        {
            seekArq(posAtual - 1);
            reg.leDoArq(arquivo);
            
        }
        else 
        {
            seekArq( (pos < 0) ? posAtual - pos : posAtual + pos);
            reg.leDoArq(arquivo);
        }    
        return reg;
    }          
}

public class ArquivoAlgoritmos
{
    static Arquivo arquivoInsercaoDireta = new Arquivo("arquivoInsercaoDireta.dat");
    static Arquivo arquivoInsercaoBinaria = new Arquivo("arquivoInsercaoBinaria.dat");
    static Arquivo arquivoSelecaoDireta = new Arquivo("arquivoSelecaoDireta.dat");
    static Arquivo arquivoShell = new Arquivo("arquivoShell.dat");
    static Arquivo arquivoHeap = new Arquivo("arquivoHeap.dat");
    static Arquivo arquivoQuickSP = new Arquivo("arquivoQuickSP.dat");
    static Arquivo arquivoQuickCP = new Arquivo("arquivoQuickCP.dat");
    static Arquivo arquivoQuickSort = new Arquivo("arquivoQuickSort.dat");
    static Arquivo arquivoMerge1 = new Arquivo("arquivoMerge1.dat");
    static Arquivo arquivoMerge2 = new Arquivo("arquivoMerge2.dat");
    static Arquivo arquivoComb = new Arquivo("arquivoComb.dat");
    static Arquivo arquivoGnome = new Arquivo("arquivoGnome.dat");
    static Arquivo arquivoBucket = new Arquivo("arquivoBucket.dat");
    static Arquivo arquivoRadix = new Arquivo("arquivoRadix.dat");
    static Arquivo arquivoTim = new Arquivo("arquivoTim.dat");
        
    public static void deletarExistentes()
    {
        new File("aux1").delete(); 
        new File("aux2").delete(); 
        new File("aux").delete(); 
        new File("aux").delete();
        
        new File("arquivoInsercaoDireta.dat").delete();
        new File("arquivoInsercaoBinaria.dat").delete();
        new File("arquivoSelecaoDireta.dat").delete();
        new File("arquivoShell.dat").delete();
        new File("arquivoHeap.dat").delete();
        new File("arquivoQuickSP.dat").delete();
        new File("arquivoQuickCP.dat").delete();
        new File("arquivoQuickSort.dat").delete();
        new File("arquivoMerge1.dat").delete(); 
        new File("arquivoMerge2.dat").delete();
        new File("arquivoComb.dat").delete();
        new File("arquivoGnome.dat").delete();
        new File("arquivoBucket.dat").delete();
        new File("arquivoRadix.dat").delete();
        new File("arquivoTim.dat").delete();
    }
    
    public static void inserirDadosListas()
    {
        int[] dados = Dados.Dados.getDadosInt();
        
        for(int i=0 ; i < dados.length ; i++)
        {
            arquivoInsercaoDireta.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
//            arquivoInsercaoBinaria.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
            arquivoSelecaoDireta.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
            arquivoShell.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
            arquivoHeap.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
            arquivoQuickSP.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
            arquivoQuickCP.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
            arquivoQuickSort.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
            arquivoMerge1.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
//            arquivoMerge2.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
//            arquivoComb.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
//            arquivoGnome.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
//            arquivoBucket.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
//            arquivoRadix.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
//            arquivoTim.inserirRegNoFinal(new Registro(dados[i], "blabla", 25));
        }
    }
    
    public static void ordenarListas()
    {
        arquivoInsercaoDireta.insercaoDireta();
//        arquivoInsercaoBinaria.insercaoBinaria();
        arquivoSelecaoDireta.selecaoDireta();
        arquivoShell.shell();
        arquivoHeap.heap();
        arquivoQuickSP.quickSP();
        arquivoQuickCP.quickCP();
        arquivoQuickSort.quickSort();
        arquivoMerge1.merge1();
//        arquivoMerge2.merge2();
//        arquivoComb.comb();
//        arquivoGnome.gnome();
//        arquivoBucket.bucket();
//        arquivoRadix.radix();
//        arquivoTim.tim();
    }
    
    public static void exibirDadosListas()
    {
        System.out.print("Insercao Direta:   ");
        arquivoInsercaoDireta.exibirArq();

        System.out.print("Insercao Binaria:  ");
        arquivoInsercaoBinaria.exibirArq();

        System.out.print("Selecao Direta:    ");
        arquivoSelecaoDireta.exibirArq();
        
        System.out.print("Shell:             ");
        arquivoShell.exibirArq();

        System.out.print("Heap:              ");        
        arquivoHeap.exibirArq();

        System.out.print("QuickSP:           ");        
        arquivoQuickSP.exibirArq();

        System.out.print("QuickCP:           ");
        arquivoQuickCP.exibirArq();

        System.out.print("QuickSort:         ");
        arquivoQuickSort.exibirArq();

        System.out.print("Merge1:            ");
        arquivoMerge1.exibirArq();

        System.out.print("Merge2:            ");
        arquivoMerge2.exibirArq();

        System.out.print("Comb:              ");
        arquivoComb.exibirArq();

        System.out.print("Gnome:             ");
        arquivoGnome.exibirArq();

        System.out.print("Bucket:            ");
        arquivoBucket.exibirArq();

        System.out.print("Radix:             ");
        arquivoRadix.exibirArq();

        System.out.print("Tim:               ");
        arquivoTim.exibirArq();
    }
    
    public static void main(String args[])
    {
        deletarExistentes();
        inserirDadosListas();
        ordenarListas();
        exibirDadosListas();
    }

}
