/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebandbplus;

/**
 *
 * @author renan
 */
public class ArvoreBPlus implements tamanho{
    private NoBPlus raiz;

    public ArvoreBPlus() {
    }

    public NoBPlus getRaiz() {
        return raiz;
    }
    
    public NoBPlus NavegarAteFolha(int info)
    {
        NoBPlus r = raiz;
        int i;
        while(r.getvLig(0) != null)
        {
            //for(int i=0; i<r.getTl() && info > r.getvInfo(i));
            i=0;
            while(i<r.getTL() && info > r.getvInfo(i))
                i++;
            r=r.getvLig(i);
        }
        return r;
    }
    
    public NoBPlus LocalizarPai(NoBPlus Folha, int info)
    {
        NoBPlus r = raiz;
        NoBPlus pai = raiz;
        int i;
        while(r != Folha)
        {
            i=0;
            while(i<r.getTL() && info > r.getvInfo(i))
                i++;
            pai = r;
            r= r.getvLig(i);
        }
        return pai;
        
    }
    
    public void InsereBPlus (int info)
    {
        int pos, ppos;
        NoBPlus Folha,Pai,IrmaE=null,IrmaD=null;
        if(raiz == null)
            raiz = new NoBPlus(info);
        else
        {
            Folha = NavegarAteFolha(info);
            pos = Folha.ProcurarPosicao(info);
            Folha.remaneja(pos);
            Folha.setvInfo(pos, info);
            Folha.setTL(Folha.getTL()+1);
            if(Folha.getTL() == n)
            {
                Pai = LocalizarPai (Folha, info);
                ppos = Pai.ProcurarPosicao(info);
                if(ppos > 0)
                    IrmaE = Pai.getvLig(ppos - 1);
                if(ppos < Pai.getTL())
                    IrmaD = Pai.getvLig(ppos + 1);
                Split(Folha,Pai,IrmaE,IrmaD);
            }
        }
    }
    
    public void Split (NoBPlus Folha, NoBPlus Pai, NoBPlus IrmaE, NoBPlus IrmaD)
    {
        int calcdistri, pos;
        NoBPlus cx1, cx2;
        cx1 = new NoBPlus();
        cx2 = new NoBPlus();
        
        if(Folha.getvLig(0) == null) //Quando é folha
        {
            calcdistri = Math.round((float)(n-1) / 2);
            for(int i =0; i<calcdistri; i++)
            {
                cx1.setvInfo(i, Folha.getvInfo(i));
                cx1.setvLig(i, Folha.getvLig(i));
            }
            cx1.setvLig(calcdistri, Folha.getvLig(calcdistri));
            cx1.setTL(calcdistri);
            for(int i=calcdistri; i<Folha.getTL(); i++)
            {
                cx2.setvInfo(i - calcdistri, Folha.getvInfo(i));
                cx2.setvLig(i - calcdistri, Folha.getvLig(i));
            }
            cx2.setvLig(Folha.getTL()-calcdistri, Folha.getvLig(Folha.getTL()));
            cx2.setTL(Folha.getTL() - calcdistri);
            //juntar as caixas;
            cx1.setProx(cx2);
            cx2.setAnt(cx1);
            if(Folha == Pai) // só ligar
            {
                Folha.setvInfo(0, cx2.getvInfo(0));
                Folha.setTL(1);
                Folha.setvLig(0, cx1);
                Folha.setvLig(1, cx2);
            }
            else // pai não é a folha, procurar melhor posicao para inserir
            {
                pos = Pai.ProcurarPosicao(cx2.getvInfo(0));
                Pai.remaneja(pos);
                Pai.setvInfo(pos, cx2.getvInfo(0));
                Pai.setTL(Pai.getTL()+1);
                Pai.setvLig(pos, cx1);
                Pai.setvLig(pos+1, cx2);
                if(IrmaE != null)
                {
                    IrmaE.setProx(cx1);
                    cx1.setAnt(IrmaE);
                }
                if(IrmaD != null)
                {
                    IrmaD.setAnt(cx2);
                    cx2.setProx(IrmaD);
                }
            }
        }
        else // quando não é folha
        {
            calcdistri = (int) (n/2)-1;
            for(int i =0; i<calcdistri; i++)
            {
                cx1.setvInfo(i, Folha.getvInfo(i));
                cx1.setvLig(i, Folha.getvLig(i));
            }
            cx1.setvLig(calcdistri, Folha.getvLig(calcdistri));
            cx1.setTL(calcdistri);
            for(int i=calcdistri; i<Folha.getTL()-1; i++)
            {
                cx2.setvInfo(i - calcdistri, Folha.getvInfo(i+1));
                cx2.setvLig(i - calcdistri, Folha.getvLig(i+1));
            }
            cx2.setvLig(Folha.getTL()-(calcdistri+1), Folha.getvLig(Folha.getTL()));
            cx2.setTL(Folha.getTL() - (calcdistri+1));
            
            
            Folha.setvInfo(0, Folha.getvInfo(calcdistri));
            Folha.setTL(1);
            Folha.setvLig(0, cx1);
            Folha.setvLig(1, cx2);
        }
        if(Pai.getTL()==n)
        {
            Folha = Pai;
            Pai = LocalizarPai (Folha, Folha.getvInfo(0));
            pos = Pai.ProcurarPosicao(Folha.getvInfo(n-1));
            if(pos > 0)
                IrmaE = Pai.getvLig(pos - 1);
            if(pos < Pai.getTL())
                IrmaD = Pai.getvLig(pos + 1);
            Split(Folha, Pai, IrmaE, IrmaD);
        }
    }
    
    
    public void inOrdem(NoBPlus raiz)
    {
        int i;
        if (raiz != null)
        {
            for(i = 0; i < raiz.getTL(); i++)
            {
                inOrdem(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i));
            }
            inOrdem(raiz.getvLig(i));
        }
    }
    
    
    
    
    
}
