package cse.psgtech.expensetrackie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class menuViewIncome extends Activity {
    Button home,viewAllinc,viewcat,viewChart;
    EditText tot;
    static int totinc;
    DatabaseHelper db;
    int check;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "view Income layout");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu_view_income);
        }
        else
        {
            setContentView(R.layout.menu_view_income);
        }

        addListenerOnButton();
        db = new DatabaseHelper(getApplicationContext());
        tot = (EditText) findViewById(R.id.view_inc);
        totinc = db.totinc();
        tot.setText(" " + totinc);
    }

    public void addListenerOnButton() {
        Log.d("tag", "on button click");
        final Context context = this;
        home = (Button) findViewById(R.id.hme_dail);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, Main_menu.class);
                startActivity(intent);
            }
        });

        viewAllinc = (Button) findViewById(R.id.view_all_inc);
        viewAllinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, viewIncome.class);
                startActivity(intent);
            }
        });
        viewcat = (Button) findViewById(R.id.viewcat);
        viewcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, viewIncomebyCategory.class);
                startActivity(intent);
            }
        });
        viewChart = (Button) findViewById(R.id.btn_chart);
        viewChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, graphIncomeActivity.class);
                startActivity(intent);
            }
        });


    }
}
