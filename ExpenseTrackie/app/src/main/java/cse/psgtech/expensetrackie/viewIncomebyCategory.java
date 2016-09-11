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

public class viewIncomebyCategory extends Activity {
    TextView loanDisp,giftsDisp,salaryDisp,interestDisp,businessDisp,rentalDisp,othersDisp;
    static int loanval=0,giftsval=0,salaryval=0,interestval=0,businessval=0,rentalval=0,othersval=0;
    Button home;
    DatabaseHelper db;
    int check;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "view category wise income layout");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_view_income_by_category);
        }
        else
        {
            setContentView(R.layout.view_income_by_category);
        }



        db = new DatabaseHelper(getApplicationContext());
        loanDisp =(TextView)findViewById(R.id.loanDisp);
        giftsDisp =(TextView)findViewById(R.id.giftsDisp);
        salaryDisp =(TextView)findViewById(R.id.salDisp);
        interestDisp =(TextView)findViewById(R.id.intDisp);
        businessDisp =(TextView)findViewById(R.id.busiDisp);
        rentalDisp =(TextView)findViewById(R.id.rentDisp);
        othersDisp =(TextView)findViewById(R.id.otherDisp);
        loanval= db.incloan();
        giftsval= db.incgifts();
        salaryval= db.incsalary();
        interestval= db.incinterest();
        businessval= db.incbusiness();
        rentalval= db.increntalincome();
        othersval= db.incothers();

        loanDisp.setText(" " +loanval);
        giftsDisp.setText(" " +giftsval);
        salaryDisp.setText(" " +salaryval);
        interestDisp.setText(" " +interestval);
        businessDisp.setText(" " +businessval);
        rentalDisp.setText(" " +rentalval);
        othersDisp.setText(" " +othersval);
        addListenerOnButton();

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
                                }
        );
    }
}
