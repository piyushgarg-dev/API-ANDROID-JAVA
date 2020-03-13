package dev.piyushgarg.jsonapi;

public class Blog {
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    private String id;
    private String title;
    private String body;


    public Blog(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }




}
