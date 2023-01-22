package org.firstinspires.ftc.teamcode.FinalBot.Internal_Code;

import com.qualcomm.robotcore.hardware.Servo;

public class BotHook {

    Servo[] hook = new Servo[2];

    public BotHook(Servo hook, Servo hook2){
        this.hook[0] = hook;
        this.hook[1] = hook2;
        raiseHook();
    }//basic constructor

    public void dropHook(){
        /*(!)*/hook[0].setPosition(0.2);//change vals later  --  OG 0.4
        hook[1].setPosition(0.2);
    }//drops hook down

    public void raiseHook()
    {
        /*(!)*/hook[0].setPosition(1);//change vals later
        hook[1].setPosition(1);
    }//raises hook

    public boolean hookDown(){
        /*(!)*/return hook[0].getPosition() == 0;//change vals later
    }//returns status of hook, true if hook is down false if it is up

}
