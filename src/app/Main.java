/*
    * Programa feito para o complemento da segunda nota da disciplina de Estrutura de Dados II
    * Feito por: Emanuel Lindoso Ferreira e Pedro Víctor de Abreu Fonseca.
    * Objetivo geral: Uso de tabelas Hash e Árvores AVL e Rubro-Negra.
*/

package app;
import Objects.Pessoa;
import file.File;
import hash.HashDuplo;
import hash.HashEncadeado;
import hash.HashLinear;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import rbtree.RedBlackTree;
import avltree.AVL_Functions;
import hash.HashEncadeado1;
import java.util.Scanner;

/*
 * Autores: Emanuel Lindoso Ferreira e Pedro Víctor de Abreu Fonseca
 * Data de modificação: 16/08/2021 por Pedro Víctor de Abreu Fonseca
        -> Adesão de comentários e criação de menus.
 * Classe: Main
 * O que faz: Apresentação e criação de dados; Uso de Hashs e Árvores.
 * Revisado em: ...
 */

public class Main {

    private static Scanner leitor = new Scanner(System.in);
    private static final String alfabeto = "ABCDEFGHIJKLMNOPRSTUVXWYZ";

    protected static int leInteiro() {
        int x = leitor.nextInt();
        leitor.nextLine();
        return x;
    }

    public static void main(String[] args) {
        Random gerador = new Random();
        int esc = 1, tamHash = 0;
        while (esc > 0) {
            System.out.println("1 - Primeira Questão\n2 - Questão Plágio\n");
            esc = leInteiro();
            switch (esc) {
                case 1:                    
                    System.out.println("Tamanho hash");  //Modificar Depois
                    tamHash = leInteiro();
                    HashEncadeado1 hashIntString = new HashEncadeado1<Integer, String>(tamHash, new Integer[tamHash], new String[tamHash]);
                    HashEncadeado1 hashIntDouble = new HashEncadeado1<Integer, Double>(tamHash, new Integer[tamHash], new Double[tamHash]);
                    Pessoa[] p = new Pessoa[tamHash];
                    HashEncadeado1 hashIntObject = new HashEncadeado1<Integer, Pessoa>(tamHash, new Integer[tamHash], p);
                    int i = 0;                    
                    Pessoa pessoa = new Pessoa();
                    Long timeBefore = System.currentTimeMillis();
                    for (i = 0; i < tamHash / 2; i++) { // Se tem 1000 posições do hash, 500 elementos serão inseridos.
                        //hashIntString.insert(i, geraString(5));
                        hashIntDouble.insert(i, gerador.nextDouble());
                        //hashIntObject.insert(i, pessoa.generateRandomPessoa());
                    }
                    Long timeAfter = System.currentTimeMillis();                    
                    System.out.println("Tempo Médio da operação de inserção em todos os Hashs: " + (timeAfter - timeBefore) / 3);

                    // -------------------- FIND ALL -----------------------------
                    //System.out.println("Busca em uma chave aleatória do hashIntObject:");
                    System.out.println("Mostrando chave 0 do hashIntObject");
                    if (hashIntDouble != null) {
                        int chave = 0;//gerador.nextInt(tamHash-1);
                        System.out.print("Chave: " + chave + " ->");
                        System.out.println(hashIntDouble.findAll(chave));
                    } else {
                        System.out.println("\nHash não inicializado");
                    }

                    break;
                case 2:
                    int escSegunda=1;
                    VerificaPlagio verificador = new VerificaPlagio(); //Objeto responsável pela chamada de métodos de verificação e leitura de arquivo
                    Long antesCarregamento, depoisCarregamento, antesVerificacao, depoisVerificacao; 
                    while (escSegunda > 0){
                        System.out.println("1 - Por Hash\n2- AVL\n3- RB");
                        escSegunda = leInteiro();
                        System.out.println("Quantas palavras para verificar?");                                
                        int n = leInteiro();
                        switch(escSegunda){
                            case 1:
                                HashEncadeado1 hashPlagio = new HashEncadeado1(10000, new String[10000], new String[10000]); //Criação do Hash                                
                                antesCarregamento = System.currentTimeMillis();
                                verificador.carregaArquivoHash("plagio.txt", hashPlagio, n); //Hash vai ser preenchido com cadeias de N palavras;
                                depoisCarregamento = System.currentTimeMillis();
                                antesVerificacao = System.currentTimeMillis();
                                boolean ehPlagioHash = verificador.verifcaByHash("dados.txt", hashPlagio, n); //Verifica se há plágio
                                depoisVerificacao = System.currentTimeMillis();
                                if (ehPlagioHash){
                                    System.out.println("Arquivo plagiado");
                                }else{
                                    System.out.println("Não há plágio");
                                }
                                System.out.println("Tempo gasto para carregar arquivo na estrutura: "+(depoisCarregamento-antesCarregamento));
                                System.out.println("Tempo gasto para verificar plágio usando a estrutura: "+(depoisVerificacao-antesVerificacao));
                                break;
                            case 2:
                                AVL_Functions avlTree = new AVL_Functions();
                                antesCarregamento = System.currentTimeMillis();
                                verificador.carregaArquivoTree("Arquivos para verificar/TemPlagio2.txt", null, avlTree, n, 0); //Carrega arquivo na Árvore.
                                depoisCarregamento = System.currentTimeMillis();
                                antesVerificacao = System.currentTimeMillis();
                                boolean flagAVL = verificador.verificaByTree("Base de Documentos/dados3.txt", null,avlTree, n, 0);
                                depoisVerificacao = System.currentTimeMillis();
                                if (flagAVL){
                                    System.out.println("Arquivo plagiado");
                                }else{
                                    System.out.println("Não há plágio");
                                }
                                System.out.println("Tempo gasto para carregar arquivo na estrutura: "+(depoisCarregamento-antesCarregamento));
                                System.out.println("Tempo gasto para verificar plágio usando a estrutura: "+(depoisVerificacao-antesVerificacao));
                                break;
                            case 3:
                                RedBlackTree rbTree = new RedBlackTree();
                                antesCarregamento = System.currentTimeMillis();
                                verificador.carregaArquivoTree("plagio.txt", rbTree,null, n,1); //Carrega arquivo na Árvore.
                                depoisCarregamento = System.currentTimeMillis();
                                antesVerificacao = System.currentTimeMillis();
                                boolean flag = verificador.verificaByTree("dados.txt", rbTree,null, n,1);
                                depoisVerificacao = System.currentTimeMillis();
                                if (flag){
                                    System.out.println("Arquivo plagiado");
                                }else{
                                    System.out.println("Não há plágio");
                                }
                                System.out.println("Tempo gasto para carregar arquivo na estrutura: "+(depoisCarregamento-antesCarregamento));
                                System.out.println("Tempo gasto para verificar plágio usando a estrutura: "+(depoisVerificacao-antesVerificacao));
                                break;
                            default:
                                break;
                        }
                    }                                        
                    break;
                default:
                    break;
            }
        }

    }

    /*
       * Função: geraString(int n)
       * Descrição: Através de concatenação gera String aleatórias de tamanho N
       * Utilização: main(String []args)
       * Parâmetros:            
            n: Tamanho do String que será retornada.
       * Retorno: String de tamanho N.
    */
    public static String geraString(int n) {
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index
                    = (int) (alfabeto.length()
                    * Math.random());

            sb.append(alfabeto.charAt(index));
        }

        return sb.toString();
    }

}
