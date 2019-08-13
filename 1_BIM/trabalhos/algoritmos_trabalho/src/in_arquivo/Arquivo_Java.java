package in_arquivo;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;

//... classe Entrada (possui m�todos para entrada de dados) ....
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

//... classe Registro (ser� um objeto que simboliza o registro em pascal) ....
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
        System.out.print("codigo....." + this.codigo);
        System.out.print(" nome.......");
        String Snome = new String(nome);
        System.out.print(Snome);
        System.out.println(" idade......." + this.idade);
        System.out.println("----------------------------------");
    }

    static int length()
    {
        return (52); //int codigo, tl=20, idade; ------------> 12 bytes
        //private char nome[] = new char[20]; --> 40 bytes
        //------------------------------------------------- +
        //                      Total : 40 + 12 = 52 bytes
    }
}


//... classe Arquivo (onde vai estar o método para ordernar, etc) ....
public class Arquivo_Java
{
    private String nomearquivo;
    private RandomAccessFile arquivo;

    public Arquivo_Java(String nomearquivo)
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

    public void exibirArq()
    {
        int i;
        Registro reg = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof())
        {
            System.out.println("Posicao " + i);
            reg.leDoArq(arquivo);
            reg.exibirReg();
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

    private int fileSize() {
        int tamanhoArquivo = 0;
        int totalBytes = 0;
        
        try 
        {
            totalBytes = (int) arquivo.length();
            tamanhoArquivo = totalBytes * Registro.length();
        } 
        catch (IOException ex) {}
        
        return (tamanhoArquivo);
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
    
    public void importarDados(Registro[] registros)
    {
        for(int i=0 ; i < registros.length ; i++)
            inserirRegNoFinal(registros[i]);
//            System.out.println(
//                    registros[i].getCodigo() + " - " +
//                    registros[i].getNome() + " - " +
//                    registros[i].getIdade()
//            );
    }
    
    //.............................................................................
    /*

    insira aqui os métodos de Ordenação;

    */
    
    public void insercaoDireta()
    {
        Registro regI=null, 
                 regAux=null;
        int i, pos, tl;
        
        tl = fileSize();
        
        i=1;
        while(i < tl)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            seekArq(i);
            regAux.leDoArq(arquivo);
            pos=i;
            while(pos > 0 && regAux.getCodigo())
        }
    }
    
    public void selecaoDireta()
    {
        Registro regI = null,
                 regJ = null,
                 regMenor = null;
        
        int menor, 
            posMenor,
            i, j, tl;
        
        tl=fileSize();
        
        i=0;
        while(i < tl)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            posMenor=i;
            menor=regI.getCodigo();
            
            j=i+1;
            while(j < tl+1)
            {
                seekArq(j);
                regJ.leDoArq(arquivo);
                if(regJ.getCodigo() < menor)
                {
                    posMenor=j;
                    menor=regJ.getCodigo();
                }
                j++;
            }
            
            seekArq(posMenor);
            regI.gravaNoArq(arquivo);
            
            seekArq(posMenor);
            regMenor.leDoArq(arquivo);
            
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
            
    public void executa()
    {
//        leArq();
        importarDados(Dados.getRegistros());
        exibirArq();
    }

    //m�todo principal
    public static void main(String args[])
    {
        Arquivo_Java a = new Arquivo_Java("arquivo.dat");
        a.executa();
    }

}
