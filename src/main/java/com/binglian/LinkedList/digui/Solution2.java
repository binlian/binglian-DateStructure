package com.binglian.LinkedList.digui;


/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

//使用递归进行编写
public class Solution2 {

	public ListNode removeElements(ListNode head,int val){
		if(head ==null)
			return null;
		
		ListNode res=removeElements(head.next, val);
		if(head.val== val)
			return res;
		else {
			head.next=res;
			return head;
		}
	}
	
	public static void main(String[] args){
		int[] nums={1,2,6,3,4,6};
		ListNode head=new ListNode(nums);
		System.out.println(head);
		
		ListNode res=(new Solution2()).removeElements(head, 6);
		System.out.println(res);
	}
}
