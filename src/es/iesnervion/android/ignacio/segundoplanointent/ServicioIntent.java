package es.iesnervion.android.ignacio.segundoplanointent;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ServicioIntent extends Activity {
	
	Button btnIniciar;
	static ProgressBar barraProgreso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_servicio_intent);
		barraProgreso = (ProgressBar) findViewById(R.id.barraProgreso);
		btnIniciar = (Button) findViewById(R.id.btnIniciar);
		btnIniciar.setOnClickListener(new OnClickListener() {
			 
		    @Override
		    public void onClick(View v) {
		        Intent msgIntent = new Intent(ServicioIntent.this, MiIntent.class);
		        msgIntent.putExtra("iteraciones", 10);
		        startService(msgIntent);
		    }

		});
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(MiIntent.ACTION_PROGRESO);
		filter.addAction(MiIntent.ACTION_FIN);
		Receptor rcp = new Receptor();
		registerReceiver(rcp, filter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.servicio_intent, menu);
		return true;
	}
	
	
	
	public class Receptor extends BroadcastReceiver {
		 
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        if(intent.getAction().equals(MiIntent.ACTION_PROGRESO)) {
	            int prog = intent.getIntExtra("progreso", 0);
	            barraProgreso.setProgress(prog);
	        }
	        else if(intent.getAction().equals(MiIntent.ACTION_FIN)) {
	            Toast.makeText(ServicioIntent.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
	        }
	    }
	}

	

}
