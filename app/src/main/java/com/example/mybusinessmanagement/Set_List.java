package com.example.mybusinessmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Set_List extends AppCompatActivity {
    RecyclerView RecView;
    My_Adapter_3 adapter_3;
    TextView set_p_price;
    ImageButton check;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_list);

        RecView = findViewById(R.id.RecView );


        RecView.setLayoutManager(new LinearLayoutManager (Set_List.this));

        FirebaseRecyclerOptions<Model_1> options =
                new FirebaseRecyclerOptions.Builder<Model_1>()
                        .setQuery( FirebaseDatabase.getInstance(Register.link).getReference().child("Users").child(Login.userID).child("sell"), Model_1.class)
                        .build();

        adapter_3 = new My_Adapter_3 (options);
        RecView.setAdapter(adapter_3);

//        *********************************************************************

        set_p_price = findViewById ( R.id.set_p_price );
        check = findViewById ( R.id.check );
        mDatabase = FirebaseDatabase.getInstance (Register.link).getReference ().child("Users").child(Login.userID).child("price");

        mDatabase.addValueEventListener ( new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float sum = 0;
                for (DataSnapshot ds: snapshot.getChildren ()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue ();
                    Object price = map.get ( "a_p_price" );
                    float pValue = Float.parseFloat ( String.valueOf ( price ) );
                    sum += pValue;
                    set_p_price.setText ( Float.toString ( sum ) );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

        check.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                set_p_price.setText ( "000" );

                FirebaseDatabase.getInstance (Register.link).getReference ().child("Users").child(Login.userID).child("sell").removeValue ();
                FirebaseDatabase.getInstance (Register.link).getReference ().child("Users").child(Login.userID).child("price").removeValue ();

            }
        } );
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter_3.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter_3.stopListening();
    }
}