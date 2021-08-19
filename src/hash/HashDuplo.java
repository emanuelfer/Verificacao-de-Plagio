package hash;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Autor: Emanuel Lindoso Ferreira
 * Data de modificação: 18/08/2021 por Pedro Víctor de Abreu Fonseca
        -> Adesão de comentários.
 * Classe: HashDuplo
 * O que faz: Implementações de métodos relacionados ao HashDuplo.
 * Revisado em: ...
 */

public class HashDuplo <T>{
    private T[] keys;
    private int size = 0;
    
    public HashDuplo(T[] keys){
        this.keys = keys;
    }
    
    /*
       * Função: insert(T key, T value) 
       * Descrição: Insere um valor 'value' em uma chave 'key' no hash
       * Utilização: HashEncadeado1
       * Parâmetros:            
            key: Chave que será utilizada para armazenar um valor.
            value: Valor que será armazenado.
       * Retorno: Nenhum.
    */
    public void insert(T key, T value){
        Integer i = 1, index, cont = 0;
        if (key != null){
            index = hash1(key)%this.keys.length;
            while(this.keys[index] != null){

                if(this.keys[index].equals(value)){
                    //System.out.println("sim");
                    return;

                }
                index = (hash1(key) + i*hash2(key))%this.keys.length;
                index = Math.abs(index);
                i++;
                cont++;
                if(cont == this.keys.length)
                    return;
            }
            this.size++;        
            this.keys[index] = value;
        }
    }
    
    
    public Integer hash1(T key){
        int code = Math.abs(key.hashCode()) + 37;                    
        return code;
    }
    
    public Integer hash2(T key){
        int code = 0;
        if(key.hashCode() %2 == 0){
            code = Math.abs(key.hashCode())+ 31 + 1;
        }else{
            code = Math.abs(key.hashCode()) + 31;
        }
        return code;
    }
    
    /*
       * Função: getAll(T key) 
       * Descrição: Retorna os valores contidos numa chave 'key'
       * Utilização: HashEncadeado1
       * Parâmetros:            
            key: Chave que será utilizada como parâmetro de busca;            
       * Retorno: Lista contendo os valores naquela chave.
    */
    
    public List<T> getAll(T key){
        int i=1, index, cont = 0;
        List<T> list = new LinkedList<>(); 
        if (key != null){
            index = hash1(key)%this.keys.length;
            while(this.keys[index] != null){
                list.add(this.keys[index]);
                index = (hash1(key) + i*hash2(key))%this.keys.length;
                index = Math.abs(index);
                i++;
                cont++;    
                if(cont == this.size)
                    return list;
            }
        }
        return list;
    }    
}