package com.example.waqarayub.firebasetask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edname;
    Button btn;
    Spinner spgen;

    DatabaseReference databaseReference;
    ListView LV;
    List<Being> BL;



    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BL.clear();
                for(DataSnapshot beingSnapshot : dataSnapshot.getChildren() )
                {
                    Being b = beingSnapshot.getValue(Being.class);
                    BL.add(b);
                }

                BeingList adapter = new BeingList(MainActivity.this,BL);
                LV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("beings");
        edname = (EditText) findViewById(R.id.editTextname);
        btn = (Button) findViewById(R.id.btnAdd);
        spgen = (Spinner) findViewById(R.id.spinnerGenres);
        LV = (ListView) findViewById(R.id.BeingLV);
        BL = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void addItem()
    {
        String name = edname.getText().toString().trim();
        String genre = spgen.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name))
        {
            String ID = databaseReference.push().getKey();
            Being b = new Being(ID,name,genre);
            //storing to db
            databaseReference.child(ID).setValue(b);

            Toast.makeText(this,"Added: "+name,Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Enter name first",Toast.LENGTH_SHORT).show();
        }
    }
}
