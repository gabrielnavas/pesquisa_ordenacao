package trabalhopo;

import java.io.IOException;
import java.io.RandomAccessFile;
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
        for(int i = 0; i < 10; i++)
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
        for(int i = 0; i < 10; i++, num--)
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
        for(i = 0; i < 10; i++)
            lista.add(i+1);
        Collections.shuffle(lista);
        for(i = 0; i < 10; i++)
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
                
                while(pos > 0 && numero < num2)
                {
                    aux.setNumero(num2);
                    seekArq(pos);
                    aux.gravaNoArq(arquivo);
                    
                    pos--;
                    
                    if(pos > 0)
                    {
                        seekArq(pos);
                        aux.leDoArq(arquivo);
                        
                        seekArq(pos-1);
                        aux2.leDoArq(arquivo);
                        num2 = aux2.getNumero();
                    }
                }
                
                aux2.setNumero(numero);
                seekArq(pos);
                aux2.gravaNoArq(arquivo);
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
        
	while(numero!=c && fim>inicio)
	{
		if(c < numero)
                    fim=meio-1;
		else
                    inicio=meio+1;
                
		meio=(fim+inicio)/2;
                
                seekArq(meio);
                aux.leDoArq(arquivo);
                numero=aux.getNumero();
	}
        
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
            pos=busca_binaria(aux,i);
            for(int j=i; j>pos;j--)
            {
                seekArq(j-1);
                aux2.leDoArq(arquivo);
                
                seekArq(j);
                aux2.gravaNoArq(arquivo);
            }
            aux2.setNumero(numero);
            seekArq(pos);
            aux2.gravaNoArq(arquivo);
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
            
            menor = reg.getNumero();
            posmenor = i;
            
            while(j<filesize())
            {
                seekArq(j);
                rega.leDoArq(arquivo);
                
                if(menor>rega.getNumero())
                {
                    menor = rega.getNumero();
                    posmenor = j;
                }
                
                j++;
            }
            seekArq(posmenor);
            rega.leDoArq(arquivo);
            
            seekArq(i);
            rega.gravaNoArq(arquivo);
            
            seekArq(posmenor);
            reg.gravaNoArq(arquivo);
            
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
                if(reg.getNumero() > reg2.getNumero())
                {
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    reg.gravaNoArq(arquivo);
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
                
                seekArq(i+1);
                rega.leDoArq(arquivo);
                
                if(reg.getNumero() > rega.getNumero())
                {
                    seekArq(i);
                    rega.gravaNoArq(arquivo);
                    
                    seekArq(i+1);
                    reg.gravaNoArq(arquivo);
                }
            }
            
            fim--;
            
            for(i = fim; i > ini; i--)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                
                seekArq(i-1);
                rega.leDoArq(arquivo);
                
                if(reg.getNumero() < rega.getNumero())
                {
                    seekArq(i);
                    rega.gravaNoArq(arquivo);
                    
                    seekArq(i-1);
                    reg.gravaNoArq(arquivo);
                }
            }
            
            ini++;
        }
    }
    
    public void shell() //laura ARRUMAR
    {
        int h = 1, n=filesize(), j, numero, numero2;
        Registro c= new Registro();
        Registro aux= new Registro();
        while(h < n) {
                h = h * 3 + 1;
        }

        h = h / 3;

        while (h > 0) {
            for (int i = h; i < n; i++) {
                seekArq(i);
                c.leDoArq(arquivo);
                numero=c.getNumero();
                j = i;
                seekArq(j-h);
                aux.leDoArq(arquivo);
                numero2=aux.getNumero();
                while (j >= h && numero2 > numero) {
                    seekArq(j);
                    aux.gravaNoArq(arquivo);
                    j = j - h;
                }
                seekArq(j);
                c.gravaNoArq(arquivo);
            }
            h = h / 2;
        }

    }
    
    public void heap()  //andressa
    {
        
    }
    
    public void quick_com_pivo()    //laura
    {
        
    }
    
    public void quick_sem_pivo()  //andressa
    {
        
    }
    
    public void fusao_direta_merge1()    //laura
    {
        
    }
    
    public void fusao_direta_merge2()  //andressa
    {
        
    }
    
    public void counting()    //laura
    {
        Registro aux=new Registro();
        int num, maior, total = filesize();
        
        seekArq(0);
        aux.leDoArq(arquivo);
        maior=aux.getNumero();
        
	for (int i = 1; i < total; i++) 
        {
            seekArq(i);
            aux.leDoArq(arquivo);
            num=aux.getNumero();
            if (num > maior)
                maior = num;
        }
		
        // frequencia
	int[] c = new int[maior];
        
	for (int i = 0; i < total; i++)
        {
            seekArq(i);
            aux.leDoArq(arquivo);
            num=aux.getNumero()-1;
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
            
            b[c[num -1] -1] = num;
            c[num -1]--;
        }
		
	for (int i = 0; i < total; i++)
        {
            aux.setNumero(b[i]);
            seekArq(i);
            aux.gravaNoArq(arquivo);
        }
    }
    
    public void bucket()  //andressa ARRUMAR
    {
        int maior, i, baldes, total = filesize(), j, ind;
        int[] aux = new int[5];
        Registro reg = new Registro();
        
        seekArq(0);
        reg.leDoArq(arquivo);
        maior = reg.getNumero();
        
        for(i = 1; !eof(); i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            
            if(reg.getNumero() > maior)
                maior = reg.getNumero();
            
        }
        
        baldes = maior / 5;
        
        LinkedList[] b = new LinkedList[baldes];
        
        for(i = 0; i < baldes; i++)
            b[i] = new LinkedList<Integer>();
        
        for(i = 0; i < total; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            
            j = baldes - 1;
            while(j > 0)
            {
                if(reg.getNumero() >= (j*5))
                    b[j].add(reg.getNumero());
                j--;
            }
        }
        
        ind = 0;
        for(i = 0; i < baldes; i++)
            aux = new int[b[i].size()];
        
        for(j = 0; j < aux.length; j++)
            aux[j] = (Integer)b[i].get(j);
        
        insercao_direta();
        
        for(j = 0; j < aux.length; j++, ind++)
        {
            seekArq(ind);
            reg.setNumero(aux[j]);
            
            seekArq(ind);
            reg.gravaNoArq(arquivo);
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
                        p++;
                    }
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
            if(reg.getNumero() > rega.getNumero())
            {
                seekArq(p);
                rega.gravaNoArq(arquivo);
                
                seekArq(p+1);
                reg.gravaNoArq(arquivo);
                
                if(p > 0)
                {
                    p -= 2;
                }
            }
            p++;
        }
    }
    
    public void tim()    //laura
    {
        
    }
}
