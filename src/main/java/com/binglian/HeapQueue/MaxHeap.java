package com.binglian.HeapQueue;

public class MaxHeap<E extends Comparable<E>> {

	private Array<E> data;
	
	public MaxHeap(int capacity){
		data=new Array<E>(capacity);
	}
	
	public MaxHeap(){
		data =new Array<E>();
	}
	
	//返回堆中的元素个数
	public int size(){
		return data.getSize();
	}
	
	//返回一个布尔值，表示堆中是否为空
	public boolean isEmpty(){
		return data.isEmpty();
	}
	
	//返回完全二叉树的数组表示中，一个索引所代表的元素的父亲节点的索引
	private int parent(int index){
		if(index == 0)
			throw new IllegalArgumentException("第一个索引没有父节点");
		
		return (index -1)/2;
	}
	
	//返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
	private int leftChild(int index){
		return index*2+1;
	}
	
	//返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
	private int rightChild(int index){
		return index*2+2;
	}
	
	//向堆中添加元素
	public void add(E e){
		data.addLast(e);
		siftUp(data.getSize()-1);
	}
	
	//上浮siftUp
	private void siftUp(int k){
		
		while(k >0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
			data.swap(k,parent(k));//交换位置
			k=parent(k);
		}
	}
	
	//看堆中的最大元素
	public E findMax(){
		if(data.getSize() ==0)
			throw new IllegalArgumentException("没有元素");;
		return data.get(0);
	}
	
	//取出堆中最大元素
	public E extractMax(){
		
		E ret =findMax();
		
		data.swap(0, data.getSize()-1);//交换最后的元素
		data.removeLast();//删除最后一个元素
		siftDown(0);//下浮，不断往下面进行匹配 直到找到符合的位置
		
		return ret;
	}
	
	//下浮 和上浮一样的思路 只是往下一直循环进行交换位置
	private void siftDown(int k){
		
		//k的左节点小于最大元素 循环条件
		while(leftChild(k) < data.getSize()){
			
			int j=leftChild(k);
			if(j+1 <data.getSize() &&
					data.get(j+1).compareTo(data.get(j)) >0)//左节点小于右节点 条件允许
				j=rightChild(k);
			//data[j]是leftChild和rightChild中最大值

			//当父节点都大于左节点和右节点都符合 位置就找到了 循环结束
			if(data.get(k).compareTo(data.get(j)) >=0)
				break;
			
			//否则一直交换
			data.swap(k, j);
			k=j;
		}
	}
}
