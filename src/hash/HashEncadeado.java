/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashSet;

/**
 *
 * @author emanu
 */
public class HashEncadeado{
    private int buckets;
    private String[] keys;
    private List<LinkedHashSet>[] values;
    
    
    public HashEncadeado(int b){
        this.buckets = b;
        this.keys = new String[this.buckets];
        this.values = new LinkedList[this.buckets];                
        for(int i = 0; i< this.buckets; i++){
            this.values[i] = new LinkedList<>();
            this.values[i].add(new LinkedHashSet<String>());
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
        //this.values[index].add(value);
        this.values[index].get(0).add(value);
        
    }
    
    public List<String> findAll(String key){
        int index = hash(key);
        List<String> list = new LinkedList<>();
        for(LinkedHashSet link : this.values[index]){
            Iterator it = link.iterator();
            while (it.hasNext()){
                list.add((String)it.next());
            }
        }
        
        return list;
    }
    
    public void show(){
        for(int i =0; i< this.buckets; i++){
            System.out.print(i + " ");
            
            for(LinkedHashSet link : this.values[i]){
                Iterator it = link.iterator();
                while (it.hasNext()){
                    System.out.print(" --> " + it.next());
                }
                //System.out.print(" --> " + value);
            }
            
            System.out.println("");
        }
    }
    
    
}
