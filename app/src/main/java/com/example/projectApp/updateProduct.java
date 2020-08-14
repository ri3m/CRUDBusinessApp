package com.example.projectApp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class updateProduct extends AppCompatActivity {

    EditText prodName, prodPrice,ProdDes;
    Button prodAdd;
    DBAdapter adapter;
    String prodNameInput, prodPriceInput, ProdDesInput;
    public RecyclerView.Adapter mAdapter;
    Intent update;
    Context context;
    int idI;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_updateproduct);

        prodName = findViewById(R.id.ProductName);
        prodPrice = findViewById(R.id.ProductPrice);
        ProdDes = findViewById(R.id.ProductDesc);
        prodAdd = findViewById(R.id.addProdcut);

        adapter = new DBAdapter(this);

        context=this;

        update = getIntent();
        final String id=update.getExtras().getString("id");
        String name=update.getExtras().getString("name");
        String price=update.getExtras().getString("price");
        String desc=update.getExtras().getString("desc");

        price=price.substring(0,price.length()-1);
        idI=Integer.parseInt(id);

        prodName.setText(name);
        prodPrice.setText(price);
        ProdDes.setText(desc);

        prodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodNameInput = prodName.getText().toString();
                prodPriceInput = prodPrice.getText().toString();
                ProdDesInput = ProdDes.getText().toString();

                if (prodNameInput.equals("") || prodPriceInput.equals("") || ProdDesInput.equals("")) {
                    if (prodNameInput.equals("")) {
                        prodName.setError("This field is required");
                    }
                    if (prodPriceInput.equals("")) {
                        prodPrice.setError("This field is required");
                    }
                    if (ProdDesInput.equals("")) {
                        ProdDes.setError("This field is required");
                    }
                } else {
                    boolean insert = adapter.updateProduct(id, prodNameInput, prodPriceInput, ProdDesInput);
                    if (insert) {
                        Toast.makeText(context,"Product updated successfully!",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context,"Product not updated successfully",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        this.mAdapter=adapter;
    }
    public RecyclerView.Adapter getAdapter(){
        return mAdapter;
    }
    @Override
    public void onBackPressed(){
        try {
            getAdapter().notifyDataSetChanged();

        } catch (NullPointerException ex){
            Intent login=new Intent(updateProduct.this, MainActivity.class);
            startActivity(login);
        }

    }


}