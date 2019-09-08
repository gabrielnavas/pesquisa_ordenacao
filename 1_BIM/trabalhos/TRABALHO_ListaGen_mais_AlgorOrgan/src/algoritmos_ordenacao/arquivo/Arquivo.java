package algoritmos_ordenacao.arquivo;

import algoritmos_ordenacao.arquivo.lista.ListaDupla;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.UtilApp.ArquivoParams;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class Arquivo {
    
    private String nomearquivo;
    private RandomAccessFile arquivo;
    
    private int comp;
    private int mov;
    
    private static final int QUANTIDADE_TOTAL_REG_ARQUIVO = 50;
//    private static final int QUANTIDADE_TOTAL_REG_ARQUIVO = 1024;

    public Arquivo(String nomeArquivo)
    {
        this.nomearquivo = nomeArquivo;
        
        this.comp=0;
        this.comp=0;
        
        try
        {
            arquivo = new RandomAccessFile(nomeArquivo, "rw");
        } 
        catch (IOException e) { }
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem) 
    {
        Registro reg = new Registro();
        int tamanhoArquivoOrigem;
        
        seekArq(0);
        
        try 
        { 
            arquivoOrigem.seek(0);        
            tamanhoArquivoOrigem = (int) arquivoOrigem.length()/Registro.length();
        } 
        catch (IOException ex) 
        {
            tamanhoArquivoOrigem=0;
        }
        
        for(int i = 0 ; i < tamanhoArquivoOrigem ; i++)
        {
            reg.leDoArq(arquivoOrigem);
            reg.gravaNoArq(arquivo);
        }
    }
    
    public RandomAccessFile getArquivo() 
    { 
        return this.arquivo; 
    }
    
    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } 
        catch (IOException ex)
        { }
    }

    //semelhante ao feof() da linguagem C
    //verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof()  
    {
        boolean retorno = false;
        
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;                               
        } 
        catch (IOException e)
        { }
        
        return (retorno);
    }
    
    public void close()
    {
        try {
            this.arquivo.close();
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //insere um Registro no final do arquivo, passado por par�metro
    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(fileSize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }
    
    public int fileSize()
    {
        int registros;
                
        try
        {
            registros = (int) arquivo.length()/Registro.length();
        }
        catch(IOException e)
        {
            registros = 0;
        }
        
        return registros;
    }

    public boolean isOrdenacao()
    {
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        seekArq(0);
        
        regI.leDoArq(arquivo);
        regJ.leDoArq(arquivo);
        while(!eof() && regI.getNumero() < regJ.getNumero())
        {
            regI.leDoArq(arquivo);
            regJ.leDoArq(arquivo);
        }
        
        return eof();
    }
    
    public void exibirArq()
    {
        int i;
        Registro reg = new Registro();
        
        System.out.print(
                ArquivoParams.gerarStringTamanho(20, this.nomearquivo.split("|")[0] ) + 
                this.nomearquivo + isOrdenacao() + ": "
        );
        
        seekArq(0);
        
        while (!this.eof())
        {
            reg.leDoArq(arquivo);
            reg.exibirReg();
        }
        
        System.out.println();
    }
    
    public void exibirArqInText()
    {
        String line = "";
        seekArq(0);
        
        while(!eof())
        {
            try {
                line = arquivo.readLine();
            } 
            catch (IOException ex) {
                line = "Não foi possível exibir linha";
            }
            
            System.out.println(line);
        }
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

    public void leArq()
    {
        int codigo; //, idade;
//        String nome;
        codigo = Entrada.leInteger("Digite o código");
        while (codigo != 0)
        {
//            nome = Entrada.leString("Digite o nome");
//            idade = Entrada.leInteger("Digite a idade");
            inserirRegNoFinal(new Registro(codigo));
            codigo = Entrada.leInteger("Digite o código");
        }
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
    

    public void initComp() 
    {
        this.comp = 0;
    }
    
    public void initMov() 
    {
        this.mov = 0;
    }
    
    public int getComp() 
    { 
        return this.comp; 
    }
    
    public int getMov() 
    { 
        return this.mov; 
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
        
        //fantástico isso daqui, rsrs
        Collections.shuffle(listaInt);
        
        seekArq(0);
        
        for(int i=0 ; i < ArquivoParams.QUANTIDADE_TOTAL_REG_ARQUIVO ; i++)
        {
            regI.setNumero((int) listaInt.get(i));
            regI.gravaNoArq(arquivo);
        }
        
    }
    
    //.............................................................................
    /*

    insira aqui os métodos de Ordenação;

    */
    public void insercaoDireta()
    {
        
    }
    
    public void insercaoBinaria()
    {
        
    }
    
    public void selecaoDireta()
    {
        
    }
    
    public void bolha()
    {
        
    }
    
    public void shake()
    {
        
    }
    
    public void shell()
    {
        
    }
    
    public void heap()
    {
        
    }
    
    public void quickCP()
    {
        
    }
    
    public void quickSP()
    {
        
    }
    
    public void merge1()
    {
        
    }
    
    public void fusao1()
    {
        
    }
    
    public void merge2()
    {
        Arquivo aux = new Arquivo("arqaux.bin");
        int tl = fileSize();
        
        merge2(aux, 0, tl-1);
    }
    
    public void merge2(Arquivo arqAux, int ini, int fim)
    {
        int meio;
        
        if(ini < fim)
        {
            meio = (fim+ini) / 2;
            merge2(arqAux, ini, meio);
            merge2(arqAux, meio+1, fim);
        }
    }
    
    public void fusao2(Arquivo arqAux, int ini1, int fim1, int ini2, int fim2)
    {
        int i, j, k;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        arqAux.seekArq(0);
        
        i=ini1;
        j=ini2;
        k=ini1;
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
            seekArq(i);
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
        ListaDupla lista;
        
        int tl = fileSize();
        
        //pega maior valor
        int maior = getMaior(), hash;
        
        //definir quantidade de baldes
        int baldes = maior/5;
        
        //definir os baldes
        ListaDupla[] buckets = new ListaDupla[ baldes+1 ];
        
        //iniciar os baldes
        for(int i=0 ; i < buckets.length ; i++)
            buckets[i] = new ListaDupla();
        
        //filtrar dados no baldes
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            regI.leDoArq(arquivo);
            hash = regI.getNumero()/baldes;
            buckets[hash].inserirFinal(regI.getNumero());
        }
        
        //ordenar os buckets
        for(int i=0 ; i < buckets.length ; i++)
            buckets[i].insercaoDireta();
        
        //gravar de volta passando por todos os buckets
        seekArq(0);
        for(int i=0 ; i < buckets.length ; i++)
        {
            lista = buckets[i];
            for(int j=0 ; j < lista.getTl() ; j++)
            {
                regI.setNumero(lista.getNo(i).getInfo());
                regI.gravaNoArq(arquivo);
            }    
        }
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
        float coefi = 1.3f;
        int dist;
     
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        dist = (int) (tl/coefi);
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
            
            dist = (int) (tl/coefi);
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
