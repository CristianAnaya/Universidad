package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, internet, programa, telefono,computadora;
    RadioButton rbInternetSi, rbInternetNo, rbComputadorSi, rbComputadorNo, rbTelefonoSi, rbTelefonoNo, radioButton, radioButton1, radioButton2;
    Button guardar, cancelar, listado;
    Spinner spinner;
    RadioGroup rgInternet, rgTelefono, rgComputadora;
    //  BaseDatos bd;
    Estudiante e;
    EstudianteController ec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codigo = findViewById(R.id.nombre);
        rbInternetSi = findViewById(R.id.rbSiInternet);
        rbInternetNo = findViewById(R.id.rbNoInternet);
        rbComputadorNo = findViewById(R.id.rbComputadorNo);
        rbComputadorSi = findViewById(R.id.rbComputadorSi);
        rbTelefonoSi = findViewById(R.id.rdSiCelular);
        rbTelefonoNo = findViewById(R.id.rbNoCelular);
        rgComputadora = findViewById(R.id.rgComputadora);
        rgTelefono = findViewById(R.id.rgTelefono);
        rgInternet = findViewById(R.id.rgInternet);
        spinner=(Spinner) findViewById(R.id.spinner);
        guardar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        listado = findViewById(R.id.btnlistado);
        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        listado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnguardar:
                int radioId = rgComputadora.getCheckedRadioButtonId();
                int radioIdInternet = rgInternet.getCheckedRadioButtonId();
                int radioIdTelefono = rgTelefono.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);
                radioButton1 = findViewById(radioIdInternet);
                radioButton2 = findViewById(radioIdTelefono);

                ec = new EstudianteController(this);

                if (rgComputadora.getCheckedRadioButtonId() == -1 || rgInternet.getCheckedRadioButtonId() == -1 || rgTelefono.getCheckedRadioButtonId() == -1 || codigo.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{     e = new Estudiante(codigo.getText().toString(), spinner.getSelectedItem().toString(), radioButton1.getText().toString(), radioButton2.getText().toString(), radioButton.getText().toString());
                ec.agregarEstudiante(e);
                    Toast.makeText(this, "Se a guardado correctamente", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnlistado:
                startActivity(new Intent(MainActivity.this,ListaRegistros.class));
                ec = new EstudianteController(this);
                Cursor c = ec.allEstudiantes();
                if (c != null) {
                    String cadena = "";
                    while (c.moveToNext()) {
                        cadena = cadena + (c.getString(1))+",";
                    }
                } else {
                    Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}
