package algoritmos_ordenacao.arquivo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.UtilApp.ArquivoParams;
import static utils.UtilApp.ArquivoParams.QUANTIDADE_TOTAL_REG_ARQUIVO;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class Arquivo {
    
    private String nomearquivo;
    private RandomAccessFile arquivo;
    
    private int comp;
    private int mov;
    
    public Arquivo(String nomeArquivo)
    {
        this.nomearquivo = nomeArquivo;
        
        this.comp=0;
        this.comp=0;
        
        try
        {
            arquivo = new RandomAccessFile(nomeArquivo, "rw");
        } 
        catch (IOException e) { }
    }

    public void copiaArquivo(RandomAccessFile arqOrig) 
    {
        Registro reg = new Registro();
        int tamArqOrigem = 0;
        
        try 
        {   
            tamArqOrigem = (int) arqOrig.length()/Registro.length();
                
            for(int i = 0 ; i < tamArqOrigem ; i++)
            {
                arqOrig.seek(i*Registro.length());
                reg.leDoArq(arqOrig);
                
                seekArq(i);    
                reg.gravaNoArq(arquivo);
            }    
        } 
        catch (IOException ex) 
        {
            
        }
    }
    
    public RandomAccessFile getArquivo() 
    { 
        return this.arquivo; 
    }
    
    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } 
        catch (IOException ex)
        { }
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
        } 
        catch (IOException e)
        { }
        
        return (retorno);
    }
    
    public void close()
    {
        try {
            this.arquivo.close();
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //insere um Registro no final do arquivo, passado por par�metro
    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(fileSize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }
    
    public int fileSize()
    {
        int registros;
                
        try
        {
            registros = (int) arquivo.length()/Registro.length();
        }
        catch(IOException e)
        {
            registros = 0;
        }
        
        return registros;
    }

    public boolean isOrdenacao()
    {
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        seekArq(0);
        
        regI.leDoArq(arquivo);
        regJ.leDoArq(arquivo);
        while(!eof() && regI.getNumero() < regJ.getNumero())
        {
            regI.leDoArq(arquivo);
            regJ.leDoArq(arquivo);
        }
        
        return eof();
    }
    
    public void exibirArq()
    {
        Registro reg = new Registro();
        
        seekArq(0);
        
        System.out.print(
            ArquivoParams.gerarStringTamanho(20, 
                    this.nomearquivo + " : ORDENADO? " +
                    isOrdenacao() + " => " 
            )
                
        );
        
        seekArq(0);
        
        while (!this.eof())
        {
            reg.leDoArq(arquivo);
            reg.exibirReg();
        }
        
        System.out.println();
    }
    
    public void exibirArqInText()
    {
        String line = "";
        seekArq(0);
        
        while(!eof())
        {
            try {
                line = arquivo.readLine();
            } 
            catch (IOException ex) {
                line = "Não foi possível exibir linha";
            }
            
            System.out.println(line);
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

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }

    public void leArq()
    {
        int codigo; //, idade;
//        String nome;
        codigo = Entrada.leInteger("Digite o código");
        while (codigo != 0)
        {
//            nome = Entrada.leString("Digite o nome");
//            idade = Entrada.leInteger("Digite a idade");
            inserirRegNoFinal(new Registro(codigo));
            codigo = Entrada.leInteger("Digite o código");
        }
    }
    
    public int getMaior()
    {
        int maior;
        
        Registro reg = new Registro();
        
        seekArq(0);
        reg.leDoArq(arquivo);
        
        maior = reg.getNumero();
        
        while(!eof())
        {
            reg.leDoArq(arquivo);
            if(reg.getNumero() > maior)
                maior = reg.getNumero();
        }
        
        return maior;
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
        return this.comp; 
    }
    
    public int getMov() 
    { 
        return this.mov; 
    }
    

    public void geraArquivoOrdenado()
    {
        Registro reg = new Registro();
        for(int i = 0; i < QUANTIDADE_TOTAL_REG_ARQUIVO; i++)
        {
            reg.setNumero(i + 1);
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
    }
    
    public void geraArquivoReverso()
    {
            Registro reg = new Registro();
        
        int num = QUANTIDADE_TOTAL_REG_ARQUIVO;
        
        for(int i = 0; i < QUANTIDADE_TOTAL_REG_ARQUIVO; i++, num--)
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

        for(int i = 0; i < QUANTIDADE_TOTAL_REG_ARQUIVO; i++)
            lista.add(i+1);
        
        Collections.shuffle(lista);
        
        for(int i = 0; i < QUANTIDADE_TOTAL_REG_ARQUIVO; i++)
        {
            reg.setNumero((int) lista.get(i));
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
    }
    
    //.............................................................................
    /*

    insira aqui os métodos de Ordenação;

    */
    public void insercaoDireta()
    {
        Registro regI = new Registro(), 
                 regAux = new Registro(),
                 regIAnt = new Registro();
        
        int i, pos, tl;
        
        tl = fileSize();
        
        i=1;
        while(i < tl)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            seekArq(i);
            regAux.leDoArq(arquivo);
            
            seekArq(i-1);
            regIAnt.leDoArq(arquivo);
            
            pos=i;
            while(pos > 0 && regIAnt.getNumero() > regAux.getNumero() )
            {
                seekArq(pos);
                regIAnt.gravaNoArq(arquivo);
                
                pos--;
                
                if(pos > 0)
                {
                    seekArq(pos-1);
                    regIAnt.leDoArq(arquivo);
                }
            }
            
            seekArq(pos);
            regAux.gravaNoArq(arquivo);
            
            i++;
        }
    }
    
    //errado
    public void insercaoDireta2(int ini, int fim)
    {
        Registro regPos = new Registro();
        Registro regAux = new Registro();
        int pos;
        
        for(int i=ini+1 ; i < fim ; i++)
        {
            seekArq(i);
            regAux.leDoArq(arquivo);
            pos = i;
            
            seekArq(i-1);
            regPos.leDoArq(arquivo);
            
            while(pos > ini && regPos.getNumero() > regAux.getNumero())
            {
                seekArq(pos-1);
                regPos.leDoArq(arquivo);
                
                seekArq(pos);
                regPos.gravaNoArq(arquivo);
                
                pos--;
            }
            
            seekArq(pos);
            regAux.gravaNoArq(arquivo);
        }
    }
    
    public void insercaoDireta(int ini, int fim)
    {
        int i, pos, num;
        Registro regAux = new Registro();
        Registro regPos = new Registro();
        
        i = ini;
        while(i < fim)
        {
            seekArq(i+1);
            regAux.leDoArq(arquivo);
            pos = i+1;
            
//            num = vet[i+1];
//            pos = i+1;
            seekArq(pos-1);
            regPos.leDoArq(arquivo);

            while(pos > ini && regAux.getNumero() < regPos.getNumero()/*num < vet[pos-1]*/)
            {
                seekArq(pos-1);
                regPos.leDoArq(arquivo);
                
                seekArq(pos);
                regPos.gravaNoArq(arquivo);
//                vet[pos] = vet[pos-1];
                pos--;
                
                if(pos > ini)
                {
                    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA 2 HORAS tentado achar esse erro 
                    seekArq(pos-1);
                    regPos.leDoArq(arquivo);
                }
            }
            
            seekArq(pos);
            regAux.gravaNoArq(arquivo);
//            
//            vet[pos] = num;
            i++;
        }
    }
    
    public int buscaBinaria(int chave, int tl)
    {
        int inicio = 0, 
            fim = tl-1, 
            meio = fim/2;
        
        Registro noM = new Registro();
        
        seekArq(meio);
        noM.leDoArq(arquivo);
        
	while(inicio < fim && noM.getNumero() != chave)
	{
            if(chave < noM.getNumero())
                fim = meio-1;
            else
                inicio = meio+1;

            meio = (inicio + fim)/2;

            seekArq(meio);
            noM.leDoArq(arquivo);
	}
        
	if(noM.getNumero() < chave)
            return meio+1;
	else
            return meio;
    }
    
    public void insercaoBinaria()
    {
        int pos, tl = fileSize();
        
        Registro noPos = new Registro();
        Registro noAux = new Registro();
        
        for(int i = 1; i < tl; i++)
        {
            seekArq(i);
            noPos.leDoArq(arquivo);
            
            seekArq(i);
            noAux.leDoArq(arquivo);
            pos = buscaBinaria(noAux.getNumero(), i);
            
            for(int j=i ; j > pos ; j--)
            {
                seekArq(j-1);
                noPos.leDoArq(arquivo);
                
                seekArq(j);
                noPos.gravaNoArq(arquivo);
                
            }
            
            seekArq(pos);
            noAux.gravaNoArq(arquivo);
        }
    }
    
    
    public void selecaoDireta()
    {
        Registro regI = new Registro(),
                 regJ = new Registro(),
                 regMenor = new Registro();
        
        int menor, 
            posMenor,
            i, j, tl;
        
        tl=fileSize();
        
        i=0;
        while(i < tl-1)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            
            posMenor=i;
            seekArq(posMenor);
            regMenor.leDoArq(arquivo);
            
            j=i+1;
            while(j < tl)
            {
                seekArq(j);
                regJ.leDoArq(arquivo);
                
                if(regJ.getNumero() < regMenor.getNumero())
                {
                    posMenor = j;
                    
                    seekArq(posMenor);
                    regMenor.leDoArq(arquivo);
                }
                j++;
            }
            
            seekArq(posMenor);
            regI.gravaNoArq(arquivo);
            
            seekArq(i);
            regMenor.gravaNoArq(arquivo);
            
            i++;
        }
    }
    
    public void bolha()
    {
        int tl = fileSize();
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        while(tl > 1)
        {
            for(int i=0 ; i < tl-1 ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                regJ.leDoArq(arquivo);
                
                comp++;
                if(regI.getNumero() > regJ.getNumero())
                {
                    mov+=2;
                    
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    regI.gravaNoArq(arquivo);
                }
            }
            
            tl--;
        }
    }
    
    public void shake()
    {
        int ini, fim, i;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        ini = 0 ;
        fim = fileSize()-1;
        
        while(ini < fim)
        {
            for(i=ini ; i < fim ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                regJ.leDoArq(arquivo);
                
                comp++;
                if(regI.getNumero() > regJ.getNumero())
                {
                    mov+=2;
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    regI.gravaNoArq(arquivo);
                }
            }
            fim--;
            
            for(i = fim ; i > ini ; i--)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                
                seekArq(i-1);
                regJ.leDoArq(arquivo);
                
                comp++;
                if(regI.getNumero() < regJ.getNumero())
                {
                    mov+=2;
                    
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    
                    seekArq(i-1);
                    regI.gravaNoArq(arquivo);
                }
            }
            
            ini++;
        }
    }
    
    public void shell()
    {
        int i, j, k, tl, dist=4;

        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        tl=fileSize();

        while(dist > 0)
        {
            for(i=0 ; i < dist ; i++)
                for(j=i ; j+dist < tl ; j+= dist)
                {
                    seekArq(j);
                    reg1.leDoArq(arquivo);

                    seekArq(j+dist);
                    reg2.leDoArq(arquivo);

                    comp++;
                    if(reg1.getNumero() > reg2.getNumero())
                    {
                        mov +=2;
                        
                        seekArq(j);
                        reg2.gravaNoArq(arquivo);
                        
                        seekArq(j+dist);
                        reg1.gravaNoArq(arquivo);
                    

                        if(j-dist >= i)
                        {   
                            mov=+2;

                            seekArq(j);
                            reg1.leDoArq(arquivo);

                            seekArq(j-dist);
                            reg2.leDoArq(arquivo);

                            comp++;

                            k=j;
                            while(k-dist >= i && reg1.getNumero() < reg2.getNumero())
                            {
                                mov=+2;

                                seekArq(k);
                                reg2.gravaNoArq(arquivo);

                                seekArq(k-dist);
                                reg1.gravaNoArq(arquivo);

                                k -= dist;

                                if(k-dist >= i)
                                {
                                    mov=+2;

                                    seekArq(k);
                                    reg1.leDoArq(arquivo);

                                    seekArq(k-dist);
                                    reg2.leDoArq(arquivo);
                                }

                                comp++; 
                            }
                        }
                    }
                }

            dist = dist /2;
        }
    }

    public void heap()
    {
        int pai, fd, fe, maiorf; 
        int tl = fileSize();
        
        Registro reg1 = new Registro(); 
        Registro reg2 = new Registro();
        
        while(tl > 1)
        {
            pai = tl/2 - 1;
            while(pai >= 0)
            {
                fe=pai+pai+1;
                fd=fe+1;
                maiorf = fe;
                
                if(fd < tl)
                {
                    seekArq(fe);
                    reg1.leDoArq(arquivo);
                    
                    seekArq(fd);
                    reg2.leDoArq(arquivo);
                
                    if(reg2.getNumero() > reg1.getNumero())   
                        maiorf = fd;
                }
                
                seekArq(maiorf);
                reg1.leDoArq(arquivo);
                
                seekArq(pai);
                reg2.leDoArq(arquivo);
                
                if(reg1.getNumero() > reg2.getNumero())
                {
                    seekArq(maiorf);
                    reg2.gravaNoArq(arquivo);
                    
                    seekArq(pai);
                    reg1.gravaNoArq(arquivo);
                }
                
                pai--;
            }
            
            seekArq(0);
            reg1.leDoArq(arquivo);
            seekArq(tl-1);
            reg2.leDoArq(arquivo);
            
            seekArq(0);
            reg2.gravaNoArq(arquivo);
            seekArq(tl-1);
            reg1.gravaNoArq(arquivo);
            
            tl--;
        }
    }

    
    public void quickSP()
    {
        quickSP(0, fileSize()-1);
    }
    
    public void quickSP(int ini, int fim)
    {
        int i, j;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        i = ini;
        j = fim;
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(i < j && regI.getNumero() <= regJ.getNumero())
            {
                i++;
                
                seekArq(i);
                regI.leDoArq(arquivo);
            }
            
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            seekArq(j);            
            regI.gravaNoArq(arquivo);
            
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(i < j && regJ.getNumero() >= regI.getNumero())
            {
                j--;
                
                seekArq(j);
                regJ.leDoArq(arquivo);
            }
            
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            seekArq(j);            
            regI.gravaNoArq(arquivo);
        }
        
        if(ini < i-1)
            quickSP(ini, i-1);
        
        if(j+1 < fim)
            quickSP(j+1, fim);
    }
    
    public void quickSort()
    {
        quickSort(0, fileSize()-1);
    }
    
    public void quickSort(int ini, int fim)
    {
        int i=ini, 
            j=fim;
        
        boolean flag = true;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            
            if(flag)
                while(i < j && regI.getNumero() <= regJ.getNumero())
                {
                    i++;

                    seekArq(i);
                    regI.leDoArq(arquivo);
                }
            
            else
                while(i < j && regJ.getNumero() >= regI.getNumero())
                {
                    j--;

                    seekArq(j);
                    regJ.leDoArq(arquivo);
                }
            
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            seekArq(j);            
            regI.gravaNoArq(arquivo);
            flag = !flag;
        }
        
        if(ini < i-1)
            quickSort(ini, i-1);
        
        if(j+1 < fim)
            quickSort(j+1, fim);
    }
    
    public void quickCP()
    {
        quickCP(0, fileSize()-1);
    }
    
    public void quickCP(int ini, int fim)
    {
        int i, j, pivo;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        pivo = (ini+fim)/2;
        seekArq(pivo);
        regI.leDoArq(arquivo);
        pivo = regI.getNumero();
        
        i=ini;
        j=fim;
        while(i < j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);    
            while(regI.getNumero() < pivo)
            {
                i++;
                regI.leDoArq(arquivo);
            }
            
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(regJ.getNumero() > pivo)
            {
                j--;
                seekArq(j);
                regJ.leDoArq(arquivo);
            }
            
            if(i <= j)
            {
                seekArq(i);
                regJ.gravaNoArq(arquivo);
                
                seekArq(j);
                regI.gravaNoArq(arquivo);
                
                i++;
                j--;
            }
        }
        
        if(ini < j)
            quickCP(ini, j);
        if(i < fim)
            quickCP(i, fim);
    }
    
    public void merge1()
    {
        
    }
    
    public void fusao1()
    {
        
    }
    
    
    public void merge2()
    {
        Arquivo aux = new Arquivo("arqaux.bin");
        
        merge2(aux, 0, fileSize()-1);
        
        new File("arqaux.bin").delete();
    }
    
    public void merge2(Arquivo arqAux, int ini, int fim)
    {
        int meio;
        
        if(ini < fim)
        {
            meio = (ini+fim) / 2;
            
            merge2(arqAux, ini, meio);
            merge2(arqAux, meio+1, fim);
            fusao2(arqAux, ini, meio, meio+1, fim);
        }
    }
    
    public void fusao2(Arquivo arqAux, int ini1, int fim1, int ini2, int fim2)
    {
        int iniAux = ini1;
        
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        arqAux.seekArq(0);
        
        while(ini1 <= fim1 && ini2 <= fim2)
        {
            seekArq(ini1);
            regI.leDoArq(arquivo);
            
            seekArq(ini2);
            regJ.leDoArq(arquivo);
            
            if(regI.getNumero() < regJ.getNumero())
            {
                regI.gravaNoArq(arqAux.getArquivo());
                ini1++;
            }
            else
            {
                regJ.gravaNoArq(arqAux.getArquivo());
                ini2++;
            }
        }
        
        seekArq(ini1);
        while(ini1 <= fim1)
        {
//            seekArq(ini1);
            regI.leDoArq(arquivo);
            
            regI.gravaNoArq(arqAux.getArquivo());
            ini1++;
        }

        seekArq(ini2);
        while(ini2 <= fim2)
        {
//            seekArq(ini1);
            regJ.leDoArq(arquivo);
            
            regJ.gravaNoArq(arqAux.getArquivo());
            ini2++;
        }
        
        arqAux.seekArq(0);
        seekArq(iniAux);
        while(!eof())
        {
//            arqAux.seekArq(ini);
            regI.leDoArq(arqAux.getArquivo());
            
//            seekArq(i);
            regI.gravaNoArq(arquivo);
        }
    }
    
    public void counting()
    {
        int maior, tl;
        int[] vetAux;
        
        //pega maior valor
        maior = getMaior();
        
        tl = fileSize();
        
        Registro[] vetSaida = new Registro[tl];
        Registro regI = new Registro();
        
        //vetor auxiliar
        vetAux = new int[maior+1];
        
        //pegar frequencia
        seekArq(0);
        for(int i=0 ; !eof(); i++)
        {
            regI.leDoArq(arquivo);
            vetAux[regI.getNumero()]++;
        }
        
        //montar acumulativa
        for(int i=1 ; i < vetAux.length ; i++)
            vetAux[i] += vetAux[i-1];
        
        //gerar vetor ordenado ja
        seekArq(0);
        for(int i=0 ; !eof() ; i++)
        {
            regI.leDoArq(arquivo);           
            vetSaida[ --vetAux[ regI.getNumero() ]] = regI.getClone();

        }
        
        //devolver pro arquivo
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            regI = vetSaida[i];
            regI.gravaNoArq(arquivo);
        }
    } 
    
    public void bucket()
    {
        Registro regI = new Registro();
        
        int tl = fileSize();
        
        //pega maior valor
        int maior = getMaior(), hash;
        
        //definir quantidade de baldes
        int baldes = maior/5;
        
        //definir os baldes
        Arquivo[] buckets = new Arquivo[ baldes+1 ];
        
        //iniciar os baldes
        for(int i=0 ; i < buckets.length ; i++)
            buckets[i] = new Arquivo("bucket"+i);
        
        //filtrar dados no baldes
        seekArq(0);
        for(int i=0 ; i < tl ; i++)
        {
            //leio reg
            regI.leDoArq(arquivo);
            
            //faço hash
            hash = regI.getNumero()/5;
            
            //insiro o reg inteiro no final do arq bucket
            buckets[hash].inserirRegNoFinal(regI);
        }
        
        //ordenar os buckets
        for(int i=0 ; i < buckets.length ; i++)
            buckets[i].insercaoDireta();
        
        //gravar de volta passando por todos os buckets
        seekArq(0);
        for(int i=0 ; i < buckets.length ; i++)
        {
            buckets[i].seekArq(0);
            
            for(int j=0 ; j < buckets[i].fileSize() ; j++)
            {
                regI.leDoArq(buckets[i].getArquivo());
                regI.gravaNoArq(arquivo);
            }
        }
        
//        deletar os buckets aux
        for(int i=0 ; i < buckets.length ; i++)
            new File("bucket"+i).delete();
    }
    
    public void radix_basico()
    {
        int maior, exp, tl;
        
        int[] vetAux;
        Registro[] vetSaida;
        Registro regI = new Registro();
        
        tl = fileSize();
        vetSaida = new Registro[tl];

        maior = getMaior();
        exp=1;
        while(maior/exp > 0)
        {
            vetAux = new int[10];
            
            //pega frequencia
            seekArq(0);
            for(int i=0 ; i < tl ; i++)
            {
                regI.leDoArq(arquivo);
                vetAux[ (regI.getNumero()/exp) % 10 ]++;
            }
            
            //gera acumulativa da frequencia
            for(int i=1 ; i < 10 ; i++)
                vetAux[i] += vetAux[i-1];
            
            //gera o vetor ordenado
            
            for(int i=tl-1 ; i >= 0 ; i--)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                vetSaida[ --vetAux[ (regI.getNumero()/exp) % 10 ]] = regI.getClone(); 
            }
            
            //devolve pro arquivo
            seekArq(0);
            for(int i=0 ; i < tl ; i++)
            {
                regI = vetSaida[i];
                regI.gravaNoArq(arquivo);
            }
            
            exp = exp * 10;
        }
    }
    
    public void radix()
    {
        int maior, exp, tl, posAux;
        
//        Arquivo arqAux = new Arquivo("arqaux");
        int[] vetAux = new int[10];
        Arquivo arqSaida = new Arquivo("arqsaida");
        Registro regI = new Registro();
        
        tl = fileSize();
        arqSaida.truncate(tl);

        maior = getMaior();
        exp=1;
        while(maior/exp > 0)
        {     
            vetAux = new int[10];
            
            //pega frequencia
            seekArq(0);
            for(int i=0 ; i < tl ; i++)
            {
                regI.leDoArq(arquivo);
                vetAux[ (regI.getNumero()/exp) % 10 ]++;
            }
            
            //gera acumulativa da frequencia
            for(int i=1 ; i < 10 ; i++)
                vetAux[i] += vetAux[i-1];
            
            //gera o vetor ordenado
            for(int i=tl-1 ; i >= 0 ; i--)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                
                posAux = --vetAux[ (regI.getNumero()/exp) % 10 ];
                arqSaida.seekArq( posAux );
                regI.gravaNoArq(arqSaida.getArquivo());
            }
            
            //devolve pro arquivo
            seekArq(0);
            arqSaida.seekArq(0);
//            for(int i=0 ; i < tl ; i++)
            while(!arqSaida.eof())
            {
                regI.leDoArq(arqSaida.getArquivo());
                regI.gravaNoArq(arquivo);
            }
            
            exp = exp * 10;
        }
        
//        new File("arqaux").delete();
        new File("arqsaida").delete();
    }
    
    public void gnome()
    {
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        //tamanho total arquivo
        int tl = fileSize();
        
        //enquanto tiver mais que um reg
        while(tl > 1)
        {
            //loop igual o bolha
            for(int i=0 ; i < tl-1 ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                regJ.leDoArq(arquivo);
                
                if(regI.getNumero() > regJ.getNumero())
                {
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    regI.gravaNoArq(arquivo);
                
                    //casi o regi for maior que regj volta duas casass
                    if(i > 0)
                        i = i - 2;
                }
            }
            
            tl--;
        }
        
    }
    
    
    public void comb()
    {
        int tl = fileSize();
        int dist;
     
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        dist = (int) ( tl / 1.3);
        while(dist > 0)
        {
            for(int i=0 ; i + dist < tl ; i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                
                seekArq(i+dist);
                regJ.leDoArq(arquivo);
                
                if(regI.getNumero() > regJ.getNumero())
                {
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    
                    seekArq(i+dist);
                    regI.gravaNoArq(arquivo);
                }
            }
            
            dist = (int) ( dist / 1.3);
        }
    }
    
    public void tim()
    {
        int ini1, ini2, fim1, fim2;
        int run = 32;
        Arquivo arqAux;
        
        int tl = fileSize();
        
        if(tl < run)
            this.insercaoDireta();
        else
        {
            for(int i=0 ; i < tl ; i += run)
            {
                ini1 = i;
                fim1 = Math.min(i+run-1, tl-1);
                insercaoDireta(ini1, fim1);
            }
        

            arqAux = new Arquivo("arqaux.bin");

            seekArq(0);
            for(int run2 = run ; run2 < tl ; run2 = 2* run)
            {
                for(ini1 = 0 ; ini1 < tl ; ini1 += 2*run)
                {
                    fim1 = ini1 + run2-1;

                    ini2 = fim1+1;
                    fim2 = Math.min( ini1 + (2*run2-1), tl-1);

                    //o seekArq vai andando dentro do fusao, quando retira os dados do aux e passa para o arquivo.
                    fusao2(arqAux, ini1, fim1, ini2, fim2);
                }
            }
        
            new File("arqaux.bin").delete();
        }
    }
}
