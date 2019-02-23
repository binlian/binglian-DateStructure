package com.binglian.LinkedList.digui;

/**
 * ջ�ݹ�
 * @author binglian
 *
 */
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
public class Solution {

	public ListNode removeElements(ListNode head,int val){
		while(head !=null && head.val ==val){//�����Ϊ�ղ���ͷ��Ϊval��ô��ɾ�� head.val ��ָ���ֵ
			ListNode delNode=head;
			head=head.next;
			delNode.next=null;
		}
		
		if(head == null)
			return null;
		
		ListNode prev=head;
		while(prev.next !=null){
			if(prev.next.val ==val){//�ж�ָ���ֵ �Ƿ���val ����� ��ɾ��
				ListNode delNode=prev.next;
				prev.next=delNode.next;
				delNode.next=null;
			}else {
				prev=prev.next;//���ֵ�����ݶ�����val ��ôָ����һ��
			}
		}
		return head;
	}
	
	public static void main(String[] args){
		int[] nums={1,2,6,3,4,6};
		ListNode head=new ListNode(nums);
		System.out.println(head);
		
		ListNode res=(new Solution()).removeElements(head, 6);
		System.out.println(res);
	}
}
