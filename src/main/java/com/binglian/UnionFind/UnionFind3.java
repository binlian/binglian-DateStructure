package com.binglian.UnionFind;

/**
 * �������汾 Quick Union ���ڵ�size�Ż�
 * @author binglian
 *
 */
public class UnionFind3 implements UF{

	private int[] parent;
	private int[] sz;	//sz[i]��ʾ�Ը��ļ�����Ԫ�ظ���
	
	
	
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
	 * ���ҹ��̣�����Ԫ��p����Ӧ�ļ��ϱ��
	 * O(h)���Ӷȣ�hΪ���ĸ߶�
	 * @param p
	 * @return
	 */
	private int find(int p){
		
		if(p < 0 && p>=parent.length)
			throw new IllegalArgumentException("�±겻�Ϸ�");
		
		
		while(p !=parent[p]){
			parent[p]=parent[parent[p]];
			p=parent[p];
		}
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
		
		//��������Ԫ����������Ԫ�ظ�����ͬ�жϺϲ�����
		//��Ԫ�ظ����ٵļ��Ϻϲ���Ԫ�ظ�����ļ�����
		if(sz[pRoot] < sz[qRoot]){//pRoot С�ڵĻ� ��ָ��pRoot��
			parent[pRoot] = qRoot;
			sz[qRoot] +=sz[pRoot];//����Ԫ�ض���
			
		}else{//��֮��Ȼ	sz[qRoot]<=sz[pRoot]
			parent[qRoot] =pRoot;
			sz[pRoot] +=sz[qRoot];//����Ԫ�ض���
		}
		
	}
}
