package cse.psgtech.expensetrackie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class graphExpenseActivity extends Activity implements OnClickListener {
    Button  barGraph;
    DatabaseHelper db;
    int check;
    static int foodval=0,medicalval=0,savingsval=0,shoppingval=0,enterval=0,transval=0,othersval=0;
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
        foodval= db.expfood();
        medicalval= db.expmedical();
        savingsval= db.expsaving();
        shoppingval= db.expshopping();
        enterval= db.expentertainment();
        transval= db.exptransportation();
        othersval= db.expothers();


    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.BarGraph:
                graphExpensePerformBarGraph bar = new graphExpensePerformBarGraph();
                Intent barIntent = bar.getIntent(this);
                startActivity(barIntent);
                break;
        }

    }

}
