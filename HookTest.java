package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class HookTest extends OpMode {

    public Servo finger;
    public Servo hook;
    public ElapsedTime time = new ElapsedTime();

    @Override
    public void init() {

        hook = hardwareMap.get(Servo.class, "hook");
        finger = hardwareMap.get(Servo.class, "finger");


        hook.setPosition(1);
        time.reset();
        while(time.seconds() < 2){}

        hook.setPosition(0);
    }


    public void loop(){
    }
}

