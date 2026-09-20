package org.firstinspires.ftc.teamcode.TeleOp;
import static java.lang.Math.abs;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Main TeleOp", group = "Teleop")
public class MainTeleOp extends LinearOpMode {
    public static MotorEx motor;
    public static GamepadEx gp1;

    public void runOpMode() throws InterruptedException{
        motor = new MotorEx(this.hardwareMap, "IntakeMotor", Motor.GoBILDA.RPM_1150);
        gp1 = new GamepadEx(gamepad1);

        waitForStart();
        while (opModeIsActive()){
            if (gp1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
                motor.set(1);
            }
            else if (gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
                motor.set(-1);
            }
            else{
                motor.set(0);
            }

        }

    }
    public void driveRobotCentric(double strafeSpeed, double forwardBackSpeed, double turnSpeed) {
        double[] speeds = {
                (forwardBackSpeed + strafeSpeed - turnSpeed),
                (forwardBackSpeed - strafeSpeed + turnSpeed),
                (forwardBackSpeed - strafeSpeed - turnSpeed),
                (forwardBackSpeed + strafeSpeed + turnSpeed)
        };
        double maxSpeed = 0;
        for (int i = 0; i < 4; i++) {
            maxSpeed = Math.max(maxSpeed, abs(speeds[i]));
        }
        if (maxSpeed > 1) {
            for (int i = 0; i < 4; i++) {
                speeds[i] /= maxSpeed;
            }
        }
        fl.set(speeds[0]);
        fr.set(speeds[1]);
        bl.set(speeds[2]);
        br.set(speeds[3]);
    }
}
