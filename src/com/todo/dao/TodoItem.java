package com.todo.dao;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date;
    String datePattern = "yyyy/MM/dd kk:mm:ss";
    String dateString;


    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.current_date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
    	dateString = format.format(current_date);
    }
    
    public TodoItem(String title, String desc, String dateString) {
    	this.title = title;
    	this.desc = desc;
    	this.dateString = dateString;
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

    public String getCurrent_date() {
        return dateString;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = current_date;
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
    	dateString = format.format(current_date);
    }
    
    public String toSaveString() {
    	return title + "##" + desc + "##" + dateString + "\n";
    }
}
