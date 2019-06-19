package first.swufe.com.searchfornews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TeachersActivity extends AppCompatActivity implements View.OnClickListener {

    TextView teacherDetail;
    EditText teacherName;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        teacherDetail = findViewById(R.id.teacherDetail);
        teacherName = findViewById(R.id.teacherName);

        name = teacherName.getText().toString();

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.submitTName){
            teacherDetail.setText("");
        }
    }
}
