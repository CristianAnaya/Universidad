package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.uac.apmoviles.sqliteuniversidad.Adapter.DescargosAdapter;

public class ListaRegistros extends AppCompatActivity {

    ListView listView;
    DescargosAdapter descargosAdapter = null;
    public static List<Estudiante> descargoList = new ArrayList<>();
   EstudianteController ec;
    SearchView inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_registros);

        listView = findViewById(R.id.listRegistros);
        ec = new EstudianteController(this);
        descargoList = ec.obtenerDescargos();
        inputSearch = findViewById(R.id.itemSearch);
        descargosAdapter = new DescargosAdapter(ListaRegistros.this, descargoList);
        listView.setAdapter(descargosAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(ListaRegistros.this);
                final int positionToRemove = position;
                adb.setTitle("Escoge una accion");
                adb.setNegativeButton("EDITAR", new AlertDialog.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MostrarDialog(descargoList.get(position),position);
                        startActivity(new Intent(ListaRegistros.this,ActualizarActivity.class).putExtra("posicion",position));
                    }
                });
                adb.setPositiveButton("Eliminar", new AlertDialog.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ec.eliminar(descargoList.get(position).getCodigo());
                        startActivity(new Intent(ListaRegistros.this,MainActivity.class));
                        Toast.makeText(ListaRegistros.this, "Se a eliminado correctamente.", Toast.LENGTH_SHORT).show();

                    }
                });
                adb.show();

            }
        });
        inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getEstudiantes(s);
                return false;
            }
        });
        descargosAdapter.notifyDataSetChanged();

    }

    private void getEstudiantes(String search){
        descargoList.clear();

        ec.openDB();
        Estudiante estudiante = null;
        Cursor c = ec.retrieve(search);
        while (c.moveToNext()){
            String id = c.getString(0);
            String programa = c.getString(1);
            String internet = c.getString(2);
            String computadora = c.getString(3);
            String telefono = c.getString(4);

            estudiante = new Estudiante(id,programa,internet,telefono,computadora);
            descargoList.add(estudiante);
        }

        ec.closeDB();
        listView.setAdapter(descargosAdapter);
    }
}