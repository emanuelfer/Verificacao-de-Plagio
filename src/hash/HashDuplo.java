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
public class HashDuplo <T>{
    private T[] keys;
    private T[] values;
    
    public HashDuplo(T[] keys, T[] values, int max){
        this.keys = keys;
        this.values = values;
    }
    
    public void insert(T key, T value){
        Integer i = 1, index;
        index = hash1(key)%this.keys.length;
        while(this.keys[index] != null){
            if(this.keys[index] == key)
                return;
            index = (hash1(key) + i*hash2(key))%this.keys.length;
            i++;
        }
        this.keys[index] = key;
        this.values[index] = value;
    }
    
    public T search(T key){
        Integer i = 1, index;
        index = hash1(key)%this.keys.length;
        while(this.keys[index] != null){
            if(this.keys[index].equals(key))
                return this.values[index];
            index = (hash1(key) + i*hash2(key))%this.keys.length;
            i++;
        }
        return null;
    }
    
    public Integer hash1(T key){
        return key.hashCode() + 37;
    }
    
    public Integer hash2(T key){
        if(key.hashCode() %2 == 0){
            return key.hashCode() + 31 + 1;
        }else{
            return key.hashCode() + 31;
        }
    }
    
    public boolean remove(T key){
        if(this.search(key) == null){
            return false;
        }else{
            Integer index = hash1(key)%this.keys.length, i= 1;
            while(!this.keys[index].equals(key)){
                index = (hash1(key) + i*hash2(key))%this.keys.length;
                i++;
            }
            System.out.println("Removendo: " + this.values[index]);
            this.keys[index] = this.values[index] = null;
            
            i = 1;
            for (index = (hash1(key) + i*hash2(key))%this.keys.length; this.keys[index] != null;
                i = (hash1(key) + i*hash2(key))%this.keys.length, i++) {
                T tmp1 = this.keys[index], tmp2 = this.values[index];
                this.keys[index] = this.values[index] = null;
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
