package bg.tu_varna.sit.asynctasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button TaskOne;
    private Button TaskTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskOne=(Button)findViewById(R.id.TaskOne);
        TaskOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, TaskOne.class);
                startActivity(intent);
            }
        });

        TaskTwo=(Button)findViewById(R.id.TaskTwo);
        TaskTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, TaskTwo.class);
                startActivity(intent);
            }
        });


    }
}
