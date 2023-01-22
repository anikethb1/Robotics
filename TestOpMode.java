package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

//Reference : https://github.com/ftctechnh/ftc_app/wiki/Creating-and-Running-an-Op-Mode-(Android-Studio)

/*
    Test class to run OpMode for TestBot
    Use for basic movement tests
*/
@Autonomous(name = "testLinearOpMode")
public class TestOpMode extends LinearOpMode {

    //initialize vars

    //static vars

    private ElapsedTime passTime = new ElapsedTime(0);//"clock" to keep track of passed time

    TestBot bot = new TestBot();//robot class that stores all motors

    //constructors

    public TestOpMode() {

    }//default constructor

    //req. methods

    /*
        Runs "Operation" to move robot
        Moves motors and inits TestBot
     */
    @Override//overrides opMode
    public void runOpMode() {

        //updates telemetry (phone data)
        telemetry.addData("Status: ","Initializing Bot");
        telemetry.update();

        bot.init(hardwareMap, DcMotor.RunMode.RUN_WITHOUT_ENCODER);//initializes motors for bot
        //Run with raw motor input for lower complexity on this test
        //starts active runmode, end init

        //updates telemetry
        telemetry.addData("Status: ","Waiting for Start");
        telemetry.update();

        waitForStart();//waits until start command is sent

        //updates telemetry
        telemetry.addData("Status: ","Running...");
        telemetry.update();

        runSingleDirection(1,3);//run motors

        //updates telemetry
        telemetry.addData("Status: ","Done!");
        telemetry.update();
    }

    /*
        Work method that rotates motor with encoder
     */
    private void runSingleDirection(double power, double time){
        if(opModeIsActive()) {//checks for active opMode before proceding
            bot.setPower(power);//sets all wheel power to power to start movemenmt
            passTime.reset();//resets timer back to 0 for counting purposes
            while(passTime.seconds() < time);//waits until time is out

            bot.setPower(power);//resets bot
        }
        //Note: making a separate "Wheels" class may be convenient for restoring the state of wheel rotation instead of setting to 0
    }

}
