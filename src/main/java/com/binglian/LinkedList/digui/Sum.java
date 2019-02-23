package com.binglian.LinkedList.digui;

public class Sum {

	public static int sum(int[] arr){
		return sum(arr,0);
	}
	
	//计算arr[l..n]这个区间内所有数组的和
	private static  int sum(int[] arr,int l){
		if(l == arr.length)//出口、求解最基本问题
			return 0;
		return arr[l]+sum(arr,l+1);//把原问题转化成更小的问题
	}
	
	public static void main(String[] args){
		int[] nums={1,2,3,4,5,6,7,8};
		
	}
}
