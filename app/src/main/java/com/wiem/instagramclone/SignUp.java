package com.wiem.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button saveBtn ;
    private EditText editName, editSpeed , editPower , editKickSpeed , editKickPower ;

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
