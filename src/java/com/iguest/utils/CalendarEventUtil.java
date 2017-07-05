/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.utils;

import java.util.Date;
import org.zkoss.bind.annotation.Immutable;
import org.zkoss.calendar.impl.SimpleCalendarEvent;

/**
 *
 * @author dewa
 */
public class CalendarEventUtil extends SimpleCalendarEvent {
    private static final long serialVersionUID = 1L;
 
    public CalendarEventUtil(Date beginDate, Date endDate, String headerColor, String contentColor, String content) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setContent(content);
        setBeginDate(beginDate);
        setEndDate(endDate);
    }
 
    public CalendarEventUtil(Date beginDate, Date endDate, String headerColor, String contentColor, String content,
            String title) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setContent(content);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
    }
 
    public CalendarEventUtil(Date beginDate, Date endDate, String headerColor, String contentColor, String content,
            String title, boolean locked) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setContent(content);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setLocked(locked);
    }
     
    public CalendarEventUtil() {
        setHeaderColor("#FFFFFF");
        setContentColor("#000000");
    }
     
    @Override
    public Date getBeginDate() {
        return super.getBeginDate();
    }
 
    @Override
    public Date getEndDate() {
        return super.getEndDate();
    }
}
