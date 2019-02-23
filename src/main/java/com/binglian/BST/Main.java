package com.binglian.BST;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args){
//		BST<Integer> bst=new BST<Integer>();
//		int[] nums={5,3,6,8,4,2};
//		
//		for(int num:nums){
//			bst.add(num);
//		}
//		
//		//////////////
//		//	  5		//
//		// 	 /  \	//
//		//  3    6	//
//		// / \  / \ //
//		//2   4    8//
//		//////////////
//
//		bst.levelOrder();
//		System.out.println();
		
//		bst.preOrder();
//		System.out.println();
		
//		bst.preOrderNR();
//		System.out.println();
		
//		bst.inOrder();
//		System.out.println();
//		
//		bst.postOrder();
//		System.out.println();
//		System.out.println(bst);
		
		
		
		BST<Integer> bst=new BST<Integer>();
		Random random=new Random();
		
		int n=1000;
		
		for(int i=0;i<n;i++)
			bst.add(random.nextInt(1000));
		
		ArrayList<Integer> nums=new ArrayList<Integer>();
		while(!bst.isEmpty())
			nums.add(bst.removeMin());//返回最小的元素，添加到nums中
		
		System.out.println(nums);
		for(int i=1;i<nums.size();i++)
			if(nums.get(i-1) >nums.get(i))
				throw new IllegalArgumentException("错误");
		
		System.out.println("removeMin 测试成功");
	
		
		//test removeMax
		for(int i=0;i<n;i++)
			bst.add(random.nextInt(1000));
		
		nums=new ArrayList<Integer>();
		while(!bst.isEmpty())
			nums.add(bst.removeMax());
		
		System.out.println(nums);
		
		for(int i=1;i<nums.size();i++)
			if(nums.get(i-1)<nums.get(i))
				throw new IllegalArgumentException("Error");
		System.out.println("removeMax 测试成功");
	}
}
