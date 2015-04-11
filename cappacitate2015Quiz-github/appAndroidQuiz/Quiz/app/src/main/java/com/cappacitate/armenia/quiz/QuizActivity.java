package com.cappacitate.armenia.quiz;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;


public class QuizActivity extends ActionBarActivity {

    private String username;
    private Firebase mFirebaseRef;
    Firebase usersRef;
    Firebase responsesRef;
    Firebase commentsRef;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        res = getResources();

        // se modifica el usuario por el que el usuario diligenció
        username = getIntent().getStringExtra("username");

        // obtenemos referencia de la informacion en Firebase
        mFirebaseRef = new Firebase(getResources().getString(R.string.firebase_url));

        // referencia de los usuarios
        usersRef = mFirebaseRef.child("users");

        // referencia de las respuestas
        responsesRef = mFirebaseRef.child("responses");

        // referencia de las respuestas
        commentsRef = mFirebaseRef.child("comments");

        // Se coloca el nombre de usuario como titulo
        //setTitle(getResources().getString(R.string.username_field) + username);

        // se arma el mensaje dinamico
        String textTop = String.format(res.getString(R.string.text_quiz_top), username);

        ((TextView)findViewById(R.id.textTop)).
                setText(textTop);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
     * Método responsable de enviar una respuesta al servidor
     */
    public void sendResponse(View v) {
        // se obtiene la respuesta
        String response = (String) v.getTag();

        // se envia la respuesta al servidor
       /* Map<String, String> responseObj = new HashMap<String, String>();
        responseObj.put("username", username);
        responseObj.put("response", response);*/
        responsesRef.push().setValue(response);

        // se agradece al usuario
        Toast.makeText(this, getResources().getString(R.string.thanks), Toast.LENGTH_LONG).show();

        // se desabilitan los botones para que no voten mas
        changeStatusButtons(false);

    }

    /**
     * Metodo responsable de habilitar y deshabilitar los botones de votacion
     * @param enabledButtons
     */
    private void changeStatusButtons (boolean enabledButtons) {
        Button btnA = (Button) findViewById(R.id.buttonA);
        Button btnB = (Button) findViewById(R.id.buttonB);
        Button btnC = (Button) findViewById(R.id.buttonC);
        Button btnD = (Button) findViewById(R.id.buttonD);
        Button btnE = (Button) findViewById(R.id.buttonE);

        btnA.setEnabled(enabledButtons);
        btnB.setEnabled(enabledButtons);
        btnC.setEnabled(enabledButtons);
        btnD.setEnabled(enabledButtons);
        btnE.setEnabled(enabledButtons);
    }

    /**
     * metodo respondeble de enviar un comentario

    public void sendComment(View view) {
        // se obtiene el comentario
        EditText et = (EditText)findViewById(R.id.comment);
        String comment = et.getText().toString();

        if (comment.length() > 0) {
            // se envia la respuesta al servidor
            Map<String, String> commentObj = new HashMap<String, String>();
            commentObj.put("username", username);
            commentObj.put("comment", comment);
            commentsRef.push().setValue(commentObj);

            // se borra el texto del campo
            et.setText("");

            // se informa al usuario que el comentario fue enviado
            Toast.makeText(this, "Comentario enviado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Escribe un comentario", Toast.LENGTH_SHORT).show();
        }
    }*/

}
