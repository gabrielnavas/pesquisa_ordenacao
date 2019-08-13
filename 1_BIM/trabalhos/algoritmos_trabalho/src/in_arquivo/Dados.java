package in_arquivo;

public class Dados {
    public static Registro[] getRegistros()
    {
        Registro[] registros = new Registro[10];
        
        registros[0] = new Registro(15, "Gabriel", 26);
        registros[1] = new Registro(12, "João", 43);
        registros[2] = new Registro(10, "Julia", 25);
        registros[3] = new Registro(8, "Maria", 76);
        registros[4] = new Registro(5, "Douglas", 87);
        registros[5] = new Registro(7, "Ricardo", 44);
        registros[6] = new Registro(4, "José", 77);
        registros[7] = new Registro(6, "José", 77);
        registros[8] = new Registro(2, "Ana", 66);
        registros[9] = new Registro(1, "Josélia", 55);
        
        return registros;
    }
}
