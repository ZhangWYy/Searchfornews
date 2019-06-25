package first.swufe.com.searchfornews;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends AppCompatActivity implements View.OnClickListener {

    TextView finalGrade;
    EditText credit;
    EditText grade;

    //private String[] =
    private int[][] data = new int[20][];
    private float sumB=0,a=0,b=0,sumA=0,gpa=0;
    private int i=1;
    private static final String TAG = "Calculate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        finalGrade = findViewById(R.id.finalGrade);
        credit = findViewById(R.id.credit);
        grade = findViewById(R.id.grade);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btn_confirm) {
            a = Integer.parseInt(credit.getText().toString());
            b = Integer.parseInt(grade.getText().toString());
            if(credit.getText().toString().equals(null)){
                Toast.makeText(this, "请输入学分！", Toast.LENGTH_SHORT).show();
            }else if(grade.getText().toString().equals(null)){
                Toast.makeText(this, "请输入成绩！", Toast.LENGTH_SHORT).show();
            }else if(b>=0&&b<60){
                Toast.makeText(this, "您该科的成绩不合格！", Toast.LENGTH_SHORT).show();
                sumA = sumA+a;//总学分
                sumB = 0 + sumB ;//单科绩点*学分的和
                credit.setText("");
                grade.setText("");
                gpa = sumB/sumA;
                finalGrade.setText("您的GPA为："+String.format("%.2f",gpa));
            }else if(b>=60){
                sumA = sumA+a;//总学分
                sumB = (b-50)/10*a + sumB ;//单科绩点*学分的和
                Log.i(TAG, "onClick: sumA="+sumA);
                Log.i(TAG, "onClick: sumB="+sumB);
                credit.setText("");
                grade.setText("");
                gpa = sumB/sumA;
                finalGrade.setText("您的GPA为："+String.format("%.2f",gpa));
            }




        }
    }
}

