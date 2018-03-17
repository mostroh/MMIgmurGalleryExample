package example.mmigmur.mmimgurgalleryexample.viewmodel;

import java.io.File;
import java.io.Serializable;

/**
 * Created by migarcma on 17/3/18.
 */

public class UploadImageViewModel implements Serializable {

    public File image;
    public String title;
    public String description;
    public String albumId;

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
}
