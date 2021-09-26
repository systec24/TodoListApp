package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[항목 추가]");
		System.out.print("제목 > ");
		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.print("제목이 중복됩니다!");
			return;
		}
		
		System.out.print("카테고리 > ");
		category = sc.nextLine();
		
		System.out.print("내용 > ");
		desc = sc.nextLine();
		
		System.out.print("마감일자 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("항목 추가 완료!\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[항목 삭제]");
		System.out.print("삭제할 항목의 번호를 입력하시오 > ");
		int num = sc.nextInt();
		
		TodoItem item = l.get(num - 1);
		System.out.println(num + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDueDate() + " - " + item.getCurrent_date());
		
		System.out.print("위 항목을 삭제하시겠습니까? (Y/N) > ");
		String check = sc.next();
		
		if(check.equals("Y") || check.equals("y")) {
			l.deleteItem(item);
			System.out.println("항목 삭제 완료!\n");
		} else {
			System.out.println("항목 삭제 취소.\n");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[항목 수정]");
		System.out.print("수정할 항목의 번호를 입력하시오 > ");
		int num = sc.nextInt();
		if (num > l.size()) {
			System.out.println("해당 항목이 존재하지 않습니다.");
			return;
		}
		
		TodoItem item = l.get(num - 1);
		System.out.println(num + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDueDate() + " - " + item.getCurrent_date());

		sc.nextLine();
		
		System.out.print("새 제목 > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.nextLine().trim();
		
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		String new_due_date = sc.nextLine().trim();
		
		l.deleteItem(item);
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		l.addItem(t);
		
		System.out.println("항목 수정 완료!\n");
		
	}
	
	public static void find(TodoList l, String search) {
		int i = 1;
		int count = 0;
		System.out.println();
		for(TodoItem item : l.getList()) {
			if(item.getCategory().contains(search) || item.getTitle().contains(search) || item.getDesc().contains(search) || item.getDueDate().contains(search) || item.getCurrent_date().contains(search)) {
				System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDueDate() + " - " + item.getCurrent_date());
				count++;
			}
			i++;
		}
		
		System.out.println("총 " + count + "개의 항목을 찾았습니다.\n");
	}
	
	public static void find_cate(TodoList l, String search) {
		int i = 1;
		int count = 0;
		System.out.println();
		for(TodoItem item : l.getList()) {
			if(item.getCategory().equals(search)) {
				System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDueDate() + " - " + item.getCurrent_date());
				count++;
			}
			i++;
		}
		
		System.out.println("총 " + count + "개의 항목을 찾았습니다.\n");
	}
	
	public static void listCate(TodoList l) {
		Set<String> category = new HashSet<>();
		
		for(TodoItem item : l.getList()) {
			category.add(item.getCategory());
		}
		
		int i = 0;
		Iterator<String> iter = category.iterator();
		System.out.println();
		while(iter.hasNext()) {
			System.out.print(iter.next());
			if(iter.hasNext()) {
				System.out.print(" / ");
			}
			i++;
		}
		
		System.out.println("\n총 " + i + "개의 카테고리가 등록되어 있습니다.\n");
	}

	public static void listAll(TodoList l) {
		System.out.println("\n[전체 목록, 총 " + l.size() + "개]");
		int i = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDueDate() + " - " + item.getCurrent_date());
			i++;
		}
		System.out.println();
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			
			for(TodoItem item : l.getList()) {
				bw.write(item.toSaveString());
			}
			
			bw.flush();
			bw.close();
			System.out.print("모든 데이터가 저장되었습니다.");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			int n = 0;
			String oneline;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String date = st.nextToken();
				
				TodoItem item = new TodoItem(title, desc, category, due_date, date);
				l.addItem(item);
				n++;
			}
			
			br.close();
			System.out.println(n + " 개의 항목을 읽었습니다.");

		} catch(FileNotFoundException e) {
			System.out.println("todolist.txt 파일이 없습니다.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
