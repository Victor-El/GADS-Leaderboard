package me.codeenzyme.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Url;

import java.util.List;

import me.codeenzyme.gadsleaderboard.viewmodels.LearnersViewModel;

public class ProjectSubmissionActivity extends AppCompatActivity {
    private Validator validator;

    private Toolbar toolbar;

    @NotEmpty
    private EditText firstNameEditText;

    @NotEmpty
    private EditText lastNameEditText;

    @Email
    private EditText emailEditText;

    @Url
    private EditText projectUrlEditText;

    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        setUpValidator();

        firstNameEditText = findViewById(R.id.edit_text_first_name);
        lastNameEditText = findViewById(R.id.edit_text_last_name);
        emailEditText = findViewById(R.id.edit_text_email_address);
        projectUrlEditText = findViewById(R.id.edit_text_project_url);

        submitBtn = findViewById(R.id.submit_project_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        toolbar = findViewById(R.id.sub_toolbar);
        setSupportActionBar(toolbar);

        ImageView backBtnImageView = findViewById(R.id.toolbar_back);
        backBtnImageView.setOnClickListener((View v) -> {
            onBackPressed();
        });
    }

    private void setUpValidator() {
        validator = new Validator(this);
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String projectUrl = projectUrlEditText.getText().toString().trim();

                new ViewModelProvider(ProjectSubmissionActivity.this).get(LearnersViewModel.class)
                        .submitProject(firstName, lastName, email, projectUrl)
                        .observe(ProjectSubmissionActivity.this, new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {

                                if (aBoolean == true) {

                                    firstNameEditText.setText("");
                                    lastNameEditText.setText("");
                                    emailEditText.setText("");
                                    projectUrlEditText.setText("");

                                    Toast.makeText(ProjectSubmissionActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                } else if(aBoolean == false) {

                                    Toast.makeText(ProjectSubmissionActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError err: errors) {
                    View view = err.getView();

                    if (view instanceof EditText) {
                        ((EditText) view ).setError(err.getCollatedErrorMessage(getApplicationContext()));
                    }
                }
            }
        });
    }
}