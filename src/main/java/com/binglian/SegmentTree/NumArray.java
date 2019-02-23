package com.binglian.SegmentTree;
class NumArray {

	private SegmentTree<Integer> segmentTree;
	
    public NumArray(int[] nums) {
    	if(nums.length>0){
    		Integer[] data=new Integer[nums.length];
    		for(int i=0;i<nums.length;i++)
    			data[i]=nums[i];//因为int不能直接转换Integer
    		segmentTree =new SegmentTree<>(data, (a,b)->a+b);
    	}
    }
    
    public int sumRange(int i, int j) {
    	if(segmentTree ==null){
    		throw new IllegalArgumentException("SegmentTree 是空的");
    	}
    	return segmentTree.query(i, j);
        
    }
}