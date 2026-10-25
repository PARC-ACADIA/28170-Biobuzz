package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Intake {
     private DcMotorEx motor;
     private double IN_POWER;
     private double OUT_POWER;

     private double setPower;

     public Intake(LinearOpMode opMode) {
         motor = opMode.hardwareMap.get(DcMotorEx.class, "intake");
     }

     public void setPower(double power) {
         setPower = power;
     }
     public void in() {
         setPower(IN_POWER);
     }
     public void out() {
         setPower(OUT_POWER);
     }
     public double getPower() {
         return setPower;
     }
     public void stopIntake() {
         setPower(0.0);
     }
}
