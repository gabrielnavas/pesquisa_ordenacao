/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos_ordenacao.arquivo;

/**
 *
 * @author navas
 */
public class NoReg
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
