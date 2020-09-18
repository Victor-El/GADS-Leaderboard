package me.codeenzyme.gadsleaderboard.viewmodels;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.codeenzyme.gadsleaderboard.data.remote.LearnersService;
import me.codeenzyme.gadsleaderboard.data.remote.RetrofitManager;
import me.codeenzyme.gadsleaderboard.models.TopLearner;
import me.codeenzyme.gadsleaderboard.models.TopSkilled;
import me.codeenzyme.gadsleaderboard.services.java.SubmitProjectService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearnersViewModel extends AndroidViewModel {
    private MutableLiveData<List<TopLearner>> liveTopLearners = new MutableLiveData<>();
    private MutableLiveData<List<TopSkilled>> liveTopSkilled = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTopLearnersLoaded = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTopSkilledLoaded = new MutableLiveData<>();

    public LearnersViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<TopLearner>> getTopLearners() {
        if (liveTopLearners.getValue() == null) loadTopLearners();
        return liveTopLearners;
    }

    public LiveData<List<TopSkilled>> getTopSkilled() {
        if (liveTopSkilled.getValue() == null) loadTopSkilled();
        return liveTopSkilled;
    }

    public void loadLearners() {
        loadTopLearners();
    }

    public void loadSkilled() {
        loadTopSkilled();
    }

    public LiveData<Boolean> getIsTopLearnerLoaded() {
        return isTopLearnersLoaded;
    }

    public LiveData<Boolean> getIsTopSkilledLoaded() {
        return isTopSkilledLoaded;
    }

    public LiveData<Boolean> submitProject(String firstName, String lastName, String email, String projectUrl) {
        return SubmitProjectService.getInstance().submit(firstName, lastName, email, projectUrl);
    }

    private void loadTopLearners() {
        isTopLearnersLoaded.setValue(false);
        RetrofitManager.getInstance().create(LearnersService.class).getAllTopLearners().enqueue(new Callback<List<TopLearner>>() {
            @Override
            public void onResponse(Call<List<TopLearner>> call, Response<List<TopLearner>> response) {
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    liveTopLearners.setValue(response.body());
                } else {
                    Toast.makeText(getApplication().getApplicationContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
                isTopLearnersLoaded.setValue(true);
            }

            @Override
            public void onFailure(Call<List<TopLearner>> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                Log.d("view-model-err", t.getLocalizedMessage());
                isTopLearnersLoaded.setValue(true);
            }
        });
    }

    private void loadTopSkilled() {
        isTopSkilledLoaded.setValue(false);
        RetrofitManager.getInstance().create(LearnersService.class).getAllTopSkilled().enqueue(new Callback<List<TopSkilled>>() {
            @Override
            public void onResponse(Call<List<TopSkilled>> call, Response<List<TopSkilled>> response) {
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    liveTopSkilled.setValue(response.body());
                } else {
                    Toast.makeText(getApplication().getApplicationContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
                isTopSkilledLoaded.setValue(true);
            }

            @Override
            public void onFailure(Call<List<TopSkilled>> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                Log.d("view-model-err", t.getLocalizedMessage());
                isTopSkilledLoaded.setValue(true);
            }
        });
    }
}
