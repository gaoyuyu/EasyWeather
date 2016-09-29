package com.gaoyy.easyweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyy.easyweather.R;

import java.util.List;

public class LifeIndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private LayoutInflater inflater;
    private Context context;
    private List<List<String>> data;
    private int[] labels = {R.mipmap.ic_chuanyi,R.mipmap.ic_ganmao,
            R.mipmap.ic_kongtiao,R.mipmap.ic_wuran,R.mipmap.ic_xiche,R.mipmap.ic_yundong,R.mipmap.ic_ziwaixian};

    public LifeIndexAdapter(Context context, List<List<String>> data)
    {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View rootView = inflater.inflate(R.layout.life_item, parent, false);
        IndexItemViewHolder indexItemViewHolder = new IndexItemViewHolder(rootView);
        return indexItemViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        IndexItemViewHolder indexItemViewHolder = (IndexItemViewHolder) holder;
        indexItemViewHolder.lifeItemLabel.setImageDrawable(context.getResources().getDrawable(labels[position]));
        indexItemViewHolder.lifeItemTitle.setText(data.get(position).get(0));
        indexItemViewHolder.lifeItemDetail.setText(data.get(position).get(1));
    }

    public void updateData(List<List<String>> data)
    {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public static class IndexItemViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView lifeItemLabel;
        private TextView lifeItemTitle;
        private TextView lifeItemDetail;

        public IndexItemViewHolder(View itemView)
        {
            super(itemView);
            lifeItemLabel = (ImageView) itemView.findViewById(R.id.life_item_label);
            lifeItemTitle = (TextView) itemView.findViewById(R.id.life_item_title);
            lifeItemDetail = (TextView) itemView.findViewById(R.id.life_item_detail);
        }
    }
}
