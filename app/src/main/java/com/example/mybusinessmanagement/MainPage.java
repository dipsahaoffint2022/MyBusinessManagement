package com.example.mybusinessmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {
    public ImageButton Inventory,noidea, set_list;
    public TextView name;
    public EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main_page );

        Inventory = findViewById ( R.id.Inventory );

        noidea = findViewById ( R.id.noidea );

        set_list = findViewById ( R.id.set_list );



        Inventory.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent next = new Intent ( MainPage.this, Inventory.class );
                startActivity ( next );
            }
        } );

        noidea.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent next = new Intent (MainPage.this,NoIdea.class);
                startActivity ( next );
            }
        } );

        set_list.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent next = new Intent (MainPage.this,Set_List.class);
                startActivity ( next );
            }
        } );

    }


}