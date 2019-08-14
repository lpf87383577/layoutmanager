package com.shinhoandroid.layoutmanager.layoutmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shinhoandroid.layoutmanager.Item;
import com.shinhoandroid.layoutmanager.R;

import java.util.List;

/**
 * @author Liupengfei
 * @describe TODO
 * @date on 2019/8/14 15:45
 */
public class Adapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> list;
    private Context context;

    public Adapter2(Context context, List<Item> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter2.MyHolder(View.inflate(context, R.layout.item2, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        TextView textView = ((Adapter2.MyHolder) holder).text;
        ((Adapter2.MyHolder) holder).lay.setBackground(list.get(position).color);
        textView.setText(list.get(position).des);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(holder.getAdapterPosition()).des, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private FrameLayout lay;

        public MyHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.content_tv);
            lay = (FrameLayout) itemView.findViewById(R.id.lay);
        }
    }
}
