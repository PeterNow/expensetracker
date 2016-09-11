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
import android.widget.TextView;


public class viewExpensebyCategory extends Activity{
    TextView foodDisp,medicalDisp,savingsDisp,shoppingDisp,enterDisp,transDisp,othersDisp;
    static int foodval=0,medicalval=0,savingsval=0,shoppingval=0,enterval=0,transval=0,othersval=0;
    Button home;
    DatabaseHelper db;
    int check;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "view category wise expense layout");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_view_expense_by_category);
        }
        else
        {
            setContentView(R.layout.view_expense_by_category);
        }

        db = new DatabaseHelper(getApplicationContext());

        foodDisp =(TextView)findViewById(R.id.loanDisp);
        medicalDisp =(TextView)findViewById(R.id.giftsDisp);
        savingsDisp =(TextView)findViewById(R.id.salDisp);
        shoppingDisp =(TextView)findViewById(R.id.intDisp);
        enterDisp =(TextView)findViewById(R.id.busiDisp);
        transDisp =(TextView)findViewById(R.id.rentDisp);
        othersDisp =(TextView)findViewById(R.id.otherDisp);

        foodval= db.expfood();
        medicalval= db.expmedical();
        savingsval= db.expsaving();
        shoppingval= db.expshopping();
        enterval= db.expentertainment();
        transval= db.exptransportation();
        othersval= db.expothers();

        foodDisp.setText(" " +foodval);
        medicalDisp.setText(" " +medicalval);
        savingsDisp.setText(" " +savingsval);
        shoppingDisp.setText(" " +shoppingval);
        enterDisp.setText(" " +enterval);
        transDisp.setText(" " +transval);
        othersDisp.setText(" " +othersval);
        addListenerOnButton();

    }
    public void addListenerOnButton() {
        Log.d("tag","on home button click");
        final Context context = this;
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View arg0) {
                                        Intent intent;
                                        intent = new Intent(context, Main_menu.class);
                                        startActivity(intent);
                                    }
                                }
        );
    }
}
