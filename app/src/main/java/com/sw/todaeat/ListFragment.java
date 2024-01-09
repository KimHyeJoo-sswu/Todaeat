package com.sw.todaeat;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListFragment extends Fragment {
    ListView safeList;
    ListView dangerList;

    StuffAdapter safeAdapter;
    StuffAdapter dangerAdapter;
    OnDatabaseCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnDatabaseCallback) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.list_fragment, container, false);
        rootView.setOnCreateContextMenuListener(this);

        safeList = (ListView) rootView.findViewById(R.id.safeList);
        dangerList = (ListView) rootView.findViewById(R.id.dangerList);

        safeAdapter = new StuffAdapter();
        dangerAdapter = new StuffAdapter();

        ArrayList<StuffInfo> result = callback.selectAll();

        ArrayList<StuffInfo> safe  = new ArrayList<StuffInfo>();
        ArrayList<StuffInfo> danger = new ArrayList<StuffInfo>();

        for(int i = 0; i < result.size(); i++) {
            try {
                Date today = new Date();
                Date d =new SimpleDateFormat("yyyy-MM-dd").parse(result.get(i).getDate());

                //날짜 차이 구하기
                long day = ((today.getTime() - d.getTime()) / 1000)/(60*60*24);
                if(day < 0) {
                    safe.add(result.get(i));
                } else {
                    danger.add(result.get(i));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        safeAdapter.setItems(safe);
        dangerAdapter.setItems(danger);

        safeList.setAdapter(safeAdapter);
        dangerList.setAdapter(dangerAdapter);

        safeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                StuffInfo item = (StuffInfo) safeAdapter.getItem(position);
                ((Yutong_MainActivity)getActivity()).openEdit(item.getName(), item.getDate());
            }
        });

        dangerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StuffInfo item = (StuffInfo) dangerAdapter.getItem(position);
                ((Yutong_MainActivity)getActivity()).openEdit(item.getName(), item.getDate());
            }
        });

        return rootView;
    }

    class StuffAdapter extends BaseAdapter {
        ArrayList<StuffInfo> items = new ArrayList<StuffInfo>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(StuffInfo item) {
            items.add(item);
        }

        public void setItems(ArrayList<StuffInfo> items) {
            this.items = items;
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            StuffItemView view = new StuffItemView(getContext());

            StuffInfo item = items.get(position);
            view.setName(item.getName());
            view.setDate(item.getDate());
            return view;
        }
    }
}