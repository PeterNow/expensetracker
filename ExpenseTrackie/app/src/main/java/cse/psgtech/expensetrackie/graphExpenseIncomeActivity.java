package cse.psgtech.expensetrackie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class graphExpenseIncomeActivity extends Activity implements OnClickListener {
    Button  barGraph;
    DatabaseHelper db;
    int check;
    static int totexp,totinc,totbal;
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

        totexp = db.totexp();
        totinc = db.totinc();
        totbal = totinc - totexp;


    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.BarGraph:
                graphExpenseIncomePerformBarGraph bar = new graphExpenseIncomePerformBarGraph();
                Intent barIntent = bar.getIntent(this);
                startActivity(barIntent);
                break;
        }

    }

}
