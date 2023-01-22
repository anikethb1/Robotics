package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class OtherDirectionTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
    DcMotor baseMotor = hardwareMap.get(DcMotor.class, "baseMotor");
    Servo wristServo = hardwareMap.get(Servo.class, "wristServo");
    Servo handServo = hardwareMap.get(Servo.class, "handServo");

    double power = .3;
/*
        telemetry.addData("Arm Power", "Positive");
        telemetry.update();
        baseMotor.setPower(power);
        sleep(2000);
        baseMotor.setPower(0);

        telemetry.addData("Arm Power", "Negative");
        telemetry.update();
        baseMotor.setPower(-power);
        sleep(2000);
        baseMotor.setPower(0);
*/

        telemetry.addData("Wrist Position", "1");
        telemetry.update();
        wristServo.setPosition(1);

        sleep(2000);

        telemetry.addData("Wrist Position", "0");
        telemetry.update();
        wristServo.setPosition(0);

        sleep(2000);

        telemetry.addData("Hand Position", "1");
        telemetry.update();
        handServo.setPosition(1);

        sleep(2000);

        telemetry.addData("Hand Position", "0");
        telemetry.update();
        handServo.setPosition(0);
    }
}
