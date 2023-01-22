package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class GetArmPosition extends LinearOpMode {

    @Override
    public void runOpMode(){
        Servo wrist = hardwareMap.get(Servo.class, "wristServo");//gets wrist

        Servo hand = hardwareMap.get(Servo.class, "handServo");//gets arm

        Servo finger = hardwareMap.get(Servo.class, "finger");//gets finger

        telemetry.addData("Wrist: ",wrist.getPosition());
        telemetry.addData("hand: ",hand.getPosition());
        telemetry.addData("Finger: ",finger.getPosition());
        telemetry.update();

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        while(time.seconds() < 20);//waits for 20 seconds


    }
}
