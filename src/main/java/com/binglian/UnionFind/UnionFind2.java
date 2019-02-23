package com.binglian.UnionFind;

/**
 * �ڶ����汾 Quick Union
 * @author binglian
 *
 */
public class UnionFind2 implements UF{

	private int[] parent;
	
	public UnionFind2(int size){
		
		parent=new int[size];
		
		for(int i=0;i<size;i++)
			parent[i] =i;
	}
	
	@Override
	public int getSize(){
		return parent.length;
	}

	
	
	/**
	 * ���ҹ��̣�����Ԫ��p����Ӧ�ļ��ϱ��
	 * O(h)���Ӷȣ�hΪ���ĸ߶�
	 * @param p
	 * @return
	 */
	private int find(int p){
		
		if(p < 0 && p>=parent.length)
			throw new IllegalArgumentException("�±겻�Ϸ�");
		
		
		while(p !=parent[p])
			p=parent[p];
		return p;
	}

	
	//����Ԫ��p��Ԫ��q�Ƿ�����һ������
	@Override
	public boolean isConnected(int p,int q){
		return find(p) == find(q);
	}
	
	/**
	 * �ϲ�Ԫ��p��Ԫ��q�����ļ���
	 * O(h)���Ӷȣ�hΪ���ĸ߶�
	 */
	@Override
	public void unionElements(int p,int q){
		int pRoot=find(p);
		int qRoot=find(q);
		
		if(pRoot == qRoot)
			return ;
		
		parent[pRoot]=qRoot;
	}
}
