package pm.pruebas.conector_a_bbdd;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ManagementActivity extends BaseActivity {
    private final String FICHERO_REGISTROS = "registros.json";
    private MaterialToolbar mgmt_appBar_top;
    private ArrayList<Registro> registros_temp = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes_creadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        Intent intent_loadMainAct = new Intent(this, MainActivity.class);

        mgmt_appBar_top = findViewById(R.id.mgmt_appBar_top);

        mgmt_appBar_top.setNavigationOnClickListener(v -> startActivity(intent_loadMainAct));

        mgmt_appBar_top.inflateMenu(R.menu.top_app_bar_menu);

        mgmt_appBar_top.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_toggleDarkMode)
            {
                // Activar/desactivar modo nocturno
                return true;
            }
            if (itemId == R.id.action_addRecord)
            {
                cargarCrearRegistro();
                return true;
            }
            if (itemId == R.id.action_showRecords)
            {
                cargarMostrarRegistros();
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        guardarRegistros();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        cargarRegistros();
    }

    private void cargarCrearRegistro()
    {
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

            crearRegistro(id, nombre, numRegistro);

            dialogo.dismiss();
        });
    }

    private void crearRegistro(String id, String nombre, String numRegistro)
    {
        Registro temp = new Registro(id, nombre, numRegistro);
        registros_temp.add(temp);
    }

    private void cargarMostrarRegistros()
    {
        checkBoxes_creadas.clear();
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_display_records, null);

        TableLayout tl = dialogView.findViewById(R.id.tl_recordsTable);
        ImageButton btn_DeleteRecords = dialogView.findViewById(R.id.btn_borrarRegistros);

        for (Registro registro : registros_temp)
        {
            crearFila(tl, registro);
        }

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(true)
                .create();
        dialogo.show();

        btn_DeleteRecords.setOnClickListener(v -> {
            borrarRegistrosSeleccionados();
            cargarMostrarRegistros();
            dialogo.dismiss();
        });
    }

    private void crearFila(TableLayout tl, Registro registro)
    {
        TableRow tr = new TableRow(this);

        CheckBox cb_seleccionar = crearCheckbox(registro);
        TextView tv_id = crearCampo(registro.getId());
        TextView tv_nombre = crearCampo(registro.getNombre());
        TextView tv_numRegistro = crearCampo(registro.getNumRegistro());

        tr.addView(cb_seleccionar);
        tr.addView(tv_id);
        tr.addView(tv_nombre);
        tr.addView(tv_numRegistro);

        tl.addView(tr);
    }

    private CheckBox crearCheckbox(Object infoAsociada)
    {
        CheckBox cb = new CheckBox(this);
        cb.setTag(infoAsociada);
        checkBoxes_creadas.add(cb);
        return cb;
    }

    private TextView crearCampo(String text)
    {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(20);
        tv.setPadding(8, 8, 8, 8);
        return tv;
    }

    private void borrarRegistrosSeleccionados()
    {
        for (CheckBox cb : checkBoxes_creadas)
        {
            if (cb.isChecked())
            {
                Registro registroSeleccionado = (Registro) cb.getTag();
                for (Registro registro : registros_temp)
                {
                    if (registro.equals(registroSeleccionado))
                    {
                        registros_temp.remove(registro);
                        break;
                    }
                }
            }
        }
    }

    private void guardarRegistros()
    {
        try
        {
            JSONArray jsonArray = new JSONArray();
            for (Registro registro : registros_temp)
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", registro.getId());
                jsonObject.put("nombre", registro.getNombre());
                jsonObject.put("numRegistro", registro.getNumRegistro());
                jsonArray.put(jsonObject);
            }

            File file = new File(getFilesDir(), FICHERO_REGISTROS);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(jsonArray.toString().getBytes());
            fos.close();
        }
        catch (Exception e)
        {
            Log.e(this.getLocalClassName(), e.getMessage(), e);
        }
    }

    private void cargarRegistros()
    {
        try
        {
            File file = new File(getFilesDir(), FICHERO_REGISTROS);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                {
                    break;
                }
                sb.append(line);
            }
            br.close();
            JSONArray jsonArray = new JSONArray(sb.toString());

            registros_temp.clear(); // por si acaso no ha sido limpiada
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String nombre = jsonObject.getString("nombre");
                String numRegistro = jsonObject.getString("numRegistro");
                Registro nuevoRegistro = new Registro(id, nombre, numRegistro);
                registros_temp.add(nuevoRegistro);
            }
        }
        catch (Exception e)
        {
            Log.e(this.getLocalClassName(), e.getMessage(), e);
        }
    }
}
