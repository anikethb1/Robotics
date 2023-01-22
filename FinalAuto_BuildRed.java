package org.firstinspires.ftc.teamcode.FinalBot.FinalOpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotWheels;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@Autonomous(name="UpdatedBuildRed")
public class FinalAuto_BuildRed extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();

        //runs robot up over build plate edge, then backs up and drags plate

        arm.handServo.setPosition(0);
        arm.baseMotor.setPower(.4);
        sleep(2500);
        arm.baseMotor.setPower(-.4);
        //arm.wristServo.setPosition(1);
        sleep(2500);
        wheels.setPower(1);
        sleep(10000);

        wheels.setPower(0);
        arm.baseMotor.setPower(0);





     }
}
