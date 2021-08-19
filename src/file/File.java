package file;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Autores: Emanuel Lindoso Ferreira e Pedro Víctor de Abreu Fonseca
 * Data de modificação: 18/08/2021 por Pedro Víctor de Abreu Fonseca
        -> Adesão de comentários.
 * Classe: File
 * O que faz: Lê arquivos.
 * Revisado em: ...
 */

public class File {
    
    /*
       * Função: leBuffered(String file)
       * Descrição: Lê um arquivo 'file'
       * Utilização: main(String []args), VerificaPlagio
       * Parâmetros:            
            file: Caminho contendo o arquivo que será lido.
       * Retorno: Vetor de strings onde cada posição representa uma palavra do arquivo.
     */

    public static String[] leBuffered(String file){
        FileInputStream arq = null;
        try {
            arq = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ex.getMessage());
        }
        InputStreamReader input = null; 
        try {
            input = new InputStreamReader(arq, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ex.getMessage());
        }
        BufferedReader buffer = new BufferedReader(input);
        String linha = "";
        String[] aux = null;
        List<String> texto = new ArrayList<>();
        try {
            linha = buffer.readLine();
            while (linha != null){
                texto.add(linha);
                linha = buffer.readLine();
            }
            linha = "";
            for(String s : texto){
                linha += " " + s;
            }
            linha = linha.replace(".", " ");
            linha = linha.replace(",", " ");
            aux = linha.split(" ");
                
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return aux;
    }        
}