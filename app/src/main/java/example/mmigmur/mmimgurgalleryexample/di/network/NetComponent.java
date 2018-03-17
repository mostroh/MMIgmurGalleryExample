package example.mmigmur.mmimgurgalleryexample.di.network;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(
        modules= {
                NetModule.class
        }
)
public interface NetComponent {
    // downstream components need these exposed
    Retrofit provideRetrofit();
    Context provideContext();
}
