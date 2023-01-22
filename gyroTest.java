package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous (name="GyroTest")
public class gyroTest extends LinearOpMode {

    public ElapsedTime time = new ElapsedTime(0);
    public GyroSensor mainGyro;
    public DcMotor frontLeft;

    public void runOpMode(){

        mainGyro = hardwareMap.get(GyroSensor.class, "mainGyro");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");

        time.reset();


        //Counts down until calibration
        telemetry.addData("Calibrating Soon", "Do Not Touch");
        telemetry.update();
        sleep(1000);
        telemetry.addData("", "3");
        telemetry.update();
        sleep(1000);
        telemetry.addData("", "2");
        telemetry.update();
        sleep(1000);
        telemetry.addData("", "1");
        telemetry.update();
        sleep(1000);
        telemetry.addData("", "Calibrating");
        telemetry.update();

        mainGyro.calibrate();

        while(mainGyro.isCalibrating()){
            sleep(100);
        }

        telemetry.addData("Finished","");
        telemetry.update();

        while (time.seconds() < 60){

           /*

           //       **Detects if the gyro is moving or not**
            telemetry.addData("RawX", mainGyro.rawX());
            telemetry.addData("RawY", mainGyro.rawY());
            telemetry.addData("RawZ", mainGyro.rawZ());

            if(mainGyro.rawX() > 2000 || mainGyro.rawX() < -2000 && mainGyro.rawY() > 2000 || mainGyro.rawY() < -2000 && mainGyro.rawZ() > 2000 || mainGyro.rawZ() < -2000){
            frontLeft.setPower(1);
            telemetry.addData("DETECTING", "MOTION");
            }


            else{
                frontLeft.setPower(0);
                telemetry.addData("DETECTING", "NOTHING");
            }
            telemetry.update();
            */

           //       **Detects heading of gyro
           int heading;
           heading = mainGyro.getHeading();
           telemetry.addData("Heading", heading);
           telemetry.addData("Time Left", time);
           telemetry.update();


        }
    }
}
