package com.binglian.AVLTree;
import java.sql.Blob;
import java.util.ArrayList;

import com.binglian.HeapQueue.MaxHeap;

/**
 * ��BST ��΢�޸�
 * @author binglian
 *
 * @param <K>
 * @param <V>
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;//������Ԫ�ظ߶�

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height=1;//������
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    //�жϸö������Ƿ���һ�Ŷ���������
    public boolean isBST(){
    	
    	ArrayList<K> keys=new ArrayList<>();
    	inOrder(root,keys);
    	for(int i=1;i<keys.size();i++)
    		if(keys.get(i-1).compareTo(keys.get(i))>0)
    			return false;
    	return true;
    }
    
    private void inOrder(Node node,ArrayList<K> keys){
    	
    	if(node == null)
    		return ;
    	inOrder(node.left, keys);
    	keys.add(node.key);
    	inOrder(node.right, keys);
    	
    }
    
    //�жϸö������Ƿ���һ��ƽ�������
    public boolean isBalanced(){
    	return isBlanced(root);
    }
    
    //�ж���nodeΪ���Ķ������Ƿ���һ�Ŷ��������ݹ��㷨
    private boolean isBlanced(Node node){
    	
    	if(node ==null)
    		return true;
    	
    	int balanceFactor=getBalanceFactor(node);
    	if(Math.abs(balanceFactor) >1)
    		return false;
    	return isBlanced(node.left) && isBlanced(node.right);
    }

    //��ȡ�ڵ�node�ĸ߶�
    private int getHeight(Node node){
    	
    	if(node ==null)
    		return 0;
    	return node.height;
    }

    // ��ýڵ�node��ƽ������
    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

 // �Խڵ�y����������ת������������ת���µĸ��ڵ�x
    //        y                              x
    //       / \                           /   \
    //      x   T4     ������ת (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
    	
    	Node x=y.left;
    	Node T3=x.right;
    	
    	//������ת����
    	x.right=y;
    	y.left=T3;
    	
    	
    	//����height
    	y.height=Math.max(getHeight(y.left), getHeight(y.right)+1);
    	x.height=Math.max(getHeight(x.left), getHeight(x.right)+1);
    	
    	return x;
    	
    }

    // �Խڵ�y����������ת������������ת���µĸ��ڵ�x
    //    y                             x
    //  /  \                          /   \
    // T1   x      ������ת (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
    	
    	Node x=y.right;
    	Node T2=x.left;
    	
    	//������ת����
    	x.left=y;
    	y.right=T2;
    	
    	//����height
    	x.height=Math.max(getHeight(y.left), getHeight(y.right))+1;
    	y.height=Math.max(getHeight(x.left), getHeight(x.right))+1;
    	
    	return x;
    }
    
    // ������������������µ�Ԫ��(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // ����nodeΪ���Ķ����������в���Ԫ��(key, value)���ݹ��㷨
    // ���ز����½ڵ������������ĸ�
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // ����height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // ����ƽ������
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            System.out.println("unbalanced : " + balanceFactor);

        //ƽ��ά��
        if(balanceFactor >1 && getBalanceFactor(node.left) >=0)//����ת
        	return rightRotate(node);
        
        if(balanceFactor <-1 && getBalanceFactor(node.right) <=0)//����ת
        	return leftRotate(node);
        
        
        //LR 
        if(balanceFactor < -1 && getBalanceFactor(node.left) > 0){
        	node.right=rightRotate(node.left);
        	return rightRotate(node);
        }
        
        //RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
        	node.right=rightRotate(node.right);
        	return leftRotate(node);
        }
        
        return node;
    }

    // ������nodeΪ���ڵ�Ķ����������У�key���ڵĽڵ�
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // ������nodeΪ���Ķ�������������Сֵ���ڵĽڵ�
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // ɾ������nodeΪ���Ķ����������е���С�ڵ�
    // ����ɾ���ڵ���µĶ����������ĸ�
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // �Ӷ�����������ɾ����Ϊkey�Ľڵ�
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // ��ɾ���ڵ�������Ϊ�յ����
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // ��ɾ���ڵ�������Ϊ�յ����
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // ��ɾ���ڵ�������������Ϊ�յ����

            // �ҵ��ȴ�ɾ���ڵ�����С�ڵ�, ����ɾ���ڵ�����������С�ڵ�
            // ������ڵ㶥���ɾ���ڵ��λ��
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST:"+map.isBST());
            System.out.println("is Balanced:"+map.isBST());
        }

        System.out.println();
    }
}