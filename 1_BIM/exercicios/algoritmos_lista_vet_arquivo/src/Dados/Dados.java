package Dados;

public class Dados {
    
    private static int range_inicio = 0;
    private static int range_fim = 1024;
    
//    public static int TL_GERAL =  39;
    public static int TL_GERAL =   Math.abs(range_inicio) + Math.abs(range_fim);
    
    private static int[] gerarDadosIntAleatorios(int rangeInicio, int rangeFim, int tamanhoFisico)
    {
        int[] dados = new int[tamanhoFisico];
        
        for(int i=0 ; i < tamanhoFisico ; i++)
            dados[i] = 0;


        for(int i=0 ; i < tamanhoFisico ; i++)
            dados[i] = (int)(Math.random() * rangeFim ) + rangeInicio;

//        return new int[]{2,11,15,5,7,5};
        return dados;
    }
    
    public static int[] getDadosInt()
    {   
        return gerarDadosIntAleatorios(range_inicio, range_fim, TL_GERAL);
    }
}
