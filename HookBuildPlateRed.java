package org.firstinspires.ftc.teamcode.FinalBot.FinalOpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotWheels;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

/*
Auto code for the blue route, assumes bot is facing wall with left side aligned with the line of the first block at the wall
 */

@Autonomous(name="!BuildRed")
public class HookBuildPlateRed extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //wait
        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();

        bot.init();



        waitForStart();

        //telemetry.addData("Readjustment: ","Wrist");
        //telemetry.update();

        bot.arm.wristServo.setPosition(100);

        sleep(35);

        bot.arm.wristServo.setPosition(100);

        //telemetry.addData("Readjustment: ","Drop Arm");
        //telemetry.update();

        bot.arm.baseRotateDegree1(-50,0.1);

        //

        //telemetry.addData("Move: ","To Tray");
        //telemetry.update();
        bot.move(0, -1);
        bot.move(-7,0); // OG -10
        //move towards tray
        bot.move(0,-29);//back into tray
        bot.getWheels().moveRelativeY(-9,0.2, 20); //OG -10 not -9


        wheels.setPower(-.05);
        sleep(1000);
        bot.hook.dropHook();//drops hook onto block
        sleep(500);
        wheels.setPower(0);



        for(int i = 0; i < 5; i++) {
               bot.getWheels().moveRelativeY(8, 0.2, 20);
               sleep(5);
        }

        bot.hook.raiseHook();//raises hook, is done with hook


        //telemetry.addData("Ram: ","Tray");
        //telemetry.update();

        ElapsedTime times = new ElapsedTime();

        bot.move(30,0);


        // [!] NEW CODE [!]
        bot.move(0, -42);


        //bot.move(0,-42); //OG code



        /*
        times.reset();
        while(times.milliseconds() < 300);
        */

        bot.move(-28,0);//rams plate

        bot.move(0,36); //OG 32

        //telemetry.addData("Park: ","Bot");
        //telemetry.update();

        bot.move(45,0);//parks under bridge

    }
}
