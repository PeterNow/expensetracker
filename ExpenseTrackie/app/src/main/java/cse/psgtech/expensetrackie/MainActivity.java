package cse.psgtech.expensetrackie;
//Importing the files needed.
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    //Initializing the variables to be used in program
    Button login;
    Button register,setting;
    DatabaseHelper db;
    int check;

    @Override
//TO EXIT THE APP ON PRESSING BACK BUTTON WHEN IN HOME
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(intent.ACTION_MAIN);
        intent.addCategory(intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "LOGIN/REGISTER - EXPENSE TRACKIE");
        super.onCreate(savedInstanceState);
        //Code to make app full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Code to set xml layout in the class
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_activity_main);
        }
        else
        {
            setContentView(R.layout.activity_main);
        }

        db = new DatabaseHelper(getApplicationContext());
        //function call for button press activities
        addListenerOnButton();

    }


    public void addListenerOnButton() {
        final Context context = this;
        //Setting up variable to respective button by button id
        Log.d("tag", "on button pressed");
        login = (Button) findViewById(R.id.loginbutton);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, userLogin.class);
                startActivity(intent);
            }
        });

        register = (Button) findViewById(R.id.btn_about);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, userRegister.class);
                startActivity(intent);

            }
        });

        setting = (Button) findViewById(R.id.btnsettings);
        setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, settings.class);
                startActivity(intent);

            }
        });

    }
}
