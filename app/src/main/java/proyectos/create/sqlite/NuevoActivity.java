package proyectos.create.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import proyectos.create.sqlite.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText nombre, apellido,género,edad,dirección,telefono;
    Button btn_Guardar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        preferences = this.getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();
        nombre= findViewById(R.id.Nombre);
        apellido= findViewById(R.id.Apellido);
        género= findViewById(R.id.Género);
        edad= findViewById(R.id.Edad);
        dirección=findViewById(R.id.Dirección);
        telefono = findViewById(R.id.Telefono);
        btn_Guardar = findViewById(R.id.btn_Guardar);

        btn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id = dbContactos.insertarContacto(nombre.getText().toString(),
                        apellido.getText().toString(), género.getText().toString(),
                        edad.getText().toString(), dirección.getText().toString(),
                        telefono.getText().toString());

                if (id > 0){
                    Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                }else{
                    Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private boolean Dbcontactos(){
        return this.preferences.getBoolean(llave,false);
    }

    private void guardarSesion(boolean checked){
        editor.putBoolean(llave,checked);
        editor.apply();
    }

    public void limpiar(){
        nombre.setText("");
        apellido.setText("");
        género.setText("");
        edad.setText("");
        dirección.setText("");
        telefono.setText("");


    }

}