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
public class HashEncadeado {
    private int buckets;
    private String[] keys;
    private List<Integer>[] values;
    
    public HashEncadeado(int b){
        this.buckets = b;
        this.keys = new String[this.buckets];
        this.values = new LinkedList[this.buckets];
        for(int i = 0; i< this.buckets; i++){
            this.values[i] = new LinkedList<>();
        }
        
    }
    
    public int hash(String x){
        return (x.hashCode() % this.buckets);
    }
    
    public void insert(String key, Integer value){
        int index = hash(key);
        
        while(this.keys[index] != null){
            if(this.keys[index].equals(key))
                break;
            index = (index + 1)%this.buckets;
        }
        this.keys[index] = key;
        this.values[index].add(value);
    }
    
    public List<Integer> findAll(String key){
        int index = hash(key);
        List<Integer> list = new LinkedList<>();
        for(Integer i : this.values[index]){
            list.add(i);
        }
        return list;
    }
    
    public void show(){
        for(int i =0; i< this.buckets; i++){
            System.out.print(i + " ");
            for(Integer value : this.values[i]){
                System.out.print(" --> " + value);
            }
            System.out.println("");
        }
    }
    
    
}
