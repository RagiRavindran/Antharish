package com.apk.ragi.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {
    EditText editText;
    Button button;
    ListView listViewNo;
    DatabaseReference db;
    List<Number> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        db= FirebaseDatabase.getInstance().getReference("numbers");

        editText=(EditText)findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button);
        listViewNo=(ListView)findViewById(R.id.listViewNo);
        numbers=new ArrayList<>();
        listViewNo.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.length() == 0){
                    editText.setError("Field is empty");
                }
                else{
                    int number= Integer.parseInt(editText.getText().toString().trim());
                    String id= db.push().getKey();

                    Number numberz=new Number(id,number);
                    db.child(id).setValue(numberz);
                    editText.setText("");
                    Toast.makeText(MainScreen.this,"NUMBER INSERTED",Toast.LENGTH_LONG).show();
                    }

               listViewNo.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                numbers.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Number number = postSnapshot.getValue(Number.class);
                    numbers.add(number);
                }
                Numberlist adapter= new Numberlist(MainScreen.this,numbers);
                listViewNo.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
