package listagen;

class NoPilha
{
    private NoGen noGen;
    private NoPilha prox;
    
    public NoPilha(NoGen no, NoPilha prox) {
        this.prox = prox;
        this.noGen = no;
    }

    public NoGen getNo() {
        return noGen;
    }

    public void setNo(NoGen no) {
        this.noGen = no;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }
}

class Pilha
{
    private NoPilha topo;

    public Pilha() {
        topo = null;
    }
    
    public void push(NoGen no)
    {
        NoPilha novo = new NoPilha(no, topo);
        topo = novo;
    }
    
    public NoGen pop()
    {
        NoGen noGen = topo.getNo();
        topo = topo.getProx();
        return noGen;
    }
    
    public boolean isEmpty()
    {
        return topo == null;
    }
    
    public NoGen topo()
    {
        return topo.getNo();
    }
}

public class ListaGen
{
    private NoGen inicio;

    public ListaGen() {
        inicio = null;
    }
    
    public void testarAParada()
    {
        char[] ch = {'a', 'a'};
//       inicio = cons(cons(cons(criat(new char[]{'c','c'}), cons(criat(new char[]{'c','c'}), null)),null), cons(cons(cons(criat(new char[]{'c','c'}), null), null),null));
//       inicio = cons(criat(new char[]{'a', 'a'}), null);
        String lista = "[aa,[bb,cc],[dd,[ee],ff,gg],hh]";
        inserirStr2(lista);
    }
    
    
//    public void exibirAtomo(listagen * l)
//    {
//        pilha * p;
//        init(&p);
//        
//        puhs(&p, l);
//        while(!isEmpty(p))
//        {
//            if(!nula(l))
//            {
//                pop(&p, &l);
//                while(!nula(l) && !atomo(l))
//                {
//                    push(&p, l);
//                    l=head(l);
//                }
//                
//                if(atomo(l))
//                    printf("%s", l->no.info);
//            }
//            
//            pop(&p, &l);
//            l=tail(l);
//            if(!nulo(l))
//                push(&p, l);
//        }
//    }
    
    public void inserirStr2(String str)
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
    
    public void exibir(NoGen no)
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
    
    public static void main(String[] args)
    {
        ListaGen listaGen = new ListaGen();
        listaGen.testarAParada();
        listaGen.exibir();
    }
}