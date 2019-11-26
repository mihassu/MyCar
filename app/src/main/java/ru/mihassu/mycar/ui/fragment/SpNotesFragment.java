package ru.mihassu.mycar.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.mihassu.mycar.R;
import ru.mihassu.mycar.domain.repository.SparePartsRepository;
import ru.mihassu.mycar.domain.repository.SparePartsRepositoryImpl;
import ru.mihassu.mycar.ui.activity.AddNewChangeActivity;
import ru.mihassu.mycar.ui.activity.MainActivity;
import ru.mihassu.mycar.ui.activity.MyListener;

import static ru.mihassu.mycar.ui.activity.MainActivity.REQUEST_NEW_CHANGE;

public class SpNotesFragment extends Fragment {

    private static final String SP_NAME = "name";
    private final String TAG = "SpNotesFragment";

    private SpNotesViewModel mViewModel;
    private RecyclerView recyclerView;
    private SpNotesAdapter adapter;
    private TextView spName;
    private Button addNewNote;
    private MyListener listener;



    public static SpNotesFragment newInstance(String name) {

        SpNotesFragment fragment = new SpNotesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SP_NAME, name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.spare_part_notes_fragment, container, false);
        initRecyclerView(v);
        spName = v.findViewById(R.id.spare_part_name);
        addNewNote = v.findViewById(R.id.button_add_new_note);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();

//        Передаем в адаптер список при создании этого фрагмента
        mViewModel.sparePartLiveData.observe(this, data -> {
            adapter.setDataList(data);
        });
        String name = getArguments().getString(SP_NAME);
        spName.setText(name);
        addNewNote.setOnClickListener((view) -> listener.showAddNewChangeActivity(name));
        mViewModel.getSpNotesByName(name);
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

    private void initViewModel() {
        //mViewModel = ViewModelProviders.of(this).get(SparePartViewModel.class);
        SparePartsRepository sparePartsRepository= new SparePartsRepositoryImpl();
        mViewModel = ViewModelProviders.of(this,
                new SparePartViewModelFactory(sparePartsRepository)).get(SpNotesViewModel.class);

//        Подписываем SparePartViewModel на события жизненного цикла
//        getLifecycle().addObserver(mViewModel);
    }

    private void initRecyclerView(View v) {
        recyclerView = v.findViewById(R.id.sparePartNotesRv);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        LinearLayoutManager layoutLinear = new LinearLayoutManager(getActivity());
        layoutLinear.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutLinear);

        //создать адаптер
        adapter = new SpNotesAdapter();
        recyclerView.setAdapter(adapter);

//        //показать фрагмент с подробностями при клике на заменяемую часть
//        adapter.setRVOnItemClickListener(new SpNotesAdapter.RVOnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//
//            }
//        });
//        //Разделитель
////        recyclerView.addItemDecoration(new ItemDivider(getActivity()));
    }


}
