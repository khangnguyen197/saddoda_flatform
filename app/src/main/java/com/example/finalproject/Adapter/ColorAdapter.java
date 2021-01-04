package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewholder> {


    Context context;
    List<Integer> colorList;        //Store new color
    ColorAdpaterListener listener;

    public ColorAdapter(Context context, List<Integer> colorList, ColorAdpaterListener listener) {
        this.context = context;
        this.colorList = colorList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ColorViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.color,parent,false);
        return new ColorViewholder(itemView);
    }

    /** Set color to each position in RecyclerView */
    @Override
    public void onBindViewHolder(@NonNull ColorViewholder holder, int position) {
            holder.color_selection.setBackgroundColor(colorList.get(position));
    }

    /** Get quantity color assign to RecyclerView */
    @Override
    public int getItemCount() {
        return colorList.size();
    }

    /** Pick color event*/
    public class ColorViewholder extends RecyclerView.ViewHolder  {

        public CardView color_selection;
        public ColorViewholder(View itemView){
            super(itemView);
            color_selection = (CardView) itemView.findViewById(R.id.color_selection);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.colorSelected(colorList.get(getAdapterPosition()));
                }
            });
        }
    }

    public  interface ColorAdpaterListener{
        void colorSelected(int color);
    }
}
