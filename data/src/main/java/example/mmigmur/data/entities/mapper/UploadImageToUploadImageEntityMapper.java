package example.mmigmur.data.entities.mapper;

import example.mmigmur.data.entities.UploadImageEntity;
import example.mmigmur.domain.model.UploadImage;

/**
 * Created by migarcma on 17/3/18.
 */

public class UploadImageToUploadImageEntityMapper extends Mapper<UploadImage, UploadImageEntity> {

    public UploadImageToUploadImageEntityMapper() {
    }

    @Override
    public UploadImageEntity map(UploadImage value) {
        UploadImageEntity uploadImageEntity = new UploadImageEntity();
        uploadImageEntity.setAlbumId(value.getAlbumId());
        uploadImageEntity.setDescription(value.getDescription());
        uploadImageEntity.setImage(value.getImage());
        uploadImageEntity.setTitle(value.getTitle());

        return uploadImageEntity;
    }

    @Override
    public UploadImage reverseMap(UploadImageEntity value) {
        throw new UnsupportedOperationException();
    }
}
