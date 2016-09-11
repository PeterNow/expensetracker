package cse.psgtech.expensetrackie;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class viewExpenses extends Activity implements View.OnClickListener {

    Button home,delete;
    boolean flag;
    TextView tv;
    EditText id;
    int checkl;

    List<DBExpensesModel> list = new ArrayList<DBExpensesModel>();
    DatabaseHelper db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "print list expense");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        checkl = settings.Index;
        if(checkl==1)
        {
            setContentView(R.layout.b_view_all_expenses);
        }
        else
        {
            setContentView(R.layout.view_all_expenses);
        }

        addListenerOnButton();
        db = new DatabaseHelper(getApplicationContext());
        tv = (TextView) findViewById(R.id.tv);
        delete = (Button) findViewById((R.id.btn_delete));
        id = (EditText) findViewById(R.id.id);
        delete.setOnClickListener(this);
        list = db.getAllExpenseList();
        print(list);


    }
    public void print(List<DBExpensesModel> list)
    {
        // TODO Auto-generated method stub
        String value = "";
        for(DBExpensesModel e : list) {
            if((e.place).length()==0&&(e.note).length()==0)
            {
                value = value+"\nID      : "+e.id+"\nDATE    : "+e.date+"\nAMOUNT  : "+e.amount+"\nCHEQUE  : "+e.cheque+"\nTYPE    : "+e.type+"\n\n";

            }
            else if((e.place).length()==0||(e.note).length()==0)
            {
                if((e.place).length()==0)
                {
                    value = value+"\nID      : "+e.id+"\nDATE    : "+e.date+"\nNOTE    : "+e.note+"\nAMOUNT  : "+e.amount+"\nCHEQUE  : "+e.cheque+"\nTYPE    : "+e.type+"\n\n";
                }
                else
                {
                    value = value+"\nID      : "+e.id+"\nDATE    : "+e.date+"\nPLACE   : "+e.place+"\nAMOUNT  : "+e.amount+"\nCHEQUE  : "+e.cheque+"\nTYPE    : "+e.type+"\n\n";
                }
            }
            else
            {
                value = value+"\nID               : "+e.id+" \nDATE         : "+e.date+"\nNOTE         : "+e.note+" \nPLACE       : "+e.place+"\nAMOUNT  : "+e.amount+"\nCHEQUE   : "+e.cheque+"\nTYPE         : "+e.type+"\n\n";
            }
        }
        tv.setText(value);
        tv.setTextColor(Color.BLACK);

    }
    public void addListenerOnButton() {
        final Context context = this;
        Log.d("tag", "on home button click");
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, Main_menu.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.d("tag","on button click");
        if (v == findViewById(R.id.btn_delete)) {
            if(id.length()==0)
            {
                Toast.makeText(this,"Enter value for id",Toast.LENGTH_SHORT).show();
            }
            else
            {
                String expense_id = id.getText().toString();
                flag = db.deleteEntry(Integer.parseInt(expense_id));
                id.setText("");
                if(flag==true)
                {
                    tv.setText("");
                    list = db.getAllExpenseList();
                    print(list);
                    Toast.makeText(this, "Expense Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "No such id exists.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}





