package org.firstinspires.ftc.teamcode.UtilityCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.LinkedList;
/*
    This class is used to test all the motors/servos on the bot
 */

@TeleOp(name = "MotorServo Test")
public class MotorServoTest extends LinearOpMode {

    LinkedList<DcMotor> motors = null;
    LinkedList<Servo> servo = null;
    private ElapsedTime passTime = new ElapsedTime(0);//"clock" to keep track of passed time

    @Override//overrides opMode
    public void runOpMode() {

        try{
            motors = (LinkedList<DcMotor>) hardwareMap.getAll(DcMotor.class);//retrieves all motors and dumps into a linkedList (single)
            servo = (LinkedList<Servo>) hardwareMap.getAll(Servo.class);//retrieves all servos and dumps into a linkedList (single)
        }catch (NullPointerException e){//here to check for if hardwareMap is null, doesn't really matter in reality, just here to satisfy the bots runtime errors

        }//finish getting all motors/servos

        boolean a = false;
        boolean b = false;

        telemetry.addData("Choose Testing Mode: ","A to test all, B to selectively test");
        telemetry.update();

        while(!a && !b) {//stalls for either a or b input
            a = gamepad1.a;
            b = gamepad1.b;
        }//saves a/b inputs

        wait(300);

        if(a) {

            for (int i = 0; i < motors.size(); i++) {

                telemetry.addData("Testing Motor: ", hardwareMap.getNamesOf(motors.get(i)) + "| Press b to continue");
                telemetry.update();
                while (!gamepad1.b) ;//stalls until b is pressed

                //tests range of motor movement forward and backwards
                motors.get(i).setPower(-1);
                wait(300);
                motors.get(i).setPower(0);

                motors.get(i).setPower(1);
                wait(300);
                motors.get(i).setPower(0);

                telemetry.addData("Finished Testing Motor: ", hardwareMap.getNamesOf(motors.get(i)) + "| Press b to continue");
                telemetry.update();
                while (!gamepad1.b) ;//stalls until b is pressed
                wait(300);

            }//loops through all motors

            for (int i = 0; i < servo.size(); i++) {

                telemetry.addData("Testing Servo: ", hardwareMap.getNamesOf(servo.get(i)) + "| Press b to continue");
                telemetry.update();
                while (!gamepad1.b) ;//stalls until b is pressed

                //tests range of servos from min to max
                servo.get(i).setPosition(1);
                wait(300);

                servo.get(i).setPosition(0);
                wait(300);

                telemetry.addData("Finished Testing Servo: ", hardwareMap.getNamesOf(servo.get(i)) + "| Press b to continue");
                telemetry.update();
                while (!gamepad1.b) ;//stalls until b is pressed
                wait(300);

            }//loops through all servos

        }else if(b){

            telemetry.addData("Currently Testing:","A for servos, B for motors");
            telemetry.update();

            a = false;
            b = false;

            while(!a && !b){
                a = gamepad1.a;
                b = gamepad1.b;
            }//waits for a or b input

            int i = 0;
            wait(300);


            while(!gamepad1.x) {
                if (a) {
                    telemetry.addData("Testing:",hardwareMap.getNamesOf(servo.get(i)));
                } else if (b) {
                    telemetry.addData("Testing:",hardwareMap.getNamesOf(motors.get(i)));
                }//adds telemetry data based on selection

                telemetry.addData("Controls:","X to EXIT, B to TEST, DPAD L/R to select");
                telemetry.update();

                while(!gamepad1.x && !gamepad1.b && !gamepad1.dpad_left && !gamepad1.dpad_right);//waits for DPAD input

                if(gamepad1.dpad_right){
                    i++;

                    while(gamepad1.dpad_right);

                }else if(gamepad1.dpad_left){
                    i--;

                    while(gamepad1.dpad_left);

                }else if(gamepad1.b){

                    wait(300);

                    telemetry.addData("Testing:","START");
                    telemetry.update();

                    if(a){
                        servo.get(i).setPosition(1);
                        wait(300);

                        telemetry.addData("Testing: ", "Press A to reset servo");

                        while(!gamepad1.a);//waits for input

                        servo.get(i).setPosition(0);//tests servos
                    }else if(b){
                        motors.get(i).setPower(1);
                        wait(300);
                        motors.get(i).setPower(-1);
                        wait(300);
                        motors.get(i).setPower(0);//tests motors
                    }

                    telemetry.addData("Testing: ","DONE, press B to continue");
                    telemetry.update();
                    while(!gamepad1.b);//waits for b input

                    wait(300);

                }//tests for DPAD input

                int m = 0;

                if(a){
                    m = servo.size();
                }else if(b){
                    m = motors.size();
                }//selects max values for m

                if(i >= m){
                    i = 0;//loops back around
                }else if(i < 0){
                    i = m-1;//loops back around
                }//wraps i around min and max values

            }//continues until x is pressed

        }
    }

    private void wait(int ms){
        passTime.reset();
        while(passTime.milliseconds() < ms);//waits for ms
    }

}
