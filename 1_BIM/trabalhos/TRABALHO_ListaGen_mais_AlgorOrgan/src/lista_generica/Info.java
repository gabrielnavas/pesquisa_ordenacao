/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista_generica;

/**
 *
 * @author navas
 */
public class Info extends NoGen
{
    private char[] info;

    public Info() {
        info = new char[2];
    }

    public Info(char[] info) {
        
        this.info = new char[2];
        this.info[0] = info[0];
        this.info[1] = info[1];
    }

    public char[] getInfo() {
        return info;
    }

    public void setInfo(char[] info) {
        this.info = info;
    }
}
