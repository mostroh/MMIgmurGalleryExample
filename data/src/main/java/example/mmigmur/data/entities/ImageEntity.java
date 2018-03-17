package example.mmigmur.data.entities;

/**
 * Created by migarcma on 16/3/18.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageEntity {

    @SerializedName("account_id")
    @Expose
    private Integer accountId;
    @SerializedName("account_url")
    @Expose
    private String accountUrl;
    @SerializedName("ad_type")
    @Expose
    private Integer adType;
    @SerializedName("ad_url")
    @Expose
    private String adUrl;
    @SerializedName("animated")
    @Expose
    private Boolean animated;
    @SerializedName("bandwidth")
    @Expose
    private Integer bandwidth;
    @SerializedName("datetime")
    @Expose
    private Integer datetime;
    @SerializedName("deletehash")
    @Expose
    private String deletehash;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("favorite")
    @Expose
    private Boolean favorite;
    @SerializedName("has_sound")
    @Expose
    private Boolean hasSound;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("in_gallery")
    @Expose
    private Boolean inGallery;
    @SerializedName("in_most_viral")
    @Expose
    private Boolean inMostViral;
    @SerializedName("is_ad")
    @Expose
    private Boolean isAd;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("width")
    @Expose
    private Integer width;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public Integer getAdType() {
        return adType;
    }

    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Integer getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getHasSound() {
        return hasSound;
    }

    public void setHasSound(Boolean hasSound) {
        this.hasSound = hasSound;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getInGallery() {
        return inGallery;
    }

    public void setInGallery(Boolean inGallery) {
        this.inGallery = inGallery;
    }

    public Boolean getInMostViral() {
        return inMostViral;
    }

    public void setInMostViral(Boolean inMostViral) {
        this.inMostViral = inMostViral;
    }

    public Boolean getAd() {
        return isAd;
    }

    public void setAd(Boolean ad) {
        isAd = ad;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
