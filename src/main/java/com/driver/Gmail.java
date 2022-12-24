package com.driver;
import com.sun.source.tree.ArrayAccessTree;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    ArrayList<Mail> inbox = new ArrayList<>(); //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    ArrayList<Mail> trash = new ArrayList<>(); //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    public Gmail(String emailId, int inboxCapacity) {
            super(emailId);
            this.inboxCapacity = inboxCapacity;
    }

    public Gmail(String emailId) {
        super(emailId);
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size() == getInboxCapacity()){
            trash.add(inbox.remove(inbox.size()-1));
        }
        inbox.add(0,new Mail(date,sender,message));

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i =0 ; i < inbox.size();i++)
        {
            Mail mail = inbox.get(i);

            if(mail.message.equals(message))
            {
                trash.add(new Mail(mail.date,mail.sender,mail.message));
                inbox.remove(mail);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
       if(inbox.size() == 0)
           return null;

       Mail mail = inbox.get(0);
       return mail.message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size() == 0)
            return null;
        Mail mail = inbox.get(inbox.size()-1);
        return mail.message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int mailBetweenDates = 0;
        for(int i=0; i<inbox.size(); i++){
            Mail mail = inbox.get(i);
            if(mail.date.compareTo(start) >= 0 && mail.date.compareTo(end) <= 0){
                mailBetweenDates++;
            }
        }
        return mailBetweenDates;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
            trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
