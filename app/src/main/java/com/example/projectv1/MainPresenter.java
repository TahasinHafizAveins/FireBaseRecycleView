package com.example.projectv1;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void setRecyclerView() {
        mView.setRecyclerView();
    }
}