package com.example.projectv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View  {

    Button add;
    private RecyclerView recyclerView;


    String[] numberWord = {"One", "Two", "Three", "Four","Five","Six","Seven", "Eight", "Nine","Ten"};
    int[] numberImage = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten};
    int[] musics = {R.raw.amibethapeyechi,R.raw.amikhusihoyechi,R.raw.amiregejacchi,R.raw.amkbiroktokorona,R.raw.amkmoshakamracche,R.raw.amrangulketegiyeche,R.raw.amrpabethakorse,R.raw.amrpaketegiyeche,R.raw.shunekhubkhushihoyechi,R.raw.amrpafulegeca};
    private MainAdapter adapter;

    private MainPresenter mPresenter;
    private List<Item> itemList ;

    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareItemList();

        adapter = new MainAdapter(this,itemList);

        mPresenter = new MainPresenter(this);
        mPresenter.setRecyclerView();

        add = (Button)findViewById(R.id.add_Btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneAddProject();
            }
        });
    }

    private void prepareItemList() {
        this.itemList = new ArrayList<>();
        for ( int i= 0; i<numberWord.length;i++) {
            Item item = new Item();
            item.setTitle(numberWord[i]);
            item.setImage(numberImage[i]);
            item.setMusic(musics[i]);
             itemList.add(item);
        }
    }

    public void oneAddProject(){
        Intent intent = new Intent(this,AddProject.class);
        startActivity(intent);
    }

    public void click(int itemNUmber) {
        Toast.makeText(this, String.valueOf(itemNUmber), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void londClick(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void playMusic(Item item) {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(this, item.getMusic());
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopplayer();
                }
            });
        }
        mediaPlayer.start();
    }
    private void stopplayer(){
        if(mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this,"Media Player Released",Toast.LENGTH_SHORT).show();
        }
    }
}
