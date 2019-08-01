package my_algos;

public class ArvoreAbb {
    
    private int nExibir;
    private NoTree raiz;
    
    public void ArvoreAbb() {
        this.raiz = null;
    }
    
    public void inserir(int info) {
        NoTree novo = new NoTree(info);
        
        NoTree aux = null;
        NoTree ante = null;
        
        if(raiz == null) 
            raiz = novo;
        else {
            aux = raiz;
            while(aux != null) {
                ante = aux;
                if(info < aux.getInfo())
                    aux = aux.getEsq();
                else
                    aux = aux.getDir();
            }
            
            if(info < ante.getInfo())
                ante.setEsq(novo);
            else
                ante.setDir(novo);
        }
    }
    
    private void exibir_start(NoTree no) {
        if(no != null) {
            this.nExibir++;
            
            this.exibir_start(no.getDir());
            
            for(int i=0 ; i < this.nExibir * 5 ; i++)
                System.out.printf(" ");
            
            System.out.printf("%d\n", no.getInfo());
            
            this.exibir_start(no.getEsq());
            
            this.nExibir--;
        }
    }
    
    public void exibir() {
        nExibir = -1;
        exibir_start(this.raiz);
    }
}
