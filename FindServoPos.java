package org.firstinspires.ftc.teamcode.UtilityCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.LinkedList;

/*
This class is used to
 */

@TeleOp(name = "Servo Position Finder")
public class FindServoPos extends LinearOpMode {

    public void runOpMode(){
        LinkedList<Servo> servos = (LinkedList<Servo>) hardwareMap.getAll(Servo.class);

        int selected = 0;
        while(!gamepad1.y){

            //input detection

            if(gamepad1.dpad_up){

                selected ++;

               sleep(300);//waits to prevent spam inputs

            }//up is detected

            if(gamepad1.dpad_down){

                selected --;

                sleep(300);//waits to prevent spam inputs

            }//down is detected

            //selected conversions

            if(selected >= servos.size()){
                selected = 0;
            }//checks for excess
            if(selected < 0){
                selected = servos.size()-1;
            }//checks for below 0

            //output ui

            for(int i = 0; i < servos.size();i++){

                String prefix;

                if(i == selected){
                    prefix = ">"+i+"< : ";
                }else{
                    prefix = "["+i+"] : ";
                }//adds selection node

                telemetry.addData(prefix,hardwareMap.getNamesOf(servos.get(i)));//adds number + name

            }//prints UI

            telemetry.addData("Controls: ","Y-Exit, DPAD U/D to navigate");
            telemetry.addData("Value: ",servos.get(selected).getPosition());

            telemetry.update();

        }//loops until y is pressed

    }

}
