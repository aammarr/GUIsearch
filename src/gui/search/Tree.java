/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Noushad
 */
public class Tree {
    
    
    public Node root;
    String url= "http://torrentz.eu/search?f=";
    
   /* void makeTree(char value)
    {
        Node newNode = new Node(value);
        
        if(root == null)
        {
            root = newNode;
        }
        else{
            Node current = root;
            while(true)
            {
                if(value < current.value)
                {
                    if(current.left == null)
                    {
                        current.left = newNode;
                        break;
                    }
                    current = current.left;
                }
                else
                {
                    
                    if(current.right== null)
                    {
                        current.right = newNode;
                        break;
                    }
                    current= current.right;
                }
            }
        }
    }
    */
    public void insert(char k) 
    {
        // create new node
        Node n = new Node(k);
        // start recursive procedure for inserting the node
        insertAVL(this.root,n);
    }
 
 /**
  * Recursive method to insert a node into a tree.
  * 
  * @param p The node currently compared, usually you start with the root.
  * @param q The node to be inserted.
  */
 public void insertAVL(Node temp, Node current) {
  // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
    if(temp==null) {
        this.root = current;
    }
    else
    {
        // If compare node is smaller, continue with the left node
        if(current.value < temp.value) 
        {
             if(temp.left==null)
             {
                temp.left = current;
                current.parent = temp;

                // Node is inserted now, continue checking the balance
                recursiveBalance(temp);
             }
             else 
             {
                insertAVL(temp.left,current);
             }

        }
        else if(current.value>temp.value) 
        {
             if(temp.right==null) 
             {
                 temp.right = current;
                 current.parent = temp;
                 // Node is inserted now, continue checking the balance
                 recursiveBalance(temp);
             } 
             else {
                 insertAVL(temp.right,current);
             }
        }
        else
        {
         // do nothing: This node already exists
        }
    }
 }
 
 
 
    public void recursiveBalance(Node cur) 
    { 
       setBalance(cur);
       int balance = cur.balance;

       // check the balance
       if(balance==-2) 
       {
            if(height(cur.left.left)>=height(cur.left.right)) {
                cur = rotateRight(cur);
            }
            else {
                cur = doubleRotateLeftRight(cur);
            }
       } 
       else if(balance==2) {
            if(height(cur.right.right)>=height(cur.right.left)) {
                cur = rotateLeft(cur);
            }
            else {
                cur = doubleRotateRightLeft(cur);
            }
        }
    }
    
    public Node rotateLeft(Node n) 
    {
        Node v = n.right;
        v.parent = n.parent;

        n.right = v.left;

        if(n.right!=null) {
            n.right.parent=n;
        }

        v.left = n;
        n.parent = v;

        if(v.parent!=null) {
            if(v.parent.right==n) {
                v.parent.right = v;
            } 
            else if(v.parent.left==n) {
                v.parent.left = v;
            }
        }

        setBalance(n);
        setBalance(v);

        return v;
    }
 
 /**
  * Right rotation using the given node.
  * 
  * @param n
  *            The node for the rotation
  * 
  * @return The root of the new rotated tree.
  */
    public Node rotateRight(Node current) 
    {
       Node newNode = current.left;
       newNode.parent = current.parent;

       current.left = newNode.right;

       if(current.left!=null) {
            current.left.parent=current;
       }

       newNode.right = current;
       current.parent = newNode;


       if(newNode.parent!=null) {
            if(newNode.parent.right==current) {
                 newNode.parent.right = newNode;
            } 
            else if(newNode.parent.left==current) {
                 newNode.parent.left = newNode;
            }
       }

       setBalance(current);
       setBalance(newNode);

       return newNode;
    }
 /**
  * 
  * @param u The node for the rotation.
  * @return The root after the double rotation.
  */
 public Node doubleRotateLeftRight(Node u) {
        u.left = rotateLeft(u.left);
        return rotateRight(u);
 }
 
 /**
  * 
  * @param u The node for the rotation.
  * @return The root after the double rotation.
  */
 public Node doubleRotateRightLeft(Node u) {
        u.right = rotateRight(u.right);
        return rotateLeft(u);
 }
 
/***************************** Helper Functions ************************************/
  
 private void setBalance(Node cur) {
        cur.balance = height(cur.right)-height(cur.left);
 }
 
 /**
  * Calculating the "height" of a node.
  * 
  * @param cur
  * @return The height of a node (-1, if node is not existent eg. NULL).
  */
 private int height(Node cur) {
        if(cur==null) {
            return -1;
        }
        if(cur.left==null && cur.right==null) {
             return 0;
        } 
        else if(cur.left==null) {
             return 1+height(cur.right);
        } 
        else if(cur.right==null) {
             return 1+height(cur.left);
        } 
        else {
            return 1+maximum(height(cur.left),height(cur.right));
        }
 }
 
 /**
  * Return the maximum of two integers.
  */
 private int maximum(int a, int b) {
        if(a>=b) {
            return a;
        } 
        else {
            return b;
        }
 }
    
    
    void putMovies(String movieName)
    {
        Node current = root;
        String links = url+movieName;
        links = links.replace(" ", "+");
        movieName = movieName.toLowerCase();
        
        if(movieName==null)
            return;
        char alphabet = movieName.charAt(0);
        while(current.value  != alphabet)
        {
            if(current.value < alphabet)
            {
                current = current.right;
            }
            else
            {
                current = current.left;
            }
        }
        current.hashtable.put(movieName, links);
    }
    
    
    void putGames(String name , String links)
    {
        Node current = root;
        name = name.toLowerCase();
        char alphabet = name.charAt(0);
        while(current.value  != alphabet)
        {
            if(current.value < alphabet)
            {
                current = current.right;
            }
            else
            {
                current = current.left;
            }
        }
        current.hashtable.put(name, links);
    }
    
    void print(String movie) throws IOException
    {
        Node current = root;
        movie = movie.toLowerCase();
        char alphabet = movie.charAt(0);
        while(current.value  != alphabet)
        {
            if(current.value < alphabet)
            {
                current = current.right;
            }
            else
            {
                current = current.left;
            }
        }
        System.out.println(movie);
        BufferedWriter bf2 = new BufferedWriter(new FileWriter("output.html"));
        
        bf2.write("<html>\n" +
                "<head>\n" +
                "<title>MOVIES</title>\n" +
                "</head>\n" +
                "<body>\n"+"<table width=100% cellspacing=\"10\" cellpadding=\"4\">\n" +
                "<tr>");
        int i=0;
        for(String key:current.hashtable.keySet())
        {
            i++;
            if(i==5)
            {
                 bf2.write("</br></tr>\n"+ "<tr>");
                i=1;
            }
            System.out.println(key); 
            bf2.write("<td><a target=\"_blank\" href=\""+current.hashtable.get(key)+"\">"
                    +"<img src=\"images/"+key+".jpg\" alt=\"image\" height=190 width=200 />"
                    + "</br>" +key+ "</a></td>");
            bf2.newLine();
            
        }
        bf2.write("</tr>\n" + " </table>"+
                    "</body>\n" +
                    "</html>");
        bf2.close();
       
    }
    
    String getValue(String name)
    {
        String s="";
        Node current = root;
        name = name.toLowerCase();
        char alphabet = name.charAt(0);
        while(current.value  != alphabet)
        {
            if(current.value < alphabet)
            {
                current = current.right;
            }
            else
            {
                current = current.left;
            }
        }
        s = current.hashtable.get(name);
        
        return s;
    }
    
    Node getRoot()
    {
        return root;
    }
    
    boolean search(String movie)
    {
        Node current = root;
        movie = movie.toLowerCase();
        char alphabet = movie.charAt(0);
        while(current.value  != alphabet)
        {
            if(current.value < alphabet)
            {
                current = current.right;
            }
            else
            {
                current = current.left;
            }
        }
        String temp = current.hashtable.get(movie);
        if(temp== null)
            return false;
        return true;
    }
}
