package com.okwy.gadsleaderboard.views;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.okwy.gadsleaderboard.R;
import com.okwy.gadsleaderboard.utilities.UtilityClass;

public class GADSSubmissionDialog extends AppCompatActivity {

    private Dialog dialog;
    private ImageView cancel;
    private Button confirm;
    View successLayout;
    View failedLayout;

    public void showSubmitDialog(Activity activity, String firstName, String lastName, String email, String githubUrl) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_confirm_submit);

        cancel = dialog.findViewById(R.id.cancel);
        confirm = dialog.findViewById(R.id.confirm);
        successLayout = dialog.getLayoutInflater().inflate(R.layout.custom_toast_submit_success, (ViewGroup) dialog.findViewById(R.id.toast_cardview_success));
        failedLayout = dialog.getLayoutInflater().inflate(R.layout.custom_toast_submit_failed, (ViewGroup) dialog.findViewById(R.id.toast_cardview_failed));

        cancel.setOnClickListener(v -> dismissDialog());
        confirm.setOnClickListener(v -> postSubmitData(activity, firstName, lastName, email, githubUrl));


        dialog.show();
    }

    private void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void postSubmitData(Activity activity, String firstName, String lastName, String email, String githubUrl) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        String submissionResponse = UtilityClass.projectSubmissionFormProcessor(firstName, lastName, email, githubUrl);
        System.out.println(">>>>>>>>>>>>Response>>>>>>>>>>>>>>" + submissionResponse);
        if (submissionResponse.equals("200")) {
            //Toast.makeText(activity, "Submitted successfully", Toast.LENGTH_SHORT).show();
            Toast toast = new Toast(activity.getBaseContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setView(successLayout);//setting the view of custom toast layout
            toast.show();

        } else {
            //Toast.makeText(activity, "Failed to Submit", Toast.LENGTH_SHORT).show();
            Toast toast = new Toast(activity.getBaseContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setView(failedLayout);//setting the view of custom toast layout
            toast.show();
        }

    }
}
