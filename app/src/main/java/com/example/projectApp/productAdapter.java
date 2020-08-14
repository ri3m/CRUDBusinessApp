package com.example.projectApp;



import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {
    product product;



    public productAdapter(product product) {
        this.product=product;
    }
    @NonNull
    @Override
    public productAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final productAdapter.ViewHolder holder, final int position) {
            holder.ProductIDCard.setText(product.getProductIDAL().get(position)+"");
            holder.ProductNameCard.setText(product.getProductNameAL().get(position));
            holder.ProductPriceCard.setText(product.getProductPriceAL().get(position)+"$");
            holder.ProductDescCard.setText(product.getProdcutDesAL().get(position));


    }


    @Override
    public int getItemCount() {
        return product.getProductNameAL().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ProductNameCard;
        public TextView ProductDescCard;
        public TextView ProductPriceCard;
        public TextView ProductIDCard;
        public ImageView update, delete;

        productAdapter myAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ProductNameCard=itemView.findViewById(R.id.ProductNameCard);
            ProductDescCard=itemView.findViewById(R.id.ProductDescCard);
            ProductPriceCard=itemView.findViewById(R.id.ProductPriceCard);
            ProductIDCard=itemView.findViewById(R.id.ProductIDCard);

            update=itemView.findViewById(R.id.update);
            delete=itemView.findViewById(R.id.delete);

            final Context context;
            context = itemView.getContext();

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent update=new Intent(context, updateProduct.class);
                    update.putExtra("id",ProductIDCard.getText());
                    update.putExtra("name",ProductNameCard.getText());
                    update.putExtra("price",ProductPriceCard.getText());
                    update.putExtra("desc", ProductDescCard.getText());
                    int position=getAdapterPosition();

                    update.putExtra("position", position);

                    Log.e("error", "position"+position);
                    context.startActivity(update);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBAdapter adapter;
                    adapter = new DBAdapter(v.getContext());
                    int position=getAdapterPosition();


                    if(adapter.deleteProduct(product.getProductIDAL().get(position)+"")){
                        product.getProductNameAL().remove(position);
                        product.getProdcutDesAL().remove(position);
                        product.getProductPriceAL().remove(position);
                        product.getProductIDAL().remove(position);

                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,getItemCount());
                        Toast.makeText(v.getContext(),"Product deleted successfully!",Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(v.getContext(),"Product not deleted successfully",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }
}