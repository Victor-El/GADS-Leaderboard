package me.codeenzyme.gadsleaderboard.data.remote;

import java.util.List;

import me.codeenzyme.gadsleaderboard.models.TopLearner;
import me.codeenzyme.gadsleaderboard.models.TopSkilled;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LearnersService {
    @GET("/api/hours")
    Call<List<TopLearner>> getAllTopLearners();

    @GET("/api/skilliq")
    Call<List<TopSkilled>> getAllTopSkilled();
}
