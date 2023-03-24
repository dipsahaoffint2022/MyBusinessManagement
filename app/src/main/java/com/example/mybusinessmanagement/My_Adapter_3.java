package com.example.mybusinessmanagement;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Objects;

public class My_Adapter_3 extends FirebaseRecyclerAdapter<Model_1,My_Adapter_3.MyViewHolder> {

    private MyViewHolder holder;
    private int position;
    private com.example.mybusinessmanagement.Model_1 model_1;

    public My_Adapter_3(@NonNull FirebaseRecyclerOptions<Model_1> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull Model_1 model_1) {
        this.holder = holder;
        this.position = position;
        this.model_1 = model_1;

        holder.set_p_name.setText ( model_1.getA_p_name () );
        holder.set_p_price.setText ( model_1.getA_p_price () );
        holder.set_p_qty.setText ( model_1.getA_p_qty () );

        holder.set_p_delete.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_singlerow,parent,false);
        return  new MyViewHolder(view);
    }

   public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView set_p_name, set_p_price, set_p_qty;
        ImageButton set_p_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            set_p_name = itemView.findViewById ( R.id.set_p_name );
            set_p_price = itemView.findViewById ( R.id.set_p_price );
            set_p_qty = itemView.findViewById ( R.id.set_p_qty );

            set_p_delete = itemView.findViewById ( R.id.set_p_delete );



        }
    }
}
