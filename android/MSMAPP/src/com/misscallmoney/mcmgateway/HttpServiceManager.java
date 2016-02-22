package com.misscallmoney.mcmgateway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class HttpServiceManager extends IntentService {

	private static String url     =	"http://10.0.2.2/mcm/storeKey.php";
	private static String reg_id  =	null;
	
	
	public HttpServiceManager() {
		super("sample");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent in) {
	// TODO Auto-generated method stub
	
			reg_id = in.getStringExtra("reg_id");
			Log.d("Received", reg_id);
			
		    /*Create a new HttpClient and Post Header*/
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost(url);

		    try {
		        /*Prepare data in dictionary format*/
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("id", reg_id));
		        httppost.setEntity((HttpEntity) new UrlEncodedFormEntity(nameValuePairs));

		        /*send HTTP request to your server to store registration id*/
		        HttpResponse response = httpclient.execute(httppost);
		        if(response.getStatusLine().getStatusCode()==200){
		        	/**/
		        	JSONObject rest=new JSONObject(EntityUtils.toString(response.getEntity()));
		        	Log.d("status", rest.get("msg").toString());
		        }
		        else{
		        	Log.d("status","response failed");
		        }
		        
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    	e.printStackTrace();
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		    	e.printStackTrace();
		    } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
}
