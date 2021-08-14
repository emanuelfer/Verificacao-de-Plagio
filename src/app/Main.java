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
        
       
        HashEncadeado he = new HashEncadeado(10); // Fórmula para decidir um tamanho?
        he.insert("Pedro", "1");
        he.insert("Pedro", "2");
        he.insert("Pedro", "1"); // Repetido n pode
        he.insert("Pedro", "3");
        he.insert("Pedro", "2"); //Repetido n pode
        System.out.println(he.findAll("Pedro"));
        //he.show();
        
        
        /*
        RedBlackTree rbTree = new RedBlackTree();
        AVL_Functions avlTree = new AVL_Functions();
        VerificaPlagio verificaPlagio = new VerificaPlagio();
        
        verificaPlagio.carregaArquivoHash("plagio.txt", he, 5);
                
//        boolean flag = verificaPlagio.verifcaByHash("dados.txt", he, 12); //Maiúsculas e minusculas // Acento;
//        //he.show();
//
//        if(flag){
//            System.out.println("É plágio");
//        }else{
//            System.out.println("Não é plagio");
//        }

        
        verificaPlagio.carregaArquivoTree("plagio.txt", rbTree,null, 5,1);
        verificaPlagio.carregaArquivoTree("plagio.txt", null, avlTree, 5, 0);
        //rbTree.show();
        
        boolean flag = verificaPlagio.verificaByTree("dados.txt", rbTree,null, 5,1); //Maiúsculas e minusculas // Acento;
        boolean flagAVL = verificaPlagio.verificaByTree("dados.txt", null,avlTree, 5, 0);
        boolean flagHash = verificaPlagio.verifcaByHash("dados.txt", he, 5);
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

        
          */

    }
}
