package example.mmigmur.data.di;

import dagger.Module;
import dagger.Provides;
import example.mmigmur.data.di.scopes.PerRepository;
import example.mmigmur.data.network.ImgurApiInterface;
import retrofit2.Retrofit;

@Module()
public class BaseRepositoryModule {

    public BaseRepositoryModule() {
    }

    @PerRepository
    @Provides
    public ImgurApiInterface providesImgurApiInterface(Retrofit retrofit){
        return retrofit.create(ImgurApiInterface.class);
    }
}
