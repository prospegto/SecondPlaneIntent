package es.iesnervion.android.ignacio.segundoplanointent;

import android.app.IntentService;
import android.content.Intent;

public class MiIntent extends IntentService {
 
    public static final String ACTION_PROGRESO =
        "es.iesnervion.android.ignacio.segundoplanointent.intent.action.PROGRESO";
    public static final String ACTION_FIN =
        "es.iesnervion.android.ignacio.segundoplanointent.intent.action.FIN";
 
    public MiIntent() {
            super("MiIntent");
        }
 
    @Override
    protected void onHandleIntent(Intent intent)
    {
        int iter = intent.getIntExtra("iteraciones", 0);
 
        for(int i=1; i<=iter; i++) {
            tareaLarga();
            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_PROGRESO);
            bcIntent.putExtra("progreso", i*10);
            sendBroadcast(bcIntent);
        }
 
        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_FIN);
        sendBroadcast(bcIntent);
    }
 
    private void tareaLarga()
        {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {}
        }

}