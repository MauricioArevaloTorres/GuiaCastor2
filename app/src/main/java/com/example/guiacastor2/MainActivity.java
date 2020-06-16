package com.example.guiacastor2;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;

        import java.util.Timer;
        import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, AuthFirebase.class);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);    //iniciar el objeto que ejecuta la siguiente activity
            }
        }, 5000);


    }
}