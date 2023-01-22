package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@TeleOp
public class ArmWristDataGet extends LinearOpMode {

    public void runOpMode(){

        FinalBot bot = new FinalBot(hardwareMap);

        Servo wrist = bot.arm.wristServo;

        telemetry.addData("Move: ","X");
        telemetry.update();

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        while (!gamepad1.x);

        telemetry.addData("STOP: ","Y");
        telemetry.update();

        //wrist.setPower(-0.1);

        while(!gamepad1.y){

         //   wrist.setPower(-0.1);

        }//hold position until released

        //3wrist.setPower(0);

        double t = time.seconds();

        telemetry.addData("TIME: ", t*0.1);
        telemetry.update();
        sleep(20000);

    }

}
