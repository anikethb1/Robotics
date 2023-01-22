/*
Gamepad 1 (movement):

    -Left Joystick
        -Forwards and Backwards (y-axis)
        -Strafing (x-axis)

    -Right Joystick
        - Turning (x-axis)

    -Button A/B
        -Toggles hook position (0/1)

    -Button X + Y + Triggers
        -Drops capstone


Gamepad 2 (arm, intake, claw):

    -Right Joystick
        -Base Motor movement (y-axis)

    -Triggers
        -Finger Movement

    -Left Joystick
        -Wrist Movement (x-axis)

    -Button A/B/Y
        -Hand Movement
            - A = Up
            - B = Down
    -D-Pad
         -Intake
 */

package org.firstinspires.ftc.teamcode.FinalBot.FinalOpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotHook;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotIntake;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotWheels;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@TeleOp(name="!!MainTeleOp_FinalBot_Modified")
public class FinalTeleOp_FinalBot_Modified extends LinearOpMode {

    private static final double MOVE_DEADZONE = 0.5;
    private static final double MIN_ACTIVATION = 0.1;
    boolean isHookRaised = true;
    boolean isHandGrab = false;

    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();
        BotHook hook = bot.hook;
        BotIntake intake = bot.intake;

        Move previousAction = Move.NO_MOVE;
        double previousPower = 0.0;


        //initializes to average pos
        arm.dropCap.setPosition(1);
        intake.finger.setPower(0);
        arm.wristServo.setPosition(1);
        wheels.setPower(0);
        arm.baseMotor.setPower(0);
        arm.handGrab(false);


        waitForStart();

        while (opModeIsActive()) {
            switch (determineMove()) {

                case ForwardTurn:

                    double basePower = gamepad1.left_stick_y;
                    double altPower = gamepad1.left_stick_x;

                    wheels.setPower(0, basePower + altPower);
                    wheels.setPower(1, basePower - altPower);
                    wheels.setPower(2, basePower + altPower);
                    wheels.setPower(3, basePower - altPower);



                    /*
                    double turnPower;

                    //sets overall basepower to minimum of .5
                    double basePower = gamepad1.left_stick_y;
                    if(gamepad1.left_stick_y < .2){
                        basePower = .2;
                    }

                    wheels.setPower(basePower);

                    if(gamepad1.left_stick_x > .2){
                        turnPower = gamepad1.left_stick_x + basePower;
                        wheels.setPower(0, turnPower);
                        wheels.setPower(2, turnPower);
                    }else if(gamepad1.left_stick_x < -.2){
                        turnPower = Math.abs(gamepad1.left_stick_x) + basePower;
                        wheels.setPower(1, turnPower);
                        wheels.setPower(3, turnPower);
                    }
                    break;

*/
                case BackwardTurn:




                    /*
                    //sets overall basepower to minimum of -.5
                    basePower = gamepad1.left_stick_y;
                    if(gamepad1.left_stick_y > -.2){
                        basePower = -.2;
                    }

                    wheels.setPower(basePower);

                    if(gamepad1.left_stick_x > .2){
                        turnPower = basePower - Math.abs(gamepad1.left_stick_x);
                        wheels.setPower(1, turnPower);
                        wheels.setPower(3, turnPower);
                    }else if(gamepad1.left_stick_x < -.2){
                        turnPower = basePower - Math.abs(gamepad1.left_stick_x);
                        wheels.setPower(0, turnPower);
                        wheels.setPower(2, turnPower);
                    }
                    break;
 */
                    /*
                case FB:
                    double power = gamepad1.left_stick_y;
                    if (previousAction == Move.FB
                            && Math.abs(power - previousPower) < MIN_ACTIVATION)
                        break;

                    wheels.setPower(0, -power);
                    wheels.setPower(1, -power);
                    wheels.setPower(2, -power * .95);
                    wheels.setPower(3, -power * .95);

                    previousAction = Move.FB;
                    previousPower = power;
                    break;

                case TURNING:
                    power = gamepad1.right_stick_x;
                    if (previousAction == Move.TURNING)
                        break;
                    wheels.setPower(0, power);
                    wheels.setPower(1, -power);
                    wheels.setPower(2, power * .95);
                    wheels.setPower(3, -power * .95);
                    previousAction = Move.TURNING;
                    previousPower = power;
                    break;
                    */

                case STRAFING:
                    double power = gamepad1.right_stick_x;
                    if (previousAction == Move.STRAFING
                            && Math.abs(power - previousPower) < MIN_ACTIVATION) // [???]
                        break;
                    wheels.setPower(0, power);
                    wheels.setPower(1, -power);
                    wheels.setPower(2, -power * .95);
                    wheels.setPower(3, power * .95);

                    previousAction = Move.STRAFING;
                    previousPower = power;
                    break;
                case SLOW_FB:
                    if (gamepad1.right_stick_y > 0)
                        power = 0.05;
                    else
                        power = -0.05;
                    if (previousAction == Move.SLOW_FB && power == previousPower)
                        break;
                    wheels.setPower(power);
                    previousAction = Move.SLOW_FB;
                    previousPower = power;
                    break;
                case TOGGLE_HOOK:
                    hookPos(hook);
                    while(gamepad1.a || gamepad1.b)
                    previousAction = Move.TOGGLE_HOOK;
                    previousPower = 0.0;
                    break;
                case DROP_CAPSTONE:
                    arm.dropCap.setPosition(0.0); //check pos
                    while(gamepad1.x || gamepad1.y || gamepad1.left_trigger > .2 || gamepad1.right_trigger > .2)
                    sleep(500);
                    arm.dropCap.setPosition(1.0); //check pos
                    previousAction = Move.DROP_CAPSTONE;
                    previousPower = 0.0;
                    break;
                case ARM_MOVE:
                    power = gamepad2.right_stick_y;
                    if (previousAction == Move.ARM_MOVE
                            && Math.abs(power - previousPower) < MIN_ACTIVATION)
                        break;
                    if (gamepad2.right_stick_y < -0.3) {
                        power = -0.3;
                    } // failsafe (doesn't slam arm down)
                    arm.baseMotor.setPower(power);
                    previousAction = Move.ARM_MOVE;
                    previousPower = power;
                    break;
                case WRIST_MOVE:
                    double pos = arm.wristServo.getPosition();
                    if (gamepad2.left_stick_x < 0) {
                        pos -= 0.05;
                        if (pos < 0.0)
                            pos = 0.0;
                    } else {
                        pos += 0.05;
                        if (pos > 1.0)
                            pos = 1.0;
                    }
                    arm.wristServo.setPosition(pos);
                    previousAction = Move.WRIST_MOVE;
                    previousPower = 0.0;
                    break;
                case FINGER_MOVE_L:
                    if (previousAction == Move.FINGER_MOVE_L)
                        break;
                    intake.finger.setPower(-0.5);
                    previousAction = Move.FINGER_MOVE_L;
                    previousPower = 0.0;
                    break;
                case FINGER_MOVE_R:
                    if (previousAction == Move.FINGER_MOVE_R)
                        break;
                    intake.finger.setPower(0.5);
                    previousAction = Move.FINGER_MOVE_R;
                    previousPower = 0.0;
                    break;
                case HAND_MOVE:
                    handPos(arm);
                    previousAction = Move.HAND_MOVE;
                    previousPower = 0.0;
                    break;
                case INTAKE:
                    if (gamepad2.dpad_up) {
                        intakePower(intake,0.3);
                    } else if (gamepad2.dpad_down) {
                        intakePower(intake,0.0);
                    } else {
                        intakePower(intake,-0.3);
                    }
                    previousAction = Move.INTAKE;
                    previousPower = 0.0;
                    break;
                case CANCEL:
                    wheels.setPower(0.0);
                    arm.baseMotor.setPower(0.0);
                    intake.finger.setPower(0.0);
                    previousAction = Move.CANCEL;
                    previousPower = 0.0;
                    break;
                case NO_MOVE:
                default:
                    if(previousAction == Move.FB || previousAction == Move.STRAFING || previousAction == Move.TURNING){
                        wheels.setPower(0.0);
                    }else if(previousAction == Move.ARM_MOVE){
                        arm.baseMotor.setPower(0.0);
                    }else if(previousAction == Move.FINGER_MOVE_L || previousAction == Move.FINGER_MOVE_R){
                        intake.finger.setPower(0);
                    }
                    previousAction = Move.NO_MOVE;
                    previousPower = 0.0;
                    break;
            }
        }
    }

    private void hookPos(BotHook hook){

        if (!isHookRaised) {
            hook.raiseHook();
        } else {
            hook.dropHook();
        }
        isHookRaised = !isHookRaised;
    }

    private void handPos(BotArm arm){

        if (!isHandGrab) {
            arm.handGrab(true);
        } else {
            arm.handGrab(false);
        }
        isHandGrab = !isHandGrab;

    } //toggles hook pos when 'a' is pressed

    private void intakePower(BotIntake intake, double x){

        intake.motors[0].setPower(x);
        intake.motors[1].setPower(-x);
    }

    private Move determineMove() {
        if (gamepad1.back || gamepad2.back)
            return Move.CANCEL;

        else if(gamepad1.left_stick_y > .2){
            return Move.ForwardTurn;
        }
        else if(gamepad1.left_stick_y < -.2){
            return Move.BackwardTurn;
        }
/*
        else if (Math.abs(gamepad1.left_stick_y) >= MOVE_DEADZONE)
            return Move.FB;

        else if (Math.abs(gamepad1.right_stick_x) >= MOVE_DEADZONE)
            return Move.TURNING;
        */
        else if (Math.abs(gamepad1.right_stick_x) >= MOVE_DEADZONE)
            return Move.STRAFING;

        else if (Math.abs(gamepad1.right_stick_y) >= MOVE_DEADZONE)
            return Move.SLOW_FB;

        else if (gamepad1.a || gamepad1.b)
            return Move.TOGGLE_HOOK;

        else if (gamepad1.x && gamepad1.y
                && Math.abs(gamepad1.left_trigger) >= MOVE_DEADZONE
                && Math.abs(gamepad1.right_trigger) >= MOVE_DEADZONE)
            return Move.DROP_CAPSTONE;

        else if (Math.abs(gamepad2.right_stick_y) >= MOVE_DEADZONE)
            return Move.ARM_MOVE;

        else if (Math.abs(gamepad2.left_stick_x) >= MOVE_DEADZONE)
            return Move.WRIST_MOVE;

        else if (Math.abs(gamepad2.left_trigger) >= MOVE_DEADZONE)
            return Move.FINGER_MOVE_L;

        else if (Math.abs(gamepad2.right_trigger) >= MOVE_DEADZONE)
            return Move.FINGER_MOVE_R;

        else if (gamepad2.a)
            return Move.HAND_MOVE;

        else if (gamepad2.dpad_up || gamepad2.dpad_down || gamepad2.dpad_left || gamepad2.dpad_right)
            return Move.INTAKE;

        else
            return Move.NO_MOVE;
    }

    private enum Move {
        ForwardTurn, BackwardTurn, FB, STRAFING, TURNING, SLOW_FB, TOGGLE_HOOK, DROP_CAPSTONE,
        ARM_MOVE, WRIST_MOVE, FINGER_MOVE_L, FINGER_MOVE_R,
        HAND_MOVE, INTAKE, CANCEL, NO_MOVE
    }
}
