package net.engineeringdigest.journalApp.Entity;

import org.springframework.stereotype.Component;

@Component
public class JournalEntry {
    private Long id;
    private String tittle;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
