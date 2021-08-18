/*
    * Programa feito para o complemento da segunda nota da disciplina de Estrutura de Dados II
    * Feito por: Emanuel Lindoso Ferreira e Pedro Víctor de Abreu Fonseca.
    * Objetivo geral: Uso de tabelas Hash e Árvores AVL e Rubro-Negra.
 */
package app;

import Objects.Pessoa;
import file.File;
import hash.HashDuplo;
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

    protected static String leString() {
        String s = leitor.nextLine();
        return s;
    }

    public static void main(String[] args) {
        Random gerador = new Random();
        LinkedList<String[]> arquivosBase = new LinkedList<>();
        for (int i = 1; i < 4; i++) {
            arquivosBase.add(File.leBuffered("Base de Documentos/dados" + i + ".txt"));
        }

        int esc = 1, tamHash = 0;
        while (esc > 0) {
            System.out.println("1 - Primeira Questão\n2 - Questão Plágio\n");
            esc = leInteiro();
            switch (esc) {
                case 1:
                    System.out.println("Tamanho hash");
                    tamHash = leInteiro();
                    HashEncadeado1 hashIntString = new HashEncadeado1<Integer, Integer>(tamHash, new Integer[tamHash], new Integer[tamHash]);
                    int i = 0;
                    Long timeBefore = System.currentTimeMillis();
                    for (i = 0; i < tamHash * 10; i++) {
                        hashIntString.insert(gerador.nextInt(tamHash), gerador.nextInt(tamHash));

                    }
                    Long timeAfter = System.currentTimeMillis();
                    System.out.println("Tempo Médio da operação de inserção em todos os Hashs: " + (timeAfter - timeBefore) / 3);

                    // -------------------- FIND ALL -----------------------------
                    System.out.println("Digite uma chave para busca");
                    int chave = leInteiro();
                    if (hashIntString != null) {
                        System.out.print("Chave: " + chave + " ->");
                        System.out.println(hashIntString.findAll(chave));
                    } else {
                        System.out.println("\nHash não inicializado");
                    }

                    System.out.println("--------------- Mostrando Tudo ---- ");
                    hashIntString.show();

                    break;
                case 2:
                    int escSegunda = 1;
                    VerificaPlagio verificador = new VerificaPlagio(); //Objeto responsável pela chamada de métodos de verificação e leitura de arquivo
                    Long antesCarregamento,
                     depoisCarregamento,
                     antesVerificacao,
                     depoisVerificacao;
                    while (escSegunda > 0) {
                        System.out.println("1 - Por Hash\n2- AVL\n3- RB");
                        escSegunda = leInteiro();
                        String nomeArquivo = "";                        
                        System.out.println("Quantas palavras para verificar?");
                        int n = leInteiro();
                        System.out.println("Nome do arquivo para verificar mais o formato");
                        nomeArquivo = leString();
                        if (nomeArquivo.contains(".txt") == false) {                            
                            nomeArquivo+=".txt";
                        }
                        System.out.println(nomeArquivo);
                        switch (escSegunda) {
                            case 1:
                                HashEncadeado1 hashPlagio;
                                for (int j = 0; j < arquivosBase.size(); j++) {
                                    System.out.println("---------------------------");
                                    hashPlagio = new HashEncadeado1(5000, new String[5000], new String[5000]); //Criação do Hash                                     
                                    antesCarregamento = System.currentTimeMillis();
                                    verificador.carregaArquivoHash("Arquivos para verificar/"+nomeArquivo, hashPlagio, n); //Hash vai ser preenchido com cadeias de N palavras;
                                    depoisCarregamento = System.currentTimeMillis();
                                    antesVerificacao = System.currentTimeMillis();
                                    boolean ehPlagioHash = verificador.verifcaByHash(arquivosBase.get(j), hashPlagio, n); //Verifica se há plágio                                    
                                    depoisVerificacao = System.currentTimeMillis();
                                    if (ehPlagioHash) {
                                        System.out.println("Arquivo dados" + (j + 1) + ".txt plagiado");
                                    } else {
                                        System.out.println("Não há plágio no arquivo dados" + (j + 1) + ".txt");
                                    }
                                    System.out.println("Tempo gasto para carregar arquivo na estrutura: " + (depoisCarregamento - antesCarregamento));
                                    System.out.println("Tempo gasto para verificar plágio usando a estrutura: " + (depoisVerificacao - antesVerificacao));
                                    hashPlagio = null;
                                }
                                break;
                            case 2:
                                AVL_Functions avlTreePlagio;
                                // ---- Verificando arquivo usando AVL
                                for (int j = 0; j < arquivosBase.size(); j++) {
                                    avlTreePlagio = new AVL_Functions();
                                    System.out.println("-------------------------");
                                    antesCarregamento = System.currentTimeMillis();
                                    verificador.carregaArquivoTree("Arquivos para verificar/"+nomeArquivo, null, avlTreePlagio, n, 0); //Carrega arquivo na Árvore.
                                    depoisCarregamento = System.currentTimeMillis();
                                    antesVerificacao = System.currentTimeMillis();
                                    boolean flagAVL = verificador.verificaByTree(arquivosBase.get(j), null, avlTreePlagio, n, 0);
                                    depoisVerificacao = System.currentTimeMillis();
                                    if (flagAVL) {
                                        System.out.println("Arquivo dados" + (j + 1) + ".txt plagiado");
                                    } else {
                                        System.out.println("Não há plágio no arquivo dados" + (j + 1) + ".txt");
                                    }
                                    System.out.println("Tempo gasto para carregar arquivo na estrutura: " + (depoisCarregamento - antesCarregamento));
                                    System.out.println("Tempo gasto para verificar plágio usando a estrutura: " + (depoisVerificacao - antesVerificacao));
                                    avlTreePlagio = null;
                                }
                                break;
                            case 3:
                                RedBlackTree rbTree;
                                for (int j = 0; j < arquivosBase.size(); j++) {
                                    System.out.println("-----------------");
                                    rbTree = new RedBlackTree();
                                    antesCarregamento = System.currentTimeMillis();
                                    verificador.carregaArquivoTree("Arquivos para verificar/"+nomeArquivo, rbTree, null, n, 1); //Carrega arquivo na Árvore.
                                    depoisCarregamento = System.currentTimeMillis();
                                    antesVerificacao = System.currentTimeMillis();
                                    boolean flag = verificador.verificaByTree(arquivosBase.get(j), rbTree, null, n, 1);
                                    depoisVerificacao = System.currentTimeMillis();
                                    if (flag) {
                                        System.out.println("Arquivo dados" + (j + 1) + ".txt plagiado");
                                    } else {
                                        System.out.println("Não há plágio no arquivo dados" + (j + 1) + ".txt");
                                    }
                                    System.out.println("Tempo gasto para carregar arquivo na estrutura: " + (depoisCarregamento - antesCarregamento));
                                    System.out.println("Tempo gasto para verificar plágio usando a estrutura: " + (depoisVerificacao - antesVerificacao));
                                    rbTree = null;
                                }

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
