package org.firstinspires.ftc.teamcode.TestCode.MotorTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@Autonomous(name="FullMotionTest")
public class FullMotionTest extends LinearOpMode {

    @Override
    public void runOpMode(){

        FinalBot bot = new FinalBot(hardwareMap);

        //bot.placeBlock();k
        //bot.hook.raiseHook();
        //bot.hook.dropHook();
        telemetry.addData("DONE: ","INIT");
        telemetry.update();
        //bot.arm.toggleWrist(false);
        /*
        bot.arm.wristServo.setPower(-1);

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        while(time.seconds() < 0.55);

        bot.arm.wristServo.setPower(0);


         */

        bot.move(12,0);
        bot.move(-12,0);
        bot.move(0,12);
        bot.move(0,-12);

        //bot.placeBlock();
        //bot.arm.wristOut();
        //bot.arm.wristIn();
    }

}
