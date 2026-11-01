package org.firstinspires.ftc.teamcode.TeleOp;


import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Tester", group = "Teleop")
public class ServoTester extends LinearOpMode {
    public static Servo servo;
    private GamepadEx gp1;

    public void runOpMode() throws InterruptedException {
        //change this to set flywheel velocity
        servo = hardwareMap.get(Servo.class, "Blocker");

        gp1 = new GamepadEx(gamepad1);

        waitForStart();

        servo.setPosition(0);
        while (opModeIsActive()) {

            gp1.readButtons();
            double position = 0;


            if (gp1.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
                position +=0.05;
            }
            else if (gp1.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
                position -= 0.05;
            }

            servo.setPosition(position);
            telemetry.addData("Servo:", servo.getPosition());

            telemetry.update();
        }

    }}
