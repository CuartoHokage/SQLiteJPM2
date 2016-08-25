package com.facci.sqlitejpm;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facci.sqlitejpm.DBHelperJAPM.DBHelper;

public class MainActivity extends AppCompatActivity {

    DBHelper dbSQLITE;
    EditText Nombre,Apellido,Recinto, Nacimiento,ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbSQLITE= new DBHelper(this);
    }
    public void insertar(View v){
        Nombre=(EditText)findViewById(R.id.txtNombre);
        Apellido=(EditText)findViewById(R.id.txtApellido);
        Recinto=(EditText)findViewById(R.id.txtRecinto);
        Nacimiento=(EditText)findViewById(R.id.txtNacimiento);

        boolean insertadatos = dbSQLITE.Insertar(Nombre.getText().toString(), Apellido.getText().toString(), Recinto.getText().toString(), Integer.parseInt(Nacimiento.getText().toString()));

        if (insertadatos==true) {
            Toast.makeText(MainActivity.this, "Datos Ingresados", Toast.LENGTH_SHORT).show();
        }
        else{Toast.makeText(MainActivity.this,"Datos no ingresados ocurrio un error :c siga participando",Toast.LENGTH_SHORT).show();}
        }

    public void Consultar (View v) {
        Cursor res = dbSQLITE.consultarDatos();

        if (res.getCount() == 0) {
            Alerta("Error", "No se encontraron registros");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getString(3)+"\n");
            buffer.append("Año de Nacimiento : "+res.getInt(4)+"\n\n");
        }
        Alerta("Registros", buffer.toString());
    }
    private void Alerta (String titulo, String Mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }

    public void Modificar (View v) {
        Nombre = (EditText) findViewById(R.id.txtNombre);
        Apellido = (EditText) findViewById(R.id.txtApellido);
        Recinto = (EditText) findViewById(R.id.txtRecinto);
        Nacimiento = (EditText) findViewById(R.id.txtNacimiento);
        ID = (EditText) findViewById(R.id.editTextID);


        boolean ActualizandoDatos = dbSQLITE.modificarRegistro(ID.getText().toString(), Nombre.getText().toString(), Apellido.getText().toString(), Recinto.getText().toString(), Integer.parseInt(Nacimiento.getText().toString()));
        if(ActualizandoDatos)
            Toast.makeText(MainActivity.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,"Lo sentimos ocurrió un error",Toast.LENGTH_SHORT).show();

    }

    public void Eliminar (View v) {
        ID = (EditText) findViewById(R.id.editTextID);

        Integer registrosEliminados = dbSQLITE.eliminar(ID.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivity.this,"Registro eliminado correctamente",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }
    }
}

