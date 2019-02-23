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
     * "."ƥ���κ��ַ� ������ʽ
     * @param node ����Trie��  ��ȡ���ݽ���ƥ��
     * @param word ����������
     * @param index ������λ�� Ĭ�ϴӸ��ڵ㿪ʼ ���µݹ��+1
     * @return
     */
    private boolean match(Node node,String word,int index){
    	
    	if(index == word.length())//������ ���ؽ��
    		return node.isWord;
    	
    	char c=word.charAt(index);
    	if(c !='.'){//��Ϊ��.��
    		if(node.next.get(c) ==null)//�ж������ǲ���Ϊ�� ���Ϊ�վͷ���false
    			return false;
    		return match(node.next.get(c), word, index+1);
    	}else{//���Ϊ'.',ѭ�������ݹ���ȥ
    		for(char nextChar : node.next.keySet())
    			if(match(node.next.get(nextChar), word, index+1))//���ƥ��ɹ� ����true
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