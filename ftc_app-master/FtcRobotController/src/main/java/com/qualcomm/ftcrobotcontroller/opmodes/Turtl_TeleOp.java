package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by TeamMember on 2/6/2016.
 */
public class Turtl_TeleOp extends OpMode {

    private DriveTrain _driveTrain;
    private Lift _lift;
    private GamepadDriver _gamepadDriver;

    @Override
    public void init() {

        //this will create a driver that uses a gamepad as its input
        _gamepadDriver = new GamepadDriver(gamepad1);

        //this will create a drive train with the selected motors
        _driveTrain = new DriveTrain(hardwareMap, "motor_1", "motor_2", "motor_3", "motor_4");

        //this will create a lift with the selected motors and will drive using the selected gamepad
        _lift = new Lift(gamepad2, hardwareMap, "motor_5", "motor_6", "motor_7", "motor_8");
        //_lift = new Lift(gamepad2, hardwareMap, "motor_5", "motor_6", "motor_7", false);

    }

    @Override
    public void loop() {

        //this will tell the drive train to move.
        //Movement calls will be gathered from the gamepad driver and the lift
        //The lift will run its own movement calls when the drive requests movement from the lift
        _driveTrain.driveUsing(_gamepadDriver, _lift);

    }

}
