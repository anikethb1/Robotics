package org.firstinspires.ftc.teamcode.FinalBot.FinalOpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@Autonomous(name="!BrickRed")
public class DropWaitParkRed extends LinearOpMode {

    public void runOpMode(){
        //wait
        FinalBot bot = new FinalBot(hardwareMap);

        bot.arm.baseRotateDegree(45, 1);
        bot.arm.baseMotor.setPower(.25);

        waitForStart();

        telemetry.addData("Readjustment: ","Wrist");
        telemetry.update();

        bot.arm.wristServo.setPosition(100); //change

        sleep(60); //originally 40

        bot.arm.wristServo.setPosition(100);//change

        telemetry.addData("Readjustment: ","Drop Arm");
        telemetry.update();

        bot.arm.baseRotateDegree1(-50,0.1);

        //

        sleep(16000);

        bot.move(0,-60);
        //bot.placeBlock();
        bot.move(0,32);

    }

}
