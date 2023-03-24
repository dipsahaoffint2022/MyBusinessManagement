package com.example.mybusinessmanagement;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Objects;

public class MyAdapter extends FirebaseRecyclerAdapter<Model,MyAdapter.MyViewHolder> {

    private MyViewHolder holder;
    private int position;
    private com.example.mybusinessmanagement.Model model;

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull Model model) {
        this.holder = holder;
        this.position = position;
        this.model = model;


        holder.v_p_name.setText(model.getA_p_name());
        holder.v_p_price.setText(model.getA_p_price());

        holder.delete.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance ( Register.link ).getReference ().child ( "Users" ).child ( Login.userID )
                        .child ( "Inventory" ).child( Objects.requireNonNull ( getRef ( position ).getKey () ) ).removeValue ();
            }
        } );

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.update.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_dialog_box))
                        .setExpanded(true,1100)
                        .create();
//                dialogPlus.show();
                View myview = dialogPlus.getHolderView();

                EditText u_p_name = myview.findViewById(R.id.u_p_name);
                EditText u_p_price = myview.findViewById(R.id.u_p_price);
                Button u_p_button = myview.findViewById(R.id.u_p_button);

                u_p_name.setText(model.getA_p_name());
                u_p_price.setText(model.getA_p_price());

                dialogPlus.show();

                u_p_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String u_product = u_p_name.getText().toString();
                        String u_price = u_p_price.getText().toString();

//                        Map<String,Object>map = new HashMap<>();
//                        map.put("u_p_name",u_p_name.getText().toString());
//                        map.put("u_p_name",u_p_price.getText().toString());

                        ProductData getdata = new ProductData(u_product,u_price);
                        FirebaseDatabase.getInstance(Register.link).getReference().child("Users").child(Login.userID).child("Inventory").child(u_product).setValue(getdata);
                        dialogPlus.dismiss();




                    }
                });

            }
        });

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return  new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView v_p_name, v_p_price;
        Button update,delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            v_p_name = itemView.findViewById(R.id.set_p_name );
            v_p_price = itemView.findViewById(R.id.set_p_price );
            update = itemView.findViewById(R.id.set );
            delete = itemView.findViewById ( R.id.delete );

        }
    }


}
