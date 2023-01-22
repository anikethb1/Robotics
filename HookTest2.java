package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@Autonomous
public class HookTest2 extends LinearOpMode {

    public void runOpMode(){

        FinalBot bot = new FinalBot(hardwareMap);

        sleep(1000);

        bot.hook.dropHook();

        sleep(1000);

        bot.hook.raiseHook();

        sleep(2000);

    }

}
