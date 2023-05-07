package com.mousuf.rarestem.tableViewTest;

public class Person {
    private String fn;
    private String ln;
    private String o;

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public void setOrigin(String o) {
        this.o = o;
    }

    public String getLn() {
        return ln;
    }

    public String getO() {
        return o;
    }

    public Person(String fn, String ln, String o) {
        this.fn = fn;
        this.ln = ln;
        this.o = o;
    }
}