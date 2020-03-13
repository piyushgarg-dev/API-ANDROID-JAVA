package dev.piyushgarg.jsonapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<Blog> blogArrayList;
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycard,parent,false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Blog currentItem = blogArrayList.get(position);
        holder.textView.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return blogArrayList.toArray().length;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
            public TextView textView;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.myText);
        }
    }
     ExampleAdapter(ArrayList<Blog> blogList){
        this.blogArrayList = blogList;
    }
}
