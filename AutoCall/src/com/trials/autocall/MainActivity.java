package com.trials.autocall;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String PREFS_NAME = "MyPrefsFile";
	private static final int CONTACT_PICKER_RESULT = 1001;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
        	Intent callIntent = new Intent(Intent.ACTION_CALL);
        	callIntent.setData(Uri.parse("tel:9686553627"));
        	
        	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        	if(settings.getBoolean("ContactsSetup", false) == false)
        	{
        		SharedPreferences.Editor editor = settings.edit();
        		editor.putBoolean("ContactsSetup", true);
        		editor.commit();
        		Toast.makeText(getApplicationContext(), "if", Toast.LENGTH_SHORT).show();

        		selectContacts();
        	}
        	else
        	{
        		Toast.makeText(getApplicationContext(), "else", Toast.LENGTH_SHORT).show();
        		startActivity(callIntent);
        	}
            
        	
        }
        catch(ActivityNotFoundException e)
        {
        	Log.e("sample app","call failed",e);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	
    }
    
	  public void selectContacts() 
	  {  
		  Intent selectContactsIntent = new Intent(getApplicationContext(),ContactListDispActivity.class);
		  startActivityForResult(selectContactsIntent, 1);
	  } 
    
//    public void selectContacts() {  
//        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,  
//                Contacts.CONTENT_URI);  
//        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);  
//    } 
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_HOME) {
          // do something

          
        }
		return false;

        
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
