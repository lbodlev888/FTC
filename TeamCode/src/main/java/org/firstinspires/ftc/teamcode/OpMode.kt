package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple


@TeleOp(name= "TeleOp")
class OpMode: LinearOpMode() {
    override fun runOpMode() {
        // Declare our motors
        // Make sure your ID's match your configuration

        val LF = hardwareMap.dcMotor["LeftFront"]
        val LB = hardwareMap.dcMotor["LeftBack"]
        val RF = hardwareMap.dcMotor["RightFront"]
        val RB = hardwareMap.dcMotor["RightBack"]
        val Macara = hardwareMap.dcMotor["Macara"]
        val Incheietura = hardwareMap.servo["Incheietura"]
        val Gheara = hardwareMap.servo["Gheara"]

        LF.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        LB.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        RF.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        RB.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE


        LF.direction = DcMotorSimple.Direction.REVERSE
        LB.direction = DcMotorSimple.Direction.REVERSE

        var Incetinire = 1.0

        waitForStart()

        if (isStopRequested) return
        while (opModeIsActive()) {

            val y = -gamepad1.left_stick_y.toDouble() // Remember, Y stick value is reversed
            val x = gamepad1.left_stick_x * 1.1 // Counteract imperfect strafing
            val rx = gamepad1.right_stick_x.toDouble()

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            val denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)
            val frontLeftPower = (y + x + rx) / denominator
            val backLeftPower = (y - x + rx) / denominator
            val frontRightPower = (y - x - rx) / denominator
            val backRightPower = (y + x - rx) / denominator

            Incetinire = 1.0 - gamepad1.right_trigger * 0.5

            LF.power = frontLeftPower * Incetinire
            LB.power = backLeftPower * Incetinire
            RF.power = frontRightPower * Incetinire
            RB.power = backRightPower * Incetinire


        }
    }
}