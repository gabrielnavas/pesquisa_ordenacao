package simplesmeste;

public class ListaEstado {
    
    private NoEstado inicio;
//    private int tl; 
    
    public ListaEstado()
    {
        this.inicio = null;
    }
    
    public void iniciar()
    {
        this.inicio = null;
    }
    
    public void inserirOrdenado(String estado, String cidade)
    {
        NoEstado ant=null, atual=null, novo=null;
        NoEstado noEstado = buscarEstado(estado);
        
        if(noEstado != null)
            noEstado.getListaCidades().inserirOrdenado(cidade);
        else
        {
            novo = new NoEstado(estado, cidade, null);
            
            if(inicio == null)
                inicio = novo;
            else
            {
                ant = null;
                atual = inicio;

                while(atual != null && atual.getNome().compareTo(estado) < 0)
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
    
    public NoEstado buscarEstado(String nomeEst)
    {
        NoEstado aux = inicio;
        while(aux != null && !aux.getNome().equals(nomeEst))
            aux = aux.getProx();
        
        if(aux != null && aux.getNome().equals(nomeEst))
            return aux;
        return null;
    }
    
    public NoCidade buscarCidade(String cidade)
    {
        NoEstado noEst = null;
        NoCidade noCid = null;
        
        noEst = inicio;
        while(noEst != null && noCid != null)
        {
            noCid = noEst.getListaCidades().buscarCidade(cidade);
            noEst = noEst.getProx();
        }
        
        return noCid;
    }
    
    public boolean verificarEstadoCidade(String estado, String cidade)
    {
        NoEstado noEst = buscarEstado(estado);
        NoCidade noCid = null;
        
        if(noEst != null)
            noCid = noEst.getListaCidades().buscarCidade(cidade);
        
        return noCid != null;
    }
    
    
    public void removerEstado(String estado)
    {
        NoEstado ant, atual;
        
        ant = null;
        atual = inicio;
        
        while(atual != null && !atual.getNome().equals(estado))
        {
            ant = atual;
            atual = atual.getProx();
        }
       
        if(atual != null)
        {
            if(ant == null)
                inicio = inicio.getProx();
            else 
                ant.setProx(atual.getProx());
        }
    }
    
    
    public void exibirRelatorioGeral()
    {
        NoEstado estado = inicio;
        while(estado != null)
        {
            System.out.println("Estado: " + estado.getNome() + " Cidades => ");
            estado.getListaCidades().exibirRelatorioGeral();
            
            estado = estado.getProx();
        }
    }
    
    public void exibirRelatorioEstado(String nomeEst)
    {
        NoEstado noEst = buscarEstado(nomeEst);
        
        if(noEst != null)
        {
            System.out.println("Estado: " + nomeEst);
            noEst.getListaCidades().exibirRelatorioGeral();
        }
    }
}
