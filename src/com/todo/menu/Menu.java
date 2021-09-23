package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("================ To-do List ================");
        System.out.println("add -  Add a new item");
        System.out.println("del -  Delete an existing item");
        System.out.println("edit - Update an item");
        System.out.println("ls - List all items");
        System.out.println("ls_name_asc - Sort the list by name");
        System.out.println("ls_name_desc - Sort the list by name");
        System.out.println("ls_date - Sort the list by date");
        System.out.println("help - Shows the menu");
        System.out.println("exit - Exit (Or press escape key to exit)");
    }
    
    public static void prompt() {
    	System.out.print("Enter your choice > ");
    }
}
