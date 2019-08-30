    package in_vetor;

import Dados.Dados;
import static java.lang.Math.min;

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
    
    public void comb()
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
    
    public void comb2()
    {
        int i, j, intervalo, trocado = 1, aux;
        NoLista no1, no2;
	intervalo = qtde();
	while (intervalo > 1 || trocado == 1)
	{
            intervalo = intervalo * 10 / 13;
            if (intervalo == 9 || intervalo == 10) intervalo = 11;
            if (intervalo < 1) intervalo = 1;
            trocado = 0;
            for (i = 0, j = intervalo; j < qtde(); i++, j++)
            {
                no1 = pegarNo(i);
                no2 = pegarNo(j);
                if (no1.getInfo() > no2.getInfo())
                {
                    aux = no1.getInfo();
                    no1.setInfo(no2.getInfo());
                    no2.setInfo(aux);
                    trocado = 1;
                }
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
                {
                    i = i-2;
                }
            }
        }
    }
    
    ///////////////////////////////////BUGGG
    public void bucket()
    {
        int tl = Dados.TL_GERAL,
                hash;
        int[] bucket;
        Vetor[] buckets = new Vetor[tl];

        // iniciar buckets
        for(int i=0 ; i < tl ; i++)
            buckets[i] = new Vetor();
        
        
        // distruibuir valores os buckets
        for(int i=0 ; i < tl ; i++)
        {
//            hash = ( (int) (vet[i] * tl) );
            hash = hashBucket(vet[i]);
            buckets[hash].inserirFinal(vet[i]);
        }
        
        // ordenar os valores de cada bucket
        for(int i=0 ; i < tl ; i++)
        {
            buckets[i].insercaoDireta();
//            buckets[i].exibir();
        }
        
        //pegar os valores ordenados dos buckets
        for(int i=0, k=0 ; i < 5 ; i++)
        {
            bucket = buckets[i].getVet();
            
            for(int j=0 ; j < buckets[i].getTl() ; j++)
                vet[k++] = bucket[j];
        }    
    }
    
    public int hashBucket(int x)
    {   
        return (x >= 10) ? 
                ((int) x/ 100) : 0;
    }
    
    public void counting()
    {
        int maior;
        int[] vetFreq;
        int[] vetSaida;

        maior = vet[0];
        for(int i = 1; i < tl; i++)
            if(vet[i] > maior)
                maior = vet[i];
        
        vetFreq = new int[maior];
        for(int i = 0; i < tl; i++)
            vetFreq[vet[i]-1] ++;
        
        for(int i = 1; i < maior; i++)
            vetFreq[i] += vetFreq[i-1];
        
        vetSaida = new int[tl];
        for(int i = 0; i < tl; i++)
        {
            vetSaida[ vetFreq[ vet[i-1] -1 ] ] = vet[i];
            vetFreq[ vet[i-1] ]--;
        }
        
        for(int i = 0; i < tl; i++)
            vet[i] = vetSaida[i];
    }
    
    
    public void radix() 
    {
	int[] b;
	int maior = vet[0];
	int exp = 1;

	b = new int[vet.length];

	for (int i = 0; i < vet.length; i++) {
            if (vet[i] > maior)
                    maior = vet[i];
	}
        
	while (maior/exp > 0) 
        {
		int[] freq = new int[10];
                
		for (int i = 0; i < vet.length; i++)
			freq[(vet[i] / exp) % 10]++;

		for (int i = 1; i < 10; i++)
			freq[i] += freq[i - 1];

		for (int i = vet.length - 1; i >= 0; i--)
			b[--freq[(vet[i] / exp) % 10]] = vet[i];

		for (int i = 0; i < vet.length; i++)
			vet[i] = b[i];

		exp *= 10;
	}
    }
    
    public void insercao_direta_tim (int ini, int fim)
    {
        NoLista no1, no2;
        int i = ini, pos, num2, numero;

        while(i < fim)
        {
            no1 = pegarNo(i);
            numero = no1.getInfo();

            pos = i;

            no2 = pegarNo(pos-1);
            num2 = no2.getInfo();
            
            while(pos > 0 && numero < num2)
            {
                no1.setInfo(num2);

                pos--;

                if(pos > 0)
                {
                    no1 = pegarNo(pos);

                    no2 = pegarNo(pos-1);
                    num2 = no2.getInfo();
                }
            }
            no2.setInfo(numero);
            i++;
        }
    }
    
    public void tim() 
    { 
        int total = qtde(), r = 32, i, size, meio, dir, esq;
        int[] aux = new int[r];
        
        for (i = 0; i < total; i+=r) 
            insercao_direta_tim(i, min((i+31), (total))); 
        
        for (size = r; size < total; size = 2*size) 
        { 
            for (esq = 0; esq < total; esq += 2*size) 
            { 
                meio = esq + size - 1; 
                dir = min((esq + 2*size - 1), (total-1)); 

                fusao(aux, esq, meio, dir); 
            } 
        } 
    } 
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
    static Vetor vetQuickCP = new Vetor();
    static Vetor vetQuickSort = new Vetor();
    static Vetor vetMerge1 = new Vetor();
    static Vetor vetMerge2 = new Vetor();
    static Vetor vetComb = new Vetor();
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
            vetQuickCP.inserirFinal(dados[i]);
            vetQuickSort.inserirFinal(dados[i]);
            vetMerge1.inserirFinal(dados[i]);
            vetMerge2.inserirFinal(dados[i]);
            vetComb.inserirFinal(dados[i]);
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
        vetQuickCP.quickCP();
        vetQuickSort.quickSort();
        vetMerge1.merge1();
        vetMerge2.merge2();
        vetShake.shake();
        vetComb.comb();
        vetGnome.gnome();
        vetBucket.bucket();
        vetRadix.radix();
//        vetTim.tim();
    }
    
    public static void exibirVetores()
    {
        System.out.print("1 - Insercao Direta:  ");
        vetInsercaoDireta.exibir();
        
        System.out.print("2 - Insercao Binária: ");
        vetInsercaoBinaria.exibir();
        
        System.out.print("3 - Seleção Direta:   ");
        vetSelecaoDireta.exibir();
        
        System.out.print("4 - Bolha:            ");
        vetBolha.exibir();
        
        System.out.print("5 - Shake:            ");
        vetShake.exibir();
        
        System.out.print("6 - Shell:            ");
        vetShell.exibir();
        
        System.out.print("7 - Heap:             ");
        vetHeap.exibir();
        
        System.out.print("8 - QuickSP:          ");
        vetQuickSP.exibir();
        
        System.out.print("9 - QuickCP:          ");
        vetQuickCP.exibir();
        
        System.out.print("10 - QuickSort:       ");
        vetQuickSort.exibir();
        
        System.out.print("12 - Merge1:          ");
        vetMerge1.exibir();
        
        System.out.print("13 - Merge2:          ");
        vetMerge2.exibir();
        
        System.out.print("14 - Comb:            ");
        vetComb.exibir();
        
        System.out.print("15 - Gnome:           ");
        vetGnome.exibir();
        
        System.out.print("16 - Bucket:          ");
        vetBucket.exibir();
        
        System.out.print("17 - Radix :          ");
        vetRadix.exibir();
        
        System.out.print("18 - Tim:             ");
        vetTim.exibir();
    }
    
    public static void main(String[] args)
    {
        inserirDadosVetores();
        ordenarVetores();
        exibirVetores();
        
    }
}
