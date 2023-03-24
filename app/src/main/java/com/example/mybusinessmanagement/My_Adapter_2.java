package com.example.mybusinessmanagement;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.Objects;

public class My_Adapter_2 extends FirebaseRecyclerAdapter<Model, My_Adapter_2.MyViewHolder> {

    private MyViewHolder holder;
    private int position;
    private com.example.mybusinessmanagement.Model model;

    public My_Adapter_2(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull Model model) {
        this.holder = holder;
        this.position = position;
        this.model = model;

        holder.p_name.setText ( model.getA_p_name() );
        holder.p_price.setText ( model.getA_p_price () );
        //spinner array_list making below
        ArrayList<String> arr_unit= new ArrayList<>();

        arr_unit.add ( "kg" );
        arr_unit.add ( "g" );
        arr_unit.add ( "No." );

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<> ( holder.spinner.getContext (), android.R.layout.simple_spinner_dropdown_item,arr_unit );
        holder.spinner.setAdapter ( spinnerAdapter );
        holder.spinner.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition ( i ).toString ();
//                    Log.i ( "text",text );
                holder.p_unit.setText ( text );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );


//        set price

        holder.set_price.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(holder.p_unit.getText().toString().equals("g") && !holder.p1_qty.getText ().toString ().equals ("") && !holder.p_price.getText ().toString ().equals ( "" ) && !holder.p_name.getText ().toString ().equals ( "" )){
                    float p= (1000/(Float.parseFloat (holder.p1_qty.getText ().toString ())));
                    float generate_price = Float.parseFloat (holder.p_price.getText().toString())/p;
                    String g_p = String.valueOf ( generate_price );
                    holder.p_price.setText ( g_p );
                }
                else if(holder.p_unit.getText().toString().equals("kg") && !holder.p1_qty.getText ().toString ().equals ("") && !holder.p_price.getText ().toString ().equals ( "" ) && !holder.p_name.getText ().toString ().equals ( "" )){
                    float generate_price = Float.parseFloat ( holder.p1_qty.getText ().toString () )*Float.parseFloat ( holder.p_price.getText ().toString () );
                    String g_p = String.valueOf ( generate_price );
                    holder.p_price.setText ( g_p );
                }
                else if(holder.p_unit.getText().toString().equals("No.") && !holder.p1_qty.getText ().toString ().equals ("") && !holder.p_price.getText ().toString ().equals ( "" ) && !holder.p_name.getText ().toString ().equals ( "" )){
                    float generate_price = Float.parseFloat ( holder.p1_qty.getText ().toString () )*Float.parseFloat ( holder.p_price.getText ().toString () );
                    String g_p = String.valueOf ( generate_price );
                    holder.p_price.setText ( g_p );
                } else{
                    Toast.makeText ( holder.set_price.getContext (), "enter a valid unit or qty", Toast.LENGTH_SHORT ).show ();
                }


                   if (!holder.p1_qty.getText ().toString ().equals ( "" )) {
                       holder.rel2.setBackgroundColor ( android.graphics.Color.parseColor ( "#FFCC99" ) );


                       String p = holder.p_price.getText ().toString ();
                       holder.prod_name.setText ( holder.p_name.getText ().toString () );
                       holder.prod_price.setText ( holder.p_price.getText ().toString () + "₹" );
                       holder.prod_weight.setText ( holder.p1_qty.getText ().toString () + holder.p_unit.getText ().toString () );

                       String fd_name = holder.prod_name.getText ().toString ();
                       String fd_price = holder.prod_price.getText ().toString ();
                       String fd_qty = holder.prod_weight.getText ().toString ();
                       String fd_price_1 = p;

                       ProductData2 getdata = new ProductData2 ( fd_name, fd_price, fd_qty );
                       ProductData3 getdata2 = new ProductData3 ( fd_price_1 );

                       FirebaseDatabase.getInstance ( Register.link ).getReference ().child ( "Users" ).child ( Login.userID ).child ( "sell" ).child ( fd_name ).setValue ( getdata );
                       FirebaseDatabase.getInstance ( Register.link ).getReference ().child ( "Users" ).child ( Login.userID ).child ( "price" ).child ( fd_name ).setValue ( getdata2 );
                       holder.p_price.setText ( model.getA_p_price () );

                   } else {
                       Toast.makeText ( holder.set_price.getContext (), "please set a valid quantity", Toast.LENGTH_SHORT ).show ();
                   }
               }

        } );


        holder.delete_prod.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                holder.rel2.setBackgroundColor ( Color.WHITE );

                holder.prod_name.setText ( "" );
                holder.prod_price.setText ( "" );
                holder.prod_weight.setText ( "" );

                FirebaseDatabase.getInstance ( Register.link ).getReference ().child ( "Users" ).child ( Login.userID )
                        .child ( "sell" ).child( Objects.requireNonNull ( getRef ( position ).getKey () ) ).removeValue ();

                FirebaseDatabase.getInstance ( Register.link ).getReference ().child ( "Users" ).child ( Login.userID )
                        .child ( "price" ).child( Objects.requireNonNull ( getRef ( position ).getKey () ) ).removeValue ();

            }
        } );

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_layout_third,parent,false);
        return  new MyViewHolder(view);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{



        TextView p_name, p_price, p_unit, prod_name,prod_price,
         prod_weight;
        Spinner spinner;
        ImageButton set_price, delete_prod;
        RelativeLayout rel1, rel2;
        TextInputLayout p_qty;
        TextInputEditText p1_qty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            p_name = itemView.findViewById ( R.id.p_name );
            p_price = itemView.findViewById ( R.id.p_price );
            p1_qty = itemView.findViewById ( R.id.p1_qty );
            p_qty = itemView.findViewById ( R.id.p_qty );
            p_unit = itemView.findViewById ( R.id.p_unit );
            spinner = itemView.findViewById ( R.id.spinner );


            set_price = itemView.findViewById ( R.id.set_price );

            rel1 = itemView.findViewById ( R.id.rel1 );
            rel2 = itemView.findViewById ( R.id.rel2 );

            prod_name = itemView.findViewById ( R.id.prod_name );
            prod_price = itemView.findViewById ( R.id.prod_price );
            prod_weight = itemView.findViewById ( R.id.prod_weight );


            delete_prod= itemView.findViewById ( R.id.delete_prod );


        }
    }
}
