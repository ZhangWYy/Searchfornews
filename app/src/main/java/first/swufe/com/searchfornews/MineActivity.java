package first.swufe.com.searchfornews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class  MineActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView teacher;
    private TextView calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        teacher = findViewById(R.id.teachers);
        teacher.setOnClickListener(this);
        calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId()==R.id.teachers){
            intent.setClass(MineActivity.this,TeachersActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.calculate){
            intent.setClass(MineActivity.this,CalculateActivity.class);
            startActivity(intent);
        }
    }
}
