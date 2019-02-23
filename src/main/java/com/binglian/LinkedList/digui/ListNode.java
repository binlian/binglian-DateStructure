package com.binglian.LinkedList.digui;

public class ListNode {
	
	public int val;
	public ListNode next;
	

	public ListNode(int x){
		val=x;
	}
	
	//����ڵ�Ľṹ����
	//ʹ��arrΪ����������һ��������ǰ��ListNodeΪ����ͷΪ�ڵ�
	public ListNode(int[] arr){
		if(arr ==null || arr.length ==0){
			throw new IllegalArgumentException("������������ݲ���Ϊ��");
		}
		
		this.val=arr[0];
		ListNode cur=this;
		for(int i=1;i<arr.length;i++){
			cur.next=new ListNode(arr[i]);
			cur=cur.next;
		}
	}
	
	//�Ե�ǰ�ڵ�Ϊͷ�ڵ��������Ϣ�ַ���
	@Override
	public String toString(){
		StringBuilder res=new StringBuilder();
		ListNode cur=this;
		while(cur !=null){
			res.append(cur.val +"->");
			cur=cur.next;
		}
		res.append("NULL");
		return res.toString();
	}
}
