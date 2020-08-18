package com.example.databinding.dataBinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databinding.adapters.ProductsAdapter;
import com.example.databinding.models.Product;

import java.util.List;

//custom binding adapter
public class MainFragmentBindingAdapters {

    private static final int NUM_COLUMNS = 2;

    //when making a binding adapter we need to pass the layout or view type that we're going to be using it on. we're going to be setting up a RecyclerView therefore
    //using it on RecyclerView layout & the list of products we add to the adapter (setProductList(RecyclerView view, List<Product> products) )
    @BindingAdapter("productList")
    public static void setProductList(RecyclerView view, List<Product> products){
        if (products == null){
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if (layoutManager == null){
            view.setLayoutManager(new GridLayoutManager(view.getContext(), NUM_COLUMNS));
        }

        ProductsAdapter adapter = (ProductsAdapter) view.getAdapter();
        if (adapter == null){
            adapter = new ProductsAdapter(view.getContext(),products);
            view.setAdapter(adapter);
        }

    }
}
