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
import com.okwy.gadsleaderboard.adapters.LearningHoursListAdapter;
import com.okwy.gadsleaderboard.model.ResponseBody;
import com.okwy.gadsleaderboard.utilities.UtilityClass;

import java.util.ArrayList;
import java.util.List;

public class TopLearnersFragment extends Fragment {

    private RecyclerView topLearnersListRecycler;
    private LearningHoursListAdapter topLearnersListAdapter;
    private List<ResponseBody> responseBodyList;
    private ProgressDialog dialog;
    private Context context;

    public TopLearnersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("------------------onCreateView--------------------------");
        return inflater.inflate(R.layout.fragment_top_learners, container, false);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("------------------onViewCreated--------------------------");
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        dialog = new ProgressDialog(context);
        responseBodyList = new ArrayList<>();
        topLearnersListRecycler = view.findViewById(R.id.top_learners_recycler);
        topLearnersListAdapter = new LearningHoursListAdapter(context, responseBodyList);
        topLearnersListRecycler.setLayoutManager(new LinearLayoutManager(context));
        topLearnersListRecycler.setAdapter(topLearnersListAdapter);

        new fetchTopLearnersList().execute();  // TODO: do internet check
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

    private class fetchTopLearnersList extends AsyncTask<Void, Void, List<ResponseBody>> {

        @Override
        protected List<ResponseBody> doInBackground(Void... voids) {

            return UtilityClass.topLearnersGETProcessor();
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
                    Log.e("TOP LEARNERS FRAGMENT", "Empty response");
                }
                topLearnersListAdapter.notifyDataSetChanged();


            } catch (Exception e) {
                e.printStackTrace();
                Log.e("TOP LEARNERS FRAGMENT", "Exeception caught");
                topLearnersListAdapter.notifyDataSetChanged();
            }
        }
    }
}
