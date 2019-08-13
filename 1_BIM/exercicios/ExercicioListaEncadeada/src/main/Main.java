package main;

import java.util.Scanner;

public class Main {

    static ListaEstado estados;
    
    public static String lerString(String msg)
    {
        String line = "";
        Scanner in = new Scanner(System.in);
        System.out.println(msg);
        line = in.next();
        
        return line;
    }
    
    public static String[] getDadosEstados()
    {   
        return new String[]{
            "Acre", "Alagoas",
            "Amapá", "Amazonas",
            "Bahia", "Ceará",
            "Distrito Federal", "Espírito Santo",
            "Goiás", "Maranhão",
            "Mato Grosso", "Mato Grosso do Sul",
            "Minas Gerais", "Pará",
            "Paraíba", "Paraná",
            "Pernambuco", "Piauí",
            "Rio de Janeiro", "Rio Grande do Norte",
            "Rio Grande do Sul", "Rondônia",
            "Roraima", "Santa","São Paulo", 
            "Sergipe","Tocantins",
        };
    }
    
    public static void inserirDados()
    {
        String cidade;
        String[] dados = getDadosEstados();
        NoEstado noEstado;
        ListaCidade cidades;
        boolean stop = false;
        
        for(int i=dados.length-1  ; i > -1 && !stop ; i--)
        {
            cidades = new ListaCidade();
            
            cidade = lerString("cidade para "+ dados[i] +": ");
            while(!cidade.equals("0") && !stop)
            {
                if(cidade.equals("1"))
                    stop = true;
              
                cidades.inserirOrdenado(cidade);
                cidade = lerString("");
            }
            
            estados.inserirOrdenado(dados[i], cidades);
        }
    }
        
    
    public static void main(String[] args) {
        estados = new ListaEstado();
        inserirDados();
        
    }
    
}
