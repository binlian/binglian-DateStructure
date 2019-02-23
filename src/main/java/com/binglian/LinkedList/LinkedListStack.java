package com.binglian.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

	private LinkedList<E> list;
	

	public LinkedListStack(){
		list=new LinkedList<E>();
	}
	
	public int getSize() {
		return list.getSize();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E e) {
		list.addHead(e);
	}
	

	public E pop() {

		return list.removeFirst();
	}

	public E peek() {

		return list.getFirst();
	}

	@Override
	public String toString(){
		StringBuilder res=new StringBuilder();
		res.append("Stack:top");
		res.append(list);
		return res.toString();
	}
	
	public static void main(String[] args){
		LinkedListStack<Integer> stack=new LinkedListStack<Integer>();
		
		for(int i=0;i<5;i++){
			stack.push(i);
			System.out.println(stack);
		}
		stack.pop();
		System.out.println(stack);
	}
}
