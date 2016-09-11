package cse.psgtech.expensetrackie;
//Importing the files needed.
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class Main_menu extends Activity {
    //Initializing the variables to be used in program
    Button Expense;
    Button Income;
    Button expvsinc;
    Button ViewInc;
    Button ViewExp;
    Button Remain;
    Button Analysis;
    Button Record;
    Button Calculator;
    Button aboutus;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "functions menu");
        super.onCreate(savedInstanceState);
        //Code to make app full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Code to set xml layout in the class
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu);
        }
        else
        {
            setContentView(R.layout.menu);
        }

        //function call for button press activities
        addListenerOnButton();



    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("No", null).show();
    }

    public void addListenerOnButton(){
        final Context context = this;
        Log.d("tag", "on button pressed");
        //Setting up variable to respective button by button id

        expvsinc=(Button)findViewById(R.id.btn_expinc);
        expvsinc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuExpenseVsIncome.class);
                startActivity(intent);
            }
        });
        ViewInc=(Button)findViewById(R.id.btn_view_inc);
        ViewInc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuViewIncome.class);
                startActivity(intent);
            }
        });
        ViewExp=(Button)findViewById(R.id.btn_view_exp);
        ViewExp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuViewExpense.class);
                startActivity(intent);
            }
        });
        Remain=(Button)findViewById(R.id.btn_remainder);
        Remain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuRemainder.class);
                startActivity(intent);
            }
        });
        Analysis=(Button)findViewById(R.id.btn_analysis);
        Analysis.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuAnalysis.class);
                startActivity(intent);
            }
        });
        Calculator=(Button)findViewById(R.id.btn_calc);
        Calculator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuCalculator.class);
                startActivity(intent);
            }
        });
        Expense=(Button)findViewById(R.id.add_exp);
        Expense.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuAddExpense.class);
                startActivity(intent);
            }
        });

        Income=(Button)findViewById(R.id.add_inc);
        Income.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuAddIncome.class);
                startActivity(intent);
            }
        });
        Record=(Button)findViewById(R.id.main_rec);
        Record.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, menuRecord.class);
                startActivity(intent);
            }
        });

    }
}
