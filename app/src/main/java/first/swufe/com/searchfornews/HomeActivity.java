package first.swufe.com.searchfornews;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,Runnable {

    private TextView newsDetail;
    private TextView newsDate;
    private TextView switch1;
    private TextView infoDetail;
    private TextView infoDate;
    private TextView switch2;
    private TextView courseDetail;
    private TextView courseDate;
    private TextView switch3;
    private TextView stateDetail;
    private TextView stateDate;
    private TextView switch4;
    private TextView date;

    private String strNews;
    private String strNewsDate;
    private String newsHttp;
    private String strInfo;
    private String strInfoDate;
    private String strCourse;
    private String strCourseDate;
    private String strState;
    private String strStateDate;
    private String[] newsStrs = {strNews,strNewsDate,newsHttp};

    Handler handler;
    private final String TAG = "HomeActivity:";
    private static final int COMPLETED = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        newsDetail = findViewById(R.id.newsDetail);
        newsDetail.setOnClickListener(this);

        newsDate = findViewById(R.id.newsDate);

        infoDetail = findViewById(R.id.infoDetail);
        infoDetail.setOnClickListener(this);
        infoDate = findViewById(R.id.infoDate);

        courseDetail = findViewById(R.id.courseDetail);
        courseDetail.setOnClickListener(this);
        courseDate = findViewById(R.id.courseDate);

        stateDetail = findViewById(R.id.stateDetail);
        stateDetail.setOnClickListener(this);
        stateDate = findViewById(R.id.stateDate);


        switch1 = findViewById(R.id.switch1);
        switch1.setOnClickListener(this);
        switch2 = findViewById(R.id.switch2);
        switch2.setOnClickListener(this);
        switch3 = findViewById(R.id.switch3);
        switch3.setOnClickListener(this);
        switch4 = findViewById(R.id.switch4);
        switch4.setOnClickListener(this);


        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr = sdf.format(today);
        date = findViewById(R.id.date);
        date.setText(todayStr.toString());

        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                 if (msg.what == COMPLETED) {
                     Bundle bdl = (Bundle) msg.obj;
                   // strNews = bdl.getString("news-detail");
                  //  strNewsDate = bdl.getString("news-date");

                    Log.i(TAG, "handleMessage: strNews"+strNews);
                    Log.i(TAG, "handleMessage: strNewsDate"+strNewsDate);

                    //保存更新的日期
                    SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("news_detail",strNews);
                    editor.putString("news_date",strNewsDate);
                    editor.putString("info_detail",strInfo);
                    editor.putString("info_date",strInfoDate);
                    editor.putString("course_detail",strCourse);
                    editor.putString("course_date",strCourseDate);
                    editor.putString("state_detail",strState);
                    editor.putString("state_date",strStateDate);
                    editor.apply();

                     newsDetail.setText(strNews);
                     newsDate.setText(strNewsDate);

                     infoDetail.setText(strInfo);
                     infoDate.setText(strInfoDate);

                     courseDetail.setText(strCourse);
                     courseDate.setText(strCourseDate);

                     stateDetail.setText(strState);
                     stateDate.setText(strStateDate);
                }
                super.handleMessage(msg);
            }


        };   

       // Log.i(TAG, "onCreate: "+"hhhhhhhhh");



    }

    private WebView webView;

    @Override
    public void onClick(View v) {

        SharedPreferences sharedPreferences = getSharedPreferences("myData", Activity.MODE_PRIVATE);

        Intent intent = new Intent();
        if(v.getId()==R.id.newsDetail){
            String aa = sharedPreferences.getString("news_http","null");
            //实例化webview对象
            webView = new WebView(this);
            //设置wubview属性，能够执行JavaScript脚本
            webView.getSettings().setJavaScriptEnabled(true);
            //打开网页
            webView.loadUrl(aa);

        }

        if(v.getId()==R.id.switch1){
            intent.setClass(HomeActivity.this,NewsActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.switch2){
            intent.setClass(HomeActivity.this,InfoActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.switch3){
            intent.setClass(HomeActivity.this,CourseActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.switch4){
            intent.setClass(HomeActivity.this,StateActivity.class);
            startActivity(intent);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

            Bundle bundle = data.getExtras();
            newsDate.setText(strNewsDate);
            newsDetail.setText(strNews);
            infoDate.setText(strInfoDate);
            infoDetail.setText(strInfo);
            courseDetail.setText(strCourse);
            courseDate.setText(strCourseDate);
            stateDetail.setText(strState);
            stateDate.setText(strStateDate);

            //将新设置的汇率写到SP里
            SharedPreferences sharedPreferences = getSharedPreferences("myData", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("news_detail",strNews);
            editor.putString("news_date",strNewsDate);

            editor.putString("news_http",newsHttp);

            editor.putString("info_detail",strInfo);
            editor.putString("info_date",strInfoDate);
            editor.putString("course_detail",strCourse);
            editor.putString("course_date",strCourseDate);
            editor.putString("state_detail",strState);
            editor.putString("state_date",strStateDate);


            //保存
        //    editor.commit();
            Log.i(TAG,"onActivityResult: 数据已保存到sharedPreferences");




        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void run() {
        Log.i(TAG, "run: run()......");

        Bundle bundle;
        bundle = getFromSwufe();

        SharedPreferences sharedPreferences = getSharedPreferences("myData", Activity.MODE_PRIVATE);
        strNews = sharedPreferences.getString("news_detail", "null");
        strNewsDate = sharedPreferences.getString("news_date", "null");
        newsHttp = sharedPreferences.getString("news_http", "null");
        strInfo = sharedPreferences.getString("info_detail","null");
        strInfoDate = sharedPreferences.getString("info_date","null");
        strCourse = sharedPreferences.getString("course_detail","null");
        strCourseDate = sharedPreferences.getString("course_date","null");
        strState = sharedPreferences.getString("state_detail","null");
        strStateDate = sharedPreferences.getString("state_date","null");


        Message msg = new Message();
        msg.what = COMPLETED;
        handler.sendMessage(msg);


    }


    private Bundle getFromSwufe(){
        Bundle bundle = new Bundle();
        Document doc = null;
        Document doc1 = null;
        Document doc2 = null;
        Document doc3 = null;

        try {
            doc = Jsoup.connect("https://www.swufe.edu.cn/1456.html").get();
            doc1 = Jsoup.connect("https://www.swufe.edu.cn/1457.html").get();
            doc2 = Jsoup.connect("https://www.swufe.edu.cn/1458.html").get();
            doc3 = Jsoup.connect("https://www.swufe.edu.cn/1459.html").get();

            Elements newsTables = doc.getElementsByTag("table");
            Element newsTable1 = newsTables.get(3);
            Elements newsTds = newsTable1.getElementsByTag("td");
            Element newsTd1 = newsTds.get(2);
            Element newsTd2 = newsTds.get(3);
          //  Log.i(TAG, "td1="+newsTd1);
          //  Log.i(TAG, "td2: "+newsTd2);
            String str1= newsTd1.text();
            String str2 = newsTd2.text();
            Elements newshrefs = newsTable1.getElementsByTag("a");
           // Elements hrefs = newshrefs.val("href");
          //  Elements hrefs1 = hrefs.tagName("href");
            String hy = newsTd1.select("a").attr("href");
           // Log.i(TAG, "newshrefs="+newshrefs);
           // Log.i(TAG, "hrefs: "+hrefs);

            String gh = "https://www.swufe.edu.cn/"+hy;
           // Log.i(TAG, "gh:"+gh);
            strNews = str1;
            strNewsDate = str2;
            newsHttp = gh;

            Elements infoTables = doc1.getElementsByTag("table");
            Element infoTable1 = infoTables.get(3);
            //    Log.i(TAG, "run: table1="+table1);
            Elements infoTds = infoTable1.getElementsByTag("td");
            Element infoTd1 = infoTds.get(2);
            Element infoTd2 = infoTds.get(3);
            Log.i(TAG, "td1="+infoTd1);
            Log.i(TAG, "td2: "+infoTd2);
            String str3= infoTd1.text();
            String str4 = infoTd2.text();
            // String str3 = td1.html();
            // Log.i(TAG, "str1: "+str1);
            strInfo = str3;
            strInfoDate = str4;

            Elements courseTables = doc2.getElementsByTag("table");
            Element courseTable1 = courseTables.get(3);
            //    Log.i(TAG, "run: table1="+table1);
            Elements courseTds = courseTable1.getElementsByTag("td");
            Element courseTd1 = courseTds.get(2);
            Element courseTd2 = courseTds.get(3);
            Log.i(TAG, "td1="+courseTd1);
            Log.i(TAG, "td2: "+courseTd2);
            String str5= courseTd1.text();
            String str6 = courseTd2.text();
            // String str3 = td1.html();
            // Log.i(TAG, "str1: "+str1);
            strCourse = str5;
            strCourseDate = str6;

            Elements stateTables = doc3.getElementsByTag("table");
            Element stateTable1 = stateTables.get(3);
            //    Log.i(TAG, "run: table1="+table1);
            Elements stateTds = stateTable1.getElementsByTag("td");
            Element stateTd1 = stateTds.get(2);
            Element stateTd2 = stateTds.get(3);
            Log.i(TAG, "td1="+stateTd1);
            Log.i(TAG, "td2: "+stateTd2);
            String str7= stateTd1.text();
            String str8 = stateTd2.text();
            // String str3 = td1.html();
            // Log.i(TAG, "str1: "+str1);
            strState = str7;
            strStateDate = str8;

            bundle.putString("news_detail",strNews);
            bundle.putString("news_date",strNewsDate);
            bundle.putString("info_detail",strInfo);
            bundle.putString("info_date",strInfoDate);
            bundle.putString("course_detail",strCourse);
            bundle.putString("course_date",strCourseDate);
            bundle.putString("state_detail",strState);
            bundle.putString("state_date",strStateDate);

            bundle.putString("news_http",newsHttp);

            SharedPreferences sharedPreferences = getSharedPreferences("myData", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("news_detail",strNews);
            editor.putString("news_date",strNewsDate);
            editor.putString("info_detail",strInfo);
            editor.putString("info_date",strInfoDate);
            editor.putString("course_detail",strCourse);
            editor.putString("course_date",strCourseDate);
            editor.putString("state_detail",strState);
            editor.putString("state_date",strStateDate);

            editor.putString("news_http",newsHttp);

            Log.i(TAG, "news_detail= "+strNews);
            Log.i(TAG, "news_date: "+strNewsDate);
            Log.i(TAG, "info_detail= "+strInfo);
            Log.i(TAG, "info_date: "+strInfoDate);
            Log.i(TAG, "course_detail= "+strCourse);
            Log.i(TAG, "course_date: "+strCourseDate);
            Log.i(TAG, "state_detail= "+strState);
            Log.i(TAG, "state_date: "+strStateDate);

            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
    }




}