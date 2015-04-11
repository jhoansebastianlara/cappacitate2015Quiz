package com.cappacitate.armenia.quiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class MainActivity extends ActionBarActivity {

    private String username;
    private Firebase mFirebaseRef;
    Firebase usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        // obtenemos referencia de la informacion en Firebase
        mFirebaseRef = new Firebase(getResources().getString(R.string.firebase_url));

        // referencia de los usuarios
        usersRef = mFirebaseRef.child("users");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo responsable de enviar el usuario al Quiz
     * @param view
     */
    public void startQuiz(View view) {

        EditText et = (EditText)findViewById(R.id.editText);

        username = et.getText().toString();

        if (username.length() > 0) {
            // enviamos el nombre del usuario
            usersRef.push().setValue(username);

            Intent callQuiz = new Intent(this, QuizActivity.class);

            callQuiz.putExtra("username", username);

            //Ejecutar o lanzar la segunda actividad
            startActivity(callQuiz);
        } else {
            Toast.makeText(this, "El usuario es requerido", Toast.LENGTH_SHORT).show();
        }
    }
}
