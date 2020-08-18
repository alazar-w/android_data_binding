package com.example.databinding;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import com.example.databinding.databinding.ActivityMainBinding;
import com.example.databinding.models.Product;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //data binding
        //by default a binding class will be generated based on the name of the layout(activity_main.xml),and android studio will convert the name of the layout file to
        //Pascal case,then the data binding library appends binding to the converted pascal case => ActivityMainBinding
        ActivityMainBinding mBinding;

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        init();

    }
    private void init(){
        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container,fragment,getString(R.string.fragment_main));
        transaction.commit();
    }

    @Override
    public void inflateViewProductFragment(Product product) {
        ViewProductFragment fragment = new ViewProductFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_product),product);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container,fragment,getString(R.string.fragment_view_product));
        transaction.addToBackStack(getString(R.string.fragment_view_product));
        transaction.commit();

    }

    @Override
    public void showQuantityDialog() {
        Log.d(TAG, "showQuantityDialog: showing Quantity Dialog.");
        ChooseQuantityDialog dialog = new ChooseQuantityDialog();
        dialog.show(getSupportFragmentManager(), getString(R.string.dialog_choose_quantity));
    }

    @Override
    public void setQuantity(int quantity) {
        Log.d(TAG, "selectQuantity: selected quantity: " + quantity);

        ViewProductFragment fragment = (ViewProductFragment)getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_product));
        if(fragment != null){
            //if we also want to change the product to different on besides changing quantity
            //Products products = new Products();
            //fragment.mBinding.getProductView().setProduct(products.PRODUCTS[0]);

            fragment.mBinding.getProductView().setQuantity(quantity);
        }
    }

    @Override
    public void addToCart(Product product, int quantity) {
    }

    @Override
    public void inflateViewCartFragment() {
    }

    @Override
    public void setCartVisibility(boolean visibility) {
    }
}
