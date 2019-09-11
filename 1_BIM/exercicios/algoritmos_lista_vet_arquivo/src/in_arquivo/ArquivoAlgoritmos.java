package in_arquivo;

import Dados.UtilApp.ArquivoParams;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public final int tf=1022;
    
    private int numero; //4 bytes
    private char lixo[] = new char[tf]; //2044 bytes

    public Registro() {
    }
    
    public Registro(int numero)
    {
        this.numero=numero;
        
        for (int i=0 ; i<tf ; i++)
            lixo[i]='X';
    }
    
    public void gravaNoArq(RandomAccessFile arquivo)
    {
        try
        {
            arquivo.writeInt(numero);
            for(int i=0 ; i<tf ; i++)
                arquivo.writeChar(lixo[i]);
        }
        catch(IOException e){}
    }
    
    public void leDoArq(RandomAccessFile arquivo)
    {
        try
        {
            numero = arquivo.readInt();
            
            for(int i=0 ; i<tf ; i++)
                lixo[i]=arquivo.readChar();
        }
        catch(IOException e) {}
    }
    
    static int length()
    {
        //int numero; 4 bytes
        //char lixo[] = new char[tf]; 2044 bytes
        //--------------------------------------
        //2048 bytes
        return(2048);
    }
    
    public void exibirReg()
    {
        int i;
        System.out.print("[" + numero + "] ");
//        System.out.print("codigo....." + this.codigo);
//        System.out.print(" nome.......");
//        String Snome = new String(nome);
//        System.out.print(Snome);
//        System.out.println(" idade......." + this.idade);
//        System.out.println("----------------------------------");
    }
    
    Registro getClone() 
    {
        return new Registro( this.getNumero());
    }

    public int getNumero() 
    {
        return numero;
    }

    public void setNumero(int numero) 
    {
        this.numero = numero;
    }

    public char[] getLixo() 
    {
        return lixo;
    }

    public void setLixo(char[] lixo) 
    {
        this.lixo = lixo;
    }
}



class Arquivo
{
    private String nomearquivo;
    private RandomAccessFile arquivo;
    
    int mov=0, comp=0;

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
    
    public void geraArquivoOrdenado() 
    {
        Registro regI = new Registro();
        seekArq(0);
        
        for(int i=1 ; i <= ArquivoParams.QUANTIDADE_TOTAL_REG_ARQUIVO ; i++)
        {
            regI.setNumero(i);
            regI.gravaNoArq(arquivo);
        }
    }
    
    public void geraArquivoReverso() 
    {
        Registro regI = new Registro();
        seekArq(0);
        
        for(int i=ArquivoParams.QUANTIDADE_TOTAL_REG_ARQUIVO ; i > 0 ; i--)
        {
            regI.setNumero(i);
            regI.gravaNoArq(arquivo);
        }
    }
    
    public void geraArquivoRandomico() 
    {
        List listaInt = new ArrayList();
        Registro regI = new Registro();
        
        for(int i=1 ; i <= ArquivoParams.QUANTIDADE_TOTAL_REG_ARQUIVO ; i++)
            listaInt.add(i);
        
        
        Collections.shuffle(listaInt);
        
        seekArq(0);
        
        for(int i=0 ; i < ArquivoParams.QUANTIDADE_TOTAL_REG_ARQUIVO ; i++)
        {
            regI.setNumero((int) listaInt.get(i));
            regI.gravaNoArq(arquivo);
        }
        
    }
    
    public RandomAccessFile getArquivo() {
        return arquivo;
    }
    
    public void setSeekArq(int pos)
    {
        seekArq(pos);
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
    
    public int getMaior()
    {
        int maior;
        
        Registro reg = new Registro();
        
        seekArq(0);
        reg.leDoArq(arquivo);
        
        maior = reg.getNumero();
        
        while(!eof())
        {
            reg.leDoArq(arquivo);
            if(reg.getNumero() > maior)
                maior = reg.getNumero();
        }
        
        return maior;
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
            while(pos > 0 && regIAnt.getNumero() > regAux.getNumero() )
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
    
    
    public int buscaBinaria(int chave, int tl)
    {
        int inicio = 0, 
            fim = tl-1, 
            meio = fim/2;
        
        Registro noI = new Registro();
        
        seekArq(meio);
        noI.leDoArq(arquivo);
        
	while(noI.getNumero() != chave && inicio < fim)
	{
            if(chave < noI.getNumero())
                fim=meio-1;
            else
                inicio=meio+1;

            meio=(fim+inicio)/2;

            seekArq(meio);
            noI.leDoArq(arquivo);
	}
        
	if(noI.getNumero() < chave)
            return meio+1;
	else
            return meio;
    }
    
//    public void insercaoBinaria()
//    {
//        int pos, numero, tl = fileSize();
//        
//        Registro no1 = new Registro();
//        Registro no2 = new Registro();
//        
//        for(int i = 1; i < tl; i++)
//        {
//            seekArq(i);
//            no1.leDoArq(arquivo);
//            
//            numero = no1.getNumero();
//            pos = buscaBinaria(no1.getNumero(),i);
//            
//            for(int j = i;  j > pos ; j--)
//            {
//                seekArq(j-1);
//                no2.leDoArq(arquivo);
//                
//                seekArq(j);
//                no2.gravaNoArq(arquivo);
//            }
//            
//            no1.setNumero(numero);
//            seekArq(pos);
//            no1.gravaNoArq(arquivo);
//        }
//    }
    
    
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
            seekArq(posMenor);
            regMenor.leDoArq(arquivo);
            
            j=i+1;
            while(j < tl)
            {
                seekArq(j);
                regJ.leDoArq(arquivo);
                
                if(regJ.getNumero() < regMenor.getNumero())
                {
                    posMenor = j;
                    
                    seekArq(posMenor);
                    regMenor.leDoArq(arquivo);
                }
                j++;
            }
            
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
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        while(tl > 1)
        {
            for(int i=0 ; i < tl-1 ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                regJ.leDoArq(arquivo);
                
                comp++;
                if(regI.getNumero() > regJ.getNumero())
                {
                    mov+=2;
                    
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    regI.gravaNoArq(arquivo);
                }
            }
            
            tl--;
        }
    }
    
    public void shake()
    {
        int ini, fim, i;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        ini = 0 ;
        fim = fileSize()-1;
        
        while(ini < fim)
        {
            for(i=ini ; i < fim ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                regJ.leDoArq(arquivo);
                
                comp++;
                if(regI.getNumero() > regJ.getNumero())
                {
                    mov+=2;
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    regI.gravaNoArq(arquivo);
                }
            }
            fim--;
            
            for(i = fim ; i > ini ; i--)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                
                seekArq(i-1);
                regJ.leDoArq(arquivo);
                
                comp++;
                if(regI.getNumero() < regJ.getNumero())
                {
                    mov+=2;
                    
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    
                    seekArq(i-1);
                    regI.gravaNoArq(arquivo);
                }
            }
            
            ini++;
        }
    }
    
    public void shell()
    {
        int i, j, k, tl, dist=4;

        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        tl=fileSize();

        while(dist > 0)
        {
            for(i=0 ; i < dist ; i++)
                for(j=i ; j+dist < tl ; j+= dist)
                {
                    seekArq(j);
                    reg1.leDoArq(arquivo);

                    seekArq(j+dist);
                    reg2.leDoArq(arquivo);

                    comp++;
                    if(reg1.getNumero() > reg2.getNumero())
                    {
                        mov +=2;
                        
                        seekArq(j);
                        reg2.gravaNoArq(arquivo);
                        
                        seekArq(j+dist);
                        reg1.gravaNoArq(arquivo);
                    

                        if(j-dist >= i)
                        {   
                            mov=+2;

                            seekArq(j);
                            reg1.leDoArq(arquivo);

                            seekArq(j-dist);
                            reg2.leDoArq(arquivo);

                            comp++;

                            k=j;
                            while(k-dist >= i && reg1.getNumero() < reg2.getNumero())
                            {
                                mov=+2;

                                seekArq(k);
                                reg2.gravaNoArq(arquivo);

                                seekArq(k-dist);
                                reg1.gravaNoArq(arquivo);

                                k -= dist;

                                if(k-dist >= i)
                                {
                                    mov=+2;

                                    seekArq(k);
                                    reg1.leDoArq(arquivo);

                                    seekArq(k-dist);
                                    reg2.leDoArq(arquivo);
                                }

                                comp++; 
                            }
                        }
                    }
                }

            dist = dist /2;
        }
    }

    public void heap()
    {
        int pai, fd, fe, maiorf; 
        int tl = fileSize();
        
        Registro reg1 = new Registro(); 
        Registro reg2 = new Registro();
        
        while(tl > 1)
        {
            pai = tl/2 - 1;
            while(pai >= 0)
            {
                fe=pai+pai+1;
                fd=fe+1;
                maiorf = fe;
                
                if(fd < tl)
                {
                    seekArq(fe);
                    reg1.leDoArq(arquivo);
                    
                    seekArq(fd);
                    reg2.leDoArq(arquivo);
                
                    if(reg2.getNumero() > reg1.getNumero())   
                        maiorf = fd;
                }
                
                seekArq(maiorf);
                reg1.leDoArq(arquivo);
                
                seekArq(pai);
                reg2.leDoArq(arquivo);
                
                if(reg1.getNumero() > reg2.getNumero())
                {
                    seekArq(maiorf);
                    reg2.gravaNoArq(arquivo);
                    
                    seekArq(pai);
                    reg1.gravaNoArq(arquivo);
                }
                
                pai--;
            }
            
            seekArq(0);
            reg1.leDoArq(arquivo);
            seekArq(tl-1);
            reg2.leDoArq(arquivo);
            
            seekArq(0);
            reg2.gravaNoArq(arquivo);
            seekArq(tl-1);
            reg1.gravaNoArq(arquivo);
            
            tl--;
        }
    }

    
    public void quickSP()
    {
        quickSP(0, fileSize()-1);
    }
    
    public void quickSP(int ini, int fim)
    {
        int i, j;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        i = ini;
        j = fim;
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(i < j && regI.getNumero() <= regJ.getNumero())
            {
                i++;
                
                seekArq(i);
                regI.leDoArq(arquivo);
            }
            
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            seekArq(j);            
            regI.gravaNoArq(arquivo);
            
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(i < j && regJ.getNumero() >= regI.getNumero())
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
        int i=ini, 
            j=fim;
        
        boolean flag = true;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            
            if(flag)
                while(i < j && regI.getNumero() <= regJ.getNumero())
                {
                    i++;

                    seekArq(i);
                    regI.leDoArq(arquivo);
                }
            
            else
                while(i < j && regJ.getNumero() >= regI.getNumero())
                {
                    j--;

                    seekArq(j);
                    regJ.leDoArq(arquivo);
                }
            
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            seekArq(j);            
            regI.gravaNoArq(arquivo);
            flag = !flag;
        }
        
        if(ini < i-1)
            quickSort(ini, i-1);
        
        if(j+1 < fim)
            quickSort(j+1, fim);
    }
    
    public void quickCP()
    {
        quickCP(0, fileSize()-1);
    }
    
    public void quickCP(int ini, int fim)
    {
        int i, j, pivo;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        pivo = (ini+fim)/2;
        seekArq(pivo);
        regI.leDoArq(arquivo);
        pivo = regI.getNumero();
        
        i=ini;
        j=fim;
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);    
            while(regI.getNumero() < pivo)
            {
                i++;
                regI.leDoArq(arquivo);
            }
            
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(regJ.getNumero() > pivo)
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
    
    public void merge1()
    {
        //mudar isso
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
                
                if(regI.getNumero() < regJ.getNumero())
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
        Arquivo aux = new Arquivo("arqaux.bin");
        
        merge2(aux, 0, fileSize()-1);
    }
    
    public void merge2(Arquivo arqAux, int ini, int fim)
    {
        int meio;
        
        if(ini < fim)
        {
            meio = (ini+fim) / 2;
            
            merge2(arqAux, ini, meio);
            merge2(arqAux, meio+1, fim);
            fusao2(arqAux, ini, meio, meio+1, fim);
        }
    }
    
    public void fusao2(Arquivo arqAux, int ini1, int fim1, int ini2, int fim2)
    {
        int i, j;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        arqAux.seekArq(0);
        
        i=ini1;
        j=ini2;
        while(i <= fim1 && j <= fim2)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            seekArq(j);
            regJ.leDoArq(arquivo);
            
            if(regI.getNumero() < regJ.getNumero())
            {
                regI.gravaNoArq(arqAux.getArquivo());
                i++;
            }
            else
            {
                regJ.gravaNoArq(arqAux.getArquivo());
                j++;
            }
        }
        
        while(i <= fim1)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            regI.gravaNoArq(arqAux.getArquivo());
            i++;
        }

        while(j <= fim2)
        {
            seekArq(j);
            regI.leDoArq(arquivo);
            
            regI.gravaNoArq(arqAux.getArquivo());
            j++;
        }
        
        for(i=ini1 ; i <= fim2 ; i++)
        {
            arqAux.seekArq(i);
            regI.leDoArq(arqAux.getArquivo());
            
            seekArq(i);
            regI.gravaNoArq(arquivo);
        }
    }
    
    public void counting()
    {
        int maior, tl;
        int[] vetAux;
        
        //pega maior valor
        maior = getMaior();
        
        tl = fileSize();
        
        Registro[] vetSaida = new Registro[tl];
        Registro regI = new Registro();
        
        //vetor auxiliar
        vetAux = new int[maior+1];
        
        //pegar frequencia
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            regI.leDoArq(arquivo);
            vetAux[regI.getNumero()]++;
        }
        
        //montar acumulativa
        for(int i=1 ; i < vetAux.length ; i++)
            vetAux[i] = vetAux[i-1];
        
        //gerar vetor ordenado ja
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            regI.leDoArq(arquivo);
            vetSaida[ --vetAux[ regI.getNumero() ]] = regI.getClone();
        }
        
        
        //devolver pro arquivo
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            regI = vetSaida[i];
            regI.gravaNoArq(arquivo);
        }
        
    } 
    
    public void bucket()
    {
        Registro regI = new Registro();
        
        int tl = fileSize();
        
        //pega maior valor
        int maior = getMaior(), hash;
        
        //definir quantidade de baldes
        int baldes = maior/5;
        
        //definir os baldes
        Arquivo[] buckets = new Arquivo[ baldes+1 ];
        
        //iniciar os baldes
        for(int i=0 ; i < buckets.length ; i++)
            buckets[i] = new Arquivo("bucket"+i);
        
        //filtrar dados no baldes
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            //leio reg
            regI.leDoArq(arquivo);
            
            //faço hash
            hash = regI.getNumero()/5;
            
            //insiro o reg inteiro no final do arq bucket
            buckets[hash].inserirRegNoFinal(regI);
        }
        
        //ordenar os buckets
        for(int i=0 ; i < buckets.length ; i++)
            buckets[i].insercaoDireta();
        
        //gravar de volta passando por todos os buckets
        seekArq(0);
        for(int i=0 ; i < buckets.length ; i++)
        {
            buckets[i].seekArq(0);
            
            for(int j=0 ; j < buckets[i].fileSize() ; j++)
            {
                regI.leDoArq(buckets[i].getArquivo());
                regI.gravaNoArq(arquivo);
            }
        }
        
//        deletar os buckets aux
        for(int i=0 ; i < buckets.length ; i++)
            new File("bucket"+i).delete();
    }
    
    public void radix()
    {
        int maior, exp, tl;
        
        int[] vetAux;
        Registro[] vetSaida;
        Registro regI = new Registro();
        
        tl = fileSize();
        vetSaida = new Registro[tl];

        maior = getMaior();
        exp=1;
        while(maior/exp > 0)
        {
            vetAux = new int[10];
            
            //pega frequencia
            seekArq(0);
            for(int i=0 ; i < tl ; i++)
            {
                regI.leDoArq(arquivo);
                vetAux[ (regI.getNumero()/exp) % 10 ]++;
            }
            
            //gera acumulativa da frequencia
            for(int i=1 ; i < 10 ; i++)
                vetAux[i] = vetAux[i-1];
            
            //gera o vetor ordenado
            seekArq(0);
            for(int i=0 ; i < tl ; i++)
            {
                regI.leDoArq(arquivo);
                vetSaida[ --vetAux[ (regI.getNumero()/exp) % 10 ]] = regI.getClone(); 
            }
            
            seekArq(0);
            for(int i=0 ; i < tl ; i++)
            {
                regI = vetSaida[i];
                regI.gravaNoArq(arquivo);
            }
        }
    }
    
    public void gnome()
    {
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        //tamanho total arquivo
        int tl = fileSize();
        
        //enquanto tiver mais que um reg
        while(tl > 1)
        {
            //loop igual o bolha
            for(int i=0 ; i < tl-1 ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                regJ.leDoArq(arquivo);
                
                if(regI.getNumero() > regJ.getNumero())
                {
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    regI.gravaNoArq(arquivo);
                
                    //casi o regi for maior que regj volta duas casass
                    if(i > 0)
                        i = i - 2;
                }
            }
            
            tl--;
        }
        
    }
    
    
    public void comb()
    {
        int tl = fileSize();
        int dist;
     
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        dist = (int) ( tl / 1.3);
        while(dist > 0)
        {
            for(int i=0 ; i + dist < tl ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                
                seekArq(i+dist);
                regJ.leDoArq(arquivo);
                
                if(regI.getNumero() > regJ.getNumero())
                {
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    
                    seekArq(i+dist);
                    regI.gravaNoArq(arquivo);
                }
            }
            
            dist = (int) ( dist / 1.3);
        }
    }
    
    public void insercaoDireta(int ini, int fim)
    {
        
    }
    
    public void tim()
    {
        int ini1, ini2, fim1, fim2;
        int run = 32;
        Arquivo arqAux;
        
        int tl = fileSize();
        
        if(tl < run)
            this.insercaoDireta();
        else
            for(int i=0 ; i < tl ; i++)
            {
                ini1 = i;
                fim1 = Math.min(i+run-1, tl-1);
                insercaoDireta(ini1, fim1);
            }
        
        seekArq(0);
        for(int run2 = run ; run2 < tl ; run2 *= run)
        {
            for(ini1 = 0 ; ini1 < tl ; ini1 += 2*run)
            {
                
                fim1 = 2*run2-1;
                ini2 = fim1+1;
                fim2 = Math.min( ini1 + (2*run2-1), tl-1);
                
                 arqAux = new Arquivo("arqAux.bin");
                
                //o seekArq vai andando dentro do fusao, quando retira os dados do aux e passa para o arquivo.
                fusao2(arqAux, ini1, fim1, ini2, fim2);
            }
        }
        
        new File("arqAux.bin").delete();
    }     
}

public class ArquivoAlgoritmos
{
    static Arquivo arquivoInsercaoDireta = new Arquivo("arquivoInsercaoDireta.dat");
    static Arquivo arquivoInsercaoBinaria = new Arquivo("arquivoInsercaoBinaria.dat");
    static Arquivo arquivoSelecaoDireta = new Arquivo("arquivoSelecaoDireta.dat");
    static Arquivo arquivoBolha = new Arquivo("arquivoBolha.dat");
    static Arquivo arquivoShake = new Arquivo("arquivoShake.dat");
    static Arquivo arquivoShell = new Arquivo("arquivoShell.dat");
    static Arquivo arquivoHeap = new Arquivo("arquivoHeap.dat");
    static Arquivo arquivoQuickSP = new Arquivo("arquivoQuickSP.dat");
    static Arquivo arquivoQuickCP = new Arquivo("arquivoQuickCP.dat");
    static Arquivo arquivoQuickSort = new Arquivo("arquivoQuickSort.dat");
    static Arquivo arquivoMerge1 = new Arquivo("arquivoMerge1.dat");
    static Arquivo arquivoMerge2 = new Arquivo("arquivoMerge2.dat");
    static Arquivo arquivoComb = new Arquivo("arquivoComb.dat");
    static Arquivo arquivoCounting = new Arquivo("arquicoCounting.dat");
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
        new File("arquivoBolha.dat").delete();
        new File("arquivoShake.dat").delete();
        new File("arquivoShell.dat").delete();
        new File("arquivoHeap.dat").delete();
        new File("arquivoQuickSP.dat").delete();
        new File("arquivoQuickCP.dat").delete();
        new File("arquivoQuickSort.dat").delete();
        new File("arquivoMerge1.dat").delete(); 
        new File("arquivoMerge2.dat").delete();
        new File("arquicoCounting.dat").delete();
        new File("arquivoComb.dat").delete();
        new File("arquivoGnome.dat").delete();
        new File("arquivoBucket.dat").delete();
        new File("arquivoRadix.dat").delete();
        new File("arquivoTim.dat").delete();
    }
    
    public static void inserirDadosArquivos()
    {
        arquivoInsercaoDireta.geraArquivoRandomico();
//        arquivoInsercaoBinaria.geraArquivoRandomico();
//        arquivoSelecaoDireta.geraArquivoRandomico();
//        arquivoBolha.geraArquivoRandomico();
//        arquivoShake.geraArquivoRandomico();
//        arquivoShell.geraArquivoRandomico();
//        arquivoHeap.geraArquivoRandomico();
//        arquivoQuickSP.geraArquivoRandomico();
//        arquivoQuickCP.geraArquivoRandomico();
//        arquivoQuickSort.geraArquivoRandomico();
//        arquivoMerge1.geraArquivoRandomico();
//        arquivoMerge2.geraArquivoRandomico();
//        arquivoComb.geraArquivoRandomico();
        arquivoCounting.geraArquivoRandomico();
//        arquivoGnome.geraArquivoRandomico();
//        arquivoComb.geraArquivoRandomico();
//        arquivoBucket.geraArquivoRandomico();
//        arquivoRadix.geraArquivoRandomico();
//        arquivoTim.geraArquivoRandomico();
    }
    
    public static void ordenarListas()
    {
//        arquivoInsercaoDireta.insercaoDireta(); // ORDENOU
//        arquivoInsercaoBinaria.insercaoBinaria();
//        arquivoSelecaoDireta.selecaoDireta(); // ORDENOU
//        arquivoBolha.bolha(); // ORDENOU
//        arquivoShake.shake(); // ORDENOU
//        arquivoShell.shell(); // ORDENOU
//        arquivoHeap.heap(); // ORDENOU
//        arquivoQuickSP.quickSP(); // ORDENOU
//        arquivoQuickCP.quickCP(); // ORDENOU 
//        arquivoQuickSort.quickSort();// ORDENOU
//        arquivoMerge1.merge1();
//        arquivoMerge2.merge2();
//        arquivoComb.comb(); // ORDENOU
        arquivoCounting.counting();
//        arquivoGnome.gnome(); // ORDENOU
//        arquivoBucket.bucket(); // ORDENOU
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
        
        System.out.print("Bolha:             ");
        arquivoBolha.exibirArq();
        
        System.out.print("Shake:             ");
        arquivoShake.exibirArq();

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
        
        System.out.print("Counting:              ");
        arquivoCounting.exibirArq();

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
        inserirDadosArquivos();
        ordenarListas();
        exibirDadosListas();
    }

}


