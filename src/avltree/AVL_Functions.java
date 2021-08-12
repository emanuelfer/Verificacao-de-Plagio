/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

import java.util.ArrayList;

/**
 *
 * @author Pedro
 */


/*

    TODO: IMPLEMENTAR CLASSE TREE_AVL e testar.

*/
public class AVL_Functions {
    
    protected AVL_Node raiz;
    
    public void insertValue(String value){
        AVL_Node no = new AVL_Node(value);
        insertAVL(this.raiz, no);
    }
    
    public void insertAVL(AVL_Node noCompara, AVL_Node noInsere){
        if (noCompara == null){
            this.raiz = noInsere;
        }else{
            //Convertendo a string em valor ASCII
            int noComparaASCII = 0;
            int noInsereASCII = 0;
            for (int i=0; i<noCompara.getKey().length(); i++){
                noComparaASCII += noComparaASCII+noCompara.getKey().charAt(i);
            }
            for (int j=0; j<noInsere.getKey().length(); j++){
                noInsereASCII += noInsereASCII+noInsere.getKey().charAt(j);
            }
            //Fim cast
            if (noInsereASCII < noComparaASCII){ //Inserido a esquerda
                if (noCompara.getEsquerda() == null){
                    noCompara.setEsquerda(noInsere);
                    noInsere.setPai(noCompara);
                    verificaBalance(noCompara);
                }else{
                    insertAVL(noCompara.getEsquerda(), noInsere);
                }
                
            }else if (noInsereASCII > noComparaASCII){ //Mesmo processo só que direita
                if (noCompara.getDireita() == null){
                    noCompara.setDireita(noInsere);
                    noInsere.setPai(noCompara);
                    verificaBalance(noCompara);
                }else{
                    insertAVL(noCompara.getDireita(), noInsere);
                }
            }else{ //No já existe
                return;
            }
        }
        
    }
    
    
    private int CalculaAltura (AVL_Node no){
        if (no == null){
            return -1;
        }
        
        if (no.getEsquerda() == null && no.getDireita()==null){
            return 0;
        }else if(no.getEsquerda() == null){ //tem direita
            return 1+ CalculaAltura(no.getDireita());
        }else if (no.getDireita() == null){
            return 1+CalculaAltura(no.getEsquerda());
        }else{
            return 1 + Math.max(CalculaAltura(no.getEsquerda()), CalculaAltura(no.getDireita()));
        }
    }
    
    private void setBalance (AVL_Node no){
        no.setBalanceFactor(CalculaAltura(no.getDireita()) - CalculaAltura(no.getEsquerda()) );
    }
    
    public AVL_Node rotacaoEsquerda (AVL_Node no){
        AVL_Node direita = no.getDireita();
        direita.setPai(no.getPai());
        no.setDireita(direita.getEsquerda());
        if (no.getDireita()!=null){
            no.getDireita().setPai(no);
        }
        direita.setEsquerda(no);
        no.setPai(direita);
        if (direita.getPai()!=null){
            if (direita.getPai().getDireita() == no){
                direita.getPai().setDireita(direita);
            }else if (direita.getPai().getEsquerda() == no){
                direita.getPai().setEsquerda(direita);
            }
        }
        setBalance(no);
        setBalance(direita);
        return direita;
    }
    
    public AVL_Node rotacaoDireita(AVL_Node no){
        AVL_Node esquerda = no.getEsquerda();
        esquerda.setPai(no.getPai());
        no.setEsquerda(esquerda.getDireita());
        if (no.getEsquerda()!= null){
            no.getEsquerda().setPai(no);
        }
        esquerda.setDireita(no);
        no.setPai(esquerda);
        if (esquerda.getPai() != null){
            if (esquerda.getPai().getDireita() == no){
                esquerda.getPai().setDireita(esquerda);
            }else if (esquerda.getPai().getEsquerda() == no){
                esquerda.getPai().setEsquerda(esquerda);
            }
        }
        
        setBalance(no);
        setBalance(esquerda);
        return esquerda;
    }
    
    public AVL_Node duplaRotacaoEsquerdaDireita(AVL_Node no){
        no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
        return rotacaoDireita(no);
    }
    
    public AVL_Node duplaRotacaoDireitaEsquerda(AVL_Node no){
        no.setDireita(rotacaoDireita(no.getDireita()));
        return rotacaoEsquerda(no);
    }
    
    public void verificaBalance(AVL_Node no){
        setBalance(no);
        int balace = no.getBalance();
        int alturaEsquerdaEsquerda = CalculaAltura(no.getEsquerda().getEsquerda());
        int alturaEsquerdaDireita = CalculaAltura(no.getEsquerda().getDireita());
        int alturaDireitaEsquerda = CalculaAltura(no.getDireita().getEsquerda());
        int alturaDireitaDireita = CalculaAltura(no.getDireita().getDireita());
        
        if (balace == -2){
            if (alturaEsquerdaEsquerda >= alturaEsquerdaDireita){
                no = rotacaoDireita(no);
            }else{
                no = duplaRotacaoEsquerdaDireita(no);
            }
        }else if (balace == 2){
            if (alturaDireitaDireita >= alturaDireitaEsquerda){
                no = rotacaoEsquerda(no);
            }else{
                no = duplaRotacaoDireitaEsquerda(no);
            }
        }
        
        if (no.getPai() != null){
            verificaBalance(no.getPai());
        }else{
            this.raiz = no;
        }
    }
    
    
    // << -- TESTAR -- >>
    final protected ArrayList<AVL_Node> showInorder(){
        ArrayList<AVL_Node> retornoLista = new ArrayList<AVL_Node>();
        return retornoLista;
    }
    
    final protected void inorder(AVL_Node no, ArrayList<AVL_Node> lista){
        if (no == null){
            return;
        }
        inorder(no.getEsquerda(), lista);
        lista.add(no);
        inorder(no.getDireita(), lista);
    }
}
