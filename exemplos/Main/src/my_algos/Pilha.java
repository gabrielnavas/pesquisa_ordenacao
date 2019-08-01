/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_algos;

/**
 *
 * @author navas
 */
public class Pilha {
    
    private No no;
    
    public Pilha() {
        this.no = null;
    }
    
    public void push(int info) {
        No novo = new No(info);
        
        novo.setProx(this.no);
        this.no = novo;
    }
    
    public int pop() {
        No elem = this.no;
        this.no.setProx(this.no.getProx());
        return elem.getInfo();
    }

    public void exibir() {
        No aux = this.no;
        while(aux != null) {
            System.out.println(aux.getInfo());
            aux = aux.getProx();
        }
    }
    
    
}
