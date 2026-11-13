package org.firstinspires.ftc.teamcode.subsystems;
import static java.lang.Math.abs;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

import java.util.ArrayList;
import java.util.List;


public class Bot{
    public LinearOpMode opMode;

    public Intake intake;
    public Outtake outtake;

    public static double Mode = 1;
    public static double SF = -0.025;
    public static double start = 0.1;
    public static Servo servo;
    public static double end = 0.4;
    //public static double driveMult = 1;
    private GamepadEx gp1;
    public MotorEx bl, br, fl, fr;
    public static double speed;
    public static double Ticks;
    public Bot (LinearOpMode opMode){
        //change this to set flywheel velocity
        speed = 3000; //in rpm
        Ticks = (speed/60)*28;
        this.opMode = opMode;

        servo = opMode.hardwareMap.get(Servo.class, "Blocker");

        double position = 0;

        gp1 = new GamepadEx(opMode.gamepad1);

        fl = new MotorEx(opMode.hardwareMap, "LFD",Motor.GoBILDA.RPM_312);
        fr = new MotorEx(opMode.hardwareMap, "RFD", Motor.GoBILDA.RPM_312);
        bl = new MotorEx(opMode.hardwareMap, "LBD", Motor.GoBILDA.RPM_312);
        br = new MotorEx(opMode.hardwareMap, "RBD", Motor.GoBILDA.RPM_312);
        fr.setInverted(true);
        bl.setInverted(true);
        fl.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);

        fl.setRunMode(Motor.RunMode.RawPower);
        fr.setRunMode(Motor.RunMode.RawPower);
        bl.setRunMode(Motor.RunMode.RawPower);
        br.setRunMode(Motor.RunMode.RawPower);
    }

    public void addTelemetry() {
        Telemetry telemetry = opMode.telemetry;
        telemetry.addData("LeftX:", gp1.getLeftX());
        telemetry.addData("LeftY:", gp1.getLeftY());
        telemetry.addData("RightX:", gp1.getRightX());
        telemetry.addData("Trigger", gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));

        telemetry.update();
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
    public static double variableSpeedV1(double input) {
        double sign = Math.signum(input);
        double x = Math.min(abs(input), 1.0); // magnitude, clamped to [0,1]

        double y;
        if (x < 0.1) {
            y = 0.0;                          // deadzone
        } else if (x > 0.9) {
            y = 1.0;                          // saturation
        } else if (x < 0.5) {
            y = 0.2 * x + 0.1;                // linear ramp (fine control)
        } else {
            // exponential ramp — value & slope matched to the linear
            // piece at x=0.5, and lands exactly on y=1 at x=0.9
            double A = 0.00024126;
            double k = 9.0375;
            double B = 0.17787;
            y = A * Math.exp(k * x) + B;
        }

        return sign * y; // preserves direction of the input
    }
    public static double variableSpeed(double input) {
        double sign = Math.signum(input);
        double x = Math.min(abs(input), 1.0); // magnitude, clamped to [0,1]

        double y;
        if (x < 0.1) {
            y = 0.0;                          // deadzone
        } else if (x > 0.9) {
            y = 1.0;                          // saturation
        } else if (x < 0.5) {
            y = 0.4 * x + 0.2;                // linear ramp (fine control)
        } else {
            // exponential ramp — value & slope matched to the linear
            // piece at x=0.5, and lands exactly on y=1 at x=0.9
            double A = 0.004333;
            double k = 5.6035;
            double B = 0.328616;
            y = A * Math.exp(k * x) + B;
        }

        return sign * y; // preserves direction of the input
    }

    public static double LinearSpeed(double input){
        double sign = Math.signum(input);
        double x = Math.min(abs(input), 1.0);

        if (x<0.05) return 0.0;//deadzone

        double y = (end-start)*x + start;
        return sign * y;
    }
    public void periodic() {
        gp1.readButtons();
        addTelemetry();
    }

    public void drive(double leftX, double leftY, double rightX, double leftTrigger, boolean leftBumper, double correction) {
        ///uncomment all lines to get the exponential scaling
        double a;
        double b;
        //double trigger = Math.min(driveMult -gp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER), 1);
        //AprilTag Facing Function:
        if(leftBumper && abs(rightX) < 0.1) {
            a = correction;
            b = correction;
        } else {
            a = LinearSpeed(-rightX);
            b = -rightX;
        }

        if (leftTrigger > 0.1 /*&& Mode == 1*/) {
            driveRobotCentric(LinearSpeed(leftX) , LinearSpeed(leftY), a);
        } else /* leftTrigger<0.1 && Mode == 1 */{
            driveRobotCentric(leftX, leftY, b);
        }
        /*
        else{

            driveRobotCentric(
                variableSpeed(leftX) * triggerModifier,
                variableSpeed(leftY) * triggerModifier,
                variableSpeed(-rightX) * triggerModifier
            );
        }
        */
    }
}