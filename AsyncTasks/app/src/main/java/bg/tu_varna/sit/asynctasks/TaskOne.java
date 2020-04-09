package bg.tu_varna.sit.asynctasks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TaskOne extends AppCompatActivity {

    private Button start;
    private EditText timer;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_one);

        start=(Button)findViewById(R.id.ActiveButton);
        timer=findViewById(R.id.Timer);
        status=findViewById(R.id.Status);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String number = timer.getText().toString();
                int count = Integer.parseInt(number);
                runner.execute(count);
                status.setText("");

            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<Integer, Integer, String> {

        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(TaskOne.this,
                    "Task is running", "");
        }

        protected String doInBackground(Integer... integers) {
            for (int count = integers[0]; count>0; count--) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task Completed.";
        }

        protected void onProgressUpdate(Integer... values) {
            status.setText("Running..."+ values[0]);
            progressDialog.setMessage(String.valueOf(values[0]));

        }
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            status.setText(result);
            status.setText("Task Completed.");
            status.setTextColor(Color.GREEN);

            progressDialog.dismiss();
        }
    }
}
