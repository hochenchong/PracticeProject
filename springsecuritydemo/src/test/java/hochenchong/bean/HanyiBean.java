package hochenchong.bean;

public class HanyiBean {
    private String ProductSetName;
    private String ProductName;
    private String Version;
    private String ProductFileName;
    private String ProductFileUrl;
    private int FavoriteCount;
    private int FavourCount;
    private int id;
    private String TimeStamp;

    public void setProductSetName(String ProductSetName) {
        this.ProductSetName = ProductSetName;
    }
    public String getProductSetName() {
        return ProductSetName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }
    public String getProductName() {
        return ProductName;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
    public String getVersion() {
        return Version;
    }

    public void setProductFileName(String ProductFileName) {
        this.ProductFileName = ProductFileName;
    }
    public String getProductFileName() {
        return ProductFileName;
    }

    public void setProductFileUrl(String ProductFileUrl) {
        this.ProductFileUrl = ProductFileUrl;
    }
    public String getProductFileUrl() {
        return ProductFileUrl;
    }

    public void setFavoriteCount(int FavoriteCount) {
        this.FavoriteCount = FavoriteCount;
    }
    public int getFavoriteCount() {
        return FavoriteCount;
    }

    public void setFavourCount(int FavourCount) {
        this.FavourCount = FavourCount;
    }
    public int getFavourCount() {
        return FavourCount;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }
    public String getTimeStamp() {
        return TimeStamp;
    }
}
