package com.misscallmoney.mcmgateway;

import java.util.ArrayList;

import android.app.IntentService;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SmsServiceManager extends IntentService {

	public SmsServiceManager() {
		super("SMS Manager");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String phoneNo = intent.getStringExtra("num");
		String sms     = intent.getStringExtra("msg");

		try {
			Log.d("sending sms","initiated");
			SmsManager smsManager = SmsManager.getDefault();
			ArrayList<String> parts = smsManager.divideMessage(sms); 
			smsManager.sendMultipartTextMessage(phoneNo, null, parts, null, null);
			Log.d("sms","sent "+sms+phoneNo);
			Toast.makeText(getApplicationContext(), "SMS Sent!",Toast.LENGTH_LONG).show();
		} catch (IllegalArgumentException e) {
			Toast.makeText(getApplicationContext(),"SMS faild, please try again later!", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			Log.e("argument", "error");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
