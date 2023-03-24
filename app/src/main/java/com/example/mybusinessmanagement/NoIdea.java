package com.example.mybusinessmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class NoIdea extends AppCompatActivity {
    RecyclerView RecView;
    My_Adapter_2 adapter_2;
    TextView set_p_price;
    ImageButton check;
    DatabaseReference mDatabase;
    SearchView searchViewInput;
//    TextInputLayout searchInput;
//    TextInputEditText searchInputText;
//    ImageButton searchButton;
//    CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_idea);

        RecView = findViewById(R.id.RecView );


        RecView.setLayoutManager(new LinearLayoutManager (NoIdea.this));
        searchViewInput=findViewById ( R.id.searchViewInput );
        searchViewInput.clearFocus ();
        searchViewInput.setOnQueryTextListener ( new SearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch ( s );
                return true;
            }
        } );


        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance(Register.link).getReference().child("Users").child(Login.userID).child("Inventory"), Model.class)
                        .build();

        adapter_2 = new My_Adapter_2 (options);
        RecView.setAdapter(adapter_2);

//        ************************************************************************

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

                Intent next = new Intent (NoIdea.this, NoIdea.class);
                startActivity ( next );

            }
        } );



//        searchButton.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                String searchText=searchInputText.getText ().toString ();
//                processsearch ( searchText );
//            }
//        } );

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter_2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter_2.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s) {
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").child(Login.userID).child("Inventory").orderByChild("a_p_name").startAt(s).endAt(s+"\uf8ff"), Model.class)
                        .build();
        adapter_2= new My_Adapter_2 (options );
        adapter_2.startListening();
        RecView.setAdapter(adapter_2);
    }

}