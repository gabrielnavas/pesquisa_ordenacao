package trabalhopo;

import java.io.IOException;
import java.io.RandomAccessFile;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Arquivo {
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov; 

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        }
        catch (IOException e)
        {
            
        }
    }
    
    public void copiaArquivo(RandomAccessFile arquivoOrigem)
    {
        Registro aux = new Registro();
        int tamanho;
        
        try
        {
            tamanho = (int)arquivoOrigem.length()/Registro.length();
        
            for(int i = 0; i < tamanho; i++)
            {
                arquivoOrigem.seek(i*Registro.length());
                aux.leDoArq(arquivoOrigem);
                arquivo.seek(i*Registro.length());
                aux.gravaNoArq(arquivo);
            }
        }
        catch(Exception e)
        {
            
        }
            
        
    } 

    public RandomAccessFile getArquivo() {
        return arquivo;
    }

    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        }
        catch (IOException exc)
        {
            
        }
    }

    //semelhante ao feof() da linguagem C
    //verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof()  
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;                               
        } catch (IOException e)
        { }
        return (retorno);
    }

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }

    public int filesize() {
        try
        {
            return (int)arquivo.length()/Registro.length();
        }catch(IOException e)
        {}
        return 0;
    }
    
    public void initComp()
    {
        this.comp = 0;
    }
    
    public void initMov()
    {
        this.mov = 0;
    }
    
    public int getComp()
    {
        return comp;
    }
    
    public int getMov()
    {
        return mov;
    } 
    
    public void geraArquivoOrdenado()
    {
        Registro reg = new Registro();
        for(int i = 0; i < 100; i++)
        {
            reg.setNumero(i + 1);
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
    }
    
    public void geraArquivoReverso()
    {
        Registro reg = new Registro();
        int num = 10;
        for(int i = 0; i < 100; i++, num--)
        {
            reg.setNumero(num);
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
    }
    
    public void geraArquivoRandomico()
    {
        Registro reg = new Registro();
        List lista = new ArrayList();
        int i;
        for(i = 0; i < 30; i++)
            lista.add(i+1);
        Collections.shuffle(lista);
        for(i = 0; i < 30; i++)
        {
            reg.setNumero((int) lista.get(i));
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
    }

    //insere um Registro no final do arquivo, passado por parâmetro
    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void exibirArq()
    {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof())
        {
            System.out.println("Posicao " + i);
            aux.leDoArq(arquivo);
            aux.exibirReg();
            i++;
        }
    }

    public void exibirUmRegistro(int pos)
    {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
        aux.exibirReg();
    }

    public void leArq()
    {
        int numero;
        numero = Entrada.leInteger("Digite o numero (0 - Sair): ");
        while (numero != 0)
        {
            inserirRegNoFinal(new Registro(numero));
            numero = Entrada.leInteger("Digite o numero (0 - Sair): ");
        }
    }
    
    public void executa()
    {
        leArq();
        exibirArq();
    }
    
    /* ORDENAÇÕES
    
    OBSERVAÇÕES:
        - Os arquivos devem ser feitos de forma Ordenada, Ordem Reversa e Randômica 
        - Acrescentar nos métodos de ordenação 2 variáveis: comparações e movimentações
        - Gerar tabela (como mostra no PDF) ao final das ordenações
        - Os arquivos devem conter pelo menos 1024 registros
    */
    
    public void insercao_direta()   //andressa
    {
        int total = filesize();
        
        if(total > 0)
        {
            Registro aux = new Registro();
            Registro aux2 = new Registro();
            int i = 1, pos, num2, numero;

            while(i < total)
            {
                seekArq(i);
                aux.leDoArq(arquivo);
                numero = aux.getNumero();
                mov++;
                
                pos = i;
                
                seekArq(pos-1);
                aux2.leDoArq(arquivo);
                num2 = aux2.getNumero();
                mov++;
                
                comp++;
                while(pos > 0 && numero < num2)
                {
                    aux.setNumero(num2);
                    seekArq(pos);
                    aux.gravaNoArq(arquivo);
                    mov++;
                    
                    pos--;
                    
                    if(pos > 0)
                    {
                        seekArq(pos);
                        aux.leDoArq(arquivo);
                        mov++;
                        
                        seekArq(pos-1);
                        aux2.leDoArq(arquivo);
                        mov++;
                        num2 = aux2.getNumero();
                    }
                    comp++;
                }
                
                aux2.setNumero(numero);
                seekArq(pos);
                aux2.gravaNoArq(arquivo);
                mov++;
                i++;
            }
        }
        else
            System.out.println("Arquivo vazio!");
    }
    
    public int busca_binaria(Registro chave, int tl) // laura aux
    {
        int inicio=0, fim=tl-1, meio=fim/2, numero, c = chave.getNumero();
        Registro aux= new Registro();
        
        seekArq(meio);
        aux.leDoArq(arquivo);
        numero=aux.getNumero();
        mov++;
        
        comp++;
	while(numero!=c && fim>inicio)
	{
            if(c < numero)
                fim=meio-1;
            else
                inicio=meio+1;

            meio=(fim+inicio)/2;
            comp++;

            seekArq(meio);
            aux.leDoArq(arquivo);
            numero=aux.getNumero();
            mov++;
            comp++;
	}
        
        comp++;
	if(numero<c)
            return meio+1;
	else
            return meio;
    }
    
    public void insercao_binaria()  //laura 
    {
        int pos, numero, total = filesize();
        Registro aux= new Registro();
        Registro aux2= new Registro();
        for(int i=1; i<total; i++)
        {
            seekArq(i);
            aux.leDoArq(arquivo);
            numero = aux.getNumero();
            mov++;
            pos=busca_binaria(aux,i);
            for(int j=i; j>pos;j--)
            {
                seekArq(j-1);
                aux2.leDoArq(arquivo);
                mov++;
                
                seekArq(j);
                aux2.gravaNoArq(arquivo);
                mov++;
            }
            aux2.setNumero(numero);
            seekArq(pos);
            aux2.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void selecao_direta()    //andressa
    {
        Registro reg = new Registro();
        Registro rega = new Registro();
        int i, j, menor, posmenor;
        
        i=0;
        
        while(i<filesize()-1)
        {
            j=i+1;
            
            seekArq(i);
            reg.leDoArq(arquivo);  
            mov++;
            
            menor = reg.getNumero();
            posmenor = i;
            
            while(j<filesize())
            {
                seekArq(j);
                rega.leDoArq(arquivo);
                mov++;
                
                if(menor>rega.getNumero())
                {
                    comp++;
                    menor = rega.getNumero();
                    mov++;
                    posmenor = j;
                }
                
                j++;
            }
            seekArq(posmenor);
            rega.leDoArq(arquivo);
            mov++;
            
            seekArq(i);
            rega.gravaNoArq(arquivo);
            mov++;
            
            seekArq(posmenor);
            reg.gravaNoArq(arquivo);
            mov++;
            
            i++;
        }
    }
    
    public void bolha() //laura
    {
        int tam= filesize();
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        while(tam>1)
        {
            for(int i=0; i<tam-1; i++)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                mov = mov + 2;
                if(reg.getNumero() > reg2.getNumero())
                {
                    comp++;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    reg.gravaNoArq(arquivo);
                    mov = mov + 2;
                } 
            }
            tam--;
        }
    }
    
    public void shake() //andressa
    {
        int ini = 0, fim = filesize()-1, i;
        Registro reg = new Registro();
        Registro rega = new Registro();
        
        while(ini < fim)
        {
            for(i = ini; i < fim; i++)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                mov++;
                
                seekArq(i+1);
                rega.leDoArq(arquivo);
                mov++;
                
                if(reg.getNumero() > rega.getNumero())
                {
                    comp++;
                    seekArq(i);
                    rega.gravaNoArq(arquivo);
                    mov++;
                    
                    seekArq(i+1);
                    reg.gravaNoArq(arquivo);
                    mov++;
                }
            }
            
            fim--;
            
            for(i = fim; i > ini; i--)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                mov++;
                
                seekArq(i-1);
                rega.leDoArq(arquivo);
                mov++;
                
                if(reg.getNumero() < rega.getNumero())
                {
                    seekArq(i);
                    rega.gravaNoArq(arquivo);
                    mov++;
                    
                    seekArq(i-1);
                    reg.gravaNoArq(arquivo);
                    mov++;
                }
            }
            
            ini++;
        }
    }
    
    public void shell() //laura 
    {
        int h = 1, n=filesize(), j, numero, numero2, k;
        Registro c= new Registro();
        Registro aux= new Registro();
        while(h < n) {
                h = h * 3 + 1;
        }

        h = h / 3;

        while (h > 0) {
            for (int i = 0; i < h; i++) {
                for(j = i; (j+h) < n; j = j + h)
                {
                    seekArq(j);
                    c.leDoArq(arquivo);
                    numero=c.getNumero();
                    mov++;
                    
                    seekArq(j+h);
                    aux.leDoArq(arquivo);
                    numero2=aux.getNumero();
                    mov++;
                    
                    if(numero > numero2)
                    {
                        comp++;
                        seekArq(j);
                        aux.gravaNoArq(arquivo);
                        
                        seekArq(j+h);
                        c.gravaNoArq(arquivo);
                        
                        mov = mov + 2;
                        
                        if(j - h >= 0)
                        {
                            seekArq(j);
                            c.leDoArq(arquivo);
                            mov++;

                            seekArq(j-h);
                            aux.leDoArq(arquivo);
                            mov++;
                        }
                        comp++;
                        for(k = j; c.getNumero() < aux.getNumero() && k - h >= i; k = k - h)
                        {
                            seekArq(k);
                            aux.gravaNoArq(arquivo);
                            
                            seekArq(k-h);
                            c.gravaNoArq(arquivo);
                            
                            mov = mov+2;
                            
                            if(k - h >= 0)
                            {
                                seekArq(k);
                                c.leDoArq(arquivo);
                                mov++;

                                seekArq(k-h);
                                aux.leDoArq(arquivo);
                                mov++;
                            }
                            comp++;
                        }
                    }
                }
            }
            h = h / 2;
        }
    }
    
    public void heap()  //andressa
    {
        int tl = filesize(), pai, fd, fe, maiorf, aux;
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        while(tl > 1)
        {
            for(pai = tl/2-1; pai >=0; pai--)
            {
                fe = pai + pai+ 1;
                fd = fe + 1;
                maiorf = fe;
                seekArq(fd);
                reg.leDoArq(arquivo);
                mov++;
                seekArq(fe);
                reg2.leDoArq(arquivo);
                mov++;
                comp++;
                if(fd < tl && reg.getNumero() > reg2.getNumero())
                    maiorf = fd;
                seekArq(maiorf);
                reg.leDoArq(arquivo);
                mov++;
                seekArq(pai);
                reg2.leDoArq(arquivo);
                mov++;
                comp++;
                if(reg.getNumero() > reg2.getNumero())
                {
                    aux = reg.getNumero();
                    reg.setNumero(reg2.getNumero());
                    reg2.setNumero(aux);
                    
                    seekArq(maiorf);
                    reg.gravaNoArq(arquivo);
                    seekArq(pai);
                    reg2.gravaNoArq(arquivo);
                    
                    mov = mov + 2;
                }
            }
            seekArq(0);
            reg.leDoArq(arquivo);
            aux = reg.getNumero();
            
            seekArq(tl - 1);
            reg2.leDoArq(arquivo);
            reg.setNumero(reg2.getNumero());
            reg2.setNumero(aux);
            
            seekArq(0);
            reg.gravaNoArq(arquivo);
            seekArq(tl - 1);
            reg2.gravaNoArq(arquivo);
            
            mov = mov + 2;
            
            tl--;
        }
    }
    
    public void quick_com_pivo(int ini, int fim)    //laura
    {
        int i = ini, j = fim, pivo, aux, tl=filesize();
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        Registro p = new Registro();
        pivo = (i + j) / 2;
        
        while(i < j)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            mov++;
            seekArq(j);
            reg2.leDoArq(arquivo);
            mov++;
            seekArq(pivo);
            p.leDoArq(arquivo);
            mov++;
            
            comp++;
            while(reg.getNumero() < p.getNumero() && i < tl-1)
            {
                i++;
                seekArq(i);
                reg.leDoArq(arquivo);
                mov++;
                comp++;
            }
            
            comp++;
            while(reg2.getNumero() > p.getNumero() && j > 0)
            {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
                mov++;
                comp++;
            }
            
            if(i <= j)
            {
                aux = reg.getNumero();
                reg.setNumero(reg2.getNumero());
                reg2.setNumero(aux);
                seekArq(i);
                reg.gravaNoArq(arquivo);
                seekArq(j);
                reg2.gravaNoArq(arquivo);
                mov = mov + 2;
                i++;
                j--;
            }
        }
        
        if(ini < j)
            quick_com_pivo(ini,j);
        if(i < fim)
            quick_com_pivo(i,fim);
    }
    
    public void quick_sem_pivo(int ini, int fim)  //andressa
    {
        int i = ini, j = fim, aux;
        boolean flag = true;
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        while(i < j)
        {
            if(flag)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                mov++;
                seekArq(j);
                reg2.leDoArq(arquivo);
                mov++;
                
                comp++;
                while(i < j && reg.getNumero() <= reg2.getNumero())
                {
                    i++;
                    seekArq(i);
                    reg.leDoArq(arquivo);
                    mov++;
                    comp++;
                }
            }
            else
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                mov++;
                seekArq(j);
                reg2.leDoArq(arquivo);
                mov++;
                
                comp++;
                while(i < j && reg2.getNumero() >= reg.getNumero())
                {
                    j++;
                    seekArq(j);
                    reg2.leDoArq(arquivo);
                    mov++;
                    comp++;
                }
            }
            
            seekArq(i);
            reg.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            
            aux = reg.getNumero();
            reg.setNumero(reg2.getNumero());
            reg2.setNumero(aux);
            
            seekArq(i);
            reg.gravaNoArq(arquivo);
            seekArq(j);
            reg2.gravaNoArq(arquivo);
            
            mov = mov + 2;
        }
        
        if(ini < i - 1)
            quick_sem_pivo(ini, i - 1);
        if(j + 1 < fim)
            quick_sem_pivo(j + 1, fim);
    }
    
    public void fusao_direta_merge1()    //laura....... só funciona com tl par
    {
        int seq = 1, total = filesize();
        int vet1[] = new int[total];
        int vet2[] = new int[total];
        while(seq < total)
        {
            particao1(vet1,vet2);
            fusao1(vet1,vet2,seq);
            seq = seq * 2;
        }
    }
    
    public void particao1(int[] vet1, int[] vet2)
    {
        int meio = filesize()/2;
        Registro reg = new Registro();
        for(int i = 0; i < meio; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet1[i] = reg.getNumero();
            
            seekArq(i+meio);
            reg.leDoArq(arquivo);
            vet2[i] = reg.getNumero();
        }
    }
    
    public void fusao1(int[] vet1, int[] vet2, int seq)
    {
        int i = 0, j = 0, k = 0, aux_seq = seq, total = filesize();
        Registro reg = new Registro();
        
        while(k < total)
        {
            while(i < seq && j < seq)
            {
                if(vet1[i] < vet2[j] && vet1[i] != 0)
                {
                    seekArq(k);
                    reg.leDoArq(arquivo);
                    reg.setNumero(vet1[i]);
                    i++;
                    
                    seekArq(k);
                    reg.gravaNoArq(arquivo);
                    k++;
                }
                else
                {
                    if(vet1[i] > vet2[j]  && vet2[i] != 0)
                    {
                        seekArq(k);
                        reg.leDoArq(arquivo);
                        reg.setNumero(vet2[j]);
                        j++;

                        seekArq(k);
                        reg.gravaNoArq(arquivo);
                        k++;
                    }
                    else
                    {
                        seekArq(k);
                        reg.leDoArq(arquivo);
                        reg.setNumero(vet2[j]);
                        j++;

                        seekArq(k);
                        reg.gravaNoArq(arquivo);
                        k++;
                        seq--;
                    }
                }
            }
            while(i < seq)
            {
                seekArq(k);
                reg.leDoArq(arquivo);
                reg.setNumero(vet1[i]);
                i++;
                    
                seekArq(k);
                reg.gravaNoArq(arquivo);
                k++;
            }
            while(j < seq)
            {
                seekArq(k);
                reg.leDoArq(arquivo);
                reg.setNumero(vet2[j]);
                j++;
                    
                seekArq(k);
                reg.gravaNoArq(arquivo);
                k++;
            }
            seq  = seq + aux_seq;
        }
    }
    
    public void fusao_direta_merge2()  //andressa
    {
        int aux[] = new int [filesize()];
        merge(aux,0,filesize()-1);
    }
    
    public void merge(int aux[], int esq, int dir)  //andressa
    {
        if (esq < dir)
        {
            int meio = (esq+dir)/2;

            merge(aux,esq,meio);
            merge(aux,meio+1,dir);
            fusao(aux,esq,meio,dir);
        }
    }
    
    public void fusao (int aux[], int ini, int meio, int fim)
    {
        int i = ini, j = meio+ 1, k = ini;
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        
        while(i <= meio && j <=fim)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            mov++;
            seekArq(j);
            reg2.leDoArq(arquivo);
            mov++;
            
            comp++;
            if(reg.getNumero() < reg2.getNumero())
            {
                aux[k] = reg.getNumero();
                mov++;
                i++;
            }
            else
            {
                aux[k] = reg2.getNumero();
                mov++;
                j++;
            }
            k++;
        }
        
        while(i <= meio)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            aux[k] = reg.getNumero();
            mov++;
            i++;
            k++;
        }
        
        while(j <= fim)
        {
            seekArq(j);
            reg.leDoArq(arquivo);
            aux[k] = reg.getNumero();
            mov++;
            j++;
            k++;
        }
        
        for(i = ini; i <=fim; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            reg.setNumero(aux[i]);
            seekArq(i);
            reg.gravaNoArq(arquivo);
            mov = mov + 2;
        }
    }
    
    public void counting()    //laura
    {
        Registro aux=new Registro();
        int num, maior, total = filesize();
        
        seekArq(0);
        aux.leDoArq(arquivo);
        maior=aux.getNumero();
        mov++;
        
	for (int i = 1; i < total; i++) 
        {
            seekArq(i);
            aux.leDoArq(arquivo);
            num=aux.getNumero();
            mov++;
            if (num > maior)
            {
                comp++;
                maior = num;
            }
        }
		
        // frequencia
	int[] c = new int[maior];
        
	for (int i = 0; i < total; i++)
        {
            seekArq(i);
            aux.leDoArq(arquivo);
            num=aux.getNumero()-1;
            mov++;
            c[num]+=1;
        }
		
        // cumulativa
	for (int i = 1; i < maior; i++)
            c[i] += c[i-1];
		
	Integer[] b = new Integer[total];
	for (int i = 0; i < total; i++)
        {
            seekArq(i);
            aux.leDoArq(arquivo);
            num = aux.getNumero();
            mov++;
            
            b[c[num -1] -1] = num;
            c[num -1]--;
        }
		
	for (int i = 0; i < total; i++)
        {
            aux.setNumero(b[i]);
            seekArq(i);
            aux.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void bucket()  //andressa
    {
        int maior, i, baldes, total = filesize(), j, ind;
        int[] aux = new int[5];
        Registro reg = new Registro();
        
        seekArq(0);
        reg.leDoArq(arquivo);
        maior = reg.getNumero();
        mov++;
        
        for(i = 1; !eof(); i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            mov++;
            
            if(reg.getNumero() > maior)
            {
                comp++;
                maior = reg.getNumero();
                mov++;
            }
            
        }
        
        baldes = maior / 3;
        
        LinkedList[] b = new LinkedList[baldes];
        
        for(i = 0; i < baldes; i++)
            b[i] = new LinkedList<Integer>();
        
        for(i = 0; i < total; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            mov++;
            
            j = baldes - 1;
            while(j > 0)
            {
                comp++;
                if(reg.getNumero() >= (j*5))
                    b[j].add(reg.getNumero());
                j--;
            }
        }
        
        ind = 0;
        for(i = 0; i < baldes; i++)
            aux = new int[b[i].size()];
        
        comp++;
        for(j = 0; j < aux.length; j++, comp++)
            aux[j] = (Integer)b[i].get(j);
        
        insercao_direta();
        
        for(j = 0; j < aux.length; j++, ind++)
        {
            seekArq(ind);
            reg.setNumero(aux[j]);
            
            seekArq(ind);
            reg.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void radix()    //laura 
    {
        int i, p, total = filesize(), num, j;
        
        for(i = 0; i < 3; i++)
        {
            p = (int)Math.pow(10, i + 1);
            
            int[][]m = new int[total][10];
            int[] v = new int[10];
            Registro aux= new Registro();
            
            for(i = 0; i < total; i++)
            {
                seekArq(i);
                aux.leDoArq(arquivo);
                num=aux.getNumero();
                mov++;
                
                m[v[(num%p)/(p/10)]][(num%p)/(p/10)] = num;
                
                v[(num%p)/(p/10)]++;
            }
            
            p = 0;
            seekArq(0);
            for(i = 0; i < 10; i++)
            {
                for(j = 0; j < total; j++)
                {
                    if(j < v[i])
                    {
                        aux.setNumero(m[j][i]);
                        seekArq(p);
                        aux.gravaNoArq(arquivo);
                        mov++;
                        p++;
                    }
                }
            }
        }
    }
    
    public void comb ()
    {
        int i, j, intervalo, trocado = 1, aux;
        Registro reg = new Registro();
        Registro reg2 = new Registro();
	intervalo = filesize();
	while (intervalo > 1 || trocado == 1)
	{
            intervalo = intervalo * 10 / 13;
            if (intervalo == 9 || intervalo == 10)
                intervalo = 11;
            if (intervalo < 1) intervalo = 1;
                trocado = 0;
            for (i = 0, j = intervalo; j < filesize(); i++, j++)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                seekArq(j);
                reg2.leDoArq(arquivo);
                mov = mov + 2;
                comp++;
                if (reg.getNumero() > reg2.getNumero())
                {
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(j);
                    reg.gravaNoArq(arquivo);
                    mov = mov + 2;
                    trocado = 1;
                }
            }
	}
    }
    
    public void gnome()  //andressa
    {
        int p = 0, total = filesize();
        Registro reg = new Registro();
        Registro rega = new Registro();
        
        while(p < total)
        {
            seekArq(p);
            reg.leDoArq(arquivo);
            rega.leDoArq(arquivo);
            mov = mov + 2;
            comp++;
            if(reg.getNumero() > rega.getNumero())
            {
                seekArq(p);
                rega.gravaNoArq(arquivo);
                mov++;
                
                seekArq(p+1);
                reg.gravaNoArq(arquivo);
                mov++;
                
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
        Registro aux = new Registro();
        Registro aux2 = new Registro();
        int i = ini, pos, num2, numero;

        while(i < fim)
        {
            seekArq(i);
            aux.leDoArq(arquivo);
            numero = aux.getNumero();
            mov++;

            pos = i;

            seekArq(pos-1);
            aux2.leDoArq(arquivo);
            num2 = aux2.getNumero();
            mov++;

            comp++;
            while(pos > 0 && numero < num2)
            {
                aux.setNumero(num2);
                seekArq(pos);
                aux.gravaNoArq(arquivo);
                mov++;

                pos--;

                if(pos > 0)
                {
                    seekArq(pos);
                    aux.leDoArq(arquivo);
                    mov++;

                    seekArq(pos-1);
                    aux2.leDoArq(arquivo);
                    mov++;
                    num2 = aux2.getNumero();
                }
                comp++;
            }

            aux2.setNumero(numero);
            seekArq(pos);
            aux2.gravaNoArq(arquivo);
            mov++;
            i++;
        }
    }
    
    public void tim() 
    { 
        int total = filesize(), r = 32, i, size, meio, dir, esq;
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
