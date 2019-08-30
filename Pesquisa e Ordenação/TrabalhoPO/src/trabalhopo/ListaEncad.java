package trabalhopo;

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
        
    }
    
    public void quick_com_pivo()  //andressa
    {
        
    }
    
    public void quick_sem_pivo()    //laura
    {
        
    }
    
    public void fusao_direta_merge1()  //andressa
    {
        
    }
    
    public void fusao_direta_merge2()    //laura
    {
        
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
    
    public void bucket()    //laura arrumar
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
    
    public void tim()      //andressa
    {
        
    }
}
