package cse.psgtech.expensetrackie;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;


public class menuCalculator extends Activity {
    EditText number1;
    EditText number2;
    TextView result;
    Button addition;
    Button home;
    Button subtraction;
    Button division;
    Button exponential;
    Button multiplication;
    Button modulus;
    int check;
    double num1 = 0, num2 = 0, tot = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "Calculator layout");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu_calculator);
        }
        else
        {
            setContentView(R.layout.menu_calculator);
        }

        initControls();
        addListenerOnButton();
    }

    private void initControls() {
        Log.d("tag","on button click");
        number1 = (EditText) findViewById(R.id.text1);
        number2 = (EditText) findViewById(R.id.text2);
        result = (TextView) findViewById(R.id.result);
        addition = (Button) findViewById(R.id.add);
        subtraction = (Button) findViewById(R.id.sub);
        multiplication = (Button) findViewById(R.id.mul);
        division = (Button) findViewById(R.id.div);
        exponential = (Button) findViewById(R.id.expo);
        modulus = (Button) findViewById(R.id.mod);
        addition.setOnClickListener(new Button.OnClickListener() {
                                        public void onClick
                                                (View v) {
                                            add();
                                        }
                                    }
        );
        subtraction.setOnClickListener(new Button.OnClickListener() {
                                           public void onClick
                                                   (View v) {
                                               sub();
                                           }
                                       }
        );
        multiplication.setOnClickListener(new Button.OnClickListener() {
                                              public void onClick
                                                      (View v) {
                                                  mul();
                                              }
                                          }
        );
        division.setOnClickListener(new Button.OnClickListener() {
                                        public void onClick
                                                (View v) {
                                            div();
                                        }
                                    }
        );
        exponential.setOnClickListener(new Button.OnClickListener() {
                                           public void onClick
                                                   (View v) {
                                               expo();
                                           }
                                       }
        );
        modulus.setOnClickListener(new Button.OnClickListener() {
                                       public void onClick
                                               (View v) {
                                           mod();
                                       }
                                   }
        );
    }

    ;

    private void add() {
        if (number1.getText().length() == 0 || number2.getText().length() == 0) {
            result.setText("Please enter 2 numbers and try again...");
        } else {
            num1 = Double.parseDouble(number1.getText().toString());
            num2 = Double.parseDouble(number2.getText().toString());
            tot = num1 + num2;
            result.setText(Double.toString(tot));
        }
    }

    private void sub() {
        if (number1.getText().length() == 0 || number2.getText().length() == 0) {
            result.setText("Please enter 2 numbers and try again...");
        } else {
            num1 = Double.parseDouble(number1.getText().toString());
            num2 = Double.parseDouble(number2.getText().toString());
            tot = num1 - num2;
            result.setText(Double.toString(tot));
        }
    }

    private void div() {
        if (number1.getText().length() == 0 || number2.getText().length() == 0) {
            result.setText("Please enter 2 numbers and try again...");
        } else {
            num1 = Double.parseDouble(number1.getText().toString());
            num2 = Double.parseDouble(number2.getText().toString());
            tot = num1 / num2;
            result.setText(Double.toString(tot));
        }
    }

    private void mul() {
        if (number1.getText().length() == 0 || number2.getText().length() == 0) {
            result.setText("Please enter 2 numbers and try again...");
        } else {
            num1 = Double.parseDouble(number1.getText().toString());
            num2 = Double.parseDouble(number2.getText().toString());
            tot = num1 * num2;
            result.setText(Double.toString(tot));
        }
    }

    private void expo() {
        if (number1.getText().length() == 0 || number2.getText().length() == 0) {
            result.setText("Please enter 2 numbers and try again...");
        } else {
            num1 = Double.parseDouble(number1.getText().toString());
            num2 = Double.parseDouble(number2.getText().toString());
            tot = Math.pow(num1, num2);
            result.setText(Double.toString(tot));
        }
    }

    private void mod() {
        if (number1.getText().length() == 0 || number2.getText().length() == 0) {
            result.setText("Please enter 2 numbers and try again...");
        } else {
            num1 = Double.parseDouble(number1.getText().toString());
            num2 = Double.parseDouble(number2.getText().toString());
            tot = num1 % num2;
            result.setText(Double.toString(tot));
        }
    }

    public void addListenerOnButton() {
        Log.d("tag", "on button click...");
        final Context context = this;
        home = (Button) findViewById(R.id.hme);
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

