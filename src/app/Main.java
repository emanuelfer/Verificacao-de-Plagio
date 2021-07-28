package app;

import hash.HashDuplo;
import hash.HashEncadeado;
import hash.HashLinear;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emanu
 */
public class Main {
    
    public static void main(String[] args) {
        String origem = new String();
        origem = "um dois três quatro cinco seis sete oito nove dez";
        String aux[] = {"um dois três", "dois três qautro", "três quatro cinco"};
        List<String> list = null;
        boolean flag = false;
        HashEncadeado he = new HashEncadeado(7);
        int cont = 0;

        //arquivo d
        he.insert("um dois quatro", "um dois quatro");
        he.insert("três qutro cinco", "três quatro cinco");
        he.insert("dois nove", "dois nove");
        he.insert("quatro dez", "quatro dez");
        he.show();
        

        
        for(String s : aux){
            list = he.findAll(s);
            if(list.isEmpty()){
                flag = false;
                break;
            }else{
                for(String i : list){
                    if(s.equals(i)){
                        flag = true;
                        break;
                    }
                }
            }
            
            if(flag)
                break;
             
        }

        System.out.println(flag);
          

    }
}
