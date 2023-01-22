/*
Gamepad 1 (movement):

    -Left Joystick
        -Forwards and Backwards (y-axis)
        -Strafing (x-axis)

    -Right Joystick
        - Turning (x-axis)

    -Button A/B
        -Toggles hook position (0/1)



Gamepad 2 (arm, intake, claw):

    -Right Joystick
        -Base Motor movement (y-axis)

    -Triggers
        -Finger Movement

    -Left Joystick
        -Wrist Movement (x-axis)

    -Button A/B/Y
        -Hand Movement
            - A = Up
            - B = Down
        - Button X
            -placeBlock()

    -D-Pad
         -Intake
 */

package org.firstinspires.ftc.teamcode.FinalBot.FinalOpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@TeleOp(name="@AltTeleOp")
public class AlternativeTeleOp extends LinearOpMode {
    private ElapsedTime passTime = new ElapsedTime(0);

    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    private DcMotor intakeMotorRight;
    private DcMotor intakeMotorLeft;

    private CRServo finger;

    private DcMotor arm;
    private CRServo wrist;
    private Servo hand;
    private Servo hook;

    private boolean armUp;
    private ElapsedTime time;
    private double posHold;

    private FinalBot bot;

    @Override
    public void runOpMode() throws InterruptedException {

        bot = new FinalBot(hardwareMap);
        time = new ElapsedTime(0);
        armUp = false;
        posHold = 0;

        //mapping devices
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        intakeMotorRight = hardwareMap.get(DcMotor.class, "intakeRight");
        intakeMotorLeft = hardwareMap.get(DcMotor.class, "intakeLeft");

        finger = hardwareMap.get(CRServo.class, "finger");
        hook = hardwareMap.get(Servo.class, "hook");

        arm = hardwareMap.get(DcMotor.class, "baseMotor");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wrist = hardwareMap.get(CRServo.class, "wristServo");
        hand = hardwareMap.get(Servo.class, "handServo");

        finger.setPower(1);
        wait(2000);
        finger.setPower(0);

        while (opModeIsActive()) {

            if (gamepad1.left_stick_y < 0) { //Forwards

                allMotors(-gamepad1.left_stick_y);

            }

            if (gamepad1.left_stick_y > 0) { //Backwards

                allMotors(-gamepad1.left_stick_y);

            }

            if (gamepad1.left_stick_x < 0) { //Strafe Left

                frontLeft.setPower(-1);
                frontRight.setPower(-1);
                backLeft.setPower(1);
                backRight.setPower(1);

            }

            if (gamepad1.right_stick_x + gamepad1.right_stick_y + gamepad1.left_stick_y + gamepad1.left_stick_x == 0) {

                allMotors(0);

            }

            if (gamepad1.left_stick_x > 0) { //Strafe Right

                frontLeft.setPower(1);
                frontRight.setPower(1);
                backLeft.setPower(-1);
                backRight.setPower(-1);

            }

            if (gamepad1.right_stick_x < 0) { //Turning Left

                frontLeft.setPower(-.7);
                frontRight.setPower(-.7);
                backLeft.setPower(-.7);
                backRight.setPower(-.7);

            }

            if (gamepad1.right_stick_x > 0) { //Turning Right

                frontLeft.setPower(.7);
                frontRight.setPower(.7);
                backLeft.setPower(.7);
                backRight.setPower(.7);

            }
 /*  //UNCOMMENT FOR BUG TESTING
        //For testing to find perfect intake speed [!] UNTESTED [!]

            if (gamepad2.dpad_right) {
                if (intPow >= 0 && intPow <= 1) {
                    intakePower(intPow);
                }
            } else if (gamepad2.dpad_left) {
                intakePower(0);
            } else if (gamepad2.dpad_up) {
                intPow += .05;
            } else if (gamepad2.dpad_down) {
                intPow -= .05;
            }
*/
//            telemetry.addData("Intake Power", intPow);
//            telemetry.update();

            //while(gamepad2.dpad_right || gamepad2.dpad_left || gamepad2.dpad_down || gamepad2.dpad_up); //stops program until dpad button is released


            // Intake movement
            if (gamepad2.dpad_up) {
                intakePower(.3);
            } else if (gamepad2.dpad_down) {
                intakePower(0);
            } else if (gamepad2.dpad_left || gamepad2.dpad_right) {
                intakePower(-.3);
            }
            if (gamepad2.right_stick_y < 0) {
                posHold = 0;
                arm.setPower(.85); //Testing
                telemetry.addData("Arm", "Moving up 1");
                telemetry.update();
            } else if (gamepad2.right_stick_y > 0) {
                posHold = 0;
                arm.setPower(-.05);
                telemetry.addData("Arm", "Moving down 0.5");
                telemetry.update();
            } else if (!armUp) {
                arm.setPower(posHold);
                telemetry.addData("Arm", "0");
                telemetry.update();
            }

            if (gamepad2.y) {
                bot.arm.baseRotateDegree(45, 1);
                bot.arm.handGrab(false);
                arm.setPower(0.25);
                posHold = 0.25;
            }//holds block position for intake

            //button to move the arm to a certain angle
            if (gamepad2.x) {

                //bot.placeBlock();
                //bot.resetArm();
            }

            //Moves finger
            if (gamepad2.right_trigger > 0 && gamepad2.left_trigger == 0) {
                finger.setPower(1);
            } else if (gamepad2.left_trigger > 0 && gamepad2.right_trigger == 0) {
                finger.setPower(-1);
            } else if (gamepad2.right_trigger == 0 && gamepad2.left_trigger == 0) {
                finger.setPower(0);
            }

            // uses joystick to move wrist

            if (gamepad2.left_stick_x < 0) {
                wrist.setPower(.15);

            } else if (gamepad2.left_stick_x > 0) {
                wrist.setPower(-.15);

            } else {
                wrist.setPower(0);
            }


            //moves hand servo
            if (gamepad2.a) {
                hand.setPosition(0.5);
            } else if (gamepad2.b) {
                hand.setPosition(0);
            }

            //moves build plate hook
            if (gamepad1.a) {
                hook.setPosition(1);//up
            } else if (gamepad1.b) {
                hook.setPosition(0);//down
            }

        }
    }

    //hi
    private void allMotors(double x) {

        frontLeft.setPower(x);
        frontRight.setPower(-x);
        backLeft.setPower(x);
        backRight.setPower(-x);

    }

    private void wait(int ms) {
        passTime.reset();
        while (passTime.milliseconds() < ms) ;//waits for ms
    }

    private void intakePower(double x) {
        intakeMotorLeft.setPower(x);
        intakeMotorRight.setPower(-x);
    }

}