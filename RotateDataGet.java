package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class RotateDataGet extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor[] wheels = {hardwareMap.get(DcMotor.class,"frontLeft"), hardwareMap.get(DcMotor.class,"frontRight"),hardwareMap.get(DcMotor.class,"backLeft"),hardwareMap.get(DcMotor.class,"backRight")};

        int orig = wheels[0].getCurrentPosition();//gets heading

        while(!gamepad1.y){

            telemetry.addData("EXIT: ", "Y");
            telemetry.addData("ROT: ", "X");

            if(gamepad1.x){

                wheels[0].setPower(0.1);
                wheels[1].setPower(0.1);
                wheels[2].setPower(0.1*0.95);
                wheels[3].setPower(0.1*0.95);

            }else{
                for(DcMotor d: wheels){
                    d.setPower(0);
                }
            }

            telemetry.addData("Difference: ",wheels[0].getCurrentPosition()-orig);
            telemetry.update();

        }//run until y is pressed

    }
}
