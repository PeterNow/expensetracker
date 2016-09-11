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

public class menuAddIncome extends Activity implements OnItemSelectedListener,View.OnClickListener {

    Button home,submit;
    EditText id,amounts,places,notes,cheques;
    String item;
    EditText selectedDate;
    Spinner spinner;
    DateFormat formate= DateFormat.getDateInstance();
    Calendar calender=Calendar.getInstance();
    TextView date;
    Button date_pick;
    List<DBIncomesModel> list = new ArrayList<DBIncomesModel>();
    DatabaseHelper db;
    int check;

    public void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "incomes menu");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        check = settings.Index;
        if(check==1)
        {
            setContentView(R.layout.b_menu_add_income);
        }
        else
        {
            setContentView(R.layout.menu_add_income);
        }



        addListenerOnButton();
        date=(TextView)findViewById(R.id.date);
        date_pick=(Button)findViewById(R.id.set_date);
        date_pick.setOnClickListener(this);
        updatedate();//to update date in the respective text field
        db = new DatabaseHelper(getApplicationContext());
        notes = (EditText) findViewById(R.id.inc_notes);
        places = (EditText) findViewById(R.id.inc_place);
        amounts = (EditText) findViewById(R.id.inc_amt);
        selectedDate = (EditText) findViewById (R.id.date);
        cheques = (EditText) findViewById(R.id.inc_chq);
        submit = (Button) findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);
        list = db.getAllIncomeList();
        print(list);
        //Spinner element
        spinner = (Spinner)findViewById(R.id.sel_type);
        //Spinner click Listner
        spinner.setOnItemSelectedListener(this);
        //Spinner Dropdown elements
        List<String> categories = new ArrayList<String>();
        categories.add("LOAN");
        categories.add("GIFTS");
        categories.add("SALARY");
        categories.add("INTEREST");
        categories.add("BUSINESS");
        categories.add("RENTAL INCOME");
        categories.add("OTHERS");
        //Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        //Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void print(List<DBIncomesModel> list)
    {
        // TODO Auto-generated method stub
        Log.d("tag","incomes - print list");
        String value = "";
        for(DBIncomesModel i : list) {
            if((i.place).length()==0&&(i.note).length()==0)
            {
                value = value+"\nid: "+i.id+"\ndate: "+i.date+"\namount: "+i.amount+"\ncheque: "+i.cheque+"\ntype: "+i.type+"\n\n";

            }
            else if((i.place).length()==0||(i.note).length()==0)
            {
                if((i.place).length()==0)
                {
                    value = value+"\nid: "+i.id+"\ndate: "+i.date+"\nnote: "+i.note+"\namount: "+i.amount+"\ncheque: "+i.cheque+"\ntype: "+i.type+"\n\n";
                }
                else
                {
                    value = value+"\nid: "+i.id+"\ndate: "+i.date+"\nplace: "+i.place+"\namount: "+i.amount+"\ncheque: "+i.cheque+"\ntype: "+i.type+"\n\n";
                }
            }
            else
            {
                value = value+"\nid: "+i.id+" \ndate: "+i.date+"\nnote: "+i.note+" \nplace: "+i.place+"\namount: "+i.amount+"\ncheque: "+i.cheque+"\ntype: "+i.type+"\n\n";
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
    public void onNothingSelected(AdapterView<?> parent)
    {
        Log.d("tag","when nothing selected");
    }

    public void updatedate()
    {
        Log.d("tag","to update date");
        date.setText(formate.format(calender.getTime()));
    }


    public void setDate()//function to pick date from the datepicker widget and sends it to fnction update date
    {
        Log.d("tag","to set date");
        new DatePickerDialog(menuAddIncome.this,d,calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show();
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
        Log.d("tag","on click...");

        if (v == findViewById(R.id.btn_submit)) {
            if(amounts.length()==0) {
                Toast.makeText(this, "Enter value for amount", Toast.LENGTH_SHORT).show();
            }
            else if(cheques.length()==0){
                Toast.makeText(this,"Enter value for cheque",Toast.LENGTH_SHORT).show();
            }
            else
            {
                DBIncomesModel inc = new DBIncomesModel();
                inc.note = notes.getText().toString();
                inc.place = places.getText().toString();
                inc.amount = Integer.parseInt(amounts.getText().toString());
                inc.cheque = Integer.parseInt(cheques.getText().toString());
                inc.type= item;
                inc.date = selectedDate.getText().toString();
                db.addIncomeDetail(inc);
                list = db.getAllIncomeList();
                print(list);
                notes.setText("");
                places.setText("");
                amounts.setText("");
                cheques.setText("");
                spinner.setSelection(0);
                Toast.makeText(this, "New Income added", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            setDate();
        }
    }
}


