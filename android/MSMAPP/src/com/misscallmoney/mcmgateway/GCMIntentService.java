package com.misscallmoney.mcmgateway;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	public GCMIntentService(){
		super(Init.appid);
	}
	@Override
	protected void onError(Context context, String err) {
		// TODO Auto-generated method stub
		Log.e("Error Occured:",err);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String msg=intent.getStringExtra("msg");
		String num=intent.getStringExtra("number");

		if(msg!=null){
			sendSms(context,num,msg);
			Log.d("msg",msg);
			
		}
		else{
			Log.d("msg","message is null");
		}
	}

	@Override
	protected void onRegistered(Context context, String id) {
		// TODO Auto-generated method stub
		Log.d("registetred :","id");
		sendId(context,id);
	}

	@Override
	protected void onUnregistered(Context context, String arg1) {
		// TODO Auto-generated method stub
		Log.d("Unregister",arg1);
		sendId(context,arg1);
	}
	
	protected void sendId(Context c, String regid){
		Intent in=new Intent(c, HttpServiceManager.class);
		in.putExtra("reg_id",regid);
		startService(in);
	}
	
	protected void sendSms(Context c,String num,String msg){
		Intent in=new Intent(c, SmsServiceManager.class);
		in.putExtra("msg",msg);
		in.putExtra("num",num);
		Log.d("sending sms","started");
		startService(in);
	}
}
