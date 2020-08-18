package com.example.databinding.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databinding.IMainActivity;
import com.example.databinding.R;
import com.example.databinding.databinding.ProductItemBinding;
import com.example.databinding.models.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductsAdapter extends  RecyclerView.Adapter<ProductsAdapter.BindingHolder>{

    private static final String TAG = "ProductsAdapter";

    private List<Product> mProducts = new ArrayList<>();
    private Context mContext;


    public ProductsAdapter(Context context, List<Product> products) {
        mProducts = products;
        mContext = context;
    }

    public void refreshList(List<Product> products){
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewDataBinding binding = DataBindingUtil.inflate(
//                LayoutInflater.from(mContext), R.layout.product_item, parent, false);
        ProductItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.product_item, parent, false);

        return new BindingHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Product product = mProducts.get(position);
        //setProduct is a method that only exists in ProductItemBinding,not in the generic one
        holder.binding.setProduct(product);

        //BR is referencing the BR class file that contains all the data binding variables for the project.So instead of referencing the product variable through the
        //ProductItemBinding class,we can reference it directly from the BR class(import androidx.databinding.library.baseAdapters.BR).
        //holder.binding.setVariable(BR.product, product);

        //the "IMainActivity" interface is added to the view,which is also set in the layout product_item.xml
        holder.binding.setIMainActivity((IMainActivity)mContext);

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    //BindingHolder is what we name before viewHolder
    public class BindingHolder extends RecyclerView.ViewHolder{

        //when it comes to dataBinding in RecyclerViews,there's actually a generic class we can use to set any layout. that class is called "ViewDataBinding"
        // ViewDataBinding binding;

        //there is no difference if i use the binding class that was generated for the layout,w/c is product_item.xml(ProductItemBinding),or i use the generic one "ViewDataBinding"
        ProductItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }



}













