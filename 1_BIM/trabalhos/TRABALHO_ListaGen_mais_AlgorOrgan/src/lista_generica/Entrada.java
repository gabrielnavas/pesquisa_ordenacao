package lista_generica;

import java.util.Scanner;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class Entrada {
    
    public static String lerString(String msg)
    {
        Scanner input = new Scanner(System.in);
        System.out.print(msg);
        
        String str = input.nextLine();
        
        return str;
    }
}
