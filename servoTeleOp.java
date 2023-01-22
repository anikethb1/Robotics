package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class servoTeleOp extends OpMode{
    private ElapsedTime passTime = new ElapsedTime(0);

    private DcMotor intakeMotorRight;
    private DcMotor intakeMotorLeft;

    private Servo finger;

    private DcMotor arm;
    private Servo wrist;
    private Servo hand;

    @Override

    public void init(){

        telemetry.addData("Status", "Initializing");
        telemetry.update();


        intakeMotorRight = hardwareMap.get(DcMotor.class, "intakeRight");
        intakeMotorLeft = hardwareMap.get(DcMotor.class, "intakeLeft");

        finger = hardwareMap.get(Servo.class, "finger");

        arm = hardwareMap.get(DcMotor.class,"baseMotor");

        wrist = hardwareMap.get(Servo.class,"wrist");
        hand = hardwareMap.get(Servo.class,"hand");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void loop(){

        if(gamepad2.dpad_left){

            intakeMotorRight.setPower(-0.1);
            intakeMotorLeft.setPower(0.1);

        }
        if(gamepad2.dpad_up){

            intakeMotorRight.setPower(-0.3);
            intakeMotorLeft.setPower(0.3);

        }
        if(gamepad2.dpad_right){

            intakeMotorRight.setPower(-0.5);
            intakeMotorLeft.setPower(0.5);

        }
        if(gamepad2.dpad_down){

            intakeMotorRight.setPower(0);
            intakeMotorLeft.setPower(0);

        }

        if(gamepad2.right_trigger > 0 && finger.getPosition() + 0.1 <= 1){

            telemetry.addData("Servo","Finger moving up");
            wait(3000);
            finger.setPosition(finger.getPosition() + 0.1);
        }
        if(gamepad2.right_bumper && finger.getPosition() - 0.1 >= 0){
            telemetry.addData("Servo","Finger moving down");
            wait(3000);
            finger.setPosition(finger.getPosition() - 0.1);

        }

        if(gamepad2.left_trigger > 0 && wrist.getPosition() + 0.1 <= 1){
            telemetry.addData("Servo","Wrist moving up");
            wait(3000);
            wrist.setPosition(wrist.getPosition() + 0.1);
        }
        if(gamepad2.left_bumper && wrist.getPosition() - 0.1 >= 0){
            telemetry.addData("Servo","Wrist moving down");
            wait(3000);
            wrist.setPosition(wrist.getPosition() - 0.1);

        }

        if(gamepad2.x && hand.getPosition() + 0.1 <= 1){
            telemetry.addData("Servo","Hand moving up");
            wait(3000);
            hand.setPosition(hand.getPosition() + 0.1);

        }
        if(gamepad2.y && hand.getPosition() - 0.1 >= 0){
            telemetry.addData("Servo","Hand moving down");
            wait(3000);
            hand.setPosition(hand.getPosition() - 0.1);

        }

        telemetry.update();//updates telemetry with new input data


    }

    private void wait(int ms){
        passTime.reset();
        while(passTime.milliseconds() < ms);//waits for ms
    }

}