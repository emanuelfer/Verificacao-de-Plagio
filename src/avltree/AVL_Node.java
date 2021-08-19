
package avltree;

/*
 * Autores: Pedro Víctor de Abreu Fonseca
 * Data de modificação: 19/08/2021 por Pedro Víctor de Abreu Fonseca
        -> Adesão de comentários.
 * Classe: AVL_Node
 * O que faz: Define a estrutura de um nó de uma árvore AVL.
 * Revisado em: ...
 */
public class AVL_Node {
    private AVL_Node noEsquerda;
    private AVL_Node noDireita;
    private AVL_Node noPai;
    private String key;
    private int balance_Factor;
    
    public AVL_Node(String chave){
        setEsquerda(setDireita(setPai(null))); //Iniciou com tudo null
        setBalanceFactor(0);
        setKey(chave);
        
    }
    
    public String getKey(){
        return key;
    }
    
    public int getBalance(){
        return balance_Factor;
    }
    
    public void setKey(String newKey){
        this.key = newKey;
    }
    
    public void setBalanceFactor(int newBalance){
        this.balance_Factor = newBalance;
    }
    
    public AVL_Node getPai(){
        return noPai;
    }
    
    public AVL_Node setPai(AVL_Node newPai){
        this.noPai = newPai;
        return noPai;
    }
    
    public AVL_Node getDireita(){
        return noDireita;
    }
    
    public AVL_Node setDireita(AVL_Node newDireita){
        this.noDireita = newDireita;
        return noDireita;
    }
    
    public AVL_Node getEsquerda(){
        return noEsquerda;
    }
    
    public void setEsquerda(AVL_Node newEsquerda){
        this.noEsquerda = newEsquerda;
    }
    
}
