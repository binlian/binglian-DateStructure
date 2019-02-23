package com.binglian.UnionFind;

/**
 * 第三个版本 Quick Union 基于的size优化
 * @author binglian
 *
 */
public class UnionFind3 implements UF{

	private int[] parent;
	private int[] sz;	//sz[i]表示以根的集合中元素个数
	
	
	
	public UnionFind3(int size){
		
		parent=new int[size];
		
		for(int i=0;i<size;i++){
			parent[i]=i;
			sz[i]=1;
		}
	}
	
	@Override
	public int getSize(){
		return parent.length;
	}

	
	
	/**
	 * 查找过程，查找元素p所对应的集合编号
	 * O(h)复杂度，h为树的高度
	 * @param p
	 * @return
	 */
	private int find(int p){
		
		if(p < 0 && p>=parent.length)
			throw new IllegalArgumentException("下标不合法");
		
		
		while(p !=parent[p]){
			parent[p]=parent[parent[p]];
			p=parent[p];
		}
		return p;
	}

	
	//查找元素p和元素q是否所属一个集合
	@Override
	public boolean isConnected(int p,int q){
		return find(p) == find(q);
	}
	
	/**
	 * 合并元素p和元素q所属的集合
	 * O(h)复杂度，h为树的高度
	 */
	@Override
	public void unionElements(int p,int q){
		int pRoot=find(p);
		int qRoot=find(q);
		
		if(pRoot == qRoot)
			return ;
		
		//根据两个元素所在树的元素个数不同判断合并方向
		//将元素个数少的集合合并到元素个数多的集合上
		if(sz[pRoot] < sz[qRoot]){//pRoot 小于的话 就指向pRoot中
			parent[pRoot] = qRoot;
			sz[qRoot] +=sz[pRoot];//计算元素多少
			
		}else{//反之亦然	sz[qRoot]<=sz[pRoot]
			parent[qRoot] =pRoot;
			sz[pRoot] +=sz[qRoot];//计算元素多少
		}
		
	}
}
