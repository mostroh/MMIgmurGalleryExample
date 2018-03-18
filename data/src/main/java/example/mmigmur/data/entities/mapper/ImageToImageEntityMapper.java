package example.mmigmur.data.entities.mapper;

import example.mmigmur.data.entities.ImageEntity;
import example.mmigmur.domain.model.Image;

/**
 * Created by migarcma on 17/3/18.
 */

public class ImageToImageEntityMapper extends Mapper<Image, ImageEntity> {

    public ImageToImageEntityMapper() {
    }

    @Override
    public ImageEntity map(Image value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Image reverseMap(ImageEntity value) {

        Image image = new Image();
        image.setAccountId(value.getAccountId());
        image.setAccountUrl(value.getAccountUrl());
        image.setAdType(value.getAdType());
        image.setAccountId(value.getAccountId());
        image.setAdUrl(value.getAdUrl());
        image.setDatetime(value.getDatetime());
        image.setDescription(value.getDescription());
        image.setFavorite(value.getFavorite());
        image.setHeight(value.getHeight());
        image.setWidth(value.getWidth());
        image.setId(value.getId());
        image.setLink(value.getLink());
        image.setName(value.getName());
        image.setSize(value.getSize());
        image.setTitle(value.getTitle());
        image.setViews(value.getViews());
        image.setType(value.getType());
        image.setDeletehash(value.getDeletehash());
        return image;
    }
}
