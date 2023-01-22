package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@Autonomous
public class DirectionTest extends LinearOpMode {




    private double power = .1;

    @Override
    public void runOpMode() throws InterruptedException {

     FinalBot bot = new FinalBot(hardwareMap);
     BotArm arm = bot.arm;

     telemetry.addData("Arm Power", "Positive");
     telemetry.update();
     //arm.baseMotor.setPower(power);
     arm.baseRotateDegree(20, 0.1);
     sleep(2000);
     arm.baseMotor.setPower(0);

     telemetry.addData("Arm Power", "Negative");
     telemetry.update();
     arm.baseMotor.setPower(-power);
     sleep(2000);
     arm.baseMotor.setPower(0);

     telemetry.addData("Wrist Position", "1");
     telemetry.update();
     //arm.wristServo.setPosition(1);

     sleep(2000);

     telemetry.addData("Wrist Position", "0");
     telemetry.update();
     //arm.wristServo.setPosition(0);

     sleep(2000);

     telemetry.addData("Hand Position", "1");
     telemetry.update();
     arm.handServo.setPosition(1);

     sleep(2000);

     telemetry.addData("Hand Position", "0");
     telemetry.update();
     arm.handServo.setPosition(0);
    }
}
