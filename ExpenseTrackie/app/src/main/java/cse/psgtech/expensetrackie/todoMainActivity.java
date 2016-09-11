package cse.psgtech.expensetrackie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class todoMainActivity extends Activity {
    //INITIALIZING THE VARIABLES...
    Button b1,b2,homebtn;
    EditText e1;
    String text,u;
    String un[]={"","","","","","","","","","","","","","",""};
    String pas;
    Cursor c;
    int i=0,check;
    String s,sqlquery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_activity_todo);
        }
        else
        {
            setContentView(R.layout.activity_todo);
        }


        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        e1=(EditText)findViewById(R.id.editText1);
        homebtn=(Button)findViewById(R.id.todo_hme);

        //ON ADDING ANY TASK OR WORK...

        b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                //CONVERTING THE TEXT IN TO STRING...
                text=e1.getText().toString();

                //CREATING A DATABASE OBJECT..HERE db is SQLITEDATABASE OBJECT AND
                // todo is our database name AND
                //WE ARE USING IT IN WRITE MODE
                SQLiteDatabase db=openOrCreateDatabase("todo", Context.MODE_WORLD_WRITEABLE, null);

                text="'"+text+"'";

                //SQL QUERY TO CREATE TABLE
                s="CREATE TABLE if not exists TODOLIST"+" ("+ "list" +" VARCHAR(100)"+");";
                db.execSQL(s);

                // QUERY TO INSERT THE DATA INTO TABLE
                sqlquery="INSERT INTO TODOLIST"+ " VALUES"+"("+text+");";
                db.execSQL(sqlquery);
                e1.setText("");

                Toast.makeText(getApplicationContext(), "TASK ADDED IN LIST", Toast.LENGTH_SHORT).show();
            }
        });

        //THIS WILL CALL ANOTHER ACTIVITY NAMED AS DETAIL.JAVA
        b2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("tag", "on button click");
                // TODO Auto-generated method stub


                startActivity(new Intent(getApplicationContext(),todoDetail.class));
            }
        });
        homebtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("tag", "on home button click..");
                // TODO Auto-generated method stub


                startActivity(new Intent(getApplicationContext(), Main_menu.class));
            }
        });
    }
}


