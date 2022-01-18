package ibf.nus.sg.SSFAssessment.model;

public class Book {

    private String title = "TITLE NOT FOUND";
    private String description = "DESCRIPTION NOT FOUND";
    private String excerpt = "EXCERPT NOT FOUND";
    private String cover = "COVER NOT FOUND";
    private boolean cached = false;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getExcerpt() {
        return this.excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public boolean isCached() {
        return this.cached;
    }

    public void setIsCached(boolean cached) {
        this.cached = cached;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return String.join(this.title, this.description, this.excerpt, this.cover);
    }
    
}
