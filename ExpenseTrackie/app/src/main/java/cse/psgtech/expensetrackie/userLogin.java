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
import android.widget.EditText;
import android.widget.Toast;

public class userLogin extends Activity {
    //Initializing the variables to be used in program
    Button login,home;
    boolean flag;
    EditText username,pin;
    DatabaseHelper db;
    int check;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "login");
        //Code to make app full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Code to set xml layout in the class
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_user_login);
        }
        else
        {
            setContentView(R.layout.user_login);
        }

        //function call for button press activities
        addListenerOnButton();
        db = new DatabaseHelper(getApplicationContext());
        username = (EditText) findViewById(R.id.editText);
        pin = (EditText) findViewById(R.id.editText2);
    }



    public void addListenerOnButton() {
        final Context context = this;
        Log.d("tag","on button pressed");
        //Setting up variable to respective button by button id

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (username.length() == 0 || pin.length() == 0) {
                    Toast.makeText(context, "Login Authentication failed..Try again..", Toast.LENGTH_SHORT).show();
                } else {
                    flag = db.checkLoginEntry(username.getText().toString(), Integer.parseInt(pin.getText().toString()));
                    if (flag == false) {
                        Toast.makeText(context, "Login Authentication failed..Try again..", Toast.LENGTH_SHORT).show();
                        username.setText("");
                        pin.setText("");
                    } else {
                        username.setText("");
                        pin.setText("");
                        Toast.makeText(context, "Login Successful..", Toast.LENGTH_SHORT).show();
                        intent = new Intent(context, Main_menu.class);
                        startActivity(intent);
                    }
                }
            }
        });
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
