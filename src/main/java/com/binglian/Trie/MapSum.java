package com.binglian.Trie;

import java.util.TreeMap;

class MapSum {

	private class Node{
		
		public int value;
		public TreeMap<Character, Node> next;
		
		public Node(int value){
			this.value=value;
			next=new TreeMap<>();
		}
		
		public Node(){
			this(0);
		}
	}
	
	private Node root;
	
    /** Initialize your data structure here. */
    public MapSum() {
        root=new Node();
    }
    
    /**
     * 插入的字符和值
     * @param word 键
     * @param val 值
     */
    public void insert(String word, int val) {
        
    	Node cur=root;
    	for(int i=0;i<word.length();i++){
    		
    		char c=word.charAt(i);//将word字符串 一个个拆分一个char
    		if(cur.next.get(c) == null)
    			cur.next.put(c, new Node());
    		cur=cur.next.get(c);
    	}
    	cur.value=val;
    }
    
    public int sum(String prefix) {
        
    	Node cur=root;
    	for(int i=0;i<prefix.length();i++){
    		
    		char c=prefix.charAt(i);
    		if(cur.next.get(c) == null)
    			return 0;
    		cur=cur.next.get(c);
    	}
    	
    	return sum(cur);
    			
    }
    
    private int sum(Node node){
    	
    	//可要可无
//    	if(node.next.size() ==0)//判断是否有元素
//    		return node.value;
    	
    	int res=node.value;
    	for(char c:node.next.keySet())
    		res +=sum(node.next.get(c));
    	
    	return res;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */