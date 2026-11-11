package org.firstinspires.ftc.teamcode.TeleOp;
import static java.lang.Math.abs;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Bot;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Main TeleOp", group = "Teleop")
public class MainTeleOp extends LinearOpMode {


    @Override
    public void runOpMode() {
        Bot bot = new Bot(this);
        GamepadEx gp1 = new GamepadEx(gamepad1);

        waitForStart();

        while (opModeIsActive()) {
            gp1.readButtons();
            double leftX = gp1.getLeftX();
            double leftY = gp1.getLeftY();
            double rightX = gp1.getRightX();
            double leftTrigger = gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
            boolean leftBumper = gp1.isDown(GamepadKeys.Button.LEFT_BUMPER);
            bot.drive(leftX, leftY, rightX, leftTrigger, leftBumper, 0.0);
            bot.periodic();
        }
    }
}