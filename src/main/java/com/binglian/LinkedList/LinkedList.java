package com.binglian.LinkedList;

/**
 * 链表
 * @author binglian
 *
 */
public class LinkedList<E>{
	
	/**
	 * 链表的创建
	 * 私有的节点，用户不需要去了解他到底是怎么样连接的
	 * 链表可以理解为火车 存储的元素 为箱子 多个箱子组成，
	 * 就需要一个节点去连接访问另一个箱子，就用Node 
	 * @author binglian
	 *
	 */
	private class Node{
		public E e;//存储元素
		public Node next;//节点。
		
		/**
		 * 连接下一个链表元素，this. 因为都是同名，所以使用this，然后给
		 * @param e
		 * @param next
		 */
		public Node(E e,Node next){
			this.e=e;
			this.next=next;
		}
		
		public Node(E e){
			this(e, null);
		}
		
		public Node(){
			this(null, null);
		}
		
		@Override
		public String toString(){
			return e.toString();
		}
	}
	private Node dummyHead;
	private int size;
	
	public LinkedList(){
		dummyHead=new Node();
		size=0;
	}
	
	//获取链表中的元素个数
	public int getSize(){
		return size;
	}
	
	//返回链表是否为空
	public boolean isEmpty(){
		return size ==0;
	}
	
	//在链头添加新的元素e
	public void addHead(E e){
//		Node node=new Node(e);//要添加的元素
//		node.next=head;//元素指向 原来头部的元素
//		head=node;//然后再吧要添加的元素做为头部
		
		//另一种更优化的方法
		dummyHead=new Node(e,dummyHead);//指向头部，然后在变为头部
		size++;
	}
	
	/**
	 *在那个地方添加元素，因为链表没有索引，所以 链表更适用于添加元素，而不是查找
	 *在链表的index(0-based)位置添加新的元素e
	 *索引在链表中不是一个常用的操作，所以不怎么重要理解就好
	 */
	public void add(int index,E e){
		if(index<0 || index >=size){
			throw new IllegalArgumentException("违法操作，");
		}
			Node prev=dummyHead;
			for(int i=0;i<index ;i++) //就是循环找到要添加元素的位置
				prev=prev.next;
		
			//链表中 顺序很重要
//			Node node =new Node(e);//添加的元素
//			node.next=prev.next;//添加的元素指向index以前位置的下一个元素
//			prev.next=node;//然后以前位置的元素 指向添加元素
			
			//和添加元素哪里一样 更优化 但是没有上面的更加容易懂
			prev.next=new Node(e,prev.next);
			size++;
	}
	//在链表末尾添加新的元素e
	public void addLast(E e){
		add(size,e);
	}
	
	//获得链表的第index(0-based)个位置的元素
	//在链表中不是一个常用的操作，练习用
	public E get(int index){
		if(index < 0 ||index >=size)
			throw new IllegalArgumentException("违法操作");
		Node cur=dummyHead.next;
		for(int i=0;i<index;i++)
			cur=cur.next;
		return cur.e;
	}
	
	//获得链表的第一个元素
	public E getFirst(){
		return get(0);
	}
	//修改链表中的第index(0-based)个位置的元素为e
	//在链表中不是一个常用的操作，联系用
	public void set(int index,E e){
		if(index < 0 ||index >=size)
			throw new IllegalArgumentException("非法操作");
		Node cur=dummyHead;
		for(int i=0;i<index;i++)
			cur=cur.next;
		cur.e=e;
	}
	
	//查找链表中是否有元素e
	public boolean contains(E e){
		Node cur=dummyHead.next;
		while(cur !=null){
			if(cur.e.equals(e))
				return true;
			cur=cur.next;
		}
		return false;
	}
	
	//从链表中删除index(0-based)位置的元素，返回删除的元素
	public E remove(int index){
		if(index < 0 ||index >=size)
			throw new IllegalArgumentException("非法操作");
		
		Node prev=dummyHead;
		for(int i=0;i<index;i++)
			prev=prev.next;
		
		Node retNode=prev.next;
		prev.next=retNode.next;
		size--;
		
		return retNode.e;
	}
	//从删除中删除第一个元素，返回删除的元素
	public E removeFirst(){
		return remove(size-1);
	}
	@Override
	public String toString(){
		StringBuilder res=new StringBuilder();
		
		Node cur=dummyHead.next;
		while(cur != null){
			res.append(cur+"->");
			cur=cur.next;
		}
		res.append("NULL");
		
		return res.toString();
	}
	
}
