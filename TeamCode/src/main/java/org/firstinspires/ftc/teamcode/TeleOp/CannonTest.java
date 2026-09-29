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

@TeleOp(name = "Cannon Test", group = "Teleop")
public class CannonTest extends LinearOpMode {
    public DcMotorEx motor;

    public static double speed;
    public static GamepadEx gp1;

    public void runOpMode()throws InterruptedException{
        motor = hardwareMap.get(DcMotorEx.class, "Cannon");
        speed = 3000;
        gp1 = new GamepadEx(gamepad1);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        double Ticks = (speed/60)*28;
        while (opModeIsActive()){

            if (gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
                motor.setVelocity(Ticks);
                //rotations per minute times 360 degrees per rotation times 1/60 minutes/second
            }
            else{
                motor.setVelocity(0);
            }

        }

    }

}