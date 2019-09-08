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
public class NoReg_EXCLUIR
{
    private int cod;
    private String nome;
    private int idade;

    private NoReg_EXCLUIR prox;
    private NoReg_EXCLUIR ant;
    
    public NoReg_EXCLUIR() { }
    
    public NoReg_EXCLUIR(int cod, String nome, int idade, NoReg_EXCLUIR ant, NoReg_EXCLUIR prox) {
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

    public NoReg_EXCLUIR getProx() {
        return prox;
    }

    public void setProx(NoReg_EXCLUIR prox) {
        this.prox = prox;
    }

    public NoReg_EXCLUIR getAnt() {
        return ant;
    }

    public void setAnt(NoReg_EXCLUIR ant) {
        this.ant = ant;
    }
}
