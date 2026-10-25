package org.firstinspires.ftc.teamcode.TeleOp;
import static java.lang.Math.abs;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Main TeleOp", group = "Teleop")
public class MainTeleOp extends LinearOpMode {
    public MotorEx motor;


    public static GamepadEx gp1;

    public void runOpMode()throws InterruptedException{
        motor = new MotorEx(this.hardwareMap, "IntakeMotor", Motor.GoBILDA.RPM_1150);
        motor.setRunMode(Motor.RunMode.RawPower);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        gp1 = new GamepadEx(gamepad1);


        waitForStart();

        while (opModeIsActive()){

            if (gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
                bot.intake.in();    //NEED TO MAKE BOT CLASS (SUBSYTEM)
                //rotations per minute times 360 degrees per rotation times 1/60 minutes/second
            }
            else if(gp1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1){
                bot.intake.out();    //SAME THING
            }
            else{
                bot.intake.stopIntake();
            }

            if (gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
                bot.outtake.shoot();
                //rotations per minute times 360 degrees per rotation times 1/60 minutes/second
            }
            else{
                bot.outtake.stopOuttake();
            }

        }

    }

}