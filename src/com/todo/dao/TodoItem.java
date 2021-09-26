package com.todo.dao;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String category;
    private String due_date;
    private String current_date;


    public TodoItem(String title, String desc, String category, String due_date){
        this.title=title;
        this.desc=desc;
        this.category = category;
        this.due_date = due_date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
    	this.current_date = format.format(new Date());
    }
    
    public TodoItem(String title, String desc, String category, String due_date, String current_date) {
    	this.title = title;
    	this.desc = desc;
    	this.category = category;
        this.due_date = due_date;
    	this.current_date = current_date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getCategory() {
    	return category;
    }

    public void setCategory(String category) {
    	this.category = category;
    }
    
    public String getDueDate() {
    	return due_date;
    }

    public void setDueDate(String due_date) {
    	this.due_date = due_date;
    }
    
    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(Date current_date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
    	this.current_date = format.format(new Date());
    }
    
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }
}
