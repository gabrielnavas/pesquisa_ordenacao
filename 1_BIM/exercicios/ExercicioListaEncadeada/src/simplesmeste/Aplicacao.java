package simplesmeste;

import simplesmeste.ListaEstado;

public class Aplicacao {

    private ListaEstado listaEstados;
    
    public Aplicacao()
    {
        listaEstados = new ListaEstado();
    }
    
    public void inserirDados(String[][] dados)
    {
        int i, j, k;
        String estado, cidade;
        
        for(i=0 ; i < dados.length ; i++)
        {
            estado = dados[i][0];
            
            for(j=1 ; j < dados[i].length ; j++)
            {
                cidade = dados[i][j];
                listaEstados.inserirOrdenado(estado, cidade);
            }
        }        
    }
    
    public void relatorioGeral()
    {
        listaEstados.exibirRelatorioGeral();
    }
}