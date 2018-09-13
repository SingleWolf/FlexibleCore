package com.walker.flexiblecore.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walker.flexiblecore.R;
import com.walker.flexiblecore.bean.Summary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Summary> mData;

    public SummaryAdapter(List<Summary> data) {
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summary, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String title = mData.get(position).getTitle();
        ((ViewHolder) holder).tvTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
