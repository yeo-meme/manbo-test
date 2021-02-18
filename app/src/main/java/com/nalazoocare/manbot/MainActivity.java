package com.nalazoocare.manbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.nalazoocare.manbot.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener{


    ActivityMainBinding binding;


    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    private TextView tvStepCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

       sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
       stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

       if (stepCountSensor == null) {
           Toast.makeText(this, "No Step Detect Sensor" , Toast.LENGTH_SHORT).show();
       }
    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            binding.tvStepCount.setText("Step Count :" + event.values[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}