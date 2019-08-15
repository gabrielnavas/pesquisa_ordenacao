package simplesmeste;

class ListaCidade {
    
    private NoCidade cidades;
    
    public ListaCidade()
    {
        cidades=null;
    }
    
    public void init()
    {
        cidades=null;
    }
    
    public NoCidade buscar(String nomeCid)
    {
        NoCidade aux = cidades;
        
        while(aux != null && !aux.getNome().equals(nomeCid))
            aux=aux.getProx();
    
        if(aux != null && aux.getNome().equals(nomeCid))
            return aux;
        return null;
    }
    
    public void inserirOrdenado(String nomeCid)
    {
        NoCidade noCidade = buscar(nomeCid);
        NoCidade ant, atual, novo;
        
        if(noCidade == null)
        {
            novo = new NoCidade(nomeCid, null);
            ant = null;
            atual = cidades; //5   1 4 6 7
            
            while(atual != null && nomeCid.compareTo(atual.getNome()) > 0)
            {
                ant = atual;
                atual = atual.getProx();
            }
            
            if(ant == null)
            {
                novo.setProx(cidades);
                cidades = novo;
            }
            else 
            {
                novo.setProx(atual);
                ant.setProx(novo);
            }
        }
    }
    
    public void inserirOrdenado(ListaCidade listaCid)
    {
        NoCidade noCid = listaCid.getCidades();
        while(noCid != null)
        {
            inserirOrdenado(noCid.getNome());
            noCid = noCid.getProx();
        }
    }

    public NoCidade getCidades() {
        return cidades;
    }

    public void exibirRelatorioGeral() {
        NoCidade noCidade = cidades;
        while(noCidade != null)
        {
            System.out.println(noCidade.getNome());
            noCidade = noCidade.getProx();
        }
    }
    
    
}
