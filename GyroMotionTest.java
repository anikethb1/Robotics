package org.firstinspires.ftc.teamcode.TestCode.MiscTests;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class GyroMotionTest extends LinearOpMode {

    /*
    Used to test gyroscope
     */

    @Override
    public void runOpMode(){

        ElapsedTime time = new ElapsedTime(0);
        ModernRoboticsI2cGyro gyro = hardwareMap.get(ModernRoboticsI2cGyro.class,"gyroscope");
        telemetry.addData("Gyro: ","Calibrating");
        telemetry.update();

        gyro.calibrate();
        while(gyro.isCalibrating()){sleep(100);}

        telemetry.addData("DONE: ","Starting, you have 15 seconds");
        telemetry.update();

        sleep(1000);

        gyro.resetZAxisIntegrator();

        time.reset();

        while(time.seconds() < 15){
            telemetry.addData("Time: ",15-time.seconds());
            telemetry.addData("Heading: ", gyro.rawZ());
            telemetry.update();

        }


    }

}
