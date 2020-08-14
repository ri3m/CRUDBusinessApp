
package com.example.projectApp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class DBAdapter {
    //works as a meduim, could be done in main activity
    SQLiteDatabase db, dbR;
    public DBAdapter(Context context){
        DBHelper myhelper = new DBHelper(context);
        db = myhelper.getWritableDatabase();
        dbR=myhelper.getReadableDatabase();
    }

    public boolean insertData(String name, String email, String password, int trade){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, name);
        contentValues.put(DBHelper.EMAIL, email);
        contentValues.put(DBHelper.PASSWORD, password);
        contentValues.put(DBHelper.TRADE, trade);
        //data in col, repeat put if more cols (?)
        long id = db.insert(DBHelper.TABLE_NAME, null , contentValues);
        if(id==-1) return false;
        else return true;
    }
    public boolean checkEmail(String email){
        Cursor cursor=dbR.rawQuery("SELECT * from user where email=?", new String[]{email});
        if(cursor.getCount()>0) return false; //email found, already registered
        else return true;
    }

    public boolean emailNPasswordCheck(String email, String password){
        Cursor cursor=dbR.rawQuery("SELECT * from user where email=? AND password=?", new String[]{email, password});
        if(cursor.getCount()>0) return true; //if there is a result then the user is registered
        else return false; //if there is no result then the user is not registered
    }
    public String username(String email){
        Cursor cursor=dbR.rawQuery("SELECT name from user where email=?", new String[]{email});
        if(cursor.getCount()>0){
            cursor.getString(2);
        }
         return "user";

    }

    public boolean addProd(String prodName, String prodPrice, String prodDes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.PRODNAME, prodName);
        contentValues.put(DBHelper.PRODPRICE, prodPrice);
        contentValues.put(DBHelper.PRODDESC, prodDes);

        long id = db.insert(DBHelper.TABLE_NAME_P, null , contentValues);
        if(id==-1) return false;
        else return true;

    }

    public product getData()   {
        String[] columns = {DBHelper.PID,DBHelper.PRODNAME, DBHelper.PRODPRICE,DBHelper.PRODDESC};
        Cursor cursor =db.query(DBHelper.TABLE_NAME_P,columns,null,null,null,null,null);
        //cursor to move through rows, true then there's more data

        product product=new product();
        ArrayList<Integer> idAL=new ArrayList<Integer>();
        ArrayList<String> nameAL=new ArrayList<String>();
        ArrayList<String> priceAL=new ArrayList<String>();
        ArrayList<String> descAL=new ArrayList<String>();
        //productIDAL

        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex(DBHelper.PID));
            String name =cursor.getString(cursor.getColumnIndex(DBHelper.PRODNAME));
            String price =cursor.getString(cursor.getColumnIndex(DBHelper.PRODPRICE));
            String desc =cursor.getString(cursor.getColumnIndex(DBHelper.PRODDESC));

            idAL.add(id);
            nameAL.add(name);
            priceAL.add(price);
            descAL.add(desc);
        }
            Collections.reverse(idAL);
            Collections.reverse(nameAL);
            Collections.reverse(priceAL);
            Collections.reverse(descAL);

            product.setProductIDAL(idAL);
            product.setProductNameAL(nameAL);
            product.setProductPriceAL(priceAL);
            product.setProdcutDesAL(descAL);

            return product;

    }

    public boolean updateProduct(String id, String prodName, String prodPrice, String prodDes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.PID, id);
        contentValues.put(DBHelper.PRODNAME, prodName);
        contentValues.put(DBHelper.PRODPRICE, prodPrice);
        contentValues.put(DBHelper.PRODDESC, prodDes);
        db.update(DBHelper.TABLE_NAME_P, contentValues, "_pid =?", new String[]{id});
        return true;
    }

    public boolean deleteProduct(String id){
        db.delete(DBHelper.TABLE_NAME_P, "_pid =?", new String[]{id});
        return true;
    }
}




