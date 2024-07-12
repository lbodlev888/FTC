package org.firstinspires.ftc.teamcode;

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
    public void runOpMode()
    {
        Servo incheietura = hardwareMap.servo.get("Incheietura");
        incheietura.setPosition(.4);
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
        runForward(3);
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
    }
}