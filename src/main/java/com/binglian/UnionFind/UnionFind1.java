package com.binglian.UnionFind;

/**
 * 第一版本的并查集(Union-Find)
 * @author binglian
 *
 */
public class UnionFind1 implements UF {

	private int[] id;
	
	/**
	 * 初始化
	 * @param size
	 */
	public UnionFind1(int size){
		
		id=new int[size];
		
		for(int i=0;i<id.length;i++)
			id[i]=i;
	}
	
	@Override
	public int getSize(){
		return id.length;
	}
	
	//查找元素p所对应的集合编号
	private int find(int p){
		if(p < 0 && p>=id.length)
			throw new IllegalArgumentException("边界不合法");
		return id[p];
	}
	
	//查看元素p和元素q是否所属一个集合
	@Override
	public boolean isConnected(int p,int q){
		return find(p)==find(q);
	}
	
	//合并元素p和元素q所属的集合
	@Override
	public void unionElements(int p,int q){
		
		int pId=find(p);
		int qId=find(q);
		
		if(pId	== qId)
			return ;
		
		for(int i=0;i<id.length;i++)
			if(id[i]==pId)//如果id[i]==qId 那么id[i]=pId;
				id[i]=qId;
	}
}
