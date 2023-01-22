package org.firstinspires.ftc.teamcode.TestCode.MotorTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

/*
    This class is used to calibrate the mechanum wheel movement for the main method via dead reckoning
 */

@TeleOp(name = "Calibrate and get wheel calibration nums")
public class MechanumCalibration extends LinearOpMode {

    @Override
    public void runOpMode(){

        FinalBot bot = new FinalBot(hardwareMap);//creates test finalBot, the only thing that we care about here is the wheels

        telemetry.addData("Calibration: ", "Press A to start");
        telemetry.update();//updates instructions on gamepad

        while(!gamepad1.a);//waits until A is pressed

        telemetry.addData("Calibrating: ","Y Traverse 24inches / 2 ft");
        telemetry.update();

        bot.move(0, 24);//attempts to move bot by 24 in

        pause(2);//pauses for 2 seconds

        telemetry.addData("Calibration: ","Finished movement, record distance moved");
        telemetry.addData("Instructions: ","Take recorded distance and divide 24in by it");
        telemetry.addData("Continue: ","Press A to continue to test X");
        telemetry.update();

        while(!gamepad1.a);//waits

        //start x tests

        telemetry.addData("Calibrating: ","X Traverse 24inches / 2 ft");
        telemetry.update();

        bot.move(24, 0);//attempts to move bot by 24 in

        pause(2);//pauses for 2 seconds

        telemetry.addData("Calibration: ","Finished movement, record distance moved");
        telemetry.addData("Instructions: ","Take recorded distance and divide 24in by it");
        telemetry.addData("Continue: ","Press A to continue to EXIT");
        telemetry.update();

        while(!gamepad1.a);//waits


    }

    private void pause(double t){

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        while(time.seconds() < t);//waits until t is done

    }

}
