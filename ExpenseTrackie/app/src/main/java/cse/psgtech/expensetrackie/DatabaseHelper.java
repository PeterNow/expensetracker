package cse.psgtech.expensetrackie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "expenditure_database";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EXPENSE = "expense";
    private static final String TABLE_INCOME = "income";
    private static final String TABLE_LOGIN = "login";

    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_PLACE = "place";
    private static final String KEY_NOTE = "note";
    private static final String KEY_CHEQUE = "cheque";
    private static final String KEY_DATE = "date";

    private static final String KEY_USERNAME ="name";
    private static final String KEY_PIN ="pin";

    public static String TAG = "tag";

    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE "
            + TABLE_EXPENSE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TYPE + " TEXT, "
            + KEY_AMOUNT + " INTEGER, "
            + KEY_PLACE + " TEXT, "
            + KEY_NOTE + " TEXT, "
            + KEY_CHEQUE + " INTEGER, "
            + KEY_DATE + " TEXT )";


    private static final String CREATE_TABLE_INCOME = "CREATE TABLE "
            + TABLE_INCOME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TYPE + " TEXT, "
            + KEY_AMOUNT + " INTEGER, "
            + KEY_PLACE + " TEXT, "
            + KEY_NOTE + " TEXT, "
            + KEY_CHEQUE + " INTEGER, "
            + KEY_DATE + " TEXT )";

    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE "
            + TABLE_LOGIN + "(" + KEY_USERNAME + " TEXT, "
            + KEY_PIN + " INTEGER )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_EXPENSE);
        db.execSQL(CREATE_TABLE_INCOME);
        db.execSQL(CREATE_TABLE_LOGIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EXPENSE); // drop table if exists
        //create the updated db
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_INCOME); // drop table if exists
        //create the updated db
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_LOGIN); // drop table if exists
        //create the updated db
        onCreate(db);
    }

    /**
     *
     * This method is used to add expense detail in expense Table
     *
     * @param exp
     * @return
     */

    public long addExpenseDetail(DBExpensesModel exp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, exp.type);
        values.put(KEY_AMOUNT, exp.amount);
        values.put(KEY_PLACE, exp.place);
        values.put(KEY_NOTE, exp.note);
        values.put(KEY_CHEQUE, exp.cheque);
        values.put(KEY_DATE, exp.date);
        long insert = db.insert(TABLE_EXPENSE, null, values);
        return insert;
    }

    /**
     *
     * This method is used to add income detail in income Table
     *
     * @param inc
     * @return
     */

    public long addIncomeDetail(DBIncomesModel inc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, inc.type);
        values.put(KEY_AMOUNT, inc.amount);
        values.put(KEY_PLACE, inc.place);
        values.put(KEY_NOTE, inc.note);
        values.put(KEY_CHEQUE, inc.cheque);
        values.put(KEY_DATE, inc.date);
        long insert = db.insert(TABLE_INCOME, null, values);
        return insert;
    }

    public long addUserDetail(DBUserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.name);
        values.put(KEY_PIN, user.pin);
        long insert = db.insert(TABLE_LOGIN, null, values);
        return insert;
    }


    /**
     * Used to delete particular exp entry
     *
     * @param id
     */
    public boolean deleteEntry(long id) {
        SQLiteDatabase db1 = this.getReadableDatabase();
        // SELECT * FROM expense WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_EXPENSE + " WHERE " + KEY_ID + " = " + id;
        //Log.d(TAG, selectQuery);
        Cursor c = db1.rawQuery(selectQuery, null);
        if (c.getCount() ==1 ) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_EXPENSE, KEY_ID + " = ?", new String[]{String.valueOf(id)});
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Used to delete particular inc entry
     *
     * @param id
     */
    public boolean deleteIncomeEntry(long id) {
        SQLiteDatabase db1 = this.getReadableDatabase();
        // SELECT * FROM expense WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_INCOME + " WHERE " + KEY_ID + " = " + id;
        //Log.d(TAG, selectQuery);
        Cursor c = db1.rawQuery(selectQuery, null);
        if (c.getCount() ==1 ) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_INCOME, KEY_ID + " = ?", new String[]{String.valueOf(id)});
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Used to delete particular inc entry
     * @param name
     * @param pin
     */

    public boolean checkLoginEntry(String name,int pin) {
        SQLiteDatabase db1 = this.getReadableDatabase();
        // SELECT * FROM expense WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN + " WHERE " + KEY_PIN + " = " + pin + " AND " + KEY_USERNAME + " = " +"'"+name+"'";
        Log.d(TAG, selectQuery);
        Cursor c = db1.rawQuery(selectQuery, null);
        if (c.getCount() ==1 ) {
            return true;
        }
        else
        {
            return false;
        }
    }



    /**
     * Used to get detail of entire database and save in array list of data type
     * DBExpensesModel
     *
     * @return
     */
    public List<DBExpensesModel> getAllExpenseList() {
        List<DBExpensesModel> expenseArrayList = new ArrayList<DBExpensesModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_EXPENSE;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DBExpensesModel expense = new DBExpensesModel();
                expense.id = c.getInt(c.getColumnIndex(KEY_ID));
                expense.type = c.getString(c.getColumnIndex(KEY_TYPE));
                expense.amount = c.getInt(c.getColumnIndex(KEY_AMOUNT));
                expense.place = c.getString(c.getColumnIndex(KEY_PLACE));
                expense.note = c.getString(c.getColumnIndex(KEY_NOTE));
                expense.cheque = c.getInt(c.getColumnIndex(KEY_CHEQUE));
                expense.date = c.getString(c.getColumnIndex(KEY_DATE));

                // adding to Expenses list
                expenseArrayList.add(expense);
            } while (c.moveToNext());
        }
        return expenseArrayList;
    }

    /**
     * Used to get detail of entire database and save in array list of data type
     * DBIncomesModel
     *
     * @return
     */
    public List<DBIncomesModel> getAllIncomeList() {
        List<DBIncomesModel> incomeArrayList = new ArrayList<DBIncomesModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_INCOME;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DBIncomesModel income = new DBIncomesModel();
                income.id = c.getInt(c.getColumnIndex(KEY_ID));
                income.type = c.getString(c.getColumnIndex(KEY_TYPE));
                income.amount = c.getInt(c.getColumnIndex(KEY_AMOUNT));
                income.place = c.getString(c.getColumnIndex(KEY_PLACE));
                income.note = c.getString(c.getColumnIndex(KEY_NOTE));
                income.cheque = c.getInt(c.getColumnIndex(KEY_CHEQUE));
                income.date = c.getString(c.getColumnIndex(KEY_DATE));

                // adding to Expenses list
                incomeArrayList.add(income);
            } while (c.moveToNext());
        }
        return incomeArrayList;
    }
    // Functions to fetch total expenses by category
    public int expfood()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM EXPENSE GROUP BY TYPE HAVING TYPE='FOOD'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int expsaving()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM EXPENSE GROUP BY TYPE HAVING TYPE='SAVINGS'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int expothers()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM EXPENSE GROUP BY TYPE HAVING TYPE='OTHERS'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int exptransportation()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM EXPENSE GROUP BY TYPE HAVING TYPE='TRANSPORTATION'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int expshopping()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM EXPENSE GROUP BY TYPE HAVING TYPE= 'SHOPPING'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }


    public int expmedical()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM EXPENSE GROUP BY TYPE HAVING TYPE = 'MEDICAL'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int expentertainment()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM EXPENSE GROUP BY TYPE HAVING TYPE = 'ENTERTAINMENT'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    //functions to fetch total income by category

    public int incloan()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM INCOME GROUP BY TYPE HAVING TYPE='LOAN'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int incgifts()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM INCOME GROUP BY TYPE HAVING TYPE='GIFTS'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int incsalary()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM INCOME GROUP BY TYPE HAVING TYPE='SALARY'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int incinterest()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM INCOME GROUP BY TYPE HAVING TYPE='INTEREST'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int incbusiness()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM INCOME GROUP BY TYPE HAVING TYPE= 'BUSINESS'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }


    public int increntalincome()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM INCOME GROUP BY TYPE HAVING TYPE = 'RENTAL INCOME'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }

    public int incothers()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM INCOME GROUP BY TYPE HAVING TYPE = 'OTHERS'";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }
    //To calculate total Expenses
    public int totexp()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM EXPENSE";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }
    //To calculate total Income
    public int totinc()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String stmt = "SELECT SUM(AMOUNT) FROM INCOME";
        Cursor tot = db1.rawQuery(stmt, null);
        if(tot.getCount()==0)
        {
            return 0;
        }
        else {
            tot.moveToFirst();
            int amount = tot.getInt(0);
            tot.close();
            return amount;
        }
    }
}

