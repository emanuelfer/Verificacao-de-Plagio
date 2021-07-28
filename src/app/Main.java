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
public class Main {
    
    public static void main(String[] args) {
        HashEncadeado he = new HashEncadeado(1000);
        VerificaPlagio verificaPlagio = new VerificaPlagio();
        
        verificaPlagio.carregaArquivo("plagio.txt", he);
                
        boolean flag = verificaPlagio.verifica("dados.txt", he);
        //he.show();
        System.out.println(flag);
          

    }
}
