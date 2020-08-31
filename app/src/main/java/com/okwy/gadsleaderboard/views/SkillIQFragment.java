package com.okwy.gadsleaderboard.views;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.okwy.gadsleaderboard.R;
import com.okwy.gadsleaderboard.adapters.SkillIQListAdapter;
import com.okwy.gadsleaderboard.model.ResponseBody;
import com.okwy.gadsleaderboard.utilities.UtilityClass;

import java.util.ArrayList;
import java.util.List;

public class SkillIQFragment extends Fragment {

    private RecyclerView skillIQListRecycler;
    private SkillIQListAdapter skillIQListAdapter;
    private List<ResponseBody> responseBodyList;
    private ProgressDialog dialog;
    private Context context;

    public SkillIQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("------------------onCreateView--------------------------");
        return inflater.inflate(R.layout.fragment_skill_iq, container, false);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("------------------onViewCreated--------------------------");
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        dialog = new ProgressDialog(context);
        responseBodyList = new ArrayList<>();
        skillIQListRecycler = view.findViewById(R.id.skill_iq_recycler);
        skillIQListAdapter = new SkillIQListAdapter(context, responseBodyList);
        skillIQListRecycler.setLayoutManager(new LinearLayoutManager(context));
        skillIQListRecycler.setAdapter(skillIQListAdapter);


        new fetchSkillIQList().execute(); // TODO: do internet check

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("------------------onActivityCreated--------------------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("------------------onResume--------------------------");
    }


    private class fetchSkillIQList extends AsyncTask<Void, Void, List<ResponseBody>> {

        @Override
        protected List<ResponseBody> doInBackground(Void... voids) {

            return UtilityClass.skillIQGETProcessor();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Fetching List");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<ResponseBody> responseBody) {
            super.onPostExecute(responseBody);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            try {
                if (null != responseBody && responseBody.size() > 0) {
                    responseBodyList.addAll(responseBody);
                    System.out.println("Sorted List======>" + responseBodyList.toString());
                    System.out.println("Size======>" + responseBodyList.size());
                } else {
                    Log.e("SKILL IQ FRAGMENT", "Empty response");
                }
                skillIQListAdapter.notifyDataSetChanged();


            } catch (Exception e) {
                e.printStackTrace();
                Log.e("SKILL IQ FRAGMENT", "Exeception caught");
                skillIQListAdapter.notifyDataSetChanged();
            }
        }
    }
}
