package ru.mihassu.mycar.ui.fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Observable;

import javax.inject.Inject;

import ru.mihassu.mycar.R;
import ru.mihassu.mycar.domain.repository.SparePartsRepository;
import ru.mihassu.mycar.domain.repository.SparePartsRepositoryImpl;
import ru.mihassu.mycar.ui.activity.MainActivity;
import ru.mihassu.mycar.ui.activity.MyListener;

public class SparePartFragment extends Fragment {


    private SparePartViewModel mViewModel;
    private RecyclerView recyclerView;
    private SparePartAdapter adapter;
    private final String TAG = "SparePartFragment";
    private MyListener listener;

    public static SparePartFragment newInstance() {
        return new SparePartFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toast.makeText(getActivity(), TAG + ": onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.spare_part_fragment, container, false);
        initRecyclerView(v);
//        Toast.makeText(getActivity(), TAG + ": onCreateView", Toast.LENGTH_SHORT).show();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
        //Передаем в адаптер список при создании этого фрагмента
        mViewModel.sparePartLiveData.observe(this, data -> {
            adapter.setDataList(data);
//            Log.w(TAG, "observe()");
        });
        mViewModel.getSparePartsList();
//        Toast.makeText(getActivity(), TAG + ": onActivityCreated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (MyListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView(View v) {
        recyclerView = v.findViewById(R.id.sparePartRv);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        LinearLayoutManager layoutLinear = new LinearLayoutManager(getActivity());
        layoutLinear.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutLinear);

        //создать адаптер
        adapter = new SparePartAdapter();
        recyclerView.setAdapter(adapter);

        //показать фрагмент с подробностями (SpNotesFragment) при клике на заменяемую часть
        adapter.setRVOnItemClickListener(new SparePartAdapter.RVOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                replaceSpNotesFragment(position);
                listener.replaceSpNotesFragment(adapter.getDataList().get(position).getName());
            }
        });
        //Разделитель
//        recyclerView.addItemDecoration(new ItemDivider(getActivity()));
    }

//    private void replaceSpNotesFragment(int position) {
//        //FragmentManager fm = getActivity().getSupportFragmentManager();
//        FragmentTransaction ft = MainActivity.activityComponent.getFragmentManager().beginTransaction();
//        ft.replace(R.id.container_main,
//                SpNotesFragment.newInstance(adapter.getDataList().get(position).getName()));
//        ft.addToBackStack("");
//        ft.commit();
//    }

    private void initViewModel() {
        //mViewModel = ViewModelProviders.of(this).get(SparePartViewModel.class);
        SparePartsRepository sparePartsRepository= new SparePartsRepositoryImpl();
        mViewModel = ViewModelProviders.of(this,
                new SparePartViewModelFactory(sparePartsRepository)).get(SparePartViewModel.class);

        //Подписываем SparePartViewModel на события жизненного цикла
//        getLifecycle().addObserver(mViewModel);
    }
}
