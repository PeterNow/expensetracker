package cse.psgtech.expensetrackie;
//Importing the files needed.
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class settings extends Activity {
    //Initializing the variables to be used in program
   // Button login;
    Button about,btnhome,btnsave;
    DatabaseHelper db;
    RadioGroup radioGroup;
    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

   // EditText textCheckedIndex;
    static int Index;
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
            setContentView(R.layout.b_settings);
        }
        else
        {
            setContentView(R.layout.settings);
        }

        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener);
        //textCheckedIndex = (EditText)findViewById(R.id.txt);
        db = new DatabaseHelper(getApplicationContext());
        //function call for button press activities
        addListenerOnButton();
        LoadPreferences();



    }

    OnCheckedChangeListener radioGroupOnCheckedChangeListener =
            new OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(checkedId);
                    int checkedIndex = radioGroup.indexOfChild(checkedRadioButton);
                   // textCheckedIndex.setText("checkedIndex = " + checkedIndex);
                    Index = checkedIndex;
                    SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                }};

    private void SavePreferences(String key, int value){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton)radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }



    public void addListenerOnButton() {
        final Context context = this;
        //Setting up variable to respective button by button id
        Log.d("tag", "on button pressed");


        about = (Button) findViewById(R.id.btn_about);
        about.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, AboutUs.class);
                startActivity(intent);

            }
        });

        btnhome = (Button) findViewById(R.id.settings_home);
        btnhome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        });
        btnsave = (Button) findViewById(R.id.btn_save);
        btnsave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
