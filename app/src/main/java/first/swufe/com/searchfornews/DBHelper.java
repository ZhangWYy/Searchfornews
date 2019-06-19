package first.swufe.com.searchfornews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "teachers.db";
    public static final String TB_NAME = "name";
    public static final String TB_TITLE = "title";
    public static final String TB_DETAIL="information";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context,name,factory,version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
