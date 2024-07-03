package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class OpModeJava extends LinearOpMode {
    DcMotor macara;
    @Override
    public void runOpMode() {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("LeftFront");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("LeftBack");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("RightFront");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("RightBack");
        macara = hardwareMap.dcMotor.get("Macara");
        Servo incheietura = hardwareMap.servo.get("Incheietura");
        Servo gheara = hardwareMap.servo.get("Gheara");
        Servo lansareAvion = hardwareMap.servo.get("Avion");

        macara.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //Scoatere din inertie
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        incheietura.setPosition(.4);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            if(gamepad1.left_trigger == 1)
                denominator *= 2;

            frontLeftMotor.setPower(frontLeftPower / denominator);
            backLeftMotor.setPower(backLeftPower / denominator);
            frontRightMotor.setPower(frontRightPower / denominator);
            backRightMotor.setPower(backRightPower / denominator);

            if(gamepad2.a){
                macara.setTargetPosition(585);
                macara.setMode(RUN_TO_POSITION);
                macara.setPower(0.5);
            }

            if(gamepad2.b) {
                macara.setTargetPosition(65);
                macara.setMode(RUN_TO_POSITION);
                macara.setPower(0.3);
            }

            if (gamepad2.x)
                incheietura.setPosition(0.6);

            if (gamepad2.y)
                incheietura.setPosition(0.75);

            if(gamepad2.dpad_down)
                gheara.setPosition(0.2);

            if(gamepad2.dpad_up)
                gheara.setPosition(0.57);

            if(gamepad1.left_bumper)
                lansareAvion.setPosition(0);

            if(gamepad1.right_bumper)
                lansareAvion.setPosition(0.5);

            telemetry.update();
        }
    }
}