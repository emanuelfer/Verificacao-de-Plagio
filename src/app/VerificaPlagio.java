/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import file.File;
import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import rbtree.RedBlackTree;
import avltree.AVL_Functions;
import hash.HashEncadeado1;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author emanu
 */
public class VerificaPlagio  {
    
    
     // n == 2
    public void carregaArquivoHash(String file, HashEncadeado1 he, int m){
        //String[] aux = File.read(file);
        String[] aux = File.leBuffered(file);
        int j;
        String frase = "";
        
        for(int i = 0; i <= (aux.length-m); i++){
            j=0;
            while(j < m){
                if(aux[i+j].isEmpty()){
                    i++;
                    continue;
                }

                frase += " " + aux[i+j];
                j++;
            }
            frase.toLowerCase();
            he.insert(frase, frase);
            frase = "";
        }       
    }
    
    public void carregaArquivoTree(String file, RedBlackTree rbTree, AVL_Functions avlTree, int m, int flag){
        //String[] aux = File.read(file);
        String[] aux = File.leBuffered(file);
        int j;
        String frase = "";
        
        for(int i = 0; i <= aux.length-m; i++){
            j=0;
            while(j < m){
                if(aux[i+j].isEmpty()){//|| aux[i+j].equals("null")){
                    i++;
                    continue;
                }

                frase += " " + aux[i+j];
                j++;
            }
            frase.toLowerCase();
            if (flag == 0 && avlTree != null){
                avlTree.insertValue(frase);
            }else if (flag == 1 && rbTree != null){
                rbTree.insert(frase);
            }
            frase = "";
        }   
    }
    
    public boolean verifcaByHash(String file, HashEncadeado1 he, int m){
        //String[] texto = File.read(file);
        String[] texto = File.leBuffered(file);        
        List<String> list;
        int testeJuncoes;
        int j = 0;
        String frase = "";
        if (texto != null){

            for(int i = 0; i <= texto.length-m; i++){
                j=0;
                testeJuncoes=0;
                frase = "";
                while(j < m){
                    if(texto[i+j].isEmpty()){//|| texto[i+j].equals("null")){
                        i++;
                        continue;
                    }

                    frase += " " + texto[i+j];
                    j++;
                }
                //System.out.println(frase);
                frase.toLowerCase();
                list = he.findAll(frase);
                if(!list.isEmpty()){
                    for(String string : list){
                        testeJuncoes++;
                        if(frase.equals(string)){
                            //System.out.println("Quant: "+testeJuncoes);
                            System.out.println("Chave: "+string);
                            return true;
                        }
                    }
                }

            }
        }
        
        return false;
    }
    
    public boolean verificaByTree(String file, RedBlackTree rbTree, AVL_Functions avlTree, int m, int flag){
        //String[] texto = File.read(file);
        String[] texto = File.leBuffered(file);
        /*for(String s : texto){
            System.out.println(s);
        }*/
        //System.out.println(texto.length);
        List<String> list;
        int testeJuncoes;
        int j = 0;
        String frase = "";
        System.out.println(texto[1]);
        if (texto != null){

            for(int i = 0; i <= texto.length-m; i++){
                j=0;
                testeJuncoes=0;
                frase = "";
                while(j < m){                    
                    if(texto[i+j].isEmpty()){
                        i++;
                        System.out.println("Sim");
                        continue;
                    }
                    System.out.println("I+J ="+(i+j));
                    System.out.println("Texto[i+j] = "+texto[i+j]);
                    frase += " " + texto[i+j];
                    
                    j++;
                }
                frase.toLowerCase();
                if (flag ==  0 && avlTree != null){
                    if (avlTree.search(frase)){
                        System.out.println("Frase:"+frase);
                        return true;
                    }
                }else if (flag == 1 && rbTree != null){
                    if(rbTree.search(frase)){
                        System.out.println("Frase:"+ frase);
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
