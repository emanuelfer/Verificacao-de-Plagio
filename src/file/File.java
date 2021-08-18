/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
//import sun.misc.IOUtils;

/**
 *
 * @author emanu
 */
public class File {
    
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
        String[] aux = null; //Testar com LinkedList
        List<String> texto = new ArrayList<>();
        try {
            linha = buffer.readLine();
            while (linha != null){
                texto.add(linha);
<<<<<<< HEAD
                while (linha != null){
                    linha = buffer.readLine();
                    texto.add(linha);
                    
                }
                linha = "";
                for (String s : texto){                   
                        linha += " " + s;
                    
                }
                linha = linha.replace(".", " ");
                linha = linha.replace(",", " ");
                aux = linha.split(" "); //nomeDalista = linha.split(' ');
                
            }
            buffer.close();
=======
                linha = buffer.readLine();
            }
            linha = "";
            for(String s : texto){
                linha += " " + s;
            }
            linha = linha.replace(".", " ");
            linha = linha.replace(",", " ");
            aux = linha.split(" ");
                
            
            
>>>>>>> 361233bc18d9baee99358812c10a0a03ce9e9dcd
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return aux;
    }
    
    public static String[] read(String file){
        List<String> texto = null;
        Path path = Paths.get(file);
        String string = "";
        String[] aux = null;
        try{
            texto = Files.readAllLines(path);   
            for(String s : texto){
                

                string += " " + s;
            }
            
            string = string.replace(".", "");
            string = string.replace(",", "");
            
            aux = string.split(" ");
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        return aux;
    }
}
