package simplesmeste;

public class ListaCidade {
    
    private NoCidade inicio;
    
    public ListaCidade()
    {
        this.inicio=null;
    }
    
    public void iniciar()
    {
        this.inicio=null;
    }
    
    public NoCidade buscarCidade(String cidade)
    {
        NoCidade aux = inicio;
        
        while(aux != null && !aux.getNome().equals(cidade))
            aux=aux.getProx();
    
        if(aux != null && aux.getNome().equals(cidade))
            return aux;
        return null;
    }
    
    public void inserirOrdenado(String cidade)
    {
        NoCidade ant=null, atual=null, novo=null;
        
        novo = new NoCidade(cidade, null);
        
        if(inicio == null)
            inicio = novo;
        else
        {
            NoCidade noBusca = buscarCidade(cidade);
            
            if(noBusca == null)
            {
                ant = null;
                atual = inicio;
                
                while(atual != null && cidade.compareTo(atual.getNome()) > 0)
                {
                    ant = atual;
                    atual = atual.getProx();
                }

                if(ant == null)
                {
                    novo.setProx(inicio);
                    inicio = novo;
                }
                else 
                {
                    novo.setProx(atual);
                    ant.setProx(novo);
                }
            }
        }
    }

    public void exibirRelatorioGeral() {
        NoCidade noCidade = null;
        
        noCidade = inicio;
        while(noCidade != null)
        {
            System.out.println(noCidade.getNome());
            noCidade = noCidade.getProx();
        }
    }
}
