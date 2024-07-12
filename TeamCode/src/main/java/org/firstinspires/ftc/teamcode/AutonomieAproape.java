package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutonomieAproape extends LinearOpMode {
    private final DcMotor[] motors = new DcMotor[4];
    private final String[] names = {"LeftFront", "LeftBack", "RightFront", "RightBack"};

    @Override
    public void runOpMode() {
        Servo incheietura = hardwareMap.servo.get("Incheietura");
        DcMotor macara = hardwareMap.dcMotor.get("Macara");
        incheietura.setPosition(.75);
        macara.setTargetPosition(70);
        macara.setMode(RUN_TO_POSITION);
        macara.setPower(0.3);
        for(int i = 0; i < 4; i++)
        {
            motors[i] = hardwareMap.dcMotor.get(names[i]);
            motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        motors[2].setDirection(DcMotor.Direction.REVERSE);
        motors[3].setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        if(isStopRequested()) return;
        runForward(2.5);
        while(opModeIsActive());
    }
    private void runForward(double requiredTime)
    {
        ElapsedTime el = new ElapsedTime();
        el.startTime();
        for(int i = 0; i < 4; i++)
            motors[i].setPower(.3);
        while(requiredTime > el.time());
        el.reset();
        for(int i = 0; i < 4; i++) motors[i].setPower(0);
        Servo gheara = hardwareMap.servo.get("Gheara");
        Servo incheietura = hardwareMap.servo.get("Incheietura");
        incheietura.setPosition(0.6);
//        Thread.sleep(200);
        gheara.setPosition(0.2);
    }
}