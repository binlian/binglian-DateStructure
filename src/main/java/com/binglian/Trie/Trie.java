package com.binglian.Trie;

import java.util.TreeMap;

/**
 * Trie（多叉树） 字典树也可以说前缀树
 * 
 * 我的理解，它是一个单词一个节点，不是一个字符一个节点
 * @author binglian
 *
 */
public class Trie {
	
	/**
	 * 一个私有的节点 字典树的创建
	 * @author binglian
	 *
	 */
	private class Node{
		
		public boolean isWord;//标记 是否单词结束 
		public TreeMap<Character, Node> next;//TreeMap 树结构的map character是char包装类 
		
		/**
		 * 节点初始化（构造方法创建）
		 * @param isWord	标记单词是否结束
		 */
		public Node(boolean isWord){
			this.isWord=isWord;
			next =new TreeMap<>();//next指向新的节点
		}
		
		public Node(){
			this(false);//isWork 默认赋值false
		}
	}
	
	private Node root;//根节点
	private int size;
	
	/**
	 * 字段数初始化
	 */
	public Trie(){
		root=new Node();
		size=0;
	}
	
	/**
	 * 获得Tire中存储的单词数量
	 * @return
	 */
	public int getSize(){
		return size;
	}
	
	//向Trice中添加一个新的单词word
	public void add(String word){
		
		Node cur=root;
		for(int i=0;i<word.length();i++){
			char c=word.charAt(i);//word.charAt 吧string 拆成一个个的字符
			
			//是否已经有指向c的内容 没有c 就创建新的节点
			if(cur.next.get(c) == null)
				cur.next.put(c, new Node());
			
			cur=cur.next.get(c);
		}
		//isword 判断是否falas 判断以前是否存在
		if(!cur.isWord){
			cur.isWord=true;
			size++;
		}
		
	}
	
	//查询单词word是否在Trie中
	public boolean contains(String word){
		
		Node cur=root;
		for(int i=0;i<word.length();i++){
			char c=word.charAt(i);//word.charAt 吧string 拆成一个个的字符
			
			if(cur.next.get(c)==null)//如果不存在c c==null没有这个元素就返回false
				return false;
			//否则继续查找下去 更新
			cur=cur.next.get(c);
		}
		return cur.isWord;//返回节点的isWord
	}
	
	
	//查询是否Trie中有单词以prefix为前缀
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
