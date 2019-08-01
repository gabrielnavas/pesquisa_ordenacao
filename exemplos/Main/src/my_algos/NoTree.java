/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_algos;

public class NoTree {
    private int info;
    private NoTree esq;
    private NoTree dir;
    
    public NoTree(int info) {
        this.info = info;
        this.esq = this.dir = null;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NoTree getEsq() {
        return esq;
    }

    public void setEsq(NoTree esq) {
        this.esq = esq;
    }

    public NoTree getDir() {
        return dir;
    }

    public void setDir(NoTree dir) {
        this.dir = dir;
    }

    
}
