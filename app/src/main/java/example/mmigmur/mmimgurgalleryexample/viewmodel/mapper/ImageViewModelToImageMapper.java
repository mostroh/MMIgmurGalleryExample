package example.mmigmur.mmimgurgalleryexample.viewmodel.mapper;

import example.mmigmur.data.entities.mapper.Mapper;
import example.mmigmur.domain.model.Image;
import example.mmigmur.mmimgurgalleryexample.viewmodel.ImageViewModel;

/**
 * Created by migarcma on 17/3/18.
 */

public class ImageViewModelToImageMapper extends Mapper<ImageViewModel, Image> {

    public ImageViewModelToImageMapper() {
    }

    @Override
    public Image map(ImageViewModel value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImageViewModel reverseMap(Image value) {
        ImageViewModel imageViewModel = new ImageViewModel();
        imageViewModel.setAccountId(value.getAccountId());
        imageViewModel.setAccountUrl(value.getAccountUrl());
        imageViewModel.setAdType(value.getAdType());
        imageViewModel.setAccountId(value.getAccountId());
        imageViewModel.setAdUrl(value.getAdUrl());
        imageViewModel.setDatetime(value.getDatetime());
        imageViewModel.setDescription(value.getDescription());
        imageViewModel.setFavorite(value.getFavorite());
        imageViewModel.setHeight(value.getHeight());
        imageViewModel.setWidth(value.getWidth());
        imageViewModel.setId(value.getId());
        imageViewModel.setLink(value.getLink());
        imageViewModel.setName(value.getName());
        imageViewModel.setSize(value.getSize());
        imageViewModel.setTitle(value.getTitle());
        imageViewModel.setViews(value.getViews());
        imageViewModel.setType(value.getType());
        return imageViewModel;
    }
}
