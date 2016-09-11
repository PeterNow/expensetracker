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
import android.widget.TextView;

public class menuViewExpense extends Activity {
    Button home,viewAllexp,viewcat,viewchart;
    static int totexp;
    DatabaseHelper db;
    EditText tot;
    int check;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "view expense layout");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu_view_expense);
        }
        else
        {
            setContentView(R.layout.menu_view_expense);
        }

        addListenerOnButton();
        db = new DatabaseHelper(getApplicationContext());
        tot = (EditText) findViewById(R.id.view_exp);
        totexp = db.totexp();
        tot.setText(" " +totexp);
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

        viewAllexp = (Button) findViewById(R.id.view_all);
        viewAllexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, viewExpenses.class);
                startActivity(intent);
            }
        });

        viewcat = (Button) findViewById(R.id.viewcat);
        viewcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, viewExpensebyCategory.class);
                startActivity(intent);
            }
        });

        viewchart = (Button) findViewById(R.id.btn_chart);
        viewchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, graphExpenseActivity.class);
                startActivity(intent);
            }
        });
    }
}
