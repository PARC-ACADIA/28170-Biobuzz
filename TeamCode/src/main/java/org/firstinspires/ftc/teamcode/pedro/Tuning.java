package org.firstinspires.ftc.teamcode.pedro;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.procedures.MecanumTuner;

@Autonomous(name = "Pedro Tuning Wizard", group = "Pedro")

public class Tuning extends LinearOpMode {
    @Tuner
    public static Procedure mecanumTuner() {
        return new MecanumTuner();
    }
}
