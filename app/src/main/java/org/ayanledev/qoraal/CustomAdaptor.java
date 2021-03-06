package org.ayanledev.qoraal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {

    Animation trans_animation;

    Activity activity;

    private Context context;
    private ArrayList<String>
            ids,
            titles,
            contents,
            dates;

    public CustomAdaptor(Activity activity, Context context, ArrayList<String> ids,
                         ArrayList<String> title,
                         ArrayList<String> content,
                         ArrayList<String> date){

        this.activity = activity;
        this.context = context;
        this.ids = ids;
        this.titles = title;
        this.contents = content;
        this.dates = date;

    }
    @NonNull
    @Override
    public CustomAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdaptor.MyViewHolder holder, final int position) {

        holder.id.setText(ids.get(position));
        holder.title.setText(titles.get(position));
        holder.content.setText(contents.get(position));


        holder.date.setText(dates.get(position));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateActivity.class);

                intent.putExtra("id", String.valueOf(ids.get(position)));
                intent.putExtra("title", String.valueOf(titles.get(position)));
                intent.putExtra("content", String.valueOf(contents.get(position)));
                intent.putExtra("date", String.valueOf(dates.get(position)));

                activity.startActivityForResult(intent, 1);


            }
        });


    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,
                title,
                content,
                date;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_tv);
            title = itemView.findViewById(R.id.title_tv);
            content = itemView.findViewById(R.id.content_tc);
            date = itemView.findViewById(R.id.time_tv);
            layout = itemView.findViewById(R.id.mainLayout);

            trans_animation = AnimationUtils.loadAnimation(context, R.anim.translate);
            layout.setAnimation(trans_animation);
        }
    }
}