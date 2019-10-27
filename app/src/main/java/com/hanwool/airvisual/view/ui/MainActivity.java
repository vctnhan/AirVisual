package com.hanwool.airvisual.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.hanwool.airvisual.R;
import com.hanwool.airvisual.databinding.ActivityMainBinding;
import com.hanwool.airvisual.model.PollutionInfo;
import com.hanwool.airvisual.server.AqiRespository;
import com.hanwool.airvisual.server.AqiService;
import com.hanwool.airvisual.view.adapter.RecyclerviewAdapter;
import com.hanwool.airvisual.view.callback.OnItemRecyClickCallback;
import com.hanwool.airvisual.viewmodel.PollutionAqiViewModel;
import com.hanwool.airvisual.viewmodel.RecyclerviewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    private AqiService aqiService;
    private static AqiRespository aqiRespository;
    PollutionAqiViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel =
                ViewModelProviders.of(this).get(PollutionAqiViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        // Observe project data
        viewModel.getObservableProject().observe(this, new Observer<PollutionInfo>() {
            @Override
            public void onChanged(@Nullable PollutionInfo pollutionInfo) {
                if (pollutionInfo != null) {
                    viewModel.setPollutionApiViewModel(pollutionInfo);
                    initView();
                    activityMainBinding.setViewmodel(viewModel);
                }
            }
        });
    }

    private final OnItemRecyClickCallback onItemRecyClickCallback = new OnItemRecyClickCallback() {
        @Override
        public void onClickListener(final RecyclerviewModel recyclerviewModel) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                viewModel.setPollutionApiViewModel(addToModel(recyclerviewModel));
                activityMainBinding.setViewmodel(viewModel);
            }
        }
    };

    public PollutionInfo addToModel(RecyclerviewModel recyclerviewModel) {
        PollutionInfo pollutionInfo = new PollutionInfo();
        pollutionInfo.setmCity(recyclerviewModel.nameCityToLV());
        pollutionInfo.setmIndex(recyclerviewModel.airIndexToLV());
        pollutionInfo.setmClassification(recyclerviewModel.classificationToLV() + "clicked");
        pollutionInfo.setMdateTime(recyclerviewModel.dateTimeToLV());
        return pollutionInfo;
    }

    public void initView() {
        RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(viewModel.getArrToLv(), onItemRecyClickCallback);
        activityMainBinding.recycler.setLayoutManager(
                new LinearLayoutManager(
                        MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        activityMainBinding.recycler.setAdapter(recyclerviewAdapter);
    }
}

