package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@TeleOp
public class RemovableTestTeleOpPushButtons extends OpMode{
    private ElapsedTime passTime = new ElapsedTime(0);

    private FinalBot testBot;

    @Override

    public void init(){

        telemetry.addData("Status", "Initializing");

        testBot = new FinalBot(hardwareMap);

        telemetry.addData("Status", "Initialized");

    }

    public void loop() {

        if(gamepad1.left_stick_y > 0 || gamepad1.left_stick_x > 0){

            testBot.move(0.0, 0.0);

        }

    }

    private void wait(int ms){
        passTime.reset();
        while(passTime.milliseconds() < ms);//waits for ms
    }

}