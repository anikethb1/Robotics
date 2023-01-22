package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

/*
    This bot is for testing motor(s) only
    A robot with (4) motors used to test basic movement during initial stages of programming
 */

import com.qualcomm.robotcore.hardware.*; //imports robot-core hardware, imports all of them for convenience

public class TestBot {

    //motor initialization

    public DcMotor backLeft;//name corresponds with motor location
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;

    //initialize other vars

    private HardwareMap hardwareMap;//map of where all the hardware is at, used to "link" motors to DCMotor class

    //constructor(s)

    public TestBot() {

    }//default constructor

    //req. methods

    /*
        Run this before using the bot
        Maps motors to those specified by the hardwareMap and sets motor modes
     */
    public void init(HardwareMap hardMap, DcMotor.RunMode r) {
        hardwareMap = hardMap;//sets

        //sets motors
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        //NOTE: fill "" with device name

        //sets motor modes
        backLeft.setMode(r);
        backRight.setMode(r);
        frontLeft.setMode(r);
        frontRight.setMode(r);

    }

    /*
        Sets modes of all motors
        Sets mode to r
    */
    public void setMode(DcMotor.RunMode r) {
        //sets motor modes
        backLeft.setMode(r);
        backRight.setMode(r);
        frontLeft.setMode(r);
        frontRight.setMode(r);
    }


    /*
        Sets power of all motors to a single value, mostly here for convenience
     */
    public void setPower(double p) {
        setPower(p,p);//reduces repetition
    }

    /*
        Sets power of all motors on the left and right sides
     */
    public void setPower(double l, double r){
        backRight.setPower(r);
        frontRight.setPower(r);
        backLeft.setPower(l);
        frontLeft.setPower(l);
    }

}//NOTE need to create a setAll method for all motor nodes
