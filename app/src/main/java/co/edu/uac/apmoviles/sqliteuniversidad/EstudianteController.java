package co.edu.uac.apmoviles.sqliteuniversidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EstudianteController {
    private BaseDatos bd;
    private Context c;
    SQLiteDatabase sql;
    public EstudianteController(Context c) {
        this.bd = new BaseDatos(c,2);
        this.c = c;
    }


    //OPEN DB
    public void openDB()
    {
        try
        {
            sql=bd.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //CLOSE
    public void closeDB()
    {
        try
        {
            bd.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public long agregarEstudiante(Estudiante e){
        try{
            sql = bd.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DefDB.col_codigo, e.getCodigo());
            cv.put(DefDB.col_programa, e.getPrograma());
            cv.put(DefDB.col_computadora, e.getComputadora());
            cv.put(DefDB.col_internet, e.getInternet());
            cv.put(DefDB.col_telefono, e.getTelefono());

            long id = sql.insert(DefDB.tabla_est,null,cv);
            return id;
        }
        catch (Exception ex){
            return 0;
        }
    }

    public Cursor allEstudiantes(){
        try{
            SQLiteDatabase data = bd.getReadableDatabase();
            Cursor cur = data.query(DefDB.tabla_est,null,null,null,null,null,null);
            return cur;
        }
        catch(Exception ex){
            Toast.makeText(c,"Error Consulta",Toast.LENGTH_LONG).show();
           return null;
        }
    }

    public List<Estudiante> obtenerDescargos(){
        SQLiteDatabase data = bd.getReadableDatabase();

        Cursor cursor = data.query(DefDB.tabla_est,null,null,null,null,null,null);
        List<Estudiante> descargos = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                descargos.add(new Estudiante(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return descargos;
    }

    public long actualizarFechaHoraIngreso(Estudiante estudiante, String codigo) {
        SQLiteDatabase sql = bd.getWritableDatabase();

        long rowId = -1;
        ContentValues contentValues = new ContentValues();

        contentValues.put(DefDB.col_programa, estudiante.getPrograma());

        if (!estudiante.getComputadora().equals("no"))
            contentValues.put(DefDB.col_computadora, estudiante.getComputadora());
        if (!estudiante.getInternet().equals("no"))
            contentValues.put(DefDB.col_internet, estudiante.getInternet());

        if (!estudiante.getTelefono().equals("no"))
            contentValues.put(DefDB.col_telefono, estudiante.getTelefono());


        rowId = sql.update(DefDB.tabla_est, contentValues, DefDB.col_codigo + " = ?", new String[]{codigo});

        return rowId;
    }

    public void eliminar(String codigo){
        SQLiteDatabase sql = bd.getWritableDatabase();
        sql.delete(DefDB.tabla_est, DefDB.col_codigo + " = ?", new String[]{codigo});
    }

    public Cursor retrieve(String searchTerm)
    {

        String[] columns = {DefDB.col_codigo, DefDB.col_programa, DefDB.col_internet, DefDB.col_computadora, DefDB.col_telefono};
        Cursor c= null;
        if (searchTerm != null && searchTerm.length()>0){
            String a = "SELECT * FROM "+DefDB.tabla_est+" WHERE "+DefDB.col_codigo+" LIKE '%"+searchTerm+"%'";
            c = sql.rawQuery(a,null);
            return c;
        }
        c = sql.query(DefDB.tabla_est,columns,null,null,null,null,null);
        return c;
    }
}
