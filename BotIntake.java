package org.firstinspires.ftc.teamcode.FinalBot.Internal_Code;

import android.hardware.Sensor;

import com.qualcomm.ftccommon.configuration.EditLegacyServoControllerActivity;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/*
    This class simply controls the intake system with motors/sensor
 */


public class BotIntake {

    private DistanceSensor touch;//Distance sensor to detect a successful intake
    public CRServo finger;//For "ramming block into position" NOTE: 360 degree
    public DcMotor[] motors = new DcMotor[2];//motors for the intake system
    public  boolean fingerOpen;//true if open, false if closed
    //0:Left
    //1:Right

    public BotIntake(DcMotor intakeLeft, DcMotor intakeRight, DistanceSensor dist, CRServo finger){
        motors[0] = intakeLeft;
        motors[1] = intakeRight;
        this.touch = dist;
        this.finger = finger;
        closeFinger();

        motors[0].setPower(0);
        motors[1].setPower(0);

    }//basic constructor to create objects

    public BotIntake(DcMotor intakeleft, DcMotor intakeRight, CRServo finger){
        this(intakeleft,intakeRight,null,finger);
    }//basic contructor for use w/o distancesensor

    public void intakeStart(){
        /*(!)*/motors[0].setPower(1);//replace 1 with -1 or 1 depending on motor setup
        /*(!)*/motors[1].setPower(-1);//replace 1 with -1 or 1 depending on motor setup
    }//Starts the intake system

    public void intakeStop(){
        motors[0].setPower(0);
        motors[1].setPower(0);
    }//stops the intake system

    public void closeFinger(){
        /*(!)*///finger.setPosition(0.25);//change value later

        finger.setPower(1);//moves towards intake

        ElapsedTime time = new ElapsedTime(0);//timer

        while(time.seconds() < 1);//waits until finger is there

        finger.setPower(0);//exits

        fingerOpen = false;//toggles fingerOpen value

    }//clamps block inside intake

    public void openFinger(){
        /*(!)*///finger.setPosition(0);//change value later

        finger.setPower(-1);//moves away from intake

        ElapsedTime time = new ElapsedTime(0);//timer
        time.reset();
        while(time.seconds() < 1);//waits until finger is there

        finger.setPower(0);//exits

        fingerOpen = true;//toggles fingerOpen value
    }//releases block from inside intake

    }//closes finger if there is a block, opens if there isnt. If there is no distance sensor, acts as a normal toggle


