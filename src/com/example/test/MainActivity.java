package com.example.test;

import java.net.URI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getName();
	private TextView textView;
	
	private Button copyButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button)findViewById(R.id.button);
		textView = (TextView)findViewById(R.id.text);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ClipboardManager cm = (ClipboardManager)MainActivity.this
		                .getSystemService(Context.CLIPBOARD_SERVICE);
		        ClipData clipData = cm.getPrimaryClip();
		        if (clipData != null) {
		            ClipData.Item clipItem = clipData.getItemAt(0);
		            CharSequence pasteText = clipItem.coerceToText(MainActivity.this);
		            Log.e(TAG, "into pasteFromClipboard:" + pasteText);
		            textView.setText(pasteText);
		        }
			}
		});
		
		copyButton = (Button)findViewById(R.id.button2);
		copyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClipboardManager cm = (ClipboardManager)MainActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
				
				Intent intent = new Intent();
				Uri uri = new Uri();
				intent.setData();
				intent.setClipData(ClipData.newHtmlText("yu1", "yu2", "yu3"));
				ClipData data = ClipData.newIntent("aaa", intent);
				cm.setPrimaryClip(data);
				Log.d(TAG, "into copy button intent:" + intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
