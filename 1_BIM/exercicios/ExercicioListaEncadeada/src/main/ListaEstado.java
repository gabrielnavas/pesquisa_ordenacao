package main;

public class ListaEstado {
    
    private NoEstado inicio;
    
    ListaEstado()
    {
        this.inicio = null;
    }
    
    public void init()
    {
        this.inicio = null;
    }
    
    public NoEstado buscar(String nomeEst)
    {
        NoEstado aux = inicio;
        while(aux != null && !aux.getNome().equals(nomeEst))
            aux = aux.getProx();
        
        if(aux != null && aux.getNome().equals(nomeEst))
            return aux;
        return null;
    }
    
    public void inserirInicio(String nomeEst, ListaCidade listaCid)
    {
        NoEstado novo = new NoEstado(nomeEst, listaCid, inicio);
        
        if(inicio == null)
            inicio = novo;
        else
        {
            NoEstado busca = buscar(nomeEst);
            
            if(busca != null)
                busca.getListaCidades().inserirOrdenado(listaCid);
            else
                inicio = novo;
        }
    }
    
    public void inserirFinal(String nomeEst, ListaCidade listaCid)
    {
        NoEstado novo = new NoEstado(nomeEst, listaCid, null);

        if(inicio == null)
            inicio = novo;
        else
        {
            NoEstado noEstado = buscar(nomeEst);

            if(noEstado != null)
                noEstado.getListaCidades().inserirOrdenado(listaCid);
            else
            {
                while(noEstado.getProx() != null)
                    noEstado = noEstado.getProx();

                noEstado.setProx(novo);
            }
        }
    }
    
    public void exibirRelatorioGeral()
    {
        NoEstado estado = inicio;
        while(estado != null)
        {
            System.out.println(estado.getNome() + ": ");
            estado.getListaCidades().exibirRelatorioGeral();
            
            estado = estado.getProx();
        }
    }
    
    public void exibirRelatorioEstado(String nomeEst)
    {
        NoEstado noEst = buscar(nomeEst);
        
        if(noEst != null)
        {
            System.out.println("Estado: " + nomeEst);
            noEst.getListaCidades().exibirRelatorioGeral();
        }
    }
    
    public void remover(String nomeEst)
    {
        NoEstado ant, atual, del;
        
        ant = null;
        atual = inicio;
        while(inicio != null && nomeEst.compareTo(atual.getNome()) != 0)
        {
            ant = atual;
            atual = atual.getProx();
        }
       
        if(ant == null)
            inicio = inicio.getProx();
        else if(atual != null)
            ant.setProx(atual.getProx());
        else
            ant.setProx(atual.getProx());
            
    }
    
    public void inserirOrdenado(String nomeEst, ListaCidade listaCid)
    {
        NoEstado ant, atual, novo;
        NoEstado noEstado = buscar(nomeEst);
        
        if(noEstado != null)
            noEstado.getListaCidades().inserirOrdenado(listaCid);
        else
        {
            novo = new NoEstado(nomeEst, listaCid, null);
            
            ant = null;
            atual = inicio;

            while(atual != null && atual.getNome().compareTo(nomeEst) < 0)
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
