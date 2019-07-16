package com.example.projectv1;

public interface MainContract {

    interface Presenter{
        void setRecyclerView();
    }
    interface View{
        void click(int itemNumber);
        void londClick(String message);
        void setRecyclerView();
        void playMusic(Item item);
    }
}