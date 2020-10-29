package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class ActualizarActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner programa;
    RadioGroup rgComputadora;
    RadioGroup rgInternet, rgTelefono;
    Button btnActualizar;
    int posicion;
    RadioButton radioButton, radioButton1, radioButton2;
    EstudianteController ec;
    Estudiante estudiante;
    EditText edtCodigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actualizar);

        programa = (Spinner) findViewById((R.id.spinnerActualizar));
        rgComputadora = (RadioGroup) findViewById((R.id.rgComputadoraActualizar));
        rgInternet = (RadioGroup) findViewById((R.id.rgInternetActualizar));
        rgTelefono = (RadioGroup) findViewById(R.id.rgTelefonoActualizar);
        btnActualizar = findViewById(R.id.btnActualizar);
        edtCodigo = findViewById(R.id.edtCodigo);
        posicion = getIntent().getIntExtra("posicion", 0);
        btnActualizar.setOnClickListener(this);
        edtCodigo.setText(ListaRegistros.descargoList.get(posicion).getCodigo());
        edtCodigo.setEnabled(false);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnActualizar:
                int radioId = rgComputadora.getCheckedRadioButtonId();
                int radioIdInternet = rgInternet.getCheckedRadioButtonId();
                int radioIdTelefono = rgTelefono.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);
                radioButton1 = findViewById(radioIdInternet);
                radioButton2 = findViewById(radioIdTelefono);

                ec = new EstudianteController(ActualizarActivity.this);


                //estudiante.setPrograma(programa.getSelectedItem().toString());

                    estudiante = new Estudiante(programa.getSelectedItem().toString(), radioButton1.getText().toString(), radioButton2.getText().toString(), radioButton.getText().toString());
                    ec.actualizarFechaHoraIngreso(estudiante,ListaRegistros.descargoList.get(posicion).getCodigo());


                Toast.makeText(this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActualizarActivity.this,ListaRegistros.class));
                break;
        }
    }
}