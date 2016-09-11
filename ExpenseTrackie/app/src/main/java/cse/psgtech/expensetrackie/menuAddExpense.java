package cse.psgtech.expensetrackie;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class menuAddExpense extends Activity implements OnItemSelectedListener,View.OnClickListener {

    Button home,submit;
    EditText id,amounts,places,notes,cheques;
    Spinner spinner;
    String item;
    EditText selectedDate;
    DateFormat formate= DateFormat.getDateInstance();
    Calendar calender=Calendar.getInstance();
    TextView date;
    Button date_pick;
    List<DBExpensesModel> list = new ArrayList<DBExpensesModel>();
    DatabaseHelper db;
    int check;


    String type1="FOOD";
    String type2="MEDICAL";
    String type3="SAVINGS";
    String type5="SHOPPING";
    String type6="ENTERTAINMENT";
    String type7="TRANSPORTATION";
    String type4="OTHERS";
    static int food=0,medical=0,savings=0,shopping=0,entertainment=0,transportation=0,others=0,i,amount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "to-do");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu_add_expense);
        }
        else
        {
            setContentView(R.layout.menu_add_expense);
        }

        addListenerOnButton();


        date=(TextView)findViewById(R.id.date);
        date_pick=(Button)findViewById(R.id.set_date);
        date_pick.setOnClickListener(this);
        updatedate();


        db = new DatabaseHelper(getApplicationContext());


        notes = (EditText) findViewById(R.id.exp_notes);
        places = (EditText) findViewById(R.id.exp_place);
        amounts = (EditText) findViewById(R.id.exp_amt);
        selectedDate = (EditText) findViewById(R.id.date);
        cheques = (EditText) findViewById(R.id.exp_cheque);
        submit = (Button) findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);
        list = db.getAllExpenseList();
        print(list);
        //Spinner element
        spinner = (Spinner)findViewById(R.id.sel_cat);
        //Spinner click Listener
        spinner.setOnItemSelectedListener(this);
        //Spinner Dropdown elements
        List<String> categories = new ArrayList<String>();
        categories.add("FOOD");
        categories.add("MEDICAL");
        categories.add("SAVINGS");
        categories.add("SHOPPING");
        categories.add("ENTERTAINMENT");
        categories.add("TRANSPORTATION");
        categories.add("OTHERS");
        //Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        //Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void print(List<DBExpensesModel> list)
    {
        Log.d("tag","expense - list");
        // TODO Auto-generated method stub
        String value = "";
        for(DBExpensesModel e : list) {
            if((e.place).length()==0&&(e.note).length()==0)
            {
                value = value+"\nid: "+e.id+"\ndate: "+e.date+"\namount: "+e.amount+"\ncheque: "+e.cheque+"\ntype: "+e.type+"\n\n";

            }
            else if((e.place).length()==0||(e.note).length()==0)
            {
                if((e.place).length()==0)
                {
                    value = value+"\nid: "+e.id+"\ndate: "+e.date+"\nnote: "+e.note+"\namount: "+e.amount+"\ncheque: "+e.cheque+"\ntype: "+e.type+"\n\n";
                }
                else
                {
                    value = value+"\nid: "+e.id+"\ndate: "+e.date+"\nplace: "+e.place+"\namount: "+e.amount+"\ncheque: "+e.cheque+"\ntype: "+e.type+"\n\n";
                }
            }
            else
            {
                value = value+"\nid: "+e.id+" \ndate: "+e.date+"\nnote: "+e.note+" \nplace: "+e.place+"\namount: "+e.amount+"\ncheque: "+e.cheque+"\ntype: "+e.type+"\n\n";
            }
        }
    }

    public void addListenerOnButton() {
        final Context context = this;
        Log.d("tag","on home button click");
        home = (Button) findViewById(R.id.hme_enter);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent;
                intent = new Intent(context, Main_menu.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //On selecting a spinner item
        Log.d("tag","when item selected");
        item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("tag","when nothing selected");
    }

    public void updatedate()
    {
        Log.d("tag","update date");
        date.setText(formate.format(calender.getTime()));
    }


    public void setDate()//function to pick date from the datepicker widget and sends it to fnction update date
    {
        Log.d("tag","setting date");
        new DatePickerDialog(menuAddExpense.this,d,calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show();
    }    DatePickerDialog.OnDateSetListener d;

    {
        d = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthofyear, int dayofMOnth) {
                calender.set(Calendar.YEAR,year);
                calender.set(Calendar.MONTH,monthofyear);
                calender.set(Calendar.DAY_OF_MONTH,dayofMOnth);
                updatedate();
            }
        };
    }

    @Override
    public void onClick(View v) {
        Log.d("tag", "on click");
        if (v == findViewById(R.id.btn_submit)) {
            if(amounts.length()==0) {
                Toast.makeText(this,"Enter value for amount",Toast.LENGTH_SHORT).show();
            }
            else if(cheques.length()==0){
                Toast.makeText(this,"Enter value for cheque",Toast.LENGTH_SHORT).show();
            }
            else
            {
                DBExpensesModel exp = new DBExpensesModel();
                exp.note = notes.getText().toString();
                exp.place = places.getText().toString();
                exp.amount = Integer.parseInt(amounts.getText().toString());
                exp.type= item;
                exp.cheque = Integer.parseInt(cheques.getText().toString());
                exp.date = selectedDate.getText().toString();
                db.addExpenseDetail(exp);
                amount = Integer.parseInt(amounts.getText().toString());
                if(type1.compareTo(item)==0) {
                    food=food+amount;
                    // System.out.println("Food : "+food);

                }
                else if(type2.compareTo(item)==0)
                {
                    medical=medical+amount;
                    //System.out.println("Medical : "+medical);
                }
                else if(type3.compareTo(item)==0)
                {
                    savings=savings+amount;
                    //System.out.println("Savings : "+savings);
                }
                else if(type4.compareTo(item)==0)
                {
                    others=others+amount;
                    //System.out.println("Savings : "+savings);
                }
                else if(type5.compareTo(item)==0)
                {
                    shopping=shopping+amount;
                    //System.out.println("Savings : "+savings);
                }
                else if(type6.compareTo(item)==0)
                {
                    entertainment=entertainment+amount;
                    //System.out.println("Savings : "+savings);
                }
                else
                {
                    transportation=transportation+amount;
                    //System.out.println("Shopping : "+shopping);
                }
                list = db.getAllExpenseList();
                print(list);
                notes.setText("");
                places.setText("");
                amounts.setText("");
                cheques.setText("");
                spinner.setSelection(0);
                Toast.makeText(this, "New Expense added", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            setDate();
        }
    }
}


