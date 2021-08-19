package app;
import file.File;
import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import rbtree.RedBlackTree;
import avltree.AVL_Functions;
import hash.HashEncadeado1;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/*
 * Autores: Emanuel Lindoso Ferreira e Pedro Víctor de Abreu Fonseca
 * Data de modificação: 18/08/2021 por Pedro Víctor de Abreu Fonseca
        -> Adesão de comentários e mudanças na leitura de arquivos.
 * Classe: VerificaPlagio
 * O que faz: Apresenta funções de carregamento de arquivos em estruturas diversas além da checagem de plágio.
 * Revisado em: ...
 */
public class VerificaPlagio  {
    
    /*
       * Função: carregaArquivoHash(String file, HashEncadeado1 he, int m)
       * Descrição: Carrega strings de um arquivo 'file' em um Hash encadedado 'he'.
       * Utilização: main(String []args)
       * Parâmetros:            
            file: Arquivo que será lido. Neste caso o arquivo que se quer verifica se existe plágio.
            he: Estrutura Hash Encadeado que guardará frases de tamanho N vindas do arquivo 'file'
            m: Inteiro que dirá o tamanho de cada cadeia de caracteres que será salva na estrutura. 
       * Retorno: Nenhum.
     */
    
    public void carregaArquivoHash(String file, HashEncadeado1 he, int m){       
        String[] aux = File.leBuffered(file); //Leitura do arquivo
        int j;
        String frase = "";
        
        for(int i = 0; i <= (aux.length-m); i++){  //O conteúdo de Aux são palavras do arquivo 'file'
            j=0;
            while(j < m){  
                    if (i+j > aux.length-1){
                        break;
                    }
                    if(aux[i+j].isEmpty()){
                        i++;
                        continue;
                    }

                    frase += " " + aux[i+j]; //Dividindo as Strings vindas do arquivo em tamanho M e concatenando.
                    j++;                
            }
            frase.toLowerCase(); //Coloca para minúscula
            he.insert(frase, frase); //Insere na estrutura a chave e o valor são iguais a frase construída.
            frase = "";
        }       
    }
    
    /*
       * Função: carregaArquivoTree(String file, RedBlackTree rbTree, AVL_Functions avlTree, int m, int flag)
       * Descrição: Carrega o arquivo em uma árvore de acordo com o valor do parâmetro 'flag'
            -> Se flag == 0: Árvore AVL é utilizada.
            -> Se flag == 1: Árvore RB é utilizada.
       * Utilização: main(String []args)
       * Parâmetros:            
            file: Arquivo que será lido. Neste caso o arquivo que se quer verificar se existe plágio.
            rbTree: Representa uma árvore rubro negra. Se seu valor for null indica que o que será utilizado será AVL.
            avlTree: Representa um árvore AVL. Se seu valor for null indica que o que será utilizado será RB.
            m: Tamanho de cada cadeia de caracteres do arquivo que serão salvos na estrutura selecionada.
            flag: Valor que indicará qual estrutura será utilizada
       * Retorno: Nenhum.
     */
    public void carregaArquivoTree(String file, RedBlackTree rbTree, AVL_Functions avlTree, int m, int flag){        
        //Processo é parecido com a função de carregar no hash. Só muda a estrutura de inserção
        String[] aux = File.leBuffered(file);        
        int j;
        String frase = "";
        
        for(int i = 0; i <= aux.length-m; i++){
            j=0;
            while(j < m){
                if (i+j > aux.length-1){
                    break;
                }
                if(aux[i+j].isEmpty()){
                    i++;
                    continue;
                }

                frase += " " + aux[i+j];
                j++;
            }
            frase.toLowerCase();
            if (flag == 0 && avlTree != null){
                avlTree.insertValue(frase);
            }else if (flag == 1 && rbTree != null){
                rbTree.insert(frase);
            }
            frase = "";
        }   
    }
    
    
    /*
       * Função: verifcaByHash(String[] texto, HashEncadeado1 he, int m)
       * Descrição: Verifica se existe uma frase presente no hash previamente preenchido.
            -> Se a frase existir == Plágio.
            -> Se não existir == Não é plágio.
       * Utilização: main(String []args)
       * Parâmetros:            
            texto: Vetor contendo palavras de um arquivo lido da Base de dados. 
            he: Estrutura previamente preenchida no método carregaArquivoHash():    
            m: Tamanho de cada frase que se deseja verificar se ocorre plágio
       * Retorno: Booleano que indica se existe plágio ou não.
     */
    public boolean verifcaByHash(String[] texto, HashEncadeado1 he, int m){        
        List<String> list; //Irá guardar frases que foram iguais.
        int testeJuncoes;
        int j = 0;
        String frase = "";
        if (texto != null){

            for(int i = 0; i <= texto.length-m; i++){ 
                j=0;
                testeJuncoes=0;
                frase = "";
                while(j < m){ //J vai até o tamanho desejado pelo usuário para verificação de plágio.
                    if (i+j > texto.length-1){
                        break;
                    }
                    if(texto[i+j].isEmpty()){
                        i++;
                        continue;
                    }

                    frase += " " + texto[i+j]; //Mesmo processo de concatenação visto nas funções anteriores.
                    j++;
                }                
                frase.toLowerCase();
                list = he.findAll(frase); //Retorno de uma lista contendo a frase que é igual a uma chave do hash. 
                if (list != null){
                    if(!list.isEmpty()){
                        for(String string : list){ //Realiza a procura por um valor dentro daquela chave que é igual a frase.
                            testeJuncoes++;
                            if(frase.equals(string)){                                
                                return true; //Encontrou. Então é plágio
                            }
                        }
                    }
                }

            }
        }
        
        return false;
    }
    
    /*
       * Função: verificaByTree(String[] texto, RedBlackTree rbTree, AVL_Functions avlTree, int m, int flag)
       * Descrição: Verifica se há plágio utilizando um árvore como estrutura de dados.
       * Utilização: main(String []args)
       * Parâmetros:            
            texto: Vetor de palavras de um arquivo da Base de Dados.
        rbTree: Estrutura de um árvore RB.
        avlTree: Estrutura de uma árvore AVL
        int m: Tamanho da cadeia de caracteres que serão analisadas.
        flag: Valor inteiro que indicará qual estrutura será utilizada.
            -> 0: AVL.
            -> 1: RB.
       * Retorno: Valor booleano que dirá se teve plágio ou não.
     */
    public boolean verificaByTree(String[] texto, RedBlackTree rbTree, AVL_Functions avlTree, int m, int flag){        
        List<String> list;
        int testeJuncoes;
        int j = 0;
        String frase = "";
        if (texto != null){

            for(int i = 0; i <= texto.length-m; i++){
                j=0;
                testeJuncoes=0;
                frase = "";
                while(j < m){  //J vai até o tamanho desejado pelo usuário para verificação de plágio.
                    if (i+j > texto.length-1){
                        break;
                    }
                    if(texto[i+j].isEmpty()){
                        i++;
                        continue;
                    }

                    frase += " " + texto[i+j]; //Mesmo processo de concatenação atuando sobre o vetor de palavras contidas de um arquivo na base de dados.
                    j++;
                }
                frase.toLowerCase();
                if (flag ==  0 && avlTree != null){ 
                    if (avlTree.search(frase)){     //Realiza busca da frase na árvore AVL.                   
                        return true;
                    }
                }else if (flag == 1 && rbTree != null){
                    if(rbTree.search(frase)){      //Realiza busca da frase na árvore RB.                  
                        return true;

                    }
                }
            }
        }
        
        return false;
    }        
}