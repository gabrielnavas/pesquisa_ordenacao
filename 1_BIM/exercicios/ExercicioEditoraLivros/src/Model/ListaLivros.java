package Model;

public class ListaLivros {
    
    private NoLivro inicio;
    
    ListaLivros() 
    {
        this.inicio = null;
    }
    
    public void exibirLivros()
    {
        NoLivro aux = inicio;
        while(aux != null)
        {
            System.out.println(aux);
            aux = aux.getProx();
        }
    }
    
    public NoLivro buscarLivro(String titulo)
    {
        NoLivro aux = inicio;
        while(aux != null && !aux.getTitulo().equals(titulo))
            aux = aux.getProx();
        
        if(aux != null)
            return aux;
        return null;
    }
}
