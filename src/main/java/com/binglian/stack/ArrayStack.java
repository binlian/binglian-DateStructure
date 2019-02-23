package com.binglian.stack;

public class ArrayStack<E> implements Stack<E> {
	Array<E> array;
	
	public ArrayStack(int capacity){
		array=new Array<E>(capacity);
	}
	public  ArrayStack() {
		array=new Array<E>();
	}
	
	public int getSize() {
		
		return array.getSize();
	}
	public boolean isEmpty() {
		return array.isEmpty();
	}
	public int getCapacity(){
		return array.getCapacity();
	}
	
	public void push(E e) {
		array.addLast(e);
	}
	public E pop() {
		return array.removeLast();
	}
	public E peek() {
		return array.getLast();
	}
	
	@Override
	public String toString(){
		StringBuilder res=new StringBuilder();
		res.append("Stack:");
		res.append('[');
		for(int i=0;i<array.getSize();i++){
			res.append(array.get(i));
			if(i != array.getSize()-1)
				res.append(", ");
		}
		res.append("] top");
		return res.toString();
	}
	
}
