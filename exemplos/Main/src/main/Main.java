package main;

import my_algos.ArvoreAbb;
import my_algos.Pilha;
import my_algos.Vetor;

public class Main {
    
    static int[] dados = new int[]{5,4,7,1,3,9,2,15};
    
    public static void pilha() {
        Pilha p = new Pilha();
        for(int i=0 ; i < dados.length ; i++)
            p.push(dados[i]);
        
        p.exibir();
    }
    
    public static void vetor() {
        Vetor v = new Vetor(8);
        
       
        for(int i=0 ; i < dados.length ; i++)
            v.inserir(dados[i]);
        
        v.ordenar_exaustivo();
        v.exibir();
       
        System.out.println( 
                (v.busca_exaustiva(55) != -1) ? "achou":"nao achou" 
        );
    }
    
    public static void arvore_abb() {
        ArvoreAbb abb = new ArvoreAbb();
        
        for(int i=0 ; i < dados.length ; i++)
            abb.inserir(dados[i]);
        
        abb.exibir();
    }
    
    public static void main(String[] args) {
//        pilha();
//        vetor();
        arvore_abb();
    }
}
