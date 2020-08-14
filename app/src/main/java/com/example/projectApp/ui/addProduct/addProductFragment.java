package com.example.projectApp.ui.addProduct;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectApp.DBAdapter;
import com.example.projectApp.R;
import com.example.projectApp.ui.home.HomeFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class addProductFragment extends Fragment  {

    EditText prodName, prodPrice,ProdDes;
    Button prodAdd;
    DBAdapter adapter;
    String prodNameInput, prodPriceInput, ProdDesInput;
    Context context;
    public static final String FIRSTCHANNEL = "Chanel1";

    private NotificationManagerCompat notificationManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_addproduct,container,false);

        createnotficationchanels();

        prodName=root.findViewById(R.id.ProductName);
        prodPrice=root.findViewById(R.id.ProductPrice);
        ProdDes=root.findViewById(R.id.ProductDesc);
        prodAdd=root.findViewById(R.id.addProdcut);
        adapter = new DBAdapter(getActivity());


        prodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager=NotificationManagerCompat.from(v.getContext());

                prodNameInput=prodName.getText().toString();
                prodPriceInput=prodPrice.getText().toString();
                ProdDesInput=ProdDes.getText().toString();

                if(prodNameInput.equals("")||prodPriceInput.equals("")||ProdDesInput.equals("")){
                    if(prodNameInput.equals("")){
                        prodName.setError("This field is required");
                    }
                    if(prodPriceInput.equals("")){
                        prodPrice.setError("This field is required");
                    }
                    if(ProdDesInput.equals("")){
                        ProdDes.setError("This field is required");
                    }
                }
                else{
                    boolean insert = adapter.addProd(prodNameInput,prodPriceInput,ProdDesInput);
                    if (insert==true) {
                        context=v.getContext();
                        Toast.makeText(context,"Product added successfully!",Toast.LENGTH_LONG).show();
                        notificationManager=NotificationManagerCompat.from(v.getContext());
                        Notification notification= new NotificationCompat.Builder(v.getContext(),FIRSTCHANNEL)
                                .setSmallIcon(R.drawable.star)
                                .setContentTitle("")
                                .setContentText("You can update products now!")
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .build();
                        notificationManager.notify(1,notification);
                    }
                    else {
                        Toast.makeText(context,"Product not added successfully",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        return root;
    }
    public void notifyAdapter(RecyclerView.Adapter adapter, int size){
        adapter.notifyItemInserted(size-1);
    }
    private void createnotficationchanels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    FIRSTCHANNEL,
                    "chanel1",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Notification from app");

            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }

    }


}