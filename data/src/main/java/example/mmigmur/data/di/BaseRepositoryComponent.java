package example.mmigmur.data.di;


import dagger.Subcomponent;
import example.mmigmur.data.di.scopes.PerRepository;
import example.mmigmur.data.services.GalleryService;
import example.mmigmur.data.services.LoginService;
import example.mmigmur.data.services.UploadService;


@PerRepository
@Subcomponent(
        modules = {
                BaseRepositoryModule.class
        })
public interface BaseRepositoryComponent {

    void inject(UploadService uploadService);

    void inject(LoginService loginService);

    void inject(GalleryService galleryService);
}
