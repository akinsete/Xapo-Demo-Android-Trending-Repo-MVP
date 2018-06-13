package trendingrepo.test.demo.xapo.app.data;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import trendingrepo.test.demo.xapo.app.BuildConfig;
import trendingrepo.test.demo.xapo.app.data.github.GithubApi;

@Module
public class ApiModule {



    @Provides
    public OkHttpClient provideClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }


    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    @Provides
    public GithubApi provideGithubApiService(){
        return provideRetrofit(BuildConfig.GITHUB_BASE_URL,provideClient()).create(GithubApi.class);
    }


}
