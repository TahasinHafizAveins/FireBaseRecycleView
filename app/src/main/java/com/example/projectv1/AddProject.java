package com.example.projectv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProject extends AppCompatActivity {

    EditText editTextProjectName;
    Button addBtn;

    DatabaseReference databaseReferenceproject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        editTextProjectName = (EditText)findViewById(R.id.project_name);
        addBtn = (Button)findViewById(R.id.add);
        databaseReferenceproject = FirebaseDatabase.getInstance().getReference("projects");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProject();
            }
        });
    }

    private void addProject(){
        String name = editTextProjectName.getText().toString().trim();

        if (!TextUtils.isEmpty(name))
        {
            String id = databaseReferenceproject.push().getKey();

            Projects projects = new Projects(id,name);
            databaseReferenceproject.child(id).setValue(projects);
            Toast.makeText(this, "New Project added", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "You should enter a project name", Toast.LENGTH_SHORT).show();
        }
    }

}
