package first.swufe.com.searchfornews;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {


    TextView detail_title;
    TextView detail_context;
    private String title;
    private String context;
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_title = findViewById(R.id.detail_title);
        detail_context = findViewById(R.id.detail_context);

        SharedPreferences sharedPreferences = getSharedPreferences("myData", Activity.MODE_PRIVATE);
        String aa = sharedPreferences.getString("news_http","null");
        Log.i(TAG, "onCreate: aa"+aa);
    }
}
