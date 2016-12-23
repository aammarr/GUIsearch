/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.search;

import java.util.HashMap;

/**
 *
 * @author Noushad
 */
public class Node {
   
    Node right;
    Node left;
    Node parent;
    int balance;
    char value;
    HashMap<String,String> hashtable = new HashMap<>();
    
    Node(char value)
    {
        this.value = value;
    }
    
    Node()
    {
        right = null;
        left = null;
    }
}
