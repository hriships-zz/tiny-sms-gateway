package com.misscallmoney.mcmgateway;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gcm.GCMRegistrar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MSMAPP extends Activity {

	public final static String AUTH = "authentication";
	private Button reg,unreg;

	TextView regid_txt ;
	// server
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mcm_activity);
		regid_txt=(TextView) findViewById(R.id.reg_id_txt);
		reg=(Button) findViewById(R.id.button1);
		unreg=(Button) findViewById(R.id.button2);
		
		reg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				register();
			}
		});
		
		unreg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unregister();
			}
		});
		
	}
	
	public void register(){
		/* code for c2dm */
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		final String regId = GCMRegistrar.getRegistrationId(this);
		
		if (regId.equals("")) {
			GCMRegistrar.register(this,Init.appid);
			Toast.makeText(this, "registering", Toast.LENGTH_SHORT).show();
		} 
		else {
			try{
				Intent in=new Intent(getApplicationContext(), HttpServiceManager.class);
				in.putExtra("reg_id", regId);
				startService(in);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			Toast.makeText(this, "Your registration Id "+regId, Toast.LENGTH_SHORT).show();
			regid_txt.setText(regId);
			Log.v("Registration", "Already registered");
		}
	}
	
	public void unregister(){
		final String regId = GCMRegistrar.getRegistrationId(this);
		if(regId!=null){
			GCMRegistrar.unregister(this);
			Toast.makeText(this, "Your device is unregistered",Toast.LENGTH_SHORT).show();
		}
	}
}