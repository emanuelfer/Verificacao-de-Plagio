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
        
       
        HashEncadeado he = new HashEncadeado(100000); // Fórmula para decidir um tamanho?
        RedBlackTree rbTree = new RedBlackTree();
        VerificaPlagio verificaPlagio = new VerificaPlagio();
        
        verificaPlagio.carregaArquivoHash("plagio.txt", he, 12);
                
//        boolean flag = verificaPlagio.verifcaByHash("dados.txt", he, 12); //Maiúsculas e minusculas // Acento;
//        //he.show();
//
//        if(flag){
//            System.out.println("É plágio");
//        }else{
//            System.out.println("Não é plagio");
//        }

        
        verificaPlagio.carregaArquivoRBTree("plagio.txt", rbTree, 10);
        //rbTree.show();
        
        boolean flag = verificaPlagio.verificaByRBTree("dados.txt", rbTree, 10); //Maiúsculas e minusculas // Acento;
        //he.show();

        if(flag){
            System.out.println("É plágio");
        }else{
            System.out.println("Não é plagio");
        }

        
          

    }
}
