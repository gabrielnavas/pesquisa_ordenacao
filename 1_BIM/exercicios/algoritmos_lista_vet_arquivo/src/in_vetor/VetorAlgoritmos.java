    package in_vetor;

import Dados.Dados;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pilha
{
    private int vet[];
    private int top;
    private int tf;
    
    public Pilha(int tf)
    {
        vet = new int[tf];
        top=-1;
        this.tf = tf;
    }
    
    public void push(int n)
    {   
        vet[++top] = n;
    }
    
    public int pop()
    {
        return vet[top--];
    }
    
    public boolean isFull()
    {
        return top == tf;
    }
    
    public boolean isEmpty()
    {
        return top == -1;
    }
}
    
class Vetor {
    
    private int[] vet;
    private int tl;
    
    public Vetor()
    {
        this.vet = new int[Dados.TL_GERAL];
        tl=0;
    }

    public int[] getVet() {
        return vet;
    }

    public int getTl() {
        return tl;
    }
    
    public void inserirFinal(int num)
    {
        this.vet[tl] = num;
        tl++;
    }

    public void exibir()
    {
        if(tl > 0)
            for(int i=0 ; i < tl ; i++)
                System.out.print("[" + vet[i] + "] ");
        else
            for(int i=0 ; i < tl ; i++)
                System.out.print("- ");
        System.out.println();
    }
    
    public boolean isOrdenado()
    {
        boolean ordenado = true;
        
        for(int i=0 ; i+1 < tl && ordenado ; )
        {
            if(vet[i] > vet[i+1])
                ordenado = false;
            else
                i++;
        }
        
        return ordenado;
    }
    
    // -- METODOS ORDENACAO -- //
    
    public void insercaoDireta()
    {
        int aux, pos;
        
        for(int i=1 ; i < tl ; i++)
        {
            aux=vet[i];
            pos=i;
            while(pos > 0 && vet[pos-1] > aux)
            {
                vet[pos] = vet[pos-1];
                pos--;
            }
            vet[pos] = aux;
        }
    }
    
    
    public int buscaBinariaSort(int chave, int tl)
    {
        int ini, fim, meio;
        
        ini=0;
        fim=tl-1;
        meio=fim/2;
        
        while(ini < fim && chave != vet[meio])
        {
            if(chave > vet[meio])
                ini = meio+1;
            else
                fim = meio-1;
            
            meio = (ini+fim)/2;
        }
        
        if(chave > vet[meio])
            return meio+1;
        return meio;
    }
    
    public void insercaoBinaria()
    {
        int aux, pos;
        
        for(int i=1; i < tl ; i++)
        {
            aux = vet[i];
            pos = buscaBinariaSort(aux, i);
            
            for(int j=i ; j > pos ; j--)
                vet[j] = vet[j-1];
            
            vet[pos] = aux;
        }
    }
    
    public void selecaoDireta()
    {
        int menor, posMenor;
        
        for(int i=0 ; i < tl-1 ; i++)
        {
            menor = vet[i];
            posMenor = i;
            for(int j=i+1 ; j < tl ; j++)
                if(vet[j] < menor)
                {
                    menor = vet[j];
                    posMenor = j;
                }
            vet[posMenor] = vet[i];
            vet[i] = menor;
        }
    }
    
    public void bolha()
    {
        int tl2, aux;
        
        tl2=tl;
        while(tl2 > 1)
        {
            for(int i=0 ; i < tl2-1 ; i++)
                if(vet[i] > vet[i+1])
                {
                    aux = vet[i];
                    vet[i] = vet[i+1];
                    vet[i+1] = aux;
                }
            
            tl2--;
        }
    }
    
    public void shake()
    {
        int ini, fim, aux;
        
        ini = 0;
        fim = tl-1;
        while(ini < fim)
        {
            for(int i=ini ; i < fim ; i++)
                if(vet[i] > vet[i+1])
                {
                    aux = vet[i];
                    vet[i] = vet[i+1];
                    vet[i+1] = aux;
                }
            
            fim--;
            
            for(int i=fim ; i > ini ; i--)
                if(vet[i] < vet[i-1])
                {
                    aux = vet[i];
                    vet[i] = vet[i-1];
                    vet[i-1] = aux;
                }
            
            ini++;
        }
    }
    
    public void shell()
    {
        int dist, aux;
        
        dist=4;
        while(dist > 0)
        {
            for(int i=0 ; i < dist ; i++)
                for(int j=i ; j+dist < tl ; j+=dist)
                    if(vet[j] > vet[j+dist])
                    {
                        aux = vet[j];
                        vet[j] = vet[j+dist];
                        vet[j+dist] = aux;
                        
                        for(int k=j ; k-dist >= 0 && vet[k] < vet[k-dist]; k-=dist)
                        {
                            aux = vet[k];
                            vet[k] = vet[k-dist];
                            vet[k-dist] = aux;
                        }
                    }
            
            dist = dist/2;
        }
    }
    
    public void heap()
    {
        int pai, fe, fd, aux, tl2, maiorf;
        
        tl2=tl;
        while(tl2 > 1)
        {
            pai = tl2/2-1;
            while(pai >= 0)
            {
                fe = pai+pai+1;
                fd = fe+1;
                if(fd < tl2)
                {
                    if(vet[fe] > vet[fd])
                        maiorf = fe;
                    else
                        maiorf = fd;
                }
                else
                    maiorf = fe;
                
                if(vet[maiorf] > vet[pai])
                {
                    aux = vet[maiorf];
                    vet[maiorf] = vet[pai];
                    vet[pai] = aux;
                }
                pai--;
            }
            
            aux = vet[0];
            vet[0] = vet[tl2-1];
            vet[tl2-1] = aux;

            tl2--;
        }
    }
    
    public void quickSP()
    {
        quickSP(0, tl-1);
    }
    
    public void quickSP(int ini, int fim)
    {
        int i = ini, j=fim, aux;
        
        while(i < j)
        {
            while(i < j && vet[i] <= vet[j])
                i++;
            
            aux = vet[i];
            vet[i]=vet[j];
            vet[j]=aux;
            
            while(i < j && vet[j] >= vet[i])
                j--;
            
            aux = vet[i];
            vet[i]=vet[j];
            vet[j]=aux;
        }
        
        if(ini < i-1)
            quickSP(ini, i-1);
        if(j+1 < fim)
            quickSP(j+1, fim);
    }
    
    
    public void quickSPIterativo()
    {
        int ini, fim, i, j, aux;
        
        Pilha p = new Pilha(5000);
        
        p.push(0); p.push(tl-1);
        
        while(!p.isEmpty())
        {
            fim=p.pop(); 
            ini=p.pop();
            
            i=ini;
            j=fim; 

            while(i < j)
            {
                while(i < j && vet[i] <= vet[j])
                    i++;

                aux = vet[i];
                vet[i]=vet[j];
                vet[j]=aux;

                while(i < j && vet[j] >= vet[i])
                    j--;

                aux = vet[i];
                vet[i]=vet[j];
                vet[j]=aux;
            }
            
            if(ini < i-1)
            {
                p.push(ini); 
                p.push(i-1);
            }
            
            if(j+1 < fim)
            {
                p.push(j+1); 
                p.push(fim);
            }
        }
    }
    
    
    public void quickSort()
    {
        quickSort(0, tl-1);
    }
    
    public void quickSort(int ini, int fim)
    {
        int i = ini, j=fim, aux;
        boolean flag=true;
        
        while(i < j)
        {
            if(flag)
                while(i < j && vet[i] <= vet[j])
                    i++;
            
            else
                while(i < j && vet[j] >= vet[i])
                    j--;
            
            aux = vet[i];
            vet[i]=vet[j];
            vet[j]=aux;
            flag=!flag;
        }
        
        if(ini < i-1)
            quickSP(ini, i-1);
        if(j+1 < fim)
            quickSP(j+1, fim);
    }
    
    public void quickSortIterativo()
    {
        int i, j, aux, ini, fim;
        boolean flag;
        
        Pilha p = new Pilha(5000);
        
        p.push(0);
        p.push(tl-1);
        
        while(!p.isEmpty())
        {
            fim = p.pop();
            ini = p.pop();
            
            i=ini;
            j=fim;
            flag= true;
            
            while(i < j)
            {
                if(flag)
                    while(i < j && vet[i] <= vet[j])
                        i++;

                else
                    while(i < j && vet[j] >= vet[i])
                        j--;

                aux = vet[i];
                vet[i]=vet[j];
                vet[j]=aux;
                flag=!flag;
            }
            
            if(ini < i-1)
            {
                p.push(ini);
                p.push(i-1);

            }

            if(j+1 < fim)
            {
                p.push(0);
                p.push(tl-1);
            }    
        }
    }
    
    public void quickCP()
    {
        quickCP(0, tl-1);
    }
    
    public void quickCP(int ini, int fim)
    {
        int i=ini, 
            j=fim, 
            pivo = vet[(ini+fim)/2], 
            aux;
        
        while(i<j)
        {
            while(vet[i] < pivo)
                i++;
            while(vet[j] > pivo)
                j--;
            
            if(i <= j)
            {
                aux = vet[i];
                vet[i]=vet[j];
                vet[j]=aux;
                
                i++;
                j--;
            }
        }
        
        if(ini < j)
            quickCP(ini, j);
        
        if(i < fim)
            quickCP(i, fim);
    }
    
    public void quickCPIterativo()
    {
        int i, j, ini, fim , aux , pivo; 
        
        Pilha p = new Pilha(5000);
        
        p.push(0);
        p.push(tl-1);

        while(!p.isEmpty())
        {
            fim = p.pop();
            ini = p.pop();
            pivo = vet[(ini+fim)/2];
            
            i=ini;
            j = fim;
            
            while(i<j)
            {
                while(vet[i] < pivo)
                    i++;
                while(vet[j] > pivo)
                    j--;

                if(i <= j)
                {
                    aux = vet[i];
                    vet[i]=vet[j];
                    vet[j]=aux;

                    i++;
                    j--;
                }
            }

            if(ini < j)
            {
                p.push(ini);
                p.push(j);
                
            }

            else if(i < fim)
            {                
                p.push(i);
                p.push(fim);
            }
        }
    }
    
    // =========================== merge 1
    public void merge1()
    {
        int seq = 1;
        int[] vet1 = new int[tl/2];
        int[] vet2 = new int[tl/2];
        
        while(seq < tl)
        {
            particao(vet1, vet2);
            fusao(vet1, vet2, seq);
            seq = seq * 2;
        }
    }
    
    public void particao(int[] vet1, int[] vet2)
    {
        int meio = tl/2;
        for(int i=0 ; i < meio ; i++)
        {
            vet1[i] = vet[i];
            vet2[i] = vet[i+meio];
        }
    }
    
    public void fusao(int[] vet1, int[] vet2, int seq)
    {
        int i=0, 
            j=0, 
            k=0, 
            aux_seq = seq;
        
        while(k < tl)
        {
            while(i < seq && j < seq)
            {
                if(vet1[i] < vet2[j])
                    vet[k++] = vet1[i++];
                else
                    vet[k++] = vet2[j++];
            }
            
            while(i < seq)
                vet[k++] = vet1[i++];

            while(j < seq)
                vet[k++] = vet2[j++];
        
            seq = seq + aux_seq;
        }
    }
    //===========================
    
    
    
    // =========================== merge 2
    public void merge2()
    {
        int aux[] = new int[tl];
        merge2(aux, 0, tl-1);
    }
    
    public void merge2(int[] aux, int esq, int dir)
    {
        int meio;
        
        if(esq < dir)
        {
            meio = (esq+dir)/2;
            merge2(aux, esq, meio);
            merge2(aux, meio+1, dir);
            fusao(aux, esq, meio, dir);
        }
    }
    
    public void fusao(int aux[], int ini, int meio, int fim)
    {
        int i=ini, 
            j=meio+1, 
            k=ini;
        
        while(i <= meio  && j <= fim)
            if(vet[i] < vet[j])
                aux[k++] = vet[i++];
            else
                aux[k++] = vet[j++];
            
        while(i <= meio)
            aux[k++] = vet[i++];
        
        while(j <= fim)
            aux[k++] = vet[j++];
        
        for(i=ini ; i <= fim ; i++)
            vet[i] = aux[i];
    }
    
    public void comb_simples()
    {
        int dist, i, aux;
        
        dist = (int) (tl/1.3);
        
        while(dist > 0)
        {
            for(i=0 ; i+dist < tl ; i++)
                if(vet[i] > vet[i+dist])
                {
                    aux=vet[i];
                    vet[i] = vet[i+dist];
                    vet[i+dist] = aux;
                }
            
            dist--;
        }
    }
    
    public void comb()
    {
        int dist, aux;
        boolean troca;
            
        troca=true;
        dist = tl;
	while (dist > 1 || troca )
	{
            dist = dist * 10 / 13;
            
            if (dist == 9 || dist == 10) 
                dist = 11;
            
            if (dist < 1) 
                dist = 1;
            
            troca = false;
            
            for (int i = 0, j = dist; j < tl ; i++, j++)
                if (vet[i] > vet[i+dist])
                {
                    aux = vet[i];
                    vet[i]=vet[i+dist];
                    vet[i+dist] = aux;
                    troca = true;
                }
	}
    }
    
    public void gnome()
    {
        int aux;
        
        for(int i=0 ; i < tl-1 ; i++)
        {
            if(vet[i] > vet[i+1])
            {
                aux = vet[i];
                vet[i] = vet[i+1];
                vet[i+1] = aux;
                
                if(i > 0)
                    i = i-2;
            }
        }
    }
    
    
    public void bucket()
    {
        final int GRUPOS = 5;
        
        int tl = Dados.TL_GERAL, hash, maior, baldes;
        int[] bucket;
        
        maior = getMaior();
        baldes = (maior/GRUPOS) + 1;
        
        Vetor[] buckets = new Vetor[baldes];

        
        // iniciar buckets
        for(int i=0 ; i < baldes ; i++)
            buckets[i] = new Vetor();
        
        
        // distruibuir valores os buckets
        for(int i=0 ; i < tl ; i++)
        {
            hash = vet[i]/baldes;
            buckets[hash].inserirFinal(vet[i]);
        }
        
        // ordenar os valores de cada bucket
        for(int i=0 ; i < baldes ; i++)
            buckets[i].insercaoDireta();
        
        //pegar os valores ordenados dos buckets
        for(int i=0, k=0 ; i < baldes ; i++)
        {
            bucket = buckets[i].getVet();
            
            for(int j=0 ; j < buckets[i].getTl() ; j++)
                vet[k++] = bucket[j];
        }    
    }
    
    
    public int getMaior()
    {
        int maior = vet[0];
        for(int i = 1; i < tl; i++)
            if(vet[i] > maior)
                maior = vet[i];
        return maior;
    }
    
    public void counting()
    {
        int maior;
        int[] vetFreq;
        int[] vetSaida;

        maior = getMaior();
        
        //gerar frequencias
        vetFreq = new int[maior+1];
        for(int i = 0; i < tl; i++)
            vetFreq[vet[i]] ++;
        
        //gerar aumuluativa
        for(int i = 1; i < maior; i++)
            vetFreq[i] += vetFreq[i-1];
        
        //jogar na auxiliar
        vetSaida = new int[tl];
        for(int i = vetSaida.length - 1; i >= 0; i--)
        {
            vetSaida[ vetFreq[vet[i]] -1] = vet[i];  
            --vetFreq[ vet[i] ];
        }
        
        //joga de volta no vetor
        for(int i = 0; i < tl; i++)
            vet[i] = vetSaida[i];
    }
    
    
    public void radix() 
    {
	int maior, exp = 1;

	int[] vetAux = new int[vet.length];

	maior = getMaior();
        
	while (maior/exp > 0) 
        {
            //deixei dentro do laco pra nao precisar ficar iniciando todoso com 0 todo loop
            int[] freq = new int[10]; 
            
            for (int i = 0; i < vet.length; i++)
                    freq[(vet[i] / exp) % 10]++;

            for (int i = 1; i < 10; i++)
                    freq[i] += freq[i - 1];

            for (int i = vet.length - 1; i >= 0; i--)
                    vetAux[ --freq[(vet[i] / exp) % 10]] = vet[i];

            for (int i = 0; i < vet.length; i++)
                    vet[i] = vetAux[i];

            exp *= 10;
	}
    }
    
    
    // TIM
    public void insercao_direta_tim (int ini, int fim)
    {
        int i, pos, num;

        i = ini;
        while(i < fim)
        {
            num = vet[i+1];
            pos = i+1;

            while(pos > ini && num < vet[pos-1])
            {
                vet[pos] = vet[pos-1];
                pos--;
            }
            
            vet[pos] = num;
            i++;
        }
    }
    
    public void fusao_tim(int aux[], int ini, int meio, int fim)
    {
        int i=ini, 
            j=meio+1, 
            k=ini;
        
        while(i <= meio  && j <= fim)
            if(vet[i] < vet[j])
                aux[k++] = vet[i++];
            else
                aux[k++] = vet[j++];
            
        while(i <= meio)
            aux[k++] = vet[i++];
        
        while(j <= fim)
            aux[k++] = vet[j++];
        
        for(i=ini ; i <= fim ; i++)
            vet[i] = aux[i];
    }
    
    public void tim() 
    { 
        int run = 32, meio, dir, esq;
        int[] vetAux;
        
        if(tl <= 32) //caso for menor ou igual a 32 faz o insercaoDireta normal
            insercaoDireta();
        else //caso nao ordena a cada 32 enderecos
            for (int i = 0; i < tl; i += run) 
            {
                dir = i; //0+32+62+128...
                esq = min( i+run-1, tl-1); //pode ser o run normal ou tl-1, as vezes no final nao cabe o run todo
                
                insercao_direta_tim(dir, esq);
            }
        
        for (int run2 = run; run2 < tl; run2 = 2*run2)
        {
            for (esq = 0; esq < tl; esq += 2*run2) 
            { 
                meio = esq + run2 - 1; 
                dir = min( esq + 2*run2 - 1, tl-1); //direita é duas vezes maior que a esquerda pra da o range todo

                vetAux = new int[dir+1]; // precisa desse tamanho exato, pois o dir vira TL
                
                fusao(vetAux, esq, meio, dir); 
            }
        }
    }
    //fim TIM
}


public class VetorAlgoritmos
{
    static int[] dados = Dados.getDadosInt();
    
    static Vetor vetInsercaoDireta = new Vetor();
    static Vetor vetInsercaoBinaria = new Vetor();
    static Vetor vetSelecaoDireta = new Vetor();
    static Vetor vetBolha = new Vetor();
    static Vetor vetShake = new Vetor();
    static Vetor vetShell = new Vetor();
    static Vetor vetHeap = new Vetor();
    static Vetor vetQuickSP = new Vetor();
    static Vetor vetQuickSPIterativo = new Vetor();
    static Vetor vetQuickCP = new Vetor();
    static Vetor vetQuickCPIterativo = new Vetor();
    static Vetor vetQuickSort = new Vetor();
    static Vetor vetQuickSortIterativo = new Vetor();
    static Vetor vetMerge1 = new Vetor();
    static Vetor vetMerge2 = new Vetor();
    static Vetor vetComb = new Vetor();
    static Vetor vetCounting = new Vetor();
    static Vetor vetGnome = new Vetor();
    static Vetor vetBucket = new Vetor();
    static Vetor vetRadix = new Vetor();
    static Vetor vetTim = new Vetor();

    public static void inserirDadosVetores()
    {   
        for(int i=0 ; i < dados.length ; i++)
        {
            vetInsercaoDireta.inserirFinal(dados[i]);
            vetInsercaoBinaria.inserirFinal(dados[i]);
            vetSelecaoDireta.inserirFinal(dados[i]);
            vetBolha.inserirFinal(dados[i]);
            vetShake.inserirFinal(dados[i]);
            vetShell.inserirFinal(dados[i]);
            vetHeap.inserirFinal(dados[i]);
            vetQuickSP.inserirFinal(dados[i]);
            vetQuickSPIterativo.inserirFinal(dados[i]);
            vetQuickCP.inserirFinal(dados[i]);
            vetQuickCPIterativo.inserirFinal(dados[i]);
            vetQuickSort.inserirFinal(dados[i]);
            vetQuickSortIterativo.inserirFinal(dados[i]);
            vetMerge1.inserirFinal(dados[i]);
            vetMerge2.inserirFinal(dados[i]);
            vetComb.inserirFinal(dados[i]);
            vetCounting.inserirFinal(dados[i]);
            vetGnome.inserirFinal(dados[i]);
            vetBucket.inserirFinal(dados[i]);
            vetRadix.inserirFinal(dados[i]);
            vetTim.inserirFinal(dados[i]);
        }
    }
        
    public static void ordenarVetores()
    {
        vetInsercaoDireta.insercaoDireta();
        vetInsercaoBinaria.insercaoBinaria();
        vetSelecaoDireta.selecaoDireta();
        vetBolha.bolha();
        vetShell.shell();
        vetHeap.heap();
        vetQuickSP.quickSP();
        vetQuickSPIterativo.quickSPIterativo();
        vetQuickCP.quickCP();
        vetQuickCP.quickCPIterativo();
        vetQuickSort.quickSort();
        vetQuickSortIterativo.quickSortIterativo();
        vetMerge1.merge1();
        vetMerge2.merge2();
        vetShake.shake();
        vetComb.comb();
        vetCounting.counting();
        vetGnome.gnome();
        vetBucket.bucket();
        vetRadix.radix();
        vetTim.tim();
    }
    
    public static void exibirVetores()
    {
        System.out.print("1-Insercao Direta "+ vetInsercaoDireta.isOrdenado() +" : ");
        vetInsercaoDireta.exibir();
        
        System.out.print("2-Insercao Binária "+vetInsercaoBinaria.isOrdenado()+": ");
        vetInsercaoBinaria.exibir();
        
        System.out.print("3-Seleção Direta "+vetSelecaoDireta.isOrdenado()+":   ");
        vetSelecaoDireta.exibir();
        
        System.out.print("4-Bolha "+vetBolha.isOrdenado()+":            ");
        vetBolha.exibir();
        
        System.out.print("5-Shake "+vetShake.isOrdenado()+":            ");
        vetShake.exibir();
        
        System.out.print("6-Shell "+vetShell.isOrdenado()+":            ");
        vetShell.exibir();
        
        System.out.print("7-Heap "+vetHeap.isOrdenado()+":             ");
        vetHeap.exibir();
        
        System.out.print("8-QuickSP "+vetQuickSP.isOrdenado()+":          ");
        vetQuickSP.exibir();
        
        System.out.print("8(2)-QuickSP It "+vetQuickSPIterativo.isOrdenado()+":    ");
        vetQuickSPIterativo.exibir();
        
        System.out.print("9-QuickCP "+vetQuickCP.isOrdenado()+":          ");
        vetQuickCP.exibir();
        
        System.out.print("9(2)-QuickCP It "+vetQuickCPIterativo.isOrdenado()+":    ");
        vetQuickCPIterativo.exibir();
        
        System.out.print("10-QuickSort "+vetQuickSort.isOrdenado()+":       ");
        vetQuickSort.exibir();
        
        System.out.print("10(2)-QuickSort It "+vetQuickSortIterativo.isOrdenado()+": ");
        vetQuickSortIterativo.exibir();
        
        System.out.print("12-Merge1 "+vetMerge1.isOrdenado()+":          ");
        vetMerge1.exibir();
        
        System.out.print("13-Merge2 "+vetMerge2.isOrdenado()+":          ");
        vetMerge2.exibir();
        
        System.out.print("14-Comb "+vetComb.isOrdenado()+":            ");
        vetComb.exibir();
        
        System.out.print("15-Counting "+vetCounting.isOrdenado()+":        ");
        vetCounting.exibir();
        
        System.out.print("16-Gnome "+vetGnome.isOrdenado()+":           ");
        vetGnome.exibir();
        
        System.out.print("17-Bucket "+vetBucket.isOrdenado()+":          ");
        vetBucket.exibir();
        
        System.out.print("18-Radix "+vetRadix.isOrdenado()+":           ");
        vetRadix.exibir();
        
        System.out.print("19-Tim "+vetTim.isOrdenado()+":             ");
        vetTim.exibir();
    }
    
    public static void main(String[] args)
    {
//        inserirDadosVetores();
//        ordenarVetores();
//        exibirVetores();
        
        List l = new ArrayList();
        
        for(int i=0 ; i < 100 ; i++)
            l.add(i);
        
        Collections.shuffle(l);
        
        for(int i=1 ; i < l.size() ; i++)
            if(l.get(i) == l.get(0))
                break;
            else
            System.out.print(" "+ l.get(i));
    }
}
