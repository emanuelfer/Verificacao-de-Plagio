
package avltree;

import java.util.ArrayList;

/*
 * Autores: Pedro Víctor de Abreu Fonseca
 * Data de modificação: 19/08/2021 por Pedro Víctor de Abreu Fonseca
        -> Adesão de comentários.
 * Classe: AVL_Functions
 * O que faz: Implementação de funções básicas relacionadas a estrutura de árvore AVL.
 * Revisado em: ...
 */

public class AVL_Functions {
    
    protected AVL_Node raiz;
    
    /*
       * Função: insertValue(String value)
       * Descrição: Cria um nó e chama a função final de inserção.
       * Utilização: main(String []args)
       * Parâmetros:            
            value: String que será inserida.
       * Retorno: Nenhum.
     */
    public void insertValue(String value){
        AVL_Node no = new AVL_Node(value); //Cria nó
        insertAVL(this.raiz, no); //Chama a função que insere
    }
    
    /*
       * Função: insertAVL(AVL_Node noCompara, AVL_Node noInsere)
       * Descrição: Insere o nó na árvore AVL.
       * Utilização: AVL_Functions
       * Parâmetros:            
            noCompara: Inicialmente é a raiz da árvore. Mas conforme o processo recursivo vai sendo feito o valor irá mudar.
            noInsere: Nó que será inserido na árvore.
       * Retorno: Nenhum.
     */
    public void insertAVL(AVL_Node noCompara, AVL_Node noInsere){
        if (noCompara == null){
            this.raiz = noInsere;
        }else{            
            if (noInsere.getKey().compareTo(noCompara.getKey()) < 0){ //Inserido a esquerda
                if (noCompara.getEsquerda() == null){
                    noCompara.setEsquerda(noInsere);
                    noInsere.setPai(noCompara);
                    verificarBalanceamento(noCompara);
                }else{
                    insertAVL(noCompara.getEsquerda(), noInsere);
                }
                
            }else if (noInsere.getKey().compareTo(noCompara.getKey()) > 0){ //Mesmo processo só que direita
                if (noCompara.getDireita() == null){
                    noCompara.setDireita(noInsere);
                    noInsere.setPai(noCompara);
                    verificarBalanceamento(noCompara);
                }else{
                    insertAVL(noCompara.getDireita(), noInsere);
                }
            }// Nó já existe
        }
        
    }
    
    /*
       * Função: CalculaAltura (AVL_Node no)
       * Descrição: Calcula a altura de uma árvore a partir de um determinado nó
       * Utilização: AVL_Functions
       * Parâmetros:                       
            no: Calcular a altura da árvore a partir deste nó.
       * Retorno: Valor inteiro representando a altura da árvore.
     */
    private int CalculaAltura (AVL_Node no){
        if (no == null){
            return -1;
        }
        
        if (no.getEsquerda() == null && no.getDireita()==null){
            return 0;
        }else if(no.getEsquerda() == null){ //tem direita
            return 1+ CalculaAltura(no.getDireita());
        }else if (no.getDireita() == null){ // ir para a esquerda
            return 1+CalculaAltura(no.getEsquerda());
        }else{ //Se tem ambos
            return 1 + Math.max(CalculaAltura(no.getEsquerda()), CalculaAltura(no.getDireita()));
        }
    }
    
    /*
       * Função: setBalance (AVL_Node no)
       * Descrição: Atribui um fator de balanceamento para um determinado nó.
       * Utilização: AVL_Functions
       * Parâmetros:                       
            no: Nó que terá seu fator atribuido.
       * Retorno: Nenhum.
     */
    private void setBalance (AVL_Node no){
        no.setBalanceFactor(CalculaAltura(no.getDireita()) - CalculaAltura(no.getEsquerda()) );
    }
    
    /*
       * Função: rotacaoEsquerda (AVL_Node no)
       * Descrição: Faz o processo de rotação a esquerda em um determinado nó
       * Utilização: AVL_Functions
       * Parâmetros:                       
            no: Nó que sofrerá rotação.
       * Retorno: Nenhum.
     */
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
    
    /*
       * Função: rotacaoDireita (AVL_Node no)
       * Descrição: Faz o processo de rotação a direita em um determinado nó
       * Utilização: AVL_Functions
       * Parâmetros:                       
            no: Nó que sofrerá rotação.
       * Retorno: Nenhum.
     */
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
    
    /*
       * Função: duplaRotacaoEsquerdaDireita(AVL_Node no)
       * Descrição: Faz o processo de rotação dupla em um determinado nó
       * Utilização: AVL_Functions
       * Parâmetros:                       
            no: Nó que sofrerá rotação.
       * Retorno: Nenhum.
     */
    public AVL_Node duplaRotacaoEsquerdaDireita(AVL_Node no){
        no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
        return rotacaoDireita(no);
    }
    
    /*
       * Função: duplaRotacaoDireitaEsquerda(AVL_Node no)
       * Descrição: Faz o processo de rotação dupla em um determinado nó
       * Utilização: AVL_Functions
       * Parâmetros:                       
            no: Nó que sofrerá rotação.
       * Retorno: Nenhum.
     */    
    public AVL_Node duplaRotacaoDireitaEsquerda(AVL_Node no){
        no.setDireita(rotacaoDireita(no.getDireita()));
        return rotacaoEsquerda(no);
    }
    
     /*
       * Função: verificarBalanceamento(AVL_Node atual)
       * Descrição: Verifica o balanceamento de um determinano nó e realiza as operações de rotação se necessário.
       * Utilização: AVL_Functions
       * Parâmetros:                       
            atual: Nó que será verificado.
       * Retorno: Nenhum.
     */     
    public void verificarBalanceamento(AVL_Node atual) {
		setBalance(atual);
		int balanceamento = atual.getBalance();

		if (balanceamento == -2) {

			if (CalculaAltura(atual.getEsquerda().getEsquerda()) >= CalculaAltura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (CalculaAltura(atual.getDireita().getDireita()) >= CalculaAltura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}
    
    /*
       * Função: search(String data)
       * Descrição: Procura um nó com o valor 'data' na árvore
       * Utilização: main(String[] args)
       * Parâmetros:                       
            data: Valor que se quer procurar.
       * Retorno: Nenhum.
     */            
    public boolean search(String data){
        AVL_Node temp = raiz;
        
        while(temp != null){
            if(data.compareTo(temp.getKey()) < 0){
                if(temp.getEsquerda() == null)
                    break;
                else
                    temp = temp.getEsquerda();
            }else if(data.equals(temp.getKey())){
                //System.out.println(data + " - " + temp.getKey());
                break;
            }else{
                if(temp.getDireita() == null)
                    break;
                else
                    temp = temp.getDireita();
            }
        }
        

        if(data.equals(temp.getKey()))
            return true;    
        return false;
    }
}
