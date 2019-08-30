package arquivo;


import java.io.RandomAccessFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
public class Registro {
    private int codigo;
    private int tl;
    private int idade;
    private String nome;

    public Registro() {
    }

    public Registro(int codigo, String nome, int idade) {
        this.codigo = codigo;
        this.idade = idade;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void gravaNoArq(RandomAccessFile arq)
    {
        
    }
    
    public void leDoArq(RandomAccessFile arq)
    {
        
    }
    
    public void exibirReg()
    {
        
    }
    
    public static int length()
    {
        return 52;
    }
}
