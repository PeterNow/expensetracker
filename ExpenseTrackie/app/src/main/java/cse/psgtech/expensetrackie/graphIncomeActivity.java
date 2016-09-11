package cse.psgtech.expensetrackie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by aswin on 06-10-2015.
 **/
import android.app.Activity;
import android.view.View.OnClickListener;

public class graphIncomeActivity extends Activity implements OnClickListener{
    Button barGraph;
    DatabaseHelper db;
    int check;
    static int loanval=0,giftsval=0,salaryval=0,interestval=0,businessval=0,rentalval=0,othersval=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_graph_layout);
        }
        else
        {
            setContentView(R.layout.graph_layout);
        }

        barGraph = (Button) findViewById(R.id.BarGraph);
        barGraph.setOnClickListener(this);
        db = new DatabaseHelper(getApplicationContext());
        loanval= db.incloan();
        giftsval= db.incgifts();
        salaryval= db.incsalary();
        interestval= db.incinterest();
        businessval= db.incbusiness();
        rentalval= db.increntalincome();
        othersval= db.incothers();


    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.BarGraph:
                graphIncomePerformBarGraph bar = new graphIncomePerformBarGraph();
                Intent barIntent = bar.getIntent(this);
                startActivity(barIntent);
                break;
        }

    }

}

