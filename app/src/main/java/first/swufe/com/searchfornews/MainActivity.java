package first.swufe.com.searchfornews;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentTabHost ft;
    private String[] str ={"首页","我的"};
    private TextView bottom_bar_text_1;
    private TextView bottom_bar_text_2;
    private RelativeLayout bottom_bar_1_btn;
    private RelativeLayout bottom_bar_2_btn;
    private ImageView bottom_bar_image_1;
    private ImageView bottom_bar_image_2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_bar_1_btn = findViewById(R.id.bottom_bar_1_btn);
        bottom_bar_2_btn = findViewById(R.id.bottom_bar_2_btn);
        bottom_bar_1_btn.setOnClickListener(this);
        bottom_bar_2_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId()==R.id.bottom_bar_1_btn){
            intent.setClass(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.bottom_bar_2_btn){
            intent.setClass(MainActivity.this,MineActivity.class);
            startActivity(intent);
        }
    }
}
