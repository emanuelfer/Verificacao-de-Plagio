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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import sun.misc.IOUtils;

/**
 *
 * @author emanu
 */
public class File {
    
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
