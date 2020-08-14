package com.example.projectApp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectApp.DBAdapter;
import com.example.projectApp.R;
import com.example.projectApp.product;
import com.example.projectApp.productAdapter;
import com.example.projectApp.updateProduct;

public class HomeFragment extends Fragment {
    RecyclerView productRV;
    RecyclerView.LayoutManager mLayoutManager;
    public RecyclerView.Adapter mAdapter;
    DBAdapter adapter;

    product product;
    ImageView update;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);



            try{
                adapter = new DBAdapter(getActivity());
                productRV=root.findViewById(R.id.product_rv);
                update=root.findViewById(R.id.update);

                productRV.setHasFixedSize(true);
                mLayoutManager=new LinearLayoutManager(getActivity());
                productRV.setLayoutManager(mLayoutManager);


                product=new product();
                product=adapter.getData();

                mAdapter=new productAdapter(product);
                com.example.projectApp.ui.addProduct.addProductFragment p=new com.example.projectApp.ui.addProduct.addProductFragment();
                p.notifyAdapter(mAdapter, product.getProductPriceAL().size());

                updateProduct updateFragment=new updateProduct();

                updateFragment.setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged();
                productRV.setAdapter(mAdapter);
            } catch (NullPointerException ex){
                Log.e("eroor", "no data yet");

            }






        return root;
    }


}
