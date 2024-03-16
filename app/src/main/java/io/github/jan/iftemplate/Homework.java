package io.github.jan.iftemplate;

/**
 * Aufgabe 1:
 * Fülle die Klasse Homework mit den notwendigen Attributen und Methoden.
 */
public class Homework {

    private final String content;
    private final String dueTime;
    private boolean done;

    public Homework(String content, String dueTime, boolean done) {
        this.content = content;
        this.dueTime = dueTime;
        this.done = done;
    }

    public String getContent() {
        return content;
    }

    public String getDueTime() {
        return dueTime;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
