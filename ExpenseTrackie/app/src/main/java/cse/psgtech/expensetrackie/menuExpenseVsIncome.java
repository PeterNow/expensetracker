package cse.psgtech.expensetrackie;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class menuExpenseVsIncome extends Activity{
    Button home,chart;
    DatabaseHelper db;
    int check;
    static int expense=0,income=0,balance=0;
    TextView exp,inc,bal;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "Expenses vs Income layout");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu_expense_income);
        }
        else
        {
            setContentView(R.layout.menu_expense_income);
        }

        addListenerOnButton();
        db = new DatabaseHelper(getApplicationContext());
        exp =(TextView)findViewById(R.id.tot_exp);
        inc =(TextView)findViewById(R.id.tot_inc);
        bal =(TextView)findViewById(R.id.tot_bal);

        expense=db.totexp();
        income=db.totinc();
        balance=income-expense;
        exp.setText(" " +expense);
        inc.setText(" " +income);
        bal.setText(" " +balance);
        if(expense<income)
        {
            bal.setTextColor(Color.GREEN);
        }
        else
        {
            bal.setTextColor(Color.RED);
        }




    }

    public void addListenerOnButton() {
        Log.d("tag", "on button click...");
        final Context context = this;
        home = (Button) findViewById(R.id.hme_yer);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, Main_menu.class);
                startActivity(intent);
            }
        });
        chart = (Button) findViewById(R.id.btn_chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, graphExpenseIncomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
