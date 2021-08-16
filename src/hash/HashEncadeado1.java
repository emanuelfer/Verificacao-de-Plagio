/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashSet;

/**
 *
 * @author emanu
 */
public class HashEncadeado1 <T, T1>{
    private int buckets;
    private T[] keys;
    private HashDuplo[] values;
    
    
    public <T1> HashEncadeado1(int b, T[] keys, T1[] values){
        this.buckets = b;
        this.keys = keys;
        this.values = new HashDuplo[this.buckets];
        for(int i = 0; i< this.buckets; i++){
            this.values[i] = new HashDuplo<>(Arrays.copyOf(values, this.buckets));
        }
        
        
    }
    
    public int hash(T x){
        int code;
        if(x.hashCode() < 0)
            code = -x.hashCode();
        else
            code = x.hashCode();
        return (code % this.buckets);
    }
    
    public void insert(T key, T value){
        int index = hash(key);
        while(this.keys[index] != null){
            if(this.keys[index].equals(key)){
                break;
            }
            index = (index + 1)%this.buckets;
        }
        this.keys[index] = key;
        //this.values[index].add(value);
        this.values[index].insert(value);
        //this.show();
    }
    
    public List<T1> findAll(T key){
        int index = hash(key);
        List<T1> retorno = new LinkedList<>();
        List<T1> list = this.values[index].getAll();
        for(T1 s : list){
            retorno.add(s);
        }
        
        return retorno;
    }
    
   /* public void show(){
        for(int i =0; i< this.buckets; i++){
            System.out.print(i + " ");
            List<T1> list = this.values[i].getAll();
            for(T1 s : list){
                System.out.print(" --> " + s);
                 //System.out.print(" --> " + value);
            }
            
            System.out.println("");
        }
    }*/
    
    
}
