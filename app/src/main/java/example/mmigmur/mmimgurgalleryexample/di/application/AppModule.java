package example.mmigmur.mmimgurgalleryexample.di.application;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.mmigmur.mmimgurgalleryexample.MMImgurGalleryApp;

@Module()
public class AppModule {

    private MMImgurGalleryApp mApplication;

    public AppModule(MMImgurGalleryApp app) {
        this.mApplication = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return this.mApplication;
    }

}
