/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import algoritmos_ordenacao.arquivo.Arquivo;

/**
 *
 * @author navas
 */
public class UtilApp {
    public static class TipoOrdenado
    {
        public static final int ORDENADO=0;
        public static final int REVERSO=1;
        public static final int RANDOMICO=2;       
    }
    
    
    public static class AlgoritmoNum
    {
        public static final int INSERCAO_DIRETA = 0;
        public static final int INSERCAO_BINARIA = 1;
        public static final int SELECAO_DIRETA = 2;
        public static final int BOLHA = 3;
        public static final int SHAKE = 4;
        public static final int SHELL = 5;
        public static final int HEAP = 6;
        public static final int QUICKCP = 7;
        public static final int QUICKSP = 8;
        public static final int MERGE1 = 9;
        public static final int MERGE2 = 10;
        public static final int COUNTING = 11;
        public static final int BUCKET = 12;
        public static final int RADIX = 13;
        public static final int COMB = 14;
        public static final int GNOME = 15;
        public static final int TIM = 16;
    }
    
    public static class ArquivoParams
    {
        public static final int QUANTIDADE_TOTAL_REG_ARQUIVO = 70;
//        public static final int QUANTIDADE_TOTAL_REG_ARQUIVO = 1024;
        
        public static final boolean DELETA_ARQUIVOS_DADOS = true;
        public static final boolean DELETA_ARQUIVO_TABELA = true;
        
       
        public static String gerarStringTamanho(int tamanho, String algoritmo)
        {
            //trata tamanho real da string para o arquivo

            char[] algoritmoName = new char[tamanho];

            for(int i=0 ; i < algoritmoName.length ; i++)
                algoritmoName[i] = ' ';

            for(int i=0 ; i < algoritmoName.length && i < algoritmo.length() ; i++)
                algoritmoName[i] = algoritmo.charAt(i);

            algoritmo = String.copyValueOf(algoritmoName);

            return algoritmo;
        }
    }
    
    
}



