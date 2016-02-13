package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * This class describes the lift of the robot
 */
public class Lift implements Driver {

    private final float NORMAL_VERTICAL_SPEED = 0.5f, SLOW_VERTICAL_SPEED = 0.1f;
    private final float NORMAL_HORIZONTAL_SPEED = 0.5f, SLOW_HORIZONTAL_SPEED = 0.1f;
    private final float NORMAL_LINEAR_SPEED = 0.5f, SLOW_LINEAR_SPEED = 0.1f;

    private DcMotor _verticalLeftMotor, _verticalRightMotor, _linearLeftMotor, _linearRightMotor, _verticalMotor;
    private float _currentVerticalSpeed,  _currentHorizontalSpeed, _currentLinearSpeed;
    private boolean _3MotorMode, __reverseVertical;
    private Gamepad _gamepad;

    public Lift(Gamepad gamepad, HardwareMap map, String linearLeftMotor, String linearRightMotor, String verticalLeftMotor, String verticalRightMotor){

        //this will setup the lift using 4 motors
        _gamepad = gamepad;
        _linearLeftMotor = map.dcMotor.get(linearLeftMotor);
        _linearRightMotor = map.dcMotor.get(linearRightMotor);
        _verticalLeftMotor = map.dcMotor.get(verticalLeftMotor);
        _verticalRightMotor = map.dcMotor.get(verticalRightMotor);
        _3MotorMode = false;

    }

    public Lift(Gamepad gamepad, HardwareMap map, String linearLeftMotor, String linearRightMotor, String verticalMotor, boolean reverseVertical){

        //this will setup the lift using 3 motors
        _gamepad = gamepad;
        _linearLeftMotor = map.dcMotor.get(linearLeftMotor);
        _linearRightMotor = map.dcMotor.get(linearRightMotor);
        _verticalMotor = map.dcMotor.get(verticalMotor);
        __reverseVertical = reverseVertical;
        _3MotorMode = true;

    }

    private Movement drive(){

        //this will set the current speed based on whether the right bumper is pressed
        if(_gamepad.right_bumper){

            _currentHorizontalSpeed = SLOW_HORIZONTAL_SPEED;
            _currentLinearSpeed = SLOW_LINEAR_SPEED;
            _currentVerticalSpeed = SLOW_VERTICAL_SPEED;

        } else {

            _currentHorizontalSpeed = NORMAL_HORIZONTAL_SPEED;
            _currentLinearSpeed = NORMAL_LINEAR_SPEED;
            _currentVerticalSpeed = NORMAL_VERTICAL_SPEED;

        }

        //this will calculate the speeds based on the gamepad and the current speed
        float horizontalSpeed = _gamepad.left_stick_x * _currentHorizontalSpeed;
        float verticalSpeed = _gamepad.left_stick_y * _currentVerticalSpeed;
        float linearSpeed = _gamepad.right_stick_y * _currentLinearSpeed;

        //this will clip the linear speed to stop motor wrap around
        float linearLeftPower = Range.clip(linearSpeed, -1, 1);
        float linearRightPower = Range.clip(-linearSpeed, -1, 1);

        //this will set the extending motors powers
        _linearLeftMotor.setPower(linearLeftPower);
        _linearRightMotor.setPower(linearRightPower);

        if(_3MotorMode) {

            //this will clip the vertical speed to stop motor wrap around
            float verticalPower = Range.clip((__reverseVertical ? -1 : 1) * verticalSpeed, -1, 1);

            //this will set the vertical motor's power
            _verticalMotor.setPower(verticalPower);

        } else {

            //this will clip the vertical speed to stop motor wrap around
            float verticalLeftPower = Range.clip(verticalSpeed, -1, 1);
            float verticalRightPower = Range.clip(-verticalSpeed, -1, 1);

            //this will set the vertical motors powers
            _verticalLeftMotor.setPower(verticalLeftPower);
            _verticalRightMotor.setPower(verticalRightPower);

        }

        //this will return the horizontal speed (spin speed) for the drive train to move
        return new Movement(horizontalSpeed, 0, 0);

    }

    @Override
    public Movement getMovement() {

        //this will drive the lift then return a movement call
        return drive();

    }
}