package com.ducle.quanlynhanvien;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

    ArrayList<Employee>arrEmployee=new ArrayList<Employee>();
    MyArrayAdapter adapter=null;
    ListView lvNhanvien=null;

    Button btnNhap;
    ImageButton btnRemoveAll;
    EditText editMa,editTen;
    RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNhap=(Button) findViewById(R.id.btnNhap);
        btnRemoveAll=(ImageButton) findViewById(R.id.btndelete);
        editMa=(EditText) findViewById(R.id.editMa);
        editTen=(EditText) findViewById(R.id.editTen);
        genderGroup=(RadioGroup) findViewById(R.id.radioGroup1);

        lvNhanvien=(ListView) findViewById(R.id.lvnhanvien);
        arrEmployee=new ArrayList<Employee>();
        adapter=new MyArrayAdapter(
                this,
                R.layout.my_item_layout,
                arrEmployee/*thiết lập data source*/);
        lvNhanvien.setAdapter(adapter);//gán Adapter vào Lisview

        btnNhap.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                xulyNhap();
            }
        });
        btnRemoveAll.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                xulyXoa();
            }
        });
    }
    public void xulyNhap()
    {
        String ma=editMa.getText()+"";
        String ten=editTen.getText()+"";
        boolean gioitinh=false;
        if(genderGroup.getCheckedRadioButtonId()==R.id.radNu)
            gioitinh=true;
        Employee emp=new Employee();
        emp.setId(ma);
        emp.setName(ten);
        emp.setGender(gioitinh);
        arrEmployee.add(emp);
        adapter.notifyDataSetChanged();
        editMa.setText("");
        editTen.setText("");
        editMa.requestFocus();
    }
    public void xulyXoa()
    {
        for(int i=lvNhanvien.getChildCount()-1;i>=0;i--)
        {
            View v=lvNhanvien.getChildAt(i);
            CheckBox chk=(CheckBox) v.findViewById(R.id.chkitem);
            if(chk.isChecked())
            {
                arrEmployee.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
