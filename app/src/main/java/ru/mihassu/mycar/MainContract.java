package ru.mihassu.mycar;

import ru.mihassu.mycar.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.View {
        void notifyAdapter();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void add(String name);
    }
}
