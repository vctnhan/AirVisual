package com.hanwool.airvisual.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.hanwool.airvisual.databinding.AqiBinding;
import com.hanwool.airvisual.model.PollutionApi;
import com.hanwool.airvisual.model.PollutionInfo;
import com.hanwool.airvisual.view.callback.OnItemRecyClickCallback;
import com.hanwool.airvisual.view.ui.MainActivity;
import com.hanwool.airvisual.viewmodel.PollutionAqiViewModel;
import com.hanwool.airvisual.viewmodel.RecyclerviewModel;

import java.util.List;

/**
 * Created by lenovo on 2/17/2018.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.CustomView> {
    List<RecyclerviewModel> newsList;
    private LayoutInflater layoutInflater;
OnItemRecyClickCallback onItemRecyClickCallback;
    public RecyclerviewAdapter(List<RecyclerviewModel> newsList,OnItemRecyClickCallback onItemRecyClickCallback ) {
        this.newsList = newsList;
        this.onItemRecyClickCallback = onItemRecyClickCallback;
    }

    @Override
    public CustomView onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        final AqiBinding newsBinding = AqiBinding.inflate(layoutInflater, parent, false);
//        newsBinding.setOnClickItem(new OnItemRecyClickCallback() {
//            @Override
//            public void onclickListener() {
//                PollutionApi pollutionApi = new PollutionApi();
//                pollutionApi.setmCity(newsBinding.getNewsview().nameCityToLV() + " clicked");
//                pollutionApi.setmIndex(newsBinding.getNewsview().airIndexToLV());
//                pollutionApi.setmClassification(newsBinding.getNewsview().classificationToLV());
//                pollutionApi.setMdateTime(newsBinding.getNewsview().dateTimeToLV());
//                PollutionAqiViewModel pollutionInfoViewModel = new PollutionAqiViewModel();
//
//            }
//        });
        newsBinding.setOnClickItem(onItemRecyClickCallback);
        return new CustomView(newsBinding);
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {
        RecyclerviewModel recyclerviewModel = newsList.get(position);
        holder.bind(recyclerviewModel);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class CustomView extends RecyclerView.ViewHolder {
        private AqiBinding aqiBinding;

        public CustomView(AqiBinding aqiBinding) {
            super(aqiBinding.getRoot());
            this.aqiBinding = aqiBinding;
        }

        public void bind(RecyclerviewModel recyclerviewModel) {
            this.aqiBinding.setViewmodel(recyclerviewModel);
        }

        public AqiBinding getNewsBinding() {
            return aqiBinding;
        }
    }
}
