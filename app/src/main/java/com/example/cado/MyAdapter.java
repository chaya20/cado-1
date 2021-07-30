package com.example.cado;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Color> list;

    public MyAdapter(Context context, ArrayList<Color> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
   //@org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Color color = list.get(position);
        holder.ColorName.setText(color.getColorName());
        holder.SaveDate.setText(color.getSaveDate());
        Glide.with(holder.itemView)
                .load(color.getColorChip())
                .into(holder.ColorChip);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ColorName, SaveDate;
        ImageView ColorChip;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ColorChip = itemView.findViewById(R.id.ivColorChip);
            ColorName = itemView.findViewById(R.id.tvColorName);
            SaveDate = itemView.findViewById(R.id.tvSaveDate);

            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Color color = list.get(pos);
                    if ( pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(context, ColorSpec.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("colorname", color.getColorName());
                        intent.putExtra("colorchip",color.getColorChip());
                        context.startActivity(intent);
                    }
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public  void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
