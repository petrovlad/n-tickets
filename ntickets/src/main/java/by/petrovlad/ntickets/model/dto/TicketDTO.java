package by.petrovlad.ntickets.model.dto;

import by.petrovlad.ntickets.model.entity.Ticket;

import java.util.Objects;

public class TicketDTO {
    private Long authorId;
    private String title;
    private String content;
    private Boolean showWarning;
    private Integer readingsCount;
    private String hash;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TicketDTO ticket = (TicketDTO) obj;
        return authorId.equals(ticket.authorId)
                && title.equals(ticket.title)
                && content.equals(ticket.content)
                && readingsCount.equals(ticket.readingsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.authorId, this.title, this.content);
    }
}
