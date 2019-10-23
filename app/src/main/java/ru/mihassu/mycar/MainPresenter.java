package ru.mihassu.mycar;

import ru.mihassu.mycar.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private SparePartsKeeper spKeeper;

    public MainPresenter() {
        this.spKeeper = new SparePartsKeeperSimple();
    }

    public SparePartsKeeper getSpKeeper() {
        return spKeeper;
    }

    @Override
    public void add(String name) {
        spKeeper.createNewSparePart(name);
        view.notifyAdapter();
    }
}
