package com.binglian.HashCode;

import java.util.HashMap;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		
		int a=42;
		System.out.println(((Integer)a).hashCode());
		
		int b=-42;
		System.out.println(((Integer)b).hashCode());
		
		double c=3.1412;
		System.out.println(((Double)c).hashCode());
		
		String d="binglian";
		System.out.println(d.hashCode());
		
		
		
		Student student=new Student(3, 2, "BINGLIAN", "xiaoxin");
		System.err.println(student.hashCode());
		
		
		HashSet<Student> set=new HashSet<>();
		set.add(student);
		
		
		HashMap<Student, Integer> scores=new HashMap<>();
		scores.put(student, 100);
		
		Student student2=new Student(3, 2, "BINGLIAN","xiaoxin");
		System.out.println(student2.hashCode());
	}

}