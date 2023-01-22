package org.firstinspires.ftc.teamcode.TestCode.MotorTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
A program to find how much power is needed to be delivered to the arm to keep it in place
//0.25
*/

@TeleOp
public class ArmLiftPower extends LinearOpMode {

    public void runOpMode(){

        double step = 0.25;//power step increments

        DcMotor baseMotor = hardwareMap.get(DcMotor.class, "baseMotor");//gets arm motor

        baseMotor.setPower(0);

        while(!gamepad1.x){
            telemetry.addData("X: ","EXIT");
            telemetry.addData("DpadLR: ","L Decrease, R Increase Power");
            telemetry.addData("DpadUD","U increase, D Decrease power increment");
            telemetry.addData("Step: ",step);
            telemetry.addData("CurrPower: ",baseMotor.getPower());
            telemetry.update();
            if(gamepad1.dpad_left){

                if(baseMotor.getPower() - step > 0){
                    baseMotor.setPower(baseMotor.getPower()-step);
                }

                while(gamepad1.dpad_left);//waits until unpressed

            }//gamepad left

            if(gamepad1.dpad_right){

                if(baseMotor.getPower() + step < 1){
                    baseMotor.setPower(baseMotor.getPower()+step);
                }

                while(gamepad1.dpad_right);//waits until unpressed

            }//gamepad right

            //------------------

            if(gamepad1.dpad_up){
                step *= 2;

                while(gamepad1.dpad_up);

            }

            if(gamepad1.dpad_down){
                step /= 2;

                while(gamepad1.dpad_down);

            }

            //------------------

        }

    }

}
