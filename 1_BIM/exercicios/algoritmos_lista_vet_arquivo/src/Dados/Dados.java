package Dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dados {
    
    
    public static int[] getDadosInt()
    {
        List lista = new ArrayList();

        for(int i = 0; i < 1024; i++)
            lista.add(i+1);
        
        Collections.shuffle(lista);
        
        Integer[] dados = new Integer[lista.size()];
        int[] dados2 = new int[lista.size()];
        
        lista.toArray(dados);
        
        for(int i=0 ; i < lista.size() ; i++)
            dados2[i] = dados[i];
        
        
//        return new int[]{2,11,15,5,7,5};
        return dados2;
    }
}
