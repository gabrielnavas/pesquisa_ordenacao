/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista_generica;

/**
 *
 * @author navas
 */
public class No extends NoGen
{
    private NoGen head;
    private NoGen tail;

    public No() {
        head = null;
        tail = null;
    }

    public No(NoGen head, NoGen tail) {
        this.head = head;
        this.tail = tail;
    }

    public NoGen getHead() {
        return head;
    }

    public void setHead(NoGen head) {
        this.head = head;
    }

    public NoGen getTail() {
        return tail;
    }

    public void setTail(NoGen tail) {
        this.tail = tail;
    }
}