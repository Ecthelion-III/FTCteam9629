/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class MatrixK9TeleOp extends OpMode {

	DcMotor motorFrontRight;
	DcMotor motorFrontLeft;
	DcMotor motorBackRight;
	DcMotor motorBackLeft;

	public MatrixK9TeleOp() {

	}

	@Override
	public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

		motorFrontRight = hardwareMap.dcMotor.get("motor_1");
		motorFrontLeft = hardwareMap.dcMotor.get("motor_4");
		motorBackRight = hardwareMap.dcMotor.get("motor_3");
		motorBackLeft = hardwareMap.dcMotor.get("motor_2");

	}

	@Override
	public void loop() {

		float speedScale = 0.5f;
		//gamepad1.but;

		// clip the right/left values so that the values never exceed +/-
		float frontRight = Range.clip((gamepad1.left_stick_x * speedScale) - (gamepad1.left_stick_y * speedScale) - (gamepad1.right_stick_x * 0.6f * speedScale), -1, 1);
		float frontLeft = Range.clip((-gamepad1.left_stick_x * speedScale) + (gamepad1.left_stick_y * speedScale) - (gamepad1.right_stick_x * 0.6f * speedScale), -1, 1);
		float backRight = Range.clip((-gamepad1.left_stick_x * speedScale) - (gamepad1.left_stick_y * speedScale) - (gamepad1.right_stick_x * 0.6f * speedScale), -1, 1);
		float backLeft = Range.clip((gamepad1.left_stick_x * speedScale) + (gamepad1.left_stick_y * speedScale) - (gamepad1.right_stick_x * 0.6f * speedScale), -1, 1);

		// write the values to the motor
		motorFrontRight.setPower(frontRight);
		motorFrontLeft.setPower(frontLeft);
		motorBackRight.setPower(backRight);
		motorBackLeft.setPower(backLeft);

		// unused telemetry code sample
        //telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));

	}

	@Override
	public void stop() {

	}

}
