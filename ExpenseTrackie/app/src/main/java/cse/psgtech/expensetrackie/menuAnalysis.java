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

public class menuAnalysis extends Activity{
    Button home;
    Button analysis;
    int check;
    public void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "analysis layout..");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu_analysis);
        }
        else
        {
            setContentView(R.layout.menu_analysis);
        }

        addListenerOnButton();
    }
    public void addListenerOnButton() {
        final Context context = this;
        Log.d("tag", "on button click...");
        home = (Button) findViewById(R.id.hme_ana);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, Main_menu.class);
                startActivity(intent);
            }
        });
        analysis = (Button) findViewById(R.id.analy);
        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, performAnalysis.class);
                startActivity(intent);
            }
        });
    }
}
