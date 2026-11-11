package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Intake {
     private DcMotorEx motor; //RPM_1150
     private DcMotorEx RampMotor; //RPM_312
     private final double IN_POWER = 1.0;
     private final double OUT_POWER = -1.0;

     private double setPower;

     public Intake(LinearOpMode opMode) {
         motor = opMode.hardwareMap.get(DcMotorEx.class, "Intake");
         RampMotor = opMode.hardwareMap.get(DcMotorEx.class, "RampMotor");
     }

     public void setPower(double power) {
         motor.setPower(power);
         RampMotor.setPower(power);
     }
     public void setPower(double mainPower, double rampPower){
         motor.setPower(mainPower);
         RampMotor.setPower(rampPower);
     }
     public void in() {
         setPower(IN_POWER);
     }
     public void out() {
         setPower(OUT_POWER);
     }
     public double getPower() {
         return motor.getPower();
     }
     public void stopIntake() {
         setPower(0.0);
     }
}
