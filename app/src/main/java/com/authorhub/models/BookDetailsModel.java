package com.authorhub.models;

public class BookDetailsModel {

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getBookDesc() {
        return BookDesc;
    }

    public void setBookDesc(String bookDesc) {
        BookDesc = bookDesc;
    }

    public String getBookCat() {
        return BookCat;
    }

    public void setBookCat(String bookCat) {
        BookCat = bookCat;
    }

    public String getAuthorImg() {
        return AuthorImg;
    }

    public void setAuthorImg(String authorImg) {
        AuthorImg = authorImg;
    }

    String Sid,BookName,BookImage,AuthorName,BookDesc,BookCat,AuthorImg;
}
