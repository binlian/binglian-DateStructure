package com.binglian.UnionFind;

/**
 * ��һ�汾�Ĳ��鼯(Union-Find)
 * @author binglian
 *
 */
public class UnionFind1 implements UF {

	private int[] id;
	
	/**
	 * ��ʼ��
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
	
	//����Ԫ��p����Ӧ�ļ��ϱ��
	private int find(int p){
		if(p < 0 && p>=id.length)
			throw new IllegalArgumentException("�߽粻�Ϸ�");
		return id[p];
	}
	
	//�鿴Ԫ��p��Ԫ��q�Ƿ�����һ������
	@Override
	public boolean isConnected(int p,int q){
		return find(p)==find(q);
	}
	
	//�ϲ�Ԫ��p��Ԫ��q�����ļ���
	@Override
	public void unionElements(int p,int q){
		
		int pId=find(p);
		int qId=find(q);
		
		if(pId	== qId)
			return ;
		
		for(int i=0;i<id.length;i++)
			if(id[i]==pId)//���id[i]==qId ��ôid[i]=pId;
				id[i]=qId;
	}
}
