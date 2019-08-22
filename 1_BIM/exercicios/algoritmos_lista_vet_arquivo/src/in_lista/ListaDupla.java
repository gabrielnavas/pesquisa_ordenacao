package in_lista;

public class ListaDupla {
    
    private No inicio;
    private No fim;
    
    public ListaDupla()
    {
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public void inicia()
    {
        this.inicio = this.fim = null;
    }
    
    public void inserirFinal(int num)
    {
        
    }
    
    public void inserirInicio(int num)
    {
        
    }
    
    public void inserirOrdenado(int num)
    {
        
    }
    
    public No getNo(No no, int pos)
    {
        No aux = null;
        
        if(pos == 0)
            aux = no;
        else if (pos < 0)
        {
            aux = no;
            while(aux != null && pos < 0)
            {
                aux = aux.getAnt();
                pos++;
            }
        }
        else
        {
            aux = no;
            while(aux != null && pos > 0)
            {
                aux = aux.getProx();
                pos--;
            }
        }
        
        return aux;
    }
//    
//    public void shell()
//    {
//        int i, j, 
//            k, aux,
//            dist = 4,
//            tl;
//        
//        No noI=null,
//           noJ=null,
//           noK=null,
//           noDist=null;
//        
//        tl=getTl();
//        
//        while(dist > 0)
//        {
//            noI=inicio;
//            for(i = 0 ; i < dist ; i++)
//            {
//                noJ = noI;
//                for(j=i ; j+dist < tl ; j+=dist)
//                {
//                    noDist = getNo(noJ, dist);
//                    if(noJ.getInfo() > noDist.getInfo())
//                    {
//                        aux = noJ.getInfo();
//                        noJ.setInfo(noDist.getInfo());
//                        noDist.setInfo(aux);
//                    }
//                    
//                    noDist = getNo(noJ, -dist);
//                    noK = noJ;
//                    for(k=j ; k-dist >= i && noK.getInfo() < noDist.getInfo() ; k-=dist)
//                    {
//                        aux = noK.getInfo();
//                        noK.setInfo(noDist.getInfo());
//                        noDist.setInfo(aux);
//                        
//                        noK = getNo(noK, -dist);
//                    }
//                    
//                    noJ = getNo(noJ, dist);
//                }
//                
//                noI = getNo(noI, 1);
//            }
//            
//            dist = dist/4;
//        }
//    }
//
//    public int getTl() {
//        return tl;
//    }
//    
}
