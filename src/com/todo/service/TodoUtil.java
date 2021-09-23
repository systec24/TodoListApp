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

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[ Add Item ]");
		System.out.print("Enter the title > ");
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.print("There exists a duplicating title.");
			return;
		}
		
		System.out.print("Enter the description > ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("Item added!\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[ Delete Item ]");
		System.out.print("Enter the title of item to remove > ");
		String title = sc.nextLine();
		
		boolean check = false;
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				check = true;
				break;
			}
		}
		if(check) {
			System.out.println("Item deleted!\n");
		} else {
			System.out.println("Given item could not be found.\n");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[ Edit Item ]");
		System.out.print("Enter the title of the item you want to update > ");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("The given title doesn't exist!");
			return;
		}

		System.out.print("Enter the new title of the item > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("There exists a duplicating title.");
			return;
		}
		
		System.out.print("Enter the new description > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("Item updated!\n");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n[ To-do List ]");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc() + " - " + item.getCurrent_date());
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
			System.out.print("All data saved.");
			
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
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				
				TodoItem item = new TodoItem(title, desc, date);
				l.addItem(item);
				n++;
			}
			
			br.close();
			System.out.println(n + " item(s) read.");

		} catch(FileNotFoundException e) {
			System.out.println("todolist.txt was not found.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
