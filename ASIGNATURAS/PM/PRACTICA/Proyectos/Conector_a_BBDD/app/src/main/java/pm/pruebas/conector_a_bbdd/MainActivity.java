package pm.pruebas.conector_a_bbdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends BaseActivity {
    private ImageButton btn_accederApp;
    private ProgressBar pb_progresoCarga;
    private Intent intent_loadMgmtAct;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent_loadMgmtAct = new Intent(MainActivity.this, ManagementActivity.class);

        btn_accederApp = findViewById(R.id.btn_accederApp);
        pb_progresoCarga = findViewById(R.id.pb_progresoCarga);

        pb_progresoCarga.setVisibility(View.GONE);

        btn_accederApp.setOnClickListener(v -> {
            pb_progresoCarga.setVisibility(View.VISIBLE);

            btn_accederApp.postDelayed(() -> {
                pb_progresoCarga.setVisibility(View.GONE);
                startActivity(intent_loadMgmtAct);
                finish(); // Quizás quitarlo ???
            }, 2000);
        });
    }
}