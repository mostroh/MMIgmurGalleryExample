package example.mmigmur.mmimgurgalleryexample.di.application;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import example.mmigmur.mmimgurgalleryexample.MMImgurGalleryApp;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityModule;
import example.mmigmur.mmimgurgalleryexample.di.network.NetModule;
import retrofit2.Retrofit;

@Component(
    modules = {
            AppModule.class,
            NetModule.class
    }
)
@Singleton
public interface AppComponent {

    /**
     * Here we can provide an App-Scoped Singleton with following method
     */
    // AppSingleton provideSingleton();
    void inject(MMImgurGalleryApp mmImgurGalleryApp);


    //LoginPasswordFragmentComponent width(LoginPasswordFragmentModule module);
    //LoginPasswordPresenterComponent width(LoginPasswordPresenterModule module);
    // void inject(MyFragment fragment);

    ActivityComponent with(ActivityModule mainActivityModule);

    // void inject(MyService service);
    Retrofit provideRetrofit();

    Application provideApplication();

}
