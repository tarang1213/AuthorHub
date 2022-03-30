package com.authorhub.models;

public class AuthorDetailsModel {
    String Sid,BookName,AuthorImage,AuthorName;

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

    public String getAuthorImage() {
        return AuthorImage;
    }

    public void setAuthorImage(String authorImage) {
        AuthorImage = authorImage;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }
}
