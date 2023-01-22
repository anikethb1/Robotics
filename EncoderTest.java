package org.firstinspires.ftc.teamcode.TestCode.MotorTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class EncoderTest extends LinearOpMode {

    public void runOpMode(){
        DcMotor wheel = hardwareMap.get(DcMotor.class,"backLeft");

        wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        wheel.setTargetPosition(wheel.getCurrentPosition()+1000);

        wheel.setPower(1);

        while(wheel.isBusy()){
            telemetry.addData("Tar: ",wheel.getTargetPosition());
            telemetry.addData("Cur: ",wheel.getCurrentPosition());
            telemetry.update();
        }

        wheel.setPower(0);

    }

}
