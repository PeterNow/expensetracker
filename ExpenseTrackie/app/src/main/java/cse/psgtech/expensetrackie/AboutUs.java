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

public class AboutUs extends Activity {
    //Initializing the variables to be used in program
    Button home;
    int check;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "About us...");
        super.onCreate(savedInstanceState);
        //Code to make app full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Code to set xml layout in the class
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_aboutus);
        }
        else
        {
            setContentView(R.layout.aboutus);
        }


        //function call for button press activities
        addListenerOnButton();

    }


    public void addListenerOnButton() {
        final Context context = this;
        //Setting up variable to respective button by button id
        Log.d("tag", "on button pressed");
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
