package com.binglian.UnionFind;

/**
 * ���ĸ��汾 Quick Union ���ڵ�size�Ż�
 * rank[i]��ʾ���ڵ�Ϊi�����ĸ߶�
 * @author binglian
 *
 */
public class UnionFind4 implements UF{

	private int[] parent;
	private int[] rank;	//rank[i]��ʾ��iΪ���ļ�������ʾ�����Ĳ���
	
	
	
	public UnionFind4(int size){
		
		parent=new int[size];
		
		for(int i=0;i<size;i++){
			parent[i]=i;
			rank[i]=1;
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
		
		//��������Ԫ����������Ԫ�ظ�����ͬ�жϺϲ�����
		//��Ԫ�ظ����ٵļ��Ϻϲ���Ԫ�ظ�����ļ�����
		if(rank[pRoot] < rank[qRoot])//���pRootԪ�ظ߶�С�� ��ָ��ߵ�Ԫ�� pRoot=qRoot
			parent[pRoot] = qRoot;//pRoot ָ��qRoot
		else if(rank[qRoot] < rank[pRoot])//��֮��Ȼ qС��p qRoot��ָ��pRoot parent[qRoot]=parent[pRoot]
			parent[qRoot]=pRoot;//qRoot ָ��pRoot
		else{//rank[qRoot] == rank[pRoot]
			parent[qRoot]=pRoot;
			rank[pRoot]+=1;//�߶ȼ�1
		}
	}
}
