package org.firstinspires.ftc.teamcode.TestCode.MotorTests;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/*
    The purpose of this program is to simply test PID control
 */

@Autonomous
public class DistanceArmTest extends LinearOpMode {

    ModernRoboticsI2cRangeSensor range;

    public void runOpMode(){

        range = (ModernRoboticsI2cRangeSensor) hardwareMap.get("armDistance");

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        range.enableLed(false);

        while(time.seconds() < 15){
            telemetry.addData("Dist: ", range.cmUltrasonic());
            telemetry.update();
            sleep(100);
        }

    }

}
