package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous (name="TouchSensorTest")
public class TouchSensor_Test extends LinearOpMode {

    public TouchSensor pokeSensor;
    public DcMotor frontLeft;
    public ElapsedTime time = new ElapsedTime(0);


    @Override
    public void runOpMode(){

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        pokeSensor = hardwareMap.touchSensor.get("pokeSensor");

        time.reset();

        //Runs for 15 seconds
        while (time.seconds() <= 15){

            //Display time left
            telemetry.addData("Time", time);
            telemetry.update();

            //If pressed, run motor
            // Else, do nothing
        if (pokeSensor.isPressed() == true) {
            frontLeft.setPower(1);
            }
        else{
            frontLeft.setPower(0);
            }
        }
    }
}
