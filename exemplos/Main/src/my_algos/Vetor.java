package my_algos;

public class Vetor {
    
    private int tl;
    private int[] vet;
    
    public Vetor(int tf) {
        this.tl=0;
        this.vet = new int[tf+1];
    }
    
    public void inserir(int valor) {
        vet[tl] = valor;
        tl++;
    }
    
    public int busca_exaustiva(int chave) {
        vet[tl] = chave;
        int i = 0;
        while(vet[i] != chave)
            i++;
        
        if(i < tl)
            return i;
        return -1;
    }
    
    public void ordenar_exaustivo() {
        int i, j, aux;
        
        for(i=0 ; i < tl ; i++)
            for(j=0 ; j < tl ; j++) {
                if(vet[i] > vet[j]) {
                    aux = vet[i];
                    vet[i] = vet[j];
                    vet[j] = aux;
                }
            }
            
    }

    public int getTl() {
        return tl;
    }
    
    public void exibir() {
        System.out.printf("Dados:\nIndex | Valor.\n");
        int i=0;
        while(i < tl) {
            System.out.printf("%d: %d\n", i, vet[i]);
            i++;
        }
    }
}
