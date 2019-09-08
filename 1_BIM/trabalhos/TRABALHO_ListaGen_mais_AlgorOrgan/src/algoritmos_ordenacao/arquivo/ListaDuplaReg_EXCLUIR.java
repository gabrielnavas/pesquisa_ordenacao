package algoritmos_ordenacao.arquivo;


/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class ListaDuplaReg_EXCLUIR
{
    private NoReg_EXCLUIR inicio;
    private NoReg_EXCLUIR fim;
    
    public ListaDuplaReg_EXCLUIR()
    {
        inicio=null;
        fim = null;
    }
    
    public void inserirFinal(int cod, String nome, int idade)
    {
        NoReg_EXCLUIR novo = new NoReg_EXCLUIR(cod, nome, idade, fim, null);
        
        if(fim == null)
            inicio = fim = novo;
        else
        {
            novo.setProx(novo);
            fim = novo;
        }
    }
    
    public int getTl()
    {
        int cont = 0;
        NoReg_EXCLUIR no = inicio;
        
        while(no != null)
        {
            cont++;
            no = no.getProx();
        }
        
        return cont;
    }
    
    public void insercaoDireta()
    {
        NoReg_EXCLUIR noI = inicio.getProx();
        NoReg_EXCLUIR noAux;
        String auxStr;
        int auxCod, auxIdade;
        int pos;
        int tl = getTl();
        
        for(int i=1 ; i < tl ; i++)
        {
            auxStr = noI.getNome().toString();
            auxCod = noI.getCod();
            auxIdade = noI.getIdade();
            noAux = noI;
            
            pos = i;
            while(pos > 0 && noAux.getAnt().getCod() > noAux.getCod())
            {
                noAux.setNome(noAux.getAnt().getNome());
                noAux.setIdade(noAux.getAnt().getIdade());
                noAux.setCod(noAux.getAnt().getCod());
                
                noAux = noAux.getAnt();
            }
            
            noAux.setCod(auxCod);
            noAux.setIdade(auxIdade);
            noAux.setNome(auxStr);
        }
    }
}
