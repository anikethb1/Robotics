package org.firstinspires.ftc.teamcode.FinalBot.Internal_Code;

import android.graphics.Color;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;


public class FinalBot {

    //motors
    private BotWheels wheels;//movement is handled internally

    public BotWheels getWheels(){
        return wheels;
    }//gets the wheels for external use if required

    public BotIntake intake;
    public BotArm arm;
    public BotHook hook;
    public Servo cap;
    //sensors

    private ColorSensor colors;
    //private GyroSensor gyro;

    //constructors

    public FinalBot(HardwareMap map){

        //CRServo w = map.get(CRServo.class, "wristServo");
        //w.setPower(0);

        wheels = new BotWheels(map.get(DcMotor.class, "frontLeft"),map.get(DcMotor.class, "frontRight"),map.get(DcMotor.class, "backLeft"),map.get(DcMotor.class, "backRight"));
        //initializes botWheels

        // [!]  intake = new BotIntake(map.get(DcMotor.class, "intakeLeft"),map.get(DcMotor.class, "intakeRight"),map.get(ModernRoboticsI2cRangeSensor.class,"intakeDistance"),map.get(Servo.class, "finger"));
        intake = new BotIntake(map.get(DcMotor.class, "intakeLeft"),map.get(DcMotor.class, "intakeRight"),map.get(CRServo.class, "finger"));
        //creates intake w/o distance sensor

        //initializes intake motors and touch sensor(can replace with distance sensor in the future)

        /*[!]*/arm = new BotArm(map.get(DcMotor.class, "baseMotor"),map.get(Servo.class, "wristServo"), map.get(Servo.class, "handServo"), (ModernRoboticsI2cRangeSensor) map.get("armDistance"), (Servo) map.get("dropCap")); //change motor names
        arm.wristServo.setPosition(1);

        //colors = map.get(ColorSensor.class, "colorSensor");//initializes color sensor

        //gyro = map.get(GyroSensor.class, "gyroscope");

        hook = new BotHook(map.get(Servo.class, "hook"), map.get(Servo.class, "hook2"));



    }//basic constructor for initializing from a HardwareMap, use this in implementations of this class

    public boolean detectColor() {

        if(colors.red() > 10 && colors.green() > 10 && colors.blue() > 10){
            return true;
        }
        else{
            return false;
        }
    }//Detects if something is in front of robot

    public FinalBot(BotWheels wheels, BotIntake intake, BotArm arm){

        this.wheels = wheels;
        this.intake = intake;
        this.arm = arm;

        //insert sensor code here

    }//basic constructor used to set all parts of the bot

    //start work methods

    public void move(double x, double y) {

        double threshold = 5;//5 degree error threshold

        //double heading = gyro.getHeading();

        if(x != 0 && y != 0) {

            /*!*/
            double steps = 1;//move in steps of 1 inch (diagonally)
            double target = Math.sqrt((x * x) + (y * y));//sets diagonal target
            double angle = Math.atan(y / x);//use later

            for (double i = 0; i < target; i += steps) {
                wheels.moveRelativeY(Math.sin(angle) * steps, 1, 1);//moves y
                wheels.moveRelativeX(Math.cos(angle) * steps, 1, 1);//moves x
                /*
                if(Math.abs(gyro.getHeading()-heading) > threshold){
                    rotate(heading-gyro.getHeading());
                    calibrateGyro(5);
                    heading = gyro.getHeading();
                }//corrects course if bot gets thrown off heading
                */
            }//moves the bots in a "staircase" with a overall linear traverse of steps inches

        }else if(x == 0){//if is simple linear grid motion just call the methods

            wheels.moveRelativeY(y, 1, 20);

            /*
            if(Math.abs(gyro.getHeading()-heading) > threshold){
                rotate(heading-gyro.getHeading());
            }//corrects course if bot is thrown off heading
            */

        }else if(y == 0){

            wheels.moveRelativeX(x, 1, 20);
            /*
            if(Math.abs(gyro.getHeading()-heading) > threshold){
                rotate(heading-gyro.getHeading());
            }//corrects course if bot is thrown off heading
            */
        }

    }//moves bot by x/y values
    /*
        Assume x/y are in inches
        x and y move relative to the front of the bot:
            -x = side to side relative to forward
            -y = forward and back


            +x = right
            -x = left

            +y = forward
            -y = back
     */

    public void rotate(double degree){
        wheels.rotate(degree,0.75, 10);
    }//helper method for simplicity

    private void rotate(double degree, double speed /*ALWAYS set this to 1*/, double timeout ) {//(!)(!)(!) LEGACY CODE DO NOT USE (!)(!)(!)

        ElapsedTime time = new ElapsedTime();
        time.reset();
        /*
        double target = gyro.rawZ()+degree;

        target = target-(((int)target)/360)*360;
        */

        wheels.setPower(0,Math.abs(degree)/degree*speed);
        wheels.setPower(1,-Math.abs(degree)/degree*speed);
        wheels.setPower(2,(Math.abs(degree)/degree*0.95)*speed);
        wheels.setPower(3,(-Math.abs(degree)/degree*0.95)*speed);//sets wheels to rotate clockwise if degree is positive

        if(degree >= 0){//clockwise
            /*
            while(gyro.rawZ() < target && time.seconds() < timeout){}//waits until degree is greater than or equal to target loc
            */
        }else{//counterclockwise

            time.reset();
            /*
            while(gyro.rawZ() > target && time.seconds() < timeout){}//waits until degree is less than or equal to target loc
            */
        }

        wheels.setPower(0);//done

        double threshold = 5;//5 degree error threshold

        /*
        if(Math.abs(gyro.rawZ() - target) > threshold){
            time.reset();
            while(time.seconds() < 1);

            rotate(target-gyro.rawZ(), speed/2, 10);//try again but slower (less room for error as any overshoot is likely caused by too much speed on the motor)
        }//corrects any errors above threshold
        */
    }//rotates bot by degree rotates clockwise IE: compass


    public void placeBlock(){// (!)WIP(!)

        intake.openFinger();

        arm.handGrab(true);

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        while(time.milliseconds() < 300);

        arm.baseRotateTo(9,1); //Original deg = 170
        //arm.toggleWrist might be the problem
        arm.baseMotor.setPower(0.25);
        sleep(400);
        arm.wristOut();

        time.reset();
        while (time.milliseconds() < 100);

        arm.baseRotateDegree1(-50, .05);
        arm.handGrab(false);

        arm.baseMotor.setPower(0);

    }//places block from internal storage onto tower

    public void resetArm(){

        arm.handGrab(false);

        arm.baseRotateTo(7, 1); //Original deg = 170
        //arm.toggleWrist might be the problem
        arm.baseMotor.setPower(0.25);

        arm.wristIn();

        ElapsedTime time2 = new ElapsedTime(0);

        time2.reset();
        while (time2.milliseconds() < 100);

        arm.baseRotateDegree1(-50, .05);
    }

    public double intake(double timeout){

        double currCount = wheels.getWheel(2).getCurrentPosition();//current position of encoder

        intake.openFinger();

        ElapsedTime time = new ElapsedTime(0);//timer to attempt a blind intake for
        wheels.setPower(1);//wheels run until end of method

        time.reset();//resets time to 0
        intake.intakeStart();//starts intake system

        while(time.seconds() < timeout)//runs until intake is filled, or time has timed out

        wheels.setPower(0);//stops wheels
        intake.intakeStop();//stops intake

        intake.closeFinger();

        return ( wheels.getWheel(2).getCurrentPosition()-currCount ) / wheels.getCountsPerInch() / wheels.getDistanceModY();//returns difference in encoder position in inches
    }//attempts to fetch a block until a certain amount of time, exits if block is already in bay, returns distance travelled (Y)

    public double intakeTime(double timeout){

        ElapsedTime time = new ElapsedTime(0);

        double currCount = wheels.getWheel(2).getCurrentPosition();//current position of encoder

        intake.openFinger();//ensures bot finger is open

        wheels.setPower(1);
        intake.intakeStart();//start wheels and intake

        time.reset();

        while(time.seconds() < timeout);//waits

        wheels.setPower(0);
        intake.intakeStop();//stops

        intake.closeFinger();//toggles finger if block is in intake

        return ( wheels.getWheel(2).getCurrentPosition()-currCount ) / wheels.getCountsPerInch() / wheels.getDistanceModY();//returns difference in encoder position in inches
    }//intakes from front for a specified amount of time, returns distance travelled

    public double intake(double timeout, int dir /*-1 or 1, sets direction of travel -1 for left, 1 for right*/){

        ElapsedTime time = new ElapsedTime(0);

        double currCount = wheels.getWheel(3).getCurrentPosition();//current position of encoder

        //sets wheels to move l/r

        wheels.setPower(0, dir);//frontleft
        wheels.setPower(1, -dir);//frontright

        wheels.setPower(2, -dir*0.95);//backleft
        wheels.setPower(3, dir*0.95);//backright

        sleep(2000);

        //starts timer

        time.reset();

        colors.enableLed(true);
        for(float[] hsvValues = {0F, 0F, 0F};time.seconds() < timeout && (hsvValues[0] >= 0 && hsvValues[0] <= 100 && hsvValues[1] > .1 && hsvValues[1] < .5 && hsvValues[2] > .005 && hsvValues[2] < .6);Color.RGBToHSV(colors.red(), colors.green(), colors.blue(), hsvValues) );// skystone

        wheels.setPower(0);
        move(0,6);//moves away from blocks
        rotate(180);//rotates around
        move(0,-intake(timeout - time.seconds()) / wheels.getCountsPerInch()/ wheels.getDistanceModX());//attepts to intake block with time left and then moves back into position
        rotate(-180);//resets rot position
        move(0,-6);//finishes compensation

        intake.closeFinger();//closes/opens intake

        return ( wheels.getWheel(3).getCurrentPosition()-currCount ) / wheels.getCountsPerInch() / wheels.getDistanceModX();//returns difference in encoder position in inches
    }//attempts to fetch a block matching the color profile, use for green path, exits if block is already in bay, returns distance traveled (X)
    public void grabTray(){

        if(hook.hookDown()){
            hook.raiseHook();
        }else{
            hook.dropHook();
        }


    }//moves bot arm to grab tray on the ground, when called again, release tray

    public void grabBlock(){

        arm.baseRotateDegree(60, .5);
        arm.baseMotor.setPower(.25);

        arm.wristServo.setPosition(0);
        sleep(2000);
        arm.wristServo.setPosition(1);

        arm.handGrab(false);
        arm.baseMotor.setPower(0);
        arm.handGrab(true);

    }//grabs a block from behind robot and places into storage

    public double calibrateGyro(double timeout){

        ElapsedTime time = new ElapsedTime();
        time.reset();
        /*
        gyro.calibrate();//starts gyro calibration
        while(gyro.isCalibrating() && time.seconds() < timeout);//waits until gyro finishes calibrating

        return gyro.getHeading();
        */
        return 0;
    }//calibrates the gyroscope, returns heading

    public void init(){

        arm.baseRotateDegree(20, .8);

        arm.baseMotor.setPower(.2);
    }

    public void stopInit(){
        arm.baseMotor.setPower(0);
    }

    private void sleep(int ms){
        ElapsedTime time = new ElapsedTime(0);
        time.reset();
        while(ms < time.milliseconds()){}
    }
}