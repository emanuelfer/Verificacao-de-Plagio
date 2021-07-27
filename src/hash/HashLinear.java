/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.util.Arrays;

/**
 *
 * @author emanu
 */
public class HashLinear <T>{
    private T[] keys;
    private T[] values;
    
    public HashLinear(T[] keys, T[] values, int max){
        this.keys = keys;
        this.values = values;
    }
    
    public void insert(T key, T value){
        Integer i;
        i = hash(key);
        while(this.keys[i] != null){
            i = (hash((T)i) + 1)%this.keys.length;
        }
        this.keys[i] = key;
        this.values[i] = value;
    }
    
    public T search(T key){
        Integer i;
        i = hash(key);
        while(this.keys[i] != null){
            if(this.keys[i].equals(key))
                return this.values[i];
            i = (hash((T)i) + 1)%this.keys.length;
        }
        return null;
    }
    
    public Integer hash(T key){
        return key.hashCode() % this.keys.length;
    }
    
    public boolean remove(T key){
        if(this.search(key) == null){
            return false;
        }else{
            Integer i = hash(key);
            while(!this.keys[i].equals(key)){
                i = (hash((T)i) + 1)%this.keys.length;
            }
            System.out.println("Removendo: " + this.values[i]);
            this.keys[i] = this.values[i] = null;
            
            for (i = (hash((T)i) + 1)%this.keys.length; this.keys[i] != null;
                i = (hash((T)i) + 1)%this.keys.length) {
                T tmp1 = this.keys[i], tmp2 = this.values[i];
                this.keys[i] = this.values[i] = null;
                insert(tmp1, tmp2);
           }
            return true;
        }
    }
    
    public void show(){
        for(int i = 0; i< this.keys.length ; i++){
            if(this.keys[i] != null)
                System.out.println(this.keys[i] + " - " + this.values[i]);
        }
    }
}
