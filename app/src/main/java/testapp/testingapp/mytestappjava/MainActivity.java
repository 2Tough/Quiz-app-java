package testapp.testingapp.mytestappjava;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // setgravity() is not updating the height of the Toast.

            Toast top = Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_LONG);
            top.setGravity(Gravity.TOP, 0, 200);
            top.show();
            }
        });
        mFalseButton = (Button) findViewById(R.id.button2);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(MainActivity.this, R.string.incorrect_toast,
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
}