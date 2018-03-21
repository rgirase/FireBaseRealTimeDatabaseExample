package com.example.rahul.fribaserealtimedatabase;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

    private TextView HeaderText;
    private EditText HeaderInput;
    private Button UpdateHeader;
    private RadioButton RbRed,RbBlue;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference= firebaseDatabase.getReference();
    private DatabaseReference mHeaderReference=mRootReference.child("heading");
    private DatabaseReference mColorReference=mRootReference.child("color");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }


    private void  initViews(){
        HeaderText= (TextView) findViewById(R.id.header1);
        HeaderInput= (EditText) findViewById(R.id.editText);
        UpdateHeader= (Button) findViewById(R.id.button2);
        RbRed= (RadioButton) findViewById(R.id.radioButton);
        RbBlue= (RadioButton) findViewById(R.id.radioButton2);


    }


    public void onSubmitButtonClick(View view){
        String heading= HeaderInput.getText().toString();
        mHeaderReference.setValue(heading);
        HeaderInput.setText("");

    }

    public void onRadioButtonClick(View view){

        switch (view.getId()){

            case R.id.radioButton:
                mColorReference.setValue("red");
                break;

            case R.id.radioButton2:
                mColorReference.setValue("blue");
                break;
        }

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        if(dataSnapshot.getValue(String.class)!= null){

            String key= dataSnapshot.getKey();
            if(key.equals("heading")){

                String heading=dataSnapshot.getValue(String.class);
                HeaderText.setText(heading);
            }
            else if(key.equals("color")){

                String color= dataSnapshot.getValue(String.class);

                if(color.equals("red")){
                    HeaderText.setTextColor(ContextCompat.getColor(this,R.color.colorRed));
                    RbRed.setChecked(true);
                    RbBlue.setChecked(false);
                }
                else if(color.equals("blue")){
                    HeaderText.setTextColor(ContextCompat.getColor(this,R.color.colorBlue));
                    RbBlue.setChecked(true);
                    RbRed.setChecked(false);
                }

            }
        }


    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        mHeaderReference.addValueEventListener(this);
        mColorReference.addValueEventListener(this);
    }
}
