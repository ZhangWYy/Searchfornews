package first.swufe.com.searchfornews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "teachers.db";
    public static final String TB_NAME = "tb_teachers";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context,name,factory,version);

    }

    public DBHelper(Context context){

        super(context,DB_NAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+TB_NAME+"(NAME TEXT PRIMARY KEY AUTOINCREMENT,TITILE TEXT,DETAIL TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
