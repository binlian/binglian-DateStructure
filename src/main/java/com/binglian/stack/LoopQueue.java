package com.binglian.stack;


/**
 * 循环队列
 * @author binglian
 *
 * @param <E>
 */
public class LoopQueue<E>  {
	
	private E[] data;
	private int front,tail;//front 队头 tail队尾
	private int size;//元素长度
	
	/**
	 * 构造方法 生成初始化队列 
	 * @param capacity
	 */
	public LoopQueue(int capacity){
		data=(E[])new Object[capacity+1];
		front=0;
		tail=0;
		size=0;
	}
	
	/**
	 * 默认长度为10的队列
	 */
	public LoopQueue(){
		this(10);
	}
	
	/**
	 * 因为循环队列最后要被浪费一个空间 所以减一
	 * @return
	 */
	public int getCapacity(){
		return data.length-1;
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty(){
		return front == tail;//队尾和队首相等那么没有元素
	}
	
	/**
	 * 队列元素长度
	 * @return
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * 入队操作
	 */
	public void enqueue(E e){
		if((tail+1) % data.length ==front){//如果tail+1 %data的长度 等于队首那么就满了
			resize(getCapacity()*2);//满了之后创建新的队列并吧 新的队列元素放进去
		}
		data[tail]=e;
		tail=(tail+1)%data.length;
		size++;
	}
	
	/**
	 * 移除元素
	 */
	public E dequeue(){
		if(isEmpty())
			throw new IllegalArgumentException("队列为空，没有元素，可以移除");
		
		E ret=data[front];
		data[front]=null;
		front=(front+1) %data.length;
		size--;
		if(size == getCapacity() /4 && getCapacity() /2 !=0 )//判断 队列是否有多余空与空间 然后进行缩小队列
			resize(getCapacity()/2);
		return ret;
	}
	
	/**
	 * 查看队首元素
	 * @return
	 */
	public E getFron(){
		if(isEmpty())
			throw new IllegalArgumentException("队列为空，没有元素，");
		return data[front];
	}
	/**
	 * 创建新的队列，并把数据移进去
	 * @param newCapacity
	 */
	private void resize(int newCapacity){
		E[] newData=(E[])new Object[newCapacity +1];//创建新的队列
		
		//吧原来队列的元素，移到新的队列
		for(int i=0;i<size;i++){
			newData[i]=data[(i+front)% data.length];//重点
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


