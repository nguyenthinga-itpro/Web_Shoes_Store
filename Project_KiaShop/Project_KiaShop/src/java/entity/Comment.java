/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author MSI GTX
 */
public class Comment {
    private int commentID;
    private String commentMsg;
    private Date commentDate;
    private int productID;
    private int accountID;
    private int rate;
    private int parentID;

    public Comment() {
    }

    public Comment(int commentID, String commentMsg, Date commentDate, int productID, int accountID, int rate, int parentID) {
        this.commentID = commentID;
        this.commentMsg = commentMsg;
        this.commentDate = commentDate;
        this.productID = productID;
        this.accountID = accountID;
        this.rate = rate;
        this.parentID = parentID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentMsg() {
        return commentMsg;
    }

    public void setCommentMsg(String commentMsg) {
        this.commentMsg = commentMsg;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
    
    
}
