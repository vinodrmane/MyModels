package com.example.vinod.mymodels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinod.mymodels.Adapter.Model;
import com.example.vinod.mymodels.Adapter.Order;
import com.example.vinod.mymodels.Adapter.Product;
import com.example.vinod.mymodels.Adapter.RecyclerAdapter;

import java.util.ArrayList;

public class oneFragment extends Fragment implements RecyclerViewClickListener {
    RecyclerView recyclerView;

    public oneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_one, container, false);
      recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        ArrayList<Model> list= new ArrayList();
       list.add(new Model(Model.ORDER_TYPE,new Order("AHDH75645")));
       list.add(new Model(Model.PRODUCT_TYPE,new Product("Panneer")));
        list.add(new Model(Model.ORDER_TYPE,new Order("AHDH75645")));
        list.add(new Model(Model.PRODUCT_TYPE,new Product("Panneer")));

        list.add(new Model(Model.ORDER_TYPE,new Order("AHDH75645")));
        list.add(new Model(Model.PRODUCT_TYPE,new Product("Panneer")));

        list.add(new Model(Model.ORDER_TYPE,new Order("AHDH75645")));
        list.add(new Model(Model.PRODUCT_TYPE,new Product("Panneer")));

        list.add(new Model(Model.ORDER_TYPE,new Order("AHDH75645")));
        list.add(new Model(Model.PRODUCT_TYPE,new Product("Panneer")));

        list.add(new Model(Model.ORDER_TYPE,new Order("AHDH75645")));
        list.add(new Model(Model.PRODUCT_TYPE,new Product("Panneer")));

        list.add(new Model(Model.ORDER_TYPE,new Order("AHDH75645")));
        list.add(new Model(Model.PRODUCT_TYPE,new Product("Panneer")));

        RecyclerAdapter adapter = new RecyclerAdapter(list,getContext(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
      return view;
    }


    @Override
    public void onClick(View view, int position) {

    }
}
