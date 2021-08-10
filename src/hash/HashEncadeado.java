/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author emanu
 */
public class HashEncadeado  {
    private int buckets;
    private String[] keys;
    private List<String>[] values;
    
    public HashEncadeado(int b){
        this.buckets = b;
        this.keys = new String[this.buckets];
        this.values = new LinkedList[this.buckets];
        for(int i = 0; i< this.buckets; i++){
            this.values[i] = new LinkedList<>();
        }
        
    }
    
    public int hash(String x){
        int code;
        if(x.hashCode() < 0)
            code = -x.hashCode();
        else
            code = x.hashCode();
        return (code % this.buckets);
    }
    
    public void insert(String key, String value){
        int index = hash(key);
//        while(this.keys[index] != null){
//            if(this.keys[index].equals(key)){
//                System.out.println(index);
//                break;
//            }
//            index = (index + 1)%this.buckets;
//            System.out.println(index);
//        }
        this.keys[index] = key;
        this.values[index].add(value);
    }
    
    public List<String> findAll(String key){
        int index = hash(key);
        List<String> list = new LinkedList<>();
        for(String i : this.values[index]){
            list.add(i);
        }
        return list;
    }
    
    public void show(){
        for(int i =0; i< this.buckets; i++){
            System.out.print(i + " ");
            for(String value : this.values[i]){
                System.out.print(" --> " + value);
            }
            System.out.println("");
        }
    }
    
    
}
