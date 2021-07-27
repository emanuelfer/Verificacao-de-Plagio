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
//        Integer[] keys = new Integer[10];
//        String[] values = new String[10];
//        HashDuplo hashLinear = new HashDuplo(keys, values, 10);   
//        
//        hashLinear.insert(10, "Emanuel");
//        hashLinear.insert(125, "Beatriz");
//        hashLinear.insert(14, "Serejo");
//        hashLinear.insert(25, "Lucas");
//        hashLinear.insert(50, "Pedro");
//        hashLinear.insert(50, "Victor");
//        hashLinear.insert(7, "Isabel");
//        hashLinear.show();    

        String origem = new String();
        origem = "Um dois três três quatro cinco seis sete oito nove dez";
        String[] aux = origem.split(" ");
        ArrayList<Integer> pos = new ArrayList<>();//posicoes de d
        List<Integer> list = null;
        boolean flag = true;
        HashEncadeado he = new HashEncadeado(7);
        int cont = 0;

        //arquivo d
        he.insert("um", 0);
        he.insert("Um", 54);
        he.insert("dois", 55);
        he.insert("três", 56);
//        he.insert("", 27);
//        he.insert("", 22);
//        he.insert("", 15);

        //he.show();
        
        for(String s : aux){
            if(cont < 3)
                cont++;
            else
                break;
            //lista das posições
            list = he.findAll(s);
            System.out.println(s);
            System.out.println(list.size());
            if(!list.isEmpty()){
                for(Integer i : list){
                   //posições já inseridas
                   if(!pos.contains(i)){
                       pos.add(i);
                       break;
                   }else{
                       
                   }
                }
            }else{
                flag = false;
                break;
            }
        }
        
        
        if(!flag){
            System.out.println("não é plágio");
        }else{
            for(int i = 1; i < pos.size(); i++){
                if(pos.get(i) - pos.get(i-1) != 1){
                    flag = false;
                }
            }
            System.out.println("É plágio? " + flag);           
        }

          

    }
}
