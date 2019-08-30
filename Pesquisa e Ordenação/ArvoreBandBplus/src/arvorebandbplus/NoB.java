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
public class NoB  implements tamanho{
    private int vInfo[] = new int[2*n+1];
    private NoB vLig[] = new NoB[2*n+2];
    private int vPos[] = new int[2*n+1];
    private int TL = 0;

    public NoB() {
    }

    public NoB(int info, int posArq)
    {
        this.vInfo[0] = info;
        this.vPos[0] = posArq;
        TL=1;
    }
    
    public int busca(int info)
    {
        int i;
        i = 0;
        while (i < tl && info > vInfo[i])
            i++;
        return i;
    }
    

    public int getvInfo(int pos) {
        return vInfo[pos];
    }

    public void setvInfo(int pos, int info) {
        this.vInfo[pos] = info;
    }

    public NoB getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, NoB lig) {
        this.vLig[pos] = lig;
    }

    public int getvPos(int pos) {
        return vPos[pos];
    }

    public void setvPos(int pos, int p) {
        this.vPos[pos] = p;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }
    
    public void remaneja (int pos)
    {
        this.vLig[TL+1] = this.vLig[TL];
        for(int i=TL; i>pos; i--)
        {
            vInfo[i] = vInfo[i-1];
            vPos[i] = vPos[i-1];
            vLig[i] = vLig[i-1];
        }
    }
    
    public int ProcurarPosicao(int info)
    {
        int i=0;
        while(i<TL && info > vInfo[i])
            i++;
        return i;
    }
    
    public void RemanejaEx (int pos)
    {
        for(int i = pos;i<TL;i++)
        {
            vInfo[i]= vInfo[i+1];
            this.vPos[i]= this.vPos[i+1];
            vLig[i]= vLig[i+1];
           
        }
    }
    
    
}
