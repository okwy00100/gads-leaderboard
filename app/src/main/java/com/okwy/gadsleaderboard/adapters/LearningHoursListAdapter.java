package com.okwy.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.okwy.gadsleaderboard.R;
import com.okwy.gadsleaderboard.model.ResponseBody;

import java.util.List;


public class LearningHoursListAdapter extends RecyclerView.Adapter<LearningHoursListAdapter.ViewHolder>{

    private Context context;
    private List<ResponseBody> responseBodyList;

    public LearningHoursListAdapter(Context context, List<ResponseBody> responseBodyList) {
        this.context = context;
        this.responseBodyList = responseBodyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.top_learners_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(responseBodyList.get(position));
    }

    @Override
    public int getItemCount() {
        return responseBodyList != null ? responseBodyList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Nullable
        private TextView nameTxt, contentTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            contentTxt = itemView.findViewById(R.id.contentTxt);
        }

        void bind(final ResponseBody responseBody){
            assert nameTxt != null;
            nameTxt.setText(responseBody.getName());
            assert contentTxt != null;
            String contentPlaceHolder = context.getString(R.string.content_placeholder_top_learners)
                    .replace("{$hours}",String.valueOf(responseBody.getHours()))
                    .replace("{$country}",responseBody.getCountry());
            contentTxt.setText(contentPlaceHolder);
        }
    }
}
