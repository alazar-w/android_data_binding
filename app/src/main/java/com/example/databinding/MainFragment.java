package com.example.databinding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.databinding.R;
import com.example.databinding.adapters.ProductsAdapter;
import com.example.databinding.databinding.FragmentMainBinding;
import com.example.databinding.models.Product;
import com.example.databinding.util.Products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "MainFragment";

    // Data binding
    FragmentMainBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMainBinding.inflate(inflater);
        //this is how we apply a listener when using data binding. we reference the binding,then the ID of the widget we want
        //to apply the listener to(i.e  android:id="@+id/swipe_refresh_layout">).
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        setupProductsList();

        return mBinding.getRoot();
    }

    private void setupProductsList(){
        Products products = new Products();
        List<Product> productsList = new ArrayList<>();
        productsList.addAll(Arrays.asList(products.PRODUCTS));

        mBinding.setProducts(productsList);
    }



    @Override
    public void onRefresh() {
        Products products = new Products();
        List<Product> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
        ((ProductsAdapter)mBinding.recyclerView.getAdapter()).refreshList(productList);

        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        (mBinding.recyclerView.getAdapter()).notifyDataSetChanged();
        mBinding.swipeRefreshLayout.setRefreshing(false);
    }
}
