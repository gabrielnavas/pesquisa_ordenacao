package Dados;

public class Dados {
    
    public final static int TL_GERAL = 16;
    
    
    public static int[] getDadosInt()
    {
        int[] dados = new int[TL_GERAL];
        
        for(int i=0, j=TL_GERAL ; i < TL_GERAL ; i++, j--)
            dados[i] = j;
        
        dados[4+1] = dados[4];
        dados[8+1] = dados[8];
        dados[14+1] = dados[14];
        
        return dados;
    }
}
