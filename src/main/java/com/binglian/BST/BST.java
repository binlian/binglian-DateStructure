package com.binglian.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * ������(�ݹ�ʵ��)
 * 
 * e.compareTo(node.e)����ֵ ��
 * ���ָ�������������ȷ���0
 * ���ָ������С�ڲ������� -1
 * ���ָ���������ڲ������� 1��
 * @author binglian
 *
 */
public class BST<E extends Comparable<E>> {

	/**
	 * �ڵ�
	 * @author binglian
	 *
	 */
	private class Node{
		public  E e;
		public Node left,right;
		
		public Node(E e){
			this.e=e;
			left=null;
			right=null;
		}
		
		
	}

	private Node root;
	private int size;
	
	public BST(){
		root=null;
		size=0;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size ==0;
	}
	
	/* 
	//�����������������µ�Ԫ��e
	public void add(E e){
		
		if(root == null){
			root =new Node(e);
			size++;
		}else
			add(root,e);
	}
	
	
	
	//����nodeΪ���Ķ���������������Ԫ��E���ݹ��㷨
	private void add(Node node,E e){
		
		if(e.equals(node.e))
			return;
		else if(e.compareTo(node.e) < 0 && node.left==null){
			node.left=new Node(e);
			size++;
			return;
		}
		else if(e.compareTo(node.e) > 0 && node.right == null){
			node.right=new Node(e);
			size++;
			return;
		}
		
		if (e.compareTo(node.e) <0) {
			add(node.left,e);
		}else {//e.compareTo(node.e) > 0
			add(node.right,e);
		}
		
	}
	
	*/
	
	//�����������������µ�Ԫ��e
	public void add(E e){
		root=add(root, e);
	}
	
	//����nodeΪ���Ķ����������в���Ԫ��e���ݹ��㷨
	//���ز����½ڵ������������ĸ�
	private Node add(Node node,E e){
		if(node == null){
			size++;
			return new Node(e);
		}
		
		if(e.compareTo(node.e) <0)
			node.left=add(node.left, e);
		else if(e.compareTo(node.e) >0)
			node.right=add(node.right, e);
		return node;
	}
	
	//���������������Ƿ����Ԫ��e
	public boolean contains(E e){
		return contains(root,e);
	}
	
	//����nodeΪ���Ķ������������Ƿ����Ԫ��e���ݹ��㷨
	private boolean contains(Node node,E e){
		if(node == null)
			return false;
		
		if(e.compareTo(node.e) == 0)
			return true;
		else if(e.compareTo(node.e) <0)
			return contains(node.left,e);
		else//e.compareTo(node.e)>0
			return contains(node.right,e);
			
	}
	
	
	//������������ǰ�����
	public void preOrder(){
		preOrder(root);
	}
	
	//ǰ�������nodeΪ���Ķ������������ݹ��㷨
	private void preOrder(Node node){
		
		if(node == null)
			return;
		
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	//�����������ķǵݹ�ǰ�����
	public void preOrderNR(){
		Stack<Node> stack=new Stack<BST<E>.Node>();
		stack.push(root);//�ѽڵ��ջ
		while(!stack.isEmpty()){//�жϲ���Ϊ��ִ��
			Node cur=stack.pop();
			System.out.println(cur.e);
			
			if(cur.right !=null)
				stack.push(cur.right);
			if(cur.left !=null)
				stack.push(cur.left);
		}
	}
	
	//�������������������
	public void inOrder(){
		inOrder(root);
	}
	
	//���������nodeΪ���Ķ������������ݹ��㷨
	private void inOrder(Node node){
		if (node == null)
			return;
		
		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}
	
	//�����������ĺ������
	public void postOrder(){
		postOrder(root);
	}
	
	//���������nodeΪ���Ķ������������ݹ��㷨
	private void postOrder(Node node){
		
		if(node == null)
			return ;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.e);
	}
	
	//�����������Ĳ������
	public void levelOrder(){
		Queue<Node> queue=new LinkedList<BST<E>.Node>();
		queue.add(root);
		while(!queue.isEmpty()){
			Node cur=queue.remove();
			System.out.println(cur.e);
			
			if(cur.left !=null)
				queue.add(cur.left);
			if(cur.right !=null)
				queue.add(cur.right);
		}
	}
	//Ѱ�Ҷ�������������СԪ��
	public E minimum(){
		if(size == 0)
			throw new IllegalArgumentException("�������ǿյ�");
		
		return minimum(root).e;//������С��������Ԫ��
	}
	
	//������nodeΪ���Ķ�������������Сֵ���ڽڵ�
	private Node minimum(Node node){
		if(node.left ==null)//��Ϊ���ֶ�����ߵ���С����������û��Ԫ�أ��Ǵ�Ԫ�ؾ�����С
			return node;//������СԪ��
		return minimum(node.left);//�������߻���Ԫ�أ���ô����ָ����ߣ��ݹ���ȥ
	}
	
	//Ѱ�Ҷ��������������Ԫ��
	public E maximum(){
		if(size == 0)
			throw new IllegalArgumentException("�������ǿյ�");
		
		return maximum(root).e;
	}
	
	//������nodeΪ���Ķ��������������ֵ���ڵĽڵ�
	private Node maximum(Node node){
		if(node.right == null)
			return node;
		
		return maximum(node.right);
	}
	
	//�Ӷ�����������ɾ����Сֵ���ڽڵ㣬������Сֵ
	public E removeMin(){
		E ret=minimum();
		root=removeMin(root);
		return ret;
	}
	
	//ɾ������nodeΪ���Ķ����������е���С�ڵ�
	//����ɾ���ڵ���µĶ����������ĸ�
	private Node removeMin(Node node){
		
		if(node.left ==null){
			Node rightNode=node.right;//������Ϊ�գ���ôɾ������ô�ұߵ�����Ԫ����������Ҳ�γɶ�������û���ƻ�
			node.right=null;//�����ϵ
			size--;
			return rightNode;
		}
		
		node.left=removeMin(node.left);//�����Ϊ�գ��Ǿ���û���ҵ����������µݹ���ȥ
		return node;
	}
	
	//�Ӷ�����������ɾ�����ֵ���ڽڵ�
	public E removeMax(){
		E ret=maximum();
		root=removeMin(root);
		return ret;
	}
	
	//ɾ������nodeΪ���Ķ����������е����ڵ�
	//����ɾ���ڵ���µĶ����������ĸ�
	private Node removeMax(Node node){
		
		if(node.right ==null){
			Node leftNode=node.left;
			node.left=null;
			size--;
			return leftNode;
		}
		
		node.right=removeMax(node.right);
		return node;
	}
	
	//�Ӷ�����������ɾ��Ԫ��Ϊe�Ľڵ�
	public void remove(E e){
		
		root=remove(root,e);
	}
	
	//ɾ����nodeΪ���Ķ�����������Ϊe�Ľڵ㣬�ݹ��㷨
	//����ɾ���ڵ���µĶ����������ĸ�
	private Node remove(Node node,E e){
		
		if(node == null)
			return null;
		
		if(e.compareTo(node.e) <0){
			node.left=remove(node.left, e);
			return node;
		}
		else if(e.compareTo(node.e) >0){
			node.right=remove(node.right, e);
			return node;
		}
		else{
			//e==node.e
			
			if(node.left == null){
				Node rightNode=node.right;
				node.right=null;
				size--;
				return rightNode;
			}
			
			if(node.right ==null){
				Node leftNode=node.left;
				node.left=null;
				size--;
				return leftNode;
			}
			
			//��ɾ���ڵ�����������Ϊ�յ����
			//�ҵ��ȴ�ɾ���ڵ�����С�ڵ㣬����ɾ���ڵ�����������С�ڵ�
			//������ڵ㶥���ɾ���ڵ��λ��
			Node successor=minimum(node.right);
			successor.right=removeMin(node.right);
			
			successor.left=node.left;
			
			node.left=node.right=null;
			
			return successor;
		}
	}
	
	
	
	
	
	
	@Override
	public String toString(){
		StringBuilder res=new StringBuilder();
		generateBSTString(root,0,res);
		return res.toString(); 
	}
	
	//������nodeΪ���ڵ㣬���Ϊdepth���������������ַ���
	private void generateBSTString(Node node,int depth,StringBuilder res){
		
		if(node == null){
			res.append(generateDepthString(depth)+"null\n");
			return;
		}
		
		res.append(generateDepthString(depth)+node.e+"\n");
		generateBSTString(node.left, depth+1, res);
		generateBSTString(node.right, depth+1, res);
	}
	
	private String generateDepthString(int depth){
		
		StringBuilder res=new StringBuilder();
		for(int i=0;i<depth;i++)
			res.append("--");
		return res.toString();
	}
}
