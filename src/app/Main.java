package app;

import file.File;
import hash.HashDuplo;
import hash.HashEncadeado;
import hash.HashLinear;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emanu
 */

import java.util.Random;
import rbtree.RedBlackTree;
import avltree.AVL_Functions;
import hash.HashEncadeado1;
public class Main {
    
    public static void main(String[] args) {
        /*
        Random randomizer = new Random();
        //--------------- 1
        
        HashEncadeado hash1 = new HashEncadeado(10);
        
        for (int i=0; i<50; i++){
            int key = randomizer.nextInt(9);
            int value = randomizer.nextInt();
            hash1.insert(key, value);
        }*/
        
       
          HashEncadeado1 he = new HashEncadeado1<String, String>(10000, new String[10000], new String[10000]); // Fórmula para decidir um tamanho?
//        he.insert(10, "1");
//        he.insert(10, "2");
//        he.insert(10, "1"); // Repetido n pode
//        he.insert(10, "3");
//        he.insert(10, "2"); //Repetido n pode
//        System.out.println(he.findAll(10));
//        he.show();
        
        
        
        RedBlackTree rbTree = new RedBlackTree();
        AVL_Functions avlTree = new AVL_Functions();
        VerificaPlagio verificaPlagio = new VerificaPlagio();
        
        verificaPlagio.carregaArquivoHash("plagio.txt", he, 10);
                
//        boolean flag = verificaPlagio.verifcaByHash("dados.txt", he, 12); //Maiúsculas e minusculas // Acento;
//        //he.show();
//
//        if(flag){
//            System.out.println("É plágio");
//        }else{
//            System.out.println("Não é plagio");
//        }

        
        verificaPlagio.carregaArquivoTree("plagio.txt", rbTree,null, 10,1);
        verificaPlagio.carregaArquivoTree("plagio.txt", null, avlTree, 10, 0);
        //rbTree.show();
        
        boolean flag = verificaPlagio.verificaByTree("dados.txt", rbTree,null, 10,1); //Maiúsculas e minusculas // Acento;
        boolean flagAVL = verificaPlagio.verificaByTree("dados.txt", null,avlTree, 10, 0);
        boolean flagHash = verificaPlagio.verifcaByHash("dados.txt", he, 10);
        //he.show();

        if (flagHash){
            System.out.println("É plágio | Usando: Hash");
        }else{
            System.out.println("Não é plágio | Usando: Hash");
        }
        if(flag){
            System.out.println("É plágio | Usando: RB");
        }else{
            System.out.println("Não é plagio | Usando RB");
        }
        
        if (flagAVL){
            System.out.println("É plágio | Usando: AVL");
        }else{
            System.out.println("Não é plágio | Usando: AVL");
        }

        
         

    }
}
