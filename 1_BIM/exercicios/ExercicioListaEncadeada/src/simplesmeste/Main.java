package simplesmeste;

public class Main
{
    
    public static void main(String[] args) {
        String [][] dados = 
                new String[][]{
                    {"Paraná", 
                        "Londrina", "Maringá", "Arapongas", "Apucarana"},
                    {"Santa Catarina", 
                        "Joinville", "Blumenau"},
                    {"São Paulo", 
                        "Presidente Prudente", "Marília", "Assis"}
                };
        
        Aplicacao app = new Aplicacao();
        app.inserirDados(dados);
        app.relatorioGeral();
    }
    
}
