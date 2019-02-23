package com.binglian.SegmentTree;

import javax.management.Query;

/**
 * �߶���
 * @author binglian
 *
 * @param <E>
 */
public class SegmentTree<E>{
	
	private E[] tree;//�ѿ�Ϊ���߶���
	private E[] data;
	private Merger<E> merger;
	
	public SegmentTree(E[] arr,Merger<E> merger){
		
		this.merger=merger;//�û���������merger����Merger����
		data=(E[])new Object[arr.length];
		for(int i=0;i<arr.length;i++)
			data[i]=arr[i];
		tree=(E[])new Object[4*arr.length];//�߶��������ռ� 4*n
		buildSegmentTree(0,0,data.length-1);
	}
	
	//��treeIndex��λ�ô�����ʾ����[l...r]���߶���
	private void buildSegmentTree(int treeIndex,int l,int r){
		
		if(l==r){//��� һ��λ�þ�����߻������һ��
			tree[treeIndex]=data[l];
			return ;
		}
		
		int leftTreeIndex=leftChild(treeIndex);//��߽ڵ�
		int rightTreeIndex=rightChild(treeIndex);//�ҽڵ�
		
		int mid=l+(r-l)/2;// (l+r)/2��������д������Ϊ�˷�ֹ���l+(r-l)/2
		buildSegmentTree(leftTreeIndex, l, mid);//��߽ڵ�
		buildSegmentTree(rightTreeIndex, mid+1, r);//�ұ߽ڵ�
		
		
		tree[treeIndex]=merger.merge(data[leftTreeIndex],data[r]);
	}
	public int getSize(){
		return data.length;
	}
	
	public E get(int index){
		if(index <0 || index >=data.length)
			throw new IllegalArgumentException("�±걨��");
		
		return data[index];
	}
	
	//������ȫ�������������ʾ�У�һ����������ʾ��Ԫ�ص����ӽڵ������
	private int leftChild(int index){
		return 2*index+1;
	}
	
	//������ȫ�������������ʾ�У�һ����������ʾ��Ԫ�ص��Һ��ӽڵ������
	private int rightChild(int index){
		return 2*index+2;
	}
	
	
	//��������[queryL,queryR]��ֵ
	public E query(int queryL,int queryR){
		if(queryL <0 || queryL >=data.length || queryL >queryR)
			throw new IllegalArgumentException("�±�߽籨��");
		
		return query(0, 0,data.length-1,queryL,queryR);
	}
	
	/**
	 * ����treeIDΪ�����߶�����[l...r]�ķ�Χ��,��������[queryL...queryR]��ֵ
	 * @param treeIndex	��ǰ��λ�� Ĭ���Ǵ�0
	 * @param l	��߽߱�
	 * @param r	�ұ߽߱�
	 * @param queryL	Ҫ��������߽�
	 * @param queryR	Ҫ�������ұ߽�
	 * @return
	 */
	private E query(int treeIndex,int l,int r,int queryL,int queryR){
		if(l==queryL && r==queryR)
			return tree[treeIndex];
		
		int mid=l+(r-l)/2;
		int leftTreeIndex=leftChild(treeIndex);//��ǰλ�õ���ڵ�
		int rightTreeIndex=rightChild(treeIndex);//��ǰλ�õ��ҽڵ�
		
		//�ҵ��������С������Χ ��������
		if(queryL >=mid+1)
			return query(rightTreeIndex, mid+1,r,queryL,queryR);
		else if (queryR <=mid) {
			return query(leftTreeIndex, l,mid,queryL,queryR);
		}
		
		
		E leftResult=query(leftTreeIndex, l, mid, queryL, mid);
		E rightResult=query(rightTreeIndex, mid+1, r, mid+1, queryR);
		
		return merger.merge(leftResult, rightResult);
	}
	
	
	//��indexλ�õ�ֵ,����Ϊe
	public void set(int index,E e){
		if(index < 0 || index >=data.length)
			throw new IllegalAccessError("�±���󡣲�����");
		
		data[index] =e;
		set(0,0,data.length-1,index,e);
	}
	
	/**
	 * ����treeIndexΪ�����߶����и��µ�index��ֵΪe
	 * @param treeIndex ��ǰλ��
	 * @param l
	 * @param r
	 * @param index
	 * @param e
	 */
	
	//����trrIndexΪ�����߶����߶����и�index��ֵΪe
	private void set(int treeIndex,int l,int r,int index,E e){
		
		if(l==r){//�ҵ��� ���и���
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
