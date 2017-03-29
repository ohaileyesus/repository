package com.example.humans.cleanwatercrowdsourcing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by omegahaileyesus on 3/28/17.
 */

public class CreatePurityReport extends AppCompatActivity {

    private Spinner waterconditionspinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_purity_report);


        waterconditionspinner = (Spinner) findViewById(R.id.SPpcondition);
        ArrayAdapter wcadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Arrays.asList("Safe", "Treatable", "Unsafe"));
        wcadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterconditionspinner.setAdapter(wcadapter);






    }


    /**
     * method for creating water purity report
     * @param v - current view
     */
    public void onSubmitPurity(View v) {
        if (v.getId() == R.id.Bsubmitpurity) {


            EditText location = (EditText) findViewById(R.id.ETplocation);
            EditText viruscount = (EditText) findViewById(R.id.ETpvirus);
            EditText contamcount = (EditText) findViewById(R.id.ETpcontam);




            SharedPreferences loadReports = getSharedPreferences("MySavedPurityReports", MODE_PRIVATE);


            int numofReports = loadReports.getInt("number of reports", 0);
            String allreports = loadReports.getString("Water Reports", "");
            String reportID = loadReports.getString("Report ID: ", "");
            String reporter = loadReports.getString("Reporter: ", "");
            String time = loadReports.getString("Time: ", "");
            String waterLocation = loadReports.getString("Location: ", "");
            String waterCondition = loadReports.getString("Water Condition: ", "");
            String virusPPM = loadReports.getString("Virus PPM", "");
            String contamPPM = loadReports.getString("Contaminant PPM", "");


            numofReports++;
            reportID += numofReports + ", ";
            reporter += Login.mEmailView.getText().toString() + ", ";
            time += DateFormat.getDateTimeInstance().format(new Date()) + "; ";
            waterLocation += location.getText() + "; ";
            waterCondition += waterconditionspinner.getSelectedItem().toString() + ", ";
            virusPPM += viruscount.getText().toString() + ", ";
            contamPPM += contamcount.getText().toString() + ", ";


            String[] reportIds = reportID.split(", ");
            String[] reporters = reporter.split(", ");
            String[] times = time.split("; ");
            String[] locations = waterLocation.split("; ");
            String[] waterconditions = waterCondition.split(", ");
            String[] virusppms = virusPPM.split(", ");
            String[] contamppms = contamPPM.split(", ");


            for (int i = 0; i < reportIds.length; i++){


                allreports += "\n"
                        + "Report ID: " + reportIds[i] + "\n"
                        + "Reporter: " + reporters[i] + "\n"
                        + "Time: " + times[i] + "\n"
                        + "Location: " +  locations[i] + "\n"
                        + "Condition: " +  waterconditions[i] + "\n"
                        + "Virus PPM: " +  virusppms[i] + "\n"
                        + "Contaminant PPM: " +  contamppms[i] + "\n \n";

            }



            SharedPreferences saveReports = getSharedPreferences("MySavedPurityReports", MODE_PRIVATE);
            SharedPreferences.Editor editor = saveReports.edit();

            editor.putString("all reports", allreports);


            editor.putInt("number of reports", numofReports);

            editor.putString("Report ID: ", reportID);
            editor.putString("Reporter: ", reporter);
            editor.putString("Time: ", time);
            editor.putString("Location: ", waterLocation);
            editor.putString("Water Condition: ", waterCondition);
            editor.putString("Virus PPM", virusPPM);
            editor.putString("Contaminant PPM", contamPPM);

            editor.apply();






            Intent createpReport = new Intent(CreatePurityReport.this, SuccessfulLogin.class);
            FirebaseAuth.getInstance().signOut();
            startActivity(createpReport);

        }
    }
}
