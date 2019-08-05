package Model;

class ListaAutores {
    
    private NoAutor inicio;

    public ListaAutores() {
    }
    
    public void exibirAutores()
    {
        NoAutor aux = inicio;
        while(aux != null)
        {
            System.out.println(aux);
            aux = aux.getProx();
        }
    }
}
