package me.codeenzyme.gadsleaderboard.services.java;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import me.codeenzyme.gadsleaderboard.data.remote.RetrofitManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitProjectService {

    private final String BASE_URL = "https://docs.google.com/forms/d/e/";
    private static SubmitProjectService INSTANCE = null;

    private MutableLiveData<Boolean> isSubmitSuccessful = new MutableLiveData<>();


    public static SubmitProjectService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SubmitProjectService();
        }
        return INSTANCE;
    }

    public LiveData<Boolean> submit(String firstName, String lastName, String email, String projectUrl) {
        RetrofitManager.configureAndGetNewInstance(BASE_URL)
                .submitProject(firstName, lastName, email, projectUrl)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200 && response.isSuccessful()) {
                            isSubmitSuccessful.setValue(true);
                        } else {
                            isSubmitSuccessful.setValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        isSubmitSuccessful.setValue(false);
                    }
                });

        return isSubmitSuccessful;
    }
}
