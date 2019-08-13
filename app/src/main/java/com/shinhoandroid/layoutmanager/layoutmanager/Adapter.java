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
 * @date on 2019/8/9 17:03
 */
public class  Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Item> list;
        private Context context;

    public Adapter(Context context, List<Item> list) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHolder(View.inflate(context, R.layout.item, null));
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
            TextView textView = ((MyHolder) holder).text;
            ((MyHolder) holder).lay.setBackground(list.get(position).color);
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


