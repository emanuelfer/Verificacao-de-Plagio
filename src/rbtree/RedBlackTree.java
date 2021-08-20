package rbtree;


public class RedBlackTree {
    Node root;
    private static boolean ll = false;
    private static boolean rr = false;
    private static boolean lr = false;
    private static boolean rl = false;
    
    public int height(Node node){
        if(node == null)
            return 0;
        return node.height;
    }
    
    public int max(int a, int b){
        return (a > b)? a:b;
    }
    
    public Node rightRotate(Node node){
        Node x = node.left;
        Node y = x.right;
        x.right = node;
        node.left = y;
        node.parent = x;
        
        if(y!= null)
            y.parent = node;
        return x;
    }
    
    public Node leftRotate(Node node){
        Node x = node.right;
        Node y = x.left;
        x.left = node;
        node.right = y;
        node.parent = x;
        
        if(y!=null)
            y.parent = node;
        
        return x;
    }
    
    public Node insertHelp(Node root, String data){
        boolean f = false;
        if(root == null)
            return(new Node(data));
        else if(data.compareTo(root.key) < 0){ //data precede root.key
            root.left = insertHelp(root.left, data);
            root.left.parent = root;
            if(root != this.root){
                if(root.color == 'R' && root.left.color == 'R')
                    f=true;
            }
        }
        else{
            root.right = insertHelp(root.right, data);
            root.right.parent = root;
            if(root != this.root){
                if(root.color == 'R' && root.right.color == 'R')
                    f=true;

            }
        }
        
        if(this.ll){//left rotate
            root = this.leftRotate(root);
            root.color = 'B';
            root.left.color = 'R';
            this.ll = false;
        }
        else if(this.rr){// right rotate
            root = this.rightRotate(root);
            root.color = 'B';
            root.right.color = 'R';
            this.rr = false;
        }
        else if(this.rl){// right rotate -> left rotate
            root.right = this.rightRotate(root.right);
            root.right.parent = root;
            root = this.leftRotate(root);
            root.color = 'B';
            root.left.color = 'R';
            this.rl = false;
        }
        else if(this.lr){//left rotate -> right rotate
            root.left = this.leftRotate(root.left);
            root.left.parent = root;
            root = this.rightRotate(root);
            root.color = 'B';
            root.right.color = 'R';
            this.lr = false;
        }
        
        if(f){
            if(root.parent.right == root){
                if(root.parent.left == null || root.parent.left.color == 'B'){
                    if(root.left != null && root.left.color =='R')
                        this.rl = true;
                    else if(root.right != null && root.right.color == 'R')
                        this.ll = true;
                }
                else{
                    root.parent.left.color = 'B';
                    root.color = 'B';
                    if(root.parent != this.root)
                        root.parent.color = 'R';
                }
            }
            else{
                if(root.parent.right == null || root.parent.right.color == 'B'){
                    if(root.left != null && root.left.color == 'R')
                        this.rr = true;
                    else if(root.right!=null && root.right.color =='R')
                        this.lr = true;
                }
                else{
                    root.parent.right.color = 'B';
                    root.color = 'B';
                    if(root.parent != this.root)
                        root.parent.color = 'R';
                }
            }
        }
        return root;
    }
    
    public void insert(String data){
        if(this.root == null){
            this.root = new Node(data);
            this.root.color = 'B';
        }else{
            this.root = insertHelp(this.root, data);
        }
    }
    
    public Node sibling(Node x){
        if(x.parent == null)
            return null;
        
        if(x == x.parent.left)
            return x.right;
        else 
            return x.left;
    }
    
    public boolean hasRedChild(Node node){
        if(node.left == null && node.right == null)
            return false;
        
        if(node.left != null)
            if(node.left.color == 'R')
                return true;
        
        if(node.right != null)
            if(node.right.color == 'R')
                return true;
        
        return false;
    }
    
    public void fixDoubleBlack(Node x){
        if(x == root)
            return;
        
        Node sibling = sibling(x), parent = x.parent;
        if(sibling == null){
            fixDoubleBlack(parent);
        }else{
            if(sibling.color == 'R'){
                parent.color = 'R';
                sibling.color = 'B';
                if(sibling.equals(sibling.parent.left)){
                    rightRotate(parent);
                }else{
                    leftRotate(parent);
                }
                fixDoubleBlack(x);
            }else{
                
                if(hasRedChild(sibling)){
                    if(sibling.left != null && sibling.left.color == 'R'){
                        if(sibling.equals(sibling.parent.left)){
                            sibling.left.color = sibling.color;
                            sibling.color = parent.color;
                            rightRotate(parent);
                        }else{
                            sibling.left.color = parent.color;
                            rightRotate(sibling);
                            leftRotate(parent);
                        }
                    }else{
                        if(sibling.equals(sibling.parent.left)){
                            sibling.right.color = parent.color;
                            leftRotate(sibling);
                            rightRotate(parent);
                        }else{
                            sibling.right.color = sibling.color;
                            sibling.color = parent.color;
                            leftRotate(parent);
                        }
                    }
                    parent.color = 'B';
                }else{
                    sibling.color = 'R';
                    if(parent.color == 'B')
                        fixDoubleBlack(parent);
                    else
                        parent.color = 'B';
                }
            }
        }
    }
    
    public void deleteNode(Node v){
        Node u = BSTreplace(v);
        
        boolean uvBlack = ((u == null || u.color == 'B') && (v.color == 'B'));
        Node parent = v.parent;
        
        if(u == null){
            if(v == root){
                root = null;
            }else{
                if(uvBlack){
                    fixDoubleBlack(v);
                }else{
                    Node sibling = sibling(v);
                    if(sibling != null)
                        sibling.color = 'R';
                }
                if(v == v.parent.left)
                    parent.left = null;
                else
                    parent.right = null;
            }
            return;
        }
        
        if(v.left == null || v.right == null){
            if(v == root){
                v.key = u.key;
                v.left = v.right;
            }else{
                if(v == v.parent.left)
                    parent.left = u;
                else
                    parent.right = u;
                
                u.parent = parent;
                if(uvBlack)
                    fixDoubleBlack(u);
                else
                    u.color = 'B';
            }
            return;
        }
        swapValues(u, v);
        deleteNode(u);
    }
    
    public void swapValues(Node u, Node v){
        String temp;
        temp = u.key;
        u.key = v.key;
        v.key = temp;
    }
    
    public Node BSTreplace(Node x){
        if(x.left != null && x.right != null){
            return sucessor(x.right);
        }
        
        if(x.left == null && x.right == null)
            return null;
        
        if(x.left != null)
            return x.left;
        else
            return x.right;
    }
    
    public Node sucessor(Node x){
        Node temp = x;
        
        while(temp.left != null)
            temp = temp.left;
        
        return temp;
    }
    
    public void show(){
        this.preOrder(root);
    }
    
        
    public void preOrder(Node node){
        if(node != null){
            System.out.println(node.key + " ");
            this.preOrder(node.left);
            this.preOrder(node.right);
        }
    }
    
    public boolean search(String data){
        Node temp = root;
        
        while(temp != null){
            if(data.compareTo(temp.key) < 0){
                if(temp.left == null)
                    break;
                else
                    temp = temp.left;
            }else if(data.equals(temp.key)){
               // System.out.println(data + " - " + temp.key);
                break;
            }else{
                if(temp.right == null)
                    break;
                else
                    temp = temp.right;
            }
        }
        

        if(data.equals(temp.key))
            return true;    
        return false;
    }
}
