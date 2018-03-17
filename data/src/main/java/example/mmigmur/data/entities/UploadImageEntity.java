package example.mmigmur.data.entities;

import java.io.File;

/**
 * Created by migarcma on 16/3/18.
 */

public class UploadImageEntity {

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
