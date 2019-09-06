package lista_generica;


/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class ListaGen
{
    private NoGen inicio;

    public ListaGen() {
        inicio = null;
    }
    
    public void init()
    {
        inicio = null;
    }
    
    public void inserirStr(String str)
    {
        Pilha p = new Pilha();
        NoGen no;
        
        char[] info = new char[2];
        
        int i=0;
        
        //mudar isso depois :)
        if(str.charAt(i) != '[')
            return;
        
        inicio = cons(null, null);
        no = inicio;
        i++;
        
        p.push(inicio);
        while(!p.isEmpty() && i < str.length())
        {   
            if(str.charAt(i) != ']' && str.charAt(i) != '[' && str.charAt(i) != ',' && i < str.length())
            {
                int j=0;
                do
                {
                    info[j] = str.charAt(i);
                    j++;
                    i++;
                    
                }while(str.charAt(i) != ']' && str.charAt(i) != ',' && j < 2);
                
                ((No) no).setHead(criat(info)); //seta info
            }
            
            if(str.charAt(i) == '[')
            {              
                ((No) no).setHead(cons(null, null)); //cria head

                p.push(((No) no).getHead());
                
//                p.push(((No) no).getHead()); //empilha head
                no = ((No) no).getHead(); //aponta para head
                i++;
            }
            
            
            if(str.charAt(i) == ',')
            {   
                if(!p.isEmpty())
                    p.pop(); //tira no da pilha 
                
                ((No) no).setTail(cons(null, null)); //seta tail

                p.push(((No) no).getTail()); //empilha tail
                no = ((No) no).getTail();
                i++;
            }
            
            if(str.charAt(i) == ']')
            {
//                ((No) no).setTail();
                p.pop(); //acabou, tira o no atual e vou para cima;
                
                if(!p.isEmpty())
                    no = p.topo();
//                p.push(((No) no).getTail());
                i++;
            }
            
        }
    }
    
    private int isEspaco(String str, int pos)
    {
        while(str.charAt(pos) == ' ')
            pos++;
        return pos;
    }
    
    private boolean isLetra(char c)
    {
        boolean letra  = ((c >= 'A') && (c <= 'Z')) ||
          ((c >= 'a') && (c <= 'z'));
        
        return letra;
    }
   
    public void exibir()
    {
        //Fé é preciso!
        NoGen aux = inicio;
        exibir(aux);
    }
    
    private void exibir(NoGen no)
    {
        if(!isNulo(inicio))
        {
            if(isAtomo(no))
                System.out.print( ((Info) no).getInfo() );
            else
            {
                System.out.print("[");
                while(!isNulo(no))
                {
                    exibir( head(no) );
                    no = tail(no);
                    if(!isNulo(no))
                        System.out.print(", ");
//                    else
//                        System.out.print(", null");

                }
                System.out.print("]");
            }
        }
        else
            System.out.print("]");
    }
    
    public NoGen criat(char[] info)
    {
        return new Info(info);
    }
    
    private NoGen cons(NoGen h, NoGen t)
    {
        NoGen novo = null;
        
        if(isAtomo(t))
            System.out.println("TAIL não pode ser terminal.");
        else
        {
            novo = new No();
            ((No)novo).setHead(h);
            ((No)novo).setTail(t);
        }
        
        return novo;
    }

    private NoGen head(NoGen no)
    {
        NoGen retorno = null;
        
        if(isNulo(no) || isAtomo(no))
            System.out.println("no nao pode ser nulo ou atomo para retornar a cabeca.");
        else
            retorno = ((No) no).getHead();
            
        return retorno;
    }
    
    private NoGen tail(NoGen no)
    {
        NoGen retorno = null;
        
        if(isNulo(no) || isAtomo(no))
            System.out.println("no nao pode ser nulo ou atomo para retornar a cauda.");
        else
            retorno = ((No) no).getTail();
            
        return retorno;
    }
    
    private boolean isNulo(NoGen no)
    {
        return no == null;
    }
    
    private boolean isAtomo(NoGen no)
    {
        boolean retorno = !isNulo(no) && (no instanceof Info);
        return retorno;
    }    
}