package Model;

public class ListaEditores 
{
    private NoEditora inicio;
    
    ListaEditores() {}
    
    public void inserirOrdenado(String editora, ListaLivros livros) 
    {
        NoEditora nova = new NoEditora(editora, livros, inicio);
        
        if(inicio == null)
            inicio = nova;
        else
        {
            if(editora.compareTo(inicio.getEditora()) < 0)
            {
                nova.setProx(inicio);
                inicio = nova;
            }
            else
            {
                NoEditora ante = null;
                NoEditora atual = inicio;
                
                while(atual != null && editora.compareTo(atual.getEditora()) < 0)
                {
                    ante = atual;
                    atual = atual.getProx();
                }
                
                nova.setProx(atual);
                ante.setProx(nova);
            }
        }
    }
    
    public void remover(String editora)
    {
        NoEditora atual = inicio;
        NoEditora ante = null;
        
        while(atual != null && editora.equals(atual.getEditora()))
        {
            ante = atual;
            atual = atual.getProx();
        }
        
        if(atual != null)
        {
            if(ante == null)
                inicio = inicio.getProx();
            else
                ante.setProx(atual.getProx());
        }
    }
    
    public NoEditora buscarEditora(String editora)
    {
        NoEditora aux = inicio;
        while(aux != null && !aux.getEditora().equals(editora))
            aux = aux.getProx();
        
        if(aux != null)
            return aux;
        return null;
    }
}
