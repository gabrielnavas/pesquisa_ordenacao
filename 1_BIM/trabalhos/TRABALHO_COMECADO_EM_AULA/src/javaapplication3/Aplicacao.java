/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author Aluno
 */
public class Aplicacao {
    public static void main(String[] args) {
        ArquivoJava arq = new ArquivoJava("D:\\oi.dat");
//        arq.inserirRegNoFinal(new Registro(25, "gustavo terrorista", -92));
//        arq.inserirRegNoFinal(new Registro(25, "gustavo terrorista", -92));
        arq.exibirUmRegistro(0);
        Registro r1 = new Registro();
        Registro r2 = new Registro();
        r1.leDoArq(arq);
        r1.gravaNoArq(arq);
    }  
}
