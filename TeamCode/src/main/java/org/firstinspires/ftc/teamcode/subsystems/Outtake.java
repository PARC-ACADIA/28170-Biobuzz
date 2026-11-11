package org.firstinspires.ftc.teamcode.subsystems;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Outtake {
    private DcMotorEx motor;
    public static double speed;

    public Outtake(LinearOpMode opMode) {
        motor = opMode.hardwareMap.get(DcMotorEx.class, "Cannon");
        speed = 3000;
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    public void shoot(double ticks) {
        motor.setVelocity(ticks);
    }

    public void stopOuttake() {
        motor.setVelocity(0);
    }
}
