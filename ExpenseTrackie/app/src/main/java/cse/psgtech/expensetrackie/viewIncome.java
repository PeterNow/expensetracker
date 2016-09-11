package cse.psgtech.expensetrackie;

import android.app.Activity;
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


public class viewIncome extends Activity implements View.OnClickListener {

    Button home,delete;
    boolean flag;
    TextView tv;
    EditText id;
    int checkl;

    List<DBIncomesModel> list = new ArrayList<DBIncomesModel>();
    DatabaseHelper db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "view incomes layout");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        checkl = settings.Index;
        if(checkl==1)
        {
            setContentView(R.layout.b_view_all_income);
        }
        else
        {
            setContentView(R.layout.view_all_income);
        }

        addListenerOnButton();
        db = new DatabaseHelper(getApplicationContext());
        tv = (TextView) findViewById(R.id.tv);
        delete = (Button) findViewById((R.id.btn_delete));
        id = (EditText) findViewById(R.id.id);
        delete.setOnClickListener(this);
        list = db.getAllIncomeList();
        print(list);
    }

    public void print(List<DBIncomesModel> list)
    {
        // TODO Auto-generated method stub
        String value = "";
        Log.d("tag","incomes - print list");
        for(DBIncomesModel i : list) {
            if((i.place).length()==0&&(i.note).length()==0)
            {
                value = value+"\nID             : "+i.id+"\nDATE        : "+i.date+"\nAMOUNT  : "+i.amount+"\nCHEQUE    : "+i.cheque+"\nTYPE         : "+i.type+"\n\n";

            }
            else if((i.place).length()==0||(i.note).length()==0)
            {
                if((i.place).length()==0)
                {
                    value = value+"\nID      : "+i.id+"\nDATE    : "+i.date+"\nNOTE    : "+i.note+"\nAMOUNT  : "+i.amount+"\nCHEQUE  : "+i.cheque+"\nTYPE    : "+i.type+"\n\n";
                }
                else
                {
                    value = value+"\nID      : "+i.id+"\nDATE    : "+i.date+"\nPLACE   : "+i.place+"\nAMOUNT  : "+i.amount+"\nCHEQUE  : "+i.cheque+"\nTYPE    : "+i.type+"\n\n";
                }
            }
            else
            {
                value = value+"\nID               : "+i.id+" \nDATE         : "+i.date+"\nNOTE         : "+i.note+" \nPLACE      : "+i.place+"\nAMOUNT  : "+i.amount+"\nCHEQUE     : "+i.cheque+"\nTYPE          : "+i.type+"\n\n";
            }
        }
        tv.setText(value);
        tv.setTextColor(Color.BLACK);

    }

    public void addListenerOnButton() {
        Log.d("tag","on button click");
        final Context context = this;
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
        Log.d("tag","on click");
        if (v == findViewById(R.id.btn_delete)) {
            if(id.length()==0)
            {
                Toast.makeText(this, "Enter value for id", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String income_id = id.getText().toString();
                flag = db.deleteIncomeEntry(Integer.parseInt(income_id));
                id.setText("");
                if(flag==true)
                {
                    tv.setText("");
                    list = db.getAllIncomeList();
                    print(list);
                    Toast.makeText(this, "Income Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "No such id exists.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}





