package com.example.projectv1;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {


    private MainActivity mainActivity;
    private LayoutInflater inflater;
    private List<Item> itemList;

    MediaPlayer mediaPlayer;

    public MainAdapter( MainActivity mainActivity, List<Item> itemList) {
       this.itemList = itemList;
        this.mainActivity = mainActivity;
        this.inflater = LayoutInflater.from(mainActivity);
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_item,parent,false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MainHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView textView;
        ImageView imageView;

        public MainHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.number);
            imageView = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bind(Item item){
            textView.setText(item.getTitle());
            imageView.setImageResource(item.getImage());
        }

        @Override
        public void onClick(View view) {

            mainActivity.playMusic(itemList.get(getAdapterPosition()));
            Toast.makeText(mainActivity, "clicked", Toast.LENGTH_SHORT).show();
            /*mainActivity.click(getAdapterPosition()+1);

            ;*/

        }

        @Override
        public boolean onLongClick(View view) {
//            mainActivity.londClick("You pressed " + names[getAdapterPosition()]);
            return true;
        }


    }
}
