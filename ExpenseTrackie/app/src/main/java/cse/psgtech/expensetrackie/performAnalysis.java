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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;


public class performAnalysis extends Activity implements CompoundButton.OnCheckedChangeListener{
    Button home;
    int i, n = 10;
    Double amount;
    float a[] = new float[10];

    int Tsum = 55;
    int Tsquaresum = 385;
    double Expense_Sum = 0, ExpenseT_Sop = 0;
    double const1,const2;
    double n1,n2,n3,n4,n5,n6,n7,n8,n9,n10;
    int RandomNum;
    EditText i1,i2,i3,i4,i5,i6,i7,i8,i9,i10;
    TextView result;
    Random random=new Random();
    int check;
    //Scanner input=new Scanner(System.in);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "Calculate analysis");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_view_analysis);
        }
        else
        {
            setContentView(R.layout.view_analysis);
        }

        addListenerOnButton();

        i1 =(EditText)findViewById(R.id.loanDisp);
        i2 =(EditText)findViewById(R.id.i2);
        i3 =(EditText)findViewById(R.id.giftsDisp);
        i4 =(EditText)findViewById(R.id.otherDisp);
        i5 =(EditText)findViewById(R.id.salDisp);
        i6 =(EditText)findViewById(R.id.rentDisp);
        i7 =(EditText)findViewById(R.id.intDisp);
        i8 =(EditText)findViewById(R.id.i8);
        i9 =(EditText)findViewById(R.id.busiDisp);
        i10 =(EditText)findViewById(R.id.i10);
        //insert = (TextView)findViewById(R.id.disp);
        result=(TextView)findViewById(R.id.op);
        Switch mySwitch = null;
        mySwitch = (Switch) findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(this);



    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("tag", "on button re-switched on");
        if (isChecked) {
            calculate();
            // do something when check is selected
        } else {
            //do something when unchecked
        }
    }

    private void calculate() {
        try {
            n1 = Double.parseDouble(i1.getText().toString());
        } catch (NumberFormatException e) {
            n1 = 0; // your default value
        }
        try {
            n2 = Double.parseDouble(i2.getText().toString());
        } catch (NumberFormatException e) {
            n2 = 0; // your default value
        }
        try {
            n3 = Double.parseDouble(i3.getText().toString());
        } catch (NumberFormatException e) {
            n3 = 0; // your default value
        }
        try {
            n4 = Double.parseDouble(i4.getText().toString());
        } catch (NumberFormatException e) {
            n4 = 0; // your default value
        }
        try {
            n5 = Double.parseDouble(i5.getText().toString());
        } catch (NumberFormatException e) {
            n5 = 0; // your default value
        }
        try {
            n6 = Double.parseDouble(i6.getText().toString());
        } catch (NumberFormatException e) {
            n6 = 0; // your default value
        }
        try {
            n7 = Double.parseDouble(i7.getText().toString());
        } catch (NumberFormatException e) {
            n7 = 0; // your default value
        }
        try {
            n8 = Double.parseDouble(i8.getText().toString());
        } catch (NumberFormatException e) {
            n8 = 0; // your default value
        }
        try {
            n9 = Double.parseDouble(i9.getText().toString());
        } catch (NumberFormatException e) {
            n9 = 0; // your default value
        }
        try {
            n10 = Double.parseDouble(i10.getText().toString());
        } catch (NumberFormatException e) {
            n10 = 0; // your default value
        }

        Expense_Sum = n1+n2+n3+n4+n5+n6+n7+n8+n9+n10;
        ExpenseT_Sop = (n1*1)+(n2*2)+(n3*3)+(n4*4)+(n5*5)+(n6*6)+(n7*7)+(n8*8)+(n9*9)+(n10*10);


        const1 = ((Expense_Sum*Tsquaresum)-(ExpenseT_Sop*Tsum))/((n*Tsquaresum)-(Tsum*Tsum));
        const2 = ((n*ExpenseT_Sop)-(Expense_Sum*Tsum))/((n*Tsquaresum)-(Tsum*Tsum));
        amount = const1 + const2*11;
        int tot= (int) ExpenseT_Sop;
        RandomNum = (tot%(6))+1;
       // System.out.println("RandomNum :"+RandomNum);
        if(RandomNum == 1)
        {
           // System.out.println("\nEstimated value = "+amount);
            //result.setText("" + amount);
            result.setText(Double.toString(amount));

        }
        else if(RandomNum == 2)
        {
            //System.out.println("\nEstimated Value = "+1.05*amount);
            //result.setText("" + amount*1.05);
            result.setText(Double.toString(amount*1.05));
        }
        else if(RandomNum == 3)
        {
            //System.out.println("Estimated Value = "+0.95*amount);
            //result.setText("" + amount*0.95);
            result.setText(Double.toString(amount*0.95));
        }
        else if(RandomNum == 4)
        {
           // System.out.println("Estimated Value ="+Expense_Sum/n);
            //result.setText("" + Expense_Sum/n);
            result.setText(Double.toString(Expense_Sum/n));
        }
        else if(RandomNum == 5)
        {
           // System.out.println("Estimated Value ="+Expense_Sum*1.05/n);
            //result.setText("" + Expense_Sum*1.05/n);
            result.setText(Double.toString(Expense_Sum*1.05/n));
        }
        else
        {
            //System.out.println("Estimated Value ="+Expense_Sum*0.95/n);
            //result.setText(""+Expense_Sum*0.95/n);
            result.setText(Double.toString(Expense_Sum*0.95/n));
        }
    }
    public void addListenerOnButton() {
        Log.d("tag", "on home click");
        final Context context = this;
        home = (Button) findViewById(R.id.hme_rem);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, Main_menu.class);
                startActivity(intent);
            }
        });
    }


}
