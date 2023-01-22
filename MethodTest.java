package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotWheels;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;


@Autonomous(name="MethodTest2")
public class MethodTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        FinalBot bot = new FinalBot(hardwareMap);
        BotWheels wheels =  bot.getWheels();

        telemetry.addData("Moving", "10 forward");
        telemetry.update();
        wheels.moveRelativeY(10, 1, 10);

        /*
        telemetry.addData("Moving", "10 right");
        telemetry.update();
        bot.move(10, 0);
*/
        telemetry.addData("Moving", "10 back");
        telemetry.update();
        wheels.moveRelativeY(-10, 1, 10);
/*
        telemetry.addData("Moving", "10 left");
        telemetry.update();
        bot.move(-10, 0);
*/
        telemetry.addData("Finished", "Moving");

    }
}
