package com.example.petagrampersistencia.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.petagrampersistencia.R;
import com.example.petagrampersistencia.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;
    public ConstructorMascotas(Context context){
        this.context=context;
    }

    public ArrayList<Mascota> obtenerDatos(){
        BaseDatos db=new BaseDatos(context);
        //insertarDatos(db);
        return db.obtenerMascotas();
}
    public  void  insertarDatos(BaseDatos db){
        if(!ConstantesBaseDatos.DATOS_INSERTADOS){
            ContentValues contentValues=new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Gucci");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.puppy1);
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Norton");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.puppy2);
            db.insertarMascota(contentValues);
            ConstantesBaseDatos.DATOS_INSERTADOS=true;
        }

    }
    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA, mascota.getIdMascota());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_NUMERO, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }
    public ArrayList<Mascota> obtenerFavoritos(){
        BaseDatos db =new BaseDatos(context);
        ArrayList<Integer> id_favoritos= db.obtenerUltimos5Likes();
        ArrayList<Mascota> favoritos=new ArrayList<>();
        ArrayList<Mascota> datos=db.obtenerMascotas();
        for(int i =0;i<id_favoritos.size();i++){
            for (int j=0;j<datos.size();j++){
                if(datos.get(j).getIdMascota()==id_favoritos.get(i)){
                    favoritos.add(datos.get(j));
                }
            }
        }
        return favoritos;

    }


}
