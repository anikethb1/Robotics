package org.firstinspires.ftc.teamcode.TestCode.MiscTests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@Autonomous(name="BRDT")
public class BaseRotateDegreeTest extends LinearOpMode {

    //public Servo hand;
    //public CRServo wrist;
    //public CRServo finger;
    //public Servo hook;

    @Override
    public void runOpMode() throws InterruptedException {
        //hand = hardwareMap.get(Servo.class, "handServo");
        //wrist = hardwareMap.get(CRServo.class, "wristServo");
        //finger = hardwareMap.get(CRServo.class, "finger");
        //hook = hardwareMap.get(Servo.class, "hook");

        //hand.setPosition(1);
        //wrist.setPower(0);
        //finger.setPower(0);
        //hook.setPosition(0);

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;

        sleep(2000);

        arm.baseRotateDegree( 20, 1); //moves arm up 10 degrees
        arm.baseMotor.setPower(.25);//holds arm at position
        sleep(2000);

        arm.baseRotateDegree(10, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);

        arm.baseRotateDegree(20, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);

        arm.baseRotateDegree(40, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);

        arm.baseMotor.setPower(0);
        sleep(4000);

        arm.baseRotateDegree(80, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);


        arm.baseMotor.setPower(0);

    }
}
