package Dados;

public class Dados {
    
    private final static int TL_GERAL = 22;
    
    
    public static int[] getDadosInt()
    {
        int[] dados = new int[TL_GERAL];
        
        for(int i=0, j=TL_GERAL ; i < TL_GERAL ; i++, j--)
            dados[i] = j;
        
        return dados;
    }
}
