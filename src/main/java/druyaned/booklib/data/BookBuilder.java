package druyaned.booklib.data;

import java.time.LocalDate;

public class BookBuilder {
    
    private Long id;
    private String name;
    private String author;
    private String imageURL;
    private String description;
    private LocalDate date;
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void reset() {
        id = null;
        name = null;
        author = null;
        imageURL = null;
        description = null;
        date = null;
    }
    
    public Book result() {
        if (
                id == null ||
                name == null ||
                author == null ||
                imageURL == null ||
                description == null ||
                date == null
        ) {
            throw new IllegalStateException("all book's fields must be not null");
        }
        return new Book(id, name, author, imageURL, description, date);
    }
    
}
