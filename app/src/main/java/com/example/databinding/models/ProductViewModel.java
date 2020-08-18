package com.example.databinding.models;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.databinding.BR;
import com.example.databinding.models.Product;

public class ProductViewModel extends BaseObservable {
    private Product product;
    private int quantity;
    private boolean imageVisibility = false;


    @Bindable
    public boolean isImageVisibility() {
        return imageVisibility;
    }
    @Bindable
    public int getQuantity() {
        return quantity;
    }

    @Bindable
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        notifyPropertyChanged(BR.product);
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }
    public void setImageVisibility(boolean imageVisibility) {
        this.imageVisibility = imageVisibility;
        notifyPropertyChanged(BR.imageVisibility);
    }

    //we have a listener waiting for a change in the view,once the view gets changed,it then triggers another change in another view - this is know as "TWO WAY DATA BINDING"
    //RequestListener -> listens change in the view
    public RequestListener getCustomRequestListener(){
        return new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            //this is the instance the image is displayed,but we should tell which image is we r taking about to glide,-> we need to reference this RequestListener when we set
            //an image.
            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                setImageVisibility(true);
                return false;
            }
        };
    };

}
