package first.swufe.com.searchfornews;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NewsActivity extends ListActivity implements Runnable,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {


    String data[] ={"wait..."};
    Handler handler;
    private String logDate = "";
    private final String DATE_SP_KEY = "lastRateDateStr";

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public void run() {
        List<String> retList = new ArrayList<String>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.swufe.edu.cn/1456.html").get();
          //  Log.i(TAG, "run: "+ doc.title());
            Elements newsTables = doc.getElementsByTag("table");
            Element newsTable1 = newsTables.get(3);
            //Log.i(TAG, "run: table1="+newsTable1);
            //获取td中的数据
            Elements tds = newsTable1.getElementsByTag("td");
            Log.i(TAG, "run:tds "+tds);
            List<MoreItem> rateList = new ArrayList<MoreItem>();

            for(int i=2;i<tds.size();i+=3){
                Element td1 = tds.get(i);
                Log.i(TAG, "run:td1 "+td1);
                String str1 = td1.text();
                Log.i(TAG, "NewsActivityRunStr1: "+str1);
                String str2 = "https://www.swufe.edu.cn/"+td1.select("a").attr("href");
             //   Log.i(TAG, "run:"+td1.text() + "==>" + td2.text());
                retList.add("\n"+str1+"\n"+str2+"\n");
                rateList.add(new MoreItem(str1,str2));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
    }




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //由于父类ListActivity中已经包含了布局，因此不用再通过页面填充
        //setContentView(R.layout.activity_rate_list);

        SharedPreferences sp = getSharedPreferences("myData", Context.MODE_PRIVATE);
        logDate = sp.getString(DATE_SP_KEY, "");
        Log.i("List","lastRateDateStr=" + logDate);

        List<String> list1 = new ArrayList<String>();
        for (int i = 1; i < 100; i++) {
            list1.add("item" + i);
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data );
        //把当前页面用adapter管理
        setListAdapter(adapter);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==7){
                    List<String> list2 = (List<String>)msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(NewsActivity.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
    }

   /* private String TAG = "NewsActivity";
    Handler handler;
    private List<HashMap<String,String>> listItems;
    private SimpleAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==7){
                    listItems = (List<HashMap<String,String>>)msg.obj;
                    listItemAdapter = new SimpleAdapter(NewsActivity.this,listItems,//listItems数据源
                            R.layout.list_item,//listItem的XML布局,描述一行数据的布局方式
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail});
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
    }

    private  void initListView(){
        listItems = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<10;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            //标题文字
            map.put("ItemTitle","Rate:"+i);
            //描述详情
            map.put("ItemDetail","detail:"+i);
            listItems.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this,listItems,//listItems数据源
                R.layout.list_item,//listItem的XML布局,描述一行数据的布局方式
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail});
    }


    private WebView webView;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String> map = (HashMap<String, String>) getListView().getItemAtPosition(position);
       // String titleStr = map.get("ItemTitle") ;
        String href = map.get("DetailTitle");
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(href);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


        return true;
    }

    @Override
    public void run() {
        List<HashMap<String,String>> retList = new ArrayList<HashMap<String,String>>();
        Document doc = null;
        try {
            Thread.sleep(1000);
            doc = Jsoup.connect("https://www.swufe.edu.cn/1456.html").get();
            Log.i(TAG, "run: "+ doc.title());
            Elements newsTables = doc.getElementsByTag("table");
            Element newsTable1 = newsTables.get(3);
            Log.i(TAG, "run: table1="+newsTable1);
            Elements newsTds = newsTable1.getElementsByTag("td");
            for(int i=0;i<newsTds.size();i+=3){
                Element td1 = newsTds.get(i);
                String str1 = td1.text();
                String str2 = "https://www.swufe.edu.cn/"+td1.select("a").attr("href");

                HashMap<String,String> map = new HashMap<String,String>();
                map.put("ItemTitle",str1);
                map.put("ItemDetail",str2);
                retList.add(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
    }*/

}
