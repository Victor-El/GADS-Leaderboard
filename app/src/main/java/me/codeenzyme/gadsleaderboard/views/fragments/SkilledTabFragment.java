package me.codeenzyme.gadsleaderboard.views.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import me.codeenzyme.gadsleaderboard.R;
import me.codeenzyme.gadsleaderboard.models.TopSkilled;
import me.codeenzyme.gadsleaderboard.viewmodels.LearnersViewModel;
import me.codeenzyme.gadsleaderboard.views.adapters.SkilledRecyclerAdapter;

public class SkilledTabFragment extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private SkilledRecyclerAdapter skilledRecyclerAdapter;
    private ArrayList<TopSkilled> topSkilledArrayList;
    private LearnersViewModel learnersViewModel;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_skilled, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.skilled_swipe_refresh_layout);

        recyclerView = view.findViewById(R.id.top_skilled_recycler_view);
        topSkilledArrayList = new ArrayList<>();
        skilledRecyclerAdapter = new SkilledRecyclerAdapter(getActivity(), topSkilledArrayList);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(skilledRecyclerAdapter);
        // Check out Fragment Activity LifeCycle
        learnersViewModel = new ViewModelProvider(requireActivity()).get(LearnersViewModel.class);
        // learnersViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(LearnersViewModel.class);
        learnersViewModel.getTopSkilled().observe(getViewLifecycleOwner(), new Observer<List<TopSkilled>>() {
            @Override
            public void onChanged(List<TopSkilled> topSkilleds) {
                topSkilledArrayList.addAll(topSkilleds);
                skilledRecyclerAdapter.notifyDataSetChanged();
            }
        });

        swipeRefreshLayout.setColorSchemeColors(Color.rgb(0, 0, 0), Color.rgb(255, 152, 0));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                learnersViewModel.loadSkilled();
            }
        });
        learnersViewModel.getIsTopSkilledLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                swipeRefreshLayout.setRefreshing(!aBoolean);
            }
        });

    }
}
