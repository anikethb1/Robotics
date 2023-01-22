package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

/*
    This test is used to test omnidirectional controller input for mechanum wheels via controller1
 */

@TeleOp(name="MultipleDirectionTest")
public class TeleOpMovementOmniDirectional extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);

        while(opModeIsActive()){

            double[] wheelPowers = new double[4];
            for(int i = 0; i < wheelPowers.length; i++){
                wheelPowers[i] = 0;
            }//sets all wheels to 0

            //0:FrontLeft
            //1:FrontRight
            //2:BackLeft
            //3:BackRight

            //insert forward/backward movement
            wheelPowers[0] += gamepad1.left_stick_y;
            wheelPowers[1] -= gamepad1.left_stick_y;
            wheelPowers[2] += gamepad1.left_stick_y;
            wheelPowers[3] -= gamepad1.left_stick_y;

            //insert left/right movement
            wheelPowers[0] += gamepad1.left_stick_x;
            wheelPowers[1] += gamepad1.left_stick_x;
            wheelPowers[2] += gamepad1.left_stick_x;
            wheelPowers[3] += gamepad1.left_stick_x;

            //factors out largest num to make all vals less than 1

            int maxI = 0;//maximum wheel value
            for(int i = 0; i < wheelPowers.length; i++){
                if(Math.abs(wheelPowers[i]) > Math.abs(wheelPowers[maxI])){
                    maxI = i;
                }
            }//gets max wheel num

            if(Math.abs(wheelPowers[maxI]) > 1){
                double divisers = Math.abs(wheelPowers[maxI]);//gets divider

                for(int i = 0; i < wheelPowers.length; i++){
                    wheelPowers[i] /= divisers;
                }

            }//factors out vals > 1 to 1

            for(int i = 0; i < wheelPowers.length; i++) {
                bot.getWheels().setPower(wheelPowers[i]);
            }//sets corresponding wheel power

        }//runs while opMode is on (runs till user manually turns it off)

    }
}
