package utils;

//<editor-fold defaultstate="collapsed" desc=".:: Imports ::.">
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//</editor-fold>

public class Arquivo {

//<editor-fold defaultstate="collapsed" desc=".:: Read ::.">
public static String Read(String Caminho) {
        String conteudo = "";
        try {

            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = "";

            try {
                linha = lerArq.readLine();

                while (linha != null) {
                    conteudo += linha + "\n";
                    linha = lerArq.readLine();
                }
                arq.close();
                Write(Caminho, "{\n"
                        + "    \"option\": \"Empty\"\n"
                        + "}");
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return "";
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=".:: Write ::.">
    public static boolean Write(String Caminho, String content) {
        try {
            FileWriter arq = new FileWriter(Caminho);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(content);
            gravarArq.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
//</editor-fold>

}