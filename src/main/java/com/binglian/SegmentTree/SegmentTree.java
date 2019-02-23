package com.binglian.SegmentTree;

import javax.management.Query;

/**
 * 线段树
 * @author binglian
 *
 * @param <E>
 */
public class SegmentTree<E>{
	
	private E[] tree;//把看为满线段树
	private E[] data;
	private Merger<E> merger;
	
	public SegmentTree(E[] arr,Merger<E> merger){
		
		this.merger=merger;//用户传过来的merger给了Merger构造
		data=(E[])new Object[arr.length];
		for(int i=0;i<arr.length;i++)
			data[i]=arr[i];
		tree=(E[])new Object[4*arr.length];//线段树的最大空间 4*n
		buildSegmentTree(0,0,data.length-1);
	}
	
	//在treeIndex的位置创建表示区间[l...r]的线段树
	private void buildSegmentTree(int treeIndex,int l,int r){
		
		if(l==r){//相等 一个位置就是左边或者最后一个
			tree[treeIndex]=data[l];
			return ;
		}
		
		int leftTreeIndex=leftChild(treeIndex);//左边节点
		int rightTreeIndex=rightChild(treeIndex);//右节点
		
		int mid=l+(r-l)/2;// (l+r)/2可以这样写，但是为了防止溢出l+(r-l)/2
		buildSegmentTree(leftTreeIndex, l, mid);//左边节点
		buildSegmentTree(rightTreeIndex, mid+1, r);//右边节点
		
		
		tree[treeIndex]=merger.merge(data[leftTreeIndex],data[r]);
	}
	public int getSize(){
		return data.length;
	}
	
	public E get(int index){
		if(index <0 || index >=data.length)
			throw new IllegalArgumentException("下标报错");
		
		return data[index];
	}
	
	//返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
	private int leftChild(int index){
		return 2*index+1;
	}
	
	//返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
	private int rightChild(int index){
		return 2*index+2;
	}
	
	
	//返回区间[queryL,queryR]的值
	public E query(int queryL,int queryR){
		if(queryL <0 || queryL >=data.length || queryL >queryR)
			throw new IllegalArgumentException("下标边界报错");
		
		return query(0, 0,data.length-1,queryL,queryR);
	}
	
	/**
	 * 在以treeID为根的线段树中[l...r]的范围里,搜索区间[queryL...queryR]的值
	 * @param treeIndex	当前的位置 默认是从0
	 * @param l	左边边界
	 * @param r	右边边界
	 * @param queryL	要搜索的左边界
	 * @param queryR	要搜索的右边界
	 * @return
	 */
	private E query(int treeIndex,int l,int r,int queryL,int queryR){
		if(l==queryL && r==queryR)
			return tree[treeIndex];
		
		int mid=l+(r-l)/2;
		int leftTreeIndex=leftChild(treeIndex);//当前位置的左节点
		int rightTreeIndex=rightChild(treeIndex);//当前位置的右节点
		
		//我的理解是缩小搜索范围 二分搜索
		if(queryL >=mid+1)
			return query(rightTreeIndex, mid+1,r,queryL,queryR);
		else if (queryR <=mid) {
			return query(leftTreeIndex, l,mid,queryL,queryR);
		}
		
		
		E leftResult=query(leftTreeIndex, l, mid, queryL, mid);
		E rightResult=query(rightTreeIndex, mid+1, r, mid+1, queryR);
		
		return merger.merge(leftResult, rightResult);
	}
	
	
	//将index位置的值,更新为e
	public void set(int index,E e){
		if(index < 0 || index >=data.length)
			throw new IllegalAccessError("下标错误。不符合");
		
		data[index] =e;
		set(0,0,data.length-1,index,e);
	}
	
	/**
	 * 在以treeIndex为根的线段树中更新的index的值为e
	 * @param treeIndex 当前位置
	 * @param l
	 * @param r
	 * @param index
	 * @param e
	 */
	
	//在以trrIndex为根的线段中线段树中更index的值为e
	private void set(int treeIndex,int l,int r,int index,E e){
		
		if(l==r){//找到了 进行更新
			tree[treeIndex]=e;
			return;
		}
		
		int mid=l+(r-l)/2;
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		if(index >=mid+1)
			set(rightTreeIndex, mid+1,r,index,e);
		else
			set(leftTreeIndex, l, mid, index, e);
	}
	
	@Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
