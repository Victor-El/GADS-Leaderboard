package me.codeenzyme.gadsleaderboard.views.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import me.codeenzyme.gadsleaderboard.R;
import me.codeenzyme.gadsleaderboard.models.TopLearner;
import me.codeenzyme.gadsleaderboard.models.TopSkilled;
import me.codeenzyme.gadsleaderboard.viewmodels.LearnersViewModel;
import me.codeenzyme.gadsleaderboard.views.adapters.LearnersRecyclerAdapter;
import me.codeenzyme.gadsleaderboard.views.adapters.SkilledRecyclerAdapter;
import me.codeenzyme.gadsleaderboard.views.adapters.TabsPagerAdapter;

public class TabFragment extends Fragment {
    public static final String TAB_KEY = "tab-key";
    private RecyclerView topLearnerRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private LearnersRecyclerAdapter learnersRecyclerAdapter;

    private ArrayList<TopLearner> topLearnersList;

    private LearnersViewModel learnersViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.learners_swipe_refresh_layout);

        topLearnerRecyclerView = view.findViewById(R.id.top_learner_recycler_view);
        topLearnersList = new ArrayList<>();
        learnersRecyclerAdapter = new LearnersRecyclerAdapter(getActivity(), topLearnersList);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        topLearnerRecyclerView.setLayoutManager(linearLayoutManager);
        topLearnerRecyclerView.setAdapter(learnersRecyclerAdapter);

        learnersViewModel = new ViewModelProvider(requireActivity()).get(LearnersViewModel.class);
        // learnersViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(LearnersViewModel.class);
        learnersViewModel.getTopLearners().observe(getViewLifecycleOwner(), topLearners -> {
            ArrayList<TopLearner> learners = (ArrayList<TopLearner>) topLearners;
            topLearnersList.addAll(topLearners);
            Log.d("LEN", String.valueOf(topLearnersList.size()));
            learnersRecyclerAdapter.notifyDataSetChanged();
        });

        swipeRefreshLayout.setColorSchemeColors(Color.rgb(0, 0, 0), Color.rgb(255, 152, 0));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                learnersViewModel.loadLearners();
            }
        });
        learnersViewModel.getIsTopLearnerLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                swipeRefreshLayout.setRefreshing(!aBoolean);
            }
        });
    }


}
