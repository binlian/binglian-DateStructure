package com.binglian.stack;


/**
 * ѭ������
 * @author binglian
 *
 * @param <E>
 */
public class LoopQueue<E>  {
	
	private E[] data;
	private int front,tail;//front ��ͷ tail��β
	private int size;//Ԫ�س���
	
	/**
	 * ���췽�� ���ɳ�ʼ������ 
	 * @param capacity
	 */
	public LoopQueue(int capacity){
		data=(E[])new Object[capacity+1];
		front=0;
		tail=0;
		size=0;
	}
	
	/**
	 * Ĭ�ϳ���Ϊ10�Ķ���
	 */
	public LoopQueue(){
		this(10);
	}
	
	/**
	 * ��Ϊѭ���������Ҫ���˷�һ���ռ� ���Լ�һ
	 * @return
	 */
	public int getCapacity(){
		return data.length-1;
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty(){
		return front == tail;//��β�Ͷ��������ôû��Ԫ��
	}
	
	/**
	 * ����Ԫ�س���
	 * @return
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * ��Ӳ���
	 */
	public void enqueue(E e){
		if((tail+1) % data.length ==front){//���tail+1 %data�ĳ��� ���ڶ�����ô������
			resize(getCapacity()*2);//����֮�󴴽��µĶ��в��� �µĶ���Ԫ�طŽ�ȥ
		}
		data[tail]=e;
		tail=(tail+1)%data.length;
		size++;
	}
	
	/**
	 * �Ƴ�Ԫ��
	 */
	public E dequeue(){
		if(isEmpty())
			throw new IllegalArgumentException("����Ϊ�գ�û��Ԫ�أ������Ƴ�");
		
		E ret=data[front];
		data[front]=null;
		front=(front+1) %data.length;
		size--;
		if(size == getCapacity() /4 && getCapacity() /2 !=0 )//�ж� �����Ƿ��ж������ռ� Ȼ�������С����
			resize(getCapacity()/2);
		return ret;
	}
	
	/**
	 * �鿴����Ԫ��
	 * @return
	 */
	public E getFron(){
		if(isEmpty())
			throw new IllegalArgumentException("����Ϊ�գ�û��Ԫ�أ�");
		return data[front];
	}
	/**
	 * �����µĶ��У����������ƽ�ȥ
	 * @param newCapacity
	 */
	private void resize(int newCapacity){
		E[] newData=(E[])new Object[newCapacity +1];//�����µĶ���
		
		//��ԭ�����е�Ԫ�أ��Ƶ��µĶ���
		for(int i=0;i<size;i++){
			newData[i]=data[(i+front)% data.length];//�ص�
		}
		data=newData;
		front=0;
		tail=size;
	}
	
	@Override
	public String toString(){
		StringBuilder res=new StringBuilder();
		res.append(String.format("Queue:size=%d,capacity=%d\n", size,getCapacity()));
		res.append("front[");
		for (int i = front; i !=tail;i=(i+1) %data.length) {
			res.append(data[i]);
			if((i+1)%data.length !=tail)
				res.append(",");
			
		}
		res.append("]tail");
		return res.toString();
	}
	
	public static void main(String[] args) {
		LoopQueue<Integer> queue=new LoopQueue<Integer>();
		for(int i=0;i<10;i++){
			queue.enqueue(i);
			System.out.println(queue);
			
			if(i%3 ==2){
				queue.dequeue();
				System.out.println(queue);
			}
		}
	}
}


