package main;

import java.util.Scanner;
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
//        arvore_abb();

        String nome1 = null;
        String nome2 = null;
        
        Scanner input = new Scanner(System.in);
        nome1 = input.next();
        nome2 = input.next();
        
        System.out.println(nome1 + " tamanho: " + nome1.length());
        System.out.println(nome2 + " tamanho: " + nome2.length());
        
        int compareTo = nome1.compareTo(nome2);
        System.out.println(compareTo);
//        System.out.println(nome1.compareTo(nome2) ? nome1+" é maior que " + nome2 : nome2+" é maior que "+nome1);
    }
}
