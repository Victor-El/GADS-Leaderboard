package me.codeenzyme.gadsleaderboard.repository;

import java.util.List;

import me.codeenzyme.gadsleaderboard.data.remote.LearnersService;
import me.codeenzyme.gadsleaderboard.data.remote.RetrofitManager;
import me.codeenzyme.gadsleaderboard.models.TopLearner;
import retrofit2.Retrofit;

public class LearnersRepository {
    private static LearnersRepository learnersRepository = null;
    private Retrofit retrofit;

    public LearnersRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public static LearnersRepository getInstance(Retrofit retro) {
        if (learnersRepository == null) {
            learnersRepository = new LearnersRepository(retro);
        }

        return learnersRepository;
    }

//    public List<TopLearner> getTopLearners() {
//        return RetrofitManager.getInstance().create(LearnersService.class).getAllTopLearners().en;
//    }


}
