package bg.tu_varna.sit.asynctasks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class TaskTwo extends AppCompatActivity {

    private ProgressBar bar1;
    private ProgressBar bar2;
    private ProgressBar bar3;
    private Button button;
    private TextView status;
    private TextView downloadView;
    private TextView loginView;

    private boolean download;
    private boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_two);

        bar1=findViewById(R.id.progressBar);
        bar2=findViewById(R.id.progressBar2);
        bar3=findViewById(R.id.progressBar3);

        status=findViewById(R.id.Status);
        downloadView=findViewById(R.id.downloadView);
        loginView=findViewById(R.id.loginView);

        button=findViewById(R.id.button);

        status.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status.setVisibility(View.INVISIBLE);
                DownloadImage taskOne = new DownloadImage();
                LoginProcess taskTwo = new LoginProcess();
                bar3.setVisibility(ProgressBar.VISIBLE);
                taskOne.execute();
                taskTwo.execute();
            }
        });
    }

    private class DownloadImage extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar1.setVisibility(ProgressBar.VISIBLE);
            downloadView.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Random random = new Random();
            int count = random.nextInt(2) + 3;
            for (int i = 0; i < count; i++) {
                publishProgress((i * 100) / count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar1.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result){
            super.onPostExecute(result);
            bar1.setProgress(0);
            bar1.setVisibility(ProgressBar.INVISIBLE);
            downloadView.setVisibility(View.INVISIBLE);
            download=result;
            tasksOneDone();
        }
    }


    private class LoginProcess extends AsyncTask<Void,Integer,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar2.setVisibility(ProgressBar.VISIBLE);
            loginView.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Random random = new Random();

            int count = random.nextInt(3) + 2;
            for (int i = 0; i < count; i++) {
                publishProgress((i * 100) / count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar2.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            bar2.setProgress(0);
            bar2.setVisibility(ProgressBar.INVISIBLE);
            loginView.setVisibility(View.INVISIBLE);
            login=result;
            tasksTwoDone();
            onFinish();
        }
    }

    private boolean tasksOneDone() {
        if(download){
            return true;
        }else
        {
            return false;
        }
    }

    private boolean tasksTwoDone() {
        if(login){
            return true;
        }else
        {
            return false;
        }
    }

    private void onFinish(){
        status.setVisibility(View.VISIBLE);
        bar3.setVisibility(bar3.INVISIBLE);
        if(tasksOneDone()&&tasksTwoDone()){
            status.setText("SUCCESSFULLY");
        }else
        {
            status.setText("NOT SUCCESSFULLY");
        }
    }

}
