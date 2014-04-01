package com.larry.msglighter;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingsActivity extends ListActivity {
    private String[] mListStr = {"����������ͣ����ʱ��","����"};
    ListView mListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	final Intent it = new Intent(this,RadioGroup1.class);
        mListView = getListView();
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mListStr));
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                    long id) 
            {
//                Toast.makeText(SettingsActivity.this,"��ѡ����" + mListStr[position], Toast.LENGTH_LONG).show();
            	  if(position==0)//listview�е�position��0��ʼ
            	  startActivity(it);
            	  else if(position==1)
            	  {
            		  new AlertDialog.Builder(SettingsActivity.this).setTitle(R.string.about).setMessage(R.string.about_content)
          			.setPositiveButton(R.string.yes, null).show();
            		  
            	  }
            }
        });

        super.onCreate(savedInstanceState);
    }
}