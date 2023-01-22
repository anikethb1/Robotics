package org.firstinspires.ftc.teamcode.UtilityCode;

/*
    finds all attached objects to the controller
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import java.util.LinkedList;

@Autonomous(name = "FindAllObjects")
public class FindAll extends LinearOpMode {

    @Override
    public void runOpMode(){

        LinkedList<? extends HardwareDevice> l = (LinkedList<HardwareDevice>) hardwareMap.getAll(HardwareDevice.class);

        for(int i = 0; i < l.size();i++){
            telemetry.addData(hardwareMap.getNamesOf(l.get(i))+": ",l.get(i));
        }

        telemetry.update();

        sleep(30000);

    }

}
