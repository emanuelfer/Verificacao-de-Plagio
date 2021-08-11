/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

/**
 *
 * @author emanu
 */
public class Node {
    String key;
    int height;
    Node left;
    Node right;
    Node parent;
    char color;
    
    
    public Node(String d){
        this.key = d;
        height = 1;
        this.left = this.right = this.parent = null;
        this.color = 'R';
    }
}
