package by.petrovlad.ntickets.model;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_user_id", nullable = false)
    private Long authorId;

    @Column(name = "t_title", nullable = false)
    private String title;

    @Column(name = "t_content", nullable = false)
    private String content;

    @Column(name = "t_show_warning", nullable = false)
    private Boolean showWarning;

    @Column(name = "t_reads_count", nullable = false)
    private Integer readingsCount;
    // expiration date

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getShowWarning() {
        return showWarning;
    }

    public void setShowWarning(Boolean showWarning) {
        this.showWarning = showWarning;
    }

    public Integer getReadingsCount() {
        return readingsCount;
    }

    public void setReadingsCount(Integer readingsCount) {
        this.readingsCount = readingsCount;
    }

    public Ticket() {}

    public Ticket(Long authorId, String title, String content, Boolean showWarning, Integer readingsCount) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.showWarning = showWarning;
        this.readingsCount = readingsCount;
    }
}
