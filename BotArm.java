/*
Dimensions of Arm:

10 inches tall (bot to top of arm (width) )
14 inches long (arm)
4 inches tall (bricks)
3 inches long (wrist)
 */
package org.firstinspires.ftc.teamcode.FinalBot.Internal_Code;

/*
    This class is used to manipulate the bot arm
 */

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class BotArm {

    /* For BaseRotateDegreeTo method
    //vars for encoders

    private static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder is 1440, neverest classic 40 are 1120
    private static final double     DRIVE_GEAR_REDUCTION    =  120/80;     // This is < 1.0 if geared UP

    //end encoder vars

    */

    private static final double rpm = 152;

    public DcMotor baseMotor;
    public Servo wristServo;
    public Servo handServo;
    public ModernRoboticsI2cRangeSensor range;
    public Servo dropCap;


    public BotArm(DcMotor base, Servo wrist, Servo hand, ModernRoboticsI2cRangeSensor range, Servo dropCap){
        baseMotor = base;
        wristServo = wrist;
        wristServo.setPosition(0);
        handServo = hand;
        this.dropCap = dropCap;
        dropCap.setPosition(1);

        this.range = range;
        //set servos and motors to 0

        baseMotor.setPower(0);
        handGrab(false);
        wristServo.setPosition(0);

    }//basic constructor to make a motor

    //start work method

    public void handGrab(boolean state){
        if (state){
            handServo.setPosition(0);
        }
        else{
            handServo.setPosition(0.5);
        }//closes hand || 1 - open     0 - closed

    }//detects if hand is closed or not and inverts results

    public void baseRotateDegree(double deg, double speed /*Never set this below 0 or above 1*/ ){

        ElapsedTime time = new ElapsedTime(0);

        double timeRot /*in seconds*/ = Math.abs(deg)/ ( (rpm*speed)/60*360 ) * 5;//calculates time that the arm needs to rotate for

        time.reset();

        baseMotor.setPower(speed*( Math.abs(deg)/deg ));
        while(time.seconds() < timeRot);
        baseMotor.setPower(0);

    }//rotates base degrees to l/r

    public void baseRotateDegree1(double deg, double speed /*Never set this below 0 or above 1*/ ){
        ElapsedTime time = new ElapsedTime(0);

        double timeRot /*in seconds*/ = Math.abs(deg)/ ( (rpm*speed)/60*360 );//calculates time that the arm needs to rotate for

        time.reset();

        baseMotor.setPower(speed*( Math.abs(deg)/deg ));
        while(time.seconds() < timeRot);
        baseMotor.setPower(0);

    }//rotates base degrees to l/r

    public  void baseRotateTo(int  distance, double power){

        baseMotor.setPower(power);

        while(range.rawUltrasonic() < distance);

        baseMotor.setPower(.2);

//        if(range.rawUltrasonic() > distance){
//            while(range.rawUltrasonic() > distance){
//                baseMotor.setPower(.1);
//            }
//            baseMotor.setPower(.2);
//        }//if overshot, realigns



    }//rotates base using distance sensor

    public float returnDistance(ModernRoboticsI2cRangeSensor range){
        return range.rawUltrasonic();
    }

    public void wristOut(){
        wristServo.setPosition(0);

    }//moves wrist to outpos

    public void wristIn(){
        wristServo.setPosition(1);

    }

/*
    public void baseRotateDegreeTo(DcMotor armMotor, double degree, double power){

        double currDeg = armMotor.getCurrentPosition()/COUNTS_PER_MOTOR_REV/DRIVE_GEAR_REDUCTION*360;//converts current encoder pos to a degree angle
        currDeg = currDeg-(((int)currDeg)/360*360);//removes excess 360's
        baseRotateDegree(armMotor,degree-currDeg,1);//rotates arm to pos
    }//rotates arm to target degree position
*/
    public void setGrabPos(){
        // [!] Check all parameters
        handGrab(false);
        baseRotateDegree(50, 1);
        wristIn();
        baseRotateDegree(-50, .1);

    }//sets all motors to pos to grab stored stone

    public boolean isCapDropped(){
        if(dropCap.getPosition() > .5){
            return true;
        }else if(dropCap.getPosition() <= .5){
            return false;
        }
       return false;
    }

}
