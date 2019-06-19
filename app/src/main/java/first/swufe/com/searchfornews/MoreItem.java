package first.swufe.com.searchfornews;

public class MoreItem {
    private int id;
    private String newsTitle;
    private String newsDetail;

    public MoreItem() {
        this.newsTitle="";
        this.newsDetail="";
    }

    public MoreItem(String newsTitle, String newsDetail) {
        this.newsTitle = newsTitle;
        this.newsDetail = newsDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }
}
