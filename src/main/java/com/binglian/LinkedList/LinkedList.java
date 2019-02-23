package com.binglian.LinkedList;

/**
 * ����
 * @author binglian
 *
 */
public class LinkedList<E>{
	
	/**
	 * ����Ĵ���
	 * ˽�еĽڵ㣬�û�����Ҫȥ�˽�����������ô�����ӵ�
	 * ����������Ϊ�� �洢��Ԫ�� Ϊ���� ���������ɣ�
	 * ����Ҫһ���ڵ�ȥ���ӷ�����һ�����ӣ�����Node 
	 * @author binglian
	 *
	 */
	private class Node{
		public E e;//�洢Ԫ��
		public Node next;//�ڵ㡣
		
		/**
		 * ������һ������Ԫ�أ�this. ��Ϊ����ͬ��������ʹ��this��Ȼ���
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
	
	//��ȡ�����е�Ԫ�ظ���
	public int getSize(){
		return size;
	}
	
	//���������Ƿ�Ϊ��
	public boolean isEmpty(){
		return size ==0;
	}
	
	//����ͷ����µ�Ԫ��e
	public void addHead(E e){
//		Node node=new Node(e);//Ҫ��ӵ�Ԫ��
//		node.next=head;//Ԫ��ָ�� ԭ��ͷ����Ԫ��
//		head=node;//Ȼ���ٰ�Ҫ��ӵ�Ԫ����Ϊͷ��
		
		//��һ�ָ��Ż��ķ���
		dummyHead=new Node(e,dummyHead);//ָ��ͷ����Ȼ���ڱ�Ϊͷ��
		size++;
	}
	
	/**
	 *���Ǹ��ط����Ԫ�أ���Ϊ����û������������ ��������������Ԫ�أ������ǲ���
	 *�������index(0-based)λ������µ�Ԫ��e
	 *�����������в���һ�����õĲ��������Բ���ô��Ҫ���ͺ�
	 */
	public void add(int index,E e){
		if(index<0 || index >=size){
			throw new IllegalArgumentException("Υ��������");
		}
			Node prev=dummyHead;
			for(int i=0;i<index ;i++) //����ѭ���ҵ�Ҫ���Ԫ�ص�λ��
				prev=prev.next;
		
			//������ ˳�����Ҫ
//			Node node =new Node(e);//��ӵ�Ԫ��
//			node.next=prev.next;//��ӵ�Ԫ��ָ��index��ǰλ�õ���һ��Ԫ��
//			prev.next=node;//Ȼ����ǰλ�õ�Ԫ�� ָ�����Ԫ��
			
			//�����Ԫ������һ�� ���Ż� ����û������ĸ������׶�
			prev.next=new Node(e,prev.next);
			size++;
	}
	//������ĩβ����µ�Ԫ��e
	public void addLast(E e){
		add(size,e);
	}
	
	//�������ĵ�index(0-based)��λ�õ�Ԫ��
	//�������в���һ�����õĲ�������ϰ��
	public E get(int index){
		if(index < 0 ||index >=size)
			throw new IllegalArgumentException("Υ������");
		Node cur=dummyHead.next;
		for(int i=0;i<index;i++)
			cur=cur.next;
		return cur.e;
	}
	
	//�������ĵ�һ��Ԫ��
	public E getFirst(){
		return get(0);
	}
	//�޸������еĵ�index(0-based)��λ�õ�Ԫ��Ϊe
	//�������в���һ�����õĲ�������ϵ��
	public void set(int index,E e){
		if(index < 0 ||index >=size)
			throw new IllegalArgumentException("�Ƿ�����");
		Node cur=dummyHead;
		for(int i=0;i<index;i++)
			cur=cur.next;
		cur.e=e;
	}
	
	//�����������Ƿ���Ԫ��e
	public boolean contains(E e){
		Node cur=dummyHead.next;
		while(cur !=null){
			if(cur.e.equals(e))
				return true;
			cur=cur.next;
		}
		return false;
	}
	
	//��������ɾ��index(0-based)λ�õ�Ԫ�أ�����ɾ����Ԫ��
	public E remove(int index){
		if(index < 0 ||index >=size)
			throw new IllegalArgumentException("�Ƿ�����");
		
		Node prev=dummyHead;
		for(int i=0;i<index;i++)
			prev=prev.next;
		
		Node retNode=prev.next;
		prev.next=retNode.next;
		size--;
		
		return retNode.e;
	}
	//��ɾ����ɾ����һ��Ԫ�أ�����ɾ����Ԫ��
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
