package com.okwy.gadsleaderboard.views;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.okwy.gadsleaderboard.R;
import com.okwy.gadsleaderboard.utilities.UtilityClass;

import java.io.UnsupportedEncodingException;

public class GADSSubmission extends AppCompatActivity {

    private GADSSubmissionDialog dialog;
    private TextView firstNameField, lastNameField, emailAddressField, projectLinkField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new GADSSubmissionDialog();
        setContentView(R.layout.activity_gads_submission);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        firstNameField = findViewById(R.id.firstNameField);
        lastNameField = findViewById(R.id.lastNameField);
        emailAddressField = findViewById(R.id.emailAddressField);
        projectLinkField = findViewById(R.id.projectLinkField);


    }

    public void initiateSubmit(View view) {
        if (firstNameField.getText().toString().isEmpty() || lastNameField.getText().toString().isEmpty()
                || emailAddressField.getText().toString().isEmpty() || projectLinkField.getText().toString().isEmpty() ||
                firstNameField.getText().toString().trim().equals("") || lastNameField.getText().toString().trim().equals("")
                || emailAddressField.getText().toString().trim().equals("") || projectLinkField.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.input_prompt, Toast.LENGTH_SHORT).show();
        } else {

            dialog.showSubmitDialog(this,
                    firstNameField.getText().toString().trim(),
                    lastNameField.getText().toString().trim(),
                    emailAddressField.getText().toString().trim(),
                    projectLinkField.getText().toString().trim());
        }
    }
}
