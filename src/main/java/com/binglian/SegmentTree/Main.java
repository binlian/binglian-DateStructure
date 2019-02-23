package com.binglian.SegmentTree;

public class Main {

	public static void main(String[] args) {

		Integer[] nums={-2,0,3,-5,2,-1};
//		SegmentTree<Integer> segmentTree=new SegmentTree<Integer>(nums,new Merger<Integer>() {
//			
//			public Integer merge(Integer a,Integer b){
//				return a+b;
//			}
//		});
		
		//上面的匿名函数可以表示如下的简便方法
		SegmentTree<Integer> segmentTree=new SegmentTree<Integer>(nums,(a,b)->a+b);
//		System.out.println(segmentTree);
		
		System.out.println(segmentTree.query(0, 2));
	}

}
