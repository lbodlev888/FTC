package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class OpModeJava extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("LeftFront");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("LeftBack");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("RightFront");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("RightBack");
        DcMotor macara = hardwareMap.dcMotor.get("Macara");
        Servo incheietura = hardwareMap.servo.get("Incheietura");
        Servo gheara = hardwareMap.servo.get("Gheara");
        Servo lansareAvion = hardwareMap.servo.get("Avion");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.

        macara.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //Scoatere din inertie
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

      // macara.setTargetPosition();
        double Incetinire = 1.0;
       waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            //Incetinire = 1.0 - gamepad1.right_trigger * 0.5;


            frontLeftMotor.setPower(frontLeftPower / denominator);
            backLeftMotor.setPower(backLeftPower / denominator);
            frontRightMotor.setPower(frontRightPower/ denominator);
            backRightMotor.setPower(backRightPower/ denominator);

            telemetry.addData("Macara Pos", macara.getCurrentPosition());

            telemetry.update();

            double apucare = gamepad2.left_stick_y;
            if(apucare != 0) {
                macara.setMode(RUN_WITHOUT_ENCODER);
                macara.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                macara.setDirection(DcMotorSimple.Direction.REVERSE);
                if(apucare < 0) {
                    macara.setDirection(DcMotorSimple.Direction.FORWARD);
                    apucare = Math.abs(apucare);
                }
                macara.setPower(apucare);
            }

            if(gamepad2.a){
                macara.setTargetPosition(635);
                macara.setMode(RUN_TO_POSITION);
                macara.setPower(0.5);
            }

            if(gamepad2.b){
               // macara.setDirection(DcMotorSimple.Direction.REVERSE);
                macara.setTargetPosition(50);
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
                gheara.setPosition(0.75);

            if(gamepad1.left_bumper)
                lansareAvion.setPosition(0);

            if(gamepad1.y)
                lansareAvion.setPosition(0.5);

            telemetry.update();
        }
    }
}