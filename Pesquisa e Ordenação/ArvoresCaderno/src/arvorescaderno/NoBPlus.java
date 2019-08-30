/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorescaderno;

/**
 *
 * @author renan
 */
public class NoBPlus implements tamanho{
    
    private int[] vInfo = new int[n];
    private NoBPlus[] vLig = new NoBPlus[n+1];
    private int TL;
    private NoBPlus ant, prox;

    public NoBPlus() {
    }
    
    public NoBPlus(int info) {
        this.vInfo[0] = info;
        this.TL = 1;
    }

    public int getvInfo(int pos) {
        return vInfo[pos];
    }

    public void setvInfo(int pos, int info) {
        this.vInfo[pos] = info;
    }

    public NoBPlus getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos,NoBPlus vlig) {
        this.vLig[pos] = vlig;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }

    public NoBPlus getAnt() {
        return ant;
    }

    public void setAnt(NoBPlus ant) {
        this.ant = ant;
    }

    public NoBPlus getProx() {
        return prox;
    }

    public void setProx(NoBPlus prox) {
        this.prox = prox;
    }
    
    public int ProcurarPosicao(int info)
    {
        int i=0;
        while(i<TL && info > vInfo[i])
            i++;
        return i;
    }
    
    public void remaneja (int pos)
    {
        this.vLig[TL+1] = this.vLig[TL];
        for(int i=TL; i>pos; i--)
        {
            vInfo[i] = vInfo[i-1];
            //vPos[i] = vPos[i-1];
            vLig[i] = vLig[i-1];
        }
    }
    
}
