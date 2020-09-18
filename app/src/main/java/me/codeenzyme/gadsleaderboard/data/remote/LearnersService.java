package me.codeenzyme.gadsleaderboard.data.remote;

import java.util.List;

import me.codeenzyme.gadsleaderboard.models.TopLearner;
import me.codeenzyme.gadsleaderboard.models.TopSkilled;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LearnersService {
    @GET("/api/hours")
    Call<List<TopLearner>> getAllTopLearners();

    @GET("/api/skilliq")
    Call<List<TopSkilled>> getAllTopSkilled();

    @POST("1FAIpQLSf8XkYSNcWDJIHLXTuRQe6cN6uyaxp5CNn5DjIaxloN8GLBSw/formResponse")
    @FormUrlEncoded
    Call<Void> submitProject(@Field("entry.858578603") String firstName, @Field("entry.252181913")
            String lastName, @Field("entry.2013122071") String email, @Field("entry.303955725") String projectUrl);
}
