package trabalhopo;

import static java.lang.Math.min;
import java.util.LinkedList;

public class ListaEncad {
    private NoLista ini;
    private NoLista fim;

    public ListaEncad() {
        this.ini = null;
        this.fim = null;
    }
    
    public void inicializa()
    {
       this.ini = null;
       this.fim = null;
    }
    
    public void inserirNoInicio(int info)
    {
        NoLista nova = new NoLista(info,ini,null);
        
        if(ini == null)
            ini = fim = nova;
        else
        {
            ini.setAnt(nova);
            ini = nova;
        }
    }
    
    public void inserirNoFinal(int info)
    {
        NoLista nova = new NoLista(info,null,fim);
        
        if(fim == null)
            ini = fim = nova;
        else
        {
            fim.setProx(nova);
            fim = nova;
        }
    }
    
    public void exibir()
    {
        NoLista aux = ini;
        
        while(aux != null)
        {
            System.out.println(aux.getInfo());
            aux = aux.getProx();
        }
    }
    
    public NoLista busca_exaustiva(int info)
    {
        NoLista aux = ini;
        
        while(aux!= null && aux.getInfo() != info)
            aux = aux.getProx();
        
        if(aux != null)
            return aux;
        return null;
    }
    
    public void remover (int info)
    {
        NoLista aux = busca_exaustiva(info);
        NoLista aux2;
        
        if(aux != null)
        {
            if(ini == fim)
                inicializa();
            else
            {
                if(ini == aux)
                {
                    ini = ini.getProx();
                    ini.setAnt(null);
                }
                else
                    if(fim == aux)
                    {
                        fim = fim.getAnt();
                        fim.setProx(null);
                    }
                    else
                    {
                        NoLista a = aux.getAnt(), p = aux.getProx();
                        a.setProx(p);
                        p.setAnt(a);
                    }
            }
        }
    }

    public NoLista getIni() {
        return ini;
    }

    public NoLista getFim() {
        return fim;
    }
    
    public int qtde ()
    {
        NoLista aux = ini;
        int i = 0;
        
        while(aux != null)
        {
            i++;
            aux = aux.getProx();
        }
        
        return i;
    }
    
    public int busca_binaria(int chave, int tl)
    {
        int inicio = 0, fim = tl-1, meio = fim/2, num;
        NoLista aux = pegarNo(meio);
        num = aux.getInfo();
        while(num != chave && inicio < fim)
        {
            if(num > chave)
                fim = meio - 1;
            else
                inicio = meio + 1;
            meio = (inicio + fim) / 2;
            aux = pegarNo(meio);
            num = aux.getInfo();
        }
        if(chave > num)
            return meio + 1;
        return meio;
    }
    
    public NoLista pegarNo(int pos)
    {
        NoLista aux = ini;
        for(int i = 0; i < pos; i++)
            aux = aux.getProx();
        return aux;
    }
    
    /* ORDENAÇÕES */
    
    public void insercao_direta()    //laura 
    {
        int aux;
        NoLista pi =ini.getProx(), ppos;
        while(pi!=null)
        {
            aux=pi.getInfo();
            ppos=pi;
            while(ppos!=ini&&aux<ppos.getAnt().getInfo())
            {
                ppos.setInfo(ppos.getAnt().getInfo());
                ppos= ppos.getAnt();
            }
            ppos.setInfo(aux);
            pi=pi.getProx();
        }
    }
    
    public void insercao_binaria()  //andressa
    {
        int pos, i, j, total = qtde(), num;
        NoLista aux = ini.getProx(), aux3;
        
        for(i = 1; i < total; i++)
        {
            num = aux.getInfo();
            pos = busca_binaria(num, i);
            for(j = i; j > pos; j--)
            {
                aux3 = pegarNo(j);
                aux3.setInfo(aux3.getAnt().getInfo());
            }
            aux3 = pegarNo(pos);
            aux3.setInfo(num);
            if(aux.getProx() != null)
                aux = aux.getProx();
        }
    }
    
    public void selecao_direta()    //laura 
    {
        NoLista aux = ini, posmenor, aux2 = null;
        int menor, total = qtde();
        
        for(int i=0;i<total-1;i++)
        {
            posmenor = aux;
            menor = aux.getInfo();
            for(int j=i+1;j<total;j++)
            {
                aux2 = pegarNo(j);
                if(aux2.getInfo()<menor)
                {
                    menor=aux2.getInfo();
                    posmenor=aux2;
                }
            }
            if(aux2.getInfo()==menor)
            {
                posmenor.setInfo(aux.getInfo());
                aux.setInfo(menor);
            }
            aux=aux.getProx();
        }
    }
    
    public void bolha()  //andressa
    {
        int tl = qtde(), aux, num1, num2;
        NoLista no1, no2;
        
        while(tl > 1)
        {
            no1 = ini;
            
            for(int i = 0; i < tl -1; i++)
            {
                no2 = no1.getProx();
                
                num1 = no1.getInfo();
                num2 = no2.getInfo();
                
                if(num1 > num2)
                {
                    aux = num1;
                    no1.setInfo(num2);
                    no2.setInfo(aux);
                }
                
                no1 = no2;
            }
            
            tl--;
        }
        
    }
    
    public void shake()    //laura 
    {
        int fina = qtde()-1, i, inic = 0, aux;
        NoLista no1, no2;
        
        while(inic < fina)
        {
            no1 = ini;
            for(i = inic; i < fina; i++)
            {
                no2=no1.getProx();
                if(no1.getInfo() > no2.getInfo())
                {
                    aux=no1.getInfo();
                    no1.setInfo(no2.getInfo());
                    no2.setInfo(aux);
                }
                no1=no2;
            }
            
            fina--;
            
            no1 = pegarNo(fina);
            for(i = fina; i > inic; i--)
            {
                no2=no1.getAnt();
                
                if(no1.getInfo() < no2.getInfo())
                {                
                    aux=no2.getInfo();
                    no2.setInfo(no1.getInfo());
                    no1.setInfo(aux);
                }
            }
            
            inic++;
        }
    }
    
    public void shell()  //andressa
    {
        int dist = 2, aux, i, j, k, total = qtde();
        NoLista no1, no2;
        while(dist > 0)
        {
            for(i = 0; i < dist; i++)
            {
                for(j = i; (j+dist) < total; j = j + dist)
                {
                    no1 = pegarNo(j);
                    no2 = pegarNo(j+dist);
                    if(no1.getInfo() > no2.getInfo())
                    {
                        aux = no1.getInfo();
                        no1.setInfo(no2.getInfo());
                        no2.setInfo(aux);
                        
                        no2 = pegarNo(j - dist);
                        for(k = j; no1.getInfo() < no2.getInfo() && (k-dist) >= i; k = k - dist)
                        {
                            aux = no1.getInfo();
                            no1.setInfo(no2.getInfo());
                            no2.setInfo(aux);
                            if(k - dist > -1)
                            {
                                no1 = pegarNo(k);
                                no2 = pegarNo(k - dist);
                            }
                        }
                    }
                }
            }
            dist /=2;
        }
    }
    
    public void heap()    //laura
    {
        int tl = qtde(), pai, fd, fe, maiorf, aux;
        NoLista no1, no2, no3, no4;
        while(tl > 1)
        {
            for(pai = tl/2-1; pai >=0; pai--)
            {
                fe = pai + pai+ 1;
                fd = fe + 1;
                maiorf = fe;
                no1=pegarNo(fd);
                no2=pegarNo(fe);
                if(fd < tl && no1.getInfo() > no2.getInfo())
                    maiorf = fd;
                no3=pegarNo(maiorf);
                no4=pegarNo(pai);
                if(no3.getInfo() > no4.getInfo())
                {
                    aux = no3.getInfo();
                    no3.setInfo(no4.getInfo());
                    no4.setInfo(aux);
                }
            }
            no1=ini;
            aux = no1.getInfo();
            
            no2=pegarNo(tl - 1);
            no1.setInfo(no2.getInfo());
            no2.setInfo(aux);
            tl--;
        }
    }
    
    public void quick_com_pivo(int ini, int fim)  //andressa
    {
        int i = ini, j = fim, pivo, aux;
        NoLista n1, n2, p;
        
        pivo = (i + j) / 2;
        
        while(i < j)
        {
            n1 = pegarNo(i);
            n2 = pegarNo(j);
            p = pegarNo(pivo);
            
            while(n1.getInfo() < p.getInfo() && i < qtde()-1)
            {
                i++;
                n1 = pegarNo(i);
            }
            
            while(n2.getInfo() > p.getInfo() && j > 0)
            {
                j--;
                n2 = pegarNo(j);
            }
            
            if(i <= j)
            {
                aux = n1.getInfo();
                n1.setInfo(n2.getInfo());
                n2.setInfo(aux);
                i++;
                j--;
            }
        }
        
        if(ini < j)
            quick_com_pivo(ini,j);
        if(i < fim)
            quick_com_pivo(i,fim);
    }
    
    public void quick_sem_pivo(int ini, int fim)    //laura
    {
        int i=ini, j=fim;
        int aux;
        boolean flag = true;
        NoLista n1, n2;
        while(i < j)
        {
            if(flag)
            {
                n1=pegarNo(i);
                n2=pegarNo(j);
                
                while(i < j && n1.getInfo() <= n2.getInfo())
                {
                    i++;
                    n1=pegarNo(i);
                }
            }
            else
            {
                n1=pegarNo(i);
                n2=pegarNo(j);
                
                while(i < j && n2.getInfo() >= n1.getInfo())
                {
                    j++;
                     n2=pegarNo(j);
                }
            }
            
            n1=pegarNo(i);
            n2=pegarNo(j);
            
            aux = n1.getInfo();
            n1.setInfo(n2.getInfo());
            n2.setInfo(aux);
            
        }
        
        if(ini < i - 1)
            quick_sem_pivo(ini, i - 1);
        if(j + 1 < fim)
            quick_sem_pivo(j + 1, fim);
    }
    
    public void fusao_direta_merge1()  //andressa .............. Só funciona com tl par
    {
        int seq = 1;
        int vet1[] = new int[qtde()/2];
        int vet2[] = new int[qtde()/2];
        while(seq < qtde())
        {
            particao(vet1,vet2);
            fusao(vet1,vet2,seq);
            seq = seq * 2;
        }
    }
    
    public void particao (int vet1[], int vet2[])
    {
        int meio = qtde()/2;
        NoLista aux;
        for(int i = 0; i < meio; i++)
        {
            aux = pegarNo(i);
            vet1[i] = aux.getInfo();
            aux = pegarNo(i+meio);
            vet2[i] = aux.getInfo();
        }
    }
    
    public void fusao (int vet1[], int vet2[], int seq)
    {
        int i = 0, j = 0, k = 0, aux_seq = seq;
        NoLista no;
        
        while(k < qtde())
        {
            while(i < seq && j < seq)
                if(vet1[i] < vet2[j])
                {
                    no = pegarNo(k++);
                    no.setInfo(vet1[i++]);
                }
                else
                {
                    no = pegarNo(k++);
                    no.setInfo(vet2[j++]);
                }
            
            while(i < seq)
            {
                no = pegarNo(k++);
                no.setInfo(vet1[i++]);
            }
            while(j < seq)
            {
                no = pegarNo(k++);
                no.setInfo(vet2[j++]);
            }
            seq = seq + aux_seq;
        }
    }
    
    public void fusao_direta_merge2()  //andressa
    {
        int aux[] = new int [qtde()];
        merge2(aux,0,qtde()-1);
    }
    
    public void merge2(int aux[], int esq, int dir)  //andressa
    {
        if (esq < dir)
        {
            int meio = (esq+dir)/2;

            merge2(aux,esq,meio);
            merge2(aux,meio+1,dir);
            fusao2(aux,esq,meio,dir);
        }
    }
    
    public void fusao2 (int aux[], int inic, int meio, int fim)
    {
        int i = inic, j = meio+ 1, k = inic;
        NoLista no1, no2;
        
        while(i <= meio && j <=fim)
        {
            no1 = pegarNo(i);
            no2 = pegarNo(j);
            
            if(no1.getInfo() < no2.getInfo())
            {
                aux[k] = no1.getInfo();
                i++;
            }
            else
            {
                aux[k] = no2.getInfo();
                j++;
            }
            k++;
        }
        
        while(i <= meio)
        {
            no1 = pegarNo(i);
            aux[k] = no1.getInfo();
            i++;
            k++;
        }
        
        while(j <= fim)
        {
            no2 = pegarNo(j);
            aux[k] = no2.getInfo();
            j++;
            k++;
        }
        
        for(i = inic; i <=fim; i++)
        {
            no1 = pegarNo(i);
            no1.setInfo(aux[i]);
        }
    }
    
    public void counting()  //andressa
    {
        int maior = ini.getInfo(), i, total = qtde();
        NoLista aux = ini.getProx();
        
        //achar o maior
        for(i = 1; i < total; i++)
        {
            if(aux.getInfo() > maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }
        //frequencia
        int [] vetor = new int[maior];
        aux = ini;
        for(i = 0; i < total; i++)
        {
            vetor[aux.getInfo()-1] += 1;
            aux = aux.getProx();
        }
        
        //cumulativa
        for(i = 1; i < maior; i++)
            vetor[i] += vetor[i-1];
        
        Integer[] vetor2 = new Integer[total];
        aux = ini;
        for(i = 0; i < total; i++)
        {
            vetor2[vetor[aux.getInfo()-1]-1] = aux.getInfo();
            vetor[aux.getInfo()-1]--;
            aux = aux.getProx();
        }
        
        aux = ini;
        for(i = 0; i < total; i++)
        {
            aux.setInfo(vetor2[i]);
            aux = aux.getProx();
        }
    }
    
    public void bucket()    //laura
    {
        int maior, i, baldes, total = qtde(), j;
        int[] aux = new int[5];
        NoLista auux = ini,ind;
        
        maior = auux.getInfo();
        auux = auux.getProx();
        
        for(i = 1; i<total; i++)
        {
            if(auux.getInfo() > maior)
                maior = auux.getInfo();
            auux=auux.getProx();
        }
        
        baldes = maior / 5;
        
        LinkedList[] b = new LinkedList[baldes];
        
        for(i = 0; i < baldes; i++)
            b[i] = new LinkedList<Integer>();
        
        auux = ini;
        for(i = 0; i < total; i++)
        {
            j = baldes - 1;
            while(j > 0)
            {
                if(auux.getInfo() >= (j*5))
                    b[j].add(auux.getInfo());
                j--;
            }
            auux=auux.getProx();
        }
        
        for(i = 0; i < baldes; i++)
            aux = new int[b[i].size()];
        
        for(j = 0; j < aux.length; j++)
            aux[j] = (Integer)b[i].get(j);
        
        insercao_direta();
        
        ind = ini;
        for(j = 0; j < aux.length; j++)
        {
            ind.setInfo(aux[j]);
            ind=ind.getProx();
        }
    }
    
    public void radix()  //andressa
    {
        int i, p, total = qtde(), num, c, j;
        
        for(i = 0; i < 3; i++)
        {
            p = (int)Math.pow(10, i + 1);
            
            int[][]m = new int[total][10];
            int[] v = new int[10];
            NoLista aux = ini;
            
            for(i = 0; i < total; i++)
            {
                num = aux.getInfo();
                
                m[v[(num%p)/(p/10)]][(num%p)/(p/10)] = num;
                
                v[(num%p)/(p/10)]++;
                
                aux = aux.getProx();
            }
            
            c = 0;
            aux = ini;
            
            for(i = 0; i < 10; i++)
            {
                for(j = 0; j < total; j++)
                {
                    if(j < v[i])
                    {
                        aux.setInfo(m[j][i]);
                        aux = aux.getProx();
                    }
                }
            }
        }
    }
    
    public void comb ()
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
    
    public void gnome()    //laura 
    {
        int p = 0, total = qtde(), aux;
        NoLista no1, no2;
        
        while(p < total-1)
        {
            no2=pegarNo(p);
            no1=no2.getProx();
            if(no2.getInfo() > no1.getInfo())
            {
                aux=no2.getInfo();
                no2.setInfo(no1.getInfo());
                no1.setInfo(aux);
                
                if(p > 0)
                {
                    p -= 2;
                }
            }
            p++;
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

                fusao2(aux, esq, meio, dir); 
            } 
        } 
    } 
}
