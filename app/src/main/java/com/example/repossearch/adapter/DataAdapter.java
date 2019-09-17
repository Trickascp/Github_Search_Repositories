package com.example.repossearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repossearch.R;
import com.example.repossearch.model.Data;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Data> data;

    public DataAdapter(Context context, ArrayList<Data> DataList){

        inflater = LayoutInflater.from(context);
        this.data = DataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_repos, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final DataAdapter.ViewHolder holder, final int position) {

        holder.tv1.setText(data.get(position).getName());
        holder.tv2.setText(data.get(position).getDescription());

        holder.Clink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse(String.valueOf(data.get(position).getHtml_url()));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                holder.itemView.getContext().startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv1, tv2;
        CardView Clink;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.r_name);
            tv2 = itemView.findViewById(R.id.r_desc);
            Clink = itemView.findViewById(R.id.c_link);

        }
    }
}
