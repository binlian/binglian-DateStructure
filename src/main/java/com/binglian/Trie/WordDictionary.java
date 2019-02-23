package com.binglian.Trie;

import java.util.TreeMap;

class WordDictionary {

	
	private class Node{
		
		public boolean isWord;
		public TreeMap<Character, Node> next;
		
		public Node(boolean isWord){
			this.isWord=isWord;
			next=new TreeMap<>();
		}
		
		public Node(){
			this(false);
		}
		
	}
	
	private Node root;
	
	
    /** Initialize your data structure here. */
    public WordDictionary() {
        root=new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        
    	Node cur=root;
    	for(int i=0;i<word.length();i++){
    		char c=word.charAt(i);
    		if(cur.next.get(c) == null)
    			cur.next.put(c, new Node());
    		cur=cur.next.get(c);
    	}
    	cur.isWord=true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0);
    }
    
    /**
     * "."匹配任何字符 正则表达式
     * @param node 整体Trie树  获取内容进行匹配
     * @param word 搜索的内容
     * @param index 搜索的位置 默认从根节点开始 往下递归就+1
     * @return
     */
    private boolean match(Node node,String word,int index){
    	
    	if(index == word.length())//到底了 返回结果
    		return node.isWord;
    	
    	char c=word.charAt(index);
    	if(c !='.'){//不为‘.’
    		if(node.next.get(c) ==null)//判断内容是不是为空 如果为空就返回false
    			return false;
    		return match(node.next.get(c), word, index+1);
    	}else{//如果为'.',循环继续递归下去
    		for(char nextChar : node.next.keySet())
    			if(match(node.next.get(nextChar), word, index+1))//如果匹配成功 返回true
    				return true;
    		return false;
    	}
		
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */