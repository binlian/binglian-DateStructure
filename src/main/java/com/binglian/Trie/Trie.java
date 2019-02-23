package com.binglian.Trie;

import java.util.TreeMap;

/**
 * Trie��������� �ֵ���Ҳ����˵ǰ׺��
 * 
 * �ҵ���⣬����һ������һ���ڵ㣬����һ���ַ�һ���ڵ�
 * @author binglian
 *
 */
public class Trie {
	
	/**
	 * һ��˽�еĽڵ� �ֵ����Ĵ���
	 * @author binglian
	 *
	 */
	private class Node{
		
		public boolean isWord;//��� �Ƿ񵥴ʽ��� 
		public TreeMap<Character, Node> next;//TreeMap ���ṹ��map character��char��װ�� 
		
		/**
		 * �ڵ��ʼ�������췽��������
		 * @param isWord	��ǵ����Ƿ����
		 */
		public Node(boolean isWord){
			this.isWord=isWord;
			next =new TreeMap<>();//nextָ���µĽڵ�
		}
		
		public Node(){
			this(false);//isWork Ĭ�ϸ�ֵfalse
		}
	}
	
	private Node root;//���ڵ�
	private int size;
	
	/**
	 * �ֶ�����ʼ��
	 */
	public Trie(){
		root=new Node();
		size=0;
	}
	
	/**
	 * ���Tire�д洢�ĵ�������
	 * @return
	 */
	public int getSize(){
		return size;
	}
	
	//��Trice�����һ���µĵ���word
	public void add(String word){
		
		Node cur=root;
		for(int i=0;i<word.length();i++){
			char c=word.charAt(i);//word.charAt ��string ���һ�������ַ�
			
			//�Ƿ��Ѿ���ָ��c������ û��c �ʹ����µĽڵ�
			if(cur.next.get(c) == null)
				cur.next.put(c, new Node());
			
			cur=cur.next.get(c);
		}
		//isword �ж��Ƿ�falas �ж���ǰ�Ƿ����
		if(!cur.isWord){
			cur.isWord=true;
			size++;
		}
		
	}
	
	//��ѯ����word�Ƿ���Trie��
	public boolean contains(String word){
		
		Node cur=root;
		for(int i=0;i<word.length();i++){
			char c=word.charAt(i);//word.charAt ��string ���һ�������ַ�
			
			if(cur.next.get(c)==null)//���������c c==nullû�����Ԫ�ؾͷ���false
				return false;
			//�������������ȥ ����
			cur=cur.next.get(c);
		}
		return cur.isWord;//���ؽڵ��isWord
	}
	
	
	//��ѯ�Ƿ�Trie���е�����prefixΪǰ׺
	public boolean isPrefix(String prefix){
		
		Node cur=root;
		for(int i=0;i<prefix.length();i++){
			char c=prefix.charAt(i);
			if(cur.next.get(c) == null)
				return false;
			cur=cur.next.get(c);
		}
		return true;
	}
	
}
