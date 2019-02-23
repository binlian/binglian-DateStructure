package com.binglian.HeapQueue;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		
		int n=100000;
		MaxHeap<Integer> maxHeap=new MaxHeap<Integer>();
		Random random=new Random();
		for(int i=0;i<n;i++)
			maxHeap.add(random.nextInt(Integer.MAX_VALUE));//随机添加0-integer最大值
	
		int[] arr=new int[n];
		for(int i=0;i<n;i++)
			arr[i]=maxHeap.extractMax();
		
		for(int i=1;i<n;i++)
			if(arr[i-1] <arr[i])
				throw new IllegalArgumentException("错误");
		
			
	}

}
