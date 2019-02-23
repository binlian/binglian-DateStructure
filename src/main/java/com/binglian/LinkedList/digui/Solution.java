package com.binglian.LinkedList.digui;

/**
 * 栈递归
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
		while(head !=null && head.val ==val){//如果不为空并且头部为val那么就删除 head.val 是指向的值
			ListNode delNode=head;
			head=head.next;
			delNode.next=null;
		}
		
		if(head == null)
			return null;
		
		ListNode prev=head;
		while(prev.next !=null){
			if(prev.next.val ==val){//判断指向的值 是否是val 如果是 就删除
				ListNode delNode=prev.next;
				prev.next=delNode.next;
				delNode.next=null;
			}else {
				prev=prev.next;//如果值的内容都不是val 那么指向下一个
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
