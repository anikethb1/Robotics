package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous (name = "ColorSensor")
public class ColorSensorTest extends LinearOpMode {


    public ElapsedTime time = new ElapsedTime(0);
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public ColorSensor toneObserver;

    public void runOpMode() {

        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        toneObserver = hardwareMap.get(ColorSensor.class, "toneObserver");

        //turn on Color Sensor LED
        toneObserver.enableLed(true);

        time.reset();

        //Array contianing Hue, Saturation, and Value of sensor
        float hsvValues[] = {0F, 0F, 0F};


        while (time.seconds() <= 60) {

            //Convert RGB to HSV and amplifies the values
            Color.RGBToHSV(toneObserver.red(), toneObserver.green(), toneObserver.blue(), hsvValues);


            //Displays RGB and HSV values on phone
            telemetry.addData("Red", toneObserver.red());
            telemetry.addData("Green", toneObserver.green());
            telemetry.addData("Blue", toneObserver.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("Saturation", hsvValues[1]);
            telemetry.addData("Value", hsvValues[2]);

            boolean runRGB = false; //True = RGB  ||  False = HSV -- Change value to change detection method

            //Run either RGB or HSV
            if (runRGB == true) {
                //     **RGB SCANNING**

                telemetry.addData("Running", "RGB Detection");

                // Scanning for Yellow
                if (toneObserver.red() > 13 && toneObserver.red() < 25 && toneObserver.green() > 5 && toneObserver.green() < 17 && toneObserver.blue() >= 0 && toneObserver.blue() < 7) {
                    telemetry.addData("Detecting", "Yellow");
                }

                //Scanning for Skystone
                else if (toneObserver.red() >= 0 && toneObserver.red() < 10 && toneObserver.green() >= 0 && toneObserver.green() < 7 && toneObserver.blue() >= 0 && toneObserver.blue() < 7) {
                    telemetry.addData("Detecting", "Skystone");
                }
                else {
                    telemetry.addData("Detecting", "Nothing");
                }
                telemetry.update();
            }

            if (runRGB == false) {
                //     **HSV SCANNING**

                telemetry.addData("Running", "HSV Detection");

                //Scanning Yellow
                if (hsvValues[0] > 35 && hsvValues[0] < 45 && hsvValues[1] > .85 && hsvValues[1] < 1.1 && hsvValues[2] > .1 && hsvValues[2] < 1.3) {
                    telemetry.addData("Detecting", "Yellow");
                }

                // ~~ .2 .03

                //Scanning Skystone
                else if (hsvValues[0] >= 0 && hsvValues[0] <= 100 && hsvValues[1] > .1 && hsvValues[1] < .5 && hsvValues[2] > .005 && hsvValues[2] < .6){
                    telemetry.addData("Detecting", "Skystone");
                }
                else {
                    telemetry.addData("Detecting", "Nothing");
                }
                telemetry.update();

            }
        }
    }
}