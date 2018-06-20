package model;

public class Invitation {
    private String author;
    private String date;
    private String content;
    private String head;
    private boolean iscollected;

    public Invitation(String author, String date, String content, String head, boolean iscollected) {
        this.author = author;
        this.date = date;
        this.content = content;
        this.iscollected = iscollected;
        this.head = head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHead() {
        return head;
    }

    public void setIscollected(boolean iscollected) {
        this.iscollected = iscollected;
    }

    public boolean isIscollected() {
        return iscollected;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
