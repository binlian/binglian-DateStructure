package com.binglian.HeapQueue;

public class MaxHeap<E extends Comparable<E>> {

	private Array<E> data;
	
	public MaxHeap(int capacity){
		data=new Array<E>(capacity);
	}
	
	public MaxHeap(){
		data =new Array<E>();
	}
	
	//���ض��е�Ԫ�ظ���
	public int size(){
		return data.getSize();
	}
	
	//����һ������ֵ����ʾ�����Ƿ�Ϊ��
	public boolean isEmpty(){
		return data.isEmpty();
	}
	
	//������ȫ�������������ʾ�У�һ�������������Ԫ�صĸ��׽ڵ������
	private int parent(int index){
		if(index == 0)
			throw new IllegalArgumentException("��һ������û�и��ڵ�");
		
		return (index -1)/2;
	}
	
	//������ȫ�������������ʾ�У�һ����������ʾ��Ԫ�ص����ӽڵ������
	private int leftChild(int index){
		return index*2+1;
	}
	
	//������ȫ�������������ʾ�У�һ����������ʾ��Ԫ�ص��Һ��ӽڵ������
	private int rightChild(int index){
		return index*2+2;
	}
	
	//��������Ԫ��
	public void add(E e){
		data.addLast(e);
		siftUp(data.getSize()-1);
	}
	
	//�ϸ�siftUp
	private void siftUp(int k){
		
		while(k >0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
			data.swap(k,parent(k));//����λ��
			k=parent(k);
		}
	}
	
	//�����е����Ԫ��
	public E findMax(){
		if(data.getSize() ==0)
			throw new IllegalArgumentException("û��Ԫ��");;
		return data.get(0);
	}
	
	//ȡ���������Ԫ��
	public E extractMax(){
		
		E ret =findMax();
		
		data.swap(0, data.getSize()-1);//��������Ԫ��
		data.removeLast();//ɾ�����һ��Ԫ��
		siftDown(0);//�¸����������������ƥ�� ֱ���ҵ����ϵ�λ��
		
		return ret;
	}
	
	//�¸� ���ϸ�һ����˼· ֻ������һֱѭ�����н���λ��
	private void siftDown(int k){
		
		//k����ڵ�С�����Ԫ�� ѭ������
		while(leftChild(k) < data.getSize()){
			
			int j=leftChild(k);
			if(j+1 <data.getSize() &&
					data.get(j+1).compareTo(data.get(j)) >0)//��ڵ�С���ҽڵ� ��������
				j=rightChild(k);
			//data[j]��leftChild��rightChild�����ֵ

			//�����ڵ㶼������ڵ���ҽڵ㶼���� λ�þ��ҵ��� ѭ������
			if(data.get(k).compareTo(data.get(j)) >=0)
				break;
			
			//����һֱ����
			data.swap(k, j);
			k=j;
		}
	}
}
