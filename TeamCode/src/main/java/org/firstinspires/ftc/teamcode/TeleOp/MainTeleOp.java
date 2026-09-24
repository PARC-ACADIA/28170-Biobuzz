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

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Main TeleOp", group = "Teleop")
public class MainTeleOp extends LinearOpMode {
    public DcMotorEx motor;

    public static double speed;
    public static GamepadEx gp1;

    public void runOpMode() throws InterruptedException{
        motor = hardwareMap.get(DcMotorEx.class, "Cannon");
        gp1 = new GamepadEx(gamepad1);

        waitForStart();
        while (opModeIsActive()){

            if (gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
                motor.setVelocity(speed*360, AngleUnit.DEGREES);
            }
            else{
                motor.setVelocity(0);
            }

        }

    }

}