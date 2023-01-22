package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous(name="!TEST_INIT!")
public class TEST_INIT extends LinearOpMode {

    private CRServo finger;
    private CRServo wrist;

    @Override
    public void runOpMode() throws InterruptedException {

        finger = hardwareMap.get(CRServo.class, "finger");


        finger.setPower(1);
        sleep(2000);
        finger.setPower(0);

        telemetry.addData("Initialization", "Finished");
        telemetry.update();

        waitForStart();

        finger.setPower(-1);
        sleep(2000);
        finger.setPower(0);

        telemetry.addData("OpMode", "Finished");

    }
}
