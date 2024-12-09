package pm.pruebas.conector_a_bbdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class ManagementActivity extends BaseActivity {

    MaterialToolbar mgmt_appBar_top;

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
                return true;
            }
            if (itemId == R.id.action_removeRecords) {
                return true;
            }
            return false;
        });
    }
}