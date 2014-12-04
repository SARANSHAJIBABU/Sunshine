package sunshine.saran.com.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"In onCreate of MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this,SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }else if(id == R.id.action_map){
            openPreferredLocation();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"In onStart of MainActivity");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"In onResume of MainActivity");
        super.onResume();
    }


    @Override
    protected void onPause() {
        Log.d(TAG,"In onPause of MainActivity");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"In onStop of MainActivity");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG,"In onRestart of MainActivity");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"In onDestroy of MainActivity");
        super.onDestroy();
    }



    private void openPreferredLocation() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String location = pref.getString(getString(R.string.pref_locaton_key),getString(R.string.pref_locaton_defaultvalue));
        Uri geoLocation = Uri.parse("geo:0.0?").buildUpon()
                .appendQueryParameter("q",location)
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        //Only if the activity resolves successfully
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else{
            Log.d(TAG,"Couldnt open map");
        }

    }


}
