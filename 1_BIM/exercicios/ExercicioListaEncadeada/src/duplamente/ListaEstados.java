package duplamente;

public class ListaEstados {
    
    private NoEstado inicio;
    private NoEstado fim;
    private int tl;
    
    public ListaEstados()
    {
        this.inicio=null;
        this.fim=null;
        this.tl=0;
    }
    
    public void init()
    {
        this.inicio=this.fim=null;
    }
    
    public NoEstado buscarInit(String estado)
    {
        NoEstado aux = inicio;
        while(aux != null && aux.getNome().compareTo(estado) != 0)
            aux=aux.getProx();
        
        return ( aux != null && aux.getNome().compareTo(estado) == 0 ) ? aux : null;
    }
    
    public NoEstado buscaFim(String estado)
    {
        NoEstado aux = fim;
        while(aux != null && aux.getNome().compareTo(estado) != 0)
            aux=aux.getAnt();
        
        return ( aux != null && aux.getNome().compareTo(estado) == 0 ) ? aux : null;
    }
    
    public void inserirFinal(String estado, ListaCidades lc)
    {
        NoEstado noBusca,
                 noNovo = new NoEstado(estado, lc, fim, null);
        
        if(fim == null)
            inicio = fim = noNovo;
        else
        {
            noBusca = buscaFim(estado);
            
            if(noBusca != null)
                remover(noBusca);
                
            fim = noNovo;
        }
        
        this.tl++;
    }
    
    public void remover(NoEstado noEstado)
    {
        if(inicio != null)
        {
            noEstado.getListaCidades().init(); //liberar cidades??, precisa??
            
            if(inicio == noEstado && fim == noEstado)
            {
                init();
            }
            else if(inicio == noEstado)
            {
                if(inicio.getProx() == fim)
                {
                    inicio = fim;
                    inicio.setAnt(null);
                }
                else
                {
                    inicio = inicio.getProx();
                }
            }
            else if(fim == noEstado)
            {
                if(fim.getAnt() == inicio)
                {
                    fim = fim.getAnt();
                    fim.setProx(null);
                }
            }
            else
            {   
                noEstado.getProx().setAnt(noEstado.getAnt());
                noEstado.getAnt().setProx(noEstado.getProx());
            }
        }
        
        this.tl--;
    }
    
    public void remover(String nomeEst)
    {
        if(inicio != null)
        {
            if(inicio.getNome().equals(nomeEst) && inicio.getNome().equals(nomeEst))
            {
                inicio.getListaCidades().init(); // precisa?
                init();
            }
            else if(inicio.getNome().equals(nomeEst))
            {
                inicio.getListaCidades().init();
                
                if(inicio.getProx() == fim)
                    inicio = fim;
                else
                    inicio = inicio.getProx();
                
                inicio.setAnt(null);
            }
            else if(fim.getNome().equals(nomeEst))
            {
                fim.getListaCidades().init();
                
                if(fim.getAnt() == inicio)
                    fim = inicio;
                else
                    fim = fim.getAnt();
                
                fim.setProx(null);
            }
            else
            {
                NoEstado aux = inicio;
                while(aux !=null && aux.getNome().compareTo(nomeEst) != 0)
                    aux = aux.getProx();
                
                if(aux != null && aux.getNome().equals(nomeEst))
                {
                    aux.getProx().setAnt(aux.getAnt());
                    aux.getAnt().setProx(aux.getProx());
                }
            }
        }
        
        this.tl--;
    }
    
    public void ordenarSelecaoDireta()
    {
//        if(inicio == null) return;
//        
//        if(inicio.getProx() == null) return;
        
        NoEstado i = null, 
                 pos = null;
        String nomeEst = null;
        ListaCidades lc = null;
        
        i = inicio.getProx();
        
        while(i != null)
        {
            pos=i;
            nomeEst=i.getNome();
            lc=i.getListaCidades();
            
            while(i != inicio && i.getAnt().getNome().compareTo(nomeEst) < 0)
            {
                i.setNome(i.getAnt().getNome());
                i.setListaCidades(i.getAnt().getListaCidades());
                i=i.getAnt();
            }
            
            i.setNome(nomeEst);
            i.setListaCidades(lc);
            i=i.getProx();
        }
    }
    
    public void selecaoDireta()
    {
        if(inicio == null) return;
        if(inicio.getProx() == null) return;
        
        NoEstado i = null,
                 j = null,
                 posMaior = null;
                 
        String maiorEst = null;
        ListaCidades maiorLc = null;
        
        i=inicio;
        while(i != fim)
        {
            posMaior=i;
            maiorEst=posMaior.getNome();
            maiorLc = posMaior.getListaCidades();
            
            j=i.getProx();
            while(j != null)
            {
                if(j.getNome().compareTo(maiorEst) > 0)
                {
                    posMaior=j;
                    maiorEst = j.getNome();
                    maiorLc = j.getListaCidades();    
                }
                
                j=j.getProx();
            }
            
            posMaior.setNome(i.getNome());
            posMaior.setListaCidades(i.getListaCidades());
            i.setNome(maiorEst);
            i.setListaCidades(maiorLc);
            
            i=i.getProx();
        }
    }
    
    public void bolha()
    {
        NoEstado i = null, 
                 tl = null;
        
        String auxEst = null;
        ListaCidades auxLc = null;
        
        tl = fim;
        while(tl != fim)
        {
            i=inicio;
            while(i != tl)
            {
                if(i.getNome().compareTo(i.getProx().getNome()) > 0)
                {
                    auxEst = i.getNome();
                    auxLc = i.getListaCidades();
                    
                    i.setNome(i.getProx().getNome());
                    i.setListaCidades(i.getProx().getListaCidades());
                    
                    i.getProx().setNome(auxEst);
                    i.getProx().setListaCidades(auxLc);
                }
                i=i.getProx();
            }
            tl=tl.getAnt();
        }
    }

    public int getTl() {
        return tl;
    }
    
    public void shell()
    {
        int i,j,k,dist=4,tl;
        
        NoEstado noI=null,
                 noJ=null,
                 noK=null;
        
        String auxEst=null;
        ListaCidades auxLc=null;
                
        tl = getTl();
                
        while(dist > 0)
        {
            noI=inicio;
            for(i=0 ; i < dist ; i++)
            {
                noJ=noI;
                for(j=i ; j+dist < tl ; j+= dist)
                {
                    if(noJ.getNome().compareTo(noJ.getProx().getNome()) > 0)
                    {
                        auxEst = noJ.getNome();
                        auxLc = noJ.getListaCidades();
                    }
                }
            }
        }
    }
}
