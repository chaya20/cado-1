package com.example.cado;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cado.R;
import com.example.cado.RcmdData;

import java.util.ArrayList;

public class RcmdAdapter extends RecyclerView.Adapter<RcmdAdapter.CustomViewHolder> {

    private ArrayList <RcmdData>arrayList;

    public RcmdAdapter(ArrayList<RcmdData> arrayList) {
        this.arrayList = arrayList;
    }

    private static void onClick(View v) {
    }

    @NonNull
    @Override
    public RcmdAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tononton_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RcmdAdapter.CustomViewHolder holder, int position) {
        holder.iv.setImageResource(arrayList.get(position).getIv());
        holder.tv1.setText(arrayList.get(position).getTv1());
        holder.tv2.setText(arrayList.get(position).getTv2());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName = holder.tv1.getText().toString();
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size():0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv;
        protected TextView tv1;
        protected TextView tv2;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv = (ImageView) itemView.findViewById(R.id.iv);
            this.tv1=(TextView) itemView.findViewById(R.id.tv1);
            this.tv2=(TextView) itemView.findViewById(R.id.tv2);
        }
    }
}