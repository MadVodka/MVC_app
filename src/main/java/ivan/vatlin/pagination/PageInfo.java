package ivan.vatlin.pagination;

import java.util.List;

public class PageInfo<T> {
    private List<T> content;
    private long numberOfPages;

    public PageInfo(List<T> content, long numberOfPages) {
        this.content = content;
        this.numberOfPages = numberOfPages;
    }

    public List<T> getContent() {
        return content;
    }

    public long getNumberOfPages() {
        return numberOfPages;
    }
}
