package com.example.vinod.mymodels.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vinod.mymodels.R;
import com.example.vinod.mymodels.RecyclerViewClickListener;
import java.util.List;

/**
 * Created by Vinod on 7/11/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Model> modelList;
    Context context;
    int type;
    private RecyclerViewClickListener mListener;

    public RecyclerAdapter(List<Model> modelList, Context context, RecyclerViewClickListener mListener) {
        this.modelList = modelList;
        this.context = context;
        this.mListener=mListener;
     //   this.type = modelList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Model.ORDER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
                return new OrderRecyclerViewHolder(view,mListener);
            case Model.PRODUCT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
                return new ProdoctRecyclerViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Model model = modelList.get(position);
        switch (model.getType()) {
            case Model.ORDER_TYPE:
                OrderRecyclerViewHolder orderRecyclerViewHolder = (OrderRecyclerViewHolder) holder;
                Order order = model.getOrder();
                orderRecyclerViewHolder.TvId.setText(order.id);
             //  orderRecyclerViewHolder.setListener();   2nd way click
                break;
            case Model.PRODUCT_TYPE:
                ProdoctRecyclerViewHolder prodoctRecyclerViewHolder = (ProdoctRecyclerViewHolder) holder;
                Product product = model.getProduct();
                prodoctRecyclerViewHolder.TvName.setText(product.p_name);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (modelList != null) {
            Model model = modelList.get(position);
            return model.type;
        }
        return 0;
    }

    public void removeItem(int position){
        modelList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,modelList.size());
    }

    public void addItem(int position,Model model){
        modelList.add(position,model);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,modelList.size());
    }

}
