/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Aluno
 */
public class Arquivo {

    private RandomAccessFile arq;

    public Arquivo(RandomAccessFile arq) {
        this.arq = arq;
    }
    
    public void truncate(int pos) //desloca eof
    {
        try
        {
            arq.setLength(pos * Registro.length());
        } catch (IOException exc)
        { }
    }
    
    public boolean eof()  
    {
        boolean retorno = false;
        try
        {
            if (arq.getFilePointer() == arq.length())
                retorno = true;                               
        } catch (IOException e)
        { }
        return (retorno);
    }
    
    public int filesize()
    {
        int i=0;
        Registro reg = new Registro();
        seekArq(0);
        while(!eof())
        {
            reg.leDoArq(arq);
            i++;
        }
        return i;
    }
    
    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arq);
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
            aux.leDoArq(arq);
            aux.exibirReg();
            i++;
        }
    }
    
    public void exibirUmRegistro(int pos)
    {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arq);
        aux.exibirReg();
    }
    
    public void seekArq(int pos)
    {
        try
        {
            arq.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }

    public void leArq()
    {
        int codigo, idade;
        String nome;
        codigo = Entrada.leInteger("Digite o código");
        while (codigo != 0)
        {
            nome = Entrada.leString("Digite o nome");
            idade = Entrada.leInteger("Digite a idade");
            inserirRegNoFinal(new Registro(codigo, nome, idade));
            codigo = Entrada.leInteger("Digite o código");
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
