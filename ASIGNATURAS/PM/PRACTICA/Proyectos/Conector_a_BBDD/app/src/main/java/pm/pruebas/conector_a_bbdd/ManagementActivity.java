package pm.pruebas.conector_a_bbdd;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class ManagementActivity extends BaseActivity {

    MaterialToolbar mgmt_appBar_top;

    ArrayList<Registro> registros_temp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        Intent intent_loadMainAct = new Intent(this, MainActivity.class);

        mgmt_appBar_top = findViewById(R.id.mgmt_appBar_top);

        mgmt_appBar_top.setNavigationOnClickListener(v -> startActivity(intent_loadMainAct));

        mgmt_appBar_top.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_addRecord) {
                cargarCrearRegistro();
                return true;
            }
            if (itemId == R.id.action_showRecords) {
                cargarMostrarRegistros();
                return true;
            }
            return false;
        });
    }

    private void cargarCrearRegistro() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View vistaDialogo = inflater.inflate(R.layout.dialog_add_record, null);

        EditText et_Id = vistaDialogo.findViewById(R.id.et_id);
        EditText et_Nombre = vistaDialogo.findViewById(R.id.et_nombre);
        EditText et_NumRegistro = vistaDialogo.findViewById(R.id.et_numRegistro);
        ImageButton btn_Guardar = vistaDialogo.findViewById(R.id.btn_grabarRegistro);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setView(vistaDialogo)
                .setCancelable(true) 
                .create();
        dialogo.show();

        btn_Guardar.setOnClickListener(v -> {
            String id = et_Id.getText().toString();
            String nombre = et_Nombre.getText().toString();
            String numRegistro = et_NumRegistro.getText().toString();

            guardarRegistro(id, nombre, numRegistro);

            dialogo.dismiss();
        });
    }
    private void guardarRegistro(String id, String nombre, String numRegistro) {
        Registro temp = new Registro(id, nombre, numRegistro);
        registros_temp.add(temp);
    }

    private void cargarMostrarRegistros() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_display_records, null);

        TableLayout tl = dialogView.findViewById(R.id.tl_recordsTable);
        ImageButton btn_DeleteRecords = dialogView.findViewById(R.id.btn_borrarRegistros);

        for (Registro registro: registros_temp) {
            crearFila(tl, registro);
        }

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(true)
                .create();
        dialogo.show();

        btn_DeleteRecords.setOnClickListener(v -> {
            // deleteAllRecords();
            dialogo.dismiss();
        });
    }

    private void crearFila(TableLayout tl, Registro registro) {
        TableRow tr = new TableRow(this);

        TextView tv_id = crearCampo(registro.getId());
        TextView tv_nombre = crearCampo(registro.getNombre());
        TextView tv_numRegistro = crearCampo(registro.getNumRegistro());

        tr.addView(tv_id);
        tr.addView(tv_nombre);
        tr.addView(tv_numRegistro);

        tl.addView(tr);
    }

    private TextView crearCampo(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(20);
        tv.setPadding(8, 8, 8, 8);
        return tv;
    }
}