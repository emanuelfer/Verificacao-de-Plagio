package hash;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashSet;

/*
 * Autores: Emanuel Lindoso Ferreira e Pedro Víctor de Abreu Fonseca
 * Data de modificação: 18/08/2021 por Pedro Víctor de Abreu Fonseca
        -> Adesão de comentários.
 * Classe: HashEncadeado1
 * O que faz: Implementação de funções relacionadas a estrutura Hash Encadeado .
 * Revisado em: ...
 */
public class HashEncadeado1 <T, T1>{
    private int buckets;
    private T[] keys;
    private HashDuplo[] values;
    private int cont;
    /*
       * Função: HashEncadeado1(int b, T[] keys, T1[] values)
       * Descrição: Inicializa a estrutura.
       * Utilização: main(String []args)
       * Parâmetros:            
            b: Tamanho do hash.
            keys: Tipo de valor que será as chaves
            values: Valores que serão associados a uma chave. 
       * Retorno: String de tamanho N.
     */
    public <T1> HashEncadeado1(int b, T[] keys, T1[] values){
        this.buckets = b;
        this.keys = keys;
        this.values = new HashDuplo[this.buckets];
        for(int i = 0; i< this.buckets; i++){
            this.values[i] = new HashDuplo<>(Arrays.copyOf(values, this.buckets)); //Para cada Key será gerada um HashDuplo evitando valores repetidos e colisões.
        }
        this.cont=0;
    }
    
    /*
       * Função: hash(T x)
       * Descrição: Calcula um valor de hash com base em uma key.
       * Utilização: HashEncadeado1
       * Parâmetros:            
            x: valor que será calculado um hashCode             
       * Retorno: Inteiro positivo com um código hash.
    */
    public int hash(T x){
        int code;
        return (Math.abs(x.hashCode()) % this.buckets);
    }
    
    /*
       * Função: insert(T key, T value)
       * Descrição: Insere um valor na chave 'key' com valor 'value'
       * Utilização: main(String []args)
       * Parâmetros:            
            Key: chave do hash.
            value: Valor que será inserido. 
       * Retorno: Nenhum.
    */
    public void insert(T key, T value){
        int index = hash(key); //Calcula index
        while(this.keys[index] != null){
            if(this.keys[index].equals(key)){
                break;
            }
            index = (index + 1)%this.buckets; //Se aquele index estiver ocupado tenta achar um disponível.
            /*if (this.cont == this.keys.length){
                return;
            }*/
        }                
        this.keys[index] = key; //Seta chave        
        this.values[index].insert(key, value); //Chama a função de insert do HashDuplo daquela posição.        
        //this.cont++;
    }
    
    /*
       * Função: findAll(T key)
       * Descrição: Retorna os valores do HashDuplo contida em uma chave 'key' passada como parâmetro.
       * Utilização: main(String []args)
       * Parâmetros:            
            Key: Chave que será procurada no hash.             
       * Retorno: Lista com os valores.
    */
    public List<T1> findAll(T key){
        int index = hash(key), cont =1;
        while(this.keys[index] != null){
            if(this.keys[index].equals(key)){ //Encontrou a chave procurada
                break;
            }
            index = (index + 1)%this.buckets; //Senão continua procurando
            if(cont == this.buckets)
                return null;
        }
        if(this.keys[index] == null )
            return null;
        List<T1> retorno = new LinkedList<>();
        List<T1> list = this.values[index].getAll(this.keys[index]); //Pega o hash com aquela chave
        for(T1 s : list){
            retorno.add(s);
        }
        
        return retorno;
    }
    
    /*
       * Função: show()
       * Descrição: Mostra todas as chaves com seus respectivos valores.
       * Utilização: main(String []args)
       * Parâmetros:                       
       * Retorno: Nenhum.
    */
   public void show(){
        for(int i =0; i< this.buckets; i++){
            System.out.print(i + " ");
            List<T1> list = this.values[i].getAll(this.keys[i]);
            for(T1 s : list){
                System.out.print(" --> " + s);
                
                 //System.out.print(" --> " + value);
            }
            
            System.out.println("");
        }
    }
    
    
}