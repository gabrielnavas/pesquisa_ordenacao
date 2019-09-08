package algoritmos_ordenacao.arquivo;

import algoritmos_ordenacao.arquivo.lista.ListaDupla;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import lista_generica.No;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

class NoReg
{
    private int cod;
    private String nome;
    private int idade;

    private NoReg prox;
    private NoReg ant;
    
    public NoReg() { }
    
    public NoReg(int cod, String nome, int idade, NoReg ant, NoReg prox) {
        this.cod = cod;
        this.nome = nome;
        this.idade = idade;
        this.ant = ant;
        this.prox = prox;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public NoReg getProx() {
        return prox;
    }

    public void setProx(NoReg prox) {
        this.prox = prox;
    }

    public NoReg getAnt() {
        return ant;
    }

    public void setAnt(NoReg ant) {
        this.ant = ant;
    }
}

class ListaDuplaReg
{
    private NoReg inicio;
    private NoReg fim;
    
    public ListaDuplaReg()
    {
        inicio=null;
        fim = null;
    }
    
    public void inserirFinal(int cod, String nome, int idade)
    {
        NoReg novo = new NoReg(cod, nome, idade, fim, null);
        
        if(fim == null)
            inicio = fim = novo;
        else
        {
            novo.setProx(novo);
            fim = novo;
        }
    }
    
    public int getTl()
    {
        int cont = 0;
        NoReg no = inicio;
        
        while(no != null)
        {
            cont++;
            no = no.getProx();
        }
        
        return cont;
    }
    
    public void insercaoDireta()
    {
        NoReg noI = inicio.getProx(), noAux;
        String auxStr;
        int auxCod, auxIdade;
        int pos;
        int tl = getTl();
        
        for(int i=1 ; i < tl ; i++)
        {
            auxStr = noI.getNome().toString();
            auxCod = noI.getCod();
            auxIdade = noI.getIdade();
            noAux = noI;
            
            pos = i;
            while(pos > 0 && noAux.getAnt().getCod() > noAux.getCod())
            {
                noAux.setNome(noAux.getAnt().getNome());
                noAux.setIdade(noAux.getAnt().getIdade());
                noAux.setCod(noAux.getAnt().getCod());
                
                noAux = noAux.getAnt();
            }
            
            noAux.setCod(auxCod);
            noAux.setIdade(auxIdade);
            noAux.setNome(auxStr);
        }
    }
}


public class Arquivo {
    
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        { }
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
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IOException ex)
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
        } catch (IOException e)
        { }
        return (retorno);
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

    public void exibirArq()
    {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof())
        {
            System.out.println("Posicao " + i);
            aux.leDoArq(arquivo);
            aux.exibirReg();
            i++;
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
        int codigo, idade;
        String nome;
        codigo = Entrada.leInteger("Digite o c�digo");
        while (codigo != 0)
        {
            nome = Entrada.leString("Digite o nome");
            idade = Entrada.leInteger("Digite a idade");
            inserirRegNoFinal(new Registro(codigo, nome, idade));
            codigo = Entrada.leInteger("Digite o c�digo");
        }
    }
    
    public int getMaior()
    {
        int maior;
        
        Registro reg = new Registro();
        
        seekArq(0);
        reg.leDoArq(arquivo);
        
        maior = reg.getCodigo();
        
        while(!eof())
        {
            reg.leDoArq(arquivo);
            if(reg.getCodigo() > maior)
                maior = reg.getCodigo();
        }
        
        return maior;
    }
    

    public void initComp() { }
    public void initMov() { }
    public int getComp() { return 0; }
    public int getMov() { return 0; }
    
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
    
    public void quickCP()
    {
        
    }
    
    public void quickSP()
    {
        
    }
    
    public void merge1()
    {
        
    }
    
    public void merge2()
    {
        
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
            vetAux[regI.getCodigo()]++;
        }
        
        //montar acumulativa
        for(int i=1 ; i < vetAux.length ; i++)
            vetAux[i] = vetAux[i-1];
        
        //gerar vetor ordenado ja
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            regI.leDoArq(arquivo);
            vetSaida[ --vetAux[ regI.getCodigo() ]] = regI.getClone();
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
        Arquivo arqJava;
        
        int tl = fileSize();
        
        //pega maior valor
        int maior = getMaior(), hash;
        
        //definir quantidade de baldes
        int baldes = maior/5;
        
        //definir os baldes
        Arquivo[] bucket = new Arquivo[ baldes+1 ];
        
        //iniciar os baldes
        for(int i=0 ; i < bucket.length ; i++)
            bucket[i] = new Arquivo("arquivo"+i);
        
        
        //filtrar dados no baldes
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            regI.leDoArq(arquivo);
            hash = regI.getCodigo()/baldes;
            bucket[hash].inserirRegNoFinal(regI);
        }
        
        //ordenar os buckets
        for(int i=0 ; i < bucket.length ; i++)
            bucket[i].insercaoDireta();
        
        //gravar de volta passando por todos os buckets
        for(int i=0 ; i < bucket.length ; i++)
        {
            arqJava = bucket[i];
            
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
                vetAux[ (regI.getCodigo()/exp) % 10 ]++;
            }
            
            //gera acumulativa da frequencia
            for(int i=1 ; i < 10 ; i++)
                vetAux[i] = vetAux[i-1];
            
            //gera o vetor ordenado
            seekArq(0);
            for(int i=0 ; i < tl ; i++)
            {
                regI.leDoArq(arquivo);
                vetSaida[ --vetAux[ (regI.getCodigo()/exp) % 10 ]] = regI.getClone(); 
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
        
    }
    
    public void comb()
    {
        
    }
    
    public void tim()
    {
        
    }
    
    public void geraArquivoOrdenado() { }
    public void geraArquivoReverso() { }
    public void geraArquivoRandomico() { }
}
