package by.petrovlad.ntickets.model.dto;

public class TicketDTO {
    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private Boolean showWarning;
    private Integer readingsCount;
    private String hash;

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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


}
