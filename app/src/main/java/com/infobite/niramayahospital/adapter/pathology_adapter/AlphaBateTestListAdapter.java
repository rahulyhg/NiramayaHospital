package com.infobite.niramayahospital.adapter.pathology_adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.ui.pathology.interfaces.InitAlphabateList;

import java.util.ArrayList;

public class AlphaBateTestListAdapter extends RecyclerView.Adapter<AlphaBateTestListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> alphaBateTestList;
    private View.OnClickListener onClickListener;
    private Boolean checked = false;
    private InitAlphabateList alphabateList;
    private int row_index = 0;
    View rootView;

    public AlphaBateTestListAdapter(Context mContext, ArrayList<String> alphaBateTestList, View.OnClickListener onClickListener,InitAlphabateList alphabateList) {
        this.mContext = mContext;
        this.alphaBateTestList = alphaBateTestList;
        this.onClickListener = onClickListener;
        this.alphabateList = alphabateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        rootView = li.inflate(R.layout.row_alphabate_testlist, null);
        return new ViewHolder(rootView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final String str = alphaBateTestList.get(i);
        viewHolder.tvAlphabateName.setText(str);
        viewHolder.tvAlphabateName.setTag(i);
        viewHolder.tvAlphabateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = i;
                notifyDataSetChanged();
                alphabateList.initAlphabateList(i);
            }
        });

        if (row_index == i) {
            viewHolder.tvAlphabateName.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg_red_p11));
            viewHolder.tvAlphabateName.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            viewHolder.tvAlphabateName.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg_red_corner_p11));
            viewHolder.tvAlphabateName.setTextColor(mContext.getResources().getColor(R.color.black));
        }
    }


    @Override
    public int getItemCount() {
        return alphaBateTestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAlphabateName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlphabateName = itemView.findViewById(R.id.tvAlphaBateName);

        }
    }
}
