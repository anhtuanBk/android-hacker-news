package com.innovatube.hackernew.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.innovatube.hackernew.data.model.Story;
import com.innovatube.hackernew.data.model.UserId;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by TOIDV on 4/4/2016.
 */
public interface HackerNewsService {

//    @FormUrlEncoded
//    @POST("jobseeker/register")
//    Observable<UserId> createJobSeekerAccount(@Field("first_name") String firstName,
//                                              @Field("last_name") String lastName,
//                                              @Field("email") String emailAddress,
//                                              @Field("password") String password,
//                                              @Field("confirm_password") String confirmPassword,
//                                              @Field("dob") String dob,
//                                              @Field("promo") String promotionCode);

    @GET("topstories.json")
    Observable<List<Long>> getTopStories();

    @GET("newstories.json")
    Observable<List<Long>> getNewStories();

    @GET("item/{storyId}.json")
    Observable<Story> getStoryDetail(@Path("storyId") long storyId);




    class Creator {
//        private static final String ENDPOINT = "https://stage-inploi-api.herokuapp.com/v1/";

        private static final String ENDPOINT = "https://hacker-news.firebaseio.com/v0/";

        public static Retrofit newRetrofitInstance() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")
                    .create();

            return new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();

        }

    }


}
