package com.example.humans.cleanwatercrowdsourcing.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.humans.cleanwatercrowdsourcing.R;

/**
 * Created by omegahaileyesus on 3/7/17.
 * class for viewing all water reports
 */

public class ViewReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reports);




        Object[] reps = viewReports();
        String allreports = (String) reps[0];


        TextView reports = (TextView) findViewById(R.id.TVreports);
        reports.setMovementMethod(new ScrollingMovementMethod());

//        String allreports = "";
//        if (CreateReport.waterReports.size() == 0) {
//            reports.setText("No reports have been submitted yet.");
//        }
//
//
//        for (int i = 0; i < CreateReport.waterReports.size(); i++){
//            allreports = allreports + "\n"
//                    + "Report ID: " + CreateReport.waterReports.get(i)[0] + "\n"
//                    + "Reporter: " + CreateReport.waterReports.get(i)[1] + "\n"
//                    + "Time: " + CreateReport.waterReports.get(i)[2] + "\n"
//                    + "Location: " +  CreateReport.waterReports.get(i)[3] + "\n"
//                    + "Water Type: " +  CreateReport.waterReports.get(i)[4] + "\n"
//                    + "Water Condition: " +  CreateReport.waterReports.get(i)[5] + "\n \n";
//        }


        //reports.setText(allreports);
        reports.setText(allreports);
        //Toast.makeText(SuccessfulLogin.this, "Login Successful",Toast.LENGTH_SHORT).show();
    }



    private Object[] viewReports(){
        SharedPreferences loadReports = getSharedPreferences("MySavedReports", MODE_PRIVATE);


        String allreports = loadReports.getString("all reports", "");

        int numofReports = loadReports.getInt("number of reports", 0);

//        String reportID = loadReports.getString("Report ID: ", "");
//        String reporter = loadReports.getString("Reporter: ", "");
//        String time = loadReports.getString("Time: ", "");
//        String waterLocation = loadReports.getString("Location: ", "");
//        String waterType = loadReports.getString("Water Type: " , "");
//        String waterCondition = loadReports.getString("Water Condition: ", "");

        Object[] reps = new Object[2];
        reps[0] = allreports;
        reps[1] = numofReports;
        return reps;
    }
}
