package com.infobite.niramayahospital.adapter.farmacy_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal.Medcinie;
import com.infobite.niramayahospital.utils.AppPreference;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    private List<Medcinie> categoryList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public CategoryListAdapter(List<Medcinie> categoryList, Context mContext, View.OnClickListener onClickListener) {
        this.categoryList = categoryList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Medcinie medcinielist = categoryList.get(position);
        holder.categoryTitle.setText(medcinielist.getTitle());
        holder.tvUpdateDescription.setText(medcinielist.getDescription());
        String strCategory = medcinielist.getId();
        AppPreference.setStringPreference(mContext, Constant.CATEGORY_ID, strCategory);

        holder.categoryTitle.setTag(position);
        holder.categoryTitle.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle, tvUpdateDescription;

        public MyViewHolder(View view) {
            super(view);
            categoryTitle = itemView.findViewById(R.id.tvCategoryName);
            tvUpdateDescription = itemView.findViewById(R.id.tvUpdateDescription);

        }
    }

}
