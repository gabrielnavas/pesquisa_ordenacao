package lista_generica;

/*
    Aluno: Gabriel Miguel Navas
    RA: 261741888
*/

public class Aplicacao 
{  
    public static void main(String[] args)
    {
        ListaGen lg = new ListaGen();

        String strGen = Entrada.lerString("\n\nDigite a string genérica: ");

        while( !strGen.isEmpty() )
        {
            System.out.println("Limpando lista já existe se ouver.");
            lg.init();
            
            System.out.println("Processando sua string, aguarde.");
            lg.inserirStr(strGen);
            
            System.out.println("Sua lista genérica ficou assim: ");
            lg.exibir();

            strGen = Entrada.lerString("\n\nDigite a string genérica: ");
        }
    }
}
