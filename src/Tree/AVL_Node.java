/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *
 * @author Pedro
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
