package com.example.assignment2.ui.folder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignment2.FolderDetailActivity;
import com.example.assignment2.FolderUtil;
import com.example.assignment2.R;

public class FolderFragment extends Fragment {

    private FolderViewModel folderViewModel;
    private ListView listView;
    private String[] folderList;
    ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        folderViewModel =
                ViewModelProviders.of(this).get(FolderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_folder, container, false);
        listView = (ListView)root.findViewById(R.id.folderList);


        folderList = FolderUtil.getAllFolders(getActivity());
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,folderList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FolderDetailActivity.class);
                intent.putExtra("path", folderList[position]);

                startActivity(intent);
            }
        });
        return root;
    }

    public void refresh(){
        folderList = FolderUtil.getAllFolders(getActivity());
        adapter.notifyDataSetChanged();
    }


}