//    [!] CHECK DEVICE NAMES [!]

//    [!] DO NOT RUN UNTIL PARAMETERS ARE CHECKED [!]

package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@Autonomous(name="PendingAdvancedBrickCode")
public class PendingAdvancedBrickCode extends LinearOpMode {

    private static final double COUNTS_PER_MOTOR_REV = 1120;
    private static final double DRIVE_GEAR_REDUCTION = 1;
    private static final double WHEEL_DIAMETER_INCHES = 4.0;
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);


    private ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;

        arm.baseRotateDegree(30, 1);
        arm.baseMotor.setPower(.25);

        waitForStart();

        arm.baseMotor.setPower(0);

        bot.move(0, 30); //Move the bot to in front of the bricks, within scanning distance

        bot.intake(30, -1);

/*
        //Moves bot to under the bridge
        bot.move(24, 0);
        bot.move(0, 24);

        bot.rotate(90); //Turns bot to face build platform
        bot.move(0, 36); //Moves bot to in front of build platform


        //Moves bot to in front of the build platform
        while (!bot.detectColor()) {
            bot.move(0, 1);
        }
        if(bot.detectColor()){
            bot.placeBlock();
        }

        bot.move(0, -24);// moves bot onto mid-line



    }
 */
    }
}