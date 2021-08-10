/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import file.File;
import hash.HashEncadeado;
import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author emanu
 */
public class VerificaPlagio {
    
    public void carregaArquivo(String file, HashEncadeado he){
        String[] aux = File.read(file);

                        int j;
        String frase = "";
        boolean flag = false;
        for(int i = 0; i < aux.length-10; i++){
            j=0;
            while(j < 10){
                if(aux[i+j].isEmpty()){
                    i++;
                    continue;
                }
                
                frase += " " + aux[i+j];
                j++;
            }
            
            he.insert(frase, frase);
            frase = "";
        }
        
    }
    
    public boolean verifica(String file, HashEncadeado he){
        String[] texto = File.read(file);
        List<String> list;
        int j = 0;
        String frase = "";
        boolean flag = true;
        
        for(int i = 0; i < texto.length-10; i++){
            j=0;
            frase = "";
            while(j < 10){
                if(texto[i+j].isEmpty()){
                    i++;
                    continue;
                }

                frase += " " + texto[i+j];
                j++;
            }
            list = he.findAll(frase);
            if(!list.isEmpty()){
                for(String string : list){
                    if(frase.equals(string)){
                        return true;
                    }
                }
            }
              
        }
        
        return false;
    }
    
    public boolean isAlphaNumeric(String s){
        String pattern= "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }
    
    public String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
