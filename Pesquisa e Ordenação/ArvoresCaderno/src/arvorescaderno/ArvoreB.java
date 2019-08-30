
package arvorescaderno;

public class ArvoreB implements tamanho{
    private NoB raiz;

    public ArvoreB() {
    }

    public NoB getRaiz() {
        return raiz;
    }

    public void setRaiz(NoB raiz) {
        this.raiz = raiz;
    }
    
    public NoB NavegarAteFolha(int info)
    {
        NoB r = raiz;
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
    
    public NoB LocalizarPai(NoB Folha, int info)
    {
        NoB r = raiz;
        NoB pai = raiz;
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

    public void Inserir (int info, int posArq)
    {
        NoB Folha,pai;
        int pos;
        
        if(raiz==null)
            raiz = new NoB(info,posArq);
        else
        {
            Folha = NavegarAteFolha (info);
            pos = Folha.ProcurarPosicao (info);
            Folha.remaneja (pos);
            Folha.setTL(Folha.getTL()+1);
            Folha.setvInfo(pos, info);
            Folha.setvPos(pos, posArq);
            if(Folha.getTL() > 2*n)
            {
                pai = LocalizarPai(Folha, info);
                Split(Folha,pai);
            }
            
        }
    }
    
    public void Split (NoB Folha, NoB Pai)
    {
        NoB cx1, cx2; int info;
        int pos;
        cx1 = new NoB();
        cx2 = new NoB();
        
        for(int i = 0 ; i<n; i++)
        {
            cx1.setvInfo(i, Folha.getvInfo(i));
            cx1.setvPos(i, Folha.getvPos(i));
            cx1.setvLig(i, Folha.getvLig(i));
        }
        cx1.setvLig(n, Folha.getvLig(n));
        cx1.setTL(n);
        
        for(int i = n+1 ; i<2*n+1; i++)
        {
            cx2.setvInfo(i-(n+1), Folha.getvInfo(i));
            cx2.setvPos(i-(n+1), Folha.getvPos(i));
            cx2.setvLig(i-(n+1), Folha.getvLig(i));
        }
        cx2.setvLig(n, Folha.getvLig(2*n+1));
        cx2.setTL(n);
        
        if(Pai == Folha)
        {
            Folha.setvInfo(0, Folha.getvInfo(n));
            Folha.setvPos(0, Folha.getvPos(n));
            Folha.setvLig(0, cx1);
            Folha.setvLig(1, cx2);
            Folha.setTL(1);
        }
        else
        {
            info = Folha.getvInfo(n);
            pos = Pai.ProcurarPosicao(info);
            Pai.remaneja(pos);
            Pai.setTL(Pai.getTL()+1);
            Pai.setvInfo(pos, Folha.getvInfo(n));
            Pai.setvPos(pos, Folha.getvPos(pos));
            Pai.setvLig(pos, cx1);
            Pai.setvLig(pos+1, cx2);
            if(Pai.getTL()>2*n)
            {
                Folha = Pai;
                info = Pai.getvInfo(pos);
                Pai = LocalizarPai(Pai,pos);
                Split(Folha,Pai);
            }
        }
        
        
    }
    
    
    public void inOrdem(NoB raiz)
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
    
    
    public void Fusao (NoB Folha , NoB Pai, NoB IrmaE, NoB IrmaD, int pos)
    {
        if(IrmaE != null)
        {
            IrmaE.setvInfo(IrmaE.getTL(), Pai.getvInfo(pos - 1));
            IrmaE.setvPos(IrmaE.getTL(), Pai.getvPos(pos - 1 ));
            IrmaE.setTL(IrmaE.getTL()+1);
            IrmaE.setvInfo(IrmaE.getTL(), Folha.getvInfo(0));
            IrmaE.setvPos(IrmaE.getTL(), Folha.getvPos(0));
            IrmaE.setvLig(IrmaE.getTL()-1, Folha.getvLig(0));
            IrmaE.setvLig(IrmaE.getTL(), Folha.getvLig(1));
            IrmaE.setTL(IrmaE.getTL()+1);
            Pai.RemanejaEx(pos - 1);
            Pai.setTL(Pai.getTL()-1);
            Folha = IrmaE;
            
        }
        else
        {
            Folha.setvInfo(Folha.getTL(), Pai.getvInfo(pos));
            Folha.setvPos(Folha.getTL(), Pai.getvPos(pos));
            Folha.setTL(Folha.getTL()+1);
            for(int i = 0 ; i < IrmaD.getTL(); i++)
            {
                Folha.setvInfo(Folha.getTL(), IrmaD.getvInfo(i));
                Folha.setvPos(Folha.getTL(), IrmaD.getvInfo(i));
                Folha.setvLig(Folha.getTL(), IrmaD.getvLig(i));
                Folha.setTL(Folha.getTL()+1);
            }
            Folha.setvLig(Folha.getTL(), IrmaD.getvLig(IrmaD.getTL()));
            Pai.RemanejaEx(pos);
            Pai.setTL(Pai.getTL()-1);
            
        }
        if(Pai.getTL() < 1)
        {
            if(raiz == Pai && Pai.getTL() == 0)
                raiz = Folha;
            else
            {
                Folha = Pai; IrmaE = null; IrmaD = null;
                Pai = LocalizarPai(Folha, Folha.getvInfo(0));
                pos = Pai.ProcurarPosicao(Folha.getvInfo(0));
                if(pos > 0)
                    IrmaE = Pai.getvLig(pos - 1);
                if(pos < Pai.getTL())
                    IrmaD = Pai.getvLig(pos + 1);
                Fusao(Folha, Pai, IrmaE, IrmaD, pos);
            }
        }
    }
    
    public NoB BuscaInfo(int info)
    {
        {
            NoB noChave = raiz, ant = null;
            int pos;

            if (raiz == null)
                System.out.println("Erro! Não há elementos na árvore!");
            else
            {
                pos = noChave.busca(info);
                while (noChave != null && noChave.getvInfo(pos) != info || noChave != null && pos == noChave.getTL())
                {
                    ant = noChave;
                    noChave = noChave.getvLig(pos);
                    if (noChave != null)
                        pos = noChave.busca(info);
                }
                if (noChave != null)
                    return noChave;
            }
            return null;
        }
    }
    
    public NoB BuscarSubE (NoB No, int pos)
    {
        NoB pai;
        pai = LocalizarPai(No,No.getvInfo(pos));
        
        return pai.getvLig(pos-1) ;
    }
    
    public NoB BuscarSubD (NoB No, int pos)
    {
        NoB pai;
        pai = LocalizarPai(No,No.getvInfo(pos));
        
        return pai.getvLig(pos+1) ;
    }
    
    public void Exclusao (int info)
    {
        int pos, posp;
        NoB No, SubE, SubD, Folha, Pai, IrmaE=null, IrmaD=null;
        No = BuscaInfo(info);
        pos = No.ProcurarPosicao(info);
        if(No.getvLig(0) != null)
        {
            SubE = BuscarSubE(No, pos);
            SubD = BuscarSubD(No, pos);
            if(SubE.getTL() >= SubD.getTL())
            {
                No.setvInfo(pos, SubE.getvInfo(SubE.getTL()-1));
                No.setvPos(pos, SubE.getvPos(SubE.getTL()-1));
                SubE.setTL(SubE.getTL()-1);
                Folha = SubE;
            }
            else
            {
                No.setvInfo(pos, SubD.getvInfo(0));
                No.setvPos(pos, SubD.getvPos(0));
                SubD.RemanejaEx(0);
                SubD.setTL(SubD.getTL()-1);
                Folha = SubD;
            }
        }
        else
        {
            Folha = No;
            Folha.RemanejaEx(pos);
            Folha.setTL(Folha.getTL()-1);
        }
        if(Folha.getTL() < n)
        {
            Pai = LocalizarPai(Folha, info);
            posp = Pai.ProcurarPosicao(info);
            if(posp > 0)
                IrmaE = Pai.getvLig(posp + 1);
            if(posp < Pai.getTL())
                IrmaD = Pai.getvLig(posp - 1);
            if(IrmaE != null && IrmaE.getTL() > n)
            {
                Folha.remaneja(0);
                Folha.setvInfo(0, Pai.getvInfo(posp-1));
                Folha.setvPos(0, Pai.getvPos(posp - 1));
                Folha.setTL(Folha.getTL()+1);
                Pai.setvInfo(posp - 1, IrmaE.getTL()-1);
            }
            else
                if(IrmaD!=null && IrmaD.getTL() > n)
                {
                    Folha.setvInfo(Folha.getTL(), Pai.getvInfo(posp));
                    Folha.setvPos(Folha.getTL(), Pai.getvPos(posp));
                    Folha.setTL(Folha.getTL()+1);
                    Pai.setvInfo(posp, IrmaD.getvInfo(0));
                    Pai.setvPos(posp, IrmaD.getvPos(0));
                    IrmaD.RemanejaEx(0);
                    IrmaD.setTL(IrmaD.getTL()-1);
                }
                else
                {
                    Fusao(Folha, Pai, IrmaE, IrmaD, pos);
                }
            
        }
    }
    
}
