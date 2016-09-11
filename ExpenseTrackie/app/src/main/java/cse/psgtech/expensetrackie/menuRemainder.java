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

public class menuRemainder extends Activity {
    Button home,todo;
    int check;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "remainder layout");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu_remainder);
        }
        else
        {
            setContentView(R.layout.menu_remainder);
        }

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        Log.d("tag", "on button click");
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
        todo = (Button) findViewById(R.id.btn_todo);
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, todoMainActivity.class);
                startActivity(intent);
            }
        });

    }
}
