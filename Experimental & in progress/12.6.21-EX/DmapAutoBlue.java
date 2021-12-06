package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import java.util.List;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DmapAutoBlue {
    /* Public OpMode members. */
     public DcMotorEx leftdrive = null;
    public DcMotorEx rightdrive = null;
    public DcMotorEx spinner = null;
    public Servo claw = null;
    public DcMotorEx duck = null;
    public DcMotorEx Arm = null;
    public TouchSensor armBack; 
    public RevBlinkinLedDriver led;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public DmapAutoBlue(){

    }

    /* HARDWARE MAP*/
    public void init(HardwareMap ahwMap) {
        
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftdrive  = hwMap.get(DcMotorEx.class, "leftdrive");
        rightdrive = hwMap.get(DcMotorEx.class, "rightdrive");
        spinner = hwMap.get(DcMotorEx.class, "spinner");
        claw = hwMap.servo.get("claw");
        duck = hwMap.get(DcMotorEx.class, "duck");
        Arm = hwMap.get(DcMotorEx.class, "Arm");
        armBack = hwMap.get(TouchSensor.class, "armBack");
        led = hwMap.get(RevBlinkinLedDriver.class , "led");

        //set correct motor directions for driving
        leftdrive.setDirection(DcMotor.Direction.FORWARD);
        rightdrive.setDirection(DcMotor.Direction.REVERSE);
    }

    //reset all encoders
    public void resetEncoders() {
    leftdrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    rightdrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    duck.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    Arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    spinner.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    //move to pos
    public void rtp(int lde , int rde , int ve){
    //reset encoders before every cmd
    leftdrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    rightdrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

    //set pos
    leftdrive.setTargetPosition(lde);
    rightdrive.setTargetPosition(rde);

    //run to pos
    leftdrive.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    rightdrive.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

    //set velocity
    leftdrive.setVelocity(ve);
    rightdrive.setVelocity(ve);
    }
    
    public void wfl(){
    while(leftdrive.isBusy()) {
//waits for left motor (Only one is needed if both motors are moving) (Will be irrelevent if oodometry is setup)
 }
    }
    
    public void wfr(){
    while(rightdrive.isBusy()) {
//waits for right motor (Only one is needed if both motors are moving) (Will be irrelevent if oodometry is setup)
 }
    }
    
 //REMOVE ALL OLD CMDS AFTER TESTING
 
 //AUTO BLUE B LOW
 
 //AUTO BLUE B MID
 public void BlueBmid() throws InterruptedException {

//telemetry.addData("Running class:" , "BlueBmid");
//telemetry.update();

        
        // Reset the encoders using custom cmd
        resetEncoders();
        claw.setPosition(0);
        Thread.sleep(500);
        
        //run to pos custom class defined above
        rtp(450 , 450 , 500);
        
        //waits for left drive
        wfl();
        
        //same as above
        rtp(350 , 0 , 500);
        wfl();
        
        //set arm pos
        Arm.setTargetPosition(300);
        // Switch to RUN_TO_POSITION mode
        Arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      //set vel of arm
        Arm.setVelocity(800);
        //wait for arm to finish
        while(Arm.isBusy()) {
        }
        
    rtp(400 , 400 , 500);
    wfl();
    
//extend spinner
spinner.setTargetPosition(1000);
spinner.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
spinner.setVelocity(500);

while(spinner.isBusy()) {
}

//open claw
claw.setPosition(0.14);
//wait 1 second
Thread.sleep(1000);
//retract spinner
spinner.setTargetPosition(0);
spinner.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
spinner.setVelocity(1000);

while(spinner.isBusy()) {
}
 
//reverse a little
rtp(-500 , -500 , 500);
wfl();

 //arm down
    Arm.setTargetPosition(0);
    // Switch to RUN_TO_POSITION mode
        Arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
      //set vel of arm
        Arm.setVelocity(800);
 
 while(Arm.isBusy()) {
 }
 
//setup to spin duck
rtp(0 , -500 , 500);
wfr();
//drive forward to line up for duck
rtp(2250 , 2250 , 1000);
wfl();
//turn to line up
rtp(0 , 900 , 750);
wfr();
//reverse and touch duck
rtp(-900 , -900 , 500);
wfl();

//set target pos 2
duck.setTargetPosition(-2000);
 
duck.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//set target 2 speed
 duck.setVelocity(2000);

 while(duck.isBusy()) {
}

rtp(0 , 500 , 750);
wfr();

//rtp(3500 , 3500 , 1750);
//wfl();
 }
 
 
 
 //AUTO BLUE B HIGH
 
 //AUTO BLUE A LOW
 
 //AUTO BLUE A MID
 
 //AUTO BLUE A HIGH
}
