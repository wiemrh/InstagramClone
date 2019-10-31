package com.wiem.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import javax.security.auth.callback.Callback;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button saveBtn , getAllData , btnTransition;
    private EditText editName, editSpeed , editPower , editKickSpeed , editKickPower ;
    private TextView txtGetData ;
    private String allKickBoxers ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(SignUp.this);
        editName = findViewById(R.id.editkickboxer);
        editSpeed = findViewById(R.id.editSpeed);
        editPower = findViewById(R.id.editPower);
        editKickSpeed = findViewById(R.id.editKickSpeed);
        editKickPower = findViewById(R.id.editKickPower);
        txtGetData = findViewById(R.id.txtGetData);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("dGIyzSf4hv", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e == null ){
                            txtGetData.setText(object.get("name") + " - " + "kickSpeed " + object.get("kickSpeed"));

                    }
                }

                });

            }
        });
        getAllData = findViewById(R.id.btnGetAllData);
        getAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             allKickBoxers = "" ;

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                //queryAll.whereGreaterThan("kickSpeed",100);
          //      queryAll.whereGreaterThanOrEqualTo("kickSpeed",200);
                queryAll.setLimit(2);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if ( e == null){
                            if(objects.size() > 0){
                                for(ParseObject kickBoxers : objects){
                                    allKickBoxers = allKickBoxers + kickBoxers.get("name") + "\n";
                                }
                                FancyToast.makeText(SignUp.this, allKickBoxers,FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                            }else{
                                FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }
                    }
                });


            }
        });
        btnTransition = findViewById(R.id.btnNextActivity);
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        try {
        final ParseObject Kickboxer = new ParseObject("KickBoxer");
        Kickboxer.put("name" , editName.getText().toString());
        Kickboxer.put("punchSpeed" , Integer.parseInt(editSpeed.getText().toString()));
        Kickboxer.put("punchPower" , Integer.parseInt(editPower.getText().toString()));
        Kickboxer.put("kickSpeed" , Integer.parseInt(editKickSpeed.getText().toString()));
        Kickboxer.put("kickPower" , Integer.parseInt(editKickPower.getText().toString()));
        Kickboxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    FancyToast.makeText(SignUp.this,Kickboxer.get("name") + " Object has saved succefully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                } else {

                    FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();


                }
            }
        });
    }catch (Exception e ){
            FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

        }
    }
}
